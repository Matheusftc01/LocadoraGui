package noGui;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ListaAluguel {
	
	public Aluguel aluguel;
	ListaEncClient clientes;
	ListaEncCarro carros;
	protected int qtd = 0;
	
	public ListaAluguel(ListaEncClient clientes, ListaEncCarro carros) {		//	recebe as listas de cliente e carros
		this.clientes = clientes;
		this.carros = carros;
		this.aluguel = null;
	}
	
	public boolean novoAluguel(int cpf, String placa, int ano, int mes, int dia) {
		Cliente nCliente = clientes.retornaCliente(cpf);		//	acho o cliente e verifica divida
		Carro nCarro = carros.retornaCarro(placa);				//	acha o carro e verifica disponibilidade
		
		if(nCliente != null && nCarro != null) {				//	insere no inicio		
			Aluguel novo = new Aluguel(nCliente,nCarro, ano, mes, dia);
			novo.prox = aluguel;
			aluguel = novo;
			System.out.print("->Aluguel concluido\n");
			this.salvaAlugueis();
			this.qtd++;
			
			return true;
		}else {													// algum nao encontrado, aluguel nao eh feito
			System.out.print("->Aluguel nao concluido\n");
			
			return false;
		}
		
		
	}
	
	private void carregaAluguel(int cpf, String placa, int ano, int mes, int dia) {
		Cliente nCliente = clientes.retornaClienteIgnoreDivida(cpf);		//	acho o cliente e verifica divida
		Carro nCarro = carros.retornaCarroIgnoreDispo(placa);				//	acha o carro e verifica disponibilidade
		
		if(nCliente != null && nCarro != null) {				//	insere no inicio		
			Aluguel novo = new Aluguel(nCliente,nCarro, ano, mes, dia);
			novo.prox = aluguel;
			aluguel = novo;
			this.qtd++;
			
		}else {													// algum nao encontrado, aluguel nao eh feito
			System.out.print("->Aluguel nao carregado\n");
		}
		
	}
	
	
	public void salvaAlugueis(){
		try {
			FileWriter arq = new FileWriter("Alugueis.txt");
			PrintWriter gravarArq = new PrintWriter(arq);	 
			
			Aluguel temp = this.aluguel;
			if(temp!=null) {
				while(temp != null) {
					if(temp.car.getDisponivel() == false ) {		// so salva se o carro nao esta disponivel
						if(temp.getDelet() == false) {
							gravarArq.printf(temp.mostraInfo());
						}
					}
					temp = temp.prox;
				}
			}else {
				gravarArq.printf("");
			}
			arq.close();
		} catch (IOException e) {
			System.out.print("->Deu erro na hora de salvar os Alugueis\n");
		}
		
	}
	
	public void carregaAlugueisArq(){
		this.aluguel = null;		// lista encadeada fica nula e carrego tudo do arquivo Alugueis.txt
		int cont = 0;
		this.qtd = 0;
		try {
			BufferedReader arq = new BufferedReader(new FileReader("Alugueis.txt"));
	         while(arq.ready()){
	            String linha = arq.readLine();
	            String[] vet = linha.split("[ ]");
	            
	            int cpf = Integer.parseInt(vet[1]);
	            int ano = Integer.parseInt(vet[5]);
	            int mes = Integer.parseInt(vet[7]);
	            int dia = Integer.parseInt(vet[9]);
	            
	            this.carregaAluguel(cpf, vet[3], ano, mes, dia);
	         
	            cont+=1;
	         }
	         System.out.print("-> "+cont+" Aluguel carregado\n");
	         arq.close();
		} catch (IOException e) {
			System.out.print("->Erro na leitura dos Carros\n");
		}
	}
	
	public void mostraAlugueis() {
		Aluguel tmp = this.aluguel;
		
		if(tmp!=null) {
			while(tmp != null) {
				System.out.print(tmp.mostraInfo());
				tmp = tmp.prox;
			}
		}else {
			System.out.print("->Nenhum aluguel\n");
		}
	}
	
	private int removeListaRecurs(Aluguel temp, String placa, int cont) {
		if(temp != null) {
			
			cont = this.removeListaRecurs(temp.prox, placa, cont);
			if(temp.car.getPlaca().compareToIgnoreCase(placa) ==  0) {		// placa do carro igual a placa de procura
				temp.setDelet(true);										//marcao para remover na hora de salvar no arquivo
				return cont+1;
			}
			
		}	
		return cont;
	}
	public boolean removeLista(String placa) { // remove por placa
		
		if(this.aluguel == null) {
			System.out.print("->Lista vazia\n");
			return false;
		}else {
		
			int cont = this.removeListaRecurs(this.aluguel, placa, 0);
			System.out.print("->Quantidade de removidos: "+cont+"\n"); 
			this.salvaAlugueis();
			this.carregaAlugueisArq();
			return true;
			
		}
	}
	
	public void getAluguelPeriodo(int iano, int imes,int fano, int fmes) {
		Aluguel tmp = this.aluguel;
		
		while(tmp != null) {
			if(tmp.getDataInicial().getYear() >= iano && tmp.getDataInicial().getYear() <= fano) {
				if(tmp.getDataInicial().getMonthOfYear() >= imes && tmp.getDataInicial().getMonthOfYear() <= fmes ) {
						System.out.print(tmp.mostraInfo());
				}
			}
			tmp = tmp.prox;
		}
	}
	
	public boolean Devolucao(String placa, double km) {
		Aluguel tmp = this.aluguel;
		
		while(tmp != null) {
			if(tmp.car.getPlaca().compareToIgnoreCase(placa) == 0) {
				if(tmp.devolveCarro(km)) {
					System.out.print("\n\n->RECIBO");
					System.out.print("\n\tCliente: \n\t\tNome: "+tmp.cli.getNome()+"\t\tCPF: "+tmp.cli.getCpf()+"\n\t\tDivida: "+tmp.cli.getDivida()+"\t\tTelefone: "+tmp.cli.getTelefone()+"\n");
					System.out.print("\n\tCarro: \n\t\tPlaca: "+tmp.car.getPlaca()+"\t\tModelo: "+tmp.car.getModelo()+"\n\t\tObsercacoes: "+tmp.car.getObservacoes()+"\tDescricao: "+tmp.car.getDescricao()+"\n");
					this.removeLista(placa);		//	removendo da lista e do arquivos "Alugueis.txt"
					this.clientes.salvaClientes();
					this.carros.salvaCarros();
					
					return true;
				}else {
					System.out.print("->Erro na devolucao\n");
					return false;
				}
			}
			tmp = tmp.prox;
		}
		System.out.print("->Erro na devolucao\n");
		return false;
	}
	
	public boolean Devolucao(String placa, int ano, int mes, int dia) {
		Aluguel tmp = this.aluguel;
		
		while(tmp != null) {
			if(tmp.car.getPlaca().compareToIgnoreCase(placa) == 0) {
				if(tmp.devolveCarro(ano,mes,dia)) {
					System.out.print("\n\n->RECIBO");
					System.out.print("\n\tCliente: \n\t\tNome: "+tmp.cli.getNome()+"\t\tCPF: "+tmp.cli.getCpf()+"\n\t\tDivida: "+tmp.cli.getDivida()+"\tTelefone: "+tmp.cli.getTelefone()+"\n");
					System.out.print("\n\tCarro: \n\t\tPlaca: "+tmp.car.getPlaca()+"\t\tModelo: "+tmp.car.getModelo()+"\n\t\tObsercacoes: "+tmp.car.getObservacoes()+"\tDescricao: "+tmp.car.getDescricao()+"\n");
					this.removeLista(placa);		//	removendo da lista e do arquivos "Alugueis.txt"
					this.clientes.salvaClientes();
					this.carros.salvaCarros();
					return true;
				}else {
					System.out.print("->Erro na devolucao\n");
					return false;
				}
			}
			tmp = tmp.prox;
		}
		return false;
		
	}
	
	public int retornaCpf(String placa) {
		Aluguel tmp = this.aluguel;
		
		while(tmp!=null) {
			if(tmp.car.getPlaca().compareToIgnoreCase(placa) == 0) {
				return tmp.cli.getCpf();
			}
			tmp = tmp.prox;
		}
		return -1;
	}
	
	public String retornaData(String placa) {
		Aluguel tmp = this.aluguel;
		
		while(tmp!=null) {
			if(tmp.car.getPlaca().compareToIgnoreCase(placa) == 0) {
				return tmp.getDataInicial().getYear()+"/"+tmp.getDataInicial().getMonthOfYear()+"/"+tmp.getDataInicial().getDayOfMonth()+"\n";
			}
			tmp = tmp.prox;
		}
		System.out.print("->Ca não encontrado\n");
		return null;
	}
	public String[][] ShowAluguelStruct() {
		String[][] data = new String[qtd][5];
		System.out.println(qtd);
		int  i = 0;
		if(this.aluguel == null) {
			System.out.print("->Lista vazia\n");
		}else {
			Aluguel temp = this.aluguel;
			System.out.print("\n");
			while(temp != null) {				
				data[i][0] = Integer.toString(temp.cli.getCpf());
				data[i][1] = temp.car.getPlaca();
				data[i][2] = Integer.toString(temp.getDataInicial().getYear());
				data[i][3] = Integer.toString(temp.getDataInicial().getMonthOfYear());
				data[i][4] = Integer.toString(temp.getDataInicial().getDayOfMonth());

				i++;
				temp = temp.prox;
			}
			return data;
		}
		
		return null;
	}
}
