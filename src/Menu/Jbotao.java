package Menu;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Jbotao extends JButton{
	
	public Jbotao(String nome) {
		super(nome);
		this.setBackground(new Color(0, 0, 0));
		this.setForeground(new Color(255, 255, 255));
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
}
