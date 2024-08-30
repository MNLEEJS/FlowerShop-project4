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

	OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
	makingJ j = new makingJ();
	FontL f = new FontL();
	JCheckBox c = new JCheckBox();
	JTextField txt = new JTextField();
	List<Flower> list = new ArrayList<Flower>();
	FlowerDAO dao = new FlowerDAO();
	ImageDAO Idao = new ImageDAO();
	ImageFileInsert IFI = new ImageFileInsert();

	// 주문정보를 담을 패널 구성
	JPanel pnl = new JPanel();

	// 주문 확인창 라벨
	JLabel lbl1 = j.라벨만들기("주문확인창", f.font5, 10, 50, 110, 50, pnl);
	JLabel lbl2 = j.라벨만들기("주문 내역", f.font5, 10, 110, 260, 50, pnl);
	// 주문 확인창 버튼
	JButton btn1 = j.버튼만들기("메인화면", f.font5, 180, 50, 120, 60, pnl);
	JButton btn2 = j.버튼만들기("전체선택", f.font5, 320, 50, 120, 60, pnl);
	JButton btn3 = j.버튼만들기("전체취소", f.font5, 460, 50, 120, 60, pnl);
	JButton btn4 = j.버튼만들기("결제", f.font5, 600, 50, 120, 60, pnl);
	JButton btn5 = j.버튼만들기("변경", f.font5, 620, 150, 150, 100, pnl);

	public OrderInfoGui() {

		int y = 180;
		List<JCheckBox> checkboxList = new ArrayList<>();

		// db안에있는 플라워 전체 들고오기
		list.addAll((dao.selectAllWithList()));
		List<OrderDetail> orderDetailList = orderDetailDAO.selectAll();
		orderDetailList.get(0).getCount();

		// JPanel의 레이아웃, 크기 설정
		pnl.setLayout(null);
		pnl.setPreferredSize(new Dimension(700, 900)); // 패널 크기 설정을 setPreferredSize로 변경
		setLayout(null);
		setSize(new Dimension(900, 860));

		// 콤보 박스에 담을 수량 생성을 위한 배열
		System.out.println(list.get(0).getCount() - 1);
		String[] count = { "1개", "2개", "3개", "4개", "5개", "6개", "7개", "8개" };

		// 개수 값 list.count로 받아올 수 있음
		// 최대 개수를 미리 정하고 크기만큼 string count에 넣어줘야함
		List<JComboBox<String>> countingList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			JComboBox<String> counting = new JComboBox<>(count);
			countingList.add(counting);
		}
		
		// 콤보박스 리스트
		int bbc = 0;

		// 버튼을 생성하는 for문
		for (int i = 0; i < list.size(); i++) {

			final int index = i;

			// 이미지 찾아오기
			String code = Idao.findByNo(list.get(i).getImage_no());
			ImageIcon icon = IFI.ImageiconCreate(code);
			
			// 체크박스와 이미지 레이블들 포함하는 패널
			// 상품의 주문을 위한 선택&취소 버튼, 이미지 버튼 생성
			checkboxList.add(j.체크박스만들기(10, y + 20, 50, 50, pnl));

			JButton imagebtn = j.버튼만들기("이미지", f.font5, 60, y, 100, 100, pnl);
			imagebtn.setIcon(icon);

			// 상품 상세정보가 나오는 이미지 버튼
			imagebtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ProductExplain PE = new ProductExplain(index);

					PE.setVisible(true);
				}
			});

			// 상품의 상세정보를 담고있는 db정보
			String product = list.get(i).getName();
			int price = list.get(i).getPrice();
			JLabel lblproduct = j.라벨만들기("상품명 : " + product, f.font5, 170, y, 200, 50, pnl);

			// 상품의 상세정보에 대한 콤보 박스에 들어갈 개수 리스트
			String detailProduct = (String) countingList.get(index).getSelectedItem();
			// 추후 입력 받을 수량을 인덱스 안에 넣어줘야함
			countingList.get(index).setSelectedIndex(list.get(i).getCount() - 1);

			countingList.get(index).addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {

				}
			});

			// 0번째부터 1번전까지 substring 이니까 index 0부터 시작
			bbc = Integer.parseInt(detailProduct.substring(0, 1));
			JLabel lblPrice = j.라벨만들기("금액 합계 : " + price * bbc, f.font5, 400, y, 200, 50, pnl);

			// 수량 선택을 위한 콤보 박스 생성
			countingList.get(index).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String selectedcounting = (String) countingList.get(index).getSelectedItem();

					// 수량 선택 시 해당 수량 반영해서 금액 산정 출력
					JOptionPane.showMessageDialog(pnl, "선택 수량" + selectedcounting);

					// 수량을 담은 리스트 인트로 변환 후 개수로 활용 변수 : bbc
					// 선택한 수량만큼 금액 계산
					int price = list.get(index).getPrice();
					String abc = (String) countingList.get(index).getSelectedItem();
					int bbc = Integer.parseInt(abc.substring(0, 1));
					System.out.println(price);
					System.out.println(abc);
					System.out.println(bbc);
					lblPrice.setText("금액 합계 : " + price * bbc);

					int userSelectCount = bbc;
					int no = orderDetailList.get(0).getNo();
					orderDetailDAO.update(userSelectCount, no);

				}
			});

			// 패널에 콤보 박스 사이즈 지정 및 추가
			pnl.add(countingList.get(i));
			countingList.get(i).setBounds(170, y + 50, 100, 50);
			y += 120;

		}
////////@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		for (int j = 0; j < countingList.size(); j++) {
			int a = countingList.get(j).getSelectedIndex();
			System.out.println(count[a]);
		}
		// 콤보박스를 선택했을때의 수량
		int userSelectCount = bbc;
		int no = orderDetailList.get(0).getNo();
		orderDetailDAO.update(userSelectCount, no);

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
				for (int i = 0; i < 4; i++) {

					checkboxList.get(i).setSelected(true);
				}

			}
		});

		// 전체 취소 버튼
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 4; i++) {

					checkboxList.get(i).setSelected(false);
				}

			}
		});

		btn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int 이아현 = orderDetailDAO.update(userSelectCount, no);
				if (이아현 < 0) {
					JOptionPane.showMessageDialog(null, "아현  -> 아연");
				} else {
					JOptionPane.showMessageDialog(null, "생존");
				}

			}
		});

	}
	
	public static void main(String[] args) {

		new OrderInfoGui().setVisible(true);

	}
}
