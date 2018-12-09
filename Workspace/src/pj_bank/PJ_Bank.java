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
			System.out.println("1.예/적금 계좌 조회");
			System.out.println("2.예/적금 계좌 신규발급");
			System.out.println("3.대출 계좌 조회");
			System.out.println("4.대출 계좌 신규발급");
			System.out.println("5.로그아웃");
			cmd = sc.nextInt();

			if (cmd == 1) {// 예적금 조회
				LoopSaving: while (true) {
					sql = "SELECT Saving_ID, Saved_money, Start_date, Product FROM saving_account WHERE User='"
							+ User_ID + "'";

					rs = stmt.executeQuery(sql);

					System.out.println("|계좌번호|예치금액|시작일자|상품명|");

					int i;
					for (i = 1; rs.next(); i++) {
						System.out.println("|" + rs.getString(1) + "|" + rs.getLong(2) + "|" + rs.getDate(3) + "|"
								+ rs.getString(4) + "|");
					}
					if (i == 1)
						System.out.println("|계좌 없음|");

					System.out.println("-------------------------------------------------------");

					System.out.println("1.계좌이체(예금 to 예금)");
					System.out.println("2.예금입금");
					System.out.println("3.예금출금");
					System.out.println("4.대출 상환(예금 to 대출)");
					System.out.println("5.홈으로 돌아가기");

					cmd = sc.nextInt();
					if (cmd == 1) {// 계좌이체
						System.out.print("입금 계좌번호: ");
						savingID = sc.next();
						System.out.print("출금 계좌번호: ");
						accountID = sc.next();
						System.out.print("금액(원): ");
						money = sc.nextLong();

						sql = "SELECT * FROM saving_account WHERE Saving_ID='" + accountID + "'";
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("출금 계좌번호가 올바르지 않습니다.");
							Thread.sleep(200);
							continue;
						}
						if (!rs.getString(4).equals(User_ID)) {
							System.out.println("출금 계좌가 본인의 계좌가 아닙니다.");
							Thread.sleep(200);
							continue;
						}
						if (rs.getLong(2) < money) {
							System.out.println("잔액이 부족합니다.");
							Thread.sleep(200);
							continue;
						}

						sql = "SELECT * FROM saving_account WHERE Saving_ID='" + savingID + "'";
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("입금 계좌번호가 올바르지 않습니다.");
							Thread.sleep(200);
							continue;
						}

						sql = "UPDATE saving_account SET Saved_money=" + (rs.getLong(2) + money) + " WHERE Saving_ID='"
								+ savingID + "'";
						stmt.execute(sql);

						sql = "SELECT * FROM saving_account WHERE Saving_ID='" + accountID + "'";
						rs = stmt.executeQuery(sql);
						rs.next();

						sql = "UPDATE saving_account SET Saved_money=" + (rs.getLong(2) - money) + " WHERE Saving_ID='"
								+ accountID + "'";
						stmt.execute(sql);
						System.out.println("계좌이체가 완료되었습니다.");
						break LoopSaving;

					} else if (cmd == 2) {// 예금입금
						System.out.print("계좌번호: ");
						accountID = sc.next();
						System.out.print("입금 금액: ");
						money = sc.nextLong();

						sql = "SELECT Saving_ID, Saved_money, Max_Money FROM saving_account, kinds_of_saving WHERE Saving_ID='"
								+ accountID + "' AND Product=Product_Name";
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("일치하는 계좌가 없습니다.");
							continue LoopSaving;
						} else {
							if (rs.getLong(3) < rs.getLong(2) + money) {
								System.out.println("최대 예금액을 넘어섭니다.");
								continue LoopSaving;
							} else {
								sql = "UPDATE saving_account SET Saved_money=" + (rs.getLong(2) + money)
										+ " WHERE Saving_ID='" + accountID + "'";
								stmt.execute(sql);
								System.out.println("입금이 완료되었습니다.");
							}
						}
					} else if (cmd == 3) {// 예금출금
						System.out.print("계좌번호: ");
						accountID = sc.next();
						System.out.print("출금 희망 금액: ");
						money = sc.nextLong();

						sql = "SELECT Saving_ID, Saved_money, Max_Money FROM saving_account, kinds_of_saving WHERE Saving_ID='"
								+ accountID + "' AND Product=Product_Name AND User='" + User_ID + "'";
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("일치하는 본인의 계좌가 없습니다.");
							continue LoopSaving;
						} else {
							if (0 > rs.getLong(2) - money) {
								System.out.println("출금 희망액이 현재 예치금액을 넘어섭니다.");
								continue LoopSaving;
							} else {
								sql = "UPDATE saving_account SET Saved_money=" + (rs.getLong(2) - money)
										+ " WHERE Saving_ID='" + accountID + "'";
								stmt.execute(sql);
								System.out.println("출금이 완료되었습니다.");
							}
						}
					} else if (cmd == 4) {// 계좌이체 대출 상환
						System.out.print("대출 상환 계좌번호: ");
						loanID = sc.next();
						System.out.print("출금 계좌번호: ");
						accountID = sc.next();
						System.out.print("금액(원): ");
						money = sc.nextLong();

						sql = "SELECT * FROM saving_account WHERE Saving_ID='" + accountID + "'";
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("출금 계좌번호가 올바르지 않습니다.");
							Thread.sleep(200);
							continue;
						}
						if (!rs.getString(4).equals(User_ID)) {
							System.out.println("출금 계좌가 본인의 계좌가 아닙니다.");
							Thread.sleep(200);
							continue;
						}
						if (rs.getLong(2) < money) {
							System.out.println("잔액이 부족합니다.");
							Thread.sleep(200);
							continue;
						}

						sql = "SELECT * FROM loan_account WHERE Loan_ID='" + loanID + "'";
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("대출상환 계좌번호가 올바르지 않습니다.");
							Thread.sleep(200);
							continue;
						}

						if (rs.getLong(2) < money) {
							money = rs.getLong(2);
						}

						sql = "UPDATE loan_account SET Borrowed_money=" + (rs.getLong(2) - money) + " WHERE Loan_ID='"
								+ loanID + "'";
						stmt.execute(sql);

						sql = "SELECT * FROM saving_account WHERE Saving_ID='" + accountID + "'";
						rs = stmt.executeQuery(sql);
						rs.next();

						sql = "UPDATE saving_account SET Saved_money=" + (rs.getLong(2) - money) + " WHERE Saving_ID='"
								+ accountID + "'";
						stmt.execute(sql);
						System.out.println("" + money + "원 대출상환이 완료되었습니다.");
						break LoopSaving;
					} else if (cmd == 5) {// 홈
						break LoopSaving;
					} else {
						System.out.println("잘못된 입력입니다.");
					}
				}

			} else if (cmd == 2) {// 예적금 신규

				LoopNewSaving: while (true) {
					sql = "SELECT kinds_of_saving.* FROM kinds_of_saving, user WHERE User_ID='" + User_ID
							+ "' AND Affordable_Credit_rate >= Credit_rate";
					rs = stmt.executeQuery(sql);
					System.out.println("가입하고 싶으신 상품명을 입력해 주세요.\n|상품명|최대예금액|가입가능신용도|이자|");
					int i;
					for (i = 1; rs.next(); i++) {
						System.out.println("|" + rs.getString(1) + "|" + rs.getLong(2) + "|" + rs.getInt(3) + "|"
								+ rs.getBigDecimal(4) + "|");
					}
					if (i == 1) {
						System.out.println("|가입 가능 상품 없음|");
						break LoopNewSaving;
					}

					System.out.print("가입할 상품명: ");
					product = sc.next();

					sql = "SELECT Product_Name FROM kinds_of_saving, user WHERE Affordable_Credit_rate >= Credit_rate AND Product_Name='"
							+ product + "'";
					rs = stmt.executeQuery(sql);
					if (!rs.next()) {
						while (true) {
							System.out.println("입력하신 상품은 존재하지 않습니다.\n1.상품명 다시 보기\n2.신규 가입 취소");
							cmd = sc.nextInt();

							if (cmd == 1) {
								continue LoopNewSaving;
							} else if (cmd == 2) {
								break LoopNewSaving;
							} else {
								System.out.println("잘못된 입력입니다.");
							}
						}
					} else {

						sql = "SELECT table_name, table_rows FROM information_schema.tables WHERE table_schema='bank' AND table_name='saving_account'";
						rs = stmt.executeQuery(sql);
						rs.next();
						int sizeOfSaving = rs.getInt(2);
						accountID = String.valueOf(sizeOfSaving + 1);
						accountID = "000000000".substring(accountID.length()) + accountID;

						System.out.println("신규 예금 ID는 '" + accountID + "' 입니다.");
						java.util.Date utilDate = new java.util.Date();
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						sql = "INSERT INTO saving_account values ('" + accountID + "', 0, '" + sqlDate + "', '"
								+ User_ID + "', '" + product + "')";
						stmt.execute(sql);
						break LoopNewSaving;
					}
				}
			} else if (cmd == 3) {// 대출 조회
				LoopLoan: while (true) {
					sql = "SELECT Loan_ID, Borrowed_money, Start_date, Product FROM loan_account WHERE User='" + User_ID
							+ "'";

					rs = stmt.executeQuery(sql);

					System.out.println("|계좌번호|대출금액|시작일자|상품명|");

					int i;
					for (i = 1; rs.next(); i++) {
						System.out.println("|" + rs.getString(1) + "|" + rs.getLong(2) + "|" + rs.getDate(3) + "|"
								+ rs.getString(4) + "|");
					}
					if (i == 1)
						System.out.println("|계좌 없음|");

					System.out.println("-------------------------------------------------------");
					System.out.println("1.대출");
					System.out.println("2.대출 상환");
					System.out.println("3.대출 상환(예금 to 대출)");
					System.out.println("4.홈으로 돌아가기");

					cmd = sc.nextInt();
					if (cmd == 1) {// 추가대출
						System.out.print("계좌번호: ");
						accountID = sc.next();
						System.out.print("대출 금액: ");
						money = sc.nextLong();

						sql = "SELECT Loan_ID, Borrowed_money, Max_Money FROM loan_account, kinds_of_loan WHERE Loan_ID='"
								+ accountID + "' AND Product=Product_Name";
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("일치하는 계좌가 없습니다.");
							continue LoopLoan;
						} else {
							if (rs.getLong(3) < rs.getLong(2) + money) {
								System.out.println("최대 대출액을 넘어섭니다.");
								continue LoopLoan;
							} else {
								sql = "UPDATE loan_account SET Borrowed_money=" + (rs.getLong(2) + money)
										+ " WHERE Loan_ID='" + accountID + "'";
								stmt.execute(sql);
								System.out.println("대출이 완료되었습니다.");
							}
						}
					} else if (cmd == 2) {// 대출 상환
						System.out.print("계좌번호: ");
						accountID = sc.next();
						System.out.print("대출 상환 금액: ");
						money = sc.nextLong();

						sql = "SELECT Loan_ID, Borrowed_money, Max_Money FROM loan_account, kinds_of_loan WHERE Loan_ID='"
								+ accountID + "' AND Product=Product_Name AND User='" + User_ID + "'";
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("일치하는 본인의 계좌가 없습니다.");
							continue LoopLoan;
						} else {
							if (rs.getLong(2) < money) {
								money = rs.getLong(2);
							}

							sql = "UPDATE loan_account SET Borrowed_money=" + (rs.getLong(2) - money)
									+ " WHERE Loan_ID='" + accountID + "'";
							stmt.execute(sql);
							System.out.println("" + money + "원 대출상환이 완료되었습니다.");
						}
					} else if (cmd == 3) {// 계좌이체 대출 상환
						System.out.print("대출 상환 계좌번호: ");
						loanID = sc.next();
						System.out.print("출금 계좌번호: ");
						accountID = sc.next();
						System.out.print("금액(원): ");
						money = sc.nextLong();

						sql = "SELECT * FROM saving_account WHERE Saving_ID='" + accountID + "'";
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("출금 계좌번호가 올바르지 않습니다.");
							Thread.sleep(200);
							continue;
						}
						if (!rs.getString(4).equals(User_ID)) {
							System.out.println("출금 계좌가 본인의 계좌가 아닙니다.");
							Thread.sleep(200);
							continue;
						}
						if (rs.getLong(2) < money) {
							System.out.println("잔액이 부족합니다.");
							Thread.sleep(200);
							continue;
						}

						sql = "SELECT * FROM loan_account WHERE Loan_ID='" + loanID + "'";
						rs = stmt.executeQuery(sql);
						if (!rs.next()) {
							System.out.println("대출상환 계좌번호가 올바르지 않습니다.");
							Thread.sleep(200);
							continue;
						}

						if (rs.getLong(2) < money) {
							money = rs.getLong(2);
						}

						sql = "UPDATE loan_account SET Borrowed_money=" + (rs.getLong(2) - money) + " WHERE Loan_ID='"
								+ loanID + "'";
						stmt.execute(sql);

						sql = "SELECT * FROM saving_account WHERE Saving_ID='" + accountID + "'";
						rs = stmt.executeQuery(sql);
						rs.next();

						sql = "UPDATE saving_account SET Saved_money=" + (rs.getLong(2) - money) + " WHERE Saving_ID='"
								+ accountID + "'";
						stmt.execute(sql);
						System.out.println("" + money + "원 대출상환이 완료되었습니다.");
						break LoopLoan;
					} else if (cmd == 4) {// 홈
						break LoopLoan;
					} else {
						System.out.println("잘못된 입력입니다.");
					}
				}
			} else if (cmd == 4) {// 대출 신규
				LoopNewLoan: while (true) {
					sql = "SELECT kinds_of_loan.* FROM kinds_of_loan, user WHERE User_ID='" + User_ID
							+ "' AND Affordable_Credit_rate >= Credit_rate";
					rs = stmt.executeQuery(sql);
					System.out.println("가입하고 싶으신 상품명을 입력해 주세요.\n|상품명|최대대출액|대출 상환 기한(일)|가입가능신용도|이자|");
					int i;
					for (i = 1; rs.next(); i++) {
						System.out.println("|" + rs.getString(1) + "|" + rs.getLong(2) + "|" + rs.getInt(3) + "|"
								+ rs.getInt(4) + "|" + rs.getBigDecimal(5) + "|");
					}
					if (i == 1) {
						System.out.println("|가입 가능 상품 없음|");
						break LoopNewLoan;
					}

					System.out.print("가입할 상품명: ");
					product = sc.next();

					sql = "SELECT Product_Name FROM kinds_of_loan, user WHERE Affordable_Credit_rate >= Credit_rate AND Product_Name='"
							+ product + "'";
					rs = stmt.executeQuery(sql);
					if (!rs.next()) {
						while (true) {
							System.out.println("입력하신 상품은 존재하지 않습니다.\n1.상품명 다시 보기\n2.신규 가입 취소");
							cmd = sc.nextInt();

							if (cmd == 1) {
								continue LoopNewLoan;
							} else if (cmd == 2) {
								break LoopNewLoan;
							} else {
								System.out.println("잘못된 입력입니다.");
							}
						}
					} else {

						sql = "SELECT table_name, table_rows FROM information_schema.tables WHERE table_schema='bank' AND table_name='loan_account'";
						rs = stmt.executeQuery(sql);
						rs.next();
						int sizeOfLoan = rs.getInt(2);
						accountID = String.valueOf(sizeOfLoan + 1);
						accountID = "000000000".substring(accountID.length()) + accountID;

						System.out.println("신규 대출계좌번호는 '" + accountID + "' 입니다.");
						java.util.Date utilDate = new java.util.Date();
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						sql = "INSERT INTO loan_account values ('" + accountID + "', 0, '" + sqlDate + "', '" + User_ID
								+ "', '" + product + "')";
						stmt.execute(sql);
						break LoopNewLoan;
					}
				}
			} else if (cmd == 5) {// 로그아웃
				break LoopHome;
			} else {
				System.out.println("잘못된 입력입니다.");
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
			System.out.println("\n#Home#\n1.사용자 관리\n2.예/적금 계좌 관리\n3.대출 계좌 관리\n4.지사 관리\n5.인사 관리\n6.로그아웃");
			cmd = sc.next();
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
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
	}

	private static void RAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String cmd;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.사용자 관리\n2.예/적금 계좌 관리\n3.대출 계좌 관리\n4.로그아웃");
			cmd = sc.next();
			if (cmd.equals("1")) {
				UserManage(conn, Admin_ID);
			} else if (cmd.equals("2")) {
				SavingManage(conn, Admin_ID);
			} else if (cmd.equals("3")) {
				LoanManage(conn, Admin_ID);
			} else if (cmd.equals("4")) {
				break LoopHome;
			} else {
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
	}

	private static void StaffManagingAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String cmd;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.사용자 관리\n2.예/적금 계좌 관리\n3.대출 계좌 관리\n4.인사 관리\n5.로그아웃");
			cmd = sc.next();
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
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
	}

	private static void ProductAdmin(Connection conn, String Admin_ID) throws Exception {// DONE
		sc = new Scanner(System.in);
		String cmd;
		LoopHome: while (true) {
			System.out.println("\n#Home#\n1.사용자 관리\n2.예/적금 계좌 관리\n3.대출 계좌 관리\n4.대출 상품 관리\n5.예/적금 상품 관리\n6.로그아웃");
			cmd = sc.next();
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
				System.out.println("잘못된 입력입니다.");
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
			adminID = sc.next();
			System.out.print("Password: ");
			password = sc.next();

			sql += " WHERE Admin_ID='" + adminID + "'";
			// System.out.println("sql statement is: " + sql);

			rs = stmt.executeQuery(sql);
			try {
				rs.next();
				String temp = rs.getString(2);
				if (password.equals(temp)) {
					System.out.println("로그인 성공\n\n환영합니다.");
					break;
				} else {
					while (true) {
						System.out.println("\n.비밀번호가 틀렸거나, 존재하지 않는 관리자 ID 입니다.");
						System.out.println("1.비밀번호 재입력");
						System.out.println("2.초기화면으로 돌아가기");
						cmd = sc.next();

						if (cmd.equals("1"))
							break;
						else if (cmd.equals("2"))
							return;
						else
							System.out.println("잘못된 입력입니다.");
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				while (true) {
					System.out.println("\n.비밀번호가 틀렸거나, 존재하지 않는 관리자 ID 입니다.");
					System.out.println("1.비밀번호 재입력");
					System.out.println("2.초기화면으로 돌아가기");
					cmd = sc.next();

					if (cmd.equals("1"))
						break;
					else if (cmd.equals("2"))
						return;
					else
						System.out.println("\n잘못된 입력입니다.");
				}
			}

		}

		sql = "SELECT Admin_ID, Position From Admin WHERE Admin_ID='" + adminID + "'";
		rs = stmt.executeQuery(sql);
		rs.next();
		if (rs.getString(2).equals("CEO")) {
			CEOAdmin(conn, adminID);
		} else if (rs.getString(2).equals("인사팀")) {
			StaffManagingAdmin(conn, adminID);// 占쏙옙占쏙옙占쏙옙 占쌩곤옙/占쏙옙占쏙옙 占쏙옙占쏙옙
		} else if (rs.getString(2).equals("기획팀")) {
			ProductAdmin(conn, adminID);// 占쏙옙품 占쌩곤옙/占쏙옙占쏙옙 占쏙옙占쏙옙
		} else {
			RAdmin(conn, adminID);// 占쏙옙占쏙옙占쏙옙 占쌩곤옙 占쌀곤옙, 占쌘깍옙 占쏙옙占쏙옙 User占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
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
			userID = sc.next();
			System.out.print("Password: ");
			password = sc.next();

			sql += " WHERE User_ID='" + userID + "'";
			// System.out.println("sql statement is: " + sql);

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			try {
				rs.next();
				String temp = rs.getString(2);
				if (password.equals(temp)) {
					System.out.println("로그인에 성공하였습니다.\n환영합니다.");
					break;
				} else {
					while (true) {
						System.out.println("\n.비밀번호가 틀렸거나, 존재하지 않는 관리자 ID 입니다.");
						System.out.println("1.비밀번호 재입력");
						System.out.println("2.초기화면으로 돌아가기");
						cmd = sc.next();

						if (cmd.equals("1"))
							break;
						else if (cmd.equals("2"))
							return;
						else
							System.out.println("\n잘못된 입력입니다.");
					}
				}
			} catch (Exception e) {
				// System.out.println(e.getMessage());
				// e.printStackTrace();
				while (true) {
					System.out.println("\n.비밀번호가 틀렸거나, 존재하지 않는 관리자 ID 입니다.");
					System.out.println("1.비밀번호 재입력");
					System.out.println("2.초기화면으로 돌아가기");
					cmd = sc.next();

					if (cmd.equals("1"))
						break;
					else if (cmd.equals("2"))
						return;
					else
						System.out.println("\n잘못된 입력입니다.");
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
			// TODO: 占쏙옙占쏙옙占쏙옙占쏙옙 占십곤옙占쏙옙 占쏙옙占쏙옙처占쏙옙
			System.out.print("등록하실 사용자의 정보를 입력해 주세요.\n이름(20자 이내): ");
			Name = sc.next();
			System.out.print("전화번호(15자 이내): ");
			Phone = sc.next();
			System.out.print("주소지(30자 이내): ");
			Address = sc.nextLine();
			System.out.print("비밀번호(20자 이내): ");
			Password = sc.next();

			System.out.println("입력하신 정보 확인");
			System.out.println("이름: " + Name + "\n전화번호: " + Phone + "\n주소지: " + Address);
			Loop2: while (true) {
				System.out.println("\n다음으로 진행하시려면 '1', 정보를 다시 입력하시려면 '2', 취소하시려면 '3'을 입력해 주세요.");

				cmd = sc.next();
				if (cmd.equals("1"))
					break Loop1;
				else if (cmd.equals("2")) {
					break Loop2;
				} else if (cmd.equals("3")) {
					return;
				} else {
					System.out.println("잘못된 입력입니다.");
				}
			}
		}
		while (true) {
			System.out.print("비밀번호 재확인: ");
			String passTemp = sc.next();
			if (passTemp.equals(Password))
				break;
			else {
				while (true) {
					System.out.println("처음 입력하신 비밀번호와 일치하지 않습니다.\n1.정보 입력으로 돌아가기\n2.비밀번호 재확인\n3.등록 취소");
					cmd = sc.next();
					if (cmd.equals("1")) {
						AddUser(conn);
						return;
					} else if (cmd.equals("2")) {
						break;
					} else if (cmd.equals("3")) {
						return;
					} else {
						System.out.println("잘못된 입력입니다.");
					}

				}
			}
		}

		System.out.println("\n占쏙옙占싻뱄옙호 占쏙옙확占싸울옙 占쏙옙占쏙옙占싹울옙占쏙옙占싹댐옙.");

		// User_ID 占쏙옙占쏙옙
		sql = "SELECT table_name, table_rows FROM information_schema.tables WHERE table_schema = 'bank' AND table_name='user'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		rs.next();
		int sizeOfUser = rs.getInt(2);
		User_ID = String.valueOf(sizeOfUser + 1);
		User_ID = "000000000".substring(User_ID.length()) + User_ID;
		// System.out.println(User_ID);
		System.out.println("등록하신 사용자의 사용자 ID는 '" + User_ID + "' 입니다.");

		// Branch 占쌉력받깍옙
		sql = "SELECT * FROM branch ORDER BY Branch_ID";
		rs = stmt.executeQuery(sql);
		System.out.println("등록하고 싶으신 지사의 지사 ID를 입력해 주세요.\n");
		while (rs.next()) {
			System.out.println(
					"#지사 ID: " + rs.getString(1) + "#소재지: " + rs.getString(2) + "#전화번호: " + rs.getString(4) + "#");
		}
		while (true) {// 占쏙옙占쏙옙占싹댐옙 Branch占쏙옙占쏙옙 확占쏙옙
			Branch = sc.next();
			sql = "SELECT COUNT(*) FROM branch WHERE Branch_ID='" + Branch + "'";
			rs = stmt.executeQuery(sql);
			rs.next();
			if (rs.getInt(1) == 1)
				break;
			else {
				System.out.println("존재하지 않는 지사 ID입니다. 입력을 확인해 주세요.");
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

			System.out.println("배정받으신 담당자 정보는 다음과 같습니다.");

			System.out.println("이름: " + rs.getString(2) + ", 연락처: " + rs.getString(3) + ", 직책: " + rs.getString(4));

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
			System.out.println("배정받으신 담당자 정보는 다음과 같습니다.");

			System.out.println("이름: " + rs.getString(2) + ", 연락처: " + rs.getString(3) + ", 직책: " + rs.getString(4));
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
			port = sc.next();
			System.out.print("root Username: ");
			userName = sc.next();
			System.out.print("root Password: ");
			password = sc.next();

			try {
				Class.forName("com.mysql.jdbc.Driver");

				String url = "jdbc:mysql://localhost:" + port + "/bank?characterEncoding=UTF-8&serverTimezone=UTC";

				// @param getConnection(url, userName, password);
				// @return Connection
				conn = DriverManager.getConnection(url, userName, password);
				System.out.println("데이터베이스 연결 성공");

				while (true) {
					System.out.println("\n로그인 방식을 선택해 주세요....\n1.관리자 로그인\n2.사용자 로그인\n3.신규 사용자 등록\n4.프로그램 종료");
					cmd = sc.next();

					try {
						if (cmd.equals("1")) {
							AdminMode(conn);
						} else if (cmd.equals("2")) {
							UserMode(conn);
						} else if (cmd.equals("3")) {
							AddUser(conn);
						} else if (cmd.equals("4")) {
							System.out.println("프로그램을 종료합니다...");
							Thread.sleep(1500);
							break;
						} else {
							Exception a = new Exception("\n잘못된 입력입니다.");
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
				System.out.println("디바이스를 불러오지 못했습니다");
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
