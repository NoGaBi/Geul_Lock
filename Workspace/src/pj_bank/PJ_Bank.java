package pj_bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PJ_Bank {

	private static Scanner sc;

	private static void UserHome(Connection conn, String User_ID) throws Exception {
		sc = new Scanner(System.in);
		String accountID, savingID, loanID, product, sql;
		int cmd;
		long money;
		Statement stmt = null;
		ResultSet rs = null;

		stmt = conn.createStatement();
		LoopHome: while (true) {
			System.out.println("#Home#");
			System.out.println("1.��/���� ���� ��ȸ");
			System.out.println("2.��/���� ���� �űԹ߱�");
			System.out.println("3.���� ���� ��ȸ");
			System.out.println("4.���� ���� �űԹ߱�");
			System.out.println("5.�α׾ƿ�");
			cmd = sc.nextInt();

			if (cmd == 1) {// ������ ��ȸ
				sql = "SELECT Saving_ID, Saved_money, Start_date, Product FROM saving_account WHERE User='" + User_ID
						+ "'";

				rs = stmt.executeQuery(sql);

				System.out.println("|���¹�ȣ|��ġ�ݾ�|��������|��ǰ��|");

				int i;
				for (i = 1; rs.next(); i++) {
					System.out.println("|" + rs.getString(1) + "|" + rs.getLong(2) + "|" + rs.getDate(3) + "|"
							+ rs.getString(4) + "|");
				}
				if (i == 1)
					System.out.println("|���� ����|");

				System.out.println("-------------------------------------------------------");
				LoopSaving: while (true) {
					System.out.println("1.������ü(���� to ����)");
					System.out.println("2.�����Ա�");
					System.out.println("3.�������");
					System.out.println("4.���� ��ȯ(���� to ����)");
					System.out.println("5.Ȩ���� ���ư���");

					cmd = sc.nextInt();
					if (cmd == 1) {// ������ü
						System.out.print("�Ա� ���¹�ȣ: ");
						savingID = sc.nextLine();
						System.out.print("��� ���¹�ȣ: ");
						accountID = sc.nextLine();
						System.out.print("�ݾ�(��): ");
						money = sc.nextLong();

						sql = "SELECT * FROM saving_account WHERE Account_ID='" + accountID + "'";
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("��� ���¹�ȣ�� �ùٸ��� �ʽ��ϴ�.");
							Thread.sleep(200);
							continue;
						}
						if (!rs.getString(6).equals(User_ID)) {
							System.out.println("��� ���°� ������ ���°� �ƴմϴ�.");
							Thread.sleep(200);
							continue;
						}
						if (rs.getLong(2) < money) {
							System.out.println("�ܾ��� �����մϴ�.");
							Thread.sleep(200);
							continue;
						}

						sql = "SELECT * FROM saving_account WHERE Account_ID='" + savingID + "'";
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("�Ա� ���¹�ȣ�� �ùٸ��� �ʽ��ϴ�.");
							Thread.sleep(200);
							continue;
						}

						sql = "UPDATE saving_account SET Saved_money=" + (rs.getLong(2) + money) + "WHERE Account_ID='"
								+ savingID + "'";
						stmt.execute(sql);

						sql = "SELECT * FROM saving_account WHERE Account_ID='" + accountID + "'";
						rs = stmt.executeQuery(sql);

						sql = "UPDATE saving_account SET Saved_money=" + (rs.getLong(2) - money) + "WHERE Account_ID='"
								+ accountID + "'";
						stmt.execute(sql);
						break LoopSaving;

					} else if (cmd == 2) {// �����Ա�
						// TODO
					} else if (cmd == 3) {// �������
						// TODO
					} else if (cmd == 4) {// ������ü ���� ��ȯ
						// TODO
					} else if (cmd == 5) {// Ȩ
						break LoopSaving;
					} else {
						System.out.println("�߸��� �Է��Դϴ�.");
					}
				}

			} else if (cmd == 2) {// ������ �ű�

				LoopNewSaving: while (true) {
					sql = "SELECT kinds_of_saving.* FROM kinds_of_saving, user WHERE User_ID='" + User_ID
							+ "' AND Affordable_Credit_rate >= Credit_rate";
					rs = stmt.executeQuery(sql);
					System.out.println("�����ϰ� ������ ��ǰ���� �Է��� �ּ���.\n|��ǰ��|�ִ뿹�ݾ�|���԰��ɽſ뵵|����|");
					int i;
					for (i = 1; rs.next(); i++) {
						System.out.println("|" + rs.getString(1) + "|" + rs.getLong(2) + "|" + rs.getInt(3) + "|"
								+ rs.getBigDecimal(4) + "|");
					}
					if (i == 1) {
						System.out.println("|���� ���� ��ǰ ����|");
						break LoopNewSaving;
					}

					System.out.print("������ ��ǰ��: ");
					product = sc.next();

					sql = "SELECT Product_Name FROM kinds_of_saving, user WHERE Affordable_Credit_rate >= Credit_rate AND Product_Name='"
							+ product + "'";
					rs = stmt.executeQuery(sql);
					if (!rs.next()) {
						while (true) {
							System.out.println("�Է��Ͻ� ��ǰ�� �������� �ʽ��ϴ�.\n1.��ǰ�� �ٽ� ����\n2.�ű� ���� ���");
							cmd = sc.nextInt();

							if (cmd == 1) {
								continue LoopNewSaving;
							} else if (cmd == 2) {
								break LoopNewSaving;
							} else {
								System.out.println("�߸��� �Է��Դϴ�.");
							}
						}
					} else {

						sql = "SELECT table_name, table_rows FROM information_schema.tables WHERE table_schema='bank' AND table_name='saving_account'";
						rs = stmt.executeQuery(sql);
						rs.next();
						int sizeOfSaving = rs.getInt(2);
						accountID = String.valueOf(sizeOfSaving + 1);
						accountID = "000000000".substring(accountID.length()) + accountID;

						System.out.println("�ű� ���� ID�� '" + accountID + "' �Դϴ�.");
						java.util.Date utilDate = new java.util.Date();
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						sql = "INSERT INTO saving_account values ('" + accountID + "', 0, '" + sqlDate + "', '"
								+ User_ID + "', '" + product + "')";
						stmt.execute(sql);
						break LoopNewSaving;
					}
				}
				// TODO
			} else if (cmd == 3) {// ���� ��ȸ
				// TODO
			} else if (cmd == 4) {// ���� �ű�
				// TODO
			} else if (cmd == 5) {// �α׾ƿ�
				break LoopHome;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		}
	}

	private static void BranchManage(Connection conn, String Admin_ID) throws Exception {
		// TODO
	}

	private static void UserManage(Connection conn, String Admin_ID) throws Exception {
		// TODO
	}

	private static void SavingManage(Connection conn, String Admin_ID) throws Exception {
		// TODO
	}

	private static void LoanManage(Connection conn, String Admin_ID) throws Exception {
		// TODO
	}

	private static void LoanProductManage(Connection conn, String Admin_ID) throws Exception {
		// TODO
	}

	private static void SavingProductManage(Connection conn, String Admin_ID) throws Exception {
		// TODO
	}

	private static void CEOAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String cmd;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.����� ����\n2.��/���� ���� ����\n3.���� ���� ����\n4.���� ����\n5.�λ� ����\n6.�α׾ƿ�");
			cmd = sc.nextLine();
			if (cmd.equals("1")) {
				UserManage(conn, Admin_ID);
			} else if (cmd.equals("2")) {
				SavingManage(conn, Admin_ID);
			} else if (cmd.equals("3")) {
				LoanManage(conn, Admin_ID);
			} else if (cmd.equals("4")) {
				BranchManage(conn, Admin_ID);
			} else if (cmd.equals("5")) {
				StaffManagingAdmin(conn, Admin_ID);
			} else if (cmd.equals("6")) {
				break LoopHome;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		}
	}

	private static void RAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String cmd;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.����� ����\n2.��/���� ���� ����\n3.���� ���� ����\n4.�α׾ƿ�");
			cmd = sc.nextLine();
			if (cmd.equals("1")) {
				UserManage(conn, Admin_ID);
			} else if (cmd.equals("2")) {
				SavingManage(conn, Admin_ID);
			} else if (cmd.equals("3")) {
				LoanManage(conn, Admin_ID);
			} else if (cmd.equals("4")) {
				break LoopHome;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		}
	}

	private static void StaffManagingAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String cmd;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.����� ����\n2.��/���� ���� ����\n3.���� ���� ����\n4.�λ� ����\n5.�α׾ƿ�");
			cmd = sc.nextLine();
			if (cmd.equals("1")) {
				UserManage(conn, Admin_ID);
			} else if (cmd.equals("2")) {
				SavingManage(conn, Admin_ID);
			} else if (cmd.equals("3")) {
				LoanManage(conn, Admin_ID);
			} else if (cmd.equals("4")) {
				StaffManagingAdmin(conn, Admin_ID);
			} else if (cmd.equals("5")) {
				break LoopHome;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		}
	}

	private static void ProductAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String cmd;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.����� ����\n2.��/���� ���� ����\n3.���� ���� ����\n4.���� ��ǰ ����\n5.��/���� ��ǰ ����\n6.�α׾ƿ�");
			cmd = sc.nextLine();
			if (cmd.equals("1")) {
				UserManage(conn, Admin_ID);
			} else if (cmd.equals("2")) {
				SavingManage(conn, Admin_ID);
			} else if (cmd.equals("3")) {
				LoanManage(conn, Admin_ID);
			} else if (cmd.equals("4")) {
				LoanProductManage(conn, Admin_ID);
			} else if (cmd.equals("5")) {
				SavingProductManage(conn, Admin_ID);
			} else if (cmd.equals("6")) {
				break LoopHome;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		}
	}

	private static void AdminMode(Connection conn) throws Exception {// DONE
		sc = new Scanner(System.in);
		String adminID, password, cmd, sql;

		Statement stmt = null;
		ResultSet rs = null;

		sql = "SELECT Admin_ID, Password FROM Admin";

		stmt = conn.createStatement();
		while (true) {
			System.out.print("Admin ID: ");
			adminID = sc.nextLine();
			System.out.print("Password: ");
			password = sc.nextLine();

			sql += " WHERE Admin_ID='" + adminID + "'";
			// System.out.println("sql statement is: " + sql);

			rs = stmt.executeQuery(sql);
			try {
				rs.next();
				String temp = rs.getString(2);
				if (password.equals(temp)) {
					System.out.println("�α��� ����\n\nȯ���մϴ�.");
					break;
				} else {
					while (true) {
						System.out.println("\n.��й�ȣ�� Ʋ�Ȱų�, �������� �ʴ� ������ ID �Դϴ�.");
						System.out.println("1.��й�ȣ ���Է�");
						System.out.println("2.�ʱ�ȭ������ ���ư���");
						cmd = sc.nextLine();

						if (cmd.equals("1"))
							break;
						else if (cmd.equals("2"))
							return;
						else
							System.out.println("�߸��� �Է��Դϴ�.");
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				while (true) {
					System.out.println("\n.��й�ȣ�� Ʋ�Ȱų�, �������� �ʴ� ������ ID �Դϴ�.");
					System.out.println("1.��й�ȣ ���Է�");
					System.out.println("2.�ʱ�ȭ������ ���ư���");
					cmd = sc.nextLine();

					if (cmd.equals("1"))
						break;
					else if (cmd.equals("2"))
						return;
					else
						System.out.println("\n�߸��� �Է��Դϴ�.");
				}
			}

		}

		sql = "SELECT Admin_ID, Position From Admin WHERE Admin_ID='" + adminID + "'";
		rs = stmt.executeQuery(sql);
		rs.next();
		if (rs.getString(2).equals("CEO")) {
			CEOAdmin(conn, adminID);
		} else if (rs.getString(2).equals("�λ���")) {
			StaffManagingAdmin(conn, adminID);// ������ �߰�/���� ����
		} else if (rs.getString(2).equals("��ȹ��")) {
			ProductAdmin(conn, adminID);// ��ǰ �߰�/���� ����
		} else {
			RAdmin(conn, adminID);// ������ �߰� �Ұ�, �ڱ� ���� User�� ���� ����
		}
	}

	private static void UserMode(Connection conn) throws Exception {// DONE
		sc = new Scanner(System.in);
		String userID, password, cmd, sql;

		Statement stmt = null;
		ResultSet rs = null;

		sql = "SELECT User_ID, Password FROM User";

		while (true) {
			System.out.print("User ID: ");
			userID = sc.nextLine();
			System.out.print("Password: ");
			password = sc.nextLine();

			sql += " WHERE User_ID='" + userID + "'";
			// System.out.println("sql statement is: " + sql);

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			try {
				rs.next();
				String temp = rs.getString(2);
				if (password.equals(temp)) {
					System.out.println("�α��ο� �����Ͽ����ϴ�.\nȯ���մϴ�.");
					break;
				} else {
					while (true) {
						System.out.println("\n.��й�ȣ�� Ʋ�Ȱų�, �������� �ʴ� ������ ID �Դϴ�.");
						System.out.println("1.��й�ȣ ���Է�");
						System.out.println("2.�ʱ�ȭ������ ���ư���");
						cmd = sc.nextLine();

						if (cmd.equals("1"))
							break;
						else if (cmd.equals("2"))
							return;
						else
							System.out.println("\n�߸��� �Է��Դϴ�.");
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				while (true) {
					System.out.println("\n.��й�ȣ�� Ʋ�Ȱų�, �������� �ʴ� ������ ID �Դϴ�.");
					System.out.println("1.��й�ȣ ���Է�");
					System.out.println("2.�ʱ�ȭ������ ���ư���");
					cmd = sc.nextLine();

					if (cmd.equals("1"))
						break;
					else if (cmd.equals("2"))
						return;
					else
						System.out.println("\n�߸��� �Է��Դϴ�.");
				}
			}
		}
		UserHome(conn, userID);
	}

	private static void AddUser(Connection conn) throws Exception {// DONE
		sc = new Scanner(System.in);

		Statement stmt = null;
		ResultSet rs = null;
		// user values (Name, User_ID, Phone, Credit_rate, Address, Admin, Branch,
		// Password)
		String sql, cmd, Name, User_ID, Phone, Address, Admin, Branch, Password;
		int Credit_rate;

		Loop1: while (true) {
			// TODO: �������� �ʰ��� ����ó��
			System.out.print("����Ͻ� ������� ������ �Է��� �ּ���.\n�̸�(20�� �̳�): ");
			Name = sc.nextLine();
			System.out.print("��ȭ��ȣ(15�� �̳�): ");
			Phone = sc.nextLine();
			System.out.print("�ּ���(30�� �̳�): ");
			Address = sc.nextLine();
			System.out.print("��й�ȣ(20�� �̳�): ");
			Password = sc.nextLine();

			System.out.println("�Է��Ͻ� ���� Ȯ��");
			System.out.println("�̸�: " + Name + "\n��ȭ��ȣ: " + Phone + "\n�ּ���: " + Address);
			Loop2: while (true) {
				System.out.println("\n�������� �����Ͻ÷��� '1', ������ �ٽ� �Է��Ͻ÷��� '2', ����Ͻ÷��� '3'�� �Է��� �ּ���.");

				cmd = sc.nextLine();
				if (cmd.equals("1"))
					break Loop1;
				else if (cmd.equals("2")) {
					break Loop2;
				} else if (cmd.equals("3")) {
					return;
				} else {
					System.out.println("�߸��� �Է��Դϴ�.");
				}
			}
		}
		while (true) {
			System.out.print("��й�ȣ ��Ȯ��: ");
			String passTemp = sc.nextLine();
			if (passTemp.equals(Password))
				break;
			else {
				while (true) {
					System.out.println("ó�� �Է��Ͻ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.\n1.���� �Է����� ���ư���\n2.��й�ȣ ��Ȯ��\n3.��� ���");
					cmd = sc.nextLine();
					if (cmd.equals("1")) {
						AddUser(conn);
						return;
					} else if (cmd.equals("2")) {
						break;
					} else if (cmd.equals("3")) {
						return;
					} else {
						System.out.println("�߸��� �Է��Դϴ�.");
					}

				}
			}
		}

		System.out.println("\n���й�ȣ ��Ȯ�ο� �����Ͽ����ϴ�.");

		// User_ID ����
		sql = "SELECT table_name, table_rows FROM information_schema.tables WHERE table_schema = 'bank' AND table_name='user'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		rs.next();
		int sizeOfUser = rs.getInt(2);
		User_ID = String.valueOf(sizeOfUser + 1);
		User_ID = "000000000".substring(User_ID.length()) + User_ID;
		// System.out.println(User_ID);
		System.out.println("����Ͻ� ������� ����� ID�� '" + User_ID + "' �Դϴ�.");

		// Branch �Է¹ޱ�
		sql = "SELECT * FROM branch ORDER BY Branch_ID";
		rs = stmt.executeQuery(sql);
		System.out.println("����ϰ� ������ ������ ���� ID�� �Է��� �ּ���.\n");
		while (rs.next()) {
			System.out.println(
					"#���� ID: " + rs.getString(1) + "#������: " + rs.getString(2) + "#��ȭ��ȣ: " + rs.getString(4) + "#");
		}
		while (true) {// �����ϴ� Branch���� Ȯ��
			Branch = sc.nextLine();
			sql = "SELECT COUNT(*) FROM branch WHERE Branch_ID='" + Branch + "'";
			rs = stmt.executeQuery(sql);
			rs.next();
			if (rs.getInt(1) == 1)
				break;
			else {
				System.out.println("�������� �ʴ� ���� ID�Դϴ�. �Է��� Ȯ���� �ּ���.");
			}
		}

		sql = "SELECT * FROM user";
		rs = stmt.executeQuery(sql);
		if (!rs.next()) {
			sql = "SELECT Admin_ID, Name, Phone, Position, Works_at, Num_of_User FROM admin WHERE Works_at='" + Branch
					+ "'";
			rs = stmt.executeQuery(sql);
			rs.next();
			Admin = rs.getString(1);

			System.out.println("���������� ����� ������ ������ �����ϴ�.");

			System.out.println("�̸�: " + rs.getString(2) + ", ����ó: " + rs.getString(3) + ", ��å: " + rs.getString(4));

			int numOfUser = rs.getInt(6) + 1;
			sql = "UPDATE admin SET Num_of_User=" + numOfUser + " WHERE Admin_ID='" + Admin + "'";
			stmt.execute(sql);
		} else {
			sql = "SELECT Admin_ID, Num_of_User FROM admin WHERE Works_at='" + Branch + "' ORDER BY Num_of_User";
			rs = stmt.executeQuery(sql);
			rs.next();
			Admin = rs.getString(1);

			int numOfUser = rs.getInt(2) + 1;
			sql = "UPDATE admin SET Num_of_User=" + numOfUser + " WHERE Admin_ID='" + Admin + "'";
			stmt.execute(sql);

			sql = "SELECT * FROM admin WHERE Admin_ID='" + Admin + "'";
			rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println("���������� ����� ������ ������ �����ϴ�.");

			System.out.println("�̸�: " + rs.getString(2) + ", ����ó: " + rs.getString(3) + ", ��å: " + rs.getString(4));
		}

		Credit_rate = 5;
		sql = "insert into user values ('" + Name + "', '" + User_ID + "', '" + Phone + "', " + Credit_rate + ", '"
				+ Address + "', '" + Admin + "', '" + Branch + "', '" + Password + "')";
		stmt.execute(sql);
	}

	public static void main(String[] args) {// DONE
		Connection conn = null;
		sc = new Scanner(System.in);
		String userName, password, cmd, port;

		while (true) {
			System.out.print("bank database port: ");
			port = sc.nextLine();
			System.out.print("root Username: ");
			userName = sc.nextLine();
			System.out.print("root Password: ");
			password = sc.nextLine();

			try {
				Class.forName("com.mysql.jdbc.Driver");

				String url = "jdbc:mysql://localhost:" + port + "/bank?characterEncoding=UTF-8&serverTimezone=UTC";

				// @param getConnection(url, userName, password);
				// @return Connection
				conn = DriverManager.getConnection(url, userName, password);
				System.out.println("�����ͺ��̽� ���� ����");

				while (true) {
					System.out.println("\n�α��� ����� ������ �ּ���....\n1.������ �α���\n2.����� �α���\n3.�ű� ����� ���\n4.���α׷� ����");
					cmd = sc.nextLine();

					try {
						if (cmd.equals("1")) {
							AdminMode(conn);
						} else if (cmd.equals("2")) {
							UserMode(conn);
						} else if (cmd.equals("3")) {
							AddUser(conn);
						} else if (cmd.equals("4")) {
							System.out.println("���α׷��� �����մϴ�...");
							Thread.sleep(1500);
							break;
						} else {
							Exception a = new Exception("\n�߸��� �Է��Դϴ�.");
							Thread.sleep(300);
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}
				break;
			} catch (ClassNotFoundException e) {
				System.out.println("����̽��� �ҷ����� ���߽��ϴ�");
			} catch (SQLException e) {
				System.out.println("ERROR: " + e.getMessage());
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
