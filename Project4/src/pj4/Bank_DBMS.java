package pj4;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Bank_DBMS{

	private static Scanner sc;

	private static void UserHome(Connection conn, String User_ID) {// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Þ´ï¿½
		// TODO
	}

	private static void BranchManage(Connection conn, String Admin_ID) {// ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
		// TODO
	}

	private static void UserManage(Connection conn, String Admin_ID) {// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
		// TODO
	}

	private static void AdminManage(Connection conn, String Admin_ID) {// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Î»ï¿½ ï¿½ï¿½ï¿½ï¿½
		// TODO
	}

	private static void SavingManage(Connection conn, String Admin_ID) {// ï¿½ï¿½/ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
		// TODO
	}

	private static void LoanManage(Connection conn, String Admin_ID) {// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
		// TODO
	}

	private static void LoanProductManage(Connection conn, String Admin_ID) {// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ç° ï¿½ï¿½ï¿½ï¿½
		// TODO
	}

	private static void SavingProductManage(Connection conn, String Admin_ID) {// ï¿½ï¿½/ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ç° ï¿½ï¿½ï¿½ï¿½
		// TODO
	}

	private static void CEOAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String command;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.»ç¿ëÀÚ °ü¸®\n2.¿¹/Àû±Ý °èÁÂ °ü¸®\n3.´ëÃâ °èÁÂ °ü¸®\n4.Áö»ç °ü¸®\n5.ÀÎ»ç °ü¸®\n6.·Î±×¾Æ¿ô");
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
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				continue;
			}
		}
	}

	private static void RAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String command;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.»ç¿ëÀÚ °ü¸®\n2.¿¹/Àû±Ý °èÁÂ °ü¸®\n3.´ëÃâ °èÁÂ °ü¸®\n4.·Î±×¾Æ¿ô");
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
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				continue;
			}
		}
	}

	private static void StaffManagingAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String command;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.»ç¿ëÀÚ °ü¸®\n2.¿¹/Àû±Ý °èÁÂ °ü¸®\n3.´ëÃâ °èÁÂ °ü¸®\n4.ÀÎ»ç °ü¸®\n5.·Î±×¾Æ¿ô");
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
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				continue;
			}
		}
	}

	private static void ProductAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String command;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.»ç¿ëÀÚ °ü¸®\n2.¿¹/Àû±Ý °èÁÂ °ü¸®\n3.´ëÃâ °èÁÂ °ü¸®\n4.´ëÃâ »óÇ° °ü¸®\n5.¿¹/Àû±Ý »óÇ° °ü¸®\n6.·Î±×¾Æ¿ô");
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
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
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
					System.out.println("·Î±×ÀÎ ¼º°ø\n\nÈ¯¿µÇÕ´Ï´Ù.");
					break;
				} else {
					while (true) {
						System.out.println("\n.ºñ¹Ð¹øÈ£°¡ Æ²·È°Å³ª, Á¸ÀçÇÏÁö ¾Ê´Â °ü¸®ÀÚ ID ÀÔ´Ï´Ù.");
						System.out.println("1.ºñ¹Ð¹øÈ£ ÀçÀÔ·Â");
						System.out.println("2.ÃÊ±âÈ­¸éÀ¸·Î µ¹¾Æ°¡±â");
						command = sc.nextLine();

						if (command.equals("1"))
							break;
						else if (command.equals("2"))
							return;
						else
							System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				while (true) {
					System.out.println("\n.ºñ¹Ð¹øÈ£°¡ Æ²·È°Å³ª, Á¸ÀçÇÏÁö ¾Ê´Â °ü¸®ÀÚ ID ÀÔ´Ï´Ù.");
					System.out.println("1.ºñ¹Ð¹øÈ£ ÀçÀÔ·Â");
					System.out.println("2.ÃÊ±âÈ­¸éÀ¸·Î µ¹¾Æ°¡±â");
					command = sc.nextLine();

					if (command.equals("1"))
						break;
					else if (command.equals("2"))
						return;
					else
						System.out.println("\nÀß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				}
			}

		}

		sql = "SELECT Admin_ID, Position From Admin WHERE Admin_ID='" + adminID + "'";
		rs = stmt.executeQuery(sql);
		rs.next();
		if (rs.getString(2).equals("CEO")) {
			CEOAdmin(conn, adminID);
		} else if (rs.getString(2).equals("ÀÎ»çÆÀ")) {
			StaffManagingAdmin(conn, adminID);// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ß°ï¿½/ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
		} else if (rs.getString(2).equals("±âÈ¹ÆÀ")) {
			ProductAdmin(conn, adminID);// ï¿½ï¿½Ç° ï¿½ß°ï¿½/ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
		} else {
			RAdmin(conn, adminID);// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ß°ï¿½ ï¿½Ò°ï¿½, ï¿½Ú±ï¿½ ï¿½ï¿½ï¿½ï¿½ Userï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
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
					System.out.println("·Î±×ÀÎ¿¡ ¼º°øÇÏ¿´½À´Ï´Ù.\nÈ¯¿µÇÕ´Ï´Ù.");
					break;
				} else {
					while (true) {
						System.out.println("\n.ºñ¹Ð¹øÈ£°¡ Æ²·È°Å³ª, Á¸ÀçÇÏÁö ¾Ê´Â °ü¸®ÀÚ ID ÀÔ´Ï´Ù.");
						System.out.println("1.ºñ¹Ð¹øÈ£ ÀçÀÔ·Â");
						System.out.println("2.ÃÊ±âÈ­¸éÀ¸·Î µ¹¾Æ°¡±â");
						command = sc.nextLine();

						if (command.equals("1"))
							break;
						else if (command.equals("2"))
							return;
						else
							System.out.println("\nÀß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				while (true) {
					System.out.println("\n.ºñ¹Ð¹øÈ£°¡ Æ²·È°Å³ª, Á¸ÀçÇÏÁö ¾Ê´Â °ü¸®ÀÚ ID ÀÔ´Ï´Ù.");
					System.out.println("1.ºñ¹Ð¹øÈ£ ÀçÀÔ·Â");
					System.out.println("2.ÃÊ±âÈ­¸éÀ¸·Î µ¹¾Æ°¡±â");
					command = sc.nextLine();

					if (command.equals("1"))
						break;
					else if (command.equals("2"))
						return;
					else
						System.out.println("\nÀß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
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
			// TODO: ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ê°ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½Ã³ï¿½ï¿½
			System.out.print("µî·ÏÇÏ½Ç »ç¿ëÀÚÀÇ Á¤º¸¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä.\nÀÌ¸§(20ÀÚ ÀÌ³»): ");
			Name = sc.nextLine();
			System.out.print("ÀüÈ­¹øÈ£(15ÀÚ ÀÌ³»): ");
			Phone = sc.nextLine();
			System.out.print("ÁÖ¼ÒÁö(30ÀÚ ÀÌ³»): ");
			Address = sc.nextLine();
			System.out.print("ºñ¹Ð¹øÈ£(20ÀÚ ÀÌ³»): ");
			Password = sc.nextLine();

			System.out.println("ÀÔ·ÂÇÏ½Å Á¤º¸ È®ÀÎ");
			System.out.println("ÀÌ¸§: " + Name + "\nÀüÈ­¹øÈ£: " + Phone + "\nÁÖ¼ÒÁö: " + Address);
			Loop2: while (true) {
				System.out.println("\n´ÙÀ½À¸·Î ÁøÇàÇÏ½Ã·Á¸é '1', Á¤º¸¸¦ ´Ù½Ã ÀÔ·ÂÇÏ½Ã·Á¸é '2', Ãë¼ÒÇÏ½Ã·Á¸é '3'À» ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");

				command = sc.nextLine();
				if (command.equals("1"))
					break Loop1;
				else if (command.equals("2")) {
					break Loop2;
				}else if (command.equals("3")) {
					return;
				} else {
					System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				}
			}
		}
		while (true) {
			System.out.print("ºñ¹Ð¹øÈ£ ÀçÈ®ÀÎ: ");
			String passTemp = sc.nextLine();
			if (passTemp.equals(Password))
				break;
			else {
				while (true) {
					System.out.println("Ã³À½ ÀÔ·ÂÇÏ½Å ºñ¹Ð¹øÈ£¿Í ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.\n1.Á¤º¸ ÀÔ·ÂÀ¸·Î µ¹¾Æ°¡±â\n2.ºñ¹Ð¹øÈ£ ÀçÈ®ÀÎ\n3.µî·Ï Ãë¼Ò");
					command = sc.nextLine();
					if (command.equals("1")) {
						AddUser(conn);
						return;
					} else if (command.equals("2")) {
						break;
					} else if (command.equals("3")) {
						return;
					} else {
						System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
					}

				}
			}
		}

		System.out.println("\nï¿½ï¿½ï¿½Ð¹ï¿½È£ ï¿½ï¿½È®ï¿½Î¿ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ï¿ï¿½ï¿½ï¿½ï¿½Ï´ï¿½.");

		// User_ID ï¿½ï¿½ï¿½ï¿½
		sql = "SELECT table_name, table_rows FROM information_schema.tables WHERE table_schema = 'bank' AND table_name='user'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		rs.next();
		int sizeOfUser = rs.getInt(2);
		User_ID = String.valueOf(sizeOfUser + 1);
		User_ID = "000000000".substring(User_ID.length()) + User_ID;
		// System.out.println(User_ID);
		System.out.println("µî·ÏÇÏ½Å »ç¿ëÀÚÀÇ »ç¿ëÀÚ ID´Â '" + User_ID + "' ÀÔ´Ï´Ù.");

		// Branch ï¿½Ô·Â¹Þ±ï¿½
		sql = "SELECT * FROM branch ORDER BY Branch_ID";
		rs = stmt.executeQuery(sql);
		System.out.println("µî·ÏÇÏ°í ½ÍÀ¸½Å Áö»çÀÇ Áö»ç ID¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä.\n");
		while (rs.next()) {
			System.out.println(
					"#Áö»ç ID: " + rs.getString(1) + "#¼ÒÀçÁö: " + rs.getString(2) + "#ÀüÈ­¹øÈ£: " + rs.getString(4) + "#");
		}
		while (true) {// ï¿½ï¿½ï¿½ï¿½ï¿½Ï´ï¿½ Branchï¿½ï¿½ï¿½ï¿½ È®ï¿½ï¿½
			Branch = sc.nextLine();
			sql = "SELECT COUNT(*) FROM branch WHERE Branch_ID='" + Branch + "'";
			rs = stmt.executeQuery(sql);
			rs.next();
			if (rs.getInt(1) == 1)
				break;
			else {
				System.out.println("Á¸ÀçÇÏÁö ¾Ê´Â Áö»ç IDÀÔ´Ï´Ù. ÀÔ·ÂÀ» È®ÀÎÇØ ÁÖ¼¼¿ä.");
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

			System.out.println("¹èÁ¤¹ÞÀ¸½Å ´ã´çÀÚ Á¤º¸´Â ´ÙÀ½°ú °°½À´Ï´Ù.");

			System.out.println("ÀÌ¸§: " + rs.getString(2) + ", ¿¬¶ôÃ³: " + rs.getString(3) + ", Á÷Ã¥: " + rs.getString(4));

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
			System.out.println("¹èÁ¤¹ÞÀ¸½Å ´ã´çÀÚ Á¤º¸´Â ´ÙÀ½°ú °°½À´Ï´Ù.");

			System.out.println("ÀÌ¸§: " + rs.getString(2) + ", ¿¬¶ôÃ³: " + rs.getString(3) + ", Á÷Ã¥: " + rs.getString(4));
		}

		Credit_rate = 5;
		sql = "insert into user values ('" + Name + "', '" + User_ID + "', '" + Phone + "', " + Credit_rate + ", '"
				+ Address + "', '" + Admin + "', '" + Branch + "', '" + Password + "')";
		stmt.execute(sql);
	}

	public static void main() {// DONE
		// Connection ï¿½ï¿½Ã¼ï¿½ï¿½ ï¿½Úµï¿½ï¿½Ï¼ï¿½ï¿½ï¿½ï¿½ï¿½ importï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ com.mysql.connectionï¿½ï¿½ ï¿½Æ´ï¿½
		// java Ç¥ï¿½ï¿½ï¿½ï¿½ java.sql.Connection Å¬ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ importï¿½Ø¾ï¿½ ï¿½Ñ´ï¿½.
		Connection conn = null;
		sc = new Scanner(System.in);
		String userName, password, command;

		while (true) {
			System.out.print("root Username: ");
			userName = sc.nextLine();
			System.out.print("root Password: ");
			password = sc.nextLine();

			try {
				// 1. ï¿½ï¿½ï¿½ï¿½ï¿½Ì¹ï¿½ ï¿½Îµï¿½
				// ï¿½ï¿½ï¿½ï¿½ï¿½Ì¹ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ì½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ Å¬ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Îµï¿½
				// mysql, oracle ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ Å¬ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¸ï¿½ï¿½ï¿½ ï¿½Ù¸ï¿½ï¿½ï¿½.
				// mysqlï¿½ï¿½ "com.mysql.jdbc.Driver"ï¿½Ì¸ï¿½, ï¿½Ì´ï¿½ ï¿½Ü¿ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Æ´Ï¶ï¿½ ï¿½ï¿½ï¿½Û¸ï¿½ï¿½Ï¸ï¿½ ï¿½È´ï¿½.
				// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ß´ï¿½ jar ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ com.mysql.jdbc ï¿½ï¿½Å°ï¿½ï¿½ï¿½ï¿½ Driver ï¿½ï¿½ï¿½ï¿½ Å¬ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ö´ï¿½.
				Class.forName("com.mysql.jdbc.Driver");

				// 2. ï¿½ï¿½ï¿½ï¿½ï¿½Ï±ï¿½
				// ï¿½ï¿½ï¿½ï¿½ï¿½Ì¹ï¿½ ï¿½Å´ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ Connection ï¿½ï¿½Ã¼ï¿½ï¿½ ï¿½Þ¶ï¿½ï¿½ï¿½ ï¿½ï¿½Ã»ï¿½Ñ´ï¿½.
				// Connectionï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Ê¿ï¿½ï¿½ï¿½ url ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½ï¿½ç¸¶ï¿½ï¿½ ï¿½Ù¸ï¿½ï¿½ï¿½.
				// mysqlï¿½ï¿½ "jdbc:mysql://localhost/ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½dbï¿½Ì¸ï¿½" ï¿½Ì´ï¿½.
				String url = "jdbc:mysql://localhost:3306/bank?characterEncoding=UTF-8&serverTimezone=UTC";

				// @param getConnection(url, userName, password);
				// @return Connection
				conn = DriverManager.getConnection(url, userName, password);
				System.out.println("µ¥ÀÌÅÍº£ÀÌ½º ¿¬°á ¼º°ø");

				while (true) {
					System.out.println("\n·Î±×ÀÎ ¹æ½ÄÀ» ¼±ÅÃÇØ ÁÖ¼¼¿ä....\n1.°ü¸®ÀÚ ·Î±×ÀÎ\n2.»ç¿ëÀÚ ·Î±×ÀÎ\n3.½Å±Ô »ç¿ëÀÚ µî·Ï\n4.ÇÁ·Î±×·¥ Á¾·á");
					command = sc.nextLine();

					try {
						if (command.equals("1")) {
							AdminMode(conn);
						} else if (command.equals("2")) {
							UserMode(conn);
						} else if (command.equals("3")) {
							AddUser(conn);
						} else if (command.equals("4")) {
							System.out.println("ÇÁ·Î±×·¥À» Á¾·áÇÕ´Ï´Ù...");
							Thread.sleep(1500);
							break;
						} else {
							Exception a = new Exception("\nÀß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
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
				System.out.println("µð¹ÙÀÌ½º¸¦ ºÒ·¯¿ÀÁö ¸øÇß½À´Ï´Ù");
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
