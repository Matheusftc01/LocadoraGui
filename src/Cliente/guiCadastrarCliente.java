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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Menu.Jbotao;
import noGui.ListaEncClient;




@SuppressWarnings("serial")
public class guiCadastrarCliente extends JPanel{
	Container c;
	CardLayout card;
	Jbotao voltar;
	Jbotao cadastrar;
	
	int [] dividas;
	JLabel cliente;
	JLabel divida;
	
	JTextField endereco;
	JTextField nome;
	JTextField cpf;
	JTextField telefone;
	
	private ListaEncClient listadeClientes;

	public guiCadastrarCliente(ListaEncClient alistCli) {
		this.listadeClientes = alistCli;
		
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);  //vertical
        this.setLayout(boxlayout);       

        JLabel label = new JLabel("Cadastrar cliente");
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
        JLabel labnome = new JLabel("Nome");
        labnome.setFont(new Font("Serif", Font.PLAIN, 10));
        labnome.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel labcpf = new JLabel("cpf");
        labcpf.setFont(new Font("Serif", Font.PLAIN, 10));
        labcpf.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel labtelefone = new JLabel("Telefone");
        labtelefone.setFont(new Font("Serif", Font.PLAIN, 10));
        labtelefone.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        JLabel labendereco = new JLabel("Endereço");
        labendereco.setFont(new Font("Serif", Font.PLAIN, 10));
        labendereco.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        nome = new JTextField();
        nome.setMinimumSize(new Dimension(100,20));
        nome.setMaximumSize(new Dimension(300,25));
        
        cpf = new JTextField();
        cpf.setMinimumSize(new Dimension(100,20));
        cpf.setMaximumSize(new Dimension(300,25));
        
        telefone = new JTextField();
        telefone.setMinimumSize(new Dimension(100,20));
        telefone.setMaximumSize(new Dimension(300,25));
        
        endereco = new JTextField();
        endereco.setMinimumSize(new Dimension(100,20));
        endereco.setMaximumSize(new Dimension(300,25));
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labnome);
        grid.add(nome);

        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labcpf);
        grid.add(cpf);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labtelefone);
        grid.add(telefone);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        grid.add(labendereco);
        grid.add(endereco);
        
        grid.add(Box.createRigidArea(new Dimension(0, 60)));
        cadastrar = new Jbotao("cadastrar");
        cadastrar.setAlignmentX(JButton.CENTER_ALIGNMENT);
        cadastrar.addActionListener(new ActionCadastrar());
        
        grid.add(cadastrar);
        
        BoxLayout box = new BoxLayout(grid, BoxLayout.Y_AXIS);
        grid.setLayout(box);
        
        JPanel borderO = new JPanel();
        borderO.setLayout(new BorderLayout());
    
        borderO.add(grid,BorderLayout.CENTER);
//        borderO.add(grid2,BorderLayout.WEST);
        


        this.add(borderO);
        
       
	}
	
	public void setTelas(Container c, CardLayout card) {
		this.c = c;
		this.card = card;
	}
	
	
	
	
	
	class ProxTela implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicado = (JButton) e.getSource();
//			String labnome = clicado.getActionCommand();
			if(clicado == voltar) {
				card.show(c,"cliente");
			
			}
		}
	}
	
	class ActionCadastrar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int acpf = Integer.parseInt(cpf.getText());
			
			if(listadeClientes.verificaCpf(acpf) == 1) {
				JOptionPane.showMessageDialog(null,"Cliente Ja cadastrado com esse cpf","Erro",JOptionPane.INFORMATION_MESSAGE);
			}else {
				if(nome.getText().length() > 0) {
					int end = Integer.parseInt(telefone.getText());
					listadeClientes.insereLista(nome.getText(), acpf,end, endereco.getText(), 0.0);
					JOptionPane.showMessageDialog(null,"Cliente inserido","Sucesso",JOptionPane.INFORMATION_MESSAGE);
					nome.setText("");
					cpf.setText("");
					endereco.setText("");
					telefone.setText("");
				}
			}
			
		}
		
	}
	
	
}
