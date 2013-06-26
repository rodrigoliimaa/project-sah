package infoliver.util{
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.ProgressEvent;
	import flash.net.FileReference;
	import flash.net.URLRequest;
	import infoliver.view.componentes.ProgressBarDownload;
	import mx.managers.PopUpManager;
	
	public class DownloadFile{
		private static var popUp:ProgressBarDownload;
		private static var fileRef:FileReference = null;
		
		public function DownloadFile(){
		}

		public static function download(fileName:String,serverUrl:String,parentPopUp:DisplayObject):void{
			var fileName:String = fileName;
			var serverUrl:String = serverUrl;
			//--------------------------------------------------
			var urlRequest:URLRequest = new URLRequest(serverUrl);
			fileRef = new FileReference();
			fileRef.download(urlRequest, fileName);
			fileRef.addEventListener(Event.SELECT, function(...args):void {
				popUp = ProgressBarDownload(PopUpManager.createPopUp(parentPopUp,ProgressBarDownload,true));
				PopUpManager.centerPopUp(popUp);
			});
			fileRef.addEventListener(ProgressEvent.PROGRESS, setDownloadProgress);
			fileRef.addEventListener(Event.COMPLETE, completeHandler);
		}
		
		private static function setDownloadProgress(event:ProgressEvent):void{
			popUp.pbProgress.setProgress(event.bytesLoaded, event.bytesTotal);
			popUp.pbProgress.label = "Baixando Arquivo [" + Math.round(event.bytesLoaded / event.bytesTotal * 100).toString() + "%]";
		}
		
		private static function completeHandler(event:Event):void{
			popUp.pbProgress.label = "Arquivo Baixado Com Sucesso!";
			popUp.btnFechar.enabled = true;
			fileRef = null;
		} 
	}
}