package leonelcasado.componentes{
	import flash.display.Sprite;
	
	import leonelcasado.componentes.alertaslide.impl.AlertaSlide;
	
	import mx.controls.Alert;
	
	/**
	 * Projeto: UtilLibrary_4x</br>
	 * Autor: leonelcasado Data de Criação: 16/05/2010</br>
	 * Motivação: Facilitar a utilização do componente Alert
	 * customizando os parâmetros:title,image e default button.
	 */
	public class Alerta extends Alert{
		
		public function Alerta(){
			super();
		}

		override public function initialize():void{
			super.initialize();
		}

		public static function success(message:String,title:String=null,closehandler:Function=null,parent:Sprite=null):Alert{
			return show(message, title==null?"Atenção":title, Alert.OK, parent, closehandler, Imagens.iconCheck);
		}

		public static function info(message:String,title:String=null,closehandler:Function=null,parent:Sprite=null):Alert{
			return show(message, title==null?"Atenção":title, Alert.OK, parent, closehandler, Imagens.iconInformation);
		}

		public static function message(title:String,message:String):Alert{
			return show(message,title, Alert.OK, null, null, Imagens.iconInformation);
		}
		
		public static function error(message:String,title:String=null,closehandler:Function=null,parent:Sprite=null):Alert{
			return show(message, title==null?"Atenção":title, Alert.OK, parent, closehandler, Imagens.iconError);
		}

		public static function restriction(message:String,title:String=null,closehandler:Function=null,parent:Sprite=null):Alert{
			return show(message, title==null?"Atenção":title, Alert.OK, parent, closehandler, Imagens.iconRestricted);
		}
		
		public static function confirm(message:String,closehandler:Function=null,title:String=null,defaultButton:uint=2,parent:Sprite=null):Alert{
			Alert.yesLabel = "Sim";
			Alert.noLabel = "Não";
			return show(message, title==null?"Atenção":title, Alert.YES | Alert.NO, parent, closehandler, Imagens.iconHelp, defaultButton);
		}
		
		public static function aviso(title:String,message:String,duracao:int=3000):AlertaSlide{
		 	return AlertaSlide.show(title,message,0,duracao);
		}
	}
}