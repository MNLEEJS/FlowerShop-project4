import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 작업자 : 이나겸
// 관리자 페이지 GUI 구현

public class ManagerWIndow extends JFrame {
	public ManagerWIndow() {
		super("관리자"); // 창 이름
		
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel(); 
		getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout());
		
		JLabel lblTitle = new JLabel("관리자 페이지"); // 관리자 페이지 레이블
		northPanel.add(lblTitle, BorderLayout.CENTER);
		lblTitle.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
		
		JPanel centerPanel = new JPanel(); 
		getContentPane().add(centerPanel, BorderLayout.CENTER);
//		centerPanel.setLayout(new BorderLayout());
		
		JPanel centerBtnPanel = new JPanel();
//		centerPanel.add(btnPanel, BorderLayout.WEST);
		centerPanel.add(centerBtnPanel);
		centerBtnPanel.setLayout(new BoxLayout(centerBtnPanel, BoxLayout.Y_AXIS)); // centerBtnPanel에 담기는 요소들은 수직 정렬 되도록
		
		JButton btnAddCategory = new JButton("카테고리 추가"); // 카테고리 추가 버튼
		centerBtnPanel.add(btnAddCategory);
		btnAddCategory.setHorizontalAlignment(JButton.CENTER); // 텍스트 가운데 정렬
		
		JButton btnFixCategory = new JButton("카테고리 수정"); // 카테고리 수정 버튼
		centerBtnPanel.add(btnFixCategory);
		btnFixCategory.setHorizontalAlignment(JButton.CENTER); // 텍스트 가운데 정렬
		
		JButton btnCurrentOrder = new JButton("현재 주문 상품"); // 현재 주문 상품 버튼
		centerBtnPanel.add(btnCurrentOrder);
		btnCurrentOrder.setHorizontalAlignment(JButton.CENTER); // 텍스트 가운데 정렬
		
		JButton btnPastOrder = new JButton("과거 주문 상품"); // 과거 주문 상품 버튼
	    centerBtnPanel.add(btnPastOrder);
	    btnPastOrder.setHorizontalAlignment(JButton.CENTER); // 텍스트 가운데 정렬
	    
		JPanel centerLblPanel = new JPanel();
//		centerPanel.add(lblPanel, BorderLayout.EAST);
		centerPanel.add(centerLblPanel);
		centerLblPanel.setLayout(new BoxLayout(centerLblPanel, BoxLayout.Y_AXIS)); // centerLblPanel에 담기는 요소들은 수직 정렬 되도록
		
		JLabel lblCurrentCategoryCount = new JLabel("현재 카테고리 개수 : "); // 현재 카테고리 개수 표시 레이블
		centerLblPanel.add(lblCurrentCategoryCount);
		lblCurrentCategoryCount.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
		
		JLabel lblShortageCountCategory = new JLabel("수량 부족 카테고리 : "); // 수량 부족 카테고리 표시 레이블
		centerLblPanel.add(lblShortageCountCategory);
		lblShortageCountCategory.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
		
		JLabel lblCurrentOrderCount = new JLabel("현재 주문 상품 개수  : "); // 현재 주문 상품 개수 표시 레이블
		centerLblPanel.add(lblCurrentOrderCount);
		lblCurrentOrderCount.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
		
		JLabel lblCurrentOrderTotalPrice = new JLabel("현재 주문 상품 총 가격 : "); // 현재 주문 상품 총 가격 표시 레이블
		centerLblPanel.add(lblCurrentOrderTotalPrice);
		lblCurrentOrderTotalPrice.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
		
		JLabel lblPastOrderCount = new JLabel("과거 주문 상품 개수  : "); // 과거 주문 상품 개수 표시 레이블
		centerLblPanel.add(lblPastOrderCount);
		lblPastOrderCount.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
		
	    JLabel lblPastOrderTotalPrice = new JLabel("과거 주문 상품 총 가격 : "); // 과거 주문 상품 총 가격  표시 레이블
	    centerLblPanel.add(lblPastOrderTotalPrice);
	    lblPastOrderTotalPrice.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
	    
		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);
//		southPanel.setLayout(new BorderLayout());
		
		JPanel southLblPanel = new JPanel();
		southPanel.add(southLblPanel);
		southLblPanel.setLayout(new BoxLayout(southLblPanel, BoxLayout.Y_AXIS)); // southLblPanel에 담기는 요소들은 수직 정렬 되도록
		
		JLabel lblTotalSales = new JLabel("총 매출 : "); // 총 매출 표시 레이블
		southLblPanel.add(lblTotalSales);
		lblTotalSales.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
		
		JLabel lblTotalOrderCount = new JLabel("총 주문 건수 : "); // 총 주문 건수 표시 레이블
		southLblPanel.add(lblTotalOrderCount);
		lblTotalOrderCount.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
		
		JButton btnOut = new JButton("나가기"); // 나가기 버튼
		southPanel.add(btnOut);
		btnOut.setHorizontalAlignment(JButton.CENTER); // 텍스트 가운데 정렬
		
	}
	
	public static void main(String[] args) {
		new ManagerWIndow().setVisible(true);
	}
}