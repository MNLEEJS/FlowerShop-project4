import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dbutil.DBUtil;

// 작성자 : 이아현

// 회원 주문 정보 생성자
public class UserOrderInfo {
	UserOrderMapper userOrderMapper = new UserOrderMapper();

	// 주문 번호, 회원 번호 정보를 담을 리스트 생성
	public List<UserOrder> findByPk(int pk1, int pk2, String column) {

		String sql = "SELECT * FROM UserOrder WHERE " + column + " = ?";

		List<UserOrder> userInfoList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);

			if (column.equals("no")) {
				stmt.setInt(1, pk1);
			} else if (column.equals("user_no")) {
				stmt.setInt(1, pk2);
			}
			rs = stmt.executeQuery();

			// while 사용해서 회원 주문 정보를 담을 리스트들을 계속해서 저장할 수 있도록 함
			// return 회원 주문 정보 리스트 반환
			while (rs.next()) {
				UserOrder userOrder = userOrderMapper.resultMapping(rs);
				userInfoList.add(userOrder);
			}
			return userInfoList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	// 주문번호는 auto_increment로 자동 생성이라서 user_no만 insert값 넣음
	public int insert(int user_no) {
		String sql = "INSERT INTO UserOrder(user_no) VALUES (?);";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, user_no);

			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return -1;
	}

	public int Delete(int user_no) {
		String sql = "delete from userOder_info where user_no = ?";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, user_no);

			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return -1;
	}

}
