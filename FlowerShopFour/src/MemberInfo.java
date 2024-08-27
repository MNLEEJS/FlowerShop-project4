import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbutil.DBUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 작업자 : 이아현
// membership 테이블의 컬럼별 선언, Mapper, CRUD 구성하는 코드 작성

//membership 테이블 컬럼별 선언
@Data
@AllArgsConstructor
@Builder
class Membership {
	private int no;
	private String name;
	private String phonenumber;
	private String id;
	private String pw;
	private String address;
}

//Mapper
class MembershipMapper implements IResultMapper<Membership> {

	@Override
	public Membership resultMapping(ResultSet rs) {
		try {
			int no = rs.getInt("no");
			String name = rs.getString("name");
			String phoneNumber = rs.getString("phoneNumber");
			String id = rs.getString("id");
			String pw = rs.getString("pw");
			String address = rs.getString("address");

			return Membership.builder().no(no).name(name).phonenumber(phoneNumber).id(id).pw(pw).address(address)
					.build();

		} catch (Exception e) {
			e.printStackTrace();

			throw new RuntimeException("Flower 매핑 중 예외 발생", e);
		}
	}
}

// 회원정보를 찾기 위해 기본키인 no로 구분하여 출력
public class MemberInfo {
	public Membership findByPk(String pk1, int pk, String column, String id, String phoneNumber, String address) {
		MembershipMapper membershipMapper = new MembershipMapper();
		String subject = "";
		if (id.length() != 0) {
			subject = "id";
		} else if (phoneNumber.length() != 0) {
			subject = "phoneNumber";

		}
		String sql = "SELECT * FROM membership WHERE " + subject + " = ?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, column);

//			if(subject.equals("no"))
//				stmt.setInt(1, "no");
//			else if(subject.equals("id"))
//				stmt.setString(1, "id");
//			
			if (pk1 == null) {
				stmt.setInt(2, pk);
			} else {
				stmt.setString(2, pk1);
			}
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

				return membership;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	// 행 추가 (insert)
	// flower 테이블의 각 컬럼에 값을 insert해주는 메소드
	// insert가 정상적으로 되면 return 1
	// insert가 정상적으로 되지않으면 return -1
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

			int result = stmt.executeUpdate();

			return 1;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(null, stmt, null);
		}
		return -1;
	}

	public int update(int no, String name, String phoneNumber, String id, String pw, String address) {
		String sql = "Update membership set no = ?, name = ?, phoneNumber = ?, pw = ?, address = ? where id = ?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");

			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, no);
			stmt.setString(2, name);
			stmt.setString(3, phoneNumber);
			stmt.setString(4, pw);
			stmt.setString(5, address);
			stmt.setString(6, id);

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