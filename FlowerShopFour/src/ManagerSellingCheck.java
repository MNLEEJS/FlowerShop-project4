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
	// 유저전체의 번호를 조회 > 그 번호로 주문번호 전체 조회 > 주문번호로 총 가격 조회해서 매출 출력
	MemberInfo MIF = new MemberInfo();
	// 모든 유저의 조회
	
	// 회원 주문 목록 테이블에서 회원 번호로 조회해서 주문번호 전체 출력받기
	UserOrderInfo UOI = new UserOrderInfo();
	// 리스트로 출력받을 리스트 선언
	List<UserOrder> userOrderList = new ArrayList<UserOrder>();
	// 주문 정보를 출력받기 위해 선언
	OrderInfoDAO OIDAO = new OrderInfoDAO(); 
	// 주문 정보를 출력받아 저장해놓을 리스트 선언
	List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
	// 주문상품번호로 주문상세내역 테이블 전체 받기위한 클래스 선언
	OrderDetailDAO ODDAO = new OrderDetailDAO();
	// 주문 상세 내역의 리스트
	List<OrderDetail> orderDetail = new ArrayList<OrderDetail>();
	
	public ManagerSellingCheck() {
		setModal(true);
		// 회원번호로  주문한적이 있는 주문번호 다 출력받기
		List<Membership> memberList = MIF.selectAll();
		for (int i = 0; i < memberList.size(); i++) {
			List<UserOrder> List = UOI.findByPk(0, memberList.get(i).getNo(), "user_no");
			userOrderList.addAll(List);
		}
		// 주문번호로 조회해서 주문 정보 다  출력받아  저장하기
		 for (int i = 0; i < userOrderList.size(); i++) {
			List<OrderInfo> List = OIDAO.selectOrderNo(userOrderList.get(i).getNo());
			orderInfoList.addAll(List);
		}
		 // 주문상세내역테이블 리스트에 전체 넣기
		for (int i = 0; i < orderInfoList.size(); i++) {
			List<OrderDetail> List = ODDAO.selectOrderDetailNo(orderInfoList.get(i).getFlowerOrderNo());
			orderDetail.addAll(List);
		}  
		
		// 위에꺼 이용해서 for문 돌려서 gui 완성하기
		
		
		
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


		for (int i = 0; i < lblCount; i++) {
			String lblName = "1000";
			String lblName2 = "졸업식 꽃다발 안개꽃";
			String lblName3 = "수량 : " + "1개";
			String lblName4 = "가격 : " + "100,000 원";
			String lblName5 = "판매 완료";
			
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