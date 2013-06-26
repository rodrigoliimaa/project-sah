package infoliver.model{
	import leonelcasado.com.adobe.cairngorm.model.IModelLocator;
	import infoliver.model.vo.LoginVO;
	import mx.collections.ArrayCollection;
	import mx.core.Container;
	import mx.resources.IResourceManager;
	import mx.resources.ResourceBundle;
	import mx.resources.ResourceManager;

	/**
	 * <b>Projeto:</b>lih</br>
	 * <b>Autor:</b>leonelcasado <b>Data de Criação:</b>15/05/2010</br>
	 * <b>Motivação:</b>Classe Singleton para utilização de objetos globais no projeto.
	 */
	[Bindable]
	public class ModelLocator implements IModelLocator{
		private static var instance:ModelLocator;
		public var urlServidor:String;
		//--------- ENTITY/LIST(Usado para passar valores entres os módulos)
		public var loginVO:LoginVO=new LoginVO;
		//----------------------------------------		
		public var _listaPermissoes:Array;
		private var resources:ResourceBundle;
		public var resourceManager:IResourceManager;

		/**
		 * Retona apenas uma instância do ModelLocator utillizando
		 * o padrão de projeto Singleton.
		 * @author Leonel Casado
		 */ 
		public static function getInstance():ModelLocator{
			if(instance==null) {
				instance = new ModelLocator(new SingletonEnforcer());
			}
			return instance;
		}

		/**
		 * Construtor da classe ModelLocator
		 * @author Leonel Casado
		 */
	    public function ModelLocator(enforcer:SingletonEnforcer){
			if(enforcer == null) throw new Error("Esta classe é singleton e não pode ser instanciada manualmente.");
		}

		/**TODO:Remover as listas que não forem mais utilizadas devido ao novo result
		 * ser implementado na própria tela eliminando o Command associado ao evento disparado.
		 * @author Leonel Casado 
		*/
		public function limparConfiguracoes():void{
			//--------- ENTITY/LIST ENTITY------------
			loginVO=null;
			//----------------------------------------
			_listaPermissoes=null;
			resources=null;
		}
		
		/**
		 * Seta a lista de permissões retornadas por cada usuário do
		 * sistema.
		 * @author Leonel Casado
		 */
		public function set listaPermissoes(listaPermissoes:Array):void{
			this._listaPermissoes=listaPermissoes;
			resources = new ResourceBundle("en_US","permissao");
			for(var i:int=0;i<_listaPermissoes.length;i++){
				resources.content[_listaPermissoes[i]]=true;
			}
			ResourceManager.getInstance().addResourceBundle(resources);
			ResourceManager.getInstance().update();
		}
		
		public function isPermissaoValida(param:String):Boolean{
			return ResourceManager.getInstance().getString('permissao',param)=='true';
		}
	}
}
class SingletonEnforcer {}