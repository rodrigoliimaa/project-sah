package br.com.infoliver.sah.negocio.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoliver.sah.configuracao.exception.FacadeException;
import br.com.infoliver.sah.negocio.bo.IAgendamentoBO;
import br.com.infoliver.sah.negocio.bo.IArquivoBO;
import br.com.infoliver.sah.negocio.bo.IEmpresaBO;
import br.com.infoliver.sah.negocio.bo.IEncaminhadorBO;
import br.com.infoliver.sah.negocio.bo.IEncaminhamentoExternoBO;
import br.com.infoliver.sah.negocio.bo.IEntrevistaBO;
import br.com.infoliver.sah.negocio.bo.IEquipamentoBO;
import br.com.infoliver.sah.negocio.bo.IEquipeMedicaBO;
import br.com.infoliver.sah.negocio.bo.IEscolaridadeBO;
import br.com.infoliver.sah.negocio.bo.IFeriadoBO;
import br.com.infoliver.sah.negocio.bo.IFornecedorBO;
import br.com.infoliver.sah.negocio.bo.IGrupoBO;
import br.com.infoliver.sah.negocio.bo.IGrupoLaudoBO;
import br.com.infoliver.sah.negocio.bo.IGrupoPermissaoBO;
import br.com.infoliver.sah.negocio.bo.IGrupoUsuarioBO;
import br.com.infoliver.sah.negocio.bo.ILicencaBO;
import br.com.infoliver.sah.negocio.bo.ILogAcessoBO;
import br.com.infoliver.sah.negocio.bo.IMedicoBO;
import br.com.infoliver.sah.negocio.bo.IMotivoBO;
import br.com.infoliver.sah.negocio.bo.IMovimentacaoBO;
import br.com.infoliver.sah.negocio.bo.IOcupacaoBO;
import br.com.infoliver.sah.negocio.bo.IPacienteBO;
import br.com.infoliver.sah.negocio.bo.IPermissaoBO;
import br.com.infoliver.sah.negocio.bo.IPreAtendimentoBO;
import br.com.infoliver.sah.negocio.bo.IProcedimentoMedicoBO;
import br.com.infoliver.sah.negocio.bo.IRacaBO;
import br.com.infoliver.sah.negocio.bo.IRelatorioBO;
import br.com.infoliver.sah.negocio.bo.ISetorBO;
import br.com.infoliver.sah.negocio.bo.ISetorEncaminhamentoExternoBO;
import br.com.infoliver.sah.negocio.bo.ISigtapBO;
import br.com.infoliver.sah.negocio.bo.ITipoDeficienciaBO;
import br.com.infoliver.sah.negocio.bo.ITipoResponsavelBO;
import br.com.infoliver.sah.negocio.bo.IUsuarioBO;
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
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;
import br.com.infoliver.sah.negocio.vo.LoginVO;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.ProcedimentoMedicoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

@Service("facade")
public class SistemaFacade implements ISistemaFacade {
	@Autowired private IPacienteBO pacienteBO;
	@Autowired private IArquivoBO arquivoBO;
	@Autowired private IEmpresaBO empresaBO;
	@Autowired private IEncaminhadorBO encaminhadorBO;
	@Autowired private IEscolaridadeBO escolaridadeBO;
	@Autowired private IOcupacaoBO ocupacaoBO;
	@Autowired private IRacaBO racaBO;
	@Autowired private ITipoResponsavelBO tipoResponsavelBO;
	@Autowired private IUsuarioBO usuarioBO;
	@Autowired private IPermissaoBO permissaoBO;
	@Autowired private ILogAcessoBO logAcessoBO;
	@Autowired private IGrupoUsuarioBO grupoUsuarioBO;
	@Autowired private IGrupoPermissaoBO grupoPermissaoBO;
	@Autowired private IGrupoBO grupoBO;	
	@Autowired private IGrupoLaudoBO grupoLaudoBO;	
	@Autowired private IProcedimentoMedicoBO procedimentoMedicoBO;
	@Autowired private IAgendamentoBO agendamentoBO;
	@Autowired private IFeriadoBO feriadoBO;
	@Autowired private IMedicoBO medicoBO;
	@Autowired private IRelatorioBO relatorioBO;
	@Autowired private ILicencaBO licencaBO;
	@Autowired private IMotivoBO motivoBO;
	@Autowired private ISetorBO setorBO;
	@Autowired private IPreAtendimentoBO preAtendimentoBO;
	@Autowired private ITipoDeficienciaBO deficienciaBO;
	@Autowired private IEncaminhamentoExternoBO encaminhamentoExternoBO;
	@Autowired private ISetorEncaminhamentoExternoBO setorEncaminhamentoExternoBO;
	@Autowired private IEntrevistaBO entrevistaBO;
	
