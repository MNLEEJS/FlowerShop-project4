
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
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
	// 로그인한 유저의 아이디 비밀번호
	LoginUserInfo user = new LoginUserInfo();

	makingJ j = new makingJ();
	memberJion mj = new memberJion();
	memberModify mM = new memberModify();
	FlowerProduct FP = new FlowerProduct("");
	ManagerWindow MW = new ManagerWindow();
	MemberInfo Mif = new MemberInfo();
	FlowerDAO flowerdao = new FlowerDAO();
	List<String> listCategory = flowerdao.selectCategory();
	List<Flower> listFlower = flowerdao.selectAllWithList();
	List<JButton> listbutton = new ArrayList<JButton>();

	int deleteCount = 0;
	FontL f = new FontL();
	public JPanel pnl3;
	public JPanel pnl2;
	public JPanel pnl1;
	private JTextField txtID;
	private JTextField txtPW;

	MemberDeleteDAO MDDAO = new MemberDeleteDAO();
	UserOrderInfo UOI = new UserOrderInfo();
	OrderInfoDAO OIDAO = new OrderInfoDAO();
	OrderDetailDAO ODDAO = new OrderDetailDAO();

	public Main() {
		super("돼지 꽃다발 화원");
		getContentPane().setLayout(null);

		Main1();
		Maingo();
		Maingo2();

		setSize(new Dimension(600, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void Main1() {

		pnl2 = new JPanel();
		pnl2.setLayout(null);
		pnl2.setBounds(460, 0, 400, 500);
		getContentPane().add(pnl2);
//		=======================================
		String category = "기본값";
		int y = 50;
		for (int i = 0; i < listCategory.size(); i++) {
			category = listCategory.get(i);

			if (category.equals(listCategory.get(i))) {

			}
			if (i == 0) {
				JLabel CategoryLabel = j.라벨만들기("카테고리", f.font5, 30, y, 100, 50, pnl2);
				y += 50;
			}
			JButton btnCategory = j.버튼만들기(category, f.font4, 10, y, 100, 50, pnl2);
			listbutton.add(btnCategory);
			y += 70;

			if (i == 6) {
				break;
			}
		}
//		======================================
		// 카테고리버튼 액션주기
		// 나겸이 완성하면 스트링으로 넘겨주기
		for (int a = 0; a < listbutton.size(); a++) {
			int z = a;

			// 각 카테고리 별 버튼을 누르면 해당 카테고리 화면(다이얼로그창)이 뜸
			listbutton.get(z).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					String catego = listbutton.get(z).getText();
					CategoryPage CP = new CategoryPage(catego);
					CP.user = user;
					CP.setVisible(true);
				}
			});
		}
	}

//		======================================

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
				List<String> listID1 = Mif.IDseach();
				List<String> listPW1 = Mif.PWseach();

				user.setID(txtID.getText());
				user.setPW(txtPW.getText());

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

						pnl1.setVisible(false);
						txtID.setText("");
						txtPW.setText("");
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
//		JLabel lblWelcome = j.라벨만들기("회원님 반갑습니다.(회원번호)", f.font3, 30, 40, 600, 100, pnl3);
		JButton btnJang = j.버튼만들기("장바구니", f.font4, 30, 150, 200, 130, pnl3);
		btnJang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OrderInfoGui orderInfoGui = new OrderInfoGui(user.getID());
				orderInfoGui.setVisible(true);
			}
		});
		JButton btnModify = j.버튼만들기("회원정보 수정", f.font4, 250, 150, 200, 130, pnl3);
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mM.user = user;
				mM.setVisible(true);
			}
		});
		JButton btnLogOut = j.버튼만들기("회원 탈퇴", f.font4, 30, 300, 200, 100, pnl3);
		btnLogOut.addActionListener(new ActionListener() {
			private List<Membership> userList;
			private List<UserOrder> userorderInfoList;
			private List<OrderInfo> ordertInfoList;
			private List<OrderDetail> orderDetailList;

			@Override
			public void actionPerformed(ActionEvent e) {

				String a = JOptionPane.showInputDialog("정말 탈퇴하시겠습니까? ||'네' 라고 입력하세요");
				if (a.equals("네")) {
					MDDAO.insert(user.getID()); // 삭제할 유저의 정보를 논리적 삭제 구현
					// 유저의 정보 조회
					userList = MDDAO.selectMembershipID(user.getID());
					// 유저오더인포 정보
					if (userorderInfoList != null) {
						userorderInfoList = UOI.findByPk(userList.get(0).getNo(), "user_NO");
					}else {

					}
					// 시팔 몰라
					if (ordertInfoList != null) {
						ordertInfoList = OIDAO.selectOrderNo(userorderInfoList.get(0).getUser_no());
					}else {

					}
					// 로그인한 유저로 정보를 조회
					if (orderDetailList != null) {
						orderDetailList = ODDAO.selectOrderDetailNo(ordertInfoList.get(0).getFlowerOrderNo());
					}else {

					}
					// 1 . 주문정보 테이블 삭제 완료
					if (userorderInfoList != null) {
						MDDAO.deleteOrder_info(userorderInfoList.get(0).getNo());
					}else {

					}
					// 2. 회원 주문 정보 테이블 삭제
					if (userList != null) {
						MDDAO.deleteUserOrder_info(userList.get(0).getNo());
					} else {

					}
					// 3. 주문상세 내역 테이블 삭제
					// 얘는 PK기준으로 삭제라서 여러PK가 있기에 FOR문 돌림
					if (ordertInfoList != null) {
						for (int i = 0; i < ordertInfoList.size(); i++) {
							MDDAO.deleteOrder_detail(ordertInfoList.get(i).getOrderNo());
						}
					}else {

					}
					// 멤버쉽 테이블에서도 마무으리 삭제
					int userDelete = 0;
					try {
						System.out.println(userList.get(0).getNo());
						userDelete = Mif.deleteMembership(userList.get(0).getNo());
					} catch (Exception e8) {
						e8.printStackTrace();
					}
//					if (userDelete > 0) {
//						JOptionPane.showMessageDialog(null, "성공적으로 탈퇴 되었습니다.");
//					} else {
//						JOptionPane.showMessageDialog(null, "탈퇴불가.");
//					}
					if (orderDetailList != null) {
						for (int j = 0; j < orderDetailList.size(); j++) {
							orderDetailList.remove(j);
						}
					}else {

					}
					if (ordertInfoList != null) {
						for (int i = 0; i < ordertInfoList.size(); i++) {
							ordertInfoList.remove(i);
						}
					}else {

					}
					if (userorderInfoList != null) {
						for (int j = 0; j < userorderInfoList.size(); j++) {
							userorderInfoList.remove(j);
						}
					}else {

					}
					if (userList != null) {
						for (int i = 0; i < userList.size(); i++) {
							userList.remove(i);
						}
					}else {

					}
					if (userDelete > 0) {
						pnl3.setVisible(false);
						pnl1.setVisible(true);
						JOptionPane.showMessageDialog(null, "성공적으로 탈퇴 되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "탈퇴불가.");
					}
				}
			}
		});
		JButton btnOut = j.버튼만들기("로그아웃", f.font4, 250, 300, 200, 100, pnl3);
		btnOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				user.setID(null);
				user.setPW(null);
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
