/****************************************************************
* Autor............: Artur Rodrigues Moura Rocha
* Matricula........: 202310240 
* Inicio...........: 25/02/2025
* Ultima alteracao.: 18/03/2025
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

  Movimento movimentoTRex;
  /** possui os titulo "dino's run" e o botao start*/
  @FXML
  private Group grupoInicial;
  /**possui a tela de selecionar a posicao de dinossauros*/
  @FXML
  private Group grupoSelecao;
  /** menu para selecionar a posicao inicial dos dinossauros */
  @FXML
  private MenuButton menuSelecao;
  /** opcao para selecionar a posicao do dinossauro */
  @FXML
  private MenuItem acima1;
  /** opcao para selecionar a posicao do dinossauro */
  @FXML
  private MenuItem abaixo1;
  /** imagem do t-rex 1 */
  @FXML
  private ImageView tRex;
  /** controla a velocidade do t-rex */
  @FXML
  private Slider sliderTRex;

  /**************************************************************** <p>
  * Metodo: Controlador (construtor) <p>
  * Funcao: Cria o controlador do javafx ja com o css <p>
  @param arquivo arquivo FXML da aplicacao
  @param css arquivo css de estilizacao
  @return <code>N/A</code> construcao do controlador
  ****************************************************************/

  public Controlador(String arquivo, String css) throws Exception {
    super(arquivo, css);
    movimentoTRex.setCaminho(caminhoB2);
    movimentoTRex.start();
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
    movimentoTRex = new Movimento(tRex, sliderTRex);
  }

  /**************************************************************** <p>
  * Metodo: botaoStart <p>
  * Funcao: modifica a cena da interface para a selcao da posicao
  dos dinossauros quando o botao start eh pressionado <p>
  @param event evento que iniciou o metodo (click)
  @return <code>void</code> 
  ****************************************************************/

  @FXML
  public void botaoStart(MouseEvent event) {
    grupoInicial.setVisible(false);
    grupoSelecao.setVisible(true);
  }

  /**************************************************************** <p>
   * Metodo: acima1Pressionado <p>
   * Funcao: faz o primeiro dinossauro ficar na barte de cima da pista <p>
   @param event evendo do javafx
   @return <code>void</code> 
   ****************************************************************/
  @FXML
  void acima1Pressionado(ActionEvent event) {
    System.out.println("pressionado acima");
    menuSelecao.setText("Acima");
  }

  /**************************************************************** <p>
   * Metodo: abaixo1Pressionado <p>
   * Funcao: faz o primeiro dinossauro ficar na barte de baixo da pista <p>
   @param event evendo do javafx
   @return <code>void</code> 
   ****************************************************************/
  @FXML
  void abaixo1Pressionado(ActionEvent event) {
    System.out.println("pressionado abaixo");
    menuSelecao.setText("Abaixo");
  }
}
