package infoliver.control{
	import infoliver.model.events.*;
	
	import leonelcasado.com.adobe.cairngorm.control.FrontController;
	
	public class FController extends FrontController{
		
		public function FController(){
			registerCommadClass(ArquivoEvent);
			registerCommadClass(AutenticacaoEvent);
			registerCommadClass(EmpresaEvent);
			registerCommadClass(EncaminhadorEvent);
			registerCommadClass(EscolaridadeEvent);
			registerCommadClass(GrupoEvent);
			registerCommadClass(GrupoLaudoEvent);
			registerCommadClass(GrupoUsuarioEvent);
			registerCommadClass(GrupoPermissaoEvent);
			registerCommadClass(LogAcessoEvent);
			registerCommadClass(AgendamentoEvent);
			registerCommadClass(FeriadoEvent);
			registerCommadClass(LicencaEvent);
			registerCommadClass(MedicoEvent);
			registerCommadClass(MotivoEvent);			
			registerCommadClass(OcupacaoEvent);
			registerCommadClass(PacienteEvent);
			registerCommadClass(PermissaoEvent);
			registerCommadClass(ProcedimentoMedicoEvent);
			registerCommadClass(RacaEvent);
			registerCommadClass(RelatorioEvent);
			registerCommadClass(SetorEvent);
			registerCommadClass(TipoResponsavelEvent);
			registerCommadClass(UsuarioEvent);
			registerCommadClass(PreAtendimentoEvent);
			registerCommadClass(TipoDeficienciaEvent);
			registerCommadClass(EncaminhamentoExternoEvent);
			registerCommadClass(SetorEncaminhamentoExternoEvent);			
			// PRATA
			registerCommadClass(FornecedorEvent)
			registerCommadClass(MovimentacaoEvent);
			registerCommadClass(SigtapEvent);
			registerCommadClass(EquipamentoEvent);
			
			registerCommadClass(EquipeMedicaEvent);
			
		}		
	}
}