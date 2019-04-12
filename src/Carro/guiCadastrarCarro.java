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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Menu.Jbotao;
import noGui.ListaEncCarro;





@SuppressWarnings("serial")
public class guiCadastrarCarro extends JPanel{
	Container c;
	CardLayout card;
	Jbotao voltar;
	Jbotao cadastrar;
	
	int [] dividas;
	JLabel cliente;
	JLabel divida;
	
	JTextArea placa;
	JTextArea modelo;
	JTextArea descricao;
	JTextArea observacao;
	JTextArea ano;
	JTextArea KM;
	JTextArea taxadia;
	JTextArea taxakm;
	
	private ListaEncCarro listCar;

	public guiCadastrarCarro(ListaEncCarro listCar) {
		this.listCar = listCar;
		
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);  //vertical
        this.setLayout(boxlayout);       

        JLabel label = new JLabel("Cadastrar carro");
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

  
        JPanel grid = new JPanel();

        //labels com indicacoes
        JLabel labPlaca = new JLabel("Placa");
        labPlaca.setFont(new Font("Serif", Font.PLAIN, 10));
        labPlaca.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel labmodelo = new JLabel("Modelo");
        labmodelo.setFont(new Font("Serif", Font.PLAIN, 10));
        labmodelo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel labdesc = new JLabel("Descrição");
        labdesc.setFont(new Font("Serif", Font.PLAIN, 10));
        labdesc.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel labObser = new JLabel("Observação");
        labObser.setFont(new Font("Serif", Font.PLAIN, 10));
        labObser.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel labKm = new JLabel("Km");
        labKm.setFont(new Font("Serif", Font.PLAIN, 10));
        labKm.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel labAno = new JLabel("Ano");
        labAno.setFont(new Font("Serif", Font.PLAIN, 10));
        labAno.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel labtaxadia = new JLabel("Taxa diaria");
        labtaxadia.setFont(new Font("Serif", Font.PLAIN, 10));
        labtaxadia.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel labtaxakm = new JLabel("Taxa km");
        labtaxakm.setFont(new Font("Serif", Font.PLAIN, 10));
        labtaxakm.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        modelo = new JTextArea();
        modelo.setMinimumSize(new Dimension(100,20));
        modelo.setMaximumSize(new Dimension(300,25));
        
        
        descricao = new JTextArea();
        descricao.setMinimumSize(new Dimension(100,20));
        descricao.setMaximumSize(new Dimension(300,25));
        
        observacao = new JTextArea();
        observacao.setMinimumSize(new Dimension(100,20));
        observacao.setMaximumSize(new Dimension(300,25));
        
        placa = new JTextArea();
        placa.setMinimumSize(new Dimension(100,20));
        placa.setMaximumSize(new Dimension(300,25));
        
        KM = new JTextArea();
        KM.setMinimumSize(new Dimension(100,20));
        KM.setMaximumSize(new Dimension(300,25));
        
        ano = new JTextArea();
        ano.setMinimumSize(new Dimension(100,20));
        ano.setMaximumSize(new Dimension(300,25));
        
        taxadia = new JTextArea();
        taxadia.setMinimumSize(new Dimension(100,20));
        taxadia.setMaximumSize(new Dimension(300,25));
        
        taxakm = new JTextArea();
        taxakm.setMinimumSize(new Dimension(100,20));
        taxakm.setMaximumSize(new Dimension(300,25));
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labPlaca);
        grid.add(placa);

        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labmodelo);
        grid.add(modelo);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labdesc);
        grid.add(descricao);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labObser);
        grid.add(observacao);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labKm);
        grid.add(KM);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labAno);
        grid.add(ano);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labtaxadia);
        grid.add(taxadia);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labtaxakm);
        grid.add(taxakm);
        
        
        
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        cadastrar = new Jbotao("cadastrar");
        cadastrar.setAlignmentX(JButton.CENTER_ALIGNMENT);
        cadastrar.addActionListener(new ActionCadastrar());
        
//        grid.add(cadastrar);
        
        BoxLayout box = new BoxLayout(grid, BoxLayout.Y_AXIS);
        grid.setLayout(box);
        
        JScrollPane scrollGrid = new JScrollPane(grid);
        
        JPanel borderO = new JPanel();
        borderO.setLayout(new BorderLayout());
    
        borderO.add(scrollGrid,BorderLayout.CENTER);
//        borderO.add(grid2,BorderLayout.WEST);
        


        this.add(borderO);
        this.add(cadastrar);
        
       
	}
	
	public void setTelas(Container c, CardLayout card) {
		this.c = c;
		this.card = card;
	}
	
	
	
	
	
	class ProxTela implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicado = (JButton) e.getSource();
//			String labPlaca = clicado.getActionCommand();
			if(clicado == voltar) {
				card.show(c,"carro");
			
			}
		}
	}
	
	class ActionCadastrar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int erro = 0;
			if(placa.getText().length() == 0) {
				JOptionPane.showMessageDialog(null,"Insira a placa","ERRO",JOptionPane.INFORMATION_MESSAGE);
				erro = 1;
			}
			if(modelo.getText().length() == 0) {
				JOptionPane.showMessageDialog(null,"Insira o modelo","ERRO",JOptionPane.INFORMATION_MESSAGE);
				erro = 1;
			}
			
			if(taxadia.getText().length() == 0) {
				JOptionPane.showMessageDialog(null,"Insira a taxa diaria","ERRO",JOptionPane.INFORMATION_MESSAGE);
				erro = 1;
			}
			
			if(taxakm.getText().length() == 0) {
				JOptionPane.showMessageDialog(null,"Insira a taxa por KM","ERRO",JOptionPane.INFORMATION_MESSAGE);
				erro = 1;
			}
		
			if(descricao.getText().length() == 0) {
				JOptionPane.showMessageDialog(null,"Insira a descrição","ERRO",JOptionPane.INFORMATION_MESSAGE);
				erro = 1;
			}
			
			if(observacao.getText().length() == 0) {
				JOptionPane.showMessageDialog(null,"Insira a observação","ERRO",JOptionPane.INFORMATION_MESSAGE);
				erro = 1;
			}
			
			if(KM.getText().length() == 0) {
				JOptionPane.showMessageDialog(null,"Insira a KM","ERRO",JOptionPane.INFORMATION_MESSAGE);
				erro = 1;
			}
			
			if(erro == 0) {
				if(listCar.insereLista(placa.getText(), modelo.getText(), descricao.getText(), observacao.getText(), true, Double.parseDouble(KM.getText()), Double.parseDouble(taxadia.getText()), Double.parseDouble(taxakm.getText()), Integer.parseInt(ano.getText()))) {
					JOptionPane.showMessageDialog(null,"Carro inserido","Sucesso",JOptionPane.INFORMATION_MESSAGE);
					
				}else {
					JOptionPane.showMessageDialog(null,"Já possui um carro com essa placa","Erro",JOptionPane.INFORMATION_MESSAGE);
					
				}
				placa.setText("");
				modelo.setText("");
				descricao.setText("");
				observacao.setText("");
				KM.setText("");
				ano.setText("");
				taxadia.setText("");
				taxakm.setText("");
			
			}
			
			
		}
		
	}
	
	
}
