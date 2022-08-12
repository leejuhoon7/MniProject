package book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import loginManage.Customer;
import loginManage.CustomerDAO;

public class BookService {

	Scanner sc = new Scanner(System.in);
	Book bk = new Book();
	
	// 1. 고객 등록

	public void registration() {
		
		Customer cr = new Customer();

		System.out.println("등록할 ID: ");
		String id = sc.nextLine();
		System.out.println("비밀번호: ");
		String pw = sc.nextLine();
		System.out.println("이름: ");
		String name = sc.nextLine();
		System.out.println("전화번호: ");
		String tel = sc.nextLine();

		cr.setId(id);
		cr.setPw(pw);
		cr.setName(name);
		cr.setTel(tel);

		int result = CustomerDAO.getInstance().insertCustomer(cr);
		if (result == 1) {
			System.out.println("정상 등록!");
		} else {
			System.out.println("등록 불가!");
		}
	}

	// 2. 도서 등록

	public void registrationBook() {

		System.out.println("등록할 책: ");
		String title = sc.nextLine();
		System.out.println("작가: ");
		String author = sc.nextLine();
		System.out.println("내용입력: ");
		String content = sc.nextLine();

		bk.setTitle(title);
		bk.setAuthor(author);
		bk.setContent(content);

		int result = BookDAO.getInstance().insertBook(bk);
		if (result == 1) {
			System.out.println("책 등록 완료!");
		} else {
			System.out.println("책 등록 실패!");
		}

	}

	// 3. 책 내용 수정

	public void updateBook() {
		BookDAO.getInstance().updateBook(bk);
		System.out.println("수정할 책 제목: ");
		String title = sc.nextLine();
		bk.setTitle(title);
		System.out.println("수정할 글 내용: ");
		String content = sc.nextLine();
		bk.setContent(content);

		int result = BookDAO.getInstance().updateBook(bk);
		if (result == 1) {
			System.out.println("내용 수정 완료!");

		} else {
			System.out.println("수정 실패!");
		}

	}

	// 7. 도서 검색

	public void searchBook() {
		System.out.println("1.전체도서 2.도서검색");
		int selectNum = Integer.parseInt(sc.nextLine());
		
		List<Book> list = BookDAO.getInstance().allsearchBook();
		
		if (selectNum == 1) {
			for(Book book : list) {
				System.out.println("=====================================================");
				System.out.println("제목: "+book.getTitle() +" 작가: "+ book.getAuthor());
				System.out.println("줄거리: " + book.getContent());
				System.out.println("=====================================================");
				
			}
			
		} else if(selectNum == 2) {
			
			
			System.out.println("찾아볼 책 제목: ");
			String search = sc.nextLine();
			
			Book book =BookDAO.getInstance().searchBook(search);
			
			System.out.println("=====================================================");
			System.out.println("제목: "+book.getTitle()+" 작가: "+ book.getAuthor());
			System.out.println("줄거리: " + book.getContent());
			System.out.println("=====================================================");
		}
	}
	
	// 8 도서 삭제
	
	public void delBook() {
		System.out.println("목록에서 제거할 책: ");
		String delbook = sc.nextLine();
		
		bk.setTitle(delbook);

		
		
		int result = BookDAO.getInstance().delBook(delbook);
		if(result == 1) {
			System.out.println("책 삭제 완료!");
		} else {
			System.out.println("삭제 실패!");
		}
	}
}
