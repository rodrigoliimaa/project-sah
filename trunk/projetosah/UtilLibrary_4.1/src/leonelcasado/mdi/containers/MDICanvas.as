package leonelcasado.mdi.containers{
	import leonelcasado.mdi.effects.IMDIEffectsDescriptor;
	import leonelcasado.mdi.managers.MDIManager;
	
	import mx.containers.Canvas;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;

	/**
	 * Convenience class that allows quick MXML implementations by implicitly creating
	 * container and manager members of MDI. Will auto-detect MDIWindow children
	 * and add them to list of managed windows.
	 */
	public class MDICanvas extends Canvas
	{
		public var windowManager:MDIManager;

		public function MDICanvas()
		{
			super();
			windowManager = new MDIManager(this);
			addEventListener(FlexEvent.CREATION_COMPLETE, onCreationComplete);
		}

		override public function initialize():void{
			super.initialize();
		}

		private function onCreationComplete(event:FlexEvent):void
		{
			for each(var child:UIComponent in getChildren())
			{
				if(child is MDIWindow)
				{
					windowManager.add(child as MDIWindow);
				}
			}
			removeEventListener(FlexEvent.CREATION_COMPLETE, onCreationComplete);
		}

		/**
		 * Proxy to MDIManager effects property.
		 *
		 * @deprecated use effects and class
		 *
		 */
		public function set effectsLib(clazz:Class):void
		{
			this.windowManager.effects = new clazz();
		}

		/**
		 * Proxy to MDIManager property of same name.
		 */
		public function set effects(effects:IMDIEffectsDescriptor):void
		{
			this.windowManager.effects = effects;
		}

		/**
		 * Proxy to MDIManager property of same name.
		 */
		public function get enforceBoundaries():Boolean
		{
			return windowManager.enforceBoundaries;
		}

		public function set enforceBoundaries(value:Boolean):void
		{
			windowManager.enforceBoundaries = value;
		}

		/**
		 * Proxy to MDIManager property of same name.
		 */
		public function get snapDistance():Number
		{
			return windowManager.snapDistance;
		}
		public function set snapDistance(value:Number):void
		{
			windowManager.snapDistance = value;
		}

		/**
		 * Proxy to MDIManager property of same name.
		 */
		public function get tilePadding():Number
		{
			return windowManager.tilePadding;
		}
		public function set tilePadding(value:Number):void
		{
			windowManager.tilePadding = value;
		}
	}
}