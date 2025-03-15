
/****************************************************************
* Autor............: Artur Rodrigues Moura Rocha
* Matricula........: 202310240 
* Inicio...........: 10/03/2025
* Ultima alteracao.: 14/03/2025
* Nome.............: Principal.java
* Funcao...........: Iniciar a aplicacao
****************************************************************/

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**************************************************************** <p>
* Classe: Principal <p>
* Funcao: Iniciar a aplicacao <p>
****************************************************************/
public class Principal extends Application {
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
    Controlador circuito = new Controlador("../view/scene.fxml", "../view/index.css");
    palco = Declarador.getG_Palco();
    palco.setResizable(false);
    palco.sizeToScene();
    circuito.abrir();
  }
}
