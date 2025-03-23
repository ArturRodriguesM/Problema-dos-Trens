/****************************************************************
* Autor............: Artur Rodrigues Moura Rocha
* Matricula........: 202310240 
* Inicio...........: 25/02/2025
* Ultima alteracao.: 19/03/2025
* Nome.............: Controlador.java
* Funcao...........: Controle da interface do javafx
****************************************************************/
package controller;

import java.net.URL;
import javafx.scene.Group;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.*;

/**************************************************************** <p>
 * Classe: Controlador <p>
 * Funcao: Controle da interface do javafx <p>
 ****************************************************************/

public class Controlador extends Declarador implements Initializable {

  //PONTOS PARA O CAMINHO A (ESQUERDA)
  private Ponto a0 = new Ponto(88, -70);
  private Ponto a1 = new Ponto(88, 70);
  private Ponto a2 = new Ponto(192, 70);
  private Ponto a3 = new Ponto(192, 210);
  private Ponto a4 = new Ponto(88, 210);
  private Ponto a5 = new Ponto(88, 320);
  private Ponto a6 = new Ponto(192, 320);
  private Ponto a7 = new Ponto(192, 460);
  private Ponto a8 = new Ponto(88, 460);
  private Ponto a9 = new Ponto(88, 600);
  private Ponto[] circuitoA1 = { a1, a2, a3, a4, a5, a6, a7, a8, a9 };
  private Caminho caminhoA1 = new Caminho( // caminho da ESQUERDA comecando por CIMA
      new Ponto(88, 10), // Inicio A1
      new Ponto(88, -70), // Reinicio A1
      circuitoA1);
  private Ponto[] circuitoA2 = { a8, a7, a6, a5, a4, a3, a2, a1, a0 };
  private Caminho caminhoA2 = new Caminho( // caminho da ESQUERDA comecando por BAIXO
      new Ponto(88, 520), // Inicio A2
      new Ponto(88, 600), // Reinicio A2
      circuitoA2);

  //PONTOS PARA O CAMINHO B (DIREITA)
  private Ponto b0 = new Ponto(298, -70);
  private Ponto b1 = new Ponto(298, 70);
  private Ponto b2 = new Ponto(192, 70);
  private Ponto b3 = new Ponto(192, 210);
  private Ponto b4 = new Ponto(298, 210);
  private Ponto b5 = new Ponto(298, 320);
  private Ponto b6 = new Ponto(192, 320);
  private Ponto b7 = new Ponto(192, 460);
  private Ponto b8 = new Ponto(298, 460);
  private Ponto b9 = new Ponto(298, 600);
  private Ponto[] circuitoB1 = { b1, b2, b3, b4, b5, b6, b7, b8, b9 };
  private Caminho caminhoB1 = new Caminho( // caminho da DIREITA comecando por CIMA
      new Ponto(298, 10), // Inicio B1
      new Ponto(298, -70), // Reinicio B1
      circuitoB1);
  private Ponto[] circuitoB2 = { b8, b7, b6, b5, b4, b3, b2, b1, b0 };
  private Caminho caminhoB2 = new Caminho( // caminho da DIREITA comecando por BAIXO
      new Ponto(298, 520), // Inicio B2
      new Ponto(298, 600), // Reinicio B2
      circuitoB2);

  /** Controle do movimento do trexA */
  Movimento movimentoTRexA;
  /** Controle do movimento do trexB */
  Movimento movimentoTRexB;

  /** possui os titulo "dino's run" e o botao start*/
  @FXML
  private Group grupoInicial;
  /**possui a tela de selecionar a posicao de dinossauros*/
  @FXML
  private Group grupoSelecao;
  /** possui a imagem dos 2 trexs */
  @FXML
  private Group grupoTRex;
  /** possui os controles de velocidade. Aparecem durante o movimento*/
  @FXML
  private Group grupoVelocidade;

