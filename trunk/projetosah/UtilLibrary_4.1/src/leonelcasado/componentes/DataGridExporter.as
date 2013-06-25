package leonelcasado.componentes{
	import flash.errors.*;
	import flash.events.*;
	import flash.external.*;
	import flash.net.URLRequest;
	import flash.net.URLRequestMethod;
	import flash.net.URLVariables;
	import flash.net.navigateToURL;
 
	public class DataGridExporter{        	
	    //Aplicação servidor que gerará o excel
	    //[Bindable] public var excelExport:String = "xls";
		//[Bindable] public var pdfExport:String = "pdf";
	 
	    // Converte a datagrid para uma tabela html
	    private function convertDGToHTMLTable(dg:*):String {
	    	var font:String = dg.getStyle('fontFamily');
	    	var size:String = (int(dg.getStyle('fontSize'))-2).toString(); //Reduz em 2, o tamanho da fonte.
	    	var str:String = '';
	    	var colors:String = '';
	    	var style:String = 'style="font-family:'+font+';font-size:'+size+'pt;"';				
	    	var hcolor:Array;
	 
	    	if(dg.getStyle("headerColor") != undefined) {
	    		hcolor = [dg.getStyle("headerColor")];
	    	} else {
	    		hcolor = dg.getStyle("headerColors");
	    	}				
	 
			if(hcolor==null)
	    		str+= '<table border="1" width="'+dg.width+'"><thead><tr width="'+dg.width+'">';
			else
				str+= '<table border="1" width="'+dg.width+'"><thead><tr width="'+dg.width+'" style="background-color:#' +Number((hcolor[0])).toString(16)+'">';
			
	    	for(var i:int = 0;i<dg.columns.length;i++) {
	    		colors = dg.getStyle("themeColor");
	 
	    		if(dg.columns[i].headerText != undefined && dg.columns[i].visible == true) {
	    			str+="<th "+style+">"+dg.columns[i].headerText+"</th>";
	    		} else if (dg.columns[i].visible == true) {
	    			str+= "<th "+style+">"+dg.columns[i].dataField+"</th>";
	    		}
	    	}
	    	str += "</tr></thead><tbody>";
	    	colors = dg.getStyle("alternatingRowColors");
	 
	    	for(var j:int =0;j<dg.dataProvider.length;j++) {					
	    		str+="<tr width=\""+Math.ceil(dg.width)+"\">";
	 
	    		for(var k:int=0; k < dg.columns.length; k++) {
	    			if(dg.dataProvider.getItemAt(j) != undefined && dg.dataProvider.getItemAt(j) != null && dg.columns[k].visible == true && dg.columns[k].dataField != "Melhor") {
	    				if(dg.columns[k].labelFunction != undefined && dg.columns[k].labelFunction != null && dg.columns[k].visible == true) {
	    					str += "<td nowrap=\"nowrap\" width=\""+Math.ceil(dg.columns[k].width)+"\" "+style+">"+dg.columns[k].labelFunction(dg.dataProvider[j],dg.columns[k])+"</td>";
	    				} else {
	    					str += "<td nowrap=\"nowrap\" width=\""+Math.ceil(dg.columns[k].width)+"\" "+style+">"+dg.dataProvider.getItemAt(j)[dg.columns[k].dataField]+"</td>";
	    				}
	    			}
	    		}
	    		str += "</tr>";
	    	}
	    	str+="</tbody></table>";
	 
	    	return str;
	    }
	 
		// Envia o HTML para a aplicação servidor com um formato(pdf,excel)
		public function gerarXLS(dg:*,caminho:String,nomeArquivo:String):void {
			var variables:URLVariables = new URLVariables(); 
			variables.htmltable	= convertDGToHTMLTable(dg);
			variables.filename=nomeArquivo;
			/*if(formato == excelExport){
				url = new URLRequest(excelExport);	
			}else if(formato == pdfExport){
				url = new URLRequest(pdfExport);
			}*/
			var url:URLRequest;
			url = new URLRequest(caminho);
			url.data = variables;
			url.method = URLRequestMethod.POST;
			navigateToURL(url,"_blank");
	    }       
	}
}