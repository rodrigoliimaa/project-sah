package leonelcasado.componentes{
	import flash.events.KeyboardEvent;
	import flash.ui.Keyboard;
	
	import mx.collections.ArrayCollection;
	import mx.controls.ComboBox;
	
	public class Uf extends ComboBox{
		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isNaoMudarFoco:Boolean;

		private var _labelView:String;		
        private var _selectedValue:String;
        private var bSelectedValueSet:Boolean = false;
		
		private var listaUF:ArrayCollection=new ArrayCollection(
			[{uf:"UF",nome:"SELECIONE",data:"UF"},
			 //{uf:"",nome:"",data:""},
			 {uf:"AC",nome:"ACRE",data:"AC"},
			 {uf:"AL",nome:"ALAGOAS",data:"AL"},
			 {uf:"AM",nome:"AMAZONAS",data:"AM"},
			 {uf:"AP",nome:"AMAPÁ",data:"AP"},
			 {uf:"BA",nome:"BAHIA",data:"BA"},
			 {uf:"CE",nome:"CEARÁ",data:"CE"},
			 {uf:"DF",nome:"DISTRITO FEDERAL",data:"DF"},
			 {uf:"ES",nome:"ESPÍRITO SANTO",data:"ES"},
			 {uf:"GO",nome:"GOIÁS",data:"GO"},
			 {uf:"MA",nome:"MARANHÃO",data:"MA"},
			 {uf:"MG",nome:"MINAS GERAIS",data:"MG"},
			 {uf:"MS",nome:"MATO GROSSO DO SUL",data:"MS"},
			 {uf:"MT",nome:"MATO GROSSO",data:"MT"},
			 {uf:"PA",nome:"PARÁ",data:"PA"},
			 {uf:"PB",nome:"PARAÍBA",data:"PB"},
			 {uf:"PE",nome:"PERNAMBUCO",data:"PE"},
			 {uf:"PI",nome:"PIAUÍ",data:"PI"},
			 {uf:"PR",nome:"PARANÁ",data:"PR"},
			 {uf:"RJ",nome:"RIO DE JANEIRO",data:"RJ"},
			 {uf:"RN",nome:"RIO GRANDE DO NORTE",data:"RN"},
			 {uf:"RO",nome:"RONDÔNIA",data:"RO"},
			 {uf:"RR",nome:"RORAIMA",data:"RR"},
			 {uf:"RS",nome:"RIO GRANDE DO SUL",data:"RS"},
			 {uf:"SC",nome:"SANTA CATARINA",data:"SC"},
			 {uf:"SE",nome:"SERGIPE",data:"SE"},
			 {uf:"SP",nome:"SÃO PAULO",data:"SP"},
			 {uf:"TO",nome:"TOCANTINS",data:"TO"}
			]);
				
		public function Uf(){
			super();
			addEventListener(KeyboardEvent.KEY_DOWN, mudarFoco);
			useHandCursor=true;
			buttonMode=true;
			setStyle("borderStyle","solid");
		}

		override public function initialize():void{
			super.initialize();
		}

		private function mudarFoco(event:KeyboardEvent):void{
			if(!isNaoMudarFoco){
				if(event.keyCode==Keyboard.ENTER){
					this.focusManager.setFocus(this.focusManager.getNextFocusManagerComponent());
				}
			}
		}

        override protected function commitProperties():void{
            super.commitProperties();
            if (bSelectedValueSet){
                bSelectedValueSet=false;
                for (var i:int=0;i<this.dataProvider.length;i++){
                    var item:String = this.dataProvider[i].data;
                    if(item == _selectedValue){
                        this.selectedIndex = i;
                        break;
                    }
                }
            }
        }

        public function set selectedValue(s:String):void{
	        if(s!=null){
				bSelectedValueSet = true;
	        	_selectedValue = s.toUpperCase();
	        	invalidateProperties();
			}else{
				bSelectedValueSet = true;
				_selectedValue = "UF";
				invalidateProperties();				
			}
        }
				
		public function set labelView(s:String):void{
			_labelView = s;
			dataProvider=listaUF;
			labelField=_labelView;
        }
	}
}