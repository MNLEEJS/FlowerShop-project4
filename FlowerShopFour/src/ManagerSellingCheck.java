import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 작업자 : 이나겸
// 관리자 화면의 판매 확인 페이지 GUI 구현

public class ManagerSellingCheck extends JDialog {
	makingJ j = new makingJ();
	FontL font = new FontL();

	public ManagerSellingCheck() {

		setSize(new Dimension(900, 500));
		setLayout(null);

// <관리자 화면의 판매 확인 페이지>------------------------------------------------------------------------------------------

		JPanel pnlBase2 = new JPanel(); // 판매 확인 페이지의 요소들이 다 들어가는 토대 패널
		pnlBase2.setLayout(null);
		pnlBase2.setSize(new Dimension(900, 500));
		add(pnlBase2);

		// JLable 생성 메소드 호출
		JLabel lblcategory = j.라벨만들기("카테고리", font.font3, 380, 15, 200, 40, pnlBase2);

		// 일정한 간격을 두고 JLable 생성하기
		int lblX = 30;
		int lblY = 85;
		int lblWidth = 300;
		int lblHeight = 40;
		int lblCount = 5;

		String lblName = "1000";
		String lblName2 = "졸업식 꽃다발 안개꽃";
		String lblName3 = "수량 : " + "1개";
		String lblName4 = "가격 : " + "100,000 원";
		String lblName5 = "판매 완료";

		for (int i = 0; i < lblCount; i++) {
			// 회원 번호 레이블 (값을 가져와서 레이블의 텍스트 변경 예정)
			JLabel lblMemberNo = j.라벨만들기(lblName, font.font4, lblX, lblY, lblWidth - 120, lblHeight, pnlBase2);
			// 꽃다발 이름 레이블 (값을 가져와서 레이블의 텍스트 변경 예정)
			JLabel lblFlowerName = j.라벨만들기(lblName2, font.font4, lblX + 80, lblY, lblWidth, lblHeight, pnlBase2);
			// 수량 레이블 (값을 가져와서 레이블의 텍스트 변경 예정)
			JLabel lblFlowerCount = j.라벨만들기(lblName3, font.font4, lblX + 320, lblY, lblWidth - 100, lblHeight, pnlBase2);
			// 가격 레이블 (값을 가져와서 레이블의 텍스트 변경 예정)
			JLabel lblFlowerPrice = j.라벨만들기(lblName4, font.font4, lblX + 460, lblY, lblWidth, lblHeight, pnlBase2);
			// 체크 박스 (체크 박스 선택하면 해당 체크 박스 비활성화)
			JCheckBox checkBox = j.체크박스만들기(lblX + 690, lblY + 10, lblWidth - 280, lblHeight - 20, pnlBase2);
			// 판매 완료 레이블
			JLabel lblComplete = j.라벨만들기(lblName5, font.font4, lblX + 720, lblY, lblWidth - 120, lblHeight, pnlBase2);
			lblY += 60;
			
			// 체크 박스 선택했을때 해당 체크 박스 비활성화 (ItemListener)
			checkBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						checkBox.setEnabled(false);
					}
				}
			});
		}
		
		JButton btnPre = j.버튼만들기("이전", font.font4, 30, 395, 80, 30, pnlBase2); // 이전 버튼
		JButton btnNext = j.버튼만들기("다음", font.font4, 120, 395, 80, 30, pnlBase2); // 다음 버튼
		
		JLabel lblTotalCount = j.라벨만들기("총 수량 : 10개", font.font4, 350, 385, 200, 40, pnlBase2); // 총 수량 표시 레이블
		JLabel lblTotalPrice = j.라벨만들기("총 가격 : 100,000원", font.font4, 570, 385, 300, 40, pnlBase2); // 총 가격 표시 레이블
		
		// 이전 버튼 눌렀을때 (ActionListener)
		btnPre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// 다음 버튼 눌렀을때 (ActionListener)
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}


}