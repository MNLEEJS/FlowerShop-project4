import java.awt.Color;
import java.awt.Dimension;
import java.lang.Character.Subset;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProductExplain extends JDialog{
   makingJ j = new makingJ();
   FontL f = new FontL();
   FlowerDAO dao = new FlowerDAO();
   ImageFileInsert image = new ImageFileInsert();
   ImageDAO Idao = new ImageDAO();
   List<Flower> list = new ArrayList<Flower>();
   List<Flower> flowerList = new ArrayList<Flower>();
   
   public void flowerGet (String category) {
	   flowerList.addAll(dao.selectWhere(category));
//	   flowerList.addAll(c)
//      list.addAll((dao.selectAllWithList()));
   }
   
   // flower 테이블의 번호의 값을 받아서 조회해서 flower의 정보를 받아
   // @ 카테고리명, @ 꽃다발이름 , @ 가격 , @ 이미지 번호를 받는다
   // @ flower_category @ flower_name @ price @ image_no
   // 여기서 이미지번호를 가지고 
   // @ image_no로 조회 해서 @ image_code를 받는다
   // flower_pic의 table 조회해서 코드를 받아서 icon화 시켜야 한다.
   
// flower 테이블의 번호의 값을 받아서 조회해서 flower의 정보를 받아
	// @ 카테고리명, @ 꽃다발이름 , @ 가격 , @ 이미지 번호를 받는다
	// @ flower_category @ flower_name @ price @ image_no
	// 여기서 이미지번호를 가지고 
	// @ image_no로 조회 해서 @ image_code를 받는다
	// flower_pic의 table 조회해서 코드를 받아서 icon화 시켜야 한다.
	// 전우들과 상의해서 무슨값을 줄지 얘기하기
	// 클래스를 하나 열어서 어떤값을 받을지 아니면 새로 받을지 
	
	// 상품의 이름으로 조회해서  리스트값 받아오기 
//	String columName = null;
//	String name = null;
//	list.addAll(dao.findBy(columName, null, name, null));
	
	// 상품의 이미지번호를 받아서 리스트값 받아오기
//	String columName = null;
//	String image_no = null;
//	list.addAll(dao.findBy(columnName, null, null, image_no));
   
   
   
   public ProductExplain(String category , int imageNum) {
      setModal(true);
      flowerGet(category);
      // 이미지 조회
      String code = Idao.findByNo(flowerList.get(imageNum).getImage_no());
      ImageIcon icon = image.ImageiconCreate(code);
      
      JPanel pnl = new JPanel();
      JLabel lblcategory = j.라벨만들기("카테고리명 : ", f.font4, 20, 10, 130, 30, pnl);
      
      String cateGory = flowerList.get(imageNum).getCategory();
      JLabel lblcategoryname = j.라벨만들기(cateGory, f.font4, 150, 10, 250, 30, pnl);
      
      JLabel lblprice = j.라벨만들기("가격 : ", f.font4, 20, 50, 800, 30, pnl);
      int price = flowerList.get(imageNum).getPrice();
      String priceString = String.valueOf(price);
      JLabel lblpricere = j.라벨만들기((priceString + " 원"), f.font4, 90, 50, 150, 30, pnl);
      
      String product = flowerList.get(imageNum).getName();
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
   
}
