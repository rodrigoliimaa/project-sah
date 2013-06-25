package leonelcasado.mdi.events{
	import flash.events.Event;
	
	import leonelcasado.mdi.containers.MDIWindow;
	import leonelcasado.mdi.managers.MDIManager;
	
	import mx.effects.Effect;
	
	/**
	 * Event type dispatched by MDIManager. Majority of events based on/relayed from managed windows.
	 */
	public class MDIManagerEvent extends Event
	{
		public static const WINDOW_ADD:String = "windowAdd";
		public static const WINDOW_MINIMIZE:String = "windowMinimize";
		public static const WINDOW_RESTORE:String = "windowRestore";
		public static const WINDOW_MAXIMIZE:String = "windowMaximize";
		public static const WINDOW_CLOSE:String = "windowClose";
		
		public static const WINDOW_FOCUS_START:String = "windowFocusStart";
		public static const WINDOW_FOCUS_END:String = "windowFocusEnd";
		public static const WINDOW_DRAG_START:String = "windowDragStart";
		public static const WINDOW_DRAG:String = "windowDrag";
		public static const WINDOW_DRAG_END:String = "windowDragEnd";
		public static const WINDOW_RESIZE_START:String = "windowResizeStart";
		public static const WINDOW_RESIZE:String = "windowResize";
		public static const WINDOW_RESIZE_END:String = "windowResizeEnd";
		
		public static const CASCADE:String = "cascade";
		public static const TILE:String = "tile";
		
		public var window:MDIWindow;
		public var manager:MDIManager;
		public var effect:Effect;
		public var effectItems:Array;
		public var resizeHandle:String;

		public function MDIManagerEvent(type:String, window:MDIWindow, manager:MDIManager, effect:Effect = null, effectItems:Array = null, resizeHandle:String = null, bubbles:Boolean = false)
		{
			super(type, bubbles, true);
			this.window = window;
			this.manager = manager;
			this.effect = effect;
			this.effectItems = effectItems;
			this.resizeHandle = resizeHandle;
		}
		
		override public function clone():Event
		{
			return new MDIManagerEvent(type, window, manager, effect, effectItems, resizeHandle, bubbles);
		}
	}
}