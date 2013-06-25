package infoliver.model.entity
{
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.Motivo",Motivo);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Motivo")]
	public class Motivo{
		public var sequencial:int;
		public var descricao:String;
		
		public function toString():String{
			return descricao;
		}
	}
}