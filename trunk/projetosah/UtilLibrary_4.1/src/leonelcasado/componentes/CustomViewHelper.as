package leonelcasado.componentes{
	import flash.net.URLVariables;
	
	import flashx.textLayout.operations.SplitParagraphOperation;
	
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.controls.CheckBox;
	import mx.controls.ComboBox;
	import mx.controls.DataGrid;
	import mx.controls.DateChooser;
	import mx.controls.DateField;
	import mx.controls.Image;
	import mx.controls.NumericStepper;
	import mx.controls.RadioButton;
	import mx.controls.TextArea;
	import mx.controls.TextInput;
	import mx.core.Container;
	import mx.formatters.CurrencyFormatter;
	import mx.formatters.DateFormatter;
	import mx.formatters.NumberBaseRoundType;
	import mx.formatters.NumberFormatter;
	import mx.utils.StringUtil;

	public class CustomViewHelper{
		private static var localidade:String="";
		private static var formatoMoeda:CurrencyFormatter=new CurrencyFormatter();
		private static var formatoNumero:NumberFormatter=new NumberFormatter();
		private static var formatoData:DateFormatter=new DateFormatter();

		public function CustomViewHelper(){
		}

		private static function initFormatacao():void{
		    formatoNumero.decimalSeparatorFrom=".";
		    formatoMoeda.decimalSeparatorFrom=".";
		    
		    formatoNumero.decimalSeparatorTo=",";
		    formatoMoeda.decimalSeparatorTo=",";
		    
		    formatoNumero.thousandsSeparatorFrom=","; 
		    formatoMoeda.thousandsSeparatorFrom=",";
		    
		    formatoNumero.thousandsSeparatorTo=".";
		    formatoMoeda.thousandsSeparatorTo=".";
		    
		    formatoNumero.useNegativeSign="true"; 
		    formatoMoeda.useNegativeSign="true";
		    
		    formatoNumero.useThousandsSeparator="true";
		    formatoMoeda.useThousandsSeparator="true";
		    
		    formatoNumero.rounding = NumberBaseRoundType.NEAREST;
		    formatoMoeda.rounding  = NumberBaseRoundType.NEAREST;		    
		}
				
		public static function trim(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				return StringUtil.trim(value);
			}
		}

		public static function ltrim(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				return value.replace(/^\s+/,"");
			}				
		}

		public static function rtrim(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				return value.replace(/\s+$/,"");
			}				
		}

		public static function retorneNuloSeVazio(value:*):*{
			if(value==null || value=="")
				return null;
			else
				return value;
		}

		public static function removerEspacos(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				return value.replace(/(\s*)/g,"");
				//return value.replace(/(^\s+)|(\s+$)/g,"");
			}				
		}
		
		public static function removerMascara(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				//return value.replace(/\D/g, "");
				// Remove todos os caracteres à seguir: 
				//( ) / - . e espaço, para tratar a string.
				return removerEspacos(value.replace(/(\.|\,|\(|\)|\/|\-)/g,""));				
 			}
		}

		public static function upperCase(value:String):String{
			return value.toUpperCase();
		}

		public static function lowerCase(value:String):String{
			return value.toLowerCase();
		}

		public static function ordenarArrayCollection(campo:String,value:Object,isOrdemDesc:Boolean=false):Object{ 
			var sort:Sort = new Sort(); 
			sort.fields = [new SortField(campo, true)]; 
			value.sort = sort;
			if(isOrdemDesc)sort.reverse();
			value.refresh();
			return value;
			
			//Ordenar o array em ordem numérica,crescente e decrescente
			//lista.sort(Array.NUMERIC);lista.sort();lista.sort(Array.DESCENDING);
			//value.sort();
		}
		
		public static function formatarData(hoje:Date=null,localidade:String=null):String{
			var retorno:String;
			if(hoje==null)var hoje:Date=new Date();
			var dia:String=hoje.getDate().toString();
			var	mes:String=(hoje.getMonth()+1).toString();
			var	ano:String=hoje.getFullYear().toString();
			if(localidade=="en_US")
				retorno=(mes.length==1?"0"+mes:mes)+"/"+(dia.length==1?"0"+dia:dia)+"/"+ano;
			else
				retorno=(dia.length==1?"0"+dia:dia)+"/"+(mes.length==1?"0"+mes:mes)+"/"+ano;
			
			return retorno;
		}
		
		public static function formatarDataPorExtenso(hoje:Date=null):String{
			var meses:Array = new Array("Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro");
			var semana:Array= new Array("Domingo","Segunda-feira","Terça-feira","Quarta-feira","Quinta-feira","Sexta-feira","Sábado");
			//-------------------------------------------
			if(hoje==null)var hoje:Date=new Date();
			var dia:String=hoje.getDate().toString();
			var	dias:String=hoje.getDay().toString();
			var	mes:String=hoje.getMonth().toString();
			var	ano:String=hoje.getFullYear().toString();
			//-------------------------------------------
			return semana[dias]+", "+dia+" de "+meses[mes]+" de "+ano;
		}
		
		public static function mascararDDDTelefone(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
			    value=value.replace(/\D/g,"");                 //Remove tudo o que não é dígito
			    value=value.replace(/^(\d\d)(\d)/g,"($1) $2"); //Coloca parênteses em volta dos dois primeiros dígitos
			    value=value.replace(/(\d{4})(\d)/,"$1-$2");    //Coloca hífen entre o quarto e o quinto dígitos
			    return value;
		 	}
		}

		public static function mascararData(value:String):String{
			if(value==null || value==""){
				return null;
			}else{		    
			    value=value.replace(/\D/g,"");                 //Remove tudo o que não é dígito
			    value=value.replace(/^(\d\d)(\d)/g,"$1/$2");   //Coloca barra entre os dois primeiros dígitos
			    value=value.replace(/(\d{2})(\d)/,"$1/$2");    //Coloca barra entre o quinto e o sexto dígitos
			    return value;
			}
		}

		public static function mascararCaceal(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
			    value=value.replace(/\D/g,"");                    //Remove tudo o que não é dígito
			    value=value.replace(/(\d{8})(\d)/,"$1-$2");       //Coloca um ponto entre o terceiro e o quarto dígitos
			    return value;
			}
		}

		public static function mascararCep(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
			    value=value.replace(/\D/g,"");                    //Remove tudo o que não é dígito
			    value=value.replace(/(\d{5})(\d)/,"$1-$2");       //Coloca um ponto entre o quinto e o sexto dígitos
			    return value;
			}
		}

		public static function mascararCpf(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				value=value.replace(/\D/g,"");                    //Remove tudo o que não é dígito
				//-------------------------------------------
				value = "00000000000" + value;
				value = value.substring(value.length - 11);
				//-------------------------------------------
			    value=value.replace(/(\d{3})(\d)/,"$1.$2");       //Coloca um ponto entre o terceiro e o quarto dígitos
			    value=value.replace(/(\d{3})(\d)/,"$1.$2");       //Coloca um ponto entre o terceiro e o quarto dígitos
			                                             		//de novo (para o segundo bloco de números)
			    value=value.replace(/(\d{3})(\d{1,2})$/,"$1-$2"); //Coloca um hífen entre o terceiro e o quarto dígitos
			    return value;
			}
		}
	
		public static function mascararCnpj(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				value=value.replace(/\D/g,"");                           //Remove tudo o que não é dígito
				//-------------------------------------------
				value = "00000000000000" + value;
				value = value.substring(value.length - 14);
				//-------------------------------------------
	    		value=value.replace(/^(\d{2})(\d)/,"$1.$2");             //Coloca ponto entre o segundo e o terceiro dígitos
	    		value=value.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3"); //Coloca ponto entre o quinto e o sexto dígitos
	    		value=value.replace(/\.(\d{3})(\d)/,".$1/$2");           //Coloca uma barra entre o oitavo e o nono dígitos
	    		value=value.replace(/(\d{4})(\d)/,"$1-$2");              //Coloca um hífen depois do bloco de quatro dígitos
	    		return value;
			}
		}

		public static function mascararCpfCnpj(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				if(value.length==14){
					return mascararCnpj(value);
				}else if(value.length==11){
					return mascararCpf(value);
				}else{
					return value;
				}
			}
		}

		public static function mascararDocumentos(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
	    		if(value.length==14){	    		
					return mascararCnpj(value);	    		
	    		}else if(value.length==11){
	    			return mascararCpf(value);
				}else if(value.length==9){
					return mascararCaceal(value);
				}else{
					return value;
				}
			}
		}
		
		public static function mascararPlaca(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
	    		value=value.substring(0,3)+"-"+value.substring(3,7);
	    		return value;
			}
		}

		public static function formatarNumeroInteiro(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				//Define qual o separador de centavos de entrada, antes de ser formatado.
				formatoNumero.decimalSeparatorFrom=",";
				//Define qual o separador de centavos que será inserido no valor formatado.
				formatoNumero.decimalSeparatorTo=",";
				//Define qual o separador de milhares de entrada, antes de ser formatado.
				formatoNumero.thousandsSeparatorFrom=".";
				//Define qual o separador de milhares de saída, após a formatação.
				formatoNumero.thousandsSeparatorTo=".";
				//Define a precisão (centavos) na formatação da moeda.
				formatoNumero.precision="0";
				return formatoNumero.format(value);
			}
		}
		
		public static function formatarMoeda(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				if(value=='0,00')value='0';
				formatoMoeda.currencySymbol="R$";
				formatoNumero.decimalSeparatorFrom=".";
				formatoNumero.decimalSeparatorTo=",";
				formatoNumero.thousandsSeparatorFrom=""; 
				formatoNumero.thousandsSeparatorTo=".";
				
				
/*				formatoMoeda.decimalSeparatorFrom=",";
				formatoMoeda.decimalSeparatorTo=",";
				formatoMoeda.thousandsSeparatorFrom=".";
				formatoMoeda.thousandsSeparatorTo=".";
*/				formatoMoeda.precision="2";
			    formatoMoeda.alignSymbol="left";
			    return formatoMoeda.format(value);
			}
		}
		
		public static function formatarNumeroDecimal(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				if(value=='0,00')value='0';
				formatoNumero.decimalSeparatorFrom=".";
				formatoNumero.decimalSeparatorTo=",";
				formatoNumero.thousandsSeparatorFrom=""; 
				formatoNumero.thousandsSeparatorTo=".";
				
/*				formatoNumero.decimalSeparatorFrom=",";
				formatoNumero.decimalSeparatorTo=",";
				formatoNumero.thousandsSeparatorFrom="."; 
				formatoNumero.thousandsSeparatorTo=".";
*/				formatoNumero.precision="2";
				return formatoNumero.format(value);
			}
		}

		public static function formatarPercentual(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				formatoNumero.decimalSeparatorFrom=".";
				formatoNumero.decimalSeparatorTo=",";
				formatoNumero.thousandsSeparatorFrom=""; 
				formatoNumero.thousandsSeparatorTo=".";
				return formatoNumero.format(value);
			}
		}

		public static function formatarNumeroDecimalPrecisao(value:String):String{
			if(value==null || value==""){
				return null;
			}else{
				if(value=='0,0000')value='0';
				formatoNumero.decimalSeparatorFrom=".";
				formatoNumero.decimalSeparatorTo=",";
				formatoNumero.thousandsSeparatorFrom=""; 
				formatoNumero.thousandsSeparatorTo=".";

/*				formatoNumero.decimalSeparatorFrom=",";
				formatoNumero.decimalSeparatorTo=",";
				formatoNumero.thousandsSeparatorFrom="."; 
				formatoNumero.thousandsSeparatorTo=".";
*/				formatoNumero.precision="4";
				return formatoNumero.format(value);
			}
		}

		public static function arredondarValorDecimalParaCima(value:Number):String{				
			formatoNumero.rounding = NumberBaseRoundType.UP;
			formatoNumero.rounding  = NumberBaseRoundType.UP;
			return formatoNumero.format(value);
		}		

		public static function arredondarValorDecimalParaBaixo(value:Number):String{				
			formatoNumero.rounding = NumberBaseRoundType.DOWN;
			formatoNumero.rounding  = NumberBaseRoundType.DOWN;
			return formatoNumero.format(value);
		}		
		
		public static function converterValorDecimalToBigDecimal(value:String):String{				
			if(value==null || value==""){
				return null;
			}else{
				if(value=='0,00')value='0';
				formatoNumero.decimalSeparatorFrom=",";  
				formatoNumero.decimalSeparatorTo="."; 
				formatoNumero.thousandsSeparatorFrom="."; 
				formatoNumero.thousandsSeparatorTo="";				
				formatoNumero.precision="2";
			    return formatoNumero.format(value);
			}
		}
		
		public static function converterValorDecimalToBigDecimalPrecisao(value:String):String{				
			if(value==null || value==""){
				return null;
			}else{
				if(value=='0,0000')value='0';
				formatoNumero.decimalSeparatorFrom=",";  
				formatoNumero.decimalSeparatorTo="."; 
				formatoNumero.thousandsSeparatorFrom="."; 
				formatoNumero.thousandsSeparatorTo="";				
				formatoNumero.precision="4";
			    return formatoNumero.format(value);
			}
		}

		public static function converterBigDecimalToValorDecimal(value:String):String{				
			if(value==null || value==""){
				return null;
			}else{
				if(value=='0')value='0,00';
				formatoNumero.decimalSeparatorFrom=".";
				formatoNumero.decimalSeparatorTo=",";
				formatoNumero.thousandsSeparatorFrom="";
				formatoNumero.thousandsSeparatorTo=".";
				formatoNumero.precision="2";
			    return formatoNumero.format(value);
			}
		}
		
		public static function converterBigDecimalToValorDecimalPrecisao(value:String):String{				
			if(value==null || value==""){
				return null;
			}else{
				if(value=='0')value='0,0000';
				formatoNumero.decimalSeparatorFrom=".";
				formatoNumero.decimalSeparatorTo=",";
				formatoNumero.thousandsSeparatorFrom="";
				formatoNumero.thousandsSeparatorTo=".";
				formatoNumero.precision="4";
			    return formatoNumero.format(value);
			}
		}
		
		public static function converterBigDecimalToMoeda(value:String):String{				
			if(value==null || value==""){
				return null;
			}else{
				if(value=='0')value='0,00';
				formatoMoeda.currencySymbol="R$";
				formatoMoeda.precision="2";
				formatoMoeda.decimalSeparatorFrom=".";
				formatoMoeda.decimalSeparatorTo=",";
				formatoMoeda.thousandsSeparatorFrom="";
				formatoMoeda.thousandsSeparatorTo=".";
				formatoMoeda.alignSymbol="left";
			    return formatoMoeda.format(value);
			}
		}

		public static function converterStringToDate(data:String):Date{
			if(data==null || data=="")return null;
			else return DateField.stringToDate(data,"DD/MM/YYYY");
		}

		public static function converterDateToString(data:Date):String{
			if(data==null)return "";
			else return DateField.dateToString(data,"DD/MM/YYYY");
		}
					
		private static function retornarValorColunaHierarquica(item:Object,column:Object):Object{
			var currentRef:Object=item;
			var fields:Array = column.dataField.split(".");
			for ( var i:int=0; i<fields.length; i++ )
				currentRef = currentRef[fields[i]];
			return currentRef;			
		}

		public static function dataGridMascararMoeda(item:Object, column:Object):String{
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:converterBigDecimalToMoeda(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return converterBigDecimalToMoeda(item[column.dataField].toString());
				}
			}
			return;
		}

		public static function dataGridMascararMoedaArray(item:Object, column:Object):String{
			if(item[0][column.dataField]==null){
				return null;
			}else{
			    return converterBigDecimalToMoeda(item[0][column.dataField]);
			}
		}
		
		public static function dataGridMascararNumeroInteiro(item:Object, column:Object):String{
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:formatarNumeroInteiro(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return formatarNumeroInteiro(item[column.dataField].toString());
				}
			}
			return;
		}

		public static function dataGridMascararNumeroInteiroArray(item:Object, column:Object):String{
			if(item[0][column.dataField]==null){
				return null;
			}else{
			    return formatarNumeroInteiro(item[0][column.dataField]);
			}
		}

		public static function dataGridMascararStringToNumeroDecimal(item:Object, column:Object):String{
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:formatarNumeroDecimal(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return formatarNumeroDecimal(item[column.dataField].toString());
				}
			}
			return;
		}

		public static function dataGridMascararNumeroDecimal(item:Object, column:Object):String{
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:converterBigDecimalToValorDecimal(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return converterBigDecimalToValorDecimal(item[column.dataField].toString());
				}
			}
			return;
		}
		
		public static function dataGridMascararNumeroDecimalArray(item:Object, column:Object):String{
			if(item[0][column.dataField]==null){
				return null;
			}else{
			    return converterBigDecimalToValorDecimal(item[0][column.dataField]);
			}
		}

		public static function dataGridMascararNumeroDecimalPrecisao(item:Object, column:Object):String{
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:converterBigDecimalToValorDecimalPrecisao(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return converterBigDecimalToValorDecimalPrecisao(item[column.dataField].toString());
				}
			}
			return;
		}

		public static function dataGridMascararNumeroDecimalPrecisaoArray(item:Object, column:Object):String{
			if(item[0][column.dataField]==null){
				return null;
			}else{
			    return converterBigDecimalToValorDecimalPrecisao(item[0][column.dataField]);
			}
		}
		
		public static function dataGridMascararDDDTelefone(item:Object, column:Object):String{
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:mascararDDDTelefone(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return mascararDDDTelefone(item[column.dataField].toString());
				}
			}
			return;
		}

		public static function dataGridMascararDDDTelefoneArray(item:Object, column:Object):String{
			var value:String;
			if(item[0][column.dataField]==null){
				return null;
			}else{
			    value=item[0][column.dataField].toString();
			    return mascararDDDTelefone(value);
			}
		}

		public static function dataGridMascararDataString(item:Object, column:Object):String{
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:mascararData(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return mascararData(item[column.dataField].toString());
				}
			}
			return;
		}

		public static function dataGridMascararData(item:Object, column:Object):String{
			formatoData.formatString="DD/MM/YYYY";
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:formatoData.format(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return formatoData.format(item[column.dataField].toString());
				}
			}
			return;
		}

		public static function dataGridMascararDataArray(item:Object, column:Object):String{
			if(item[0][column.dataField]==null){
				return null;
			}else{
				formatoData.formatString="DD/MM/YYYY";
				return formatoData.format(item[0][column.dataField]);
			}
		}

		public static function dataGridMascararDataHora(item:Object, column:Object):String{
			formatoData.formatString="DD/MM/YYYY H:NN:SS";
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:formatoData.format(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return formatoData.format(item[column.dataField].toString());
				}
			}
			return;
		}

		public static function dataGridMascararDataHoraArray(item:Object, column:Object):String{
			if(item[0][column.dataField]==null){
				return null;
			}else{
				formatoData.formatString="DD/MM/YYYY H:NN:SS";
				return formatoData.format(item[0][column.dataField]);
			}
		}

		public static function dataGridMascararCaceal(item:Object, column:Object):String{			
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:mascararCaceal(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return mascararCaceal(item[column.dataField].toString());
				}
			}
			return;
		}

		public static function dataGridMascararCacealArray(item:Object, column:Object):String{			
			var value:String;// = new String();
			if(item[0][column.dataField]==null){
				return null;
			}else{
				value=item[0][column.dataField].toString();
				return mascararCaceal(value);
		 	}	
		}

		public static function dataGridMascararCpfCnpj(item:Object, column:Object):String{
			var value:String;
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				if(obj==null ||obj=='')return null;
				value=obj.toString();
				if(value.length==14){	    		
					return mascararCnpj(value);
				}else if(value.length==11){
					return mascararCpf(value);
				}else{
					return value;
				}
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					value=item[column.dataField].toString();
					if(value.length==14){	    		
						return mascararCnpj(value);
					}else if(value.length==11){
						return mascararCpf(value);
					}else{
						return value;
					}
				}
			}
			return;
		}

		public static function dataGridMascararCpfCnpjArray(item:Object, column:Object):String{
			var value:String;// = new String();
			if(item[0][column.dataField]==null){
				return null;
			}else{
				value=item[0][column.dataField].toString();
	    		if(value.length==14){	    		
					return mascararCnpj(value);
	    		}else if(value.length==11){
					return mascararCpf(value);
	    		}else{
					return value;
				}
			}
		}

		public static function dataGridMascararDocumentos(item:Object, column:Object):String{
			var value:String;
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				if(obj==null ||obj=='')return null;
				value=obj.toString();
				if(value.length==14){	    		
					return mascararCnpj(value);	    		
				}else if(value.length==11){
					return mascararCpf(value);
				}else if(value.length==9){
					return mascararCaceal(value);
				}else{
					return value;
				}
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					value=item[column.dataField].toString();
					if(value.length==14){	    		
						return mascararCnpj(value);	    		
					}else if(value.length==11){
						return mascararCpf(value);
					}else if(value.length==9){
						return mascararCaceal(value);
					}else{
						return value;
					}
				}
			}
			return;
		}

		public static function dataGridMascararDocumentosArray(item:Object, column:Object):String{
			var value:String;// = new String();
			if(item[0][column.dataField]==null){
				return null;
			}else{
				value=item[0][column.dataField].toString();
	    		if(value.length==14){	    		
					return mascararCnpj(value);	    		
	    		}else if(value.length==11){
	    			return mascararCpf(value);
	    		}else{
					return mascararCaceal(value);
	    		}
			}
		}
		
		public static function dataGridMascararCpf(item:Object, column:Object):String{
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:mascararCpf(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
				    return mascararCpf(item[column.dataField].toString());
				}
			}
			return;
		}

		public static function dataGridMascararCpfArray(item:Object, column:Object):String{
			var value:String;// = new String();
			if(item[0][column.dataField]==null){
				return null;
			}else{
				value=item[0][column.dataField].toString();
			    return mascararCpf(value);
			}
		}
	
		public static function dataGridMascararCnpj(item:Object, column:Object):String{
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:mascararCnpj(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return mascararCnpj(item[column.dataField].toString());
				}
			}
			return;
		}

		public static function dataGridMascararCnpjArray(item:Object, column:Object):String{
			var value:String;// = new String();
			if(item[0][column.dataField]==null){
				return null;
			}else{
				value=item[0][column.dataField].toString();
				return mascararCnpj(value);
			}
		}

		public static function dataGridMascararCep(item:Object, column:Object):String{
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:mascararCep(obj.toString());			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return mascararCep(item[column.dataField].toString());
				}
			}
			return;
		}

		public static function dataGridMascararCepArray(item:Object, column:Object):String{
			var value:String;// = new String();
			if(item[0][column.dataField]==null){
				return null;
			}else{
				value=item[0][column.dataField].toString();
				return mascararCep(value);
			}
		}

		public static function dataGridMascararPercentual(item:Object, column:Object):String{
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:formatarPercentual(obj.toString())+'%';			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return formatarPercentual(item[column.dataField])+'%';
				}
			}
			return;
		}

		public static function dataGridMascararPercentualArray(item:Object, column:Object):String{
			if(item[0][column.dataField]==null){
				return null;
			}else{
			    return formatarPercentual(item[0][column.dataField])+'%';
			}
		}

		public static function dataGridMascararValorKB(item:Object, column:Object):String{
			if (column.dataField.indexOf(".")!=-1){
				var obj:Object = retornarValorColunaHierarquica(item,column);
				return (obj==null ||obj=='')?null:formatarNumeroInteiro(obj.toString())+' KB';			
			}else{				
				if(item[column.dataField]==null){
					return null;
				}else{
					return formatarNumeroInteiro(item[column.dataField])+' KB';
				}
			}
			return;
		}

		public static function dataGridMascararValorKBArray(item:Object, column:Object):String{
			if(item[0][column.dataField]==null){
				return null;
			}else{
			    return formatarNumeroInteiro(item[0][column.dataField])+' KB';
			}
		}
		
		
		/*
		Corrige o problema de tamanho da fote ao converter em html
		*/
		public function corrigirRichTextEditor(campo:Object):String{
			var tempString:String = campo.htmlText;
			var pattern:RegExp = new RegExp("SIZE=\"([0-9]{1,2})\"","g");		
			return tempString.replace(pattern, "SIZE=\"$1\" STYLE=\"font-size: $1px\""); 	
		}
	
		/*
		Seleciona o item no ComboBox que tenha uma propriedade cujo valor 
		é igual ao valor da mesma propriedade do item selecionado no DataGrid
		*/
		public function matchComboItem2(combo:Object,dados:Object,campo:String):void{
			for(var i:int=0;i<combo.dataProvider.length;i++){
				if(combo.dataProvider[i].campo == dados[0].campo){
					combo.selectedIndex = i;
					break;
				}
			}
		}

		/*BindByField source="dg.selectedItem" destination="cb.selectedItem" field="rowId"/>*/
		public function matchComboItem(combo:Object,dados:Object,rowId:String):void{
		    var matchField:String = rowId;
		    for each (var item:Object in combo.dataProvider){
		        if (item[matchField] == dados.selectedItem[matchField]){
		            combo.selectedItem = item;
		            break;
		        }
		    }
		}

		public static function converteObjectToURLVariables(parameters:Object):URLVariables{ 
			var paramsToSend:URLVariables = new URLVariables(); 
			for(var i:String in parameters) { 
				if(i!=null) { 
					if(parameters[i] is Array) paramsToSend[i] = parameters[i]; 
					else paramsToSend[i] = parameters[i].toString(); 
				} 
			} 
			return paramsToSend; 
		} 

		public static function limparCamposFormulario(container:Container):void {
			for each(var component:Object in container.getChildren()) {
				if(component is TextInput) {
					TextInput(component).text = "";
				} else if(component is TextArea) {
					TextArea(component).text = "";
				} else if (component is DateField) {
					DateField(component).selectedDate = null;
					DateField(component).text = "";
				} else if(component is DateChooser) {
					DateChooser(component).selectedDate = null;
				} else if (component is ComboBox) {
					ComboBox(component).selectedIndex = 0;
				} else if (component is CheckBox) {
					CheckBox(component).selected = false;
				} else if (component is RadioButton) {
					(component as RadioButton).selected = false;
					(component as RadioButton).group.selection = null;
				} else if (component is NumericStepper) {
					(component as NumericStepper).data = '';
				} else if(component is Container){
					limparCamposFormulario(component as Container);
				} else if (component is Image) {
					Image(component).source = null;
				} else if (component is CustomDateField) {
					CustomDateField(component).selectedDate = null;
					CustomDateField(component).text = "";
				} else if (component is CustomTextInput) {
					CustomTextInput(component).text = "";
				}
			}
		}
	}
}