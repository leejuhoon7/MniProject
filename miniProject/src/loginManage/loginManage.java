package loginManage;

import common.DAO;

public class loginManage extends DAO {
	
	private static loginManage lm = new loginManage();
	private loginManage() {
		
	}
	
	public static loginManage getInstance() {
		return lm;
	}
	
	//로그인 DAO
	
	public Customer loginInfo(String id) {
		
		Customer cr = null;
		
		try {
			conn();
			String sql = "select * from customer where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cr = new Customer();
				cr.setId(rs.getString("id"));
				cr.setPw(rs.getString("pw"));
				cr.setName(rs.getString("name"));
				cr.setTel(rs.getString("tel"));
				cr.setRole(rs.getInt("role"));
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cr;
	}
}
