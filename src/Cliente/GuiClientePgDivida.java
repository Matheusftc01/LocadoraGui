package Cliente;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Menu.Jbotao;
import noGui.ListaEncClient;




@SuppressWarnings("serial")
public class GuiClientePgDivida extends JPanel{
	Container c;
	CardLayout card;
	Jbotao voltar;
	Jbotao quitar;
	JComboBox<Object> jb;
	
	int [] dividas;
	JLabel cliente;
	JLabel divida;
	
	int cpf = -1;
	String [] jl;
	
	private ListaEncClient listadeClientes;

	public GuiClientePgDivida(ListaEncClient alistCli) {
		this.listadeClientes = alistCli;
		cpf = -1;
		
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);  //vertical
        this.setLayout(boxlayout);       

        JLabel label = new JLabel("Pagar Divida");
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

        
//        panelV.setBackground(cor.red);
//        panelFlow2.setBackground(cor.blue);
//        panelV.setPreferredSize(new Dimension(10,10));
//        panelV.setSize(new Dimension(10,10));
        panelV.setMaximumSize(new Dimension(3000,40));
        panelV.setMinimumSize(new Dimension(this.getWidth(),40));
        this.add(panelV);
        this.add(panelFlow2,FlowLayout.CENTER);

	        
//        String po = this.dividas.toString();
        this.jl = this.listadeClientes.comDivida();
//        System.out.println(String.valueOf(jl));
        
        this.jb = new JComboBox<Object>(jl);
        
        this.jb.addActionListener(new ActionJcomb());
        this.jb.setMaximumSize(new Dimension(3000,40));
        this.jb.setMinimumSize(new Dimension(10,40));
        
        JPanel grid = new JPanel();
//        grid.setLayout(new GridLayout(2,2));    
        
        JLabel nome = new JLabel("Cliente");
        nome.setFont(new Font("Serif", Font.PLAIN, 10));
        nome.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        cliente= new JLabel("");
        cliente.setFont(new Font("Serif", Font.BOLD, 15));
        cliente.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel labDivida = new JLabel("Divida");
        labDivida.setFont(new Font("Serif", Font.PLAIN, 10));
        labDivida.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        divida= new JLabel("");
        divida.setFont(new Font("Serif", Font.BOLD, 15));
        divida.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(nome);
        grid.add(cliente);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labDivida);
        grid.add(divida);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        quitar = new Jbotao("Zerar divida");
        quitar.setAlignmentX(JButton.CENTER_ALIGNMENT);
        quitar.addActionListener(new QuitaDivida());
        grid.add(quitar);
        
        BoxLayout box = new BoxLayout(grid, BoxLayout.Y_AXIS);
        grid.setLayout(box);
        
        JPanel borderO = new JPanel();
        borderO.setLayout(new BorderLayout());
        borderO.add(jb,BorderLayout.NORTH);
        borderO.add(grid,BorderLayout.CENTER);
//        borderO.add(grid2,BorderLayout.WEST);
        
        JLabel cpfs = new JLabel("Cpf");
        cpfs.setFont(new Font("Serif", Font.ITALIC, 15));
        
        this.add(cpfs);
        this.add(borderO);
        
//        this.add(label);
       
	}
	
	public void setTelas(Container c, CardLayout card) {
		this.c = c;
		this.card = card;
	}
	
	public void atualizaDivida() {
		this.jb.removeAllItems();
		this.listadeClientes.carregaClientes();
		this.jl = this.listadeClientes.comDivida();
	
		for(int i = 0; i<jl.length; i++) {
			jb.insertItemAt(jl[i], i);
		}
	}
	
	class ActionJcomb implements ActionListener{

        
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<?> cb =(JComboBox<?>) e.getSource();
			
			if(cb.getItemCount() > 0) {
		        String selec = (String) cb.getSelectedItem ();
		        
		        cpf = Integer.parseInt(selec);
		        
		        cliente.setText(listadeClientes.achaClienteNome(cpf));
		        
		        Double divd = listadeClientes.achaClienteDivida(cpf);
		        divida.setText(divd.toString());
			}
	        
			
		}
	}
	
	class ProxTela implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicado = (JButton) e.getSource();
//			String nome = clicado.getActionCommand();
			if(clicado == voltar) {
				card.previous(c);
			
			}
		}
	}
	
	class QuitaDivida implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicado = (JButton) e.getSource();
			
			System.out.println(jb.getItemCount());
			if (clicado == quitar && cpf != -1) {
//				System.out.println(jb.getItemCount());
				listadeClientes.zeraDivida(cpf);
				listadeClientes.salvaClientes();
				if(jb.getItemCount() == 1) {
					divida.setText("");
					cliente.setText("");
					jb.removeAllItems();
					
					//
				}else {
					jb.removeItemAt(jb.getSelectedIndex());
				}
				cpf= -1;
				
			}
			
		}
		
	}
}