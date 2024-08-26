import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 작업자 : 이나겸
// flower 테이블 컬럼별 선언

@Data
@AllArgsConstructor
@Builder
public class Flower {
	private int no;
	private String category;
	private String name;
	private int count;
	private int price;
	private int image_no;
}