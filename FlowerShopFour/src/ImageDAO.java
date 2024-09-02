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
// flower_pic 테이블의 컬럼별 선언, Mapper, CRUD 구성하는 코드 작성

//flower_pic 테이블 컬럼별 선언
@Data
@AllArgsConstructor
@Builder
class Image {
	private int no;
	private String code;
}

// Mapper
class ImageMapper implements IResultMapper<Image> {

	@Override
	public Image resultMapping(ResultSet rs) {
		try {
			int no = rs.getInt("no");
			String code = rs.getString("code");

			return Image.builder().no(no).code(code).build();

		} catch (SQLException e) {
			e.printStackTrace();

			throw new RuntimeException("Image 매핑 중 예외 발생", e);
		}
	}
}

class ImageService {
	private static final IResultMapper<Image> imageMapper = new ImageMapper();
	private ImageDAO imageDAO;

	// 생성자
	public ImageService(ImageDAO imageDAO) {
		super();
		this.imageDAO = imageDAO;
	}
}

// CRUD 구성
// flower_pic 테이블의  조회 (select), 행 추가 (insert), 자료 수정(update)
public class ImageDAO {
	ImageMapper imageMapper = new ImageMapper();
	Image image;

	// 조회 (select)
	// flower_pic 테이블의 전체 컬럼 조회
	public Image selectAll() {
		String sql = "select * from flower_pic";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Image image = imageMapper.resultMapping(rs);
			}
			return image;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 조회 (select), List 이용
	// flower_pic 테이블의 전체 컬럼 조회
//	public List<Image> selectAllWithList() {
//		String sql = "select * from flower_pic";
//
//		List<Image> list = new ArrayList<>();
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
//				Image image = imageMapper.resultMapping(rs);
//				list.add(image);
//			}
//			return list;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	// pk로 조회 (select)
	// flower_pic 테이블의 pk : no
	// SELECT LAST_INSERT_ID()

	public int LastID(Connection conn) {
		String sql = "SELECT LAST_INSERT_ID()";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int a = rs.getInt(1);
				return a;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, null);
		}
		return -1;
	}

	public String findByNo(int no) {
		String sql = "select * from flower_pic where no = ?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			List<Image> list = new ArrayList<>();
			rs = stmt.executeQuery();

			if (rs.next()) {
				Image image = imageMapper.resultMapping(rs);
				list.add(image);
			}
			return list.get(0).getCode();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	// 행 추가 (insert)
	// flower_pic 테이블의 각 컬럼에 값을 insert해주는 메소드
	// insert가 정상적으로 되면 return 1
	// insert가 정상적으로 되지않으면 return -1
	public int insert(String code) {
		String sql = "insert into flower_pic (code) values (?)";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, code);

			int result = stmt.executeUpdate();

			int picNumber = LastID(conn);
			return picNumber;
			// select문이 와야함

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return -1;
	}
	
	// 방금 추가
	// 이미지를 불러오는 selete문
	//orderDetail을 이용하여 Flower_pic의 내용을 불러오는 select문
	public String selectOrderImage(int no) {
		String sql = "SELECT fp.no, fp.code \r\n" + 
				"FROM flower_pic AS fp\r\n" + 
				"INNER JOIN (\r\n" + 
				"    SELECT fl.no, fl.image_no\r\n" + 
				"    FROM order_detail AS od\r\n" + 
				"    INNER JOIN flower AS fl ON od.flower_no = fl.no \r\n" + 
				"    WHERE od.no = ? \r\n" + 
				") AS flowerOrder ON flowerOrder.image_no = fp.no;\r\n";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			List<Image> list = new ArrayList<>();
			rs = stmt.executeQuery();

			if (rs.next()) {
				Image image = imageMapper.resultMapping(rs);
				list.add(image);
			}
			return list.get(0).getCode();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
	
	
}