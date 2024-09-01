import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 작업자 : 이아현

// 관리자 페이지 
public class OwnerPageFrame extends JFrame {
	FontL f = new FontL();
	makingJ j = new makingJ();
	JCheckBox c = new JCheckBox();
	// 이미지코드를 이미지 아이콘으로 바꿔주기 위한 클래스선언
	ImageFileInsert IFI = new ImageFileInsert();
	// 플라워 리스트 받아서 이미지 코드를 찾기위한 클래스
	ImageDAO IDAO = new ImageDAO();
	// 플라워 카테고리별 리스트 받아보기
	FlowerDAO FDAO = new FlowerDAO();
	// 조회해서 리스트에 넣기
	List<Flower> flowerList = FDAO.selectcategoryLowConut();

	// 컬럼명 저장할곳(빈 스트링 생성)
	String category = null;
	// 꽃다발이름
	String flowerName = null;
	// 남은수량
	int counting = 0;
	// 가격
	int nowPrice = 0;

	// 관리자 페이지의 상단 라벨과 버튼 생성
	public OwnerPageFrame() {
//		setModal(true);
		JPanel pnl = new JPanel();
		JLabel lbl1 = j.라벨만들기("관리자 페이지", f.font1, 10, 10, 200, 50, pnl);
//		JLabel lbl2 = j.라벨만들기("카테고리 1", f.font1, 210, 10, 170, 50, pnl);
		JButton btnAddColumn = j.버튼만들기("컬럼삭제", f.font1, 370, 10, 140, 50, pnl);
		JButton btnCoulumn = j.버튼만들기("뒤로가기", f.font1, 520, 10, 140, 50, pnl);

		int z = 0;
		int y = 120;
		int x = 100;
		int w = 50;
		int h = 100;
		// 수량이 2개 이하인 꽃다발 만큼 행만들기
		for (int i = 0; i < flowerList.size(); i++) {
			JCheckBox che = j.체크박스만들기(50, y, 30, 30, pnl);
			// 남은 공간에 카테고리도 넣기
//		JLabel lblCategory = j.라벨만들기(category, f.font1, x, y, h, w, pnl);

			JLabel lblFlowerName = j.라벨만들기(flowerName, f.font1, x, y, h, w, pnl);
			JLabel lblCounting = j.라벨만들기("남은 수량 :  " + counting, f.font1, 2 * x, y, h + 10, w, pnl);
			JLabel lblnowPrice = j.라벨만들기("꽃다발 가격 : " + nowPrice, f.font1, 3 * x + 30, y, h + 10, w, pnl);
			JButton btnImage = j.버튼만들기("이미지", f.font1, 4 * x + 60, y, h + 100, w, pnl);

			// 행별 이미지 출력
			int imageNo = flowerList.get(i).getImage_no();
			// 이미지 코드 받기
			String imageCode = IDAO.findByNo(imageNo);
			// 이미지코드로 이미지 아이콘만들기
			ImageIcon icon = IFI.ImageiconCreate(imageCode);
			// 만든아이콘 버튼에 씌우기
			btnImage.setIcon(icon);

			y += 60;
		}

		add(pnl);
		pnl.setLayout(null);
		pnl.setSize(new Dimension(700, 600));
		setLayout(null);
		setSize(new Dimension(700, 600));

	}

	public static void main(String[] args) {
		new OwnerPageFrame().setVisible(true);

	}
}
