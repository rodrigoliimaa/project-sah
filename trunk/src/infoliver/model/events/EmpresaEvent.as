package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class EmpresaEvent extends CairngormEvent{
		public static const CONSULTAR:String = "EmpresaEvent.consultarEmpresa";
		public static const INSERIR:String = "EmpresaEvent.inserirEmpresa";
		public static const ALTERAR:String = "EmpresaEvent.alterarEmpresa";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function EmpresaEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}