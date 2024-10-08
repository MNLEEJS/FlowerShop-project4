import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//작성자 || 이진석
public class makingJ {
	//									라벨명				//폰트		/x축		/y축	/가로길이	/세로길이		/패널
	public JLabel 라벨만들기(String name, Font font, int x, int y, int h, int w, JPanel pnl) {
		JLabel lbl = new JLabel(name);
		lbl.setFont(font);
		lbl.setBounds(x, y, h, w);
		pnl.add(lbl);
		return lbl;
	}
														//	필드길이//폰트	/x축	/y축	/가로길이	/세로길이		/패널
	public JTextField 텍스트필드만들기(int line, Font font, int x, int y, int h, int w, JPanel pnl) {
			JTextField txt = new JTextField(line);
			txt.setFont(font);
			pnl.add(txt);
			txt.setBounds(x, y, h, w);
		return txt;
	}
//													/x축	/y축	/가로길이	/세로길이		/패널
	public JCheckBox 체크박스만들기(int x, int y, int h, int w, JPanel pnl) {
		JCheckBox che = new JCheckBox();
		che.setBounds(x, y, h, w);
		pnl.add(che);
		return che;
	}
//												버튼이름		//폰트	/x축	/y축	/가로길이	/세로길이		/패널
	public JButton 버튼만들기(String name, Font font, int x, int y, int h, int w, JPanel pnl) {
		JButton btn = new JButton(name);
		btn.setBounds(x, y, h, w);
		btn.setFont(font);
		pnl.add(btn);
		return btn;
	}
	
	// 패널 만드는 메소드
	public JPanel makePanel(int x, int y, int width, int height, JPanel pnl) {
		JPanel includePnl = new JPanel();
		includePnl.setBounds(x, y, width, height);
		pnl.add(includePnl);
		return includePnl;
	}
	
	// 콤보박스 만드는 메소드
	public JComboBox<Integer> makeComboBox(Integer[] arr, int x, int y, int width, int height, JPanel pnl) {
		JComboBox<Integer> comboBox = new JComboBox<Integer>(arr);
		comboBox.setBounds(x, y, width, height);
		pnl.add(comboBox);
		return comboBox;
	}
}
