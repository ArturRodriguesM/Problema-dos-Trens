/****************************************************************
* Autor............: Artur Rodrigues Moura Rocha
* Matricula........: 202310240 
* Inicio...........: 10/03/2025
* Ultima alteracao.: 14/03/2025
* Nome.............: Caminho.java
* Funcao...........: Classe de caminho. Indica o caminho do movimento.
****************************************************************/

package model;

/**************************************************************** <p>
* Classe: Caminho <p>
* Funcao: Guarda o caminho, em pixeis, da movimentacao dos objetos
interface <p>
****************************************************************/

public class Caminho {
  private Ponto inicio; // Ponto em que o objeto ira iniciar o movimento
  private Ponto reinicio; // Ponto em que o objeto ira reiniciar o movimento
  private Ponto[] circuito; // Conjunto de pontos nos quais o objeto ira se mover

  /**************************************************************** <p>
  * Metodo: Caminho <p>
  * Funcao Cria um novo caminho de movimento (construtor) <p>
  @param inicio se fornecido, sera onde o objeto iniciara o movimento
  @param reinicio se fornecido, sera onde ele reiniciara o movimento
  quando o circuito acabar
  @param circuito array de pontos por onde o objeto se deslocara. Entre
  um ponto e outro, ele se movimentara em linha reta.
  @return <code>N/A</code> um novo caminho
  ****************************************************************/

  public Caminho(Ponto inicio, Ponto reinicio, Ponto[] circuito) {
    this.inicio = inicio;
    this.reinicio = reinicio;
    this.circuito = circuito;
  }

  /**************************************************************** <p>
  * Metodo: Caminho <p>
  * Funcao Cria um novo caminho de movimento (construtor) <p>
  @param caminho o circuito do caminho passado no parametro
  sera o novo caminho criado
  @return <code>N/A</code> um novo caminho
  ****************************************************************/

  public Caminho(Caminho caminho) {
    this.inicio = new Ponto(caminho.getInicio());
    this.reinicio = new Ponto(caminho.getReinicio());
    this.circuito = new Ponto[caminho.getCircuito().length];
    for (int i = 0; i < circuito.length; i++) {
      this.circuito[i] = caminho.getCircuito()[i];
    }

  }

  /*
  *************************************************************** <p>
  * Metodo: Getters <p>
  * Funcao: getters dos atributos da classe <p>
  @param N/A
  @return  atributos
  ****************************************************************/

  public Ponto getInicio() {
    return inicio;
  }

  public Ponto getReinicio() {
    return reinicio;
  }

  public Ponto[] getCircuito() {
    return circuito;
  }

  /*
  *************************************************************** <p>
  * Metodo: Setters <p>
  * Funcao: setters dos atributos da classe <p>
  @param  respectivos atributos
  @return  N/A
  ****************************************************************/

  public void setInicio(Ponto inicio) {
    this.inicio = inicio;
  }

  public void setReinicio(Ponto reinicio) {
    this.reinicio = reinicio;
  }

  public void setCircuito(Ponto[] circuito) {
    this.circuito = circuito;
  }
}
