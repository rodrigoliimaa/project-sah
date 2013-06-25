package leonelcasado.componentes{
	import flash.events.ContextMenuEvent;
	import flash.net.URLRequest;
	import flash.net.URLRequestMethod;
	import flash.net.navigateToURL;
	import flash.ui.ContextMenu;
	import flash.ui.ContextMenuBuiltInItems;
	import flash.ui.ContextMenuItem;
	import mx.core.FlexGlobals;

   	public class Creditos{
		public var myContextMenu:ContextMenu;
      	
      	public function Creditos(){
			myContextMenu = new ContextMenu();
		    removeDefaultItems();
		    addCustomMenuItems();
			FlexGlobals.topLevelApplication.contextMenu = myContextMenu;
		}
		
		public function removeDefaultItems():void {
		    myContextMenu.hideBuiltInItems();
		    var defaultItems:ContextMenuBuiltInItems = myContextMenu.builtInItems;
		    defaultItems.print = false;
		}
		
		public function addCustomMenuItems():void {
		    var item1:ContextMenuItem = new ContextMenuItem("************************ ATALHOS ******************************");
		    myContextMenu.customItems.push(item1);		 

		    var item2:ContextMenuItem = new ContextMenuItem("1. L.I.H - Soluções Tecnológicas",false);
		    myContextMenu.customItems.push(item2);
		    item2.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, getSite);

		    var item3:ContextMenuItem = new ContextMenuItem("2. REPORTAR PROBLEMAS OCORRIDOS");
		    myContextMenu.customItems.push(item3);
		    item3.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, getAtendimento);

		    var item4:ContextMenuItem = new ContextMenuItem("***************************************************************");
		    myContextMenu.customItems.push(item4);		 
		}    
		
		public function getSite(event:ContextMenuEvent):void {
		    var request:URLRequest = new URLRequest("http://www.leonelcasado.com.br");
		    navigateToURL(request);
		}

		public function getAtendimento(event:ContextMenuEvent):void {
		    var request:URLRequest = new URLRequest("mailto:leonelcasado@yahoo.com.br?subject=Problemas com a Biblioteca&body=[Obs: Detalhe o problema ocorrido]");
		    request.method=URLRequestMethod.POST;
		    navigateToURL(request,"_self");
		}
	}
}