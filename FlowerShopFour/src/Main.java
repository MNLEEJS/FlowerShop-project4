import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
// 이진석 메인화면 작성
import javax.swing.JPanel;
import javax.swing.JTextField;

//작성자 || 이진석
public class Main extends JFrame {
	makingJ j = new makingJ();
	memberJion mj = new memberJion();
	memberModify mM = new memberModify();
	FlowerProduct FP = new FlowerProduct("");
	ManagerWindow MW = new ManagerWindow();
	MemberInfo Mif = new MemberInfo();
	FontL f = new FontL();
	public JPanel pnl3;
	public JPanel pnl2;
	public JPanel pnl1;
	private JTextField txtID;
	private JTextField txtPW;

	public Main() {
		super("돼지 꽃다발 화원");
		getContentPane().setLayout(null);
		Main1();
		Maingo();
		Maingo2();

		setSize(new Dimension(1200, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void Main1() {
		pnl2 = new JPanel();
		pnl2.setLayout(null);
		pnl2.setBounds(600, 0, 600, 500);
		getContentPane().add(pnl2);

		ImageIcon icon1 = new ImageIcon(Main.class.getResource("/image/예시1.jpg"));
		JButton photo = j.버튼만들기(null, f.font1, 0, 0, 500, 450, pnl2);
		photo.setIcon(icon1);
		photo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FP.setVisible(true);
			}
		});
		pnl2.add(photo);
	}

	private void Maingo() {
		pnl1 = new JPanel();
		JLabel lbl12 = j.라벨만들기("돼지 꽃집 메인화면", f.font2, 0, 10, 600, 100, pnl1);
		// 아이디
		JLabel lblID = j.라벨만들기("아이디", f.font1, 100, 150, 100, 30, pnl1);
		txtID = j.텍스트필드만들기(15, null, 200, 150, 100, 30, pnl1);
		// 비밀번호
		JLabel lblPW = j.라벨만들기("비밀번호", f.font1, 80, 200, 150, 30, pnl1);
		txtPW = j.텍스트필드만들기(15, null, 200, 200, 100, 30, pnl1);
		// 로그인 버튼
		List<String> listID1 = Mif.IDseach();
		List<String> listPW1 = Mif.PWseach();
		System.out.println(listID1.size());
		System.out.println(listPW1.size());
		JButton btnLogin = j.버튼만들기("로그인", f.font1, 0, 300, 200, 100, pnl1);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 일반회원 조회
				int countID = 0;
				int countPW = 0;
				// 관리자 조회
				int countIDID = 0;
				int countPWPW = 0;
				int king = 0;
				for (int j = 0; j < listID1.size(); j++) {
					if (txtID.getText().equals(listID1.get(j))) {
						countID++;
					}
					if (txtPW.getText().equals(listPW1.get(j))) {
						countPW++;
					}

					if (txtID.getText().equals("이나겸")) {
						countIDID++;
					}
					if (txtPW.getText().equals("12")) {
						countPWPW++;
					}

				}
				if (countIDID > 0 && countPWPW > 0) {
					king++; // 관리자로 로그인하면 일반회원 로그인 못하게 조건 주는 매개체
					setVisible(false);
					txtID.setText("");
					txtPW.setText("");
					MW.setVisible(true);

					setVisible(true);

				}
				if (king == 0) { // 관리자 로그인 실패시 일반회원 정보 조회
					if (countID > 0 && countPW > 0) {
						txtID.setText("");
						txtPW.setText("");
						pnl1.setVisible(false);
						pnl3.setVisible(true);
					} else if (countID > 0 && countPW == 0) {
						JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요");
					} else {
						JOptionPane.showMessageDialog(null, "회원정보가 존재하지 않습니다");
					}
				} else {
					// 관리자 조건 if문 구분하려고
				}
			}
		});
		// 회원가입 버튼
		JButton btnJoin = j.버튼만들기("회원가입", f.font1, 200, 300, 200, 100, pnl1);
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mj.setVisible(true);
			}
		});
		add(pnl1);
		pnl1.setLayout(null);
		pnl1.setBounds(0, 0, 600, 500);
	}

// =====================================
	public void Maingo2() {
		pnl3 = new JPanel();
		// 메인화면
		JLabel lbl1 = j.라벨만들기("돼지 꽃집 메인화면", f.font2, 0, 10, 600, 100, pnl3);
		JLabel lblWelcome = j.라벨만들기("회원님 반갑습니다.(회원번호)", f.font3, 30, 40, 600, 100, pnl3);
		JButton btnJang = j.버튼만들기("장바구니", f.font3, 30, 150, 200, 130, pnl3);
		JButton btnModify = j.버튼만들기("회원정보 수정", f.font3, 250, 150, 200, 130, pnl3);
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mM.setVisible(true);
			}
		});
		JButton btnLogOut = j.버튼만들기("회원 탈퇴", f.font3, 30, 300, 200, 100, pnl3);
		JButton btnOut = j.버튼만들기("로그아웃", f.font3, 250, 300, 200, 100, pnl3);
		btnOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtID.setText("");
				txtPW.setText("");
				pnl1.setVisible(true);
				pnl3.setVisible(false);
			}
		});
		pnl3.setLayout(null);
		pnl3.setBounds(0, 0, 600, 500);
		pnl3.setVisible(false);
		add(pnl3);
	}

	public static void main(String[] args) {
		new Main().setVisible(true);
	}

}
