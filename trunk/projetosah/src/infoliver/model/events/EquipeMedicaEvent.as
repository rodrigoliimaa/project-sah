package infoliver.model.events
{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;

	public class EquipeMedicaEvent extends CairngormEvent {
		public static const ALTERAR:String = "EquipeMedicaEvent.alterarEquipeMedica";
		public static const EXCLUIR:String = "EquipeMedicaEvent.excluirEquipeMedica";
		public static const INSERIR:String = "EquipeMedicaEvent.inserirEquipeMedica";
		public static const LISTAR:String = "EquipeMedicaEvent.listarEquipeMedica";
		public static const LISTA_PAGINADO:String = "EquipeMedicaEvent.listarEquipeMedicaPaginado";
		public static const OBTER:String = "EquipeMedicaEvent.obterEquipeMedica";
		
		private var idDestination:String="facade";
		
		public function EquipeMedicaEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
		
	}
}