/****************************************************************
* Autor............: Artur Rodrigues Moura Rocha
* Matricula........: 202310240 
* Inicio...........: 25/02/2025
* Ultima alteracao.: 14/03/2025
* Nome.............: Controlador.java
* Funcao...........: Controle da interface do javafx
****************************************************************/
package controller;

import java.net.URL;
import javafx.scene.Group;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.*;

/**************************************************************** <p>
* Classe: Controlador <p>
* Funcao: Controle da interface do javafx <p>
****************************************************************/

public class Controlador extends Declarador implements Initializable {
  // Pontos para o caminho B (pela DIREITA)
  // private Ponto b0 = new Ponto(732, -60);
  private Ponto b1 = new Ponto(732, 68);
  private Ponto b2 = new Ponto(570, 68);
  private Ponto b3 = new Ponto(570, 260);
  private Ponto b4 = new Ponto(732, 260);
  private Ponto b5 = new Ponto(732, 355);
  private Ponto b6 = new Ponto(570, 355);
  private Ponto b7 = new Ponto(570, 547);
  private Ponto b8 = new Ponto(732, 547);
  private Ponto b9 = new Ponto(732, 675);
  private Ponto[] circuitoB1 = { b1, b2, b3, b4, b5, b6, b7, b8, b9 };
  private Caminho caminhoB1 = new Caminho( // caminho da DIREITA comecando por CIMA
      new Ponto(732, 15), // Inicio B1
      new Ponto(732, -60), // Reinicio B1
      circuitoB1);

  Movimento testeDino;

  @FXML
  private Group grupoInicial; //possui os titulo "dino's run" e o botao start

  /**************************************************************** <p>
  * Metodo: Controlador (construtor) <p>
  * Funcao: Cria o controlador do javafx ja com o css <p>
  @param arquivo arquivo FXML da aplicacao
  @param css arquivo css de estilizacao
  @return <code>N/A</code> construcao do controlador
  ****************************************************************/

  public Controlador(String arquivo, String css) throws Exception {
    super(arquivo, css);
    // testeDino.start();
    // testeDino.setCaminho(caminhoB1);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // testeDino = new Movimento(dino, sliderTeste);
    grupoInicial.setVisible(true);
  }

  /**************************************************************** <p>
  * Metodo: botaoStart <p>
  * Funcao: modifica a cena da interface quando o botao start eh 
  clicado <p>
  @param event evento que iniciou o metodo (click)
  @return <code>void</code> 
  ****************************************************************/

  @FXML
  public void botaoStart(MouseEvent event) {
    grupoInicial.setVisible(false);

  }

}
