package br.com.infoliver.sah.negocio.facade;

import java.util.List;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;

import br.com.infoliver.sah.configuracao.exception.FacadeException;
import br.com.infoliver.sah.negocio.dto.CodigoDescricaoProcedimentoDTO;
import br.com.infoliver.sah.negocio.entity.Agendamento;
import br.com.infoliver.sah.negocio.entity.Arquivo;
import br.com.infoliver.sah.negocio.entity.Empresa;
import br.com.infoliver.sah.negocio.entity.Encaminhador;
import br.com.infoliver.sah.negocio.entity.EncaminhamentoExterno;
import br.com.infoliver.sah.negocio.entity.Entrevista;
import br.com.infoliver.sah.negocio.entity.Equipamento;
import br.com.infoliver.sah.negocio.entity.EquipeMedica;
import br.com.infoliver.sah.negocio.entity.Escolaridade;
import br.com.infoliver.sah.negocio.entity.Feriado;
import br.com.infoliver.sah.negocio.entity.Fornecedor;
import br.com.infoliver.sah.negocio.entity.Grupo;
import br.com.infoliver.sah.negocio.entity.GrupoLaudo;
import br.com.infoliver.sah.negocio.entity.GrupoLaudoPaciente;
import br.com.infoliver.sah.negocio.entity.GrupoPermissao;
import br.com.infoliver.sah.negocio.entity.GrupoUsuario;
import br.com.infoliver.sah.negocio.entity.Licenca;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.entity.Motivo;
import br.com.infoliver.sah.negocio.entity.Movimentacao;
import br.com.infoliver.sah.negocio.entity.Ocupacao;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.entity.Permissao;
import br.com.infoliver.sah.negocio.entity.PreAtendimento;
import br.com.infoliver.sah.negocio.entity.ProcedimentoMedico;
import br.com.infoliver.sah.negocio.entity.Raca;
import br.com.infoliver.sah.negocio.entity.Relatorio;
import br.com.infoliver.sah.negocio.entity.Setor;
import br.com.infoliver.sah.negocio.entity.SetorEncaminhamentoExterno;
import br.com.infoliver.sah.negocio.entity.SigtapCID;
import br.com.infoliver.sah.negocio.entity.SigtapProcedimento;
import br.com.infoliver.sah.negocio.entity.TipoDeficiencia;
import br.com.infoliver.sah.negocio.entity.TipoResponsavel;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.vo.LoginVO;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.ProcedimentoMedicoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@RemotingDestination
public interface ISistemaFacade{

	//Paciente ==========================================================
	@RemotingInclude
	RetornoVO inserirPaciente(Paciente paciente)throws FacadeException;
	
	@RemotingInclude
	RetornoVO alterarPaciente(Paciente paciente)throws FacadeException;

	@RemotingInclude
	RetornoVO excluirPaciente(Paciente paciente)throws FacadeException;
	
	@RemotingInclude
	PaginacaoVO listarPacientePaginado(PaginacaoVO paciente);
	
	@RemotingInclude
	List<Paciente> listarPaciente();
	
	@RemotingInclude
	List<Paciente> listarPacienteRelatorio(PaginacaoVO paciente);
	
	@RemotingInclude
	Paciente obterPaciente(Paciente paciente);
	//====================================================================

	//Arquivo ============================================================
	@RemotingInclude
	RetornoVO excluirArquivo(Arquivo arquivo)throws FacadeException;

	Arquivo consultarParaVisualizacaoArquivo(Integer sequencial);
	
	@RemotingInclude
	List<Arquivo> listarArquivo(Integer sequencialPaciente);
	//=====================================================================

	//Empresa =============================================================
	@RemotingInclude
	Empresa consultarEmpresa();
	
	@RemotingInclude
	RetornoVO inserirEmpresa(Empresa empresa)throws FacadeException;
	
	@RemotingInclude
	RetornoVO alterarEmpresa(Empresa empresa)throws FacadeException;
	//======================================================================

	//Encaminhador =========================================================
	@RemotingInclude
	List<Encaminhador> listarEncaminhador();
	//======================================================================

	//Escolaridade =========================================================
	@RemotingInclude
	List<Escolaridade> listarEscolaridade();
	//======================================================================

	//Ocupacao =============================================================
	@RemotingInclude
	List<Ocupacao> listarOcupacao();
	
	@RemotingInclude
	List<Ocupacao> pesquisarOcupacaoPorDescricao(String descricao);
	//======================================================================

