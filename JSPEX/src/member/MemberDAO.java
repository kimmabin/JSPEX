package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception {
		
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/emv");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	};
	
	public int memberCheck(String id, String passwd) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPasswd = "";
		int isMember =  -1;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "PASSWD "
					+ "FROM MEMBER "
					+ "WHERE ID=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dbPasswd = rs.getString("PASSWD");
				if (dbPasswd.equals(passwd)) {
					isMember = 1;
				} else {
					isMember = 0;
				}
			} else {
				isMember = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close();} catch (SQLException se ) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn != null) try {conn.close();} catch (SQLException se) {}
		}
		
		return isMember;
	}
	public void insertMember(MemeberDTO member) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"INSERT "
					+ "INTO MEMBER "
					+ "(ID, PASSWD, NAME, JUMIN1, JUMIN2, EMAIL, BLOG, REG_DATE) "
					+ "VALUES "
					+ "(ID=?, PASSWD=?, NAME=?, JUMIN1=?, JUMIN2=?, EMAIL=?, BLOG=?, REG_DATE=?)");
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getname());
			pstmt.setString(4, member.getJumin1());
			pstmt.setString(5, member.getJumin2());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getBlog());
			pstmt.setTimestamp(8, member.getRegDate());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch  (SQLException se) {}
			if (conn != null) try {conn.close();} catch (SQLException se) {}
		}
	}
	
	@SuppressWarnings("resource")
	public int deleteMember(String id, String passwd) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPasswd = "";
		int isDelete = -1;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"SELECT "
					+ "PASSWD "
					+ "FROM MEMBER "
					+ "WHERE ID=?");
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dbPasswd = rs.getString("PASSWD");
				if (dbPasswd.equals(passwd)) {
					pstmt = conn.prepareStatement(
							"DELETE "
							+ "FROM MEMBER "
							+ "WHERE ID=?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					isDelete = 1;
				} else {
					isDelete = 0;
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close();} catch (SQLException se) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn != null) try {conn.close();} catch (SQLException se) {}
		}
		return isDelete;
	}
	
	public void updateMember(MemeberDTO member) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE "
					+ "MEMBER SET "
					+ "PASSWD=? NAME=? EMAIL=? BLOG=? "
					+ "WHERE ID=?");
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getname());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getBlog());
			pstmt.setString(5, member.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException se) {}
			if (conn != null) try {conn.close();} catch (SQLException se) {}
		}
	}
	
}
