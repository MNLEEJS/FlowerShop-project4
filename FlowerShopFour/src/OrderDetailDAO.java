import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.management.RuntimeErrorException;

import dbutil.DBUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 작업자 : 이나겸

// order_detail 테이블 컬럼별 선언
@Data
@AllArgsConstructor
@Builder
class OrderDetail {
	private int no;
	private int flowerNo;
	private int count;
}

// Mapper
class OrderDetailMapper implements IResultMapper<OrderDetail> {

	@Override
	public OrderDetail resultMapping(ResultSet rs) {
		try {
			int no = rs.getInt("no");
			int flowerNo = rs.getInt("flowerNo");
			int count = rs.getInt("count");

			return OrderDetail.builder().no(no).flowerNo(flowerNo).count(count).build();

		} catch (SQLException e) {
			e.printStackTrace();

			throw new RuntimeException("OrderDetail 매핑 중 예외 발생", e);
		}
	}
}

// Mapping
class OrderDetailService {
	private static final IResultMapper<OrderDetail> orderDetailMapper = new OrderDetailMapper();
	private OrderDetailDAO orderDetailDAO;

	// 생성자
	public OrderDetailService(OrderDetailDAO orderDetailDAO) {
		super();
		this.orderDetailDAO = orderDetailDAO;
	}
}

// CRUD 구성
// order_detail 테이블의 조회 (select), 행 추가 (insert)
public class OrderDetailDAO {
	OrderDetailMapper orderDetailMapper = new OrderDetailMapper();
	OrderDetail orderDetail;

	// 조회 (select)
	// order_detail 테이블의 전체 컬럼 조회
	public OrderDetail selectAll() {
		String sql = "select * from order_detail";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				OrderDetail orderDetail = orderDetailMapper.resultMapping(rs);
			}
			return orderDetail;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 행 추가 (insert)
	// flower 테이블의 각 컬럼에 값을 insert해주는 메소드
	// insert가 정상적으로 되면 return 1
	// insert가 정상적으로 되지않으면 return -1
	public int insert(int flowerNo, int count) {
		String sql = "insert into order_detail (flower_No, count) values (?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, flowerNo);
			stmt.setInt(2, count);

			int result = stmt.executeUpdate();

			return result;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return -1;
	}
	
	// 고객이 주문한 정보 테이블(orderDetail)의 수량 update 메소드
	public int update(int count, int no) {
		String sql = "update flower set count = ? where no = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, count);
		
			return 1;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return -1;
	}
}