  /** menu para selecionar a posicao inicial do dinossauro da esquerda */
  @FXML
  private MenuButton menuSelecaoA;
  /** menu para selecionar a posicao inicial do dinossauro da direita */
  @FXML
  private MenuButton menuSelecaoB;
  /** coloca o dino da esquerda na parte de cima */
  @FXML
  private MenuItem acimaA;
  /** coloca o dino da esquerda na parte de baixo */
  @FXML
  private MenuItem abaixoA;
  /** coloca o dino da direita na parte de cima */
  @FXML
  private MenuItem acimaB;
  /** coloca o dino da direita na parte de baixo */
  @FXML
  private MenuItem abaixoB;
  /** botao da tela de selecao da posicao dos dinossauros para iniciar o movimento 
   * dos dinossauros
   */
  @FXML
  private Button botaoIniciarMovimento;

  /** imagem do trex da direita (B)*/
  @FXML
  private ImageView tRexB;
  /** imagem do trex da esquerda (A)*/
  @FXML
  private ImageView tRexA;
  /** controla a velocidade do t-rex A*/
  @FXML
  private Slider sliderTRexA;
  /** controla a velocidade do t-rex B*/
  @FXML
  private Slider sliderTRexB;
  /** valor inicial da velocidade dos dinos */
  private final int velocidadePadrao = 200;

