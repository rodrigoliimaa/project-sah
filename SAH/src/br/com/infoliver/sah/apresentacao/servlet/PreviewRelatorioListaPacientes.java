package br.com.infoliver.sah.apresentacao.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.infoliver.sah.negocio.entity.GrupoLaudo;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;

@Controller
public class PreviewRelatorioListaPacientes {

	@Autowired
	private ISistemaFacade sistemaFacade;
	
	@RequestMapping(method = RequestMethod.GET, value="/**/PreviewRelatorioListaPacientes")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			
			String caminho_relatorios = request.getRealPath("/relatorios");//getServletContext().getRealPath("/relatorios");
			String caminho_imagens = request.getRealPath("/imagens");//getServletContext().getRealPath("/imagens");
			
			GrupoLaudo grupoLaudo = new GrupoLaudo();
			grupoLaudo.setSequencial(new Integer(request.getParameter("seqGrupoLaudo")));
			List<Paciente> lista = sistemaFacade.listarPacienteGrupoLaudo(grupoLaudo);
		
			String file = "previewListaPacientes.jasper";

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("P_PATH_IMAGENS", caminho_imagens);
			
			JasperPrint impressao;
			
			impressao = JasperFillManager.fillReport(caminho_relatorios + "/" + file, parametros, ds);
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(impressao,response.getOutputStream());
		} catch (JRException e) {
			System.out.println("Erro inesperado relatorio ListaPacientes: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
