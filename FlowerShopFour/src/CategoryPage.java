import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.omg.PortableServer.IdAssignmentPolicyOperations;

public class CategoryPage extends JFrame {
	makingJ j = new makingJ();
	FontL f = new FontL();
	FlowerDAO flowerDAO = new FlowerDAO();
	ImageFileInsert imageFileInsert = new ImageFileInsert();
	ImageDAO imageDAO = new ImageDAO();
	OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

	// 이미지 버튼 리스트
	List<JButton> listBtn = new ArrayList<JButton>();
	// 체크박스 리스트
	List<JCheckBox> listChe = new ArrayList<JCheckBox>();
	// 레이블 리스트
	List<JLabel> listLbl = new ArrayList<JLabel>();
	// 콤보박스 리스트
	List<JComboBox<Integer>> listComboBox = new ArrayList<JComboBox<Integer>>();
	// 패널 리스트
	List<JPanel> listPnl = new ArrayList<JPanel>();
	// 클래스 리스트
	List<ListClass> listClass = new ArrayList<ListClass>();
	Map<List<JPanel>, List<ListClass>> map;
	FlowerDAO flowerdao = new FlowerDAO();
	// flower 테이블 전체 조회해서 담는 리스트
	List<Flower> flowerList = flowerdao.selectAllWithList();
	// 이미지 버튼에 이미지 넣을 때 활용할 리스트
	List<Flower> flowerList1 = new ArrayList<>();
	// 이전 버튼 리스트
	// 패널 1 2 3까지 있을 때
	// if (패널의 사이즈가 2보다 클때) 패널 1에 이전버튼 remove
	List<JButton> listreturn = new ArrayList<JButton>();
	// 다음 버튼 리스트
	// 패널 3에 다음버튼 remove
	List<JButton> listNext = new ArrayList<JButton>();
	int o = 0;

	public CategoryPage() {

		int count = 0;
		int pnlCount = flowerList.size() / 6; // 패널의 갯수
		int addPnl = flowerList.size() % 6; // 추가 패널 생성할지 말지

		JPanel pnl = new JPanel(); // 카테고리 장바구니추가 메인으로가기 담는 패널

		JLabel lblCategory = j.라벨만들기("카테고리", f.font3, 20, 20, 150, 50, pnl);

		JButton btnInCart = j.버튼만들기("장바구니 추가", f.font4, 400, 20, 180, 50, pnl);
		JButton btnGoMain = j.버튼만들기("메인으로 가기", f.font4, 600, 20, 180, 50, pnl);

		int productCount = 7;
		// 패널의크기만큼 도는 for문
		for (int k = 1; k < pnlCount + 1; k++) {
			count++;

			// 컴포넌트들 각 6개씩 만들기
			JPanel pnl1 = new JPanel();
			listPnl.add(pnl1);

			// 체크박스 좌표 초기값
			int x = 20;
			int y = 65;

			// 이미지 버튼 좌표 초기값
			int x1 = 50;
			int y1 = 0;

			// 컬럼명(상품명) 레이블 좌표 초기값
			int x2 = 30;
			int y2 = 100;

			// 이전 다음 버튼 좌표 초기값
			int x3 = 50;
			int y3 = 500;

			// 콤보 박스 좌표 초기값
			int x4 = 180;
			int y4 = 160;

			// 버튼 사이즈 (고정)
			int h = 200;
			int w = 150;

			// 6개의 버튼, 체크박스, 라벨, 콤보박스 만드는 for문
			for (int i = 1; i < productCount; i++) {

				// 콤보박스 만들기
				Integer[] counting = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
				JComboBox<Integer> comboBox = j.makeComboBox(counting, x4, y4, 70, 40, pnl1);
				y4 += 250;

				if (i % 2 == 0) {
					y4 = 160;
					x4 += 250;
				}
				listComboBox.add(comboBox);
				comboBox.setEnabled(false); // 처음엔 콤보박스 비활성화 상태

				// 체크박스 만들기
				JCheckBox checkBox = j.체크박스만들기(x, y, 20, 20, pnl1);
				y += 250;

				if (i % 2 == 0) {
					y = 65;
					x += 250;
				}
				listChe.add(checkBox);

				checkBox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						// 체크 박스 선택했을 때 해당 품목 콤보 박스가 활성화됨
						if (e.getStateChange() == ItemEvent.SELECTED) {
							comboBox.setEnabled(true);
							// 체크 박스 해제했을 때 해당 품목 콤보 박스가 비활성화됨
						} else {
							comboBox.setEnabled(false);
						}
					}
				});

