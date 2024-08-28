import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.swing.ImageIcon;

public class ImageFileInsert {
	
	public String ImageFile(String code) {
		Path path = Paths.get(code);
//		System.out.println(code);
		try {
			byte[] readAllBytes = Files.readAllBytes(path);
			Encoder encoder = Base64.getEncoder();
			String coding = encoder.encodeToString(readAllBytes);
			// intsert 메소드만 하면 끝
			return coding;
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	// 코드화시킨 이미지 아이콘 변환
	public ImageIcon ImageiconCreate(String coding) {
		// 디코드화 시키는 디코드 선언
		Decoder decoder = Base64.getDecoder();
		// 디코드화 시킨 코드를 바이트로 변경
		byte[] decode = decoder.decode(coding);
		// 바이트로 바꾼 코드를 이미지 아이콘으로 변경해서 리턴해준다.
		return new ImageIcon(decode);
		
	}
	
	
	// 코드화 시킨 이미지를 다시 이미지아이콘으로 변화하는 방법은? 
	
	
}
