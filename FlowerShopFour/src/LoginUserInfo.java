import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder

// 유저가 로그인 했을떄 저장할 공간
public class LoginUserInfo {
	public LoginUserInfo() {
	}
	private String ID;
	private String PW;
}
