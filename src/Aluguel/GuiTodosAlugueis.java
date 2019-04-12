package Aluguel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import noGui.ListaAluguel;
import Menu.Jbotao;


@SuppressWarnings("serial")
public class GuiTodosAlugueis extends JPanel{
	    JTable table; 
	    ListaAluguel listAluguel;
	    Jbotao voltar;
	    Container c;
		CardLayout card;
		
		String[] columnNames = { "Cpf", "Placa", "Ano","Mes","Dia"}; 
		String[][] data;
		
		JScrollPane scroll;
	  
	    public GuiTodosAlugueis(ListaAluguel listAluguel) {
	    	this.listAluguel = listAluguel;

	        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);  //vertical
	        this.setLayout(boxlayout);       

	        JLabel label = new JLabel("Todos os alugueis ");
	        label.setFont(new Font("Serif", Font.BOLD, 30));
	    
	            
	        voltar = new Jbotao("<- voltar");
	        voltar.setAlignmentY(TOP_ALIGNMENT);
	        voltar.addActionListener(new ProxTela());
	        
	        
	 
	        JPanel panelV = new JPanel();
	        panelV.setLayout(new BorderLayout());

	        JPanel panelFlow = new JPanel(new FlowLayout());
	        panelFlow.add(voltar);

	        panelV.add(panelFlow, BorderLayout.WEST);
	        
	        JPanel panelFlow2 = new JPanel(new FlowLayout());
	        panelFlow2.add(label);
	        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);


	        panelV.setMaximumSize(new Dimension(3000,40));
	        panelV.setMinimumSize(new Dimension(this.getWidth(),40));
	        this.add(panelV);
	        this.add(panelFlow2,FlowLayout.CENTER);
	    	
	    	
	    	
	    	
	        this.data = listAluguel.ShowAluguelStruct();
	    	table = new JTable(this.data, this.columnNames); 
	    	table.setBounds(30, 40, 200, 300); 
	        scroll = new JScrollPane(table);
	        this.add(this.scroll);
	        // Initializing the JTable 
	        
	    }
	    
	    public void setTelas(Container c, CardLayout card) {
			this.c = c;
			this.card = card;
		}
	    
	    public void atualizaTable() {
	    	this.remove(scroll);
	    	this.listAluguel.carregaAlugueisArq();
	    	this.data = this.listAluguel.ShowAluguelStruct();
	    	this.table = new JTable(this.data, this.columnNames);
	        this.table.setBounds(30, 40, 200, 300); 
	        this.scroll = new JScrollPane(this.table);
	        this.add(scroll);
	        
	    }
	    
	    
	    class ProxTela implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton clicado = (JButton) e.getSource();
//				String nome = clicado.getActionCommand();
				if(clicado == voltar) {
					card.show(c, "aluguel");
				}
			}
		}

} 

