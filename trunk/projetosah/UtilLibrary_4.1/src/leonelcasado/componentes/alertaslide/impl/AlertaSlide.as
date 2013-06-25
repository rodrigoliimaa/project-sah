package leonelcasado.componentes.alertaslide.impl{
	import flash.display.DisplayObject;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	import leonelcasado.componentes.alertaslide.AlertaUI;
	import mx.core.FlexGlobals;
	import mx.events.FlexEvent;
	import mx.managers.PopUpManager;

	/**
	 * Responsavel por exibir e controlar o componente de alerta na tela.
	 *
	 * @author Fabiel Prestes
	 */
	public class AlertaSlide extends AlertaUI {

		private var _novaAltura:int;

		private var _duracao:int = 3000;

		private var _timerOcultarAlerta:Timer;
		
		public static var ALERTA_SUCESSO:int = 0;
		public static var ALERTA_ERRO:int = 1;
		
		public function AlertaSlide() {
			super();

			this.addEventListener(FlexEvent.CREATION_COMPLETE, onCreate);
		}

		/**
		 * @private
		 * Espera o alerta ser totalmente criado para seguir o fluxo de configuraçao de posicionamento.
		 * @param evt
		 */
		private function onCreate(evt:FlexEvent):void {
				
			/* Definindo o local onde o alerta será exibido,
			 * Por simplicidade irei adotar o mesmo padrao do MSN, Twitter e outros, ou seja,
			 * no canto inferior direito da tela
			 *
			 * Define o X, para isso pega-se o tamanho total da tela - o tamanho do nosso alerta - um gap qualquer.
			 * Define o Y, para isso pega-se a (altura total da tela - um gap qualquer) - (a altura do nosso alerta * total de alerta visiveis na tela)  */
			this.x = FlexGlobals.topLevelApplication.width - this.width - 0;
			this.y = (FlexGlobals.topLevelApplication.height - 0) - (this.height * GerenciadorAlerta.getInstance().totalAlertaVisivel);

			/* Configurando os efeitos de MOVE. */
			this.mvExibirAlerta.yFrom = FlexGlobals.topLevelApplication.height;
			this.mvExibirAlerta.yTo = y;

			this.mvOcultarAlerta.yTo = FlexGlobals.topLevelApplication.height;

			/* Configurando o eventos do MOUSE
			 *
			 * Apos o alerta ser exibido o mesmo ficará visivel apenas por um determinado tempo.
			 * Desta maneira caso o demore para ler a msg o alerta será ocultado, para que isso nao aconteça
			 * iremos definir que quando o usuario colocar o mouse em cima o alerta o mesmo fica disponivel ate
			 * que o usuario retire o mouse de cima do alerta. */
			this.addEventListener(MouseEvent.ROLL_OVER, function():void {
				_timerOcultarAlerta.removeEventListener(TimerEvent.TIMER_COMPLETE, fecharAlerta);
			});

			this.addEventListener(MouseEvent.ROLL_OUT, function():void {
				fecharAlerta(null);
			});
			
			/* Configurando o Tempo 'Timer' no qual o alerta ficará disponivel antes de ser ocultado */
			_timerOcultarAlerta = new Timer(_duracao, 1);
			_timerOcultarAlerta.addEventListener(TimerEvent.TIMER_COMPLETE, fecharAlerta);
			_timerOcultarAlerta.start();
			
			/* Ao sair deste metodo o efeito Parallel que nos deixamos como Bindable no creationCompleteEffect da Tela
			 * será ativado */
		}
		
		/**
		 * @private
		 * Remove o alerta da Tela. 
		 * Neste momento o efeito Parallel que nos deixamos como Bindable no removedEffect da Tela
		 * será ativado
		 * @param evt
		 */
		private function fecharAlerta(evt:TimerEvent):void{
			PopUpManager.removePopUp( this );
			
			/* Avisa o gerenciador que foi removido um Alerta */
			GerenciadorAlerta.getInstance().alertaRemovido();
		}
		
		/**
		 * Responsavel por criar, configurar e exibir o Alerta tendo como base os argumentos como referencia.
		 *
		 * @param titulo Define o titulo a ser exibido no componente
		 * @param conteudo Define o conteudo do alerta
		 * @param tipoAlerta Define o style do alerta
		 * @param duracao Define a duracao em segundos que o alerta ficará visivel para o usuario. Padra 3000 '3s'
		 * @param icone Define um icone para ser exibido no alerta
		 * @return a instancia do alerta criado e exibido;
		 */
		public static function show(titulo:String, conteudo:String, tipoAlerta:int = 0, duracao:int = 3000, icone:String = null):AlertaSlide {
			var alerta:AlertaSlide = new AlertaSlide();
			alerta._titulo = titulo;
			alerta._conteudo = conteudo;
			//alerta._icone = icone;
			alerta._duracao = duracao;
			
			/* Define o Style do Alerta pelo tipo.
			 * O padrao será sempre o fundoAlertaSucesso */			
			if(tipoAlerta == ALERTA_ERRO)
				alerta.setStyle('styleName', 'fundoAlertaErro');
			
			/* Exibe o alerta na tela */
			PopUpManager.addPopUp(alerta, DisplayObject(FlexGlobals.topLevelApplication), false);
			
			/* Avisa o gerenciador que foi criado um novo alerta */
			GerenciadorAlerta.getInstance().alertaCriado();
			
			return alerta;
		}
	}
}