import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
	List<JLabel> lblList = new ArrayList<>(); // 레이블 넣을 리스트

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

		JScrollPane scrollPnl = new JScrollPane(); // 스크롤 바가 설치될 영역
		scrollPnl.setLayout(new ScrollPaneLayout());
		pnl.add(scrollPnl);
		scrollPnl.setBounds(0, 70, 985, 600);

		// 체크박스와 이미지 레이블들 포함하는 패널
		// 실제로 스크롤 바가 움직일 공간을 위해 패널 생성
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(0, 3, 30, 30));

		for (int i = 0; i < 8; i++) {

			JPanel pnlIncludeFlowerInfo = new JPanel();
			pnlIncludeFlowerInfo.setLayout(new BorderLayout());
			pnlIncludeFlowerInfo.setPreferredSize(new Dimension(300, 240));
			pnlIncludeFlowerInfo.setBorder(new LineBorder(new Color(15, 57, 90))); // 패널 위치 확인용 테두리, 추후에 삭제 예정
			contentPanel.add(pnlIncludeFlowerInfo);

			JPanel pnlIncludeCheLbl = new JPanel();
			pnlIncludeFlowerInfo.add(pnlIncludeCheLbl, BorderLayout.CENTER);
			
			JCheckBox che = new JCheckBox();
			pnlIncludeCheLbl.add(che);

			JLabel lblImage = new JLabel();
			lblImage.setPreferredSize(new Dimension(200, 200));
			lblImage.setBorder(new LineBorder(new Color(15, 210, 90))); // 레이블 위치 확인용 테두리, 이미지 삽입 후에 삭제 예정
			pnlIncludeCheLbl.add(lblImage);
			lblList.add(lblImage); // 리스트에 레이블 추가
			
			JLabel lblFlowerName = new JLabel("꽃다발 이름");
			pnlIncludeFlowerInfo.add(lblFlowerName, BorderLayout.SOUTH);
			
		}

		int numRows = (int) Math.ceil(13 / 3.0);
		int itemHeight = 220;
		int itemGap = 30;
		int contentHeight = numRows * itemHeight + (numRows - 1) * itemGap;

		contentPanel.setPreferredSize(new Dimension(985, contentHeight));
		scrollPnl.setViewportView(contentPanel);

		contentPanel.revalidate();
		contentPanel.repaint();

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

//		JLabel lbl = j.라벨만들기(name, f.font2, 10, 20, 300, 80, pnlRepeat);

//		pnl.setLayout(null);
		pnl.setLayout(new BorderLayout());
		pnl.setSize(new Dimension(1000, 600));
//		setLayout(null);
		setLayout(new BorderLayout());
		add(pnl);
		setSize(new Dimension(1000, 600));

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
}