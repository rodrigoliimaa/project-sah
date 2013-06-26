package infoliver.model.events
{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;

	public class EntrevistaEvent extends CairngormEvent
	{
		public static const INSERIR:String = "PreAtendimentoEvent.inserirEntrevista";		
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function EntrevistaEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}
}