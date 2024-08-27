import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbutil.DBUtil;

// 회원정보를 찾기 위해 기본키인 no로 구분하여 출력
public class MemberInfo {
	MembershipMapper membershipMapper = new MembershipMapper();

	public Membership findByPk(String pk1, int pk, String column) {
		String sql = "SELECT * FROM membership WHERE " + column + " = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			// ID = 유니크가 걸어놔서 중복을 허용하지 않아서 하나의 행에 각자다른 ID를 사용해서 조회 가능
			// NO = primaryKey설정되어있어서 중복 허용하지 않아서 하나의 행에 각자다른 no를 사용해서 조회 가능
			
			// ID로 조회할경우 ID는 스트링으로 컬럼구성되어 있어 setString 사용
			if(column.equals("ID")) {
				stmt.setString(1, pk1);
			// no로 조회할경우 no는 Int로 구성되어 있기에 setInt 사용
			}else if (column.equals("no")) {
				stmt.setInt(1, pk);
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				// mapper 작업
				Membership membership = membershipMapper.resultMapping(rs);
				
				return membership;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		} 
		return null;
	}

	public int insert(String name, String phoneNumber, String id, String pw, String address) {
		String sql = "INSERT INTO membership(name, phoneNumber, id, pw, address) VALUES (?, ?, ?, ?, ?);";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, name);
			stmt.setString(2, phoneNumber);
			stmt.setString(3, id);
			stmt.setString(4, pw);
			stmt.setString(5, address);

			return stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return -1;
	}


public int update(String name, String phoneNumber, String id, String pw, String address) {
	String sql = "Update membership set phoneNumber = ?, pw = ?, address = ? where ID = ?";

	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	try {
		conn = DBUtil.getConnection("project3");
		
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		stmt.setString(1, phoneNumber);
		stmt.setString(2, pw);
		stmt.setString(3, address);
		stmt.setString(4, id);
		
		int result = stmt.executeUpdate();
		if (result == 1) {
			rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		DBUtil.closeAll(rs, stmt, null);
	}
	return -1;
}
}
