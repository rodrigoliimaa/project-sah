package leonelcasado.componentes{
	import mx.controls.Alert;
	import mx.utils.ObjectUtil;

	/**
	 * <b>Projeto:</b>UtilLibrary_4x</br>
	 * <b>Autor:</b>leonelcasado <b>Data de Criação:</b>06/06/2010</br>
	 * <b>Motivação:</b>
	 * Facilitar o processo de debug na aplicação.
	 */
	public class Debugger extends ObjectUtil{
		
		public function Debugger(){
		}
		
		/**
		 * <b>Motivação:</b>Método utilizado para exibir um objeto no Alert.</b><br/>
		 * <b>Como Usar:</b><br/>
		 * <b>Debugger.showObjectAlert(objetoQualquer);</b></br>
		 * <b>Autor:</b>leonelcasado <b>Data de Criação:</b>06/06/2010</br>
		 */		
		public static function showObjectAlert(obj:*):void{
			Alert.show(ObjectUtil.toString(obj),"Debug");
		}

		/**
		 * <b>Motivação:</b>Método utilizado para exibir um objeto no console.</b><br/>
		 * <b>Como Usar:</b><br/>
		 * <b>Debugger.showObjectTrace(objetoQualquer);</b></br>
		 * <b>Autor:</b>leonelcasado <b>Data de Criação:</b>06/06/2010</br>
		 */		
		public static function showObjectTrace(obj:*):void{
			trace(ObjectUtil.toString(obj));
		}
	}
}