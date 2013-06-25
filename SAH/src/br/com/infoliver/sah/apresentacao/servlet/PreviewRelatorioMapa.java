package br.com.infoliver.sah.apresentacao.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import br.com.infoliver.sah.negocio.dto.MovimentacaoRelatorioDTO;
import br.com.infoliver.sah.negocio.entity.Fornecedor;
import br.com.infoliver.sah.negocio.entity.Movimentacao;
import br.com.infoliver.sah.negocio.entity.Programa;
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

/**
 * Servlet implementation class PreviewRelatorioMapa
 */
public class PreviewRelatorioMapa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PreviewRelatorioMapa() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String caminho_relatorios = getServletContext().getRealPath("/relatorios");
			String caminho_imagens = getServletContext().getRealPath("/imagens");

			// ----------------------------------------------------------------------
			Integer seqFornecedor = Integer.parseInt(request.getParameter("seqFornecedor"));
			String dataInicio = request.getParameter("dataInicioSolicitacao");
			String dataFim = request.getParameter("dataFim");
		
			Integer seqPrograma = Integer.parseInt(request.getParameter("seqPrograma"));

			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			
			Date dataInicial = new java.sql.Date( ((java.util.Date)formatter.parse(dataInicio)).getTime()); 
			Date dataFinal = new java.sql.Date( ((java.util.Date)formatter.parse(dataFim)).getTime());
			// **********************************************************************************************
			BeanFactory beanFactory = new BeanFactory();
			beanFactory.setPath(getServletContext().getRealPath("/WEB-INF"));
			ISistemaFacade facade = (ISistemaFacade) beanFactory.lookup("facade");
			// ------------------------------------------------------------------------------------
			
			Fornecedor fornecedor = new Fornecedor();
			Movimentacao movimentacao = new Movimentacao();
			
			if (new Integer(0).equals(seqFornecedor)) {
				fornecedor.setSequencial(null);
			} else {
				fornecedor.setSequencial(seqFornecedor);
			}
			
			movimentacao.setFornecedor(fornecedor);
			Programa programa = new Programa();
			programa.setSequencial(seqPrograma);
			movimentacao.setPrograma(programa);			
			
			movimentacao.setDataInicioSolicitacao(dataInicial);
			movimentacao.setDataFimSolicitacao(dataFinal);
			
			movimentacao.setSituacao("TODAS");
			
			PaginacaoVO paginacaoVO = new PaginacaoVO();
			paginacaoVO.setEntidade(movimentacao);
			paginacaoVO.setInicioPaginacao(0);
			paginacaoVO.setQuantidadePaginacao(100000);
			
			PaginacaoVO vo = facade.listarMovimentacaoPaginadoParaRelatorio(paginacaoVO);
			
			List<Movimentacao> lista = (List<Movimentacao>)vo.getEntidade();
			
			List<MovimentacaoRelatorioDTO> listaMovimentacaoRelatorio = new ArrayList<MovimentacaoRelatorioDTO>();
			
			for (Movimentacao object : lista) {
				listaMovimentacaoRelatorio.add(new MovimentacaoRelatorioDTO(object));
				if ("BILATERAL".equals(object.getProcedimentos().get(0).getCategoriaTipo())) {
					listaMovimentacaoRelatorio.add(new MovimentacaoRelatorioDTO(object));
				}
			}
			
			// **********************************************************************************************
			
			String file = "previewMapaEncaminhamento.jasper";

			// -----------------------------------------------------//-------------------------------
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaMovimentacaoRelatorio);
			// ------------------------------------------------------------------------------------
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("P_PATH_IMAGENS", caminho_imagens);
			parametros.put("P_DATA_INICIO", dataInicio);
			parametros.put("P_DATA_FINAL", dataFim);
			parametros.put("P_NOME_FORNECEDOR", request.getParameter("nomeFornecedor"));
			// ------------------------------------------------------------------------------------
			JasperPrint impressao = JasperFillManager.fillReport(caminho_relatorios + "/" + file, parametros, ds);
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(impressao,response.getOutputStream());
		} catch (Exception e) {
			System.out.println("Erro inesperado relatorio Mapa " + e.getMessage());
			e.printStackTrace();
		}

	}
	
}
