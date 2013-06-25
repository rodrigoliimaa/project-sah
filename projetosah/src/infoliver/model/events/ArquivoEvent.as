package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class ArquivoEvent extends CairngormEvent{
		public static const LISTAR:String = "ArquivoEvent.listarArquivo";
		public static const EXCLUIR:String = "ArquivoEvent.excluirArquivo";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function ArquivoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}