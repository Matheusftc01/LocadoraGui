package noGui;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;



public class Aluguel {
	
	private DateTime dataInicial;
	private DateTime dataFinal;
	private boolean delet;
	
	

	Aluguel prox;
	Cliente cli;
	Carro car;
	
	DateTimeZone BRAZIL = DateTimeZone.forID("America/Sao_Paulo");
	
	public Aluguel(Cliente cliente, Carro carro, int ano, int mes, int dia){
		this.cli = cliente;
		this.car = carro;
		this.car.setDisponivel(false);
		this.dataInicial = new DateTime(ano, mes,dia, 0, 0, 0, this.BRAZIL);
		this.dataFinal = null;
		this.prox = null;
		this.delet = false;
	}
	
	public boolean getDelet() {
		return delet;
	}
	
	public void setDelet(boolean delet) {
		this.delet = delet;
	}
	public DateTime getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(int ano, int mes, int dia) {
		this.dataInicial = new DateTime(ano, mes,dia, 0, 0, 0, this.BRAZIL);
	}

	public DateTime getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(int ano, int mes, int dia) {
		this.dataFinal = new DateTime(ano, mes,dia, 0, 0, 0, this.BRAZIL);;
	}

	public Cliente getCli() {
		return cli;
	}

	public void setCli(Cliente cli) {
		this.cli = cli;
	}

	public Carro getCar() {
		return car;
	}

	public void setCar(Carro car) {
		this.car = car;
	}

	public Aluguel getProx() {
		return prox;
	}

	public void setProx(Aluguel prox) {
		this.prox = prox;
	}
	
	public String mostraInfo() {
		return "CPF: "+this.cli.getCpf()+" Placa: "+this.car.getPlaca()+" Ano: "+this.dataInicial.getYear()+" Mes: "+this.dataInicial.getMonthOfYear()+" Dia: "+this.dataInicial.getDayOfMonth()+"\n";
	}
	
	public boolean devolveCarro(int ano, int mes, int dia) {
		if(ano >= this.dataInicial.getYear() && mes >= this.dataInicial.getMonthOfYear() && dia > this.dataInicial.getDayOfMonth()) {
			this.setDataFinal(ano, mes, dia);		//	data de entreda do carro
		
			int diasCar = Days.daysBetween(this.dataInicial.toLocalDate(),this.dataFinal.toLocalDate()).getDays();	//	dias com o carro
			
			System.out.println("->Dias com o carro: "+diasCar);
			double divida = diasCar * this.car.getTaxadiar();		
			System.out.print("->Valor a ser pago: "+divida);
			
			this.cli.setDivida(divida);		//	atribuindo divida ao cliente
			this.car.setDisponivel(true);
			return true;
			//perguntar se quer pagar a divida agora
		}else{
			System.out.print("->Data inferior a do aluguel\n");
			return false;
		}
		
	}
	
	public boolean devolveCarro(double kmrodados) {
		if(kmrodados >= this.car.getKm()) {
			double km = kmrodados-this.car.getKm();		//	km que o cliente andou
			System.out.println("->Km rodados: "+km);
			
			double divida = km*this.car.getTaxakm();		//	calculando a divida
			System.out.print("->Valor a ser pago: "+divida);
			
			this.cli.setDivida(divida); 	//	atribuindo divida ao cliente
			this.car.setKm(kmrodados); 		//	att os km do carro
			this.car.setDisponivel(true);	//	att disponibilidade
			
			//perguntar se quer pagar a divida agora
			return true;
		}else {
			System.out.print("->Quilometragem inferior a registrada no sistema, impossivel calcular a divida\n");
			return false;
		}
		
	}
	
}
