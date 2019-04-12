package Carro;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Menu.Jbotao;





@SuppressWarnings("serial")
public class guiCarro extends JPanel{
	Menu menu;
	Container c;
	CardLayout card;
	
	Jbotao cdCarro;
	Jbotao rmCarro;
	Jbotao showCarros;
	Jbotao voltar;
	
	guiremovCarro pagrmCarros;
	guiTodosCarros guiTdCarros;
	
	
	public guiCarro(guiremovCarro pagrmCarros,guiTodosCarros guiTdCarros){
		this.pagrmCarros = pagrmCarros;
		this.guiTdCarros = guiTdCarros;
	

        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);  //vertical
         

        this.setLayout(boxlayout);
         
        cdCarro = new Jbotao("Cadastrar");
        cdCarro.addActionListener(new ProxTela());
        
        rmCarro = new Jbotao("Remover");
        rmCarro.addActionListener(new ProxTela());
       
        
        showCarros = new Jbotao("Mostrar todos");
        showCarros.addActionListener(new ProxTela());
        
        JLabel label = new JLabel("Carro");
        label.setFont(new Font("Serif", Font.BOLD, 30));
    
      
        
        voltar = new Jbotao("<- voltar");
        voltar.setAlignmentY(TOP_ALIGNMENT);
        voltar.addActionListener(new ProxTela());
        
        JPanel panelFlow = new JPanel(new FlowLayout());
 
        JPanel panelV = new JPanel();
        panelV.setLayout(new BorderLayout());

        
        panelFlow.add(voltar);
        panelV.add(panelFlow, BorderLayout.WEST);
        

        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        this.add(panelV);

        JPanel grid = new JPanel();
        

        grid.setBackground(new Color(0, 204, 102,255));
        grid.add(rmCarro);
        grid.add(cdCarro);
        grid.add(showCarros);
        
        JPanel bd = new JPanel();
        bd.setLayout(new BorderLayout());
        bd.add(grid,BorderLayout.CENTER);

        JPanel klabel = new JPanel();
        klabel.setLayout(new BorderLayout());
        klabel.setBackground(new Color(102, 255, 153,255));
        klabel.add(label,BorderLayout.CENTER);
        bd.setBackground(new Color(102, 255, 153,255));
        this.add(label);
        this.add(bd);
        
	}
	
	public void setTelas(Container c, CardLayout card) {
		this.c = c;
		this.card = card;
	}
	
	class ProxTela implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicado = (JButton) e.getSource();
			if(clicado == voltar) {
				card.show(c,"menu");

			}else if(clicado == showCarros) {
				card.show(c, "guiTdCarro");
				guiTdCarros.atualizaTable();
				
			}else if(clicado == rmCarro) {
				card.show(c, "rmCarro");
				pagrmCarros.atualizaJcomb();
				
			}else if(clicado == cdCarro) {
				card.show(c, "guicdCarro");
			}
		}
	}
	
}


