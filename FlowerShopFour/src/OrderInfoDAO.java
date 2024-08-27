import java.sql.Connection;
import java.sql.Date;
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
class OrderInfo {
	private int orderNo;
	private int flowerOrderNo;
	private Date date;
}

//Mapper
class OrderInfoMapper implements IResultMapper<OrderInfo> {

	@Override
	public OrderInfo resultMapping(ResultSet rs) {
		try {
			int orderNo = rs.getInt("orderNo");
			int flowerOrderNo = rs.getInt("flowerOrderNo");
			Date date = rs.getDate("date");
			System.out.println(date);
			return OrderInfo.builder().orderNo(orderNo).flowerOrderNo(flowerOrderNo).date(date).build();

		} catch (SQLException e) {
			e.printStackTrace();

			throw new RuntimeException("OrderInfo 매핑 중 예외 발생", e);
		}
	}
}

//Mapping
class OrderInfoService {
	private static final IResultMapper<OrderInfo> OrderInfoMapper = new OrderInfoMapper();
	private OrderInfoDAO orderInfoDAO;

	// 생성자
	public OrderInfoService(OrderInfoDAO orderInfoDAO) {
		super();
		this.orderInfoDAO = orderInfoDAO;
	}
}

// CRUD 구성
// order_info 테이블의 조회 (select), 행 추가 (insert)
public class OrderInfoDAO {
	OrderInfoMapper orderInfoMapper = new OrderInfoMapper();
	OrderInfo orderInfo;

	// 조회 (select)
	// order_info 테이블의 전체 컬럼 조회
	public OrderInfo selectAll() {
		String sql = "select * from order_info";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				OrderInfo orderInfo = orderInfoMapper.resultMapping(rs);
			}
			return orderInfo;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 행 추가 (insert)
	// order_info 테이블의 각 컬럼에 값을 insert해주는 메소드
	// insert가 정상적으로 되면 return 1
	// insert가 정상적으로 되지않으면 return -1
	public int insert(int orderNo, int flowerOrderNo, Date date) {
		String sql = "insert into option_detail (orderNo, flowerOrderNo, date) values (?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderNo);
			stmt.setInt(2, flowerOrderNo);
			stmt.setDate(3, date);

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