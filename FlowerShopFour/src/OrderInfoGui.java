import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

import javafx.scene.control.ComboBox;

//작성자 : 이아현

// 구매자를 위한 주문 구성의 선택과 취소창

public class OrderInfoGui extends JFrame {
	 String customerId;
	makingJ j = new makingJ();
	FontL f = new FontL();
	JCheckBox c = new JCheckBox();
	JTextField txt = new JTextField();
	
	OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
	OrderInfoDAO orderInfoDAO = new OrderInfoDAO();
	FlowerDAO dao = new FlowerDAO();
	ImageDAO Idao = new ImageDAO();
	
	List<Flower> flowerDBlist = new ArrayList<Flower>(); // Flower DB에 있는 정보를 담고 있는 List
	List<OrderDetail> orderDetailList = orderDetailDAO.selectAll(); // OrderDetail DB에 있는 정보를 모두 담고 있는 List
	List<JCheckBox> checkboxList = new ArrayList<>(); // 사용자의 체크박스를 담고 있는 List
	List<JComboBox<String>> comboBoxCountList = new ArrayList<>(); // 사용자가 선택한 수량을 담고있는 콤보박스 List
	List<JButton> imgButtonList = new ArrayList<>(); // 사용자의 버튼을 담고 있는 List
	List<JLabel> productNameLblList = new ArrayList<>(); // 상품명을 담고 있는 레이블 List
	List<JLabel> productPriceLblList = new ArrayList<>(); // 상품가격을 담고 있는 레이블 List
	ImageFileInsert IFI = new ImageFileInsert();
	
	// 주문정보를 담을 패널 구성
	JPanel pnl = new JPanel();
	
	// 주문 확인창 레이블
	JLabel lbl1 = j.라벨만들기("주문확인창", f.font5, 10, 50, 110, 50, pnl);
	JLabel lbl2 = j.라벨만들기("주문 내역", f.font5, 10, 110, 260, 50, pnl);
	
	// 주문 확인창 버튼
	JButton btn1 = j.버튼만들기("메인화면", f.font5, 180, 50, 120, 60, pnl);
	JButton btn2 = j.버튼만들기("전체선택", f.font5, 320, 50, 120, 60, pnl);
	JButton btn3 = j.버튼만들기("전체취소", f.font5, 460, 50, 120, 60, pnl);
	JButton btn4 = j.버튼만들기("결제", f.font5, 600, 50, 120, 60, pnl);
	JButton btn5 = j.버튼만들기("변경", f.font5, 620, 150, 150, 100, pnl);
	JButton btn6 = j.버튼만들기("삭제", f.font5, 620, 300, 150, 100, pnl);

