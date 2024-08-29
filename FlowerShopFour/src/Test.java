import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Test extends JFrame {
	makingJ j = new makingJ();
	FontL f = new FontL();
	List<JButton> listbtn = new ArrayList<JButton>();
	List<JCheckBox> listche = new ArrayList<JCheckBox>();
	List<JLabel> listLbl = new ArrayList<JLabel>();
	List<JPanel> listPnl = new ArrayList<JPanel>();;
	List<ListClass> listClass = new ArrayList<ListClass>();
	Map<List<JPanel>, List<ListClass>> map;
	FlowerDAO flowedao = new FlowerDAO();
	List<Flower> flowerList = flowedao.selectAllWithList();
	// if( 패널의 사이즈가 2보다 클때 )
	// 이전버튼 2 3 리무브 시키기
	List<JButton> listreturn = new ArrayList<JButton>();
	// 다음버튼 1 2 리무브 시키기
	List<JButton> listNext = new ArrayList<JButton>();
	int o = 0;
	// 패널 1 2 3

	public Test() {
		int count = 0;
		int a = flowerList.size() / 6; // 패널의 갯수
		int b = flowerList.size() % 6; // 추가패널 생성할지 말지
		JPanel pnl = new JPanel(); // 카테고리 장바구니추가 메인으로가기 담는 패널

		JLabel lbl = j.라벨만들기("카테고리", f.font3, 20, 20, 150, 50, pnl);
		JButton btn = j.버튼만들기("장바구니 추가", f.font4, 400, 20, 180, 50, pnl);
		JButton btn2 = j.버튼만들기("메인으로 가기", f.font4, 600, 20, 180, 50, pnl);
		int z = 7;
		for (int k = 1; k < a + 1; k++) {
			count++;
//	6개의 문장 만들기
			JPanel pnl1 = new JPanel();
			listPnl.add(pnl1);
// 체크박스 좌표
			int x = 20;
			int y = 65;
// 버튼 좌표
			int x1 = 50;
			int y1 = 000;
// 컬럼명 좌표 (상품명)
			int x2 = 50;
			int y2 = 100;
// 이전 다음 버튼좌표
			int x3 = 50;
			int y3 = 500;

// 버튼사이즈
			int h = 200;
			int w = 150;

// 6개의 버튼, 체크박스, 라벨 만드는 for문

			for (int i = 1; i < z; i++) {
//		체크박스 만들기
				JCheckBox che = j.체크박스만들기(x, y, 20, 20, pnl1);
				y += 250;
				if (i % 2 == 0) {
					y = 65;
					x += 250;
				}
//		버튼만들기
				JButton btnImage = j.버튼만들기("으아아아", null, x1, y1, h, w, pnl1);
				btnImage.setBackground(Color.BLUE);
				y1 += 250;
				if (i % 2 == 0) {
					y1 = 000;
					x1 += 250;
				}
//		라벨만들기
				String name = flowerList.get(0).getName();
				JLabel lblProduct = j.라벨만들기(name, f.font3, x2, y2, 250, 150, pnl1);
				y2 += 250;
				if (i % 2 == 0) {
					y2 = 100;
					x2 += 250;
				}
//		이전,다음 버튼 만들기
				flowerList.remove(0);

			}
			// 패널의 크기 만큼 만들기
			JButton btnReturn = j.버튼만들기("이전", f.font4, 40, 500, 80, 50, pnl1);
			listreturn.add(btnReturn);
//			btnReturn.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					listPnl.get(o).setVisible(false);
//					listPnl.get(o - 1).setVisible(true);
//				}
//			});

			JButton btnNext = j.버튼만들기("다음", f.font4, 120, 500, 80, 50, pnl1);
			listNext.add(btnNext);
//			btnNext.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					listPnl.get(o).setVisible(false);
//					listPnl.get(o - 1).setVisible(true);
//				}
//			});

			add(pnl1);
			pnl1.setBounds(0, 100, 800, 650);
			pnl1.setLayout(null);

//		 리스트값만큼 돌리고 나머지만 돌리기 위한 조건
			if (count == a) {
				// 플라워 사이즈의 몫만큼 돌렸다면?
				count = -123754;
				if (b > 0) {
					// 한번만 더 돌아라
					a = a + 1;
					// z의 사이즈를 바꾸고
					z = b + 1;
				}
			}
			o++;
		}
		o = 0;
//		패널의 사이즈가 2보다 크면 
//		첫번쨰 패널의 이전버튼 삭제
//		마지막 패널의 다음버튼 삭제
		
		for (int j = listPnl.size() - 1; j > 0; j--) {
			listPnl.get(j).setVisible(false);
			if (listPnl.size() > 2) {
				listreturn.get(0);
				listNext.remove(listNext.size() - 1).setVisible(false);
			}
		}
		
		
		

//		/////
		pnl.setLayout(null);
		add(pnl);
		setSize(new Dimension(800, 750));

	}

	public static void main(String[] args) {
		new Test().setVisible(true);
	}
}
