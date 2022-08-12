package book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.DAO;

public class BookDAO extends DAO {

	private static BookDAO bd = null;
	Scanner sc = new Scanner(System.in);

	private BookDAO() {

	}

	public static BookDAO getInstance() {

		if(bd == null) {
			bd = new BookDAO();
		}
		return bd;
	}

	// 2. 도서 등록

	public int insertBook(Book bk) {
		int result = 0;

		try {

			conn();
			String sql = "insert into book(title, author, content) values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bk.getTitle());
			pstmt.setString(2, bk.getAuthor());
			pstmt.setString(3, bk.getContent());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	// 3. 도서 정보 수정(내용물)

	public int updateBook(Book bk) {
		int result = 0;

		try {
			conn();
			String sql = "update book set content = ? where title = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bk.getContent());
			pstmt.setString(2, bk.getTitle());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	// 7-1. 도서 검색 (부분)

	public Book searchBook(String bk) {

		Book book = null;

		try {
			conn();

			String sql = "select * from book where title = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bk);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				book = new Book();
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setContent(rs.getString("content"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return book;
	}

	// 7-2 도서 전체 검색

	public List<Book> allsearchBook() {
		List<Book> list = new ArrayList<>();
		Book book = null;

		try {
			conn();

			String sql = "select * from book";
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setContent(rs.getString("content"));
				list.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;
	}

	// 8. 도서 삭제

	public int delBook(String bk) {

		int result = 1;

		try {

			conn();
			String sql = "delete from book where title = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bk);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}
}
