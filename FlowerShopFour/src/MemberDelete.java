import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MemberDelete extends JFrame {

	makingJ j = new makingJ();
	FontL f = new FontL();

	public MemberDelete() {
		JPanel pnl = new JPanel();

		JButton btnCan = j.버튼만들기("취소", f.font5, 150, 250, 100, 50, pnl);
		JButton btnDelete = j.버튼만들기("회원탈퇴", f.font5, 50, 250, 100, 50, pnl);
		add(pnl);
		pnl.setLayout(null);
		setLayout(null);
		setSize(new Dimension(300, 300));
	}
}
