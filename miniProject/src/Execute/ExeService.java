package Execute;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import BookInformation.BookDataService;
import book.BookService;
import loginManage.Customer;
import loginManage.loginDAO;
import loginManage.loginService;

public class ExeService {

	Scanner sc = new Scanner(System.in);

	BookService bs = new BookService();
	loginService ls = new loginService();
	BookDataService bds = new BookDataService();

	int menuNo;
	boolean isTrue = true;

	public ExeService() {
		login();
		
	}

	// 로그인
	private void login() {
		System.out.println("1.로그인 2.종료");

		menuNo = Integer.parseInt(sc.nextLine());

		switch (menuNo) {
		case 1:
			ls.doLogin();
			if (loginService.memberInfo != null) {
				new loginService();
				run();
			}
			break;
			
		case 2:
			System.out.println("종료 되었습니다.");
			break;
		}
	}

	private void run() {
		while (isTrue) {
			System.out.println(
					"|1|고객 등록 |2|도서 등록 |3|도서 정보수정 |4|도서 대출 |5|도서 반납" + " |6|도서 검색 |7|도서 삭제 |8|종료 |0|회원정보 수정");
			menuNo = Integer.parseInt(sc.nextLine());
			if (menuNo == 1) {
				bs.registration();
			} else if (menuNo == 2) {
				bs.registrationBook();
			} else if (menuNo == 3) {
				bs.updateBook();
			} else if (menuNo == 4) {
				bds.lendBook();
			} else if (menuNo == 5) {
				bds.returnBook();
			} else if (menuNo == 6) {
				bs.searchBook();
			} else if (menuNo == 7) {
				bs.delBook();
			} else if (menuNo == 8) {
				System.out.println("종료");
				isTrue = false;
			} else if (menuNo == 0) {
				menuInfo(); // 회원정보수정(비밀번호, 이름, 전화번호)
			}
		}

	}

	private void menuInfo() {
//		MemberService.memberInfo.getRole().equals("1")

		int menuNo = 0;

		while (true) {
			if (loginService.memberInfo.getRole() == 1) {
				System.out.println("관리자 로그인!");
				System.out.println("1.전체회원조회 2.회원수정 3.회원삭제 4.메뉴로 돌아가기");

				menuNo = Integer.parseInt(sc.nextLine());
				if (menuNo == 1) {
					CustomerSelect(); // 전체회원조회
				} else if (menuNo == 2) {
					System.out.println("1.비밀번호변경 2.이름변경 3.전화번호변경 4. 메뉴로 돌아가기");
					menuNo = Integer.parseInt(sc.nextLine());
					if (menuNo == 1) {
						CustomerUpdatePw();
					} else if (menuNo == 2) {
						CustomerUpdateName();
					} else if (menuNo == 3) {
						CustomerUpdateTel();
					} else if (menuNo == 4) {
						run();
					}

				} else if (menuNo == 3) {
					Customerdel();
				} else if (menuNo == 4) {

					run();
					break;

				}
			} else if (loginService.memberInfo.getRole() == 0) {
				System.out.println("일반 사용자!");
				System.out.println("1.정보변경 2.메뉴로 돌아가기");
				menuNo = Integer.parseInt(sc.nextLine());
				if (menuNo == 1) {
					System.out.println("1.비밀번호변경 2.이름변경 3.전화번호변경 4.메뉴로 돌아가기");
					menuNo = Integer.parseInt(sc.nextLine());
					if (menuNo == 1) {
						CustomerUpdatePw();
					} else if (menuNo == 2) {
						CustomerUpdateName();
					} else if (menuNo == 3) {
						CustomerUpdateTel();
					} else if (menuNo == 4) {
						run();
					}

				} else if (menuNo == 2) {
					run();
					break;
				}
			}
		}
	}

	// 1. 전체회원 조회
	public void CustomerSelect() {
		System.out.println("전체 ID");
		List<Customer> list = new ArrayList<>();
		list = loginDAO.getInstance().searchCustomer();

		for (Customer cr : list) {
			System.out.println(cr.getId());
		}
	}

	// 2-1. 회원 수정 (비밀번호 변경)

	public void CustomerUpdatePw() {

		System.out.println("pw변경할 아이디를 입력!");
		String id = sc.nextLine();

		System.out.println("수정할 비밀번호: ");
		String pw = sc.nextLine();

		int result = loginDAO.getInstance().updateCustomerPw(id, pw);

		if (result == 1) {
			System.out.println("비밀번호 변경 완료!");
		} else {
			System.out.println("비밀번호 변경 실패!");
		}

	}

	// 2-2. 회원 수정 (이름 변경)
	public void CustomerUpdateName() {
		System.out.println("이름 변경할 아이디 입력!");
		String id = sc.nextLine();

		System.out.println("변경할 이름: ");
		String name = sc.nextLine();

		int result = loginDAO.getInstance().updateCustomerName(id, name);

		if (result == 1) {
			System.out.println("이름 변경 완료!");
		} else {
			System.out.println("이름 변경 실패!");
		}
	}

	// 2-3. 회원 수정(전화번호 변경)

	public void CustomerUpdateTel() {
		System.out.println("전화번호 변경할 아이디 입력!");
		String id = sc.nextLine();

		System.out.println("변경할 전화번호: ");
		String tel = sc.nextLine();

		int result = loginDAO.getInstance().updateCustomerTel(id, tel);

		if (result == 1) {
			System.out.println("전화번호 변경 완료!");
		} else {
			System.out.println("전화번호 변경 실패!");
		}
	}

	// 3. 회원 삭제

	public void Customerdel() {
		System.out.println("삭제할 ID: ");
		String id = sc.nextLine();

		int result = loginDAO.getInstance().delCustomer(id);

		if (result == 1) {
			System.out.println("ID 삭제 완료!");
		} else {
			System.out.println("ID 삭제 실패!");
		}
	}
}
