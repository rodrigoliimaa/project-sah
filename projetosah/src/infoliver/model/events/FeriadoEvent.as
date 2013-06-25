package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class FeriadoEvent extends CairngormEvent{
		
		public static const ALTERAR:String = "FeriadoEvent.alterarFeriado";
		public static const EXCLUIR:String = "FeriadoEvent.excluirFeriado";
		public static const INSERIR:String = "FeriadoEvent.inserirFeriado";
		public static const LISTAR:String = "FeriadoEvent.listarFeriado";
		
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function FeriadoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}