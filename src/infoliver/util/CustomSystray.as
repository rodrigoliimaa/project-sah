import flash.desktop.NativeApplication;
import flash.display.NativeWindow;
import leonelcasado.componentes.Imagens;
import mx.core.BitmapAsset;

private var dockImage:BitmapAsset;
private var win:NativeWindow;

private function includeSysTray():void {
	win = stage.nativeWindow;
	dockImage = new Imagens.iconSysTray as BitmapAsset;
	if (NativeApplication.supportsSystemTrayIcon){
		SystemTrayIcon(NativeApplication.nativeApplication.icon).tooltip = "L.I.H-Soluções Tecnológicas";
		SystemTrayIcon(NativeApplication.nativeApplication.icon).addEventListener(MouseEvent.CLICK, undock);
		SystemTrayIcon(NativeApplication.nativeApplication.icon).menu = createSystrayRootMenu();
		win.addEventListener(NativeWindowDisplayStateEvent.DISPLAY_STATE_CHANGING,interceptMinimize);
	}				
}

private function interceptMinimize(e:NativeWindowDisplayStateEvent):void {
	if (e.afterDisplayState == "minimized") {
		e.preventDefault();
		win.visible = false;
		NativeApplication.nativeApplication.icon.bitmaps = [dockImage];
	}				
}

private function undock(e:Event):void {
	win.visible = true;
	win.orderToFront();
	NativeApplication.nativeApplication.icon.bitmaps = [];
}

private function createSystrayRootMenu():NativeMenu{
	var menu:NativeMenu = new NativeMenu();
	var openNativeMenuItem:NativeMenuItem = new NativeMenuItem("Abrir");
	//var siteNativeMenuItem:NativeMenuItem = new NativeMenuItem("Acessar o meu Blog!");
	var exitNativeMenuItem:NativeMenuItem = new NativeMenuItem("Fechar");
	openNativeMenuItem.addEventListener(Event.SELECT, undock);
	//siteNativeMenuItem.addEventListener(Event.SELECT, openSite);
	exitNativeMenuItem.addEventListener(Event.SELECT, closeWindowByEvent);
	menu.addItem(openNativeMenuItem);
	//menu.addItem(siteNativeMenuItem);    
	menu.addItem(new NativeMenuItem("",true));
	menu.addItem(exitNativeMenuItem);
	return menu;
}

private function openSite(e:Event):void {
	var url:String = new String("http://www.leonelcasado.com.br/"); 
	var urlReq:URLRequest = new URLRequest(url); 
	navigateToURL(urlReq);
}

private function closeWindowByEvent(e:Event):void {
	var closing:Event = new Event(Event.CLOSING,true,true);
	dispatchEvent(closing);
	if(!closing.isDefaultPrevented()){
		win.close();
	}
}