	//Raca =================================================================
	@RemotingInclude
	List<Raca> listarRaca();
	//======================================================================

	//TipoResponsavel ======================================================
	@RemotingInclude
	List<TipoResponsavel> listarTipoResponsavel();
	//======================================================================
	
	//Usuario ==============================================================
	@RemotingInclude
	LoginVO acessarSistema(Usuario usuario)throws FacadeException;
	
	@RemotingInclude
	RetornoVO inserirUsuario(Usuario usuario)throws FacadeException;

	@RemotingInclude
	RetornoVO alterarUsuario(Usuario usuario)throws FacadeException;

	@RemotingInclude
	RetornoVO excluirUsuario(Usuario usuario)throws FacadeException;

	@RemotingInclude
	PaginacaoVO listarUsuarioPaginado(PaginacaoVO usuario);

	@RemotingInclude
	List<Usuario> listarUsuario();

	@RemotingInclude
	List<Usuario> listarUsuarioDoGrupo(Integer sequencialGrupo);

	@RemotingInclude
	List<Usuario> listarUsuarioDiferenteDoGrupo(Integer sequencialGrupo);
	
	@RemotingInclude
	Usuario obterUsuario(Usuario usuario);
	//======================================================================
	
	//Permissao ============================================================
	@RemotingInclude
	List<Permissao> listarPermissaoDoGrupo(Integer sequencialGrupo);

	@RemotingInclude
	List<Permissao> listarPermissaoDiferenteDoGrupo(Integer sequencialGrupo);
	//======================================================================

	//LogAcesso ============================================================
	@RemotingInclude
	PaginacaoVO listarLogAcessoPaginado(PaginacaoVO logAcesso);
	//======================================================================

	//GrupoUsuario =========================================================
	@RemotingInclude
	void inserirGrupoUsuario(GrupoUsuario grupoUsuario)throws FacadeException;

	@RemotingInclude
	void excluirGrupoUsuario(GrupoUsuario grupoUsuario)throws FacadeException;
	//======================================================================

	//GrupoPermissao =======================================================
	@RemotingInclude
	void inserirGrupoPermissao(GrupoPermissao grupoPermissao)throws FacadeException;

	@RemotingInclude
	void excluirGrupoPermissao(GrupoPermissao grupoPermissao)throws FacadeException;
	//======================================================================

	//Grupo ================================================================
	@RemotingInclude
	void inserirGrupo(Grupo grupo)throws FacadeException;

	@RemotingInclude
	void alterarGrupo(Grupo grupo)throws FacadeException;

	@RemotingInclude
	void excluirGrupo(Grupo grupo)throws FacadeException;

	@RemotingInclude
	List<Grupo> listarGrupo();
	//======================================================================
	
	//Grupo Laudo ==========================================================
	@RemotingInclude
	List<GrupoLaudo> listarGrupoLaudo();
	
	@RemotingInclude
	List<Paciente> listarPacienteGrupoLaudo(GrupoLaudo grupoLaudo);
	
	@RemotingInclude
	void inserirGrupoLaudo(GrupoLaudo grupoLaudo)throws FacadeException;

	@RemotingInclude
	void alterarGrupoLaudo(GrupoLaudo grupoLaudo)throws FacadeException;

	@RemotingInclude
	void excluirGrupoLaudo(GrupoLaudo grupoLaudo)throws FacadeException;	

	@RemotingInclude
	void inserirGrupoLaudoPaciente(GrupoLaudoPaciente grupoLaudoPaciente) throws FacadeException;
	
	@RemotingInclude
	void excluirGrupoLaudoPaciente(GrupoLaudoPaciente grupoLaudoPaciente) throws FacadeException;
	//======================================================================

	//Procedimento Medico ==================================================
	@RemotingInclude
	List<ProcedimentoMedico> listarTipoProcedimentoMedico();

	ProcedimentoMedicoVO consultarProcedimentoMedico(Integer seqGrupoLaudo,Integer seqProcedimentoMedico,Integer seqMedico,Integer seqRelatorio);

	@RemotingInclude
	List<ProcedimentoMedico> listarProcedimentoMedicoPrincipal();
	
	@RemotingInclude
	List<ProcedimentoMedico> listarProcedimentoMedicoPrincipalPoliclinica();
	
	@RemotingInclude
	List<ProcedimentoMedico> listarProcedimentoMedicoSecundario();
	//======================================================================