	// PRATA
	@Autowired private IEquipamentoBO equipamentoBO;
	@Autowired private IFornecedorBO fornecedorBO;
	@Autowired private IMovimentacaoBO movimentacaoBO;
	@Autowired private ISigtapBO sigtapBO;
	
	@Autowired private IEquipeMedicaBO equipeMedicaBO;
	
	
	//Paciente ==========================================================
	@Override
	//@Secured("ROLE_INSERIR_PACIENTE")
	public RetornoVO inserirPaciente(Paciente paciente)throws FacadeException{
		return pacienteBO.inserir(paciente);
	}
	
	@Override
	//@Secured("ROLE_ALTERAR_PACIENTE")
	public RetornoVO alterarPaciente(Paciente paciente)throws FacadeException{
		return pacienteBO.alterar(paciente);
	}

	@Override
	//@Secured("ROLE_EXCLUIR_PACIENTE")
	public RetornoVO excluirPaciente(Paciente paciente)throws FacadeException{
		return pacienteBO.excluir(paciente);
	}
	
	@Override
	//@Secured("ROLE_LISTAR_PACIENTE")
	public PaginacaoVO listarPacientePaginado(PaginacaoVO paciente){
		return pacienteBO.listarPaginado(paciente);
	}

	@Override
	//@Secured("ROLE_LISTAR_PACIENTE")
	public List<Paciente> listarPaciente(){
		return pacienteBO.listar();
	}
	@Override
	//@Secured("ROLE_LISTAR_PACIENTE")
	public List<Paciente> listarPacienteRelatorio(PaginacaoVO paciente){
		return pacienteBO.listarPacienteRelatorio(paciente);
	}
	
	@Override
	public Paciente obterPaciente(Paciente paciente) {
		return pacienteBO.obter(paciente);
	}
	//====================================================================

	//Arquivo ============================================================
	@Override
	//@Secured("ROLE_EXCLUIR_ARQUIVO")
	public RetornoVO excluirArquivo(Arquivo arquivo)throws FacadeException{
		return arquivoBO.excluir(arquivo);
	}

	@Override
	//@Secured("ROLE_CONSULTAR_PARA_VISUALIZACAO_ARQUIVO")
	public Arquivo consultarParaVisualizacaoArquivo(Integer sequencial){
		return arquivoBO.consultarImagem(sequencial);
	}
	
	@Override
	//@Secured("ROLE_LISTAR_ARQUIVO")
	public List<Arquivo> listarArquivo(Integer sequencialPaciente){
		return arquivoBO.listar(sequencialPaciente);
	}
	//=====================================================================

	//Empresa =============================================================
	@Override
	//@Secured("ROLE_CONSULTAR_EMPRESA")
	public Empresa consultarEmpresa(){
		return empresaBO.consultar();
	}
	
	@Override
	//@Secured("ROLE_INSERIR_EMPRESA")
	public RetornoVO inserirEmpresa(Empresa empresa)throws FacadeException{
		return empresaBO.inserir(empresa);
	}
	
	@Override
	//@Secured("ROLE_ALTERAR_EMPRESA")
	public RetornoVO alterarEmpresa(Empresa empresa)throws FacadeException{
		return empresaBO.alterar(empresa);
	}
	//======================================================================

