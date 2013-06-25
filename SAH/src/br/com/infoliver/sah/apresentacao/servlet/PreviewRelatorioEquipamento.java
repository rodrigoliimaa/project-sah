package br.com.infoliver.sah.apresentacao.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.infoliver.sah.apresentacao.servlet.util.MapaEquipamentosPacientes;
import br.com.infoliver.sah.configuracao.util.BeanFactory;
import br.com.infoliver.sah.negocio.entity.Fornecedor;
import br.com.infoliver.sah.negocio.entity.Movimentacao;
import br.com.infoliver.sah.negocio.entity.Programa;
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;
import flex.messaging.io.ArrayList;

public class PreviewRelatorioEquipamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PreviewRelatorioEquipamento() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String caminho_relatorios = getServletContext().getRealPath("/relatorios");
			String caminho_imagens = getServletContext().getRealPath("/imagens");
			String dataInicio = request.getParameter("dataInicioSolicitacao");
			String dataFim = request.getParameter("dataFim");
			Integer seqPrograma = Integer.parseInt(request.getParameter("seqPrograma"));
			String setor = request.getParameter("setor");			
			String situacao = request.getParameter("situacao");
			String tipo = request.getParameter("tipo");
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			formatter.parse(dataInicio);
			
			Date dataInicial = formatter.parse(dataInicio); 
			Date dataFinal = formatter.parse(dataFim);
			
			BeanFactory beanFactory = new BeanFactory();
			beanFactory.setPath(getServletContext().getRealPath("/WEB-INF"));
			ISistemaFacade facade = (ISistemaFacade) beanFactory.lookup("facade");
			
			Movimentacao movimentacao = new Movimentacao();
			Programa programa = new Programa();
			programa.setSequencial(seqPrograma);
			movimentacao.setPrograma(programa);			
			movimentacao.setDataInicioSolicitacao(dataInicial);
			movimentacao.setDataFimSolicitacao(dataFinal);
			movimentacao.setSituacao(situacao);
			movimentacao.setFornecedor(new Fornecedor());
			
			List<Movimentacao> lista = new ArrayList();
			String file = "";
			
			MapaEquipamentosPacientes mapaEquipamentosPacientes = new MapaEquipamentosPacientes();
			
			lista = facade.listarRelatorioEquipamentos(movimentacao);
			
			for (Movimentacao movimentacao2 : lista) {
				mapaEquipamentosPacientes.adicionarMovimentacao(movimentacao2);
			}
			
			if(tipo.equals("analitico")){
				file="previewRelatorioEquipamentosAnalitico.jasper";
			}else{
				file="previewRelatorioEquipamentosSintetico.jasper";
			}
			
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(mapaEquipamentosPacientes.getListaApacBpiPacientes());

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("P_PATH_IMAGENS", caminho_imagens);
			parametros.put("P_SETOR", setor);
			parametros.put("P_SITUACAO", situacao);
			parametros.put("SUBREPORT_DIR", caminho_relatorios + "/");
			
			JasperPrint impressao = JasperFillManager.fillReport(caminho_relatorios + "/" + file, parametros, ds);
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(impressao,response.getOutputStream());
		} catch (Exception e) {
			System.out.println("Erro inesperado relatorio Mapa "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

}