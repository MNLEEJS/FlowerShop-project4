import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

// 회원 정보에 담을 변수 선언

public class Membership {
	private int no;
	private String name;
	private String phonenumber;
	private String id;
	private String pw;
	private String address;

// 회원 정보
}