	//Encaminhador =========================================================
	@Override
	//@Secured("ROLE_LISTAR_ENCAMINHADOR")
	public List<Encaminhador> listarEncaminhador(){
		return encaminhadorBO.listar();
	}
	//======================================================================

	//Escolaridade =========================================================
	@Override
	//@Secured("ROLE_LISTAR_ESCOLARIDADE")
	public List<Escolaridade> listarEscolaridade(){
		return escolaridadeBO.listar();
	}
	//======================================================================

	//Ocupacao =============================================================
	@Override
	//@Secured("ROLE_LISTAR_OCUPACAO")
	public List<Ocupacao> listarOcupacao(){
		return ocupacaoBO.listar();
	}
	
	@Override
	public List<Ocupacao> pesquisarOcupacaoPorDescricao(String descricao) {
		return ocupacaoBO.pesquisarOcupacaoPorDescricao(descricao);
	}
	//======================================================================

	//Raca =================================================================
	@Override
	//@Secured("ROLE_LISTAR_RACA")
	public List<Raca> listarRaca(){
		return racaBO.listar();
	}
	//======================================================================

	//TipoResponsavel ======================================================
	@Override
	//@Secured("ROLE_LISTAR_TIPO_RESPONSAVEL")
	public List<TipoResponsavel> listarTipoResponsavel(){
		return tipoResponsavelBO.listar();
	}
	//======================================================================
	
	//Usuario ==============================================================	
	@Override
	public LoginVO acessarSistema(Usuario usuario)throws FacadeException {
		return usuarioBO.acessar(usuario);
	}
	
	@Override
	//@Secured("ROLE_INSERIR_USUARIO")
	public RetornoVO inserirUsuario(Usuario usuario) throws FacadeException {
		return usuarioBO.inserir(usuario);
	}

	@Override
	//@Secured("ROLE_ALTERAR_USUARIO")
	public RetornoVO alterarUsuario(Usuario usuario) throws FacadeException {
		return usuarioBO.alterar(usuario);
	}

	@Override
	//@Secured("ROLE_EXCLUIR_USUARIO")
	public RetornoVO excluirUsuario(Usuario usuario) throws FacadeException {
		return usuarioBO.excluir(usuario);
	}

	@Override
	//@Secured("ROLE_LISTAR_USUARIO")
	public PaginacaoVO listarUsuarioPaginado(PaginacaoVO usuario){
		return usuarioBO.listarPaginado(usuario);
	}

	@Override
	//@Secured("ROLE_LISTAR_USUARIO")
	public List<Usuario> listarUsuario(){
		return usuarioBO.listar();
	}
	
	@Override
	//@Secured("ROLE_LISTAR_USUARIO_DO_GRUPO")
	public List<Usuario> listarUsuarioDoGrupo(Integer sequencialGrupo) {
		return usuarioBO.listarUsuarioDoGrupo(sequencialGrupo);
	}

	@Override
	//@Secured("ROLE_LISTAR_USUARIO_DIFERENTE_DO_GRUPO")
	public List<Usuario> listarUsuarioDiferenteDoGrupo(Integer sequencialGrupo) {
		return usuarioBO.listarUsuarioDiferenteDoGrupo(sequencialGrupo);
	}
	//======================================================================

	//Permissao ============================================================
	@Override
	//@Secured("ROLE_LISTAR_PERMISSAO_DO_GRUPO")
	public List<Permissao> listarPermissaoDoGrupo(Integer sequencialGrupo) {
		return permissaoBO.listarPermissaoDoGrupo(sequencialGrupo);
	}

	@Override
	//@Secured("ROLE_LISTAR_PERMISSAO_DIFERENTE_DO_GRUPO")
	public List<Permissao> listarPermissaoDiferenteDoGrupo(Integer sequencialGrupo) {
		return permissaoBO.listarPermissaoDiferenteDoGrupo(sequencialGrupo);
	}
	//======================================================================

