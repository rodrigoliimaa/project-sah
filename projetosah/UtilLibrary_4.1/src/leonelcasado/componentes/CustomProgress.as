package leonelcasado.componentes{
	import mx.preloaders.DownloadProgressBar;
	
	public class CustomProgress extends DownloadProgressBar{
		
		public function CustomProgress(){
        	super();
            downloadingLabel = "Carregando...";
            initializingLabel =  "Iniciando...";			
		}

		override public function initialize():void{
			super.initialize();
		}
	}
}