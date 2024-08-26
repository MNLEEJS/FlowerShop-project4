import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

//class FontHolder {
//	private Font font;
//
//	public FontHolder() {
//
//		// 폰트 사용중에 발생할 예외 처리
//		try {
//			font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/fonts/발표자료 글꼴.ttf"));
//
//		} catch (FontFormatException e) {
//			e.printStackTrace();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// 폰트 사용할 부분에 호출시킬 메소드
//	// (폰트의 스타일, 폰트 크기)
//	public Font getUseFont(int style, float size) {
//		return font.deriveFont(style, size);
//	}
//}

public class FontL {
	
	public Font font1 = new Font("궁서체", Font.BOLD, 25);
	public Font font2 = new Font("돋움", Font.BOLD, 50);
	public Font font3 = new Font("돋움", Font.BOLD, 30);
	public Font font4 = new Font("돋움", Font.BOLD, 20);
	public Font font5 = new Font("돋움", Font.BOLD, 18);
	
}