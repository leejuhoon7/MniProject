package BookInformation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class BookDataService {

	Scanner sc = new Scanner(System.in);
	

	// 4,5. 도서 대출, 반납

	public void lendBook() {
		
		System.out.println("빌려갈 책 이름: ");
		String lendbook = sc.nextLine();
		
		int result = BookDataDAO.getInstance().delayBook(lendbook);
		if(result == 1) {
			System.out.println("빌려감!");
		}else {
			System.out.println("빌리기 실패!");
		}

	}
	
	public void returnBook() {
		
		System.out.println("반납할 책 이름: ");
		String lendbook = sc.nextLine();
		
		int result = BookDataDAO.getInstance().returnBook(lendbook);
		if(result == 1) {
			System.out.println("반납함!");
		}else {
			System.out.println("반납 실패!");
		}
	}
	
	//6. 도서 연체
	
//	public void delayBook() {
//			System.out.println("빌려갈책제목");
//			String lendbook = sc.nextLine();
//
//		
//		int result = BookDataDAO.getInstance().delayBook(lendbook);
//		
//		
//		
//		if(result == 8) {
//			System.out.println("연체 됨!!");
//		}else {
//			System.out.println("연체 안됨!");
//		}
//		
//		
//	}
	
}
