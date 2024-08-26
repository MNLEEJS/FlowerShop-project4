import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddColumn extends JFrame {
	// makingJ, FontL 선언으로 메소드 사용 가능
	makingJ j = new makingJ();
	FontL f = new FontL();

	// 하나의 패널과 메인 컬럼 추가
	// 컬럼추가 화면의 각 라벨과 텍스트 필드 생성
	public AddColumn() {
		JPanel pnl = new JPanel();
		JLabel lbl6 = j.라벨만들기("컬럼 추가 화면", f.font2, 120, 50, 500, 50, pnl);

		int y = 120;
		int x = 150;
		int w = 50;
		int h = 100;
		
		
		for (int i = 0; i < 5; i++) {
			String name = "카테고리";
			if (i == 1) {
				name = "컬럼명";
			} else if (i == 2) {
				name = "수량";
			} else if (i == 3) {
				name = "가격";
			} else if (i == 4) {
				name = "이미지";
			}
			JLabel lbl = j.라벨만들기(name, f.font1, x, y, h, w, pnl);
			JTextField txt1 = j.텍스트필드만들기(15, f.font1, 2 * x, y, h + 50, w, pnl);
			y += 60;
		}
		
		// 하단 확인, 취소 버튼 생성
		JButton btn1 = j.버튼만들기("확인", f.font1, 300, 450, 100, 50, pnl);
		JButton btn2 = j.버튼만들기("취소", f.font1, 410, 450, 100, 50, pnl);
		
		pnl.add(btn1);
		
		pnl.setLayout(null);
		pnl.setSize(new Dimension(600, 600));
		add(pnl);
		setLayout(null);
		setSize(new Dimension(600, 600));

	}

	public static void main(String[] args) {
		new AddColumn().setVisible(true);
	}
}
