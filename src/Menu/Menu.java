package Menu;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Cliente.guiCliente;




// usa o cardLAYOUT


@SuppressWarnings("serial")
public class Menu extends JPanel{
	Jbotao btCliente;
	Jbotao btCarro;
	Jbotao btAluguel;
	Container c;
	guiCliente cliente;
	CardLayout card;
	
	
	public Menu() {
//		this.setBackground();
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);  //vertical
         

        this.setLayout(boxlayout);
         
        // setando a borda
//        this.setBorder(new EmptyBorder(new Insets(100, 150, 100, 150)));
        //this.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));     
         
        // Define new buttons
        btCliente = new Jbotao("Cliente");
        btCliente.addActionListener(new ProxTela());


        
        btCarro = new Jbotao("Carro");
        btCarro.addActionListener(new ProxTela());

        
        btAluguel = new Jbotao("Aluguel");
        btAluguel.addActionListener(new ProxTela());

        
        JLabel label = new JLabel("Menu");
        label.setFont(new Font("Serif", Font.BOLD, 30));
    
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JPanel grid = new JPanel();
//        grid.setLayout(new GridLayout(3,1));

        grid.setBackground(new Color(0, 204, 102,255));
        grid.add(btCliente);
        grid.add(btCarro);
        grid.add(btAluguel);
        
       

        JPanel klabel = new JPanel();
//        klabel.setLayout(new BorderLayout());
        label.setBackground(new Color(102, 255, 153,255));
//        klabel.setBackground(new Color(102, 255, 153,255));
        klabel.add(label,BorderLayout.CENTER);

//        bd.setBackground(new Color(102, 255, 153,255));
        
        this.add(klabel);
        this.add(grid);


	}
	public void setTelas(Container c, CardLayout card) {
		this.c = c;
		this.card = card;
	}
	class ProxTela implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton clicado = (JButton) e.getSource();

			if(clicado == btCliente) {
				card.show(c, "cliente");
			}
			else if(clicado == btCarro) {
				card.show(c, "carro");
			}
			else if(clicado == btAluguel) {
				card.show(c, "aluguel");
			}
			
		}
	}
}
	

