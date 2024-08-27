import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbutil.DBUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 작업자 : 이나겸

// option_info 테이블 컬럼별 선언
@Data
@AllArgsConstructor
@Builder
class OptionInfo {
	private int no;
	private String color;
	private int count;
	private String kind;
}

// Mapper
class OptionInfoMapper implements IResultMapper<OptionInfo> {

	@Override
	public OptionInfo resultMapping(ResultSet rs) {
		try {
			int no = rs.getInt("no");
			String color = rs.getString("color");
			int count = rs.getInt("count");
			String kind = rs.getString("kind");

			return OptionInfo.builder().no(no).color(color).count(count).kind(kind).build();

		} catch (SQLException e) {
			e.printStackTrace();

			throw new RuntimeException("OptionInfo 매핑 중 예외 발생", e);
		}
	}
}

// Mapping
class OptionInfoService {
	private static final IResultMapper<OptionInfo> OptionInfoMapper = new OptionInfoMapper();
	private OptionInfoDAO optionInfoDAO;

	// 생성자
	public OptionInfoService(OptionInfoDAO optionInfoDAO) {
		super();
		this.optionInfoDAO = optionInfoDAO;
	}
}

// CRUD 구성
// option_info 테이블의 조회 (select), 행 추가 (insert)
public class OptionInfoDAO {
	OptionInfoMapper optionInfoMapper = new OptionInfoMapper();
	OptionInfo optionInfo;

	// 조회 (select)
	// order_detail 테이블의 전체 컬럼 조회
	public OptionInfo selectAll() {
		String sql = "select * from option_info";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				OptionInfo optionInfo = optionInfoMapper.resultMapping(rs);
			}
			return optionInfo;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 행 추가 (insert)
	// flower 테이블의 각 컬럼에 값을 insert해주는 메소드
	// insert가 정상적으로 되면 return 1
	// insert가 정상적으로 되지않으면 return -1
	public int insert(String color, int count, String kind) {
		String sql = "insert into option_info (color, count, kind) values (?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, color);
			stmt.setInt(2, count);
			stmt.setString(3, kind);

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