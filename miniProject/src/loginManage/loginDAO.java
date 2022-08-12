package loginManage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.DAO;

public class loginDAO extends DAO {

	private static loginDAO ld = null;
	Scanner sc = new Scanner(System.in);

	private loginDAO() {

	}

	public static loginDAO getInstance() {

		if (ld == null) {
			ld = new loginDAO();
		}
		return ld;
	}

	// 1. 전체 회원 조회

	public List<Customer> searchCustomer() {

		List<Customer> list = new ArrayList<>();
		Customer cr = null;

		try {
			conn();

			String sql = "select * from customer";
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				cr = new Customer();
				cr.setId(rs.getString("id"));
				cr.setPw(rs.getString("pw"));
				cr.setName(rs.getString("name"));
				cr.setTel(rs.getString("tel"));
				list.add(cr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	//id로 검색해서 1명 가져오기
	public Customer searchPerson(String id) {

		
		Customer cr = null;

		try {
			conn();

			String sql = "select * from customer where id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cr = new Customer();
				cr.setId(rs.getString("id"));
				cr.setPw(rs.getString("pw"));
				cr.setName(rs.getString("name"));
				cr.setTel(rs.getString("tel"));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cr;
	}

	// 2-1. 회원 수정(비밀번호)

	public int updateCustomerPw(String id ,String pw) {
		int result = 0;
 
		try {
			conn();
			String sql = "update customer set pw =? where id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	// 2-2. 회원수정(이름)

	public int updateCustomerName(String id, String name) {
		int result = 0;

		try {
			conn();
			String sql = "update customer set name =? where id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	// 2-3. 회원수정(전화번호)

	public int updateCustomerTel(String id, String tel) {
		int result = 0;

		try {
			conn();
			String sql = "update customer set tel =? where id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			pstmt.setString(2, id);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	// 3. 회원 삭제

	public int delCustomer(String id) {

		int result = 1;

		try {
			conn();
			String sql = "delete from customer where id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}
}
