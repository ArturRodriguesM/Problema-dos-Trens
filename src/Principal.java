
/****************************************************************
* Autor............:  Artur Rodrigues Moura Rocha
* Matricula........:  202310240
* Inicio...........:  23/03/2024
* Ultima alteracao.:  18/04/2024
* Nome.............:  Principal
* Funcao...........:  Iniciar a aplicacao do Javafx
*************************************************************** */

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Principal extends Application {
  public static void main(String args[]) throws Exception {
    launch(args);
    System.exit(0);
  }

  /****************************************************************
   * Metodo: start (metodo herdado de Application)
   * Funcao: Iniciar a aplicacao JavaFX
   * Parametros: palco
   * Retorno: VOID
   */
  @Override
  public void start(Stage palco) throws Exception {
    Controlador circuito = new Controlador("../view/scene.fxml");
    palco = Declarador.getG_Palco();
    palco.setResizable(false);
    palco.sizeToScene();
    circuito.abrir();
  }
}
