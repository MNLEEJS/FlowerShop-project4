import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBUtil;

// 회원정보를 찾기 위해 기본키인 no로 구분하여 출력
public class MemberInfo {
	public Membership findByPk(int pk) {
		MembershipMapper membershipMapper = new MembershipMapper();
		String sql = "SELECT * FROM membership WHERE no = ?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");

			// 작성한 sql문을 연결해서 db에 값들을 보낼 준비함
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);
			// (1 (첫번째 물음표) , pk 값을 삽입함)
			rs = stmt.executeQuery();
			// db의 project3과 연결 - 자바에서 db 접근이 가능해서 삽입 삭제가 가능함.
			// sql문을 실행해서 원하는 결과값을 resultset에 저장해서 전송할 준비가 완료 되었다
			// executeQuery는 select에서만 사용함

			// 저장한 resultset의 값을 확인해서 membership객체를 만듦
			// 결과값이 있다면 membership 회원정보 객체 만들어서 저장
			if (rs.next()) {
				Membership membership = membershipMapper.resultMapping(rs);
				// mapper 작업
//				int no = rs.getInt("no");
//				String name = rs.getString("name");
//				String phoneNumber = rs.getString("phoneNumber");
//				String id = rs.getString("id");
//				String pw = rs.getString("pw");
//				String address = rs.getString("address");

				return membership;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		} // conn은 db를 연결해주는 역할인데 연결종료도 필요함.
			// 자원해제 계속 해줄 수 없으니 굳이 끊어버리지 않고 있는 연결을 사용해서 아래 insert에 파라미터값으로 넣어줌
		return null;
	}

	public void insert(String name, String phoneNumber) {

// 사용자 입력값을 받는다

	}
}