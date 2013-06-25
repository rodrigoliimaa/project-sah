package br.com.infoliver.sah.apresentacao.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import flex.messaging.io.ArrayList;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.infoliver.sah.configuracao.util.BeanFactory;
import br.com.infoliver.sah.negocio.entity.Movimentacao;
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;

/**
 * Servlet implementation class PreviewEncaminhamentoOrteseProtese
 */
public class PreviewEncaminhamentoOrteseProtese extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewEncaminhamentoOrteseProtese() {
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
			String cid1 = null;
			String cid2 = null;
			String cid3 = null;
			String procedimento1 = null;
			String procedimento2 = null;
			String procedimento3 = null;
			String tipo1 = null;
			String tipo2 = null;
			String tipo3 = null;
			// **********************************************************************************************
			BeanFactory beanFactory = new BeanFactory();
			beanFactory.setPath(getServletContext().getRealPath("/WEB-INF"));
			ISistemaFacade facade = (ISistemaFacade) beanFactory.lookup("facade");
			// ------------------------------------------------------------------------------------
			
			
			Movimentacao movimentacao = new Movimentacao();
			movimentacao.setSequencial(seqMovimentacao);
			
			
			List<Movimentacao> lista =facade.listarRelatorioEncaminhamento(movimentacao);			
			List<Movimentacao> lista2 = new ArrayList();
			
			if(lista.size()==1){
				lista2.add(lista.get(0));
				for (int i = 0; i < lista.size(); i++) {
					cid1 = lista.get(0).getNumeroNota();
					procedimento1 = lista.get(0).getObservacao();
					tipo1=lista.get(0).getPaciente().getEstadoCivil();
					 
				}
			}
			if(lista.size()==2){
				lista2.add(lista.get(0));
				for (int i = 0; i <lista.size(); i++) {
					cid1 = lista.get(0).getNumeroNota();					
					procedimento1 = lista.get(0).getObservacao();
					tipo1=lista.get(0).getPaciente().getEstadoCivil();
					
					cid2 = lista.get(1).getNumeroNota();					
					procedimento2 = lista.get(1).getObservacao();					
					tipo2 = lista.get(1).getPaciente().getEstadoCivil();
				}
				
			}
			if(lista.size()==3){
				lista2.add(lista.get(0));
				for (int i = 0; i < lista.size(); i++) {
					cid1 = lista.get(0).getNumeroNota();
					procedimento1 = lista.get(0).getObservacao();
					tipo1=lista.get(0).getPaciente().getEstadoCivil();
					cid2 = lista.get(1).getNumeroNota();
					procedimento2 = lista.get(1).getObservacao();
					tipo2=lista.get(1).getPaciente().getEstadoCivil();
					cid3 = lista.get(2).getNumeroNota();
					procedimento3 = lista.get(2).getObservacao();
					tipo3=lista.get(2).getPaciente().getEstadoCivil();
					
				}
				
			}
			
			// **********************************************************************************************
			
			String file = "previewEncaminhamentoOrteseProtese.jasper";

			// -----------------------------------------------------//-------------------------------
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista2);
			// ------------------------------------------------------------------------------------
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("P_PATH_IMAGENS", caminho_imagens);
			parametros.put("P_CID1", cid1);
			parametros.put("P_CID2", cid2);
			parametros.put("P_CID3", cid3);
			parametros.put("P_PROCEDIMENTO1", procedimento1);
			parametros.put("P_PROCEDIMENTO2", procedimento2);
			parametros.put("P_PROCEDIMENTO3", procedimento3);
			parametros.put("P_TIPO1", tipo1);
			parametros.put("P_TIPO2", tipo2);
			parametros.put("P_TIPO3", tipo3);
			// ------------------------------------------------------------------------------------
			JasperPrint impressao = JasperFillManager.fillReport(caminho_relatorios + "/" + file, parametros, ds);
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(impressao,response.getOutputStream());
		} catch (Exception e) {
			System.out.println("Erro inesperado relatorio Mapa "
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
