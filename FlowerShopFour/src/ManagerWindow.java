import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;

// 작업자 : 이나겸
// 관리자 화면의 메인 페이지 GUI 구현

public class ManagerWindow extends JDialog {
	OwnerPageFrame OPF = new OwnerPageFrame();
	AddColumn AC = new AddColumn();

	FlowerDAO flowerDAO = new FlowerDAO();
	List<String> listFlowerCategory = new ArrayList<String>();

	OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
	List<Integer> listOrderCount = new ArrayList<Integer>();

	makingJ j = new makingJ();
	FontL font = new FontL();

	public ManagerWindow() {
		ManagerSellingCheck MSC = new ManagerSellingCheck();
		setModal(true);
		setSize(new Dimension(500, 500));
		setLayout(null);
		listFlowerCategory = flowerDAO.selectCategory();
		listOrderCount = orderDetailDAO.selectCount();

// <관리자 화면의 메인 페이지>---------------------------------------------------------------------------------------------------

		JPanel pnlBase = new JPanel(); // 메인 페이지의 요소들이 다 들어가는 토대 패널
		pnlBase.setLayout(null);
		pnlBase.setSize(new Dimension(500, 500));
		add(pnlBase);

		JPanel pnlIncludeLabel = new JPanel(); // 총 매출 표시, 총 주문 건수 표시 레이블들 포함하는 패널
		pnlIncludeLabel.setLayout(new BoxLayout(pnlIncludeLabel, BoxLayout.Y_AXIS)); // 패널에 추가되는 요소들 수직 정렬
		pnlIncludeLabel.setBounds(40, 385, 220, 70); // x좌표, y좌표, 가로, 세로
		pnlBase.add(pnlIncludeLabel);

		// JLable 생성 메소드 호출
		JLabel lblTitle = j.라벨만들기("관리자 페이지", font.font3, 140, 15, 250, 40, pnlBase);

		// 일정한 간격을 두고 JLable 생성하기
		int lblX = 135; // x 좌표 초기값 (맨 위에 있는 레이블)
		int lblY = 200; // y 좌표 초기값 (맨 위에 있는 레이블)
		int lblWidth = 250; // 가로 초기값 (맨 위에 있는 레이블)
		int lblHeight = 30; // 세로 초기값 (맨 위에 있는 레이블)

		String lblName = "현재 카테고리 개수 : " + "개";
		JLabel lbl = j.라벨만들기(lblName, font.font5, lblX, lblY, lblWidth, lblHeight, pnlBase);
		lbl.setText("현재 카테고리 개수 : " + listFlowerCategory.size() + "개");

		String lblName1 = "현재 주문 상품 개수  : " + "개";
		JLabel lbl2 = j.라벨만들기(lblName1, font.font5, lblX, lblY + 70, lblWidth, lblHeight, pnlBase);

		int orderCount = 0;
		for (int i = 0; i < listOrderCount.size(); i++) {
			orderCount += listOrderCount.get(i);
		}
		lbl2.setText("현재 주문 상품 개수 : " + orderCount + "개");

		// JButton 생성 메소드 호출
		JButton btnOut = j.버튼만들기("나가기", font.font4, 250, 390, 200, 50, pnlBase);
		btnOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		// 일정한 간격을 두고 JButton 생성하기
		int btnX = 110; // x 좌표 초기값 (맨 위에 있는 버튼)
		int btnY = 125; // y 좌표 초기값 (맨 위에 있는 버튼)
		int btnWidth = 270; // 가로 초기값 (맨 위에 있는 버튼)
		int btnHeight = 40; // 세로 초기값 (맨 위에 있는 버튼)

		String btnName; // 버튼 이름 초기값

		btnName = "카테고리별 상품 추가";
		JButton btn = j.버튼만들기(btnName, font.font4, btnX, btnY, btnWidth, btnHeight, pnlBase);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AC.setVisible(true);
			}
		});
	}
}