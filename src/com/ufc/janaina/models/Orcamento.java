package com.ufc.janaina.models;
import java.lang.Math;

public class Orcamento {
	
	private Veiculo veiculo;
	private int qtdParcelas;
	private boolean tanqueCheio;
	private boolean seguro;
	
	private Double taxaEntrada = .20;
	private Double juros;
	
	
	
	public Orcamento(Veiculo veiculo, int qtdParcelas, boolean tanqueCheio, boolean seguro) {
		this.veiculo = veiculo;
		this.qtdParcelas = qtdParcelas;
		this.tanqueCheio = tanqueCheio;
		this.seguro = seguro;
		
		//calculo do juros
		this.juros = Math.sqrt(qtdParcelas)/50.0;	
	}
	
	//converte o numero para string de dois digitos
	private String d2(Double x) {
		return String.format("%.02f", x);
	}
	
	public String gerarOrcamento() {
		String doc = "-----------   JANAINA AUTOMÓVEIS   -----------\n\n";
		
		doc += "Modelo do Veículo: " + this.veiculo.getModelo() + "\n";
		doc += "Placa do Veículo : " + this.veiculo.getPlaca() + "\n\n";
		
		
		doc += "--------------------------------------------------\n";
		doc += "-- Prestações a serem pagas --\n";
		doc += "--------------------------------------------------\n\n";
		
		Double entrada = this.veiculo.getPreco() * this.taxaEntrada * (1+this.juros);
		Double prestacao = (this.veiculo.getPreco()*(1 -this.taxaEntrada)) / qtdParcelas * (1+this.juros);
		Double valorSeguro = 0.0;
		Double valorGasolina = 0.0;
		
		doc += "0. Entrada do veículo: " + d2(entrada) + "\n";
		if(this.tanqueCheio) {
			valorGasolina = 50.00;
			doc += "0. Tanque de gasolina cheio: " + d2(valorGasolina) + "\n";
		}
		if(this.seguro) {
			valorSeguro = this.veiculo.getPreco() * 0.05;
			doc += "0. Valor do seguro: " + d2(valorSeguro) + "\n";
		}
		
		for(int i = 1; i <= this.qtdParcelas; i++) {
			doc += "" + i + ". Prestação do veículo: " +  d2(prestacao) + "\n";
		}
		doc += "------------------------------\n\n";
		
		doc += "Preço : " + d2(this.veiculo.getPreco()) + "\n";
		doc += "Juros : " + d2(this.veiculo.getPreco()*(this.juros)) + "\n\n";
		
		
		Double total = this.veiculo.getPreco()*(1+this.juros) + valorGasolina + valorSeguro;
		doc += "Total : " + d2(total) + "\n";
		doc += "-----------   ------------------   -----------\n\n";
		return doc;
	}
	
	
	
}
