package Aluguel;

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




public class guiAluguel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Menu menu;
	Container c;
	CardLayout card;
	
	Jbotao jbnvAluguel;
	Jbotao fnAluguel;
	Jbotao showAluguel;
	Jbotao voltar;
	
	GuiNovoAluguel nvAluguel;
	GuiTodosAlugueis tdAluguel;
	GuiFinalizarAluguel finalAluguel;
	
	public guiAluguel(GuiNovoAluguel nvAluguel, GuiTodosAlugueis tdAluguel,GuiFinalizarAluguel finalAluguel){
		
		this.tdAluguel = tdAluguel;
		this.nvAluguel = nvAluguel;
		this.finalAluguel = finalAluguel;
			
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);  //vertical
         

        this.setLayout(boxlayout);
         
        jbnvAluguel = new Jbotao("Novo");
        jbnvAluguel.addActionListener(new ProxTela());
        
        fnAluguel = new Jbotao("Pagar");
        fnAluguel.addActionListener(new ProxTela());
       
        
        showAluguel = new Jbotao("Mostrar todos");
        showAluguel.addActionListener(new ProxTela());
        
        JLabel label = new JLabel("Aluguel");
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
        grid.add(fnAluguel);
        grid.add(jbnvAluguel);
        grid.add(showAluguel);
        
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
			}
			else if(clicado == jbnvAluguel) {
				card.show(c, "nvAluguel");
				nvAluguel.atualizaJcomb();
				
			}
			else if(clicado == showAluguel) {
				card.show(c, "tdAlugueis");
				tdAluguel.atualizaTable();
			}
			else if(clicado == fnAluguel) {
				card.show(c,"fnAluguel");
				finalAluguel.atualizaJcomb();
			}
		}
	}
	
}