	//Agendamento ===============================================================
		@RemotingInclude
		RetornoVO alterarSituacaoAgendamento(Agendamento agendamento);
		
		@RemotingInclude
		RetornoVO inserirAgendamento(Agendamento agendamento);
		
		@RemotingInclude
		PaginacaoVO listarAgendamentoPaginado(PaginacaoVO agendamento);
		
		@RemotingInclude
		RetornoVO reagendarAgendamento(Agendamento agendamento);
		
		@RemotingInclude
		List<Agendamento>listarAgendamentos(Medico medico);
		
		@RemotingInclude
		List<Agendamento>listarRelatorioAgendamentos(String sql);
		//======================================================================
		
		//Feriado ===============================================================
		@RemotingInclude
		RetornoVO alterarFeriado(Feriado feriado);
		
		@RemotingInclude
		RetornoVO excluirFeriado(Feriado feriado);
		
		@RemotingInclude
		RetornoVO inserirFeriado(Feriado feriado);
		
		@RemotingInclude
		List<Feriado> listarFeriado();
		
//		@RemotingInclude
//		PaginacaoVO listarMedicoPaginado(PaginacaoVO medico);
		//======================================================================

		//Licenca ===============================================================
		@RemotingInclude
		RetornoVO alterarLicenca(Licenca licenca);
		
		@RemotingInclude
		RetornoVO excluirLicenca(Licenca licenca);

		@RemotingInclude
		RetornoVO inserirLicenca(Licenca licenca);
		
		@RemotingInclude
		PaginacaoVO listarLicencaPaginado(PaginacaoVO licenca);
		//======================================================================
		
		//Medico ===============================================================
		@RemotingInclude
		RetornoVO alterarMedico(Medico medico);
		
		@RemotingInclude
		RetornoVO inserirMedico(Medico medico);
		
		@RemotingInclude
		List<Medico> listarMedico();
		
		@RemotingInclude
		PaginacaoVO listarMedicoPaginado(PaginacaoVO medico);
		
		@RemotingInclude
		Medico obterMedico(Medico medico);
		//======================================================================
		
		//Motivo ===============================================================
		@RemotingInclude
		RetornoVO alterarMotivo(Motivo motivo);
		
		@RemotingInclude
		RetornoVO excluirMotivo(Motivo motivo);
		
		@RemotingInclude
		RetornoVO inserirMotivo(Motivo motivo);
		
		@RemotingInclude
		List<Motivo> listarMotivo();
		//======================================================================

		//Relatorio ============================================================
		@RemotingInclude
		List<Relatorio> listarRelatorioProcedimento();
		//Setor ================================================================
		@RemotingInclude
		List<Setor> listar();
		
		//PreAtendimento========================================================
		@RemotingInclude
		RetornoVO inserirPreAtendimento(PreAtendimento preAtendimento);
		@RemotingInclude
		PaginacaoVO listar(PaginacaoVO preAtendimento);
		@RemotingInclude
		List<PreAtendimento> listarPaginado(PaginacaoVO preAtendimento);
		@RemotingInclude
		PaginacaoVO listarPreAtendimentoAtendidos(PaginacaoVO preAtendimento);
		@RemotingInclude
		PaginacaoVO listarPreAtendimentoPaginado(PaginacaoVO preAtendimento);
		@RemotingInclude
		PaginacaoVO listarPreAtendimentoPaginadoFiltro(PaginacaoVO preAtendimento);
		@RemotingInclude
		List<PreAtendimento> listarEncaminhamentoExterno(PreAtendimento preAtendimento);
		@RemotingInclude
		void excluirPreAtendimento(PreAtendimento preAtendimento);
		@RemotingInclude
		void alterarPreAtendimento(PreAtendimento preAtendimento);
		
		//Deficiência========================================================
		@RemotingInclude
		List<TipoDeficiencia> listarDeficiencia();
		void inserirTipoDeficiencia(TipoDeficiencia tipoDeficiencia);
		//ENCAMINHAMENTO EXTERNO========================================================
		@RemotingInclude
		List<EncaminhamentoExterno> listarEncaminhamento();
		@RemotingInclude
		List<EncaminhamentoExterno> listarEncaminhamentoFiltro(EncaminhamentoExterno encaminhamentoExterno);
		@RemotingInclude
		void inserirEncaminhamento(EncaminhamentoExterno encaminhamentoExterno);
		@RemotingInclude
		void alterarEncaminhamento(EncaminhamentoExterno encaminhamentoExterno);
		//SETOR ENCAMINHAMENTO EXTERNO========================================================
		@RemotingInclude
		List<SetorEncaminhamentoExterno> listarSetorEncaminhamentoExterno();
		void inserirSetorEncaminhamentoExterno(SetorEncaminhamentoExterno SetorencaminhamentoExterno);
		
