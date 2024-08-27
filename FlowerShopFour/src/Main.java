import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	FlowerProduct FP = new FlowerProduct(" ");
	ManagerWindow MW = new ManagerWindow();
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
		JButton btnLogin = j.버튼만들기("로그인", f.font1, 0, 300, 200, 100, pnl1);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtID.getText().equals("이나겸") && txtPW.getText().equals("123")) {
					txtID.setText("");
					txtPW.setText("");
					pnl1.setVisible(false);
					pnl3.setVisible(true);
				} else if(txtID.getText().equals("이아현") && txtPW.getText().equals("123")){
					setVisible(false);
					txtID.setText("");
					txtPW.setText("");
					MW.setVisible(true);
					
					setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, " 아이디 비밀번호 확인 바람 ");
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
