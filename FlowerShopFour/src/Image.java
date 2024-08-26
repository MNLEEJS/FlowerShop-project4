import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 작업자 : 이나겸
// flower_pic 테이블 컬럼별 선언

@Data
@AllArgsConstructor
@Builder
public class Image {
	private int no;
	private String code;
}