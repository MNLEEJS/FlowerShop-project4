import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Encoder;

public class ImageFileInsert {
	
	public void ImageFile(String code) {
		Path path = Paths.get(code);
//		System.out.println(code);
		try {
			byte[] readAllBytes = Files.readAllBytes(path);
			Encoder encoder = Base64.getEncoder();
			String coding = encoder.encodeToString(readAllBytes);
			// intsert 메소드만 하면 끝
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
