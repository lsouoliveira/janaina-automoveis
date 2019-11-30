package com.ufc.janaina.ui.views;

public class ViewFactory {
	public View getView(String viewName) {
		if(viewName.contentEquals("JanelaPrincipalView")) {
			View v = new JanelaPrincipalView();
			
			return v; 
		} else if(viewName.contentEquals("BuscarVeiculosView")) {
			View v = new BuscarVeiculosView(true);
			
			return v; 
		}else if(viewName.contentEquals("BuscarMontadorasView")) {
			View v = new BuscarMontadorasView();
			
			return v; 
		}else if(viewName.contentEquals("BuscarClientesView")) {
			View v = new BuscarClientesView(true);
			
			return v; 
		}else if(viewName.contentEquals("BuscarVendedoresView")) {
			View v = new BuscarVendedoresView();
			
			return v; 
		}else if(viewName.contentEquals("GerarOrcamentoView")) {
			View v = new GerarOrcamentoVeiculoView();
			
			return v; 
		}else if(viewName.contentEquals("BuscarVendasView")) {
			View v = new BuscarVendasView();
			
			return v; 
		}else if(viewName.contentEquals("BuscarAvaliacaoView")) {
			View v = new BuscarAvaliacaoView();
			
			return v; 
		}else if(viewName.contentEquals("BuscarTestesView")) {
			View v = new BuscarTestesView();
			
			return v; 
		}else if(viewName.contentEquals("MostrarRelatorioVendasView")) {
			View v = new MostrarRelatorioVendasView();
			
			return v; 
		}else if(viewName.contentEquals("GerarNotaFiscalVendaView")) {
			View v = new GerarNotaFiscalVendaView();
			
			return v; 
		}
		
		return null;
	}
}
