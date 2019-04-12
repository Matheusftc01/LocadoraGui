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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.joda.time.LocalDate;

import noGui.ListaAluguel;
import noGui.ListaEncCarro;
import noGui.ListaEncClient;
import Menu.Jbotao;





@SuppressWarnings("serial")
public class GuiNovoAluguel extends JPanel{
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
	
	private String[] todoCpf;
	private JComboBox<Object> jcbCpf;
	

	public GuiNovoAluguel(ListaEncCarro listCar, ListaEncClient listCli, ListaAluguel listAluguel) {
		this.listCar = listCar;
		this.listCli = listCli;
		this.listAluguel = listAluguel;

        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);  //vertical
        this.setLayout(boxlayout);       

        JLabel label = new JLabel("Novo aluguel");
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
     
        this.todocar = this.listCar.TdCarrosDispo();
        
        this.jcbPlaca = new JComboBox<Object>(todocar);
        this.jcbPlaca.addActionListener(new ActionJcomb());
        this.jcbPlaca.setMaximumSize(new Dimension(3000,40));
        this.jcbPlaca.setMinimumSize(new Dimension(10,40));
        
        this.todoCpf = this.listCli.TdClientesLimpo();
        
        this.jcbCpf = new JComboBox<Object>(todoCpf);       
        this.jcbCpf.addActionListener(new ActionJcomb());
        this.jcbCpf.setMaximumSize(new Dimension(3000,40));
        this.jcbCpf.setMinimumSize(new Dimension(10,40));
        
    
        
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
        jbFinalizar = new Jbotao("Finaliza");
        jbFinalizar.setAlignmentX(JButton.CENTER_ALIGNMENT);
        jbFinalizar.addActionListener(new Actionfinalizar());
        grid.add(jbFinalizar);
        
        BoxLayout box = new BoxLayout(grid, BoxLayout.Y_AXIS);
        grid.setLayout(box);
        
        JLabel lbPlaca = new JLabel("Placa");
        lbPlaca.setFont(new Font("Serif", Font.ITALIC, 10));
        lbPlaca.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel lbcpf = new JLabel("Cpf");
        lbcpf.setFont(new Font("Serif", Font.ITALIC, 10));
        lbcpf.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JPanel barra = new JPanel();
        
        barra.add(lbcpf);
        barra.add(jcbCpf);
        barra.add(Box.createRigidArea(new Dimension(0, 10)));
        
        barra.add(lbPlaca);
        barra.add(jcbPlaca);
        BoxLayout boxBarra = new BoxLayout(barra, BoxLayout.Y_AXIS);
        barra.setLayout(boxBarra);
        
        JPanel borderO = new JPanel();
        borderO.setLayout(new BorderLayout());
        borderO.add(barra,BorderLayout.NORTH);     
        borderO.add(grid,BorderLayout.CENTER);
        
        this.add(borderO);
           
	}
	
	public void setTelas(Container c, CardLayout card) {
		this.c = c;
		this.card = card;
	}
	
	public void atualizaJcomb() {

		this.jcbPlaca.removeAllItems();
		this.listCar.carregaCarros();;
		this.todocar = this.listCar.TdCarrosDispo();
		
		this.jcbCpf.removeAllItems();
		this.listCli.carregaClientes();;
		this.todoCpf = this.listCli.TdClientesLimpo();
	
		for(int i = 0; i<todocar.length; i++) {
			jcbPlaca.insertItemAt(todocar[i], i);
		}
		
		for(int i = 0; i<todoCpf.length; i++) {
			jcbCpf.insertItemAt(todoCpf[i], i);
		}
	}
	
	class ActionJcomb implements ActionListener{

        
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<?> cb =(JComboBox<?>) e.getSource();
			if(cb.getItemCount() > 0) {
				if(cb == jcbCpf) {	
					String a = (String) cb.getSelectedItem();
					cpf = Integer.parseInt(a);
			        labCliente.setText(listCli.achaClienteNome(cpf));
				}
				else if(cb ==jcbPlaca) {
					placa = (String) cb.getSelectedItem ();
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
			int erro = 0;
			if(placa.length() <= 0 ) {
				JOptionPane.showMessageDialog(null,"selecione um veiculo","Erro",JOptionPane.INFORMATION_MESSAGE);
				erro = 1;
			}
			if(cpf == 0) {
				JOptionPane.showMessageDialog(null,"selecione um cliente","Erro",JOptionPane.INFORMATION_MESSAGE);
				erro = 1;
			}
			if(erro == 0) {
				LocalDate localDate = new LocalDate();
				if(listAluguel.novoAluguel(cpf, placa,localDate.getYear(),localDate.getMonthOfYear(),localDate.getDayOfMonth())) {
					JOptionPane.showMessageDialog(null,"Aluguel realizado","Sucesso",JOptionPane.INFORMATION_MESSAGE);
					listCar.salvaCarros();
					listCar.carregaCarros();
					
//					if(jcbCpf.getItemCount() == 1) {
//						jcbCpf.removeAllItems();
//					}else {
//						jcbCpf.removeItemAt(jcbCpf.getSelectedIndex());
//					}

					if(jcbPlaca.getItemCount() == 1) {
						jcbPlaca.removeAllItems();
					}else {
						jcbPlaca.removeItemAt(jcbPlaca.getSelectedIndex());
					}
				}else {
					JOptionPane.showMessageDialog(null,"Aluguel não realizado","Erro",JOptionPane.INFORMATION_MESSAGE);
				}
				
				labModelo.setText("");
				
				
			}
			
		}
		
	}
	
}

