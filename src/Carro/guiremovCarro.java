package Carro;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Menu.Jbotao;
import noGui.ListaEncCarro;





@SuppressWarnings("serial")
public class guiremovCarro extends JPanel{
	Container c;
	CardLayout card;
	Jbotao voltar;
	Jbotao removerCarro;
	JComboBox<Object> jb;
	
	int [] disponivels;
	JLabel labModelo;
	JLabel disponivel;
	
	String placa;
	String [] jl;
	
	private ListaEncCarro listCar;

	public guiremovCarro(ListaEncCarro listCar) {
		this.listCar = listCar;

        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);  //vertical
        this.setLayout(boxlayout);       

        JLabel label = new JLabel("Remover carro");
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
     
        this.jl = this.listCar.TdCarros();
        
        this.jb = new JComboBox<Object>(jl);
        
        this.jb.addActionListener(new ActionJcomb());
        this.jb.setMaximumSize(new Dimension(3000,40));
        this.jb.setMinimumSize(new Dimension(10,40));
        
        JPanel grid = new JPanel();


        JLabel nome = new JLabel("Modelo");
        nome.setFont(new Font("Serif", Font.PLAIN, 10));
        nome.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        labModelo= new JLabel("");
        labModelo.setFont(new Font("Serif", Font.BOLD, 15));
        labModelo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel labdisponivel = new JLabel("Disponibilidade");
        labdisponivel.setFont(new Font("Serif", Font.PLAIN, 10));
        labdisponivel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        disponivel= new JLabel("");
        disponivel.setFont(new Font("Serif", Font.BOLD, 15));
        disponivel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(nome);
        grid.add(labModelo);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labdisponivel);
        grid.add(disponivel);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        removerCarro = new Jbotao("remover");
        removerCarro.setAlignmentX(JButton.CENTER_ALIGNMENT);
        removerCarro.addActionListener(new actionRemoveCarro());
        grid.add(removerCarro);
        
        BoxLayout box = new BoxLayout(grid, BoxLayout.Y_AXIS);
        grid.setLayout(box);
        
        JPanel borderO = new JPanel();
        borderO.setLayout(new BorderLayout());
        borderO.add(jb,BorderLayout.NORTH);
        borderO.add(grid,BorderLayout.CENTER);
        
        JLabel cpfs = new JLabel("placa");
        cpfs.setFont(new Font("Serif", Font.ITALIC, 15));
        
        this.add(cpfs);
        this.add(borderO);
           
	}
	
	public void setTelas(Container c, CardLayout card) {
		this.c = c;
		this.card = card;
	}
	
	public void atualizaJcomb() {

		this.jb.removeAllItems();
		this.listCar.carregaCarros();;
		this.jl = this.listCar.TdCarros();
	
		for(int i = 0; i<jl.length; i++) {
			jb.insertItemAt(jl[i], i);
		}
	}
	
	class ActionJcomb implements ActionListener{

        
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<?> cb =(JComboBox<?>) e.getSource();
			
			if(cb.getItemCount() > 0) {
				placa = (String) cb.getSelectedItem ();
		        
		        labModelo.setText(listCar.achaModelo(placa));
		        
		        Boolean divd = listCar.achaDisponibilidade(placa);
		        disponivel.setText(divd.toString());
			}
	        
			
		}
	}
	
	class ProxTela implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicado = (JButton) e.getSource();
//			String nome = clicado.getActionCommand();
			if(clicado == voltar) {
				labModelo.setText("");
				disponivel.setText("");
				card.show(c,"carro");
			
			}
		}
	}
	
	class actionRemoveCarro implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			System.out.println(jb.getItemCount());
			System.out.println(placa);
			if (placa.compareTo("$") != 0) {
				System.out.println("if OK");
				
				if(listCar.removeLista(placa)) {
					if(jb.getItemCount() == 1) {
						disponivel.setText("");
						labModelo.setText("");
						jb.removeAllItems();
						
						//
					}else {
						jb.removeItemAt(jb.getSelectedIndex());
						disponivel.setText("");
						labModelo.setText("");
					}
					placa = "$";
				}else {
					JOptionPane.showMessageDialog(null,"Carro alugado","Não removido",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			
		}
		
	}
}

