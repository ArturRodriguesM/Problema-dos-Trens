
/****************************************************************
* Autor............: Artur Rodrigues Moura Rocha
* Matricula........: 202310240 
* Inicio...........: 10/03/2025
* Ultima alteracao.: 18/03/2025
* Nome.............: Principal.java
* Funcao...........: Iniciar a aplicacao
****************************************************************/

import controller.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**************************************************************** <p>
* Classe: Principal <p>
* Funcao: Iniciar a aplicacao <p>
****************************************************************/
public class Principal extends Application {

  /** icone da aplicacao */
  Image icone = new Image("img/t_rex_branco_icon.png");

  public static void main(String args[]) throws Exception {
    launch(args);
    System.exit(0);
  }

  /**************************************************************** <p>
  * Metodo: start <p>
  * Funcao: Iniciar a aplicacao do javafx <p>
  @param palco objeto da interface
  @return <code>void</code> 
  ****************************************************************/

  @Override
  public void start(Stage palco) throws Exception {
    Font.loadFont(Principal.class.getResourceAsStream("view/Minecraft.ttf"), 10);
    Controlador circuito = new Controlador("../view/scene.fxml", "../index.css");
    palco = Declarador.getG_Palco();
    palco.setResizable(false);
    palco.getIcons().add(icone);
    palco.setTitle("dino's run");
    palco.sizeToScene();
    circuito.abrir();
  }
}
