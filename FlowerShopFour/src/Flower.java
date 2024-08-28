import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