	// 회원정보를 파라미터 값으로 받아옴
	public OrderInfoGui(String customerId) {
		this.customerId = customerId;
		List<OrderDetail> orderNoList = orderDetailDAO.selectOrderDetailbyMemberNo(customerId);
		
		JLabel lblMemberID = j.라벨만들기(customerId + " 회원님", f.font5, 10, 80, 200, 50, pnl);
		int y = 180;

		// db안에있는 플라워 전체 들고오기
		flowerDBlist.addAll((dao.selectAllWithList()));


		// JPanel의 레이아웃, 크기 설정
		pnl.setLayout(null);
		pnl.setPreferredSize(new Dimension(700, 900)); // 패널 크기 설정을 setPreferredSize로 변경
		setLayout(null);
		setSize(new Dimension(900, 860));

		// -------------------------------콤보박스 ---------------------------

		// 콤보 박스에 담을 수량 생성을 위한 배열
		String[] count = { "1개", "2개", "3개", "4개", "5개", "6개", "7개", "8개" };

		// 개수 값 list.count로 받아올 수 있음
		// 최대 개수를 미리 정하고 크기만큼 string count에 넣어줘야함

		// ---------------- 콤보박스 개수 행 추가 for문 --------------------------------------

		for (int i = 0; i < orderNoList.size(); i++) {
			JComboBox<String> counting = new JComboBox<>(count);
			comboBoxCountList.add(counting);
		}

//--------------- 전체 틀(버튼, 레이블, 콤보 박스)을 반복 생성 for문 -----------------------------------

		for (int i = 0; i < orderNoList.size(); i++) {

			final int index = i;

			// 이미지 찾아오기
//			String code = Idao.findByNo(list.get(i).getImage_no()); // 주석해제 필수!
//			ImageIcon icon = IFI.ImageiconCreate(code);// 주석해제 필수!

			
			// 체크박스, 이미지 버튼 생성
			checkboxList.add(j.체크박스만들기(10, y + 20, 50, 50, pnl));

			imgButtonList.add(j.버튼만들기("이미지", f.font5, 60, y, 100, 100, pnl));
//			imagebtn.setIcon(icon);// 주석해제 필수!

			// 상품 상세정보가 나오는 이미지 버튼
			imgButtonList.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ProductExplain PE = new ProductExplain(index);

					PE.setVisible(true);
				}
			});
			
			// 상품의 번호를 가져옴
			int orderNo = orderNoList.get(i).getNo();
			
			
			// update(userSelectCount, orderDetailList.get(i).getNo());
			
			// 상품의 상세정보를 담고있는 db정보
			String product = flowerDBlist.get(i).getName();
			int price = flowerDBlist.get(i).getPrice();
			productNameLblList.add(j.라벨만들기("상품명 : " + product, f.font5, 170, y, 200, 50, pnl));

			// 추후 입력 받을 수량을 인덱스 안에 넣어줘야함
			comboBoxCountList.get(index).setSelectedIndex(orderNoList.get(i).getCount() - 1);

			// 상품의 상세정보에 대한 콤보 박스에 들어갈 개수 리스트
			String detailProduct = (String) comboBoxCountList.get(index).getSelectedItem();

			// 0번째부터 1번전까지 substring으로 나눠서 1개의 '1'출력
			int selectedCounting = Integer.parseInt(detailProduct.substring(0, 1));
			productPriceLblList.add(j.라벨만들기("금액 합계 : " + price * selectedCounting, f.font5, 400, y, 200, 50, pnl));

			// 수량 선택을 위한 콤보 박스 생성
			comboBoxCountList.get(index).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String selectedcounting = (String) comboBoxCountList.get(index).getSelectedItem();

					// 수량 선택 시 해당 수량 반영해서 금액 산정 출력
					JOptionPane.showMessageDialog(pnl, "선택 수량" + selectedcounting);

					// 수량을 담은 리스트 인트로 변환 후 개수로 활용 변수 : selectedCounting
					// 선택한 수량만큼 금액 계산
					int price = flowerDBlist.get(index).getPrice();
					String comboboxTxt = (String) comboBoxCountList.get(index).getSelectedItem();
					int selectedCounting = Integer.parseInt(comboboxTxt.substring(0, 1));
					productPriceLblList.get(index).setText("금액 합계 : " + price * selectedCounting);
					
				}
			});
			
			// 콤보박스 수량에 이미 입력되어 있는 배열을 인덱스 값을 통해 출력함
			for (int j = 0; j < comboBoxCountList.size(); j++) {
				int a = comboBoxCountList.get(j).getSelectedIndex();
			}
			
			// 패널에 콤보 박스 사이즈 지정 및 추가
			pnl.add(comboBoxCountList.get(i));
			comboBoxCountList.get(i).setBounds(170, y + 50, 100, 50);
			y += 120;
		}
		
		// ------------------------------------- 버튼 액션 추가-------------------------------


		// 카테고리 메인으로 이동 버튼
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main m = new Main();
				m.setVisible(true);
				m.Maingo2();
				dispose();
			}
		});

		// 전체 선택 버튼
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 9; i++) {

					checkboxList.get(i).setSelected(true);
				}

			}
		});

		// 전체 취소 버튼
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 9; i++) {

					checkboxList.get(i).setSelected(false);
				}
			}
		});
		
		// 결제 버튼
				btn4.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int chkUpdate = 0;
						for (int i = 0; i < checkboxList.size(); i++) {

							// 체크박스가 선택되어있을 때
							if (checkboxList.get(i).isSelected()) {
								// 콤보박스에서 선택된 개수를 1,2 이런 숫자형식으로 바꿔줌
								String userSelectCountString = (String) comboBoxCountList.get(i).getSelectedItem();
								userSelectCountString = userSelectCountString.substring(0, 1);
								int userSelectCount = Integer.parseInt(userSelectCountString);

								// 바꿔준 콤보박스의 수량대로 DB에 업데이트
								int updateCount = orderInfoDAO.updateSellingCheck(orderNoList.get(i).getNo());
								// 삭제된 부분 패널에서 지워주기
								pnl.remove(imgButtonList.get(i));
								pnl.remove(comboBoxCountList.get(i));
								pnl.remove(checkboxList.get(i));
								pnl.remove(productNameLblList.get(i));
								pnl.remove(productPriceLblList.get(i));

								// 변경 적용
								pnl.revalidate();
								pnl.repaint();

								int deleteCount = orderDetailDAO.delete(orderNoList.get(i).getNo());
								chkUpdate++;
							}

						}
						if (chkUpdate > 0)
							JOptionPane.showMessageDialog(OrderInfoGui.this, "결제 완료!");
						else
							JOptionPane.showMessageDialog(OrderInfoGui.this, "결제할 물품을 선택해주세요");
					}
				});
		

		// 변경 버튼
		btn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int chkUpdate = 0;
				for (int i = 0; i < checkboxList.size(); i++) {

					// 체크박스가 선택되어있을 때
					if (checkboxList.get(i).isSelected()) {
						// 콤보박스에서 선택된 개수를 1,2 이런 숫자형식으로 바꿔줌
						String userSelectCountString = (String) comboBoxCountList.get(i).getSelectedItem();
						userSelectCountString = userSelectCountString.substring(0, 1);
						int userSelectCount = Integer.parseInt(userSelectCountString);

						// 바꿔준 콤보박스의 수량대로 DB에 업데이트
						int updateCount = orderDetailDAO.update(userSelectCount, orderNoList.get(i).getNo());
						chkUpdate++;
					}

				}
				if (chkUpdate > 0)
					JOptionPane.showMessageDialog(OrderInfoGui.this, "변경 완료!");
				else
					JOptionPane.showMessageDialog(OrderInfoGui.this, "변경할 물품을 선택해주세요");
			}
		});

		// 삭제 버튼
		btn6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int chkDelete = 0;

				for (int i = 0; i < checkboxList.size(); i++) {

					// 체크박스가 선택되어있을 때
					if (checkboxList.get(i).isSelected()) {
						// 바꿔준 콤보박스의 수량대로 DB에 업데이트

						// 삭제된 부분 패널에서 지워주기
						pnl.remove(imgButtonList.get(i));
						pnl.remove(comboBoxCountList.get(i));
						pnl.remove(checkboxList.get(i));
						pnl.remove(productNameLblList.get(i));
						pnl.remove(productPriceLblList.get(i));

						// 변경 적용
						pnl.revalidate();
						pnl.repaint();

						int deleteCount = orderDetailDAO.delete(orderNoList.get(i).getNo());
						chkDelete++;
					}

				}
				if (chkDelete > 0)
					JOptionPane.showMessageDialog(OrderInfoGui.this, "삭제 완료!");
				else
					JOptionPane.showMessageDialog(OrderInfoGui.this, "삭제할 물품을 선택해주세요");

			}
		});

		// 스크롤 패널 추가
		JScrollPane scrollPnl = new JScrollPane(pnl);
		add(scrollPnl);
		scrollPnl.setLayout(new ScrollPaneLayout());
		scrollPnl.setBounds(0, 70, 800, 700);
		scrollPnl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPnl.setViewportView(pnl);
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

	}

	public static void main(String[] args) {

		new OrderInfoGui("aaaa").setVisible(true);

	}
}
