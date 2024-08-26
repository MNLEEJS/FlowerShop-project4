import java.sql.ResultSet;
import java.sql.SQLException;

// 작업자 : 이나겸
// mapping

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

public class ImageService {
	private static final IResultMapper<Image> imageMapper = new ImageMapper();
	private ImageDAO imageDAO;

	// 생성자
	public ImageService(ImageDAO imageDAO) {
		super();
		this.imageDAO = imageDAO;
	}
}