  /**************************************************************** <p>
  * Metodo: initialize <p>
  * Funcao: roda quando o javafx inicializa os elementos da interface.
  Eh aqui onde se determina os seus estados iniciais <p>
  @param location parametro do javafx
  @param resources parametro do javafx
  @return <code>void</code> 
  ****************************************************************/
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    grupoInicial.setVisible(true);
    grupoSelecao.setVisible(false);
    grupoTRex.setVisible(true);
    grupoVelocidade.setVisible(false);
    movimentoTRexA = new Movimento(tRexA, sliderTRexA);
    movimentoTRexB = new Movimento(tRexB, sliderTRexB);
    botaoIniciarMovimento.setDisable(false);
  }

  /**************************************************************** <p>
  * Metodo: Controlador (construtor) <p>
  * Funcao: Cria o controlador do javafx ja com o css <p>
  @param arquivo arquivo FXML da aplicacao
  @param css arquivo css de estilizacao
  @return <code>N/A</code> construcao do controlador
  ****************************************************************/
  public Controlador(String arquivo, String css) throws Exception {
    super(arquivo, css);
    //movimento da tela inicial dos dinos
    movimentoTRexA.setCaminho(caminhoA1);
    movimentoTRexB.setCaminho(caminhoB2);
    movimentoTRexA.getControle_Velocidade().setValue(100);
    movimentoTRexB.getControle_Velocidade().setValue(200);
    movimentoTRexB.moverPara();
    movimentoTRexA.moverPara();
  }

  /**************************************************************** <p>
  * Metodo: botaoStart <p>
  * Funcao: modifica a cena da interface para a selcao da posicao
  dos dinossauros quando o botao start inicial eh pressionado <p>
  @param event evento que iniciou o metodo (click)
  @return <code>void</code> 
  ****************************************************************/
  @FXML
  public void botaoStart(MouseEvent event) {
    grupoInicial.setVisible(false);
    habilitarSelecao(true, true);
  }

  /**************************************************************** <p>
  * Metodo: habilitarSelecao <p>
  * Funcao: fazer aparecer a tela de selecao da posicao dos dinossauros <p>
  @param habilitar true para aparecer e false para sumir
  @param anularCaminho parametro para o fim da movimentacao
  @param transicao parametro para o fim da movimentacao  
  @return <code>void</code> 
  @see #fimMovimentacao(boolean, boolean)
  ****************************************************************/
  public void habilitarSelecao(boolean anularCaminho, boolean transicao) {

    //thread responsavel por esperar o fim do movimento do dino, caso haja transicao de movimento
    //obs.: verificar como funciona a transicao de movimento na documentacao do metodo fimMovimentacao
    Thread esperarFimDoMovimento = new Thread() {
      @Override
      public void run() {
        grupoSelecao.setVisible(true);

        //apenas habilita a tela de selecao quando 
        //os dinossauros terminarem seus respectivos movimentos
        grupoSelecao.setDisable(true);
        fimMovimentacao(anularCaminho, transicao); //Possui espera ocupada
        grupoSelecao.setDisable(false);

        habilitarBotaoIniciarMovimento();
      }
    };
    esperarFimDoMovimento.setName("esperarFimDoMovimento");
    esperarFimDoMovimento.start();
  }

  /**************************************************************** <p>
  * Metodo: fimMovimentacao <p>
  * Funcao: finaliza a movimentacao dos dinossauros <p>
  @param anularCaminho se for true, os dinossauros deixam de seguir
  os seus respectivos caminhos para receberem outros; se for
  false, o caminho se mantem o mesmo que estava antes de parar o 
  movimento
  @param transicao se for true, os dinossauros irao primeiro finalizar
  o movimento que estao realizando em alta velocidade, ate chegar no final
  do caminho. Se false, eles apenas pararao o movimento e se teleportarao 
  o fim da pista. <p>
  OBS.: Se a transicao for TRUE, deve-se chamar o metodo "fimMovimento"
  em uma thread DIFERENTE da do javafx, pois o metodo possui espera
  ocupada, e isso travara a interface do javafx
  @return <code>void</code>
  ****************************************************************/
  public void fimMovimentacao(boolean anularCaminho, boolean transicao) {
    //para o movimento dos dinos

    if (transicao) {
      //primeiro, eh necessario indicar o ponto de reinicio como nulo, 
      //pois assim o dino nao iria reiniciar o movimento
      movimentoTRexA.getCaminho().setReinicio(null);
      movimentoTRexB.getCaminho().setReinicio(null);

      //agora, aumenta-se a velocidade dos dinos
      movimentoTRexA.getControle_Velocidade().setValue(500);
      movimentoTRexB.getControle_Velocidade().setValue(500);

      //While de espera, ate o movimento terminar
      do {
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {

        }
      } while (movimentoTRexA.getEmMovimento() || movimentoTRexB.getEmMovimento());

      System.out.println("saiu do loop");
    } else {
      //apenas para o movimento
      movimentoTRexA.pararMovimento();
      movimentoTRexB.pararMovimento();
    }
    if (anularCaminho) {
      //deve-se anular o caminho quando o usuario for escolher
      //o caminho do dino, na tela de selecao
      movimentoTRexA.setCaminho(null);
      movimentoTRexB.setCaminho(null);
      //obs.: como o dino nao vai ter mais caminho,
      //a posicao que ele ficara depois de acabar a movimentacao
      //nao importa, desde que o dino fique escondido. 
      //por isso, um ponto de "reinicio" qualquer
      //foi escolhido
      movimentoTRexA.setPosicao(caminhoA1.getReinicio());
      movimentoTRexB.setPosicao(caminhoA1.getReinicio());
    } else {
      //o caminho se mantem quando o usuario der reset na 
      //movimentacao. Quando a tela de selecao aparecer, os 
      //dinos estarao no inicio de seus respectivos caminhos
      movimentoTRexA.setPosicao(movimentoTRexA.getCaminho().getInicio());
      movimentoTRexB.setPosicao(movimentoTRexB.getCaminho().getInicio());
    }
    //reinicia velocidade dos dinos
    movimentoTRexA.getControle_Velocidade().setValue(velocidadePadrao);
    movimentoTRexB.getControle_Velocidade().setValue(velocidadePadrao);
  }

  /**************************************************************** <p>
  * Metodo: habilitarBotaoIniciarMovimento <p>
  * Funcao: teste para saber se o botao responsavel por iniciar o 
  movimento dos dinossauros pode estar habilitado ou nao <p>
  @return <code>boolean</code> retorna se foi possivel habilitar ou
  nao o botao
  ****************************************************************/
  public boolean habilitarBotaoIniciarMovimento() {
    //se os 2 trexs possuirem um caminho, entao pode-se iniciar o movimento
    if (movimentoTRexA.getCaminho() != null && movimentoTRexB.getCaminho() != null) {
      botaoIniciarMovimento.setDisable(false);
      return true;
    }
    botaoIniciarMovimento.setDisable(true);
    return false;
  }

  /**************************************************************** <p>
  * Metodo: indicarCaminho <p>
  * Funcao: set do caminho que os dinos irao seguir, sendo este metodo
  acionado pelos menus de selecao da tela de selecao. Ao ser escolhido
  o caminho, o dino sera teleportado automaticamente para o seu ponto
  inicial <p>
  @param event indica qual opcao foi escolhida pelo usuario,
  atraves do click do mouse
  @return <code>void</code> 
  ****************************************************************/
  @FXML
  void indicarCaminho(ActionEvent event) {
    //sequencia de ifs para saber qual opcao foi selecionada 
    Object opcaoClicada = event.getSource(); //opcao clicada pelo usuario
    if (opcaoClicada.equals(acimaA)) {
      //o dino da ESQUERDA fica na parde de CIMA da pista
      movimentoTRexA.setPosicao(caminhoA1.getInicio());
      menuSelecaoA.setText("Acima");
      movimentoTRexA.setCaminho(caminhoA1);

    } else if (opcaoClicada.equals(abaixoA)) {
      //o dino da ESQUERDA fica na parde de BAIXO da pista
      movimentoTRexA.setPosicao(caminhoA2.getInicio());
      menuSelecaoA.setText("Abaixo");
      movimentoTRexA.setCaminho(caminhoA2);

    } else if (opcaoClicada.equals(acimaB)) {
      //o dino da DIREITA fica na parde de CIMA da pista
      movimentoTRexB.setPosicao(caminhoB1.getInicio());
      menuSelecaoB.setText("Acima");
      movimentoTRexB.setCaminho(caminhoB1);

    } else if (opcaoClicada.equals(abaixoB)) {
      //o dino da DIREITA fica na parde de BAIXO da pista
      movimentoTRexB.setPosicao(caminhoB2.getInicio());
      menuSelecaoB.setText("Abaixo");
      movimentoTRexB.setCaminho(caminhoB2);

    }
    habilitarBotaoIniciarMovimento();
  }

  /**************************************************************** <p>
  * Metodo: mostrarPosicao <p>
  * Funcao: mostrar a posicao que ficara o dino, respectiva de cada
   opcao dos menus <p>
  @param event aciona o evento quando o mouse passa por cima da opcao
  @return <code>void</code> 
  ****************************************************************/
  @FXML
  void mostrarPosicao(MouseEvent event) {
    //sequencia de ifs para saber qual opcao o usuario passou o mouse por cima
    Object opcaoAcionada = event.getSource();
    if (opcaoAcionada.equals(acimaA.getGraphic())) {
      //mostra a posicao inicial do dino da ESQUERDA em CIMA
      movimentoTRexA.setPosicao(caminhoA1.getInicio());

    } else if (opcaoAcionada.equals(abaixoA.getGraphic())) {
      //mostra a posicao inicial do dino da ESQUERDA em BAIXO
      movimentoTRexA.setPosicao(caminhoA2.getInicio());

    } else if (opcaoAcionada.equals(acimaB.getGraphic())) {
      //mostra a posicao inicial do dino da DIREITA em CIMA
      movimentoTRexB.setPosicao(caminhoB1.getInicio());

    } else if (opcaoAcionada.equals(abaixoB.getGraphic())) {
      //mostra a posicao inicial do dino da DIREITA em BAIXO
      movimentoTRexB.setPosicao(caminhoB2.getInicio());

    }
  }

  /**************************************************************** <p>
  * Metodo: iniciarMovimento <p>
  * Funcao: inicia o movimento dos dinossauros. Roda quando o botao
  iniciar movimento eh clicado <p>
  @param event evento de click do mouse do javafx
  @return <code>void</code> 
  ****************************************************************/
  @FXML
  void iniciarMovimento(MouseEvent event) {
    grupoTRex.setVisible(true);
    grupoSelecao.setVisible(false);
    grupoVelocidade.setVisible(true);
    movimentoTRexA.moverPara();
    movimentoTRexB.moverPara();
  }

  /**************************************************************** <p>
  * Metodo: reset <p>
  * Funcao: reseta o movimento dos dinos, voltando para a tela de
  selecao da posicao inicial do movimento, porem mantendo selecionado
  a posicao inicial antes escolhida pelo usuario <p>
  @param event evento de click do javafx
  @return <code>void</code>
  ****************************************************************/
  @FXML
  void reset(MouseEvent event) {
    grupoVelocidade.setVisible(false);
    fimMovimentacao(false, false);
    grupoSelecao.setVisible(true);
    habilitarBotaoIniciarMovimento();
  }

}
