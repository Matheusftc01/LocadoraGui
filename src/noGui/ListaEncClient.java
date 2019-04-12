package noGui;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ListaEncClient {
	Cliente one;
	int qtd;
	
	public ListaEncClient() {
		this.one = null;
	}
	
	public void insereLista(String nome, int cpf, int telefone, String end, double divida) {
		if(this.verificaCpf(cpf) == 0) {
			if(this.one == null) {
				this.one = new Cliente(nome,cpf,telefone,end,divida);
			}else {// insere no inicio
				Cliente novo = new Cliente(nome,cpf,telefone,end,divida);
				novo.prox = one;
				one = novo;
			}
			this.qtd++;
			this.salvaClientes();
		}else {
			System.out.print("->Cliente ja possui cadastro\n");
		}
		
	}
	
	public int verificaCpf(int cpf) {
		Cliente tmp = this.one;
		
		while(tmp!=null) {
			if(tmp.getCpf() == cpf) {
				return 1;
			}
			tmp = tmp.prox;
		}
		return 0;
	}
	public void mostraLista() {
		if(this.one == null) {
			System.out.print("->Lista vazia\n");
		}else {
			Cliente temp = one;
			System.out.print("\n");
			while(temp != null) {
				System.out.print(temp.mostraDados());
				temp = temp.prox;
			}
			
		}
	}
	
	public String[][] ShowClientesStruct() {
		String[][] data = new String[qtd][5];
		int  i = 0;
		if(this.one == null) {
			System.out.print("->Lista vazia\n");
		}else {
			Cliente temp = one;
			System.out.print("\n");
			while(temp != null) {
				data[i][0] = temp.getNome().toString();
				data[i][1] = Integer.toString(temp.getCpf());
				data[i][2] = Double.toString(temp.getDivida());
				data[i][3] = Integer.toString(temp.getTelefone());
				data[i][4] = temp.getEndereco();
				
				i++;
				temp = temp.prox;
			}
			return data;
		}
		
		return null;
	}
	
	private int removeListaRecurs(Cliente temp, int cpf, int cont) {
		if(temp != null) {
			
			cont = this.removeListaRecurs(temp.prox, cpf, cont);
			if(temp.getCpf() == cpf) {		// placa do carro igual a placa de procura
				temp.setCpf(-1);	//marcao para remover na hora de salvar no arquivo
				return cont+1;
			}
			
		}	
		return cont;
	}
	public boolean removeLista(int cpf) { // remove por placa
		
		if(this.one == null) {
			System.out.print("->Lista vazia\n");
			return false;
		}else {
			System.out.print("->Removido: "+this.achaCliente(cpf));
			int cont = this.removeListaRecurs(this.one, cpf, 0);
			System.out.print("->Quantidade de removidos: "+cont); 
			this.salvaClientes();
			this.carregaClientes();
			return true;
			
		}
	}
	public void salvaClientes(){
		try {
			FileWriter arq = new FileWriter("clientes.txt");
			PrintWriter gravarArq = new PrintWriter(arq);	 
			
			Cliente temp = this.one;
			
			if(temp!=null) {
				while(temp != null) {
					if(temp.getCpf() != -1) {
						gravarArq.printf(temp.mostraDados());
					}
					temp = temp.prox;
				}
			}else {
				gravarArq.printf("");
			}
			arq.close();
		} catch (IOException e) {
			System.out.print("->Deu erro na hora de salvar os clientes");
		}
		
	}
	public void carregaClientes(){
		this.one = null;
		this.qtd = 0;
		try {
			BufferedReader arq = new BufferedReader(new FileReader("clientes.txt"));
	         while(arq.ready()){
	            String linha = arq.readLine();

	            String[] vet = linha.split("[ ]");

//	            System.out.printf("%s ",vet[1]);// nome
//	            System.out.printf("%s ",vet[3]);// cpf
//	            System.out.printf("%s ",vet[5]);// endereco
//	            System.out.printf("%s ",vet[7]);// telefone
//	            System.out.printf("%s\n",vet[9]);// divida
	           
	            int cpf = Integer.parseInt(vet[3]);
	            int telefone = Integer.parseInt(vet[7]);
	            double divida = Double.parseDouble(vet[9]);
	            
	            this.insereLista(vet[1],cpf,telefone,vet[5],divida);	           
	         }
	         arq.close();
	         System.out.println("Clientes carregados");

		} catch (IOException e) {
			System.out.print("->Erro na leitura dos clientes");
		}
	}
	
	public Cliente retornaCliente(int cpf) {
		Cliente tmp = this.one;
		
		while (tmp!=null){
			if(tmp.getCpf() == cpf) {
				if(tmp.getDivida() == 0) {
					return tmp;
				}else {
					System.out.print("->Cliente com divida");
					return null;
				}
				
			}
			tmp = tmp.prox;
		}
		System.out.print("->Cliente nao cadastrado\n");
		return null;
	}
	
	public Cliente retornaClienteIgnoreDivida(int cpf) {
		Cliente tmp = this.one;
		
		while (tmp!=null){
			if(tmp.getCpf() == cpf) {
				return tmp;
			}
			tmp = tmp.prox;
		}
		System.out.print("->Cliente nao cadastrado\n");
		return null;
	}
	
	public void pagaDivida(int cpf, double valor) {
		Cliente tmp = this.one;
		
		while (tmp != null) {
			if(tmp.getCpf() == cpf) {
				tmp.pagaDivida(valor);
				this.salvaClientes();
				return;
			}
			tmp = tmp.prox;
		}
	}
	
	public String achaCliente(int cpf) {
		Cliente tmp = this.one;
		
		while(tmp!=null) {
			if(tmp.getCpf() == cpf) {
				return tmp.mostraDados();
			
			}
			tmp = tmp.prox;
		}
		return "->Cliente nao encontrado\n";
		
	}
	
	public String achaClienteNome(int cpf) {
		Cliente tmp = this.one;
		
		while(tmp!=null) {
			if(tmp.getCpf() == cpf) {
				return tmp.getNome();
			}
			tmp = tmp.prox;
		}
		return "Not found";
	}
	
	public double achaClienteDivida(int cpf) {
		Cliente tmp = this.one;
		
		while(tmp!=null) {
			if(tmp.getCpf() == cpf) {
				return tmp.getDivida();
			
			}
			tmp = tmp.prox;
		}
		return 0.0;
	}
	
	public String[] comDivida() {
		Cliente tmp = this.one;
//		String[] dvd = new String[this.qtd]; 
		java.util.List<String> dvd = new ArrayList<String>();
		
		while (tmp!=null){
			if(tmp.getDivida() != 0) {
				System.out.println(Integer.toString(tmp.getCpf()));
				dvd.add(Integer.toString(tmp.getCpf()));
			}
			tmp = tmp.prox;
		}
		System.out.println("Metodo comDivida");
		String[] simpleArray = new String[dvd.size()];
		dvd.toArray( simpleArray );
		return simpleArray;
	}
	
	public void zeraDivida(int cpf) {
		Cliente tmp = this.one;
		
		while(tmp!=null) {
			if(tmp.getCpf() == cpf) {
				tmp.setDivida(0);
			}
			tmp = tmp.prox;
		}
	}
	
	public String[] TdClientes() {
		Cliente tmp = this.one; 
		java.util.List<String> dvd = new ArrayList<String>();
		
		while (tmp!=null){
//			System.out.println(Integer.toString(tmp.getCpf()));
			dvd.add(Integer.toString(tmp.getCpf()));
			tmp = tmp.prox;
		};
		String[] simpleArray = new String[dvd.size()];
		dvd.toArray( simpleArray );
		return simpleArray;
	}
	
	public String[] TdClientesLimpo() {
		Cliente tmp = this.one; 
		java.util.List<String> dvd = new ArrayList<String>();
		
		while (tmp!=null){
			if(tmp.getDivida() == 0) {
				dvd.add(Integer.toString(tmp.getCpf()));
			}
				
			tmp = tmp.prox;
		};
		String[] simpleArray = new String[dvd.size()];
		dvd.toArray( simpleArray );
		return simpleArray;
	}
}
