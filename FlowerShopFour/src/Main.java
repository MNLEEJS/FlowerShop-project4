import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
// 이진석 메인화면 작성
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame {
	memberJion mj = new memberJion();
	memberModify mM = new memberModify();
	int count = 0;
	FontL f = new FontL();
	private JLabel lbl1;
	private JPanel pnl3;
	private JPanel pnl2;

	public Main() {
		super("돼지 꽃다발 화원");

		ImageIcon icon1 = new ImageIcon("src/image/예시1.jpg");
//==================================== 로그인 하기 전 pnl1 
		// 로그인 화면
		로그인하기전에();
		
		pnl2 = new JPanel();
		// 카테고리별 사진 띄우기 for문 만들어서 수정하기
		JButton photo = new JButton();
		pnl2.add(photo);
		pnl2.setBounds(500, 0, 600, 500);
		add(pnl2);
		pnl2.setLayout(null);
		photo.setIcon(icon1);
		photo.setBounds(100, 0, 500, 450);

//=========================================로그인을 하고 나면 띄워줄 화면 pnl3
		// 로그인 했을때 화면 띄우기
		

//==========================================================
		setSize(new Dimension(1200, 500));
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void 로그인하기전에() {
		
		JPanel pnl1 = new JPanel();
		lbl1 = new JLabel("돼지 꽃집 메인화면");
		lbl1.setBounds(0, 10, 600, 100);
		lbl1.setFont(f.font2);
		// 아이디
		JLabel lblID = new JLabel("아이디");
		lblID.setBounds(100, 150, 100, 30);
		lblID.setFont(f.font1);
		JTextField txtID = new JTextField(15);
		txtID.setBounds(200, 150, 100, 30);
		// 비밀번호
		JLabel lblPW = new JLabel("비밀번호");
		lblPW.setBounds(80, 200, 150, 30);
		lblPW.setFont(f.font1);
		JTextField txtPW = new JTextField(15);
		txtPW.setBounds(200, 200, 100, 30);
		// 로그인 버튼
		JButton btnLogin = new JButton("로그인");

		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnl1.setVisible(false);
				로그인했을때();
			}
		});
		btnLogin.setBounds(0, 300, 200, 100);
		btnLogin.setFont(f.font1);
		// 회원가입 버튼
		JButton btnJoin = new JButton("회원가입");
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mj.setVisible(true);
			}
		});
		btnJoin.setBounds(200, 300, 200, 100);
		btnJoin.setFont(f.font1);
		pnl1.add(btnJoin);
		pnl1.add(btnLogin);
		pnl1.add(lblID);
		pnl1.add(txtID);
		pnl1.add(lblPW);
		pnl1.add(txtPW);
		pnl1.add(lbl1);
		add(pnl1);
		pnl1.setLayout(null);
		pnl1.setBounds(0, 0, 600, 500);
	}

// =====================================
	private void 로그인했을때() {
		pnl3 = new JPanel();
		// 메인화면
		pnl3.add(lbl1);
		lbl1.setBounds(0, 0, 600, 100);
		lbl1.setFont(f.font2);

		JLabel lblWelcome = new JLabel("회원님 반갑습니다.(회원번호)");
		lblWelcome.setBounds(30, 40, 600, 100);
		lblWelcome.setFont(f.font3);
		JButton btnJang = new JButton("장바구니");
		btnJang.setBounds(30, 150, 200, 130);
		btnJang.setFont(f.font3);
		JButton btnModify = new JButton("회원정보 수정");
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mM.setVisible(true);
			}
		});
		
		btnModify.setBounds(250, 150, 200, 130);
		btnModify.setFont(f.font3);
		JButton btnLogOut = new JButton("회원 탈퇴");
		btnLogOut.setBounds(30, 300, 200, 100);
		btnLogOut.setFont(f.font3);
		JButton btnOut = new JButton("로그아웃");
		btnOut.setBounds(250, 300, 200, 100);
		btnOut.setFont(f.font3);

		pnl3.add(lblWelcome);
		pnl3.add(btnJang);
		pnl3.add(btnModify);
		pnl3.add(btnLogOut);
		pnl3.add(btnOut);
		pnl3.setLayout(null);
		pnl3.setBounds(0, 0, 600, 500);
		add(pnl3);
	}

	public static void main(String[] args) {
		new Main().setVisible(true);
	}

}
