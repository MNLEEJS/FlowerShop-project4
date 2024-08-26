import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

//작업자 : 이아현

//이미지 버튼 클릭시 생성되는 다이얼로그창
public class ColumnImage extends JDialog {
	public ColumnImage() {
		super();
		// 객체 생성
		Dialog d = new Dialog(this, "이미지 확인창");
		// 위치와 크기 결정
		setBounds(0, 0, 400, 400);
		// 창을 화면에 보이게함
		setVisible(true);
		// 크기 결정
		d.setSize(400, 400);
		// 다이어로그를 화면에 보이게함
	}
	
	public static void main(String[] args) {
		new ColumnImage();
	}
}