	//LogAcesso ============================================================
	@Override
	//@Secured("ROLE_LISTAR_LOG_ACESSO")
	public PaginacaoVO listarLogAcessoPaginado(PaginacaoVO logAcesso) {
		return logAcessoBO.listarPaginado(logAcesso);
	}
	//======================================================================

	//GrupoUsuario =========================================================
	@Override
	//@Secured("ROLE_INSERIR_GRUPO_USUARIO")
	public void inserirGrupoUsuario(GrupoUsuario grupoUsuario) throws FacadeException {
		grupoUsuarioBO.inserir(grupoUsuario);
	}

	@Override
	//@Secured("ROLE_EXCLUIR_GRUPO_USUARIO")
	public void excluirGrupoUsuario(GrupoUsuario grupoUsuario) throws FacadeException {
		grupoUsuarioBO.excluir(grupoUsuario);
	}
	//======================================================================
	
	//GrupoPermissao =======================================================	
	@Override
	//@Secured("ROLE_INSERIR_GRUPO_PERMISSAO")
	public void inserirGrupoPermissao(GrupoPermissao grupoPermissao) throws FacadeException {
		grupoPermissaoBO.inserir(grupoPermissao);
	}

	@Override
	//@Secured("ROLE_EXCLUIR_GRUPO_PERMISSAO")
	public void excluirGrupoPermissao(GrupoPermissao grupoPermissao) throws FacadeException {
		grupoPermissaoBO.excluir(grupoPermissao);
	}
	//======================================================================
	
	//Grupo ================================================================
	@Override
	//@Secured("ROLE_INSERIR_GRUPO")
	public void inserirGrupo(Grupo grupo) throws FacadeException {
		grupoBO.inserir(grupo);
	}

	@Override
	//@Secured("ROLE_ALTERAR_GRUPO")
	public void alterarGrupo(Grupo grupo) throws FacadeException {
		grupoBO.alterar(grupo);
	}

	@Override
	//@Secured("ROLE_EXCLUIR_GRUPO")
	public void excluirGrupo(Grupo grupo) throws FacadeException {
		grupoBO.excluir(grupo);
	}

	@Override
	//@Secured("ROLE_LISTAR_GRUPO")
	public List<Grupo> listarGrupo() {
		return grupoBO.listar();
	}
	//======================================================================

	//Grupo Laudo ==========================================================
	@Override
	//@Secured("ROLE_LISTAR_GRUPO_LAUDO")
	public List<GrupoLaudo> listarGrupoLaudo() {
		return grupoLaudoBO.listar();
	}
	
	@Override
	//@Secured("ROLE_LISTAR_PACIENTE_GRUPO_LAUDO")
	public List<Paciente> listarPacienteGrupoLaudo(GrupoLaudo grupoLaudo) {
		return grupoLaudoBO.listarPacienteGrupoLaudo(grupoLaudo);
	}
	
	@Override
	//@Secured("ROLE_INSERIR_GRUPO_LAUDO")
	public void inserirGrupoLaudo(GrupoLaudo grupoLaudo) throws FacadeException {
		grupoLaudoBO.inserir(grupoLaudo);
	}

	@Override
	//@Secured("ROLE_ALTERAR_GRUPO_LAUDO")
	public void alterarGrupoLaudo(GrupoLaudo grupoLaudo) throws FacadeException {
		grupoLaudoBO.alterar(grupoLaudo);
	}

	@Override
	//@Secured("ROLE_EXCLUIR_GRUPO_LAUDO")
	public void excluirGrupoLaudo(GrupoLaudo grupoLaudo) throws FacadeException {
		grupoLaudoBO.excluir(grupoLaudo);
	}
	
	@Override
	//@Secured("ROLE_INSERIR_GRUPO_LAUDO_PACIENTE")
	public void inserirGrupoLaudoPaciente(GrupoLaudoPaciente grupoLaudoPaciente)throws FacadeException {
		grupoLaudoBO.inserirGrupoLaudoPaciente(grupoLaudoPaciente);
	}

