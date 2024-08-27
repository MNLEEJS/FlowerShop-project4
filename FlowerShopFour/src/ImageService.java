import java.sql.ResultSet;
import java.sql.SQLException;

// 작업자 : 이나겸
// mapping

public class ImageService {
	private static final IResultMapper<Image> imageMapper = new ImageMapper();
	private ImageDAO imageDAO;

	// 생성자
	public ImageService(ImageDAO imageDAO) {
		super();
		this.imageDAO = imageDAO;
	}
}