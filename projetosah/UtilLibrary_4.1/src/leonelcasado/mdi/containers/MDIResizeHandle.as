package leonelcasado.mdi.containers{
	/**
	 * Enumeration of possible resize handles.
	 */
	public class MDIResizeHandle
	{
		public static const LEFT:String = "left";

		public static const RIGHT:String = "right";

		public static const TOP:String = "top";

		public static const BOTTOM:String = "bottom";

		public static const TOP_LEFT:String = "topLeft";

		public static const TOP_RIGHT:String = "topRight";

		public static const BOTTOM_LEFT:String = "bottomLeft";

		public static const BOTTOM_RIGHT:String = "bottomRight";

		/**
		 * Constructor
		 */
		public function MDIResizeHandle() {}

		/**
		 * Checks if the handle is on the top (TOP, TOP_RIGHT, or TOP_LEFT)
		 */
		static public function isTop( handle:String ):Boolean
		{
			return handle == TOP || handle == TOP_RIGHT || handle == TOP_LEFT;
		}

		/**
		 * Checks if the handle is on the bottom (BOTTOM, BOTTOM_RIGHT, or BOTTOM_LEFT)
		 */
		static public function isBottom( handle:String ):Boolean
		{
			return handle == BOTTOM || handle == BOTTOM_RIGHT || handle == BOTTOM_LEFT;
		}

		/**
		 * Checks if the handle is on the left side (LEFT, TOP_LEFT, or BOTTOM_LEFT)
		 */
		static public function isLeft( handle:String ):Boolean
		{
			return handle == LEFT || handle == TOP_LEFT || handle == BOTTOM_LEFT;
		}

		/**
		 * Checks if the handle is on the right side (RIGHT, TOP_RIGHT, or BOTTOM_RIGHT)
		 */
		static public function isRight( handle:String ):Boolean
		{
			return handle == RIGHT || handle == TOP_RIGHT || handle == BOTTOM_RIGHT;
		}
	}
}