	@Override
	//@Secured("ROLE_EXCLUIR_GRUPO_LAUDO_PACIENTE")
	public void excluirGrupoLaudoPaciente(GrupoLaudoPaciente grupoLaudoPaciente)throws FacadeException {
		grupoLaudoBO.excluirGrupoLaudoPaciente(grupoLaudoPaciente);
	}
	//======================================================================

	//Procedimento Medico ==================================================
	@Override
	//@Secured("ROLE_LISTAR_TIPO_PROCEDIMENTO_MEDICO")
	public List<ProcedimentoMedico> listarTipoProcedimentoMedico() {
		return procedimentoMedicoBO.listarTipoProcedimentoMedico();
	}
	
	@Override
	public ProcedimentoMedicoVO consultarProcedimentoMedico(Integer seqGrupoLaudo,Integer seqProcedimentoMedico,Integer seqMedico,Integer seqRelatorio){
		return procedimentoMedicoBO.consultarProcedimentoMedico(seqGrupoLaudo,seqProcedimentoMedico,seqMedico, seqRelatorio);
	}
	
	public CodigoDescricaoProcedimentoDTO consultarCodigoProcedimentoPrincipal(Integer seqProcedimentoPrincipalPoliclinica) {
		return procedimentoMedicoBO.consultarCodigoProcedimentoPrincipal(seqProcedimentoPrincipalPoliclinica);
	}
	
	@Override
	//@Secured("ROLE_LISTAR_PROCEDIMENTO_MEDICO_PRINCIPAL")
	public List<ProcedimentoMedico> listarProcedimentoMedicoPrincipal() {
		return procedimentoMedicoBO.listarProcedimentoMedicoPrincipal();
	}
	
	@Override
	public List<ProcedimentoMedico> listarProcedimentoMedicoPrincipalPoliclinica() {
		return procedimentoMedicoBO.listarProcedimentoMedicoPrincipalPoliclinica();
	}
	@Override
	//@Secured("ROLE_LISTAR_PROCEDIMENTO_MEDICO_SECUNDARIO")
	public List<ProcedimentoMedico> listarProcedimentoMedicoSecundario() {
		return procedimentoMedicoBO.listarProcedimentoMedicoSecundario();
	}
	//======================================================================

	//Agendamento ===============================================================
		@Override
		public RetornoVO alterarSituacaoAgendamento(Agendamento agendamento) {
			return agendamentoBO.alterarSituacao(agendamento);
		}
		
		@Override
		public RetornoVO inserirAgendamento(Agendamento agendamento) {
			return agendamentoBO.inserir(agendamento);
		}
		
		@Override
		public PaginacaoVO listarAgendamentoPaginado(PaginacaoVO agendamento) {
			return agendamentoBO.listarPaginado(agendamento);
		}
		
		@Override
		public RetornoVO reagendarAgendamento(Agendamento agendamento) {
			return agendamentoBO.reagendarAgendamento(agendamento);
		}
		
		@Override
		public List<Agendamento> listarAgendamentos(Medico medico) {
			
			return agendamentoBO.listarAgendamentos(medico);
		}
		
		@Override
		public	List<Agendamento> listarRelatorioAgendamentos(String sql) {
			return agendamentoBO.listarAgendamentoRelatorio(sql);
		}
		//======================================================================
		
		//Feriado ===============================================================
		@Override
		public RetornoVO alterarFeriado(Feriado feriado) {
			return feriadoBO.alterar(feriado);
		}
		
		@Override
		public RetornoVO excluirFeriado(Feriado feriado) {
			return feriadoBO.excluirFeriado(feriado);
		}
		
		@Override
		public RetornoVO inserirFeriado(Feriado feriado) {
			return feriadoBO.inserir(feriado);
		}
		
		@Override
		public List<Feriado> listarFeriado() {
			return feriadoBO.listar();
		}
		//======================================================================
		
		//Feriado ===============================================================
		@Override
		public RetornoVO alterarLicenca(Licenca licenca) {
			return licencaBO.alterar(licenca);
		}
		
