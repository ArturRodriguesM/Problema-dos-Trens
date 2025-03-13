/* ***************************************************************
* Autor............:  Artur Rodrigues Moura Rocha
* Matricula........:  202310240
* Inicio...........:  23/03/2024
* Ultima alteracao.:  29/03/2024
* Nome.............:  Ponto
* Funcao...........:  Classe de pontos. Indica coordenadas na aplicacao
*************************************************************** */
package model;

public class Ponto {
  private double x; // coordenada x
  private double y; // coodernada y

  /*
   * ***************************************************************
   * Metodo: Ponto
   * Funcao: Construtor da classe Ponto
   * Parametros: coordenadas x e y
   * Retorno: Metodo construtor nao possui retorno
   */
  public Ponto(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /*
   * ***************************************************************
   * Metodo: Ponto
   * Funcao: Construtor da classe Ponto. Responsavel por copiar o objeto
   * do parametro para o objeto atual.
   * Parametros: objeto do tipo ponto
   * Retorno: Metodo construtor nao possui retorno
   */
  public Ponto(Ponto ponto) {
    this.x = ponto.getX();
    this.y = ponto.getY();
  }

  /*
   * ***************************************************************
   * Metodos: Getters
   * Funcao: Getters dos atributos da classe
   * Parametros: N/A
   * Retorno: Respectivos atributos
   */
  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  /*
   * ***************************************************************
   * Metodos: Setters
   * Funcao: Setters dos atributos da classe
   * Parametros: Respectivos atributos
   * Retorno: VOID
   */
  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  /*
   * ***************************************************************
   * Metodos: toString (herdada)
   * Funcao: retornar os valores do objeto
   * Parametros: N/A
   * Retorno: string
   */
  @Override
  public String toString() {
    return "(" + getX() + ", " + getY() + ")";
  }
}
