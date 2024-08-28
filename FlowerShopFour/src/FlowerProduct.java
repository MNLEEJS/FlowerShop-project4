import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.LineBorder;

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
		setModal(true);
		JPanel pnl = new JPanel(); // 전체 패널

		JLabel lblCategory = j.라벨만들기("카테고리1", f.font4, 50, 15, 150, 40, pnl);

		JButton btnAddProduct = j.버튼만들기("장바구니 추가", f.font4, 580, 15, 180, 40, pnl);

		JButton btnGoMain = j.버튼만들기("메인으로 가기", f.font4, 780, 15, 180, 40, pnl);

		ImageIcon icon = NewIcon(name);

		JScrollPane scrollPnl = new JScrollPane(); // 스크롤 바가 설치될 영역(패널)
		scrollPnl.setLayout(new ScrollPaneLayout());
		pnl.add(scrollPnl);
		scrollPnl.setBounds(0, 70, 985, 580);

		JPanel pnlIncludeLbl = new JPanel(); // 체크박스와 이미지 레이블들 포함하는 패널
		pnlIncludeLbl.setLayout(null);
		pnlIncludeLbl.setBounds(0, 20, 985, 580);
		scrollPnl.setViewportView(pnlIncludeLbl); // 실제로 스크롤되는 컨텐츠 포함 (스크롤하는 영역을 제한)

		// 마우스 휠 리스너를 이용한 스크롤 속도 조정
		scrollPnl.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int scrollAmount = e.getScrollAmount();
				int scrollSpeed = 9; // 스크롤 속도 배수 조정 (9배)

				if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
					JScrollBar verticalBar = scrollPnl.getVerticalScrollBar(); // 세로 방향 스크롤 바
					int scroll = e.getUnitsToScroll() * verticalBar.getUnitIncrement() * scrollSpeed;

					verticalBar.setValue(verticalBar.getValue() + scroll);
				}
			}
		});

		JLabel lbl = j.라벨만들기(name, f.font2, 10, 20, 300, 80, pnlIncludeLbl);

		pnl.setLayout(null);
		pnl.setSize(new Dimension(1000, 600));
		setLayout(null);
		add(pnl);
		setSize(new Dimension(1000, 600));

		int x = 30;
		int y = 100;
		int w = 40;
		int h = 100;
		체크박스반복(x, y, w, h, 6, pnlIncludeLbl);

		x = 70;
		y = 50;
		w = 220;
		h = 200;
		라벨반복(icon, x, y, w, h, 6, pnlIncludeLbl);

		// 장바구니 추가 버튼을 눌렀을 때
		btnAddProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// 메인으로 가기 버튼 눌렀을 때
		// 다이얼로그 창 닫히고 메인 화면만 남아있도록
		btnGoMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); // 다이얼로그 창 닫힘
			}
		});
	}

	public void 체크박스반복(int x, int y, int w, int h, int a, JPanel pnlIncludeLbl) {
		int count = 0;

		for (int i = 0; i < a; i++) {
			count++;

			if (i % 2 == 1) {
				JCheckBox che = j.체크박스만들기(x, (y * 3), w, h, pnlIncludeLbl);

			} else {
				JCheckBox che = j.체크박스만들기(x, y, w, h, pnlIncludeLbl);
			}

			if (count == 2) {
				count = 0;
				x += 300;
			}
		}
	}

	public void 라벨반복(ImageIcon icon, int x, int y, int w, int h, int a, JPanel pnlIncludeLbl) {
		int count = 0;

		for (int i = 0; i < a; i++) {
			count++;

			if (i % 2 == 1) {
				JLabel lbl = j.라벨만들기(null, null, x, (y * 5) + 20, w, h, pnlIncludeLbl);
				lbl.setIcon(icon);
//				lblList.add(lbl);

			} else {
				JLabel lbl = j.라벨만들기(null, null, x, y, w, h, pnlIncludeLbl);
				lbl.setIcon(icon);
//				lblList.add(lbl);
			}

			if (count == 2) {
				count = 0;
				x += 300;
			}
		}
	}
}