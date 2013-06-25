package br.com.infoliver.sah.apresentacao.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

/**
 * Servlet implementation class PreviewRelatorioEtiquetasPacientes
 */
public class PreviewRelatorioEtiquetasPacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public PreviewRelatorioEtiquetasPacientes() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			String caminho_relatorios = getServletContext().getRealPath("/relatorios");
			String caminho_imagens = getServletContext().getRealPath("/imagens");

			// ----------------------------------------------------------------------
			Integer seqPaciente = Integer.parseInt(request.getParameter("seqPaciente"));
			Integer qtdEtiquetas = Integer.parseInt(request.getParameter("qtdEtiquetas"));
			String[] listaSequencial = request.getParameterValues("array");		
			String[]sequenciais = null;
			for (int x=0;x<listaSequencial.length;x++){
				String l = listaSequencial[x];
				sequenciais=l.split(",");
			}
			
			// **********************************************************************************************
			BeanFactory beanFactory = new BeanFactory();
			beanFactory.setPath(getServletContext().getRealPath("/WEB-INF"));
			ISistemaFacade facade = (ISistemaFacade) beanFactory.lookup("facade");
			// ------------------------------------------------------------------------------------
			List<Paciente> lista = new ArrayList<Paciente>();
			List<Paciente> listajr = new ArrayList<Paciente>();			
			PaginacaoVO paciente = new PaginacaoVO();
			Paciente entidade = new Paciente();
			// **********************************************************************************************
			for (int i = 0; i < qtdEtiquetas; i++) {
				for (int j =0 ;j < sequenciais.length;j++) {
					String sequencial = sequenciais[j];							
					entidade.setSequencial(Integer.parseInt(sequencial));
					paciente.setEntidade(entidade);
					lista = facade.listarPacienteRelatorio(paciente);
					listajr.addAll(lista);
					
				}
			
			}
			String file = "tratamentoReabilitacao.jasper";

			// -----------------------------------------------------//-------------------------------
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listajr);
			// ------------------------------------------------------------------------------------
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("P_PATH_IMAGENS", caminho_imagens);
			// ------------------------------------------------------------------------------------
			JasperPrint impressao = JasperFillManager.fillReport(caminho_relatorios + "/" + file, parametros, ds);
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(impressao,response.getOutputStream());

		} catch (Exception e) {
			System.out.println("Erro inesperado relatorio etiquetas: "
					+ e.getMessage());
			e.printStackTrace();
		}
	}
}
