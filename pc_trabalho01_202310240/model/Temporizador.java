/****************************************************************
* Autor............: Artur Rodrigues Moura Rocha
* Matricula........: 202310240 
* Inicio...........: 10/03/2025
* Ultima alteracao.: 14/03/2025
* Nome.............: Temporizador.java
* Funcao...........: Responsavel por calcular a passagem de tempo
                     do sistema para a realizacao de animacoes.
                     Feita do tipo abstrato, pois o metodo "acao"
                     e variavel de acordo com os objetos em animacao.
                     Eh uma extensao da classe "AnimationTimer"
****************************************************************/
package model;

import javafx.animation.AnimationTimer;

public abstract class Temporizador extends AnimationTimer {
  private long tempoInicial = -1; // Possui o instante de tempo em que 1 frame comeca.
                                  // Inicializado com "-1" para indicar que nao ha animacao no momento
  @SuppressWarnings("unused")
  private double variacao_De_Tempo; // Passagem de tempo de 1 frame
  boolean pausa = false; // Variavel responsavel por pausar o funcionamento da animacao com espera
                         // ocupada

  /*
   * ***************************************************************
   * Metodo: handle (Herdado)
   * Funcao: calcular a variacao de tempo do sistema. Eh rodado no inicio de cada
   * frame a cada frame
   * Parametros: tempoFinal = tempo atual do sistema, em nanosegundos
   * Retorno: VOID
   */
  @Override
  public void handle(long tempoFinal) {
    // Calculo de variacao de tempo de um unico frame, em segundos
    double variacao_De_Tempo = (tempoFinal - tempoInicial) / (double) Math.pow(10, 9);

    // cria uma certa acao que sera realizada com o tempo calculado
    if (!pausa) {
      acao(variacao_De_Tempo);
    }

    // atualiza tempoInicial
    tempoInicial = tempoFinal;
  }

  /**
   * ***************************************************************
   * Metodo: acao
   * Funcao: realizar a animacao do objeto
   * Parametros: variacao_De_Tempo
   * Retorno: VOID
   */

  public abstract void acao(double variacao_De_Tempo);

  /*
   * ***************************************************************
   * Metodo: start (Herdado)
   * Funcao: iniciar uma animacao. Atribui ao tempo inicial o
   * tempo atual do sistema
   * Parametros: N/A
   * Retorno: VOID
   */
  @Override
  public void start() {
    tempoInicial = System.nanoTime();
    super.start();
  }

  /*
   * ***************************************************************
   * Metodo: stop (Herdado)
   * Funcao: parar uma certa animacao. Atribui ao tempo inicial -1
   * Parametros: N/A
   * Retorno: VOID
   */
  @Override
  public void stop() {
    tempoInicial = -1;
    super.stop();
  }

  public long getTempoInicial() {
    return this.tempoInicial;
  }
}