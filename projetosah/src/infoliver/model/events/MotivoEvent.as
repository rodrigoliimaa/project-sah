package infoliver.model.events
{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;

	public class MotivoEvent extends CairngormEvent{
		
		public static const ALTERAR:String = "MotivoEvent.alterarMotivo";
		public static const EXCLUIR:String = "MotivoEvent.excluirMotivo";
		public static const INSERIR:String = "MotivoEvent.inserirMotivo";;
		public static const LISTAR:String = "MotivoEvent.listarMotivo";

		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function MotivoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}
}