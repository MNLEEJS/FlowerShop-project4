import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 작업자 : 이아현

// 관리자 페이지 
public class OwnerPageFrame extends JFrame {
	FontL f = new FontL();
	makingJ j = new makingJ();
	JCheckBox c = new JCheckBox();

	// 관리자 페이지의 상단 라벨과 버튼 생성
	public OwnerPageFrame() {
		JPanel pnl = new JPanel();
		int y = 120;
		int x = 100;
		int w = 50;
		int h = 100;

		JLabel lbl1 = j.라벨만들기("관리자 페이지", f.font1, 10, 10, 200, 50, pnl);
		JLabel lbl2 = j.라벨만들기("카테고리 1", f.font1, 210, 10, 170, 50, pnl);

		JButton btnAddColumn = j.버튼만들기("컬럼추가", f.font1, 370, 10, 140, 50, pnl);
		JButton btnCoulumn = j.버튼만들기("컬럼삭제", f.font1, 520, 10, 140, 50, pnl);

		for (int i = 0; i < 6; i++) {
			String name = "컬럼1";
			if (i == 1) {
				name = "컬럼2";
			} else if (i == 2) {
				name = "컬럼3";
			} else if (i == 3) {
				name = "컬럼4";
			} else if (i == 4) {
				name = "컬럼5";
			} else if (i == 5) {
				name = "컬럼6";
			}
			// 컬럼 개수대로 체크박스 생성
			j.체크박스만들기(50, y, 50, 50, pnl);
			
			// 컬럼 개수대로 라벨 생성
			JLabel lbl = j.라벨만들기(name, f.font1, x, y, h, w, pnl);

			// 컬럼마다의 텍스트 필드, 이미지 버튼 생성
			JTextField txt1 = j.텍스트필드만들기(10, f.font1, 2 * x, y, h + 10, w, pnl);
			JTextField txt2 = j.텍스트필드만들기(10, f.font1, 3 * x + 30, y, h + 10, w, pnl);
			JButton image = j.버튼만들기("이미지", f.font1, 4 * x + 60, y, h + 10, w, pnl);
			y += 60;

			// 텍스트 필드의 정보 입력창
			txt1.setText("남은 수량");
			txt2.setText("가격");
			
			// 텍스트 필드
			txt1.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (txt1.getText().isEmpty())
						txt1.setText("남은 수량");
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (txt1.getText().equals("남은 수량"))
						txt1.setText("");
				}
			});

			txt2.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (txt2.getText().isEmpty())
						txt2.setText("가격");
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (txt2.getText().equals("가격"))
						txt2.setText("");
				}
			});

			// 하단 변경 완료, 뒤로가기 버튼 생성
			JButton btnConfirm = j.버튼만들기("변경완료", f.font1, 380, 500, 140, 50, pnl);
			JButton btnBackBtn = j.버튼만들기("뒤로가기", f.font1, 530, 500, 140, 50, pnl);

			add(pnl);
			pnl.setLayout(null);
			pnl.setSize(new Dimension(700, 600));
			setLayout(null);
			setSize(new Dimension(700, 600));

	image.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ColumnImage columnImage = new ColumnImage();
				columnImage.setVisible(true);
				
			}
		});
		}
		
		
	}

	public static void main(String[] args) {
		new OwnerPageFrame().setVisible(true);
	}
}
