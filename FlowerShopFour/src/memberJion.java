
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 작성자 || 이진석
// 회원가입 클래스
public class memberJion extends JDialog {
	FontL f = new FontL();
	makingJ makeJ = new makingJ();
	List<JTextField> listTxt = new ArrayList<JTextField>();

	public memberJion() {
		JPanel pnl = new JPanel();
		setModal(true);
		int x = 100;
		int y = 50;
		int h = 400;
		int w = 40;
		라벨및텍스트만들기(x, y, h, w, 6, pnl);
		JButton btn = makeJ.버튼만들기("회원가입", f.font1, 150, 400, 150, 60, pnl);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean go = false;
				
				for (int i = 0; i < listTxt.size(); i++) {
					System.out.println(listTxt.get(i).getText());
					go = true;
				}
				// insert문 넣고 올바르게 들어가면
				if (go) {
					JOptionPane.showMessageDialog(null, "회원 가입이 완료되었습니다.");
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "올바른 값을 넣어주세요");
				}
			}
		});
		JButton btn1 = makeJ.버튼만들기("뒤로가기", f.font1, 300, 400, 150, 60, pnl);
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "회원가입을 취소합니다.");
				setVisible(false);
			}
		});

		pnl.add(btn);
		pnl.add(btn1);
		pnl.setLayout(null);
		pnl.setSize(new Dimension(600, 600));
		add(pnl);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setSize(new Dimension(600, 600));
		setLayout(null);
	}

	// 라벨 생성 pnl
	public void 라벨및텍스트만들기(int x, int y, int h, int w, int a, JPanel pnl) {
		String name = "아이디";
		for (int i = 0; i < a; i++) {
			if (i == 1) {
				name = "비밀번호";
			} else if (i == 2) {
				name = "비밀번호 확인";
			} else if (i == 3) {
				name = "이름";
			} else if (i == 4) {
				name = "휴대폰 번호";
			} else if (i == 5) {
				name = "주소";
			}
			makeJ.라벨만들기(name, f.font1, x, y, h, w, pnl);
			JTextField txt = makeJ.텍스트필드만들기(15, f.font1, x + 200, y, h, w, pnl);
			if (i != 1) {
				listTxt.add(txt);
			}
			y += 50;
		}
	}
}
