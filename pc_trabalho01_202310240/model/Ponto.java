/****************************************************************
* Autor............: Artur Rodrigues Moura Rocha
* Matricula........: 202310240 
* Inicio...........: 10/03/2025
* Ultima alteracao.: 14/03/2025
* Nome.............: Ponto.java
* Funcao...........: Classe de pontos. Indica coordenadas na aplicacao
****************************************************************/
package model;

public class Ponto {
  private double x; // coordenada x
  private double y; // coodernada y

  /**************************************************************** <p>
  * Metodo: Ponto <p>
  * Funcao: Construtor da classe Potno <p>
  @param x coordenada x
  @param y coordenada y
  @return <code>N/A</code> 
  ****************************************************************/

  public Ponto(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**************************************************************** <p>
  * Metodo: Ponto <p>
  * Funcao: Construtor da classe Ponto. Responsavel por copiar o 
  * objeto do parametro para o objeto atual. <p>
  @param ponto ponto a ser copiado
  @return <code>N/A</code> 
  ****************************************************************/

  public Ponto(Ponto ponto) {
    this.x = ponto.getX();
    this.y = ponto.getY();
  }

  /*
  *************************************************************** <p>
  * Metodo: Getters <p>
  * Funcao: getters dos atributos da classe <p>
  @param N/A
  @return  atributos
  ****************************************************************/

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  /*
  *************************************************************** <p>
  * Metodo: Setters <p>
  * Funcao: setters dos atributos da classe <p>
  @param  respectivos atributos
  @return  N/A
  ****************************************************************/

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + getX() + ", " + getY() + ")";
  }
}
