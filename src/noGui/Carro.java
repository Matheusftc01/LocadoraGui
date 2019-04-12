package noGui;

public class Carro {
	private String placa;
	private String modelo;
	private String descricao;
	private String observacoes;
	private boolean disponivel;
	private double km;
	private double taxadiar;
	private double taxakm;
	private int ano;
	
	Carro prox;
	
	public Carro(String pl, String mod, String desc, String obser, boolean dispo, double km, double taxadiar, double taxakm, int ano) {
		this.placa = pl;
		this.modelo = mod;
		this.descricao = desc.replace(" ", "_");
		this.observacoes = obser.replace(" ", "_");
		this.disponivel = dispo;
		this.km = km;
		this.taxadiar = taxadiar;
		this.taxakm = taxakm;
		this.ano = ano;
		this.prox = null;		
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.replace(" ", "_");
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes.replace(" ", "_");
	}

	public boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}

	public double getTaxadiar() {
		return taxadiar;
	}

	public void setTaxadiar(double taxadiar) {
		this.taxadiar = taxadiar;
	}

	public double getTaxakm() {
		return taxakm;
	}

	public void setTaxakm(double taxakm) {
		this.taxakm = taxakm;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Carro getProx() {
		return prox;
	}

	public void setProx(Carro prox) {
		this.prox = prox;
	}
	public String mostraDados() {
		return "Placa: "+this.placa+" Modelo: "+this.modelo+" Descrição: "+this.descricao+" Observações: "+this.observacoes+" Disponibilidade: "+this.disponivel+" Km: "+this.km+" Taxa_diaria: "+this.taxadiar+" Taxa_Km: "+this.taxakm+" Ano: "+this.ano+"\n";
	}
	
}
