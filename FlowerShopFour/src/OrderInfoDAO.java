import java.sql.Connection;
import java.sql.Date;
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

// option_info 테이블 컬럼별 선언
@Data
@AllArgsConstructor
@Builder
class OrderInfo {
	private int orderNo;
	private int flowerOrderNo;
	private int sellingCheck;
}

//Mapper
class OrderInfoMapper implements IResultMapper<OrderInfo> {

	@Override
	public OrderInfo resultMapping(ResultSet rs) {
		try {
			int orderNo = rs.getInt("orderNo");
			int flowerOrderNo = rs.getInt("flowerOrderNo");
			int sellingCheck = rs.getInt("sellingCheck");
			return OrderInfo.builder().orderNo(orderNo).flowerOrderNo(flowerOrderNo).sellingCheck(sellingCheck).build();

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

	// 작성자 -- 이진석
	// 주문번호로 조회해서 리스트값을 출력받는
	public List<OrderInfo> selectOrderNo(int pk) {
		String sql = "select * from order_info Where orderNo = ?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pk);
			rs = stmt.executeQuery();

			List<OrderInfo> list = new ArrayList<OrderInfo>();
			while (rs.next()) {
				OrderInfo orderInfo = orderInfoMapper.resultMapping(rs);
				list.add(orderInfo);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

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
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	// 행 추가 (insert)
	// order_info 테이블의 각 컬럼에 값을 insert해주는 메소드
	// insert가 정상적으로 되면 return 1
	// insert가 정상적으로 되지않으면 return -1
	public int insert(int orderNo, int flowerOrderNo, int sellingCheck) {
		String sql = "insert into option_detail (orderNo, flowerOrderNo, sellingCheck) values (?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderNo);
			stmt.setInt(2, flowerOrderNo);
			stmt.setInt(3, sellingCheck);

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