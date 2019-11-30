package com.ufc.janaina.ui.windows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.ufc.janaina.jdbc.dao.VendedorDAO;
import com.ufc.janaina.models.Usuario;
import com.ufc.janaina.models.Vendedor;
import com.ufc.janaina.services.AuthService;
import com.ufc.janaina.ui.views.BuscarVendasView;
import com.ufc.janaina.ui.views.FormasPagamentoView;
import com.ufc.janaina.ui.views.MostrarRelatorioVendasView;
import com.ufc.janaina.ui.views.ViewFactory;
import com.ufc.janaina.ui.views.ViewManager;

public class SystemWindow implements Window {
	private JFrame mainFrame;
	private ViewManager viewManager;
	
	private void make() {
		// Setup ViewManager
		ViewFactory viewFactory = new ViewFactory();
		viewManager = new ViewManager();
		
		// Views
		BuscarVendasView buscarVendasView = (BuscarVendasView)viewFactory.getView("BuscarVendasView");
		
		// add views
		viewManager.setView("JanelaPrincipalView", viewFactory.getView("JanelaPrincipalView"));
		viewManager.setView("BuscarVeiculosView", viewFactory.getView("BuscarVeiculosView"));
		viewManager.setView("BuscarMontadorasView", viewFactory.getView("BuscarMontadorasView"));
		viewManager.setView("BuscarClientesView", viewFactory.getView("BuscarClientesView"));
		viewManager.setView("BuscarVendedoresView", viewFactory.getView("BuscarVendedoresView"));
		viewManager.setView("GerarOrcamentoView", viewFactory.getView("GerarOrcamentoView"));
		viewManager.setView("BuscarVendasView", buscarVendasView);
		viewManager.setView("BuscarAvaliacaoView", viewFactory.getView("BuscarAvaliacaoView"));
		viewManager.setView("BuscarTestesView", viewFactory.getView("BuscarTestesView"));
		
		// meu bar
		JMenuBar menuBar = new JMenuBar();
		
		// menu arquivo
		JMenu menuArquivo = new JMenu("Arquivo");
		JMenuItem menuItemSair = new JMenuItem("Sair");
		
		// menu cadastros
		JMenu menuCadastros = new JMenu("Cadastros");
		JMenuItem menuItemVeiculos = new JMenuItem("Veículos");
		JMenuItem menuItemMontadoras = new JMenuItem("Montadoras");
		JMenuItem menuItemClientes = new JMenuItem("Clientes");
		
		// menu vendas
		JMenu menuVendas = new JMenu("Vendas");
		JMenuItem menuItemConsultarVendas = new JMenuItem("Consultar Vendas");
		JMenuItem menuItemGerarOrcamento = new JMenuItem("Gerar Orçamento");
		JMenuItem menuItemGerarRelatorioVendas = new JMenuItem("Gerar relatorio de vendas");//severo passou aqui
		
		// menu teste
		JMenu menuTeste = new JMenu("Testes");
		JMenuItem menuItemTestes = new JMenuItem("Consultar testes");
		JMenuItem menuItemAvaliacoes = new JMenuItem("Consultar avaliações");
		
		JMenu menuAdmin = new JMenu("Adminstração");
		JMenuItem menuItemVendedores = new JMenuItem("Vendedores");
		JMenuItem menuItemFormaPagamento = new JMenuItem("Formas de Pagamento");
		
		// Set menus actions
		menuItemSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		menuItemVeiculos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewManager.getView("BuscarVeiculosView");
			}
		});

		menuItemMontadoras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewManager.getView("BuscarMontadorasView");
			}
		});
		
		menuItemClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewManager.getView("BuscarClientesView");
			}
		});
		
		menuItemVendedores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewManager.getView("BuscarVendedoresView");
			}
		});
		
		menuItemGerarOrcamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewManager.getView("GerarOrcamentoView");
			}
		});
		
		menuItemConsultarVendas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewManager.getView("BuscarVendasView");
				buscarVendasView.update();
			}
		});
		
		menuItemFormaPagamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BlankWindow(new FormasPagamentoView(), null, "Formas de Pagamento", true, false).launch();
			}
		});
		
	
		menuItemAvaliacoes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewManager.getView("BuscarAvaliacaoView");
			}
		});
		
		menuItemTestes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewManager.getView("BuscarTestesView");
			}
		});
		
		menuItemGerarRelatorioVendas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BlankWindow(new MostrarRelatorioVendasView(), null, "Relatório de Vendas", true, false).launch();
			}
		});
		
				// add to arquivo
		menuArquivo.add(menuItemSair);
		
		// add to cadastros
		menuCadastros.add(menuItemVeiculos);
		menuCadastros.add(menuItemMontadoras);
		menuCadastros.add(menuItemClientes);
		
		// add to vendas
		menuVendas.add(menuItemConsultarVendas);
		menuVendas.add(menuItemGerarOrcamento);
		menuVendas.add(menuItemGerarRelatorioVendas);
		
		// add to admin
		menuAdmin.add(menuItemVendedores);
		menuAdmin.add(menuItemFormaPagamento);
		
		// add to teste
		menuTeste.add(menuItemTestes);
		menuTeste.add(menuItemAvaliacoes);
		
		
		// Add to menu bar
		menuBar.add(menuArquivo);
		menuBar.add(menuCadastros);
		menuBar.add(menuVendas);
		menuBar.add(menuTeste);
		
		if(AuthService.getInstance().isAuthenticated()) {
			if(AuthService.getInstance().getAuth().getUser().getAdminLevel() == Usuario.AdminLevel.GERENTE) {
				menuBar.add(menuAdmin);
			}
		}
		
		String windowTitle = "Janaína Automóveis";
		
		if(AuthService.getInstance().isAuthenticated()) {
			if(AuthService.getInstance().getAuth().getUser().getAdminLevel() == Usuario.AdminLevel.GERENTE) {
				windowTitle += " - Gerente";
			}else {
				Vendedor v = (Vendedor)new VendedorDAO().getByUserId(AuthService.getInstance().getAuth().getUser().getId());
				windowTitle += " - Vendedor [Nome: " + v.getNome() + "]";
			}
		}
		
		mainFrame = new JFrame(windowTitle);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(new Dimension(640, 480));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		mainFrame.setJMenuBar(menuBar);
		mainFrame.add(viewManager);
	}
	
	@Override
	public void launch() {
		make();
		
		viewManager.getView("JanelaPrincipalView");
		
		mainFrame.setVisible(true);
	}
	
}
