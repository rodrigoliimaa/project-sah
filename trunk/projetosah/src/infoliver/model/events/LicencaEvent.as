package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class LicencaEvent extends CairngormEvent{
		public static const ALTERAR:String = "LicencaEvent.alterarLicenca";
		public static const EXCLUIR:String = "LicencaEvent.excluirLicenca";;
		public static const INSERIR:String = "LicencaEvent.inserirLicenca";
//		public static const LISTAR:String = "LicencaEvent.listarLicenca";
		public static const LISTAR_PAGINADO:String = "LicencaEvent.listarLicencaPaginado";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function LicencaEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}
}