package infoliver.model.entity
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.MovimentacaoCID",MovimentacaoCID);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.MovimentacaoCID")]
	[Bindable]
	public class MovimentacaoCID
	{
		public var sigtap_co_cid:String;
		public var sigtap_no_cid:String;
		
		public var procedimentos:ArrayCollection;
		public var movimentacao:Movimentacao;
		
		public static function fromSigtapCID(sigtapCID:SigtapCID):MovimentacaoCID {
			var cid:MovimentacaoCID = new MovimentacaoCID();
			cid.sigtap_co_cid = sigtapCID.co_cid;
			cid.sigtap_no_cid = sigtapCID.no_cid;
			cid.procedimentos = MovimentacaoProcedimento.fromSigtapProcedimentos(sigtapCID.sigtapProcedimentos);
			return cid;
		}
		
		public static function fromSigtapCIDs(sigtapCIDs:ArrayCollection):ArrayCollection {
			var cids:ArrayCollection = new ArrayCollection();
			for each (var sigtapCID:SigtapCID in sigtapCIDs) {
				cids.addItem(fromSigtapCID(sigtapCID));
			}
			return cids;
		}
	}
}