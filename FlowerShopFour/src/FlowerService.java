import java.sql.ResultSet;
import java.sql.SQLException;

// 작업자 : 이나겸
// mapping

public class FlowerService {
	private static final IResultMapper<Flower> flowerMapper = new FlowerMapper();
	private FlowerDAO flowerDAO;

	// 생성자
	public FlowerService(FlowerDAO flowerDAO) {
		super();
		this.flowerDAO = flowerDAO;
	}
}