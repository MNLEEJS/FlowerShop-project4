
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jdk.nashorn.internal.scripts.JO;

public class memberModify extends JDialog {
	int LoginSucces = 0; // 로그인 성공여부 확인
	FontL f = new FontL();
	makingJ makeJ = new makingJ();
	memberJion ma = new memberJion();
	MemberInfo MIF = new MemberInfo();
	LoginUserInfo user;
	int PWcount = 0; // 비밀번호 확인을 완료 했는지
	public String passWord;
	public String name;
	public String phoneNum;
	public String address;
	public int passWordChe;
	public int nameChe;
	public int phoneNumChe;
	public int addressChe;

	List<JCheckBox> listChe = new ArrayList<>(); // 체크박스들을 담아놓을 리스트
	List<JTextField> listpassWord = new ArrayList<>(); // 비밀번호 확인을 위해서
	List<JTextField> listTxt = new ArrayList<>(); // 변경할 텍스르 리스르

	public memberModify() {

		JPanel pnl = new JPanel();
		int x = 60;
		int y = 220;
		int h = 200;
		int w = 50;
		int a = 4;
		라벨및텍스트만들기(x, y, h, w, a, pnl);
		CheckBoxEvent();
		// 최초에 텍스트 필드와 체크박스 false를 준다.
		for (int i = 0; i < listChe.size(); i++) {
			listChe.get(i).setEnabled(false);
			listTxt.get(i).setEditable(false);
		}
		listpassWord.get(0).setEditable(false);

		JLabel lbl = makeJ.라벨만들기("회원 정보 수정", f.font2, 10, 10, 400, 100, pnl);
		JLabel lblID = makeJ.라벨만들기("아이디", f.font3, 130, 110, 150, 50, pnl);
		JTextField txtID = makeJ.텍스트필드만들기(10, f.font1, 280, 110, 200, 50, pnl);
		JLabel lblPW = makeJ.라벨만들기("비밀번호", f.font3, 130, 160, 150, 50, pnl);
		JTextField txtPW = makeJ.텍스트필드만들기(10, f.font1, 280, 160, 200, 50, pnl);
		// 변경할 비밀번호 확인용
		JButton btnpassWord = makeJ.버튼만들기("비번확인", f.font4, 460, 270, 120, 50, pnl);
		btnpassWord.setEnabled(false);
		// 비밀번호 확인하는 버튼
		btnpassWord.addActionListener(new ActionListener() {
			private String passWord;

			@Override
			public void actionPerformed(ActionEvent e) {
				passWord = listTxt.get(0).getText();
				String passWOK = listpassWord.get(0).getText();
				if (passWord.equals(user.getPW())) {
					JOptionPane.showMessageDialog(null, "기존비밀번호와 같습니다.");
				} else if (passWord.equals(passWOK)) {
					if (PWcount == 0) {
						PWcount++;
					}
					listTxt.get(0).setEnabled(false);
					listpassWord.get(0).setEnabled(false);
					btnpassWord.setEnabled(false);
					JOptionPane.showMessageDialog(null, "비밀번호가 확인되었습니다.");
				} else {
					if (PWcount == 0) {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					}
				}
			}
		});
		// 로그인한 유저가 아이디랑 비밀번호를 아는지 검증
		JButton btnOk = makeJ.버튼만들기("확인", f.font1, 480, 110, 100, 100, pnl);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = user.getID();
				String PW = user.getPW();

				if (txtID.getText().equals(ID) && txtPW.getText().equals(PW)) {

					JOptionPane.showMessageDialog(null, "정보가 확인되었습니다.");
					btnOk.setEnabled(false);
					for (int i = 0; i < listChe.size(); i++) {
						listChe.get(i).setEnabled(true);
						listTxt.get(i).setEditable(true);
						listpassWord.get(0).setEditable(true);
						btnpassWord.setEnabled(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "정보를 다시 확인하세요.");
				}

			}
		});
		JButton btnBack = makeJ.버튼만들기("뒤로가기", f.font5, 350, 450, 130, 100, pnl);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				btnOk.setEnabled(true);
				btnpassWord.setEnabled(false);
				for (int i = 0; i < listChe.size(); i++) {
					listChe.get(i).setSelected(false);
					listTxt.get(i).setText("");
					listpassWord.get(0).setText("");
					txtID.setText("");
					txtPW.setText("");
				}
			}
		});
		JButton btnChange = makeJ.버튼만들기("변경", f.font3, 480, 450, 100, 100, pnl);
		btnChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 업데이트문 만들기
				if (passWordChe > 0 && PWcount > 0) {
					passWord = listTxt.get(0).getText();
				} else if (passWordChe > 0) {
					JOptionPane.showMessageDialog(null, "비밀번호 확인해주세요");
				}
				if (addressChe > 0) {
					phoneNum = listTxt.get(1).getText();
				}
				if (phoneNumChe > 0) {
					address = listTxt.get(2).getText();
				}

				int update = MIF.update(phoneNum, user.getID(), passWord, address);
				if (update > 0) {
					JOptionPane.showMessageDialog(null, "성공");
				} else {
					JOptionPane.showMessageDialog(null, "실패");
				}

				for (int i = 0; i < listChe.size(); i++) {
					listTxt.get(i).setText("");
					listpassWord.get(0).setText("");
					txtID.setText("");
					txtPW.setText("");
				}
			}
		});

		pnl.setLayout(null);
		pnl.setSize(new Dimension(600, 600));
		add(pnl);
		setLayout(null);
		setSize(new Dimension(600, 600));
	}

// 체크박스 이벤트 
	private void CheckBoxEvent() {
		for (int i = 0; i < listChe.size(); i++) {
			int forcount = i;
			listChe.get(i).addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					// 눌렀을떄
					if (e.getStateChange() == ItemEvent.SELECTED) {
						CheckBoxItemEvent(forcount);
						// 취소했을때
					} else {
						CheckBoxItemEvent2(forcount);
					}
				}

				// 체크박스를 눌렀을때 조건
				private void CheckBoxItemEvent(int checkNum) {
					if (forcount == checkNum && PWcount > 0) {
						passWordChe++;
					} else if (forcount == checkNum) {
						phoneNumChe++;
					} else if (forcount == checkNum && checkNum == 2) {
						addressChe++;
					}
				}

				// 체크박스를 취소했을때 조건
				private void CheckBoxItemEvent2(int checkNum) {
					if (forcount == checkNum && PWcount > 0) {
						passWordChe--;
					} else if (forcount == checkNum) {
						phoneNumChe--;
					} else if (forcount == checkNum && checkNum == 2) {
						addressChe--;
					}
				}
			});
		}
	}

	public void 라벨및텍스트만들기(int x, int y, int h, int w, int a, JPanel pnl) {
		String name = "비밀번호";
		for (int i = 0; i < a; i++) {
			if (i == 1) {
				name = "비밀번호 확인";
			} else if (i == 2) {
				name = "휴대폰 번호";
			} else if (i == 3) {
				name = "주소";
			}
			makeJ.라벨만들기(name, f.font1, x, y, h, w, pnl);
			JTextField txt = makeJ.텍스트필드만들기(15, f.font1, x + 200, y, h, w, pnl);
			if (i != 1) {
				listTxt.add(txt);
			} else {
				listpassWord.add(txt);
			}
			String name1 = name;

			if (i != 1) {
				JCheckBox che = makeJ.체크박스만들기(10, y, 50, 50, pnl);
				listChe.add(che);
				y += 50;
			} else {
				y += 50;
			}

		}
	}
}
