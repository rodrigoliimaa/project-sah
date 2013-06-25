package leonelcasado.componentes.alertaslide.impl{

	/**
	 * Classe responsavel por gerenciar os alertas que estao visiveis ao usuario.
	 * Esta classe será um Singleton.
	 *
	 * @author Fabiel Prestes
	 */
	public class GerenciadorAlerta {

		private static var _instance:GerenciadorAlerta;

		private var _totalAlertaVisivel:int = 0;

		private var _totalAlertaVisivelAux:int = 0;

		public function GerenciadorAlerta(type:PrivateGerenciadorAlerta) {
			if (type == null) {
				throw new Error("Erro: Não é possivel instanciar GerenciadorAlerta, já que este é um Singleton.");
			}
		}

		/**
		 * Retorna a instancia unica da classe GerenciadorAlerta
		 */
		public static function getInstance():GerenciadorAlerta {
			if (_instance == null)
				_instance = new GerenciadorAlerta(new PrivateGerenciadorAlerta());
			return _instance;
		}

		public function alertaCriado():void {
			_totalAlertaVisivel++;
			_totalAlertaVisivelAux++;
		}

		public function alertaRemovido():void {
			_totalAlertaVisivel--;
			
			if(_totalAlertaVisivel <= 0)
				_totalAlertaVisivelAux = 0;
		}
		
		/**
		 * Retorna o total de alerta que esta visivel para o usuario na tela
		 */
		public function get totalAlertaVisivel():int {
			return _totalAlertaVisivelAux;
		}
	}
}

internal class PrivateGerenciadorAlerta {
	public function PrivateGerenciadorAlerta() {
	}
}