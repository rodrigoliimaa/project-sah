package leonelcasado.componentes{
	import mx.controls.Image;

	public class CustomImagem extends Image{
		
		public function CustomImagem(){
			super();
			useHandCursor=true;
			buttonMode=true;
		}

		override public function initialize():void{
			super.initialize();
		}		
	}
}