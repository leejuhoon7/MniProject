package loginManage;

import java.util.Scanner;

public class loginService {

	public static Customer memberInfo = null;
	Scanner sc = new Scanner(System.in);

	// 로그인
	public void doLogin() {

		Customer cr = new Customer();
			System.out.println("Id입력: ");
			String id = sc.nextLine();

			System.out.println("Pw입력: ");
			String pw = sc.nextLine();

			cr = loginManage.getInstance().loginInfo(id);
			if (cr != null) {
				if (cr.getPw().equals(pw)) {
					memberInfo = cr;
				} else {
					System.out.println("로그인 실패");
				}
			} else {
				System.out.println("id를 확인하세요!");
		}
	}

	public void logout() {
		if (memberInfo != null) {
			memberInfo = null;
		}
	}

}
