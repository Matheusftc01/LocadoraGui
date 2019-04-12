package Menu;
import java.awt.*;  
import java.awt.CardLayout;
import java.awt.event.*;  
  
import javax.swing.*;

import Aluguel.GuiFinalizarAluguel;
import Aluguel.GuiNovoAluguel;
import Aluguel.GuiTodosAlugueis;
import Aluguel.guiAluguel;
import Carro.guiCadastrarCarro;
import Carro.guiCarro;
import Carro.guiTodosCarros;
import Carro.guiremovCarro;
import Cliente.GuiClientePgDivida;
import Cliente.guiCadastrarCliente;
import Cliente.guiCliente;
import Cliente.guiRemoverCLiente;
import Cliente.guiTodosClientes;
import noGui.ListaAluguel;
import noGui.ListaEncCarro;
import noGui.ListaEncClient;

  
@SuppressWarnings("serial")
public class GerenciaTela extends JFrame implements ActionListener{  
		CardLayout card;   
		Container c;
		//telas
		Menu menu;
		guiCliente cliente;
		guiCarro carro;
		guiAluguel aluguel;
		
		//telas cliente
		GuiClientePgDivida pgDivida;
		guiTodosClientes showClientes;
		guiRemoverCLiente rmCliente;
		guiCadastrarCliente cdCliente;
		
		//telas carro
		guiremovCarro rmCarro;
		guiTodosCarros guiTdCarro;
		guiCadastrarCarro guicdCarro;
		
		
		//telas aluguel
		GuiNovoAluguel nvAluguel;
		GuiTodosAlugueis tdAluguel;
		GuiFinalizarAluguel fnAluguel;
		
		private ListaEncClient listCli;
		private ListaEncCarro listCar;
		private ListaAluguel listAluguel;
		
		
		public GerenciaTela(){  
			this.setTitle("Locadora");
			
			this.loadClientes();
			this.loadCarros();
			this.loadAluguel();
			
			c=getContentPane();
			System.out.println(getContentPane());
			card=new CardLayout(this.getWidth(), this.getHeight());  
			//create CardLayout object with 40 hor space and 30 ver space  
			c.setLayout(card);  

			
			
			menu = new Menu();
			menu.setTelas(c, card);
			
			
			//cliente
			pgDivida = new GuiClientePgDivida(this.listCli);
			pgDivida.setTelas(c, card);
			
			showClientes = new guiTodosClientes(this.listCli);
			showClientes.setTelas(c, card);
			
			rmCliente = new guiRemoverCLiente(this.listCli);
			rmCliente.setTelas(c, card);
			
			cdCliente = new guiCadastrarCliente(this.listCli);
			cdCliente.setTelas(c, card);
			
			cliente = new guiCliente(showClientes,pgDivida,rmCliente);
			cliente.setTelas(c, card);
			
			
			
			c.add("menu",menu); 
			c.add("cliente", cliente);
			//cliente
			c.add("pgDivida",pgDivida);
			c.add("showClientes", showClientes);
			c.add("rmCliente",rmCliente);
			c.add("cdCliente",cdCliente);
			
			//carro
			
			
			rmCarro = new guiremovCarro(this.listCar);
			rmCarro.setTelas(c, card);
			
			guiTdCarro = new guiTodosCarros(this.listCar);
			guiTdCarro.setTelas(c, card);
			
			guicdCarro = new guiCadastrarCarro(this.listCar);
			guicdCarro.setTelas(c, card);
			
			
			carro = new guiCarro(rmCarro, guiTdCarro);
			carro.setTelas(c, card);
			
			c.add("carro",carro);
			c.add("rmCarro",rmCarro);
			c.add("guiTdCarro",guiTdCarro);
			c.add("guicdCarro",guicdCarro);
			
			//aluguel
			
			nvAluguel = new GuiNovoAluguel(this.listCar, this.listCli, this.listAluguel);
			nvAluguel.setTelas(c, card);

			tdAluguel = new GuiTodosAlugueis(listAluguel);
			tdAluguel.setTelas(c, card);
			
			fnAluguel = new GuiFinalizarAluguel(this.listCar, this.listCli, this.listAluguel);
			fnAluguel.setTelas(c, card);
			
			aluguel = new guiAluguel(nvAluguel,tdAluguel,fnAluguel);
			aluguel.setTelas(c, card);
			
			c.add("aluguel",aluguel);
			c.add("nvAluguel", nvAluguel);
			c.add("tdAlugueis", tdAluguel);
			c.add("fnAluguel",fnAluguel);
			
			this.setSize(600,600);  
//			this.pack();
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
                          
		}

		public void loadClientes() {
			this.listCli = new ListaEncClient();
			this.listCli.carregaClientes();
			
		}
		
		public void loadCarros() {
			this.listCar = new ListaEncCarro();
			this.listCar.carregaCarros();	
		}
		
		public void loadAluguel() {
			this.listAluguel = new ListaAluguel(this.listCli, this.listCar);
			this.listAluguel.carregaAlugueisArq();
		}
		
		public void actionPerformed(ActionEvent e) {  
			card.next(c);
		} 	
}  