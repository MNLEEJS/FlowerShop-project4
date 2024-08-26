import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbutil.DBUtil;

// 작업자 : 이나겸
// CRUD 구성

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
			
			return Flower.builder().no(no).category(category).name(name).count(count).price(price).image_no(image_no).build();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new RuntimeException("Flower 매핑 중 예외 발생", e);
		}
	}
}

public class FlowerDAO {
	FlowerMapper flowerMapper = new FlowerMapper();
	Flower flower;
	// CRUD 구성
	// 조회 (select)
	// 행 추가 (insert)
	// 행 삭제 (delete)
	// 자료 수정(update)

	// 조회 (select)
	// flower 테이블의 전체 컬럼 조회
	public Flower selectAll() {
		
		String sql = "select * from flower";
		
		try (Connection conn = DBUtil.getConnection("project3");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				Flower flower = flowerMapper.resultMapping(rs);
			}
			return flower;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 행 추가 (insert)
	// flower 테이블의 각 컬럼에 값을 insert해주는 메소드
	// insert가 정상적으로 되면 return 1
	// insert가 정상적으로 되지않으면 return -1
	public int insert(int no, String category, String name, int count, int price, int image_no) {
		String sql = "insert into flower (no, category, name, count, price, image_no) values (?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setString(2, category);
			stmt.setString(3, name);
			stmt.setInt(4, count);
			stmt.setInt(5, price);
			stmt.setInt(6, image_no);
			
			int result = stmt.executeUpdate();
			
			if (result == 1) {
				rs.next();
			}
			
			return 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return -1;
 	}
}