package leonelcasado.mdi.effects{
	import flash.geom.Point;
	import flash.geom.Rectangle;
	
	import leonelcasado.mdi.containers.MDIWindow;
	import leonelcasado.mdi.managers.MDIManager;
	
	import mx.effects.Effect;
	
	/**
	 * Interface expected by MDIManager. All effects classes must implement this interface.
	 */
	public interface IMDIEffectsDescriptor
	{
		// window effects
		
		function getWindowAddEffect(window:MDIWindow, manager:MDIManager):Effect;
		
		function getWindowMinimizeEffect(window:MDIWindow, manager:MDIManager, moveTo:Point = null):Effect;
	
		function getWindowRestoreEffect(window:MDIWindow, manager:MDIManager, restoreTo:Rectangle):Effect;
		
		function getWindowMaximizeEffect(window:MDIWindow, manager:MDIManager, bottomOffset:Number = 0):Effect;
	
		function getWindowCloseEffect(window:MDIWindow, manager:MDIManager):Effect;
		
		function getWindowFocusStartEffect(window:MDIWindow, manager:MDIManager):Effect;
	
		function getWindowFocusEndEffect(window:MDIWindow, manager:MDIManager):Effect;
		
		function getWindowDragStartEffect(window:MDIWindow, manager:MDIManager):Effect;
		
		function getWindowDragEffect(window:MDIWindow, manager:MDIManager):Effect;
		
		function getWindowDragEndEffect(window:MDIWindow, manager:MDIManager):Effect;
	
		function getWindowResizeStartEffect(window:MDIWindow, manager:MDIManager):Effect;
		
		function getWindowResizeEffect(window:MDIWindow, manager:MDIManager):Effect;
		
		function getWindowResizeEndEffect(window:MDIWindow, manager:MDIManager):Effect;
	
		// group effects
		
		function getTileEffect(items:Array, manager:MDIManager):Effect;
		
		function getCascadeEffect(items:Array, manager:MDIManager):Effect;
		
		function reTileMinWindowsEffect(window:MDIWindow, manager:MDIManager, moveTo:Point):Effect;
	}
}