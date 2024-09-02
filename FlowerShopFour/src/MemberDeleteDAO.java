import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

// 탈퇴한 회원 전체 조회
public class MemberDeleteDAO {
	MembershipMapper membershipMapper = new MembershipMapper();

	public List<Membership> selectAll() {
		String sql = "SELECT * FROM deleted_membership";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<Membership> list = new ArrayList<Membership>();
			while (rs.next()) {
				Membership membership = membershipMapper.resultMapping(rs);
				list.add(membership);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

// 회원탈퇴할경우 유저의 정보를 여기로 저장한다.
	public int insert(String no) {
		String sql = "INSERT INTO deleted_membership (Select * from membership where ID = ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, no);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return -1;
	}

// 멤버 리스트로 반환
	public List<Membership> selectMembershipID(String ID) {
		String sql = "SELECT * FROM membership where Id  = ? ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ID);
			rs = stmt.executeQuery();
			List<Membership> list = new ArrayList<Membership>();
			while (rs.next()) {

				Membership membership = membershipMapper.resultMapping(rs);
				list.add(membership);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	// 회원정보로 회원 주문 정보 테이블 삭제
	public int deleteUserOrder_info(int no) {
		String sql = "delete from userOrder_info where user_no = ?";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);

			int result = stmt.executeUpdate();

			if (result == 1) {
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return -1;
	}

	// 주문번호로 주문정보테이블 삭제
	public int deleteOrder_info(int no) {
		String sql = "delete from Order_info where order_no = ?";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);

			int result = stmt.executeUpdate();

			if (result == 1) {
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return -1;
	}

	// 주문상품 번호로 주문 상세 내역 테이블 삭제
	public int deleteOrder_detail(int no) {
		String sql = "delete from order_detail where no = ?";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);

			int result = stmt.executeUpdate();

			if (result == 1) {
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return -1;
	}

	// 회원 이름과 ID를 조회해서 주문번호를 가져옴

}
