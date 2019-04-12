package Aluguel;

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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import org.joda.time.LocalDate;

import noGui.ListaAluguel;
import noGui.ListaEncCarro;
import noGui.ListaEncClient;
import Menu.Jbotao;



@SuppressWarnings("serial")
public class GuiFinalizarAluguel extends JPanel{
	Container c;
	CardLayout card;
	Jbotao voltar;
	Jbotao jbFinalizar;
	JComboBox<Object> jcbPlaca;
	
	int [] labModelos;
	JLabel labCliente;
	JLabel labModelo;
	
	int cpf=0;
	String placa = new String("");
	String [] todocar;
	
	private ListaEncCarro listCar;
	private ListaEncClient listCli;
	ListaAluguel listAluguel;
	
	JRadioButtonMenuItem cb1;
	JRadioButtonMenuItem cb2;
	JTextField tst;
	

	public GuiFinalizarAluguel(ListaEncCarro listCar, ListaEncClient listCli, ListaAluguel listAluguel) {
		this.listCar = listCar;
		this.listCli = listCli;
		this.listAluguel = listAluguel;

        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);  //vertical
        this.setLayout(boxlayout);       

        JLabel label = new JLabel("Finalizar aluguel");
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
     
        this.todocar = this.listCar.TdCarrosIndispo();
        
        this.jcbPlaca = new JComboBox<Object>(todocar);
        this.jcbPlaca.addActionListener(new ActionJcomb());
        this.jcbPlaca.setMaximumSize(new Dimension(3000,40));
        this.jcbPlaca.setMinimumSize(new Dimension(10,40));
   
        JPanel grid = new JPanel();


        JLabel nome = new JLabel("Cliente");
        nome.setFont(new Font("Serif", Font.PLAIN, 10));
        nome.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        labCliente= new JLabel("");
        labCliente.setFont(new Font("Serif", Font.BOLD, 15));
        labCliente.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel lablabModelo = new JLabel("Modelo");
        lablabModelo.setFont(new Font("Serif", Font.PLAIN, 10));
        lablabModelo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        labModelo= new JLabel("");
        labModelo.setFont(new Font("Serif", Font.BOLD, 15));
        labModelo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(nome);
        grid.add(labCliente);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(lablabModelo);
        grid.add(labModelo);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));

        BoxLayout box = new BoxLayout(grid, BoxLayout.Y_AXIS);
        grid.setLayout(box);
        
        JLabel lbPlaca = new JLabel("Placa");
        lbPlaca.setFont(new Font("Serif", Font.ITALIC, 10));
        lbPlaca.setAlignmentX(JLabel.CENTER_ALIGNMENT);
           

        
        JPanel borderO = new JPanel();
        borderO.setLayout(new BorderLayout());
        borderO.add(jcbPlaca,BorderLayout.NORTH);     
        borderO.add(grid,BorderLayout.CENTER);
        
        //pagar por km ou dia
        
        JPanel btgroup = new JPanel();
        BoxLayout boxbt = new BoxLayout(btgroup, BoxLayout.Y_AXIS);
        btgroup.setLayout(boxbt);
        
        cb1 = new JRadioButtonMenuItem("KM", false);
        cb1.setAlignmentX(JRadioButtonMenuItem.CENTER);
        cb2 = new JRadioButtonMenuItem("Dia", false);
        cb2.setAlignmentX(JRadioButtonMenuItem.CENTER);
        
        ButtonGroup group = new ButtonGroup();
        
        cb1.addActionListener(new Actionradio());
        cb2.addActionListener(new Actionradio());
        
        group.add(cb1);
        group.add(cb2);
        
        
        tst = new JTextField();
        tst.setMinimumSize(new Dimension(100,20));
        tst.setMaximumSize(new Dimension(200,25));
 

        btgroup.add(cb1);
        btgroup.add(cb2);
        
        JLabel kmrodados = new JLabel("Km rodados");
        kmrodados.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        btgroup.add(Box.createRigidArea(new Dimension(0, 20)));
        btgroup.add(kmrodados);
        btgroup.add(tst);
        btgroup.add(Box.createRigidArea(new Dimension(0, 20)));
        
        
        //opcoes de pagamento
        
        jbFinalizar = new Jbotao("Finaliza");
        jbFinalizar.setAlignmentX(JButton.CENTER_ALIGNMENT);
        jbFinalizar.addActionListener(new Actionfinalizar());
       
        
        this.add(borderO);
        this.add(btgroup);
        
        this.add(jbFinalizar);
        
           
	}
	
	public void setTelas(Container c, CardLayout card) {
		this.c = c;
		this.card = card;
	}
	
	public void atualizaJcomb() {

		this.jcbPlaca.removeAllItems();
		this.listCar.carregaCarros();
		this.todocar = this.listCar.TdCarrosIndispo();

	
		for(int i = 0; i<todocar.length; i++) {
			jcbPlaca.insertItemAt(todocar[i], i);
		}
	}
	
	class ActionJcomb implements ActionListener{

        
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<?> cb =(JComboBox<?>) e.getSource();
			if(cb.getItemCount() > 0) {
				if(cb ==jcbPlaca) {
					placa = (String) cb.getSelectedItem ();
					cpf = listAluguel.retornaCpf(placa);
					
					
					labCliente.setText(listCli.achaClienteNome(cpf));
					labModelo.setText(listCar.achaModelo(placa));
				}
			}
		}
	}
	
	class ProxTela implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicado = (JButton) e.getSource();
			if(clicado == voltar) {
				labCliente.setText("");
				labModelo.setText("");
				card.show(c,"aluguel");
			
			}
		}
	}
	
	class Actionfinalizar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(tst.isEnabled()) {
				if(listAluguel.Devolucao(placa, Double.parseDouble(tst.getText()))) {
					JOptionPane.showMessageDialog(null,"Aluguel finalizado, divida adicionada a conta do cliente","Sucesso",JOptionPane.INFORMATION_MESSAGE);
					if(jcbPlaca.getItemCount() == 1) {
						jcbPlaca.removeAllItems();
					}else {
						jcbPlaca.removeItemAt(jcbPlaca.getSelectedIndex());
					}
				}else {
					JOptionPane.showMessageDialog(null,"Aluguel não finalizado","Erro",JOptionPane.INFORMATION_MESSAGE);
				}
			}else {
				LocalDate localDate = new LocalDate();
				if(listAluguel.Devolucao(placa, localDate.getYear(),localDate.getMonthOfYear(),localDate.getDayOfMonth())) {
					JOptionPane.showMessageDialog(null,"Aluguel finalizado, divida adicionada a conta do cliente","Sucesso",JOptionPane.INFORMATION_MESSAGE);
					if(jcbPlaca.getItemCount() == 1) {
						jcbPlaca.removeAllItems();
					}else {
						jcbPlaca.removeItemAt(jcbPlaca.getSelectedIndex());
					}
				}else {
					JOptionPane.showMessageDialog(null,"Aluguel não finalizado","Erro",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			
			
		}
		
	}
	
	class Actionradio implements ActionListener{

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButtonMenuItem clicado =(JRadioButtonMenuItem) e.getSource();
			
			if(clicado == cb1) {
				System.out.println("KM");
				tst.enable();
				
			}else if(clicado == cb2) {
				System.out.println("Dias");
				tst.disable();
			}
			
		}
		
	}
	
}

