package noGui;

public class Cliente {
	
	private String nome;
	private String endereco;
	private int cpf;
	private int telefone;
	private double divida;
	Cliente prox;
	
	public Cliente(String nome, int cpf, int telefone, String end, double divida) {
		this.nome = nome.replace(" ", "_");;
		this.cpf = cpf;
		this.divida = divida;
		this.endereco = end.replace(" ", "_"); // facilita na leitura dos dados do arq txt
		this.telefone = telefone;
		this.prox = null;
	}
	
	public void pagaDivida(double valor) {
		if(valor > 0) {
			if(this.divida > 0) {
				System.out.print("\n->Divida de: "+this.divida);
				if(valor >= this.divida) {
					double troco = valor-this.divida; 
					System.out.print("\n->Troco: "+troco);
					System.out.print("\n->Divida de: 0");
					this.divida = 0;
				}else {
					System.out.print("\n->Valor inferior a divida");
				}
			}else {
				System.out.print("\n->Sem dividas");
			}
		}else {
			System.out.print("->Valor invalido");
		}
	}
	
	//set
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEndereco(String End) {
		this.endereco = End.replace(" ", "_");
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public void setTelefone(int tele) {
		this.telefone = tele;
	}
	
	public void setDivida(double divida) {
		this.divida = divida;
	}
	
	public void setProx(Cliente prox) {
		this.prox = prox;
	}
	
	//get
	public String getNome() {
		return this.nome;
	}
	public String getEndereco() {
		return this.endereco;
	}
	public int getCpf() {
		return this.cpf;
	}
	public int getTelefone() {
		return this.telefone;
	}
	public double getDivida() {
		return this.divida;
	}
	public Cliente getProx() {
		return prox;
	}
	public String mostraDados() {
		return "Nome: "+this.nome+" CPF: "+this.cpf+" Endereco: "+this.endereco+" telefone: "+this.telefone+" divida: "+this.divida+"\n";
		
	}
}
