
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.*;

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
  private ImageView dino;

  @FXML
  private Slider sliderTeste;

  public Controlador(String arquivo, String css) throws Exception {
    super(arquivo, css);
    testeDino.start();
    testeDino.setCaminho(caminhoB1);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    testeDino = new Movimento(dino, sliderTeste);
  }

}
