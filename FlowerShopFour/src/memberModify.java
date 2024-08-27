
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class memberModify extends JDialog {
	FontL f = new FontL();
	makingJ makeJ = new makingJ();
	memberJion ma = new memberJion();
	
	List<JTextField> listTxt = new ArrayList<JTextField>(); // 변경할 텍스르 리스르
	public memberModify() {
		JPanel pnl = new JPanel();
		int x = 60;
		int y = 220;
		int h = 200;
		int w = 50;
		int a = 5;
		라벨및텍스트만들기(x, y, h, w, a, pnl);
		JLabel lbl = makeJ.라벨만들기("회원 정보 수정", f.font2, 10, 10, 400, 100, pnl);
		JLabel lblID = makeJ.라벨만들기("아이디", f.font3, 130, 110, 150, 50, pnl);
		JTextField txtID = makeJ.텍스트필드만들기(10, f.font1, 280, 110, 200, 50, pnl);
		JLabel lblPW = makeJ.라벨만들기("비밀번호", f.font3, 130, 160, 150, 50, pnl);
		JTextField txtPW = makeJ.텍스트필드만들기(10, f.font1, 280, 160, 200, 50, pnl);
		
		JButton btn = makeJ.버튼만들기("확인", f.font1, 480, 110, 100, 100, pnl);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(txtID.getText());
				System.out.println(txtPW.getText());
				
			}
		});

		JButton btn1 = makeJ.버튼만들기("변경하기", f.font1, 480, 350, 50, 150, pnl);
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int j = 0; j < listTxt.size(); j++) {
					System.out.println(listTxt.get(j).getText());
				}
			}
		});
		pnl.setLayout(null);
		pnl.setSize(new Dimension(600, 600));
		add(pnl);
		setLayout(null);
		setSize(new Dimension(600, 600));
	}

	public void 라벨및텍스트만들기(int x, int y, int h, int w, int a, JPanel pnl) {
		String name = "비밀번호";
		for (int i = 0; i < a; i++) {
			if (i == 1) {
				name = "비밀번호 확인";
			} else if (i == 2) {
				name = "이름";
			} else if (i == 3) {
				name = "휴대폰 번호";
			} else if (i == 4) {
				name = "주소";
			}
			makeJ.라벨만들기(name, f.font1, x, y, h, w, pnl);
			JTextField txt = makeJ.텍스트필드만들기(15, f.font1, x + 200, y, h, w, pnl);
			if(i != 1) {
				listTxt.add(txt);
			}
			String name1 = name;
			txt.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					if (txt.getText().isEmpty()) 
						txt.setText(name1);
					
				}

				@Override
				public void focusGained(FocusEvent e) {
					if(txt.getText().equals(name1)) 
						txt.setText(" ");
					
				}
			});
			if (i != 1) {
				makeJ.체크박스만들기(10, y, 50, 50, pnl);
				y += 50;
			} else {
				y += 50;
			}
		}
	}
}
