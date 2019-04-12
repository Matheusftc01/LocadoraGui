package Cliente;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;

import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Menu.Jbotao;
import Menu.Menu;




@SuppressWarnings("serial")
public class guiCliente extends JPanel{
	Menu menu;
	Container c;
	CardLayout card;
	
	Jbotao cdCliente;
	Jbotao rmCliente;
	Jbotao pgDivida;
	Jbotao showClientes;
	Jbotao voltar;
	
	guiTodosClientes guiClie;
	GuiClientePgDivida pagdivida;
	guiRemoverCLiente pagrmClientes;
	
	public guiCliente(guiTodosClientes guiClie, GuiClientePgDivida pagdivida, guiRemoverCLiente pagrmClientes){
		this.guiClie = guiClie;
		this.pagdivida = pagdivida;
		this.pagrmClientes = pagrmClientes;
	
//		this.setBackground();
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);  //vertical
         

        this.setLayout(boxlayout);
         
        // setando a borda
//        this.setBorder(new EmptyBorder(new Insets(100, 150, 100, 150)));
        //this.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));     
         
        // Define new buttons
        cdCliente = new Jbotao("Cadastrar");
        cdCliente.addActionListener(new ProxTela());
        
        rmCliente = new Jbotao("Remover");
        rmCliente.addActionListener(new ProxTela());
        
        pgDivida = new Jbotao("Pagar divida");
        pgDivida.addActionListener(new ProxTela());
        
        showClientes = new Jbotao("Mostrar todos");
        showClientes.addActionListener(new ProxTela());
        
        JLabel label = new JLabel("Cliente");
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
        grid.add(pgDivida);
        grid.add(rmCliente);
        grid.add(cdCliente);
        grid.add(showClientes);
        
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
//			String nome = clicado.getActionCommand();
			if(clicado == voltar) {
				card.previous(c);
	
			}else if(clicado == pgDivida) {
				card.show(c,"pgDivida");
				pagdivida.atualizaDivida();
				
			}else if(clicado == showClientes) {
				card.show(c, "showClientes");
				guiClie.atualizaTable();
			}else if(clicado == rmCliente) {
				card.show(c, "rmCliente");
				pagrmClientes.atualizaJcomb();
			}else if(clicado == cdCliente) {
				card.show(c, "cdCliente");
			}
		}
	}
	
}

