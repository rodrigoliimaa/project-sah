package leonelcasado.componentes{
	
	public class CustomEvent{
		
		public function CustomEvent(){
		}
		
		public static function dispararEvento(classeEvento:*,evento:String,telaResult:*=null,object:*=null):void {
			new classeEvento(evento,telaResult,object).dispatch();
		}
	}
}