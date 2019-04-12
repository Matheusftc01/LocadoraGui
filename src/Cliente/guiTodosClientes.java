package Cliente;
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

import Menu.Jbotao;
import noGui.ListaEncClient;

@SuppressWarnings("serial")
public class guiTodosClientes extends JPanel{
	    JTable table; 
	    ListaEncClient listaClientes;
	    Jbotao voltar;
	    Container c;
		CardLayout card;
		
		String[] columnNames = { "Nome", "CPF", "Divida","Telefone","Endereço" }; 
		String[][] data;
		
		JScrollPane scroll;
	  
	    public guiTodosClientes(ListaEncClient listCli) {
	    	this.listaClientes = listCli;
	    	
	        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);  //vertical
	        this.setLayout(boxlayout);       

	        JLabel label = new JLabel("Todos os clientes ");
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
	    	
	    	
	    	
	    	
	        this.data = listaClientes.ShowClientesStruct();
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
	    	this.data = this.listaClientes.ShowClientesStruct();
	    	this.table = new JTable(this.data, this.columnNames); 
	        this.table.setBounds(30, 40, 200, 300); 
	        this.scroll = new JScrollPane(this.table);
	        this.add(scroll);
//	    	this.table = new JTable(data,columnNames);
	    }
	    
	    
	    class ProxTela implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton clicado = (JButton) e.getSource();
//				String nome = clicado.getActionCommand();
				if(clicado == voltar) {
					card.show(c, "cliente");
				}
			}
		}

} 
