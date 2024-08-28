import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

//작성자 : 이아현

// 구매자를 위한 주문 구성의 선택과 취소창
public class OrderInfoGui extends JFrame {
	
//	JScrollPane scrollPnl = new JScrollPane();

	makingJ j = new makingJ();
	FontL f = new FontL();
	JCheckBox c = new JCheckBox();
	JTextField txt = new JTextField();

	// 주문정보를 담을 패널 구성
			JPanel pnl = new JPanel();
			
	// 기본 주문 확인창 및 상단 버튼 생성
			JLabel lbl1 = j.라벨만들기("주문확인창", f.font5, 10, 50, 110, 50, pnl);
			JLabel lbl2 = j.라벨만들기("주문 내역", f.font5, 10, 100, 260, 50, pnl);

			JButton btn1 = j.버튼만들기("변경완료", f.font5, 150, 50, 120, 60, pnl);
			JButton btn2 = j.버튼만들기("선택취소", f.font5, 280, 50, 120, 60, pnl);
			JButton btn3 = j.버튼만들기("전체취소", f.font5, 410, 50, 120, 60, pnl);
			JButton btn4 = j.버튼만들기("결제", f.font5, 540, 50, 120, 60, pnl);
			
			
	
	public OrderInfoGui() {

		add(pnl);
		pnl.setLayout(null);
		pnl.setSize(new Dimension(700, 800));
		setLayout(null);
		setSize(new Dimension(700, 900));
		
//		// 패널에 스크롤 추가
//		pnl.add(scrollPnl);
//		scrollPnl.setBounds(10, 70, 700, 900);
//		scrollPnl.setViewportView(pnl);
		
		
		// 콤보 박스에 담을 수량 생성을 위한 배열
		String[] count = { "수량", "1개", "2개", "3개", "4개" };
		JComboBox<String> counting = new JComboBox<>(count);
		int y = 180;
		
		// 수량 선택을 위한 콤보 박스 생성
		// "수량" 선택시 정확한 수량 입력하라는 메세지 뜨면서 수량 1개로 자동 이동 
		counting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedcounting = (String) counting.getSelectedItem();

				if ("수량".equals(selectedcounting)) {
				JOptionPane.showMessageDialog(pnl, "수량을 정확히 입력해 주세요");
				counting.setSelectedIndex(1);
					
				} else {
				JOptionPane.showMessageDialog(pnl, "선택 수량" + selectedcounting);
				}
				}
			
		});
		for (int i = 0; i < 4; i++) {
			if (i == 1) {
			} else if (i == 2) {
			} else if (i == 3) {
			}
			// 상품의 주문을 위한 선택&취소 버튼, 이미지 버튼 생성
		j.체크박스만들기(10, y+20, 50, 50, pnl);
		ImageIcon icon1 = new ImageIcon(Main.class.getResource("/image/꽃 이미지.jpg"));
		JButton image = j.버튼만들기("이미지", f.font5, 60, y, 100, 100, pnl);
		image.setIcon(icon1);
		JLabel lblproduct = j.라벨만들기("상품명", f.font5, 170, y, 150, 50, pnl);
		JLabel lblPrice = j.라벨만들기("금액", f.font5, 400, y, 150, 50, pnl);
		JComboBox<String> counting1 = new JComboBox<>(count);
		pnl.add(counting1);
		counting1.setBounds(170, y+50, 100, 50);
		y += 120;
	
	
		}
		
	}

	public static void main(String[] args) {
		
		new OrderInfoGui().setVisible(true);
		
	}
}
