package infoliver.model.events
{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class EquipamentoEvent extends CairngormEvent{
		public static const LISTA:String = "EquipamentoEvent.listarEquipamento";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function EquipamentoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}
}