		@Override
		public RetornoVO excluirLicenca(Licenca licenca) {
			return licencaBO.excluir(licenca);
		}
		
		@Override
		public RetornoVO inserirLicenca(Licenca licenca) {
			return licencaBO.inserir(licenca);
		}
		
		@Override
		public PaginacaoVO listarLicencaPaginado(PaginacaoVO licenca) {
			return licencaBO.listarPaginado(licenca);
		}
		//======================================================================

		//Medico ===============================================================
		@Override
		public RetornoVO alterarMedico(Medico medico) {
			return medicoBO.alterar(medico);
		}
		
		@Override
		public RetornoVO inserirMedico(Medico medico) {
			return medicoBO.inserir(medico);
		}
		
		@Override
		//@Secured("ROLE_LISTAR_MEDICO")
		public List<Medico> listarMedico() {
			return medicoBO.listar();
		}
		
		@Override
		public PaginacaoVO listarMedicoPaginado(PaginacaoVO medico) {
			return medicoBO.listarPaginado(medico);
		}
		
		@Override
		public Medico obterMedico(Medico medico) {
			return medicoBO.obter(medico);
		}
		//======================================================================
		
		//Motivo ===============================================================
		@Override
		public RetornoVO alterarMotivo(Motivo motivo) {
			return motivoBO.alterar(motivo);
		}
		
		@Override
		public RetornoVO excluirMotivo(Motivo motivo) {
			return motivoBO.excluir(motivo);
		}
		
		@Override
		public RetornoVO inserirMotivo(Motivo motivo) {
			return motivoBO.inserir(motivo);
		}
		
		@Override
		public List<Motivo> listarMotivo() {
			return motivoBO.listar();
		}
	//Setor ======================================================================
		public List<Setor> listar(){
			return setorBO.listar();
		}
		
	//Relatorio ===================================================================
	@Override
	//@Secured("ROLE_LISTAR_RELATORIO_PROCEDIMENTO")
	public List<Relatorio> listarRelatorioProcedimento(){
		return relatorioBO.listarRelatorioProcedimento();
	}
	//PreAtendimento================================================================
	@Override
	public List<PreAtendimento> listarEncaminhamentoExterno(PreAtendimento preAtendimento) {
		return preAtendimentoBO.listarEncaminhamentoExterno(preAtendimento);
	}

	@Override
	public PaginacaoVO listar(PaginacaoVO preAtendimento) {
		
		return  preAtendimentoBO.listar(preAtendimento);
	}
	
	@Override
	public List<PreAtendimento> listarPaginado(PaginacaoVO preAtendimento) {
		
		return preAtendimentoBO.listarPaginado(preAtendimento);
	}
	@Override
	public PaginacaoVO listarPreAtendimentoPaginadoFiltro(PaginacaoVO preAtendimento) {
		return preAtendimentoBO.listarPaginadoFiltro(preAtendimento);
	}
	@Override
	public PaginacaoVO listarPreAtendimentoPaginado(PaginacaoVO preAtendimento) {
		
		return preAtendimentoBO.listarPreAtendimentoPaginado(preAtendimento);
	}

	@Override
	public PaginacaoVO listarPreAtendimentoAtendidos(PaginacaoVO preAtendimento) {
		return preAtendimentoBO.listarPreAtendimentoAtendidos(preAtendimento);
	}
	@Override
	public RetornoVO inserirPreAtendimento(PreAtendimento preAtendimento){
		return preAtendimentoBO.inserirPreAtendimento(preAtendimento);
	}
	@Override
	public void excluirPreAtendimento(PreAtendimento preAtendimento) {
		preAtendimentoBO.excluir(preAtendimento);
		
	}
	@Override
	public void alterarPreAtendimento(PreAtendimento preAtendimento) {
		preAtendimentoBO.alterar(preAtendimento);
		
	}

	//DEFICIÊNCIA ===================================================================
	@Override
	public List<TipoDeficiencia> listarDeficiencia() {		
		return deficienciaBO.listar();
	}
	
