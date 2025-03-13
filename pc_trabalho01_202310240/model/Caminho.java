/* ***************************************************************
* Autor............:  Artur Rodrigues Moura Rocha
* Matricula........:  202310240
* Inicio...........:  23/03/2024
* Ultima alteracao.:  30/03/2024
* Nome.............:  Caminho
* Funcao...........:  Classe de caminho. Indica o caminho do movimento.
*************************************************************** */
package model;

public class Caminho {
  private Ponto inicio; // Ponto em que o objeto ira iniciar o movimento
  private Ponto reinicio; // Ponto em que o objeto ira reiniciar o movimento
  private Ponto[] circuito; // Conjunto de pontos nos quais o objeto ira se mover

  /*
   * ***************************************************************
   * Metodo: Caminho
   * Funcao: Construtor da classe Caminho
   * Parametros: Conjunto de pontos do circuito com o ponto de inicio
   * e reinicio
   * Retorno: Metodo construtor nao possui retorno
   */
  public Caminho(Ponto inicio, Ponto reinicio, Ponto[] circuito) {
    this.inicio = inicio;
    this.reinicio = reinicio;
    this.circuito = circuito;
  }

  /*
   * ***************************************************************
   * Metodo: Caminho
   * Funcao: Construtor da classe Caminho. Responsavel por copiar o objeto
   * do parametro para o objeto atual.
   * Parametros: objeto do tipo Caminho
   * Retorno: Metodo construtor nao possui retorno
   */
  public Caminho(Caminho caminho) {
    this.inicio = new Ponto(caminho.getInicio());
    this.reinicio = new Ponto(caminho.getReinicio());
    this.circuito = new Ponto[caminho.getCircuito().length];
    for (int i = 0; i < circuito.length; i++) {
      this.circuito[i] = caminho.getCircuito()[i];
    }

  }

  /*
   * ***************************************************************
   * Metodos: Getters
   * Funcao: Getters dos atributos da classe
   * Parametros: N/A
   * Retorno: Respectivos atributos
   */
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
   * ***************************************************************
   * Metodos: Setters
   * Funcao: Setters dos atributos da classe
   * Parametros: Respectivos atributos
   * Retorno: VOID
   */
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
