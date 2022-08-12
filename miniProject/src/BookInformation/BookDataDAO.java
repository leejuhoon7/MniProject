package BookInformation;

import java.util.Scanner;

import book.Book;
import common.DAO;

public class BookDataDAO extends DAO {

	private static BookDataDAO bdd = null;
	
	Scanner sc = new Scanner(System.in);
	Book bk = null;
	
	
	private BookDataDAO() {
		
	}
	
	public static BookDataDAO getInstance() {
		if (bdd == null) {
			bdd = new BookDataDAO();
		}
		return bdd;
	}
	
	
	
	
	// 4. 도서 대출
	public int delayBook(String title) {
		
		int result = 0;
		
		try {
			
			conn();
			String sql = "update book set lenddate = sysdate, delaybook "
					+ "= sysdate + 7, booklend = 'Y'\r\n"
					+ "where title = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return result;
	}
	
	// 5. 도서 반납
		public int returnBook(String title) {
			
			int result = 0;
			
			try {
				
				conn();
				String sql = "update book set lenddate = null, delaybook "
						+ "= null, booklend = 'N'\r\n"
						+ "where title = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
			return result;
		}
}