	@Override
	public void inserirTipoDeficiencia(TipoDeficiencia tipoDeficiencia) {
		deficienciaBO.inserir(tipoDeficiencia);		
		
	}

	//Encaminhamento Externo==========================================================
	@Override
	public List<EncaminhamentoExterno> listarEncaminhamento() {
		return encaminhamentoExternoBO.listarEncaminhamento();
	}
	
	@Override
	public List<EncaminhamentoExterno> listarEncaminhamentoFiltro(EncaminhamentoExterno encaminhamentoExterno) {
		
		return encaminhamentoExternoBO.listarEncaminhamentoFiltro(encaminhamentoExterno);
	}
	@Override
	public void inserirEncaminhamento(EncaminhamentoExterno encaminhamentoExterno) {
		 encaminhamentoExternoBO.inserirEncaminhamento(encaminhamentoExterno);
		
	}
	@Override
	public void alterarEncaminhamento(EncaminhamentoExterno encaminhamentoExterno) {
		encaminhamentoExternoBO.alterarEncaminhamento(encaminhamentoExterno);
		
	}


	@Override
	public List<SetorEncaminhamentoExterno> listarSetorEncaminhamentoExterno() {
		
		return setorEncaminhamentoExternoBO.listarSetor();
	}

	@Override
	public void inserirSetorEncaminhamentoExterno(SetorEncaminhamentoExterno encaminhamentoExterno) {
		setorEncaminhamentoExternoBO.inserirSetor(encaminhamentoExterno);
		
	}

	@Override
	public RetornoVO inserirEntrevista(Entrevista entrevista) {
		
		return entrevistaBO.inserirEntrevista(entrevista);
	}

	
	// PRATA
	//Equipamento ===============================================================
	@Override
	public List<Equipamento> listarEquipamento() {
		return equipamentoBO.listar();
	}
	
	//Fornecedor ===============================================================
	@Override
	public RetornoVO alterarFornecedor(Fornecedor fornecedor) {
		return fornecedorBO.alterar(fornecedor);
	}

	@Override
	public RetornoVO inserirFornecedor(Fornecedor fornecedor) {
		return fornecedorBO.inserir(fornecedor);
	}
	
	@Override
	public List<Fornecedor> listarFornecedores() {
		return fornecedorBO.listarFornecedores();
	}
	
	@Override
	public PaginacaoVO listarFornecedorPaginado(PaginacaoVO fornecedor) {
		return fornecedorBO.listarPaginado(fornecedor);
	}
	
	@Override
	public Fornecedor obterFornecedor(Fornecedor fornecedor) {
		return fornecedorBO.obter(fornecedor);
	}
	//Movimentacao===============================================================
	@Override
	public RetornoVO alterarMovimentacao(Movimentacao movimentacao) {
		return movimentacaoBO.alterar(movimentacao);
	}
	
	@Override
	public RetornoVO inserirMovimentacao(Movimentacao movimentacao) {
		return movimentacaoBO.inserir(movimentacao);
	}
	
	public void excluirMovimentacao(Movimentacao movimentacao) {
		movimentacaoBO.excluirMovimentacao(movimentacao);
	}
	
	@Override			
	public PaginacaoVO listarMovimentacaoPaginado(
			PaginacaoVO movimentacao) {
		
		return movimentacaoBO.listarPaginado(movimentacao);
	}
	
	@Override			
	public PaginacaoVO listarPaginandoAuditivo(String tipoPesquisa) {
		return movimentacaoBO.listarPaginandoAuditivo(tipoPesquisa);
	}	

	@Override			
	public PaginacaoVO listarMovimentacaoPaginadoParaRelatorio(
			PaginacaoVO movimentacao) {
		
		return movimentacaoBO.listarPaginadoParaRelatorio(movimentacao);
	}

