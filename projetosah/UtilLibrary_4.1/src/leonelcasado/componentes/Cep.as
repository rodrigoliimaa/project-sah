package leonelcasado.componentes{
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;
	
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.mxml.HTTPService;
	import mx.utils.StringUtil;

	public class Cep extends CustomTextInput{
		[Bindable] public var codigoResultado:uint;
		[Bindable] public var resultado:String;
		[Bindable] public var uf:String;
		[Bindable] public var cidade:String;
		[Bindable] public var bairro:String;
		[Bindable] public var tipoLogradouro:String;
		[Bindable] public var logradouro:String;
		[Bindable] public var logradouroCompleto:String;
		
		public function Cep(){
			super();
			addEventListener(KeyboardEvent.KEY_UP, mascara);
			addEventListener(FocusEvent.FOCUS_OUT,valida);
			maxChars=9;
			restrict="0-9";
			maxWidth=80;
			width=80;
			toolTip="Digite um CEP válido com 8 Dígitos";
			isComponenteValido=false;			
		}

		override public function initialize():void{
			super.initialize();
		}

		public function textoComMascara(value:String):void{
			text=CustomViewHelper.mascararCep(value);
		}

		public function consultarCep():void{
			var url:String="http://cep.republicavirtual.com.br/web_cep.php?cep="+CustomViewHelper.removerMascara(text);
			var httpService:HTTPService= new HTTPService();
			httpService.url = url;
			httpService.method = "POST";
			httpService.addEventListener(ResultEvent.RESULT,resultHandler);
			httpService.addEventListener(FaultEvent.FAULT,faultHandler);
			httpService.send();
		}
		
		private function resultHandler(event:ResultEvent):void{
			codigoResultado=uint(event.result.webservicecep.resultado);
			if(codigoResultado>0){
				resultado=event.result.webservicecep.resultado_txt;
				uf=event.result.webservicecep.uf.toUpperCase();
				cidade=event.result.webservicecep.cidade.toUpperCase();
				bairro=event.result.webservicecep.bairro==null?"":event.result.webservicecep.bairro.toUpperCase();
				tipoLogradouro=event.result.webservicecep.tipo_logradouro==null?"":event.result.webservicecep.tipo_logradouro.toUpperCase();
				logradouro=event.result.webservicecep.logradouro==null?"":event.result.webservicecep.logradouro.toUpperCase();
				logradouroCompleto=StringUtil.trim(tipoLogradouro+' '+logradouro);
			}else{
				uf="UF";
				cidade="";
				bairro="";
				tipoLogradouro="";
				logradouro="";
				logradouroCompleto="";				
			}
		}
		
		private function faultHandler(event:FaultEvent):void{
			uf="UF";
			cidade="";
			bairro="";
			tipoLogradouro="";
			logradouro="";
			logradouroCompleto="";
			Alerta.error('Problema na obtenção do logradouro!');
		}

		private function mascara(event:KeyboardEvent):void{
			var pos:Number=Number(text.length) + 1;
	        if (text.length == 5){
	            text = text + "-";
				setSelection(pos,pos);
	 		}	
		}
		
		public function valida(event:FocusEvent):void{
			if(text.length>0){
				var valor:String=CustomViewHelper.removerMascara(text);
				if(!Validacao.validarCep(valor)){
					isComponenteValido=false;
					setSelection(0,8);
				}else{
					text=valor.substring(0,5)+"-"+valor.substring(5,8);
					//consultarCep();
					isComponenteValido=true;				
				}
			}else{
				isComponenteValido=false;
			}
		}
	}
}