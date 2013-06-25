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
import br.com.infoliver.sah.configuracao.util.BeanFactory;
import br.com.infoliver.sah.negocio.entity.Movimentacao;
import br.com.infoliver.sah.negocio.entity.Programa;
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;

/**
 * Servlet implementation class PreviewRelatorioMapaBpi
 */
public class PreviewRelatorioMapaBpi extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewRelatorioMapaBpi() {
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
			String competencia = request.getParameter("competencia");
			// ----------------------------------------------------------------------
			Integer seqPrograma = Integer.parseInt(request.getParameter("seqPrograma"));
			String apacBpi = request.getParameter("apacBpi");
			String numeroNota = request.getParameter("numeroNota");
			String dataInicio = request.getParameter("dataInicio");			
			String dataFim = request.getParameter("dataFim");
			
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
			
			Date dataInicial = new java.sql.Date( ((java.util.Date)formatter.parse(dataInicio)).getTime()); 
			Date dataFinal = new java.sql.Date( ((java.util.Date)formatter.parse(dataFim)).getTime());
			
			
			// **********************************************************************************************
			BeanFactory beanFactory = new BeanFactory();
			beanFactory.setPath(getServletContext().getRealPath("/WEB-INF"));
			ISistemaFacade facade = (ISistemaFacade) beanFactory.lookup("facade");
			// ------------------------------------------------------------------------------------
			
			
			Movimentacao movimentacao = new Movimentacao();
			Programa programa = new Programa();
			programa.setSequencial(seqPrograma);
			
			movimentacao.setPrograma(programa);
			if(numeroNota==""){
				movimentacao.setNumeroNota(null);
			}else{
				movimentacao.setNumeroNota(numeroNota);
			}
			if(apacBpi==""){
				movimentacao.setApacBpi(null);
			}else{
				movimentacao.setApacBpi(apacBpi);
			}
			
			movimentacao.setDataInicioSolicitacao(dataInicial);
			movimentacao.setDataFimSolicitacao(dataFinal);
			
			List<Movimentacao> lista =(List<Movimentacao>) facade.listarRelatorioMapaBpi(movimentacao);
			
			// **********************************************************************************************
			
			String file = "previewMapaBpi.jasper";

			// -----------------------------------------------------//-------------------------------
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
			// ------------------------------------------------------------------------------------
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("P_PATH_IMAGENS", caminho_imagens);
			parametros.put("P_COMPETENCIA", competencia);
			parametros.put("SUBREPORT_DIR", caminho_relatorios + "/");
			
			// ------------------------------------------------------------------------------------
			JasperPrint impressao = JasperFillManager.fillReport(caminho_relatorios + "/" + file, parametros, ds);
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(impressao,response.getOutputStream());
		} catch (Exception e) {
			System.out.println("Erro inesperado relatorio MapaBPI"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