	@Override
	public PaginacaoVO listarMovimentacaoPaginadoComDataAutorizacaoPreenchida(
			PaginacaoVO movimentacao) {
		List<Movimentacao> listarRelatorioMapaBpi = movimentacaoBO.listarRelatorioMapaBpi((Movimentacao)movimentacao.getEntidade());
		
		PaginacaoVO paginacaoVO = new PaginacaoVO();
		paginacaoVO.setEntidade(listarRelatorioMapaBpi);
		paginacaoVO.setInicioPaginacao(0);
		paginacaoVO.setQuantidadePaginacao(10);
		paginacaoVO.setTotalRegistros(listarRelatorioMapaBpi.size());
		
		return paginacaoVO;
		
	}
	
	@Override
	public List<Movimentacao> listarRelatorioMapa(Movimentacao movimentacao) {
	
		return movimentacaoBO.listarRelatorioMapa(movimentacao);
	}
	
	@Override
	public List<Movimentacao> listarRelatorioMapaBpi(Movimentacao movimentacao) {

		return movimentacaoBO.listarRelatorioMapaBpi(movimentacao);
	}

	
	@Override
	public List<Movimentacao> listarRelatorioEncaminhamento(
			Movimentacao movimentacao) {
		return movimentacaoBO.listarRelatorioEncaminhamento(movimentacao);
	}	
	@Override
	public List<Movimentacao> listarRelatorioTermoCompromisso(
			Movimentacao movimentacao) {
			return movimentacaoBO.listarRelatorioTermoCompromisso(movimentacao);
	}
	@Override
	public List<Movimentacao> listarRelatorioEquipamentos(
			Movimentacao movimentacao) {
		
		return movimentacaoBO.listarRelatorioEquipamentos(movimentacao);
	}
	@Override
	public List<Movimentacao> listarRelatorioEquipamentosSintetico(
			Movimentacao movimentacao) {
		
		return movimentacaoBO.listarRelatorioEquipamentosSintetico(movimentacao);
	}
	
	//SigtapProcedimento ===============================================================
	@Override
	public PaginacaoVO listarSigtapProcedimentoPaginado(
			PaginacaoVO sigtapProcedimento) {
		return sigtapBO.listarProcedimentoPaginado(sigtapProcedimento);
	}
	
	@Override
	public PaginacaoVO listarSigtapCIDPaginado(PaginacaoVO sigtapCID) {
		return sigtapBO.listarCIDPaginado(sigtapCID);
	}

	@Override
	public List<SigtapProcedimento> listarProcedimentosSigtap() {
		
		return sigtapBO.listarProcedimentos();
	}

	@Override
	public SigtapCID obterSigtapCID(SigtapCID sigtapCID) {
		return sigtapBO.obterCid(sigtapCID);
	}

	@Override
	public SigtapProcedimento obterSigtapProcedimento(
			SigtapProcedimento sigtapProcedimento) {
		return sigtapBO.obterProcedimento(sigtapProcedimento);
	}

	@Override
	public SigtapProcedimento obterSigtapProcedimentoParaConsulta(
			String codigo) {
		return sigtapBO.obterSigtapProcedimentoParaConsulta(codigo);
	}

	@Override
	public RetornoVO alterarEquipeMedica(EquipeMedica equipeMedica) {
		return equipeMedicaBO.alterar(equipeMedica);
	}

	@Override
	public RetornoVO excluirEquipeMedica(EquipeMedica equipeMedica) {
		return equipeMedicaBO.excluir(equipeMedica);
	}

	@Override
	public RetornoVO inserirEquipeMedica(EquipeMedica equipeMedica) {
		return equipeMedicaBO.inserir(equipeMedica);
	}

	@Override
	public List<EquipeMedica> listarEquipeMedica() {
		return equipeMedicaBO.listar();
	}

	@Override
	public PaginacaoVO listarEquipeMedicaPaginado(PaginacaoVO equipeMedica) {
		return equipeMedicaBO.listarPaginado(equipeMedica);
	}

	@Override
	public EquipeMedica obterEquipeMedica(EquipeMedica equipeMedica) {
		return equipeMedicaBO.obter(equipeMedica);
	}

	@Override
	public Usuario obterUsuario(Usuario usuario) {
		return usuarioBO.obter(usuario);
	}

	

	
	
}