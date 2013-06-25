package br.com.infoliver.sah.apresentacao.servlet;

import java.io.IOException;
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
import br.com.infoliver.sah.configuracao.util.BeanFactory;
import br.com.infoliver.sah.negocio.entity.Movimentacao;
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;

/**
 * Servlet implementation class PreviewTermoCompromisso
 */
public class PreviewTermoCompromisso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewTermoCompromisso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String caminho_relatorios = getServletContext().getRealPath("/relatorios");
			String caminho_imagens = getServletContext().getRealPath("/imagens");

			// ----------------------------------------------------------------------
			Integer seqMovimentacao = Integer.parseInt(request.getParameter("seqMovimentacao"));
			// **********************************************************************************************
			BeanFactory beanFactory = new BeanFactory();
			beanFactory.setPath(getServletContext().getRealPath("/WEB-INF"));
			ISistemaFacade facade = (ISistemaFacade) beanFactory.lookup("facade");
			// ------------------------------------------------------------------------------------
			
			
			Movimentacao movimentacao = new Movimentacao();
			movimentacao.setSequencial(seqMovimentacao);
			
			
			List<Movimentacao> lista =facade.listarRelatorioTermoCompromisso(movimentacao);			
			
			// **********************************************************************************************
			
			String file = "previewTermodeCompromisso.jasper";

			// -----------------------------------------------------//-------------------------------
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
			// ------------------------------------------------------------------------------------
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("P_PATH_IMAGENS", caminho_imagens);
			
			// ------------------------------------------------------------------------------------
			JasperPrint impressao = JasperFillManager.fillReport(caminho_relatorios + "/" + file, parametros, ds);
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(impressao,response.getOutputStream());
		} catch (Exception e) {
			System.out.println("Erro inesperado Termo de Compromisso "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
