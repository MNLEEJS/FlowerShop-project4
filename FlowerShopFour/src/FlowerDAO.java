import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.x.ResultMessageListener;

import dbutil.DBUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 작업자 : 이나겸
// flower 테이블의 컬럼별 선언, Mapper, CRUD 구성하는 코드 작성

// flower 테이블 컬럼별 선언
// Mapper
class FlowerMapper implements IResultMapper<Flower> {

	@Override
	public Flower resultMapping(ResultSet rs) {
		try {
			int no = rs.getInt("no");
			String category = rs.getString("category");
			String name = rs.getString("name");
			int count = rs.getInt("count");
			int price = rs.getInt("price");
			int image_no = rs.getInt("image_no");

			return Flower.builder().no(no).category(category).name(name).count(count).price(price).image_no(image_no)
					.build();

		} catch (SQLException e) {
			e.printStackTrace();

			throw new RuntimeException("Flower 매핑 중 예외 발생", e);
		}
	}
}

class FlowerService {
	private static final IResultMapper<Flower> flowerMapper = new FlowerMapper();
	private FlowerDAO flowerDAO;

	// 생성자
	public FlowerService(FlowerDAO flowerDAO) {
		super();
		this.flowerDAO = flowerDAO;
	}
}

// CRUD 구성
// flower 테이블의  조회 (select), 행 추가 (insert), 자료 수정(update)
public class FlowerDAO {
	FlowerMapper flowerMapper = new FlowerMapper();
	Flower flower;
	ImageDAO dao = new ImageDAO();
	
	
	// 조회 (select)
	// flower 테이블의 전체 컬럼 조회
//	public Flower selectAll() {
//		String sql = "select * from flower";
//
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = DBUtil.getConnection("project3");
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//
//			while (rs.next()) {
//				Flower flower = flowerMapper.resultMapping(rs);
//			}
//			return flower;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	// 조회 (select) , List 이용
	// flower 테이블의 전체 컬럼 조회
	public List<Flower> selectAllWithList() {
		String sql = "select * from flower";

		List<Flower> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Flower flower = flowerMapper.resultMapping(rs);
				list.add(flower);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 조건(where절)에 따라 달라지는 조회 (select)
	public List<Flower> findBy(String columnName, int no, String name, int image_no) {
		String sql = "select no, category, name, count, price, image_no from flower where " + columnName + " = ?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);

			if (columnName.equals("no")) {
				stmt.setString(1, columnName);
				stmt.setInt(2, no);

			} else if (columnName.equals("name")) {
				stmt.setString(1, columnName);
				stmt.setString(2, name);

			} else if (columnName.equals("image_no")) {
				stmt.setString(1, columnName);
				stmt.setInt(2, image_no);
			}

			List<Flower> list = new ArrayList<>();
			rs = stmt.executeQuery();

			while (rs.next()) {
				Flower flower = flowerMapper.resultMapping(rs);
				list.add(flower);
			}
			return list;

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
	public int insert(String category, String name, int count, int price, String code) {
		String sql = "insert into flower (category, name, count, price, image_no) values (?, ?, ?, ?, ?)";
		int image_no = dao.insert(code);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, category);
			stmt.setString(2, name);
			stmt.setInt(3, count);
			stmt.setInt(4, price);
			stmt.setInt(5, image_no);

			int result = stmt.executeUpdate();

			return 1;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return -1;
	}

	// 행 수정 (update)
	// flower 테이블의 각 컬럼에 값을 update해주는 메소드
	// update가 정상적으로 되면 return 1
	// update가 정상적으로 되지않으면 return -1
	public int update(int no, String category, String name, int count, int price, int image_no) {
		String sql = "update flower set catgory = ?, name = ?, count = ?, price = ?, image_no = ? where no = ?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, category);
			stmt.setString(2, name);
			stmt.setInt(3, count);
			stmt.setInt(4, price);
			stmt.setInt(5, image_no);
			stmt.setInt(6, no);

			return 1;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return -1;
	}
}