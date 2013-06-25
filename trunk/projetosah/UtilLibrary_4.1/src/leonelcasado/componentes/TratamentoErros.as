package leonelcasado.componentes{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.FaultEvent;
	
	public class TratamentoErros{
				
		public static function getMensagensSistema(event:FaultEvent):ArrayCollection {
			var mensagens:ArrayCollection = new ArrayCollection();
			var extendedData:Object = Object(event).message.extendedData;
//			if(extendedData != null && extendedData.hasOwnProperty(CHAVE_MENSAGENS_SISTEMA)) {
//				mensagens = extendedData[CHAVE_MENSAGENS_SISTEMA];
//			}
			if (mensagens.length == 0) {
				if(event.fault.faultString == "Send failed"){
					mensagens.addItem({tipo:"ERRO", detalhe:"Não foi possível conectar ao servidor."});
				}else{
					mensagens.addItem({tipo:"ERRO", detalhe:event.fault.faultString});
				}
			}
			return mensagens;
		}
		
		public static function getStackTrace(event:FaultEvent):String {
			var extendedData:Object = Object(event).message.extendedData;
//			if(extendedData != null && extendedData.hasOwnProperty(CHAVE_STACK_TRACE)) {
//				return extendedData[CHAVE_STACK_TRACE];
//			}
			return event.fault.faultString;
		}
		
		public static function exibirMensagensSistema(event:FaultEvent):void {
			var mensagens:ArrayCollection = getMensagensSistema(event);
			var stackTrace:String = getStackTrace(event);
			if(mensagens.length == 0) {
				mensagens.addItem({tipo:"FATAL", detalhe:event.fault.faultString});
			}
			if (event.fault.faultString != null) {
				MensagensSistema.show(mensagens, event.fault.faultString + ":", stackTrace);
			} else {
				MensagensSistema.show(mensagens, "", stackTrace);
			}
		}
		
		public static function exibirMensagensComTitulo(titulo:String, mensagens:ArrayCollection):void {
			MensagensSistema.show(mensagens, titulo);
		}
		
		public static function exibirMensagens(mensagens:ArrayCollection):void {
			MensagensSistema.show(mensagens);
		}		
	}
}