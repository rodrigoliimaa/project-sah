package infoliver.model.entity
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.SigtapCID",SigtapCID);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.SigtapCID")]
	[Bindable]
	public class SigtapCID
	{
		public var co_cid:String;
		public var no_cid:String;
		
		public var sigtapProcedimentos:ArrayCollection;
		
		public static function fromMovimentacaoCID(cid:MovimentacaoCID):SigtapCID {
			var sigtapCID:SigtapCID = new SigtapCID();
			sigtapCID.co_cid = cid.sigtap_co_cid;
			sigtapCID.no_cid = cid.sigtap_no_cid;
			sigtapCID.sigtapProcedimentos = null;
			return sigtapCID;
		}
		
		public static function fromMovimentacaoCIDs(cids:ArrayCollection):ArrayCollection {
			var sigtapCIDs:ArrayCollection = new ArrayCollection();
			for each (var cid:MovimentacaoCID in cids) {
				sigtapCIDs.addItem(fromMovimentacaoCID(cid));
			}
			return sigtapCIDs;
		}
	}
}