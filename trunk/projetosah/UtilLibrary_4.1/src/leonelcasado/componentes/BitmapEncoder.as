package leonelcasado.componentes{
	import flash.display.BitmapData;
	import flash.geom.Rectangle;
	import flash.utils.ByteArray;
	
	import mx.utils.Base64Decoder;

	//http://www.foxarc.com/blog/article/56.htm
	public class BitmapEncoder{
		
		public function BitmapEncoder(){
			throw new Error("BitmapEncoder não pode ser instanciada!");
		}
		
		public static function encodeByteArray(data:BitmapData):ByteArray{   
			if(data==null)return null;
			var bytes:ByteArray = data.getPixels(data.rect);   
			bytes.writeShort(data.width);   
			bytes.writeShort(data.height);   
			bytes.writeBoolean(data.transparent);   
			bytes.compress();   
			return bytes;   
		}   	
		
		public static function decodeByteArray(bytes:ByteArray):BitmapData{   
			if(bytes == null)return null;
			try{
				bytes.uncompress();   
			}catch(error:Error){
				bytes.readObject();
			}
			if(bytes.length<6)return null;
			
			bytes.position = bytes.length - 1;   
			var transparent:Boolean = bytes.readBoolean();   
			bytes.position = bytes.length - 3;   
			var height:int = bytes.readShort();   
			bytes.position = bytes.length - 5;   
			var width:int = bytes.readShort();   
			bytes.position = 0;   
			var datas:ByteArray = new ByteArray();             
			bytes.readBytes(datas,0,bytes.length - 5);   
			var bmp:BitmapData = new BitmapData(width,height,transparent,0);   
			bmp.setPixels(new Rectangle(0,0,width,height),datas);   
			return bmp;
		}  
	}
}