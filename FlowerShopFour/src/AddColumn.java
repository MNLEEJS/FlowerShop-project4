import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.Character.Subset;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddColumn extends JDialog {
	
	List<Flower> listF = new ArrayList<Flower>();
	JFileChooser jfc = new JFileChooser();
	// makingJ, FontL 선언으로 메소드 사용 가능
	makingJ j = new makingJ();
	FontL f = new FontL();
	ImageFileInsert IFI = new ImageFileInsert();
	List<JTextField> listTxt = new ArrayList<JTextField>();
	List<JButton> listJB = new ArrayList<JButton>();
	String code = null;
	// 하나의 패널과 메인 컬럼 추가
	// 컬럼추가 화면의 각 라벨과 텍스트 필드 생성
	public AddColumn() {
		setModal(true);
		JPanel pnl = new JPanel();
		JLabel lbl6 = j.라벨만들기("컬럼 추가 화면", f.font2, 120, 50, 500, 50, pnl);

		int y = 120;
		int x = 150;
		int w = 50;
		int h = 100;

		for (int i = 0; i < 5; i++) {
			String name = "카테고리";
			if (i == 1) {
				name = "컬럼명";
			} else if (i == 2) {
				name = "수량";
			} else if (i == 3) {
				name = "가격";
			} else if (i == 4) {
				name = "이미지";
			}
			JLabel lbl = j.라벨만들기(name, f.font1, x, y, h, w, pnl);
			if (i != 4) {
				JTextField txt1 = j.텍스트필드만들기(15, f.font1, 2 * x, y, h + 50, w, pnl);
				listTxt.add(txt1);
			} else if (i == 4) {
				JButton btn = j.버튼만들기(" + ", f.font2, 2 * x, y, h + 50, w, pnl);
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						jfc.showOpenDialog(null);
						int a = JFileChooser.CANCEL_OPTION; // 종료 했을때 반환하는 int 값
						int b = JFileChooser.APPROVE_OPTION; // 저장 했을때 반환하는 int 값

						File file = jfc.getSelectedFile();

						String files = file.getPath(); // 파일경로 String
						String fileName = file.getName(); // 파일 이름 String
						int lastIndex = fileName.lastIndexOf("."); // 확장자로 자르기
						String last = fileName.substring(lastIndex); // 확장자 String
						if (last.equals(".jpg") || last.equals(".png")) {
							code = IFI.ImageFile(files);
						} else {
							if (a != 1) {
								JOptionPane.showMessageDialog(null, "올바른 파일을 선택하세요");
							} else {
								JOptionPane.showMessageDialog(null, "종료합니다.");
							}
						}
					}
				});
			}
			y += 60;
		}
		// 하단 확인, 취소 버튼 생성
		JButton btn1 = j.버튼만들기("확인", f.font1, 300, 450, 100, 50, pnl);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String category = listTxt.get(0).getText();
				String name = listTxt.get(1).getText();
				int count = Integer.valueOf(listTxt.get(2).getText());
				int price = Integer.valueOf(listTxt.get(3).getText());

				FlowerDAO da = new FlowerDAO();
				int a = da.insert(category, name, count, price, code);
				if (a > 0) {
					JOptionPane.showMessageDialog(null, "성공");
					for (int j = 0; j < listTxt.size(); j++) {
						listTxt.get(j).setText("");
						code = null;
					}
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "대실패");
				}
			}
		});
		JButton btn2 = j.버튼만들기("취소", f.font1, 410, 450, 100, 50, pnl);
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		pnl.add(btn1);

		pnl.setLayout(null);
		pnl.setSize(new Dimension(600, 600));
		add(pnl);
		setLayout(null);
		setSize(new Dimension(600, 600));

	}

}
