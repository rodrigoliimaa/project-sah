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
import br.com.infoliver.sah.negocio.dto.CodigoDescricaoProcedimentoDTO;
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;
import br.com.infoliver.sah.negocio.facade.impl.SistemaFacade;
import br.com.infoliver.sah.negocio.vo.ProcedimentoMedicoVO;
import br.com.infoliver.sah.negocio.vo.RelatorioProcedimentoMedicoVO;

public class PreviewRelatorioControleFrequenciaIndividualDeficienciaFisica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String caminho_relatorios = getServletContext().getRealPath("/relatorios");
			String caminho_imagens = getServletContext().getRealPath("/imagens");
			
			//----------------------------------------------------------------------
			Integer seqGrupoLaudo= Integer.parseInt(request.getParameter("seqGrupoLaudo"));
			Integer seqMedico= Integer.parseInt(request.getParameter("seqMedico"));
			Integer seqRelatorio= Integer.parseInt(request.getParameter("seqRelatorio"));
			
			//**********************************************************************************************
			Integer seqProcedimentoMedico= Integer.parseInt(request.getParameter("seqProcedimentoMedico"));
			//**********************************************************************************************
			BeanFactory beanFactory=new BeanFactory();
			beanFactory.setPath(getServletContext().getRealPath("/WEB-INF"));
			ISistemaFacade facade = (SistemaFacade) beanFactory.lookup("facade");			
			//------------------------------------------------------------------------------------
			ProcedimentoMedicoVO procedimentoMedicoVO = facade.consultarProcedimentoMedico(seqGrupoLaudo,seqProcedimentoMedico,seqMedico,seqRelatorio);
			String file = procedimentoMedicoVO.getCaminhoRelatorio();
			List<RelatorioProcedimentoMedicoVO> lista=procedimentoMedicoVO.getRelatorioProcedimentoMedicoVO();

			CodigoDescricaoProcedimentoDTO codigoDescricaoProcedimentoDTO = new CodigoDescricaoProcedimentoDTO(); 
			
			if (request.getParameter("seqProcedimentoPrincipalPoliclinica") != null) {
				Integer seqProcedimentoPrincipalPoliclinica = Integer.parseInt(request.getParameter("seqProcedimentoPrincipalPoliclinica"));
				codigoDescricaoProcedimentoDTO = facade.consultarCodigoProcedimentoPrincipal(seqProcedimentoPrincipalPoliclinica); 
			}

			//------------------------------------------------------------------------------------			
			JRBeanCollectionDataSource ds= new JRBeanCollectionDataSource(lista);
			//------------------------------------------------------------------------------------
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("P_PATH_IMAGENS", caminho_imagens);			
			parametros.put("P_CODIGO_PROCEDIMENTO_PRINCIPAL", codigoDescricaoProcedimentoDTO.getCodigo());			
			parametros.put("P_DESCRICAO_PROCEDIMENTO_PRINCIPAL", codigoDescricaoProcedimentoDTO.getDescricao());			
			//------------------------------------------------------------------------------------
			JasperPrint impressao = JasperFillManager.fillReport(caminho_relatorios+"/"+file,parametros,ds);
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(impressao,response.getOutputStream());
			
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}