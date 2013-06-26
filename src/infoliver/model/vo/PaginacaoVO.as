package infoliver.model.vo{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.vo.PaginacaoVO",PaginacaoVO);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.vo.PaginacaoVO")]
	[Bindable]
	public class PaginacaoVO{
		public var entidade:Object;
		public var quantidadePaginacao:uint;
		public var inicioPaginacao:uint;
		public var totalRegistros:uint;
	}
}