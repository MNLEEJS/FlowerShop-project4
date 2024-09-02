import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 작업자 : 이나겸

// option_detail 테이블 컬럼별 선언
@Data
@AllArgsConstructor
@Builder
class OptionDetail {
	private int flowerOrderNo;
	private int optionNo;
}

// Mapper
class OptionDetailMapper implements IResultMapper<OptionDetail> {

	@Override
	public OptionDetail resultMapping(ResultSet rs) {
		try {
			int flowerOrderNo = rs.getInt("flowerOrderNo");
			int optionNo = rs.getInt("optionNo");

			return OptionDetail.builder().flowerOrderNo(flowerOrderNo).optionNo(optionNo).build();

		} catch (SQLException e) {
			e.printStackTrace();

			throw new RuntimeException("OptionDetail 매핑 중 예외 발생", e);
		}
	}
}

// Mapping
class OptionDetailService {
	private static final IResultMapper<OptionDetail> OptionDetailMapper = new OptionDetailMapper();
	private OptionDetailDAO optionDetailDAO;

	// 생성자
	public OptionDetailService(OptionDetailDAO optionDetailDAO) {
		super();
		this.optionDetailDAO = optionDetailDAO;
	}
}

// CRUD 구성
// order_detail 테이블의 조회 (select), 행 추가 (insert)
public class OptionDetailDAO {
	OptionDetailMapper optionDetailMapper = new OptionDetailMapper();
	OptionDetail optionDetail;

	public int selectLastPK() {
		String sql = "SELECT LAST_INSERT_ID()";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int a = 0;
			if (rs.next()) {
				a = rs.getInt("LAST_INSERT_ID");
			}
			if (a > 0) {
				return a;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 조회 (select)
	// order_detail 테이블의 전체 컬럼 조회
	public OptionDetail selectAll() {
		String sql = "select * from option_detail";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				OptionDetail optionDetail = optionDetailMapper.resultMapping(rs);
			}
			return optionDetail;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 행 추가 (insert)
	// flower 테이블의 각 컬럼에 값을 insert해주는 메소드
	// insert가 정상적으로 되면 return 1
	// insert가 정상적으로 되지않으면 return -1
	public int insert(int flowerOrderNo, int optionNo) {
		String sql = "insert into option_detail (flowerOrderNo, optionNo) values (?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, flowerOrderNo);
			stmt.setInt(2, optionNo);

			int result = stmt.executeUpdate();

			return result;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return -1;
	}
}