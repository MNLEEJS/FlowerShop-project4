import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor

public class ListClass {
	public List<JButton> listbtn;
	public List<JCheckBox> listche;
	public List<JLabel> listLbl;
}
