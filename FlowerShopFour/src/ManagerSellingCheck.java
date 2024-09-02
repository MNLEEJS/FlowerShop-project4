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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// 작업자 : 이나겸
// 관리자 화면의 판매 확인 페이지 GUI 구현
// 
public class ManagerSellingCheck extends JDialog {
	makingJ j = new makingJ();
	FontL f = new FontL();
	// 유저전체의 번호를 조회 > 그 번호로 주문번호 전체 조회 > 주문번호로 총 가격 조회해서 매출 출력
	MemberInfo MIF = new MemberInfo();
	// 모든 유저의 조회

	OrderInfoDAO OIDAO = new OrderInfoDAO();

	// 주문번호 ,,, 꽃다발 이름 ,, 수량 ,, 가격 ,,,
	OrderDetailDAO ODDAO = new OrderDetailDAO();
	// 꽃다발의 이름, 가격을 찾기위해
	FlowerDAO FDAO = new FlowerDAO();

	List<JPanel> pnlList = new ArrayList<>();
	List<JCheckBox> cheList = new ArrayList<>();

	// 주문번호를 담을 리스트
	List<Integer> orderList = new ArrayList<Integer>();
	List<OrderInfo> orderInfoList = new ArrayList<>();

	public ManagerSellingCheck() {
		setModal(true);
			orderInfoList = OIDAO.selectSellingCheck();
			// 주문 상세 내역 테이블 조회해서 저장 하는 리스트 == 꽃다발 번호 ,, 꽃다발 주문 수량을 알수 있음
			List<OrderDetail> orderDetaiilList = new ArrayList<>();
			
			for (int i = 0; i < orderInfoList.size(); i++) {
				List<OrderDetail> list = ODDAO.selectOrderDetailNo(orderInfoList.get(i).getFlowerOrderNo());
				orderDetaiilList.addAll(list);
			}


			int a = orderInfoList.size() / 6;
			int b = orderInfoList.size() % 6;
			if (a != 0) {
				a = orderInfoList.size() % 6;
				b = 0;
			}
			
			패널돌리기(orderDetaiilList, a);
			if (b > 0) {
				패널돌리기(orderDetaiilList, b);
			}
			MainButton();
			if (pnlList.size() != 0) {
				for (int i = 0; i < pnlList.size(); i++) {
					if (i == 0) {
						pnlList.get(i).setVisible(true);
					} else {
						pnlList.get(i).setVisible(false);
					}
				}
			}

			if (pnlList.size() > 1) {
				JButton btnNEXT = j.버튼만들기("다음", f.font3, 50, 530, 100, 60, pnlList.get(0));
				btnNEXT.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pnlList.get(0).setVisible(false);
						try {
							pnlList.get(1).setVisible(true);
						} catch (Exception e10) {
							JOptionPane.showMessageDialog(null, "다음페이지는 없습니다.");
							pnlList.get(0).setVisible(true);
						}
					}
				});
				if (pnlList.size() > 2) {
					JButton btnNEXT1 = j.버튼만들기("다음", f.font3, 50, 530, 100, 60, pnlList.get(1));
					btnNEXT1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							pnlList.get(1).setVisible(false);
							try {
								pnlList.get(2).setVisible(true);
							} catch (Exception e3) {
								JOptionPane.showMessageDialog(null, "다음페이지는 없습니다.");
								pnlList.get(1).setVisible(true);
							}
						}
					});
					JButton btnBEFORE1 = j.버튼만들기("이전", f.font3, 180, 530, 100, 60, pnlList.get(1));
					btnBEFORE1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							pnlList.get(1).setVisible(false);
							pnlList.get(0).setVisible(true);
						}
					});
				}
				if (pnlList.size() > 3) {
					JButton btnNEXT2 = j.버튼만들기("다음", f.font3, 50, 530, 100, 60, pnlList.get(2));
					btnNEXT2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							pnlList.get(2).setVisible(false);
							try {
								pnlList.get(3).setVisible(true);
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "다음페이지는 없습니다.");
								pnlList.get(2).setVisible(true);
							}
						}
					});
					JButton btnBEFORE2 = j.버튼만들기("이전", f.font3, 180, 530, 100, 60, pnlList.get(2));
					btnBEFORE2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							pnlList.get(2).setVisible(false);
							pnlList.get(1).setVisible(true);
						}
					});
				}
			}

//			JPanel pnl = new JPanel();
//			pnl.setSize(new Dimension(100, 500));
//			pnl.setBounds(800, 0, 900, 100);
//			add(pnl);
//			JButton btn = j.버튼만들기("주문상품 없음", f.font2, 0, 0, 100, 50, pnl);
		
		setSize(new Dimension(900, 600));
		setLayout(null);
	}

	private void MainButton() {
		JPanel pnl = new JPanel();
		JButton btn = j.버튼만들기("판매 완료", f.font4, 10, 30, 150, 50, pnl);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i = 0; i < orderList.size(); i++) {

					}
					JOptionPane.showMessageDialog(null, "성공적으로 완료했습니다.");

				} catch (Exception e123) {
					JOptionPane.showMessageDialog(null, "체크된게 없습니다.");
				}
			}
		});
		pnl.setLayout(null);
		pnl.setSize(new Dimension(900, 100));
		add(pnl);
	}

	private void 패널돌리기(List<OrderDetail> orderDetaiilList, int a) {
		for (int i = 0; i < a; i++) {
			JPanel pnl = new JPanel();
			pnlList.add(pnl);
			int OrderNum = orderInfoList.get(i).getOrderNo();
			JLabel lblOrderNum = j.라벨만들기("" + OrderNum, f.font4, 50, 50, 100, 50, pnl);

			List<Flower> flowerList = new  ArrayList<Flower>();
			flowerList.addAll(FDAO.findBy("no", orderDetaiilList.get(i).getFlowerNo(), null, 0));
			String name = flowerList.get(0).getName();
			JLabel lblName = j.라벨만들기(name, f.font4, 150, 50, 100, 50, pnl);

			int count = orderDetaiilList.get(i).getCount();
			JLabel lblCount = j.라벨만들기("" + count, f.font4, 250, 50, 100, 50, pnl);

			int price = flowerList.get(0).getPrice();
			JLabel lblPrice = j.라벨만들기("" + (count + price), f.font4, 350, 50, 100, 50, pnl);
			JCheckBox che = j.체크박스만들기(500, 50, 30, 30, pnl);
			cheList.add(che);
			int z = i;
			che.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						orderList.add(orderInfoList.get(z).getOrderNo());
					} else {
						orderList.remove(z);
					}
				}
			});
			JLabel lblSelling = j.라벨만들기("판매 완료", f.font4, 600, 50, 100, 50, pnl);
			pnl.setSize(new Dimension(900, 500));
			pnl.setLayout(null);
			add(pnl);
		}
	}

}