import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//작성자 || 이진석
public class FlowerProduct extends JDialog {
	makingJ j = new makingJ();
	FontL f = new FontL();
	ImageIcon icon = new ImageIcon(Main.class.getResource("/image/예시1.jpg"));

	// name = 카테고리명 btn.getText();
	public ImageIcon NewIcon(String name) {
		if (name.equals("카테고리1")) {
			ImageIcon icon = new ImageIcon(Main.class.getResource("/image/예시1.jpg"));
		} else if (name.equals("카테고리2")) {
			ImageIcon icon = new ImageIcon(Main.class.getResource("/image/예시1.jpg"));
		} else if (name.equals("카테고리3")) {
			ImageIcon icon = new ImageIcon(Main.class.getResource("/image/예시1.jpg"));
		} else if (name.equals("카테고리4")) {
			ImageIcon icon = new ImageIcon(Main.class.getResource("/image/예시1.jpg"));
		}
		return icon;
	}

	public FlowerProduct(String name) {
		JPanel pnl = new JPanel();
		ImageIcon icon = NewIcon(name);
		JLabel lbl = j.라벨만들기(name, f.font2, 10, 20, 300, 80, pnl);

		int x = 10;
		int y = 200;
		int w = 30;
		int h = 30;
		체크박스반복(x, y, w, h, 6, pnl);
		x = 50;
		y = 100;
		w = 220;
		h = 200;
		라벨반복(icon, x, y, w, h, 6, pnl);

		pnl.setLayout(null);
		pnl.setSize(new Dimension(1000, 600));
		setLayout(null);
		add(pnl);
		setSize(new Dimension(1000, 600));
	}

	public void 체크박스반복(int x, int y, int w, int h, int a, JPanel pnl) {
		int count = 0;
		for (int i = 0; i < a; i++) {
			count++;
			if (i % 2 == 1) {
				JCheckBox che = j.체크박스만들기(x, (y * 2), w, h, pnl);
			} else {
				JCheckBox che = j.체크박스만들기(x, y, w, h, pnl);
			}
			if (count == 2) {
				count = 0;
				x += 300;
			}
		}
	}

	public void 라벨반복(ImageIcon icon, int x, int y, int w, int h, int a, JPanel pnl) {
		int count = 0;
		for (int i = 0; i < a; i++) {
			count++;
			if (i % 2 == 1) {
				JLabel lbl = j.라벨만들기(null, null, x, (y * 3) + 20, w, h, pnl);
				lbl.setIcon(icon);
			} else {
				JLabel lbl = j.라벨만들기(null, null, x, y, w, h, pnl);
				lbl.setIcon(icon);
			}
			if (count == 2) {
				count = 0;
				x += 300;
			}

		}
	}

	public static void main(String[] args) {
		new FlowerProduct("카테고리명").setVisible(true);
	}

}
