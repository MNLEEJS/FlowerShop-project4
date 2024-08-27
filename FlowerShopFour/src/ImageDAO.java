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

// mapping
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
	public List<Image> findByNo(int no) {
		String sql = "select no, code from flower_pic where = ?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("project3");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);

			List<Image> list = new ArrayList<>();
			rs = stmt.executeQuery();

			while (rs.next()) {
				Image image = imageMapper.resultMapping(rs);
				list.add(image);
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
	// flower_pic 테이블의 각 컬럼에 값을 insert해주는 메소드
	// insert가 정상적으로 되면 return 1
	// insert가 정상적으로 되지않으면 return -1
	public int insert(int no, String code) {
		String sql = "insert into flower_pic (no, code) values (?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.setString(2, code);

			int result = stmt.executeUpdate();
			// select문이 와야함
			return 1;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.closeAll(null, stmt, conn);
		}
		return -1;
	}
}