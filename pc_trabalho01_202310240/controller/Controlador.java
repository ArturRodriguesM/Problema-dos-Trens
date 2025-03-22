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
  * Metodo: Controlador (construtor) <p>
  * Funcao: Cria o controlador do javafx ja com o css <p>
  @param arquivo arquivo FXML da aplicacao
  @param css arquivo css de estilizacao
  @return <code>N/A</code> construcao do controlador
  ****************************************************************/
  public Controlador(String arquivo, String css) throws Exception {
    super(arquivo, css);
    movimentoTRexB.setCaminho(caminhoB2);
    movimentoTRexA.setCaminho(caminhoA1);
    System.out.println(sliderTRexA);
    movimentoTRexB.moverPara();
    movimentoTRexA.moverPara();
  }

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
  * Metodo: botaoStart <p>
  * Funcao: modifica a cena da interface para a selcao da posicao
  dos dinossauros quando o botao start inicial eh pressionado <p>
  @param event evento que iniciou o metodo (click)
  @return <code>void</code> 
  ****************************************************************/
  @FXML
  public void botaoStart(MouseEvent event) {
    grupoInicial.setVisible(false);
    habilitarSelecao(true);
  }

  /**************************************************************** <p>
  * Metodo: habilitarSelecao <p>
  * Funcao: fazer aparecer ou desaparecer a tela de selecao da posicao
   dos dinossauros <p>
  @param habilitar true para aparecer e false para sumir
  @return <code>void</code> 
  ****************************************************************/
  public void habilitarSelecao(boolean habilitar) {
    grupoSelecao.setVisible(habilitar);
    fimMovimentacao(true);
    System.out.println(habilitarBotaoIniciarMovimento());
  }

  /**************************************************************** <p>
  * Metodo: fimMovimentacaoInicial <p>
  * Funcao: finaliza a movimentacao dos dinossauros do comeco da 
  aplicacao <p>
  @param anularCaminho se for true, ele deixa o caminho que os 
  dinossauros devem seguir nulo ao final do movimento; se for
  false, o caminho se mantem o mesmo que estava antes de parar o 
  movimento
  @return <code>void</code>
  ****************************************************************/
  public void fimMovimentacao(boolean anularCaminho) {
    movimentoTRexA.pararMovimento();
    movimentoTRexB.pararMovimento();
    if (anularCaminho) {
      movimentoTRexA.setCaminho(null);
      movimentoTRexB.setCaminho(null);
    }
    movimentoTRexA.getControle_Velocidade().setValue(velocidadePadrao);
    movimentoTRexB.getControle_Velocidade().setValue(velocidadePadrao);
    grupoTRex.setVisible(false);
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
   * Metodo: acima1Pressionado <p>
   * Funcao: faz o dinossauro da ESQUERDA ficar na barte de cima da pista <p>
   @param event evendo do javafx
   @return <code>void</code> 
   ****************************************************************/
  @FXML
  void acimaAPressionado(ActionEvent event) {
    System.out.println("pressionado acima");
    menuSelecaoA.setText("Acima");
    movimentoTRexA.setCaminho(caminhoA1);
    habilitarBotaoIniciarMovimento();
  }

  /**************************************************************** <p>
   * Metodo: abaixo1Pressionado <p>
   * Funcao: faz o dinossauro da ESQUERDA ficar na barte de baixo da pista <p>
   @param event evendo do javafx
   @return <code>void</code> 
   ****************************************************************/
  @FXML
  void abaixoAPressionado(ActionEvent event) {
    System.out.println("pressionado abaixo");
    menuSelecaoA.setText("Abaixo");
    movimentoTRexA.setCaminho(caminhoA2);
    habilitarBotaoIniciarMovimento();
  }

  /**************************************************************** <p>
   * Metodo: acimaBPressionado <p>
   * Funcao: faz o dinossauro da DIREITA ficar na barte de cima da pista <p>
   @param event evendo do javafx
   @return <code>void</code> 
   ****************************************************************/
  @FXML
  void acimaBPressionado(ActionEvent event) {
    System.out.println("pressionado acima");
    menuSelecaoB.setText("Acima");
    movimentoTRexB.setCaminho(caminhoB1);
    habilitarBotaoIniciarMovimento();
  }

  /**************************************************************** <p>
   * Metodo: abaixoBPressionado <p>
   * Funcao: faz o dinossauro da DIREITA ficar na barte de baixo da pista <p>
   @param event evendo do javafx
   @return <code>void</code> 
   ****************************************************************/
  @FXML
  void abaixoBPressionado(ActionEvent event) {
    System.out.println("pressionado abaixo");
    menuSelecaoB.setText("Abaixo");
    movimentoTRexB.setCaminho(caminhoB2);
    habilitarBotaoIniciarMovimento();
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
    fimMovimentacao(false);
    grupoSelecao.setVisible(true);
    habilitarBotaoIniciarMovimento();
  }

}
