import java.sql.ResultSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

// 작성자 : 이아현
public class UserOrder {
	// 회원 주문 정보
	// 주문 번호, 회원 번호
	private int no;
	private int user_no;

}

// userOrder mapper 생성
class UserOrderMapper implements IResultMapper<UserOrder> {

	@Override
	public UserOrder resultMapping(ResultSet rs) {
		try {
			int no = rs.getInt("no");
			int user_no = rs.getInt("user_no");

			return UserOrder.builder()
					.no(no)
					.user_no(user_no)
					.build();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

