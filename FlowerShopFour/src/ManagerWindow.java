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

import javax.swing.SwingConstants;

// 작업자 : 이나겸
// 관리자 화면의 메인 페이지 GUI 구현

public class ManagerWindow extends JDialog{
	OwnerPageFrame OPF = new OwnerPageFrame();
	AddColumn AC = new AddColumn();
	ManagerSellingCheck MSC = new ManagerSellingCheck() ;
	makingJ j = new makingJ();
	FontL font = new FontL();
	
	public ManagerWindow() {
		setModal(true);
		setSize(new Dimension(500, 500));
		setLayout(null);

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
		
		JLabel lblTotalSales = j.라벨만들기("총 매출 : " + " 원", font.font4, 17, 364, 215, 25, pnlIncludeLabel);

		JLabel lblTotalOrderCount = j.라벨만들기("총 주문 건수 : " + " 건", font.font4, 17, 404, 215, 25, pnlIncludeLabel);

		// 일정한 간격을 두고 JLable 생성하기
		int lblX = 210; // x 좌표 초기값 (맨 위에 있는 레이블)
		int lblY = 85; // y 좌표 초기값 (맨 위에 있는 레이블)
		int lblWidth = 250; // 가로 초기값 (맨 위에 있는 레이블)
		int lblHeight = 30; // 세로 초기값 (맨 위에 있는 레이블)
		int lblCount = 6; // for문이 돌아갈 횟수 (생성할 레이블 개수)

		String lblName = "현재 카테고리 개수 : " + " 개"; // 레이블 이름 초기값 (맨 위에 있는 레이블)

		for (int i = 0; i < lblCount; i++) {
			if (i == 1) {
				lblName = "수량 부족 카테고리 : " + "결혼식";

			} else if (i == 2) {
				lblName = "현재 주문 상품 개수  : " + " 개";

			} else if (i == 3) {
				lblName = "현재 주문 상품 총 가격 : " + " 원";

			} else if (i == 4) {
				lblName = "과거 주문 상품 개수  : " + " 개";

			} else if (i == 5) {
				lblName = "과거 주문 상품 총 가격 : " + " 원";
			}
			JLabel lbl = j.라벨만들기(lblName, font.font5, lblX, lblY, lblWidth, lblHeight, pnlBase);
			lblY += 45;
		}

		// JButton 생성 메소드 호출
		JButton btnOut = j.버튼만들기("나가기", font.font4, 310, 390, 120, 40, pnlBase);
		btnOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				M.setVisible(true);
				setVisible(false);
			}
		});

		// 일정한 간격을 두고 JButton 생성하기
		int btnX = 17; // x 좌표 초기값 (맨 위에 있는 버튼)
		int btnY = 85; // y 좌표 초기값 (맨 위에 있는 버튼)
		int btnWidth = 180; // 가로 초기값 (맨 위에 있는 버튼)
		int btnHeight = 40; // 세로 초기값 (맨 위에 있는 버튼)
		int btnCount = 4; // for문이 돌아갈 횟수 (생성할 버튼 개수)

		String btnName ; // 버튼 이름 초기값 (맨 위에 있는 버튼)

		for (int i = 0; i < btnCount; i++) {
			
			if (i == 0) {
				btnName= "카테고리 추가";
				JButton btn = j.버튼만들기(btnName, font.font4, btnX, btnY, btnWidth, btnHeight, pnlBase);
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						AC.setVisible(true);
					}
				});
			} else if (i == 1) {
				btnName = "카테고리 수정";
				JButton btn = j.버튼만들기(btnName, font.font4, btnX, btnY, btnWidth, btnHeight, pnlBase);
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						OPF.setVisible(true);
					}
				});
			} else if (i == 2) {
				btnName = "현재 주문 상품";
				JButton btn = j.버튼만들기(btnName, font.font4, btnX, btnY, btnWidth, btnHeight, pnlBase);
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						MSC.setVisible(true);						
					}
				});
			} else if (i == 3) {
				btnName = "과거 주문 상품";
				JButton btn = j.버튼만들기(btnName, font.font4, btnX, btnY, btnWidth, btnHeight, pnlBase);
			}

			btnY += 70;
		}
	}
}