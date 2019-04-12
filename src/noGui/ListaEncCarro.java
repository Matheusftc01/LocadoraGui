package noGui;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ListaEncCarro {
	Carro one;
	int qtd;
	public ListaEncCarro() {
		this.one = null;
	}
	
	public boolean insereLista(String pl, String mod, String desc, String obser, boolean dispo, double km, double taxadiar, double taxakm, int ano) {
		if(this.achaCarro(pl) == 0) {
			if(this.one == null) {
				this.one = new Carro(pl, mod, desc, obser, dispo, km, taxadiar, taxakm, ano);
			}else {// insere no inicio
				Carro novo = new Carro(pl, mod, desc, obser, dispo, km, taxadiar, taxakm, ano);
				novo.prox = one;
				one = novo;
			}
			this.qtd++;
			this.salvaCarros();
			return true;
		}else {
			System.out.print("->Carro ja possui cadastro");
			return false;
		}
		
	}
	
	public int achaCarro(String placa) {
		Carro tmp = this.one;
		
		while(tmp != null) {
			if(tmp.getPlaca().compareToIgnoreCase(placa)==0) {
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
			Carro temp = one;
			System.out.print("\n");
			while(temp != null) {
				System.out.print(temp.mostraDados());
				temp = temp.prox;
			}
			
		}
	}
	private int removeListaRecurs(Carro temp, String placa, int cont) {
		if(temp != null) {
			
			cont = this.removeListaRecurs(temp.prox, placa, cont);
			if(temp.getPlaca().compareToIgnoreCase(placa) ==  0) {		// placa do carro igual a placa de procura
				if(temp.getDisponivel() == true) {
					temp.setPlaca("---");		//marcao para remover na hora de salvar no arquivo
					return cont+1;
				}else {
					System.out.print("->Impossivel remover, carro alugado\n");
					return cont;
				}
			}
			
		}	
		return cont;
	}
	public boolean removeLista(String placa) { // remove por placa
		
		if(this.one == null) {
			System.out.print("->Lista vazia\n");
			return false;
		}else {
		
			int cont = this.removeListaRecurs(this.one, placa, 0);
			System.out.print("->Quantidade de removidos: "+cont); 
			if(cont>0) {
				this.salvaCarros();
				this.carregaCarros();
				return true;
			}
			return false;
			
		}
	}
	public void salvaCarros(){
		try {
			FileWriter arq = new FileWriter("Carros.txt");
			PrintWriter gravarArq = new PrintWriter(arq);	 
			
			Carro temp = this.one;
			if(temp!=null) {
				while(temp != null) {
					if(temp.getPlaca().compareToIgnoreCase("---") != 0) {		// so salva se for diferente da marcacao de remocao
						gravarArq.printf(temp.mostraDados());
					}
					temp = temp.prox;
				}
			}else {
				gravarArq.printf("");
			}
			arq.close();
		} catch (IOException e) {
			System.out.print("->Deu erro na hora de salvar os Carros\n");
		}
		
	}
	public void carregaCarros(){
		this.one = null;		// lista encadeada fica nula e carrego tudo do arquivo Carros.txt
		this.qtd = 0;
		try {
			BufferedReader arq = new BufferedReader(new FileReader("Carros.txt"));
	         while(arq.ready()){
	            String linha = arq.readLine();
	            String[] vet = linha.split("[ ]");
	            
//	            System.out.printf("\n%s ",vet[1]);  // placa
//	            System.out.printf("%s ",vet[3]);    // modelo
//	            System.out.printf("%s ",vet[5]);    // descricao
//	            System.out.printf("%s ",vet[7]);    // observacao
//	            System.out.printf("%s ",vet[9]);	// disponibilidade
//	            System.out.printf("%s ",vet[11]);	// km
//	            System.out.printf("%s ",vet[13]);	// taxadiaria
//	            System.out.printf("%s ",vet[15]);	// taxakm
//	            System.out.printf("%s\n",vet[17]);	// ano
	            
	          
	            boolean var = Boolean.parseBoolean(vet[9]);
	            double km = Double.parseDouble(vet[11]);
	            double taxadiar = Double.parseDouble(vet[13]);
	            double taxakm = Double.parseDouble(vet[15]);
	            int ano = Integer.parseInt(vet[17]);
	            
	            this.insereLista(vet[1], vet[3], vet[5], vet[7], var, km, taxadiar, taxakm, ano);	           
	         }
	         arq.close();
		} catch (IOException e) {
			System.out.print("->Erro na leitura dos Carros\n");
		}
	}
	
	public Carro retornaCarro(String placa) {
		Carro tmp = this.one;
		
		while (tmp!=null){
			if(tmp.getPlaca().compareToIgnoreCase(placa) == 0) {
				if(tmp.getDisponivel() == true) {
					return tmp;
				}else {
					System.out.print("->Carro nao disponivel\n");
					return null;
				}
						
			}
			tmp = tmp.prox;
		}
		System.out.print("->Carro nao cadastrado\n");
		return null;
	}
	
	public Carro retornaCarroIgnoreDispo(String placa) {
		Carro tmp = this.one;
		
		while (tmp!=null){
			if(tmp.getPlaca().compareToIgnoreCase(placa) == 0) {		
				return tmp;			
			}
			tmp = tmp.prox;
		}
		System.out.print("->Carro nao cadastrado\n");
		return null;
	}
	
	public double retornaKm(String placa) {
		Carro tmp = this.one;
		
		while(tmp!=null) {
			if(tmp.getPlaca().compareToIgnoreCase(placa) == 0) {
				return tmp.getKm();
			}
			tmp = tmp.prox;
		}
		return -1;
	}
	
	
	public String[] TdCarros() {
		Carro tmp = this.one; 
		java.util.List<String> dvd = new ArrayList<String>();
		
		while (tmp!=null){
//			System.out.println(Integer.toString(tmp.getPlaca()));
			dvd.add(tmp.getPlaca());
			tmp = tmp.prox;
		};
		String[] simpleArray = new String[dvd.size()];
		dvd.toArray( simpleArray );
		return simpleArray;
	}
	
	public String[] TdCarrosDispo() {
		Carro tmp = this.one; 
		java.util.List<String> dvd = new ArrayList<String>();
		
		while (tmp!=null){
			if(tmp.getDisponivel()) {
				dvd.add(tmp.getPlaca());
			}
			tmp = tmp.prox;
		};
		String[] simpleArray = new String[dvd.size()];
		dvd.toArray( simpleArray );
		return simpleArray;
	}
	public String[] TdCarrosIndispo() {
		Carro tmp = this.one; 
		java.util.List<String> dvd = new ArrayList<String>();
		
		while (tmp!=null){
			if(!tmp.getDisponivel()) {
				dvd.add(tmp.getPlaca());
			}
			tmp = tmp.prox;
		};
		String[] simpleArray = new String[dvd.size()];
		dvd.toArray( simpleArray );
		return simpleArray;
	}
	
	public String achaModelo(String placa) {
		Carro tmp = this.one;
		
		while(tmp!=null) {
			if(tmp.getPlaca().compareToIgnoreCase(placa) == 0) {
				return tmp.getModelo();
			}
			tmp = tmp.prox;
		}
		return "";
	}
	
	public boolean achaDisponibilidade(String placa) {
		Carro tmp = this.one;
		
		while(tmp!=null) {
			if(tmp.getPlaca().compareToIgnoreCase(placa) == 0) {
				return tmp.getDisponivel();
			}
			tmp = tmp.prox;
		}
		return false;
	}
	
	public String[][] ShowCarrosStruct() {
		String[][] data = new String[qtd][9];
		int  i = 0;
		if(this.one == null) {
			System.out.print("->Lista vazia\n");
		}else {
			Carro temp = one;
			System.out.print("\n");
			while(temp != null) {
				data[i][0] = temp.getPlaca();
				data[i][1] = temp.getModelo();
				data[i][2] = temp.getDescricao();
				data[i][3] = temp.getObservacoes();
				data[i][4] = Integer.toString(temp.getAno());
				data[i][5] = Boolean.toString(temp.getDisponivel());
				data[i][6] = Double.toString(temp.getKm());
				data[i][7] = Double.toString(temp.getTaxadiar());
				data[i][8] = Double.toString(temp.getTaxakm());
				
				i++;
				temp = temp.prox;
			}
			return data;
		}
		
		return null;
	}
}
