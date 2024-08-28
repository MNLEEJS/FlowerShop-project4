import java.awt.Color;
import java.awt.Dimension;
import java.lang.Character.Subset;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProductExplain extends JFrame{
	makingJ j = new makingJ();
	FontL f = new FontL();
	FlowerDAO dao = new FlowerDAO();
	ImageFileInsert image = new ImageFileInsert();
	ImageDAO Idao = new ImageDAO();
	List<Flower> list = new ArrayList<Flower>();
	// flower 테이블의 번호의 값을 받아서 조회해서 flower의 정보를 받아
	// @ 카테고리명, @ 꽃다발이름 , @ 가격 , @ 이미지 번호를 받는다
	// @ flower_category @ flower_name @ price @ image_no
	// 여기서 이미지번호를 가지고 
	// @ image_no로 조회 해서 @ image_code를 받는다
	// flower_pic의 table 조회해서 코드를 받아서 icon화 시켜야 한다.
	public void flowerGet () {
		list.addAll((dao.selectAllWithList()));
	}
	
	
	
	public ProductExplain() {
		
		flowerGet();
		// 이미지 조회
		String code = Idao.findByNo(list.get(0).getImage_no());
		ImageIcon icon = image.ImageiconCreate(code);
		
		JPanel pnl = new JPanel();
		JLabel lblcategory = j.라벨만들기("카테고리명 : ", f.font4, 20, 10, 130, 30, pnl);
		
		String cateGory = list.get(0).getCategory();
		JLabel lblcategoryname = j.라벨만들기(cateGory, f.font4, 150, 10, 250, 30, pnl);
		
		JLabel lblprice = j.라벨만들기("가격 : ", f.font4, 20, 50, 800, 30, pnl);
		int price = list.get(0).getPrice();
		String priceString = String.valueOf(price);
		JLabel lblpricere = j.라벨만들기((priceString + " 원"), f.font4, 90, 50, 150, 30, pnl);
		
		String product = list.get(0).getName();
		JLabel lblname = j.라벨만들기(product, f.font3, 200, 40, 250, 50, pnl);
		
		
		JLabel lblproduct = j.라벨만들기(null, null, 50, 100, 400, 300, pnl);
		lblproduct.setIcon(icon);
		lblproduct.setOpaque(true);
		
		
		
		
		pnl.setLayout(null);
		pnl.setSize(new Dimension(500,500));
		add(pnl);
		setLayout(null);
		setSize(new Dimension(500,500));
	}
	public static void main(String[] args) {
		new ProductExplain().setVisible(true);
	}
	
}
