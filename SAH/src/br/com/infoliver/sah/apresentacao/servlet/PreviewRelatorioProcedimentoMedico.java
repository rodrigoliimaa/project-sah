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
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;
import br.com.infoliver.sah.negocio.facade.impl.SistemaFacade;
import br.com.infoliver.sah.negocio.vo.ProcedimentoMedicoVO;
import br.com.infoliver.sah.negocio.vo.RelatorioProcedimentoMedicoVO;

public class PreviewRelatorioProcedimentoMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String caminho_relatorios = getServletContext().getRealPath("/relatorios");
			String caminho_imagens = getServletContext().getRealPath("/imagens");
			//---------------------------------------------------------------------
			
			Integer seqGrupoLaudo= Integer.parseInt(request.getParameter("seqGrupoLaudo"));
			Integer seqMedico= Integer.parseInt(request.getParameter("seqMedico"));
			Integer seqRelatorio= Integer.parseInt(request.getParameter("seqRelatorio"));
			//**********************************************************************************************
			Integer seqProcedimentoMedico= Integer.parseInt(request.getParameter("seqProcedimentoMedico"));
			//------------------------------------------------------------------------------------
			String seqProcedimentoMedicoPrincipal= request.getParameter("seqProcedimentoMedicoPrincipal");
			String dscProcedimentoMedicoPrincipal= request.getParameter("dscProcedimentoMedicoPrincipal");
			//------------------------------------------------------------------------------------
			String seqProcedimentoMedicoSecundario01= request.getParameter("seqProcedimentoMedicoSecundario01");
			String dscProcedimentoMedicoSecundario01= request.getParameter("dscProcedimentoMedicoSecundario01");
			//------------------------------------------------------------------------------------
			String seqProcedimentoMedicoSecundario02= request.getParameter("seqProcedimentoMedicoSecundario02");
			String dscProcedimentoMedicoSecundario02= request.getParameter("dscProcedimentoMedicoSecundario02");
			//------------------------------------------------------------------------------------
			String seqProcedimentoMedicoSecundario03= request.getParameter("seqProcedimentoMedicoSecundario03");
			String dscProcedimentoMedicoSecundario03= request.getParameter("dscProcedimentoMedicoSecundario03");
			//------------------------------------------------------------------------------------
			String seqProcedimentoMedicoSecundario04= request.getParameter("seqProcedimentoMedicoSecundario04");
			String dscProcedimentoMedicoSecundario04= request.getParameter("dscProcedimentoMedicoSecundario04");
			//------------------------------------------------------------------------------------
			String seqProcedimentoMedicoSecundario05= request.getParameter("seqProcedimentoMedicoSecundario05");
			String dscProcedimentoMedicoSecundario05= request.getParameter("dscProcedimentoMedicoSecundario05");
			//**********************************************************************************************
			BeanFactory beanFactory=new BeanFactory();
			beanFactory.setPath(getServletContext().getRealPath("/WEB-INF"));
			ISistemaFacade facade = (SistemaFacade) beanFactory.lookup("facade");			
			//------------------------------------------------------------------------------------
			ProcedimentoMedicoVO procedimentoMedicoVO = facade.consultarProcedimentoMedico(seqGrupoLaudo,seqProcedimentoMedico,seqMedico,seqRelatorio);
			String file = procedimentoMedicoVO.getCaminhoRelatorio();
			
			List<RelatorioProcedimentoMedicoVO> lista=procedimentoMedicoVO.getRelatorioProcedimentoMedicoVO();
			//------------------------------------------------------------------------------------			
			JRBeanCollectionDataSource ds= new JRBeanCollectionDataSource(lista);
			//------------------------------------------------------------------------------------
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("P_PATH_IMAGENS", caminho_imagens);			
			parametros.put("P_PROC_PRI", seqProcedimentoMedicoPrincipal);
			parametros.put("P_PROC_PRI_DSC", dscProcedimentoMedicoPrincipal);
			parametros.put("P_PROC_SEC_01", seqProcedimentoMedicoSecundario01);
			parametros.put("P_PROC_SEC_DSC_01", dscProcedimentoMedicoSecundario01);
			parametros.put("P_PROC_SEC_02", seqProcedimentoMedicoSecundario02);
			parametros.put("P_PROC_SEC_DSC_02", dscProcedimentoMedicoSecundario02);
			parametros.put("P_PROC_SEC_03", seqProcedimentoMedicoSecundario03);
			parametros.put("P_PROC_SEC_DSC_03", dscProcedimentoMedicoSecundario03);
			parametros.put("P_PROC_SEC_04", seqProcedimentoMedicoSecundario04);
			parametros.put("P_PROC_SEC_DSC_04", dscProcedimentoMedicoSecundario04);
			parametros.put("P_PROC_SEC_05", seqProcedimentoMedicoSecundario05);
			parametros.put("P_PROC_SEC_DSC_05", dscProcedimentoMedicoSecundario05);
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