		// ENTREVISTA ======================================================================
		@RemotingInclude
		RetornoVO inserirEntrevista(Entrevista entrevista);
		
	
	// PRATA
	// EQUIPAMENTO ======================================================================
	@RemotingInclude
	List<Equipamento> listarEquipamento();
	
	// FORNECEDOR ======================================================================
	@RemotingInclude
	RetornoVO alterarFornecedor(Fornecedor fornecedor);

	@RemotingInclude
	RetornoVO inserirFornecedor(Fornecedor fornecedor);
	
	@RemotingInclude
	PaginacaoVO listarFornecedorPaginado(PaginacaoVO fornecedor);
	
	@RemotingInclude
	List<Fornecedor> listarFornecedores();
	
	@RemotingInclude
	Fornecedor obterFornecedor(Fornecedor fornecedor);
	// MOVIMENTACAO ======================================================================
	@RemotingInclude
	RetornoVO alterarMovimentacao(Movimentacao movimentacao);
	
	@RemotingInclude
	RetornoVO inserirMovimentacao(Movimentacao movimentacao);
	
	@RemotingInclude
	PaginacaoVO listarMovimentacaoPaginado(PaginacaoVO movimentacao);
	
	@RemotingInclude
	PaginacaoVO listarMovimentacaoPaginadoParaRelatorio(PaginacaoVO movimentacao);

	@RemotingInclude
	PaginacaoVO listarMovimentacaoPaginadoComDataAutorizacaoPreenchida(PaginacaoVO movimentacao);
	
	@RemotingInclude
	List<Movimentacao> listarRelatorioMapa(Movimentacao movimentacao);
	
	@RemotingInclude
	List<Movimentacao> listarRelatorioMapaBpi(Movimentacao movimentacao);
	
	@RemotingInclude
	List<Movimentacao> listarRelatorioEncaminhamento(Movimentacao movimentacao);
	
	@RemotingInclude
	List<Movimentacao> listarRelatorioTermoCompromisso(Movimentacao movimentacao);
	
	@RemotingInclude
	List<Movimentacao> listarRelatorioEquipamentos(Movimentacao movimentacao);
	
	@RemotingInclude
	List<Movimentacao> listarRelatorioEquipamentosSintetico(Movimentacao movimentacao);

	// SIGTAP PROCEDIMENTO ======================================================================
	@RemotingInclude
	PaginacaoVO listarSigtapProcedimentoPaginado(PaginacaoVO sigtapProcedimento);
	
	@RemotingInclude
	PaginacaoVO listarSigtapCIDPaginado(PaginacaoVO sigtapCID);
	
	@RemotingInclude
	List<SigtapProcedimento> listarProcedimentosSigtap();
	
	@RemotingInclude
	SigtapCID obterSigtapCID(SigtapCID sigtapCID);
	
	@RemotingInclude
	SigtapProcedimento obterSigtapProcedimento(SigtapProcedimento sigtapProcedimento);
	
	//Equipe Medica ===============================================================
	@RemotingInclude
	RetornoVO alterarEquipeMedica(EquipeMedica equipeMedica);
			
	@RemotingInclude
	RetornoVO excluirEquipeMedica(EquipeMedica equipeMedica);
			
	@RemotingInclude
	RetornoVO inserirEquipeMedica(EquipeMedica equipeMedica);
			
	@RemotingInclude
	List<EquipeMedica> listarEquipeMedica();
	
	@RemotingInclude
	PaginacaoVO listarEquipeMedicaPaginado(PaginacaoVO equipeMedica);
	
	@RemotingInclude
	EquipeMedica obterEquipeMedica(EquipeMedica equipeMedica);

	@RemotingInclude
	CodigoDescricaoProcedimentoDTO consultarCodigoProcedimentoPrincipal(Integer seqProcedimentoPrincipalPoliclinica);

	SigtapProcedimento obterSigtapProcedimentoParaConsulta(String codigo);

	@RemotingInclude
	PaginacaoVO listarPaginandoAuditivo(String tipoPesquisa);
		
}