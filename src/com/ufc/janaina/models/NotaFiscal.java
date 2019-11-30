package com.ufc.janaina.models;
import java.lang.Math;

public class NotaFiscal {
	
	private Venda venda;
	private Cliente cliente;
	private Veiculo veiculo;
	
	private String mensagemSigilo = "A Janaína Automóveis compromete-se em garantir "
			+ "e preservar o seu sigilo e privacidade, pois é "
			+ "nosso desejo estabelecer uma relação duradoura e confiável.\n";
	
	
	public NotaFiscal(Venda venda) {
		this.venda = venda;
		this.veiculo = venda.getVeiculo();
		this.cliente = venda.getCliente();
	}
	
	//converte o numero para string de dois digitos
	private String d2(Double x) {
		return String.format("%.02f", x);
	}
	
	public String gerarNotaFiscal() {
		String doc = "-----------   JANAINA AUTOMÓVEIS   -----------\n\n";
		
		doc += "INFORMAÇÕES DO CLIENTE:\n";
		doc += "Nome: " + this.cliente.getNome() + "\n";
		doc += "RG  : " + this.cliente.getRg() + "\n\n";
		
		doc += "INFORMAÇÕES DO VEÍCULO:\n";
		doc += "Modelo do Veículo: " + this.veiculo.getModelo() + "\n";
		doc += "Placa do Veículo : " + this.veiculo.getPlaca() + "\n";
		doc += "Valor do Veículo : " + d2(this.veiculo.getPreco()) + "\n\n";
		
		doc += "INFORMAÇÕES DO PAGAMENTO:\n";
		doc += "Data da venda     : " + this.venda.getData()+ "\n";
		doc += "Forma de pagamento: " + this.venda.getFormaPagamento()+ "\n";
		doc += "Descrição         : " + this.venda.getDescricao()+ "\n\n";
		

		doc += "--------------------------------------------------\n\n";
		doc += this.mensagemSigilo;
		doc += "-----------   ------------------   -----------\n\n";
		return doc;
	}
	
	
	
}










