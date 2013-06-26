package infoliver.util
{
	import mx.formatters.NumberFormatter;

	public class DecimalFormatter extends NumberFormatter
	{
		private static var _instance:DecimalFormatter = new DecimalFormatter();
		
		public function DecimalFormatter()
		{
			if (_instance != null) {
				throw new Error("Use the instace() method");
			}
			
			decimalSeparatorTo = ",";
			precision = 2;
			thousandsSeparatorTo = ".";
			useThousandsSeparator = true;
		}
		
		public static function get instance():Object
		{
			return _instance;
		}
	}
}