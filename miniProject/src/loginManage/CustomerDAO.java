package loginManage;

import java.util.List;

import book.Book;
import common.DAO;

public class CustomerDAO extends DAO {

	private static CustomerDAO cd = null;

	private CustomerDAO() {

	}

	public static CustomerDAO getInstance() {

		if (cd == null) {
			cd = new CustomerDAO();
		}
		return cd;
	}

	// 1. 고객 등록
	public int insertCustomer(Customer ct) {
		int result = 0;

		try {

			conn();
			String sql = "insert into customer (id, pw, name, tel) " + " values(?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ct.getId());
			pstmt.setString(2, ct.getPw());
			pstmt.setString(3, ct.getName());
			pstmt.setString(4, ct.getTel());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

}
