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
import br.com.infoliver.sah.negocio.entity.Agendamento;
import br.com.infoliver.sah.negocio.entity.EquipeMedica;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.entity.Ocupacao;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

/**
 * Servlet implementation class PreviewRelatorioAgendamentos
 */
public class PreviewRelatorioAgendamentos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewRelatorioAgendamentos() {
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
			String nomeMedico = request.getParameter("nomeMedico");
			String cnsMedico = request.getParameter("cnsMedico");
			String ocupacaoMedico = request.getParameter("ocupacaoMedico");
			String cnsPaciente = request.getParameter("cnsPaciente");
			String cpfPaciente = request.getParameter("cpfPaciente");
			String nomePaciente = request.getParameter("nomePaciente");
			String descricaoOcupacaoPaciente = request.getParameter("ocupacaoPaciente");
			String rgPaciente = request.getParameter("rgPaciente");
			String prontuario = request.getParameter("prontuario");
			String descricaoEquipeMedica = request.getParameter("descricaoEquipeMedica");
			String usuarioLogado = request.getParameter("usuarioLogado");
			String data = request.getParameter("data");
			String dataFim = request.getParameter("dataFim");
			String tipo = request.getParameter("tipo");
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			// **********************************************************************************************
			BeanFactory beanFactory = new BeanFactory();
			beanFactory.setPath(getServletContext().getRealPath("/WEB-INF"));
			ISistemaFacade facade = (ISistemaFacade) beanFactory.lookup("facade");
			// ------------------------------------------------------------------------------------
			Medico medico = new Medico();
			Usuario usuario = new Usuario();
			Paciente paciente = new Paciente();	
			Ocupacao ocupacao = new Ocupacao();
			Ocupacao ocupacaoPaciente = new Ocupacao();
			Agendamento agendamento = new Agendamento();
			Agendamento ag = new Agendamento();
			EquipeMedica equipeMedica = new EquipeMedica();
			
			List<Agendamento> lista = new ArrayList<Agendamento>();
				
			medico.setNome(retornarNullQuandoForNull(nomeMedico));
			medico.setCns(retornarNullQuandoForNull(cnsMedico));
			ocupacao.setDescricao(retornarNullQuandoForNull(ocupacaoMedico));
			medico.setOcupacao(ocupacao);
			agendamento.setMedico(medico);
			
			paciente.setNome(retornarNullQuandoForNull(nomePaciente));
			paciente.setCns(retornarNullQuandoForNull(cnsPaciente));
			paciente.setRg(retornarNullQuandoForNull(rgPaciente));
			paciente.setCpf(retornarNullQuandoForNull(cpfPaciente));
			
			if (prontuario != null && !(prontuario.equals("null"))) {
				Integer sequencial = Integer.parseInt(prontuario);
				paciente.setSequencial(sequencial);
			}
			ocupacaoPaciente.setDescricao(retornarNullQuandoForNull(descricaoOcupacaoPaciente));
			paciente.setOcupacao(ocupacaoPaciente);
			agendamento.setPaciente(paciente);
			
			equipeMedica.setDescricao(retornarNullQuandoForNull(descricaoEquipeMedica));
			agendamento.setEquipeMedica(equipeMedica);
			
			usuario.setSequencial(Integer.parseInt(usuarioLogado));
			
			
			agendamento.setData(new Date());
			if (data != null && !(data.equals("null"))) {
				agendamento.setData(formatter.parse(data));
			}

			agendamento.setDataFim(new Date());
			if (dataFim != null && !(dataFim.equals("null"))) {
				agendamento.setDataFim(formatter.parse(dataFim));
			}
				
				
//				if(tipo != "") {
//					if(tipo.toString().equals(Agendamento.MEDICO.toString()))
//						agendamento.setTipo(Agendamento.MEDICO);
//					if(tipo.toString().equals(Agendamento.EQUIPE_MEDICA.toString()))
//						agendamento.setTipo(Agendamento.EQUIPE_MEDICA);
//				}
					
			PaginacaoVO paginacaoVO = new PaginacaoVO();					
			paginacaoVO.setQuantidadePaginacao(10);
			paginacaoVO.setInicioPaginacao(0);
			paginacaoVO.setEntidade(agendamento);
			ag = (Agendamento) paginacaoVO.getEntidade();
			
			PaginacaoVO pag = facade.listarAgendamentoPaginado(paginacaoVO);
			lista = new ArrayList<Agendamento>();
			lista = (List<Agendamento>) pag.getEntidade();		
					
			// **********************************************************************************************
			
			String file = "previewRelatorioAgendamento.jasper";

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
			System.out.println("Erro inesperado relatorio agendamentos: "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	private String retornarNullQuandoForNull(String valor) {
		if (valor != null && valor.equals("null")) {
			return null;
		}
		
		return valor;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
