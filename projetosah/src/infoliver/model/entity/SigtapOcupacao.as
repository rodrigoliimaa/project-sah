package infoliver.model.entity
{
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.SigtapOcupacao",SigtapOcupacao);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.SigtapOcupacao")]
	[Bindable]
	public class SigtapOcupacao
	{
		public var co_ocupacao:String;
		
		public static function fromOcupacao(ocupacao:Ocupacao):SigtapOcupacao {
			var sigtapOcupacao:SigtapOcupacao = new SigtapOcupacao();
			sigtapOcupacao.co_ocupacao = ocupacao.codigoOcupacao;
			return sigtapOcupacao;
		}
	}
}