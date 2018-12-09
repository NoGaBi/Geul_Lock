package pj_bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PJ_Bank{

	private static Scanner sc;

	private static void UserHome(Connection conn, String User_ID) {// ������ �޴�
		// TODO
	}

	private static void BranchManage(Connection conn, String Admin_ID) {// ���� ����
		// TODO
	}

	private static void UserManage(Connection conn, String Admin_ID) {// ������ ����
		// TODO
	}

	private static void AdminManage(Connection conn, String Admin_ID) {// ������ �λ� ����
		// TODO
	}

	private static void SavingManage(Connection conn, String Admin_ID) {// ��/���� ���� ����
		// TODO
	}

	private static void LoanManage(Connection conn, String Admin_ID) {// �������� ����
		// TODO
	}

	private static void LoanProductManage(Connection conn, String Admin_ID) {// ������ǰ ����
		// TODO
	}

	private static void SavingProductManage(Connection conn, String Admin_ID) {// ��/���� ��ǰ ����
		// TODO
	}

	private static void CEOAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String command;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.����� ����\n2.��/���� ���� ����\n3.���� ���� ����\n4.���� ����\n5.�λ� ����\n6.�α׾ƿ�");
			command = sc.nextLine();
			if (command.equals("1")) {
				UserManage(conn, Admin_ID);
			} else if (command.equals("2")) {
				SavingManage(conn, Admin_ID);
			} else if (command.equals("3")) {
				LoanManage(conn, Admin_ID);
			} else if (command.equals("4")) {
				BranchManage(conn, Admin_ID);
			} else if (command.equals("5")) {
				StaffManagingAdmin(conn, Admin_ID);
			} else if (command.equals("6")) {
				break LoopHome;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		}
	}

	private static void RAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String command;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.����� ����\n2.��/���� ���� ����\n3.���� ���� ����\n4.�α׾ƿ�");
			command = sc.nextLine();
			if (command.equals("1")) {
				UserManage(conn, Admin_ID);
			} else if (command.equals("2")) {
				SavingManage(conn, Admin_ID);
			} else if (command.equals("3")) {
				LoanManage(conn, Admin_ID);
			} else if (command.equals("4")) {
				break LoopHome;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		}
	}

	private static void StaffManagingAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String command;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.����� ����\n2.��/���� ���� ����\n3.���� ���� ����\n4.�λ� ����\n5.�α׾ƿ�");
			command = sc.nextLine();
			if (command.equals("1")) {
				UserManage(conn, Admin_ID);
			} else if (command.equals("2")) {
				SavingManage(conn, Admin_ID);
			} else if (command.equals("3")) {
				LoanManage(conn, Admin_ID);
			} else if (command.equals("4")) {
				StaffManagingAdmin(conn, Admin_ID);
			} else if (command.equals("5")) {
				break LoopHome;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		}
	}

	private static void ProductAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String command;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.����� ����\n2.��/���� ���� ����\n3.���� ���� ����\n4.���� ��ǰ ����\n5.��/���� ��ǰ ����\n6.�α׾ƿ�");
			command = sc.nextLine();
			if (command.equals("1")) {
				UserManage(conn, Admin_ID);
			} else if (command.equals("2")) {
				SavingManage(conn, Admin_ID);
			} else if (command.equals("3")) {
				LoanManage(conn, Admin_ID);
			} else if (command.equals("4")) {
				LoanProductManage(conn, Admin_ID);
			} else if (command.equals("5")) {
				SavingProductManage(conn, Admin_ID);
			} else if (command.equals("6")) {
				break LoopHome;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		}
	}

	private static void AdminMode(Connection conn) throws Exception {// DONE
		sc = new Scanner(System.in);
		String adminID, password, command, sql;

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
						command = sc.nextLine();

						if (command.equals("1"))
							break;
						else if (command.equals("2"))
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
					command = sc.nextLine();

					if (command.equals("1"))
						break;
					else if (command.equals("2"))
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
		String userID, password, command, sql;

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
						command = sc.nextLine();

						if (command.equals("1"))
							break;
						else if (command.equals("2"))
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
					command = sc.nextLine();

					if (command.equals("1"))
						break;
					else if (command.equals("2"))
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
		String sql, command, Name, User_ID, Phone, Address, Admin, Branch, Password;
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

				command = sc.nextLine();
				if (command.equals("1"))
					break Loop1;
				else if (command.equals("2")) {
					break Loop2;
				}else if (command.equals("3")) {
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
					command = sc.nextLine();
					if (command.equals("1")) {
						AddUser(conn);
						return;
					} else if (command.equals("2")) {
						break;
					} else if (command.equals("3")) {
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
		// Connection ��ü�� �ڵ��ϼ����� import�� ���� com.mysql.connection�� �ƴ�
		// java ǥ���� java.sql.Connection Ŭ������ import�ؾ� �Ѵ�.
		Connection conn = null;
		sc = new Scanner(System.in);
		String userName, password, command;

		while (true) {
			System.out.print("root Username: ");
			userName = sc.nextLine();
			System.out.print("root Password: ");
			password = sc.nextLine();

			try {
				// 1. �����̹� �ε�
				// �����̹� �������̽��� ������ Ŭ������ �ε�
				// mysql, oracle �� �� ������ ���� Ŭ���� �̸��� �ٸ���.
				// mysql�� "com.mysql.jdbc.Driver"�̸�, �̴� �ܿ��� ���� �ƴ϶� ���۸��ϸ� �ȴ�.
				// ������ ������ �����ߴ� jar ������ ���� com.mysql.jdbc ��Ű���� Driver ���� Ŭ������ �ִ�.
				Class.forName("com.mysql.jdbc.Driver");

				// 2. �����ϱ�
				// �����̹� �Ŵ������� Connection ��ü�� �޶��� ��û�Ѵ�.
				// Connection�� ���� ���� �ʿ��� url ����, �����縶�� �ٸ���.
				// mysql�� "jdbc:mysql://localhost/������db�̸�" �̴�.
				String url = "jdbc:mysql://localhost:3306/bank?characterEncoding=UTF-8&serverTimezone=UTC";

				// @param getConnection(url, userName, password);
				// @return Connection
				conn = DriverManager.getConnection(url, userName, password);
				System.out.println("�����ͺ��̽� ���� ����");

				while (true) {
					System.out.println("\n�α��� ����� ������ �ּ���....\n1.������ �α���\n2.����� �α���\n3.�ű� ����� ���\n4.���α׷� ����");
					command = sc.nextLine();

					try {
						if (command.equals("1")) {
							AdminMode(conn);
						} else if (command.equals("2")) {
							UserMode(conn);
						} else if (command.equals("3")) {
							AddUser(conn);
						} else if (command.equals("4")) {
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