				for (int a = 0; a < flowerList.size(); a++) {
					String code = imageDAO.findByNo(flowerList.get(a).getImage_no());
					ImageIcon icon = imageFileInsert.ImageiconCreate(code);

					// 이미지 버튼 만들기
					JButton btnImage = j.버튼만들기("이미지", null, x1, y1, h, w, pnl1);
					// 이미지 버튼에 이미지 넣기
					btnImage.setIcon(icon);
				}
				y1 += 250;

				if (i % 2 == 0) {
					y1 = 0;
					x1 += 250;
				}

				// 라벨만들기
				String name = flowerList.get(0).getName();
				JLabel lblProduct = j.라벨만들기(name, f.font3, x2, y2, 200, 150, pnl1);
				y2 += 250;

				if (i % 2 == 0) {
					y2 = 100;
					x2 += 250;
				}
				// 메인에서 카테고리명이 적힌 버튼을 눌렀을 때 이 창이 뜸
				// 버튼에 적힌 카테고리명에 따라 lblProduct의 텍스트도 바뀜
//				lblProduct.setText(text); 

				flowerList.remove(0); // flowerList에서 remove 시키면서 다음걸 당겨옴
			}

			// 메인으로 가기 버튼 눌렀을 때
			btnGoMain.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose(); // 다이얼로그 창 닫히고 메인 창만 남음
				}
			});

			// 이전 버튼
			JButton btnReturn = j.버튼만들기("이전", f.font4, 40, 500, 80, 50, pnl1);
			listreturn.add(btnReturn);

			// 다음 버튼
			JButton btnNext = j.버튼만들기("다음", f.font4, 120, 500, 80, 50, pnl1);
			listNext.add(btnNext);

			add(pnl1);
			pnl1.setBounds(0, 100, 800, 650);
			pnl1.setLayout(null);

			// 리스트값만큼 돌리고 나머지만 돌리기 위한 조건
			if (count == pnlCount) {
				// 플라워 사이즈의 몫만큼 돌렸다면?
				count = -123754;
				if (addPnl > 0) {
					// 한번만 더 돌아라
					pnlCount = pnlCount + 1;
					// productCount의 사이즈를 바꾸고
					productCount = addPnl + 1;
				}
			}
			o++;
		}
		flowerList = flowerdao.selectAllWithList();
		
		// 장바구니 버튼 눌렀을때
		// order_detail 테이블에 insert
		btnInCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < listComboBox.size(); i++) {

					if (listComboBox.get(i).getSelectedIndex() != 0) {
						flowerList.get(i).getNo();

						orderDetailDAO.insert(flowerList.get(i).getNo(), listComboBox.get(i).getSelectedIndex());
						// i는 체크박스가 풀려있는거
					} else {
						// 체크박스가 false 인것
					}
				}
			}
		});
		o = 0;

		for (int i = 0; i < listreturn.size(); i++) {
			final int o = i;

			// 이전 버튼 눌렀을 때
			listreturn.get(o).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						if (o == 0) {
							listPnl.get(o).setVisible(true);
						}
						listPnl.get(o).setVisible(true);
						listPnl.get(o - 1).setVisible(true);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "이전 패널 없음");
					}
				}
			});

			// 다음 버튼 눌렀을 때
			listNext.get(o).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						if (o == listPnl.size()) {
							listPnl.get(o).setVisible(true);
						} else {
							listPnl.get(o).setVisible(false);
							listPnl.get(o + 1).setVisible(true);
						}

					} catch (Exception e1) {
						listPnl.get(o).setVisible(true);
						JOptionPane.showMessageDialog(null, "다음 패널 없음");
					}
				}
			});
		}

		// 패널을 담은 리스트의 사이즈가 2보다 크면
		// 첫번째 패널의 이전버튼 삭제
		// 마지막 패널의 다음버튼 삭제
		for (int j = listPnl.size() - 1; j > 0; j--) {
			listPnl.get(j).setVisible(false);

			if (listPnl.size() > 2) {
				listreturn.get(0);
				listNext.remove(listNext.size() - 1).setVisible(true);
			}
		}

		pnl.setLayout(null);

		add(pnl);
		setSize(new Dimension(800, 750));
	}

	public static void main(String[] args) {
		new CategoryPage().setVisible(true);
	}
}