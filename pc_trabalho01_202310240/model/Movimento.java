/****************************************************************
* Autor............: Artur Rodrigues Moura Rocha
* Matricula........: 202310240 
* Inicio...........: 10/03/2025
* Ultima alteracao.: 14/03/2025
* Nome.............: Movimento.java
* Funcao...........: Responsavel por determinar o movimento de uma
                    imagem qualquer por um certo caminho
****************************************************************/

package model;

import javafx.scene.image.ImageView;
import javafx.scene.control.Slider;
import javafx.geometry.NodeOrientation;
import javafx.application.Platform;

/**************************************************************** <p>
* Classe: Movimento <p>
* Funcao: Cria o movimento de uma certa imagem <p>
****************************************************************/

public class Movimento extends Thread {
  private ImageView objeto; // Imagem que e movimentada
  private Ponto posicao; // Posicao do objeto
  private double velocidadeX; // velocidade de deslocamento no eixo x
  private double velocidadeY; // velocidade de deslocamento no eixo y
  private boolean sentidoX; // sentido do deslocamento do fantasma no eixo x
  private boolean sentidoY; // sentido do deslocamento do fantasma no eixo y
                            // OBS.:true - velocidade positiva; false - velocidade negativa;
  private volatile boolean emMovimento = false; // Indica se o objeto esta em movimento ou nao
  private Caminho caminho; // Caminho que o objeto se movimentara
  private Slider controle_Velocidade; // controle de velocidade associado ao objeto
  private boolean em_Regiao_Critica = false; // indica se o objeto esta em alguma regiao critica ou nao
  /**
  * FUNCIONAMENTO DA ANIMACAO <p>
  * 1- calcula-se o quanto que o objeto necessita se movimentar (variavel 
  * variacao_Total) para cada eixo  <p>
  * 2- calcula o sentido de movimento (positivo ou negativo) para cada eixo <p>
  * 3- a medida que o objeto se movimenta (metodo atualizar), sua variacao 
  * aumenta, ocasionando em 2 condicoes: <p>
  * - se a variacao atual ainda eh menor que a total, o objeto mantem-se 
  * movimentando <p>
  * - se a variacao atual e igual ou maior que a total, o objeto finaliza sua 
  * movimentacao <p>
  * 4- o objeto parte em direcao ao outro ponto do caminho indicado no 
  * parametro(variavel indice_Atual) <p>
  * 5- se os pontos acabarem, o objeto eh teleportado ao ponto de reinicio da 
  * animacao <p>
  * 6- se nao ha ponto de reinicio, entao a animacao eh finalizada <p>
  */
  Temporizador animacao = new Temporizador() { // responsavel por movimentar o objeto

    double variacao_Total_X;
    double variacao_Total_Y;
    double variacao_Atual_X;
    double variacao_Atual_Y;
    double finalX, finalY; // Ponto em que o objeto precisa alcancar no final da animacao
    int indice_Atual; // Indice do ponto em que o objeto precisa se mover

    /**************************************************************** <p>
    * Metodo: start (Herdado) <p>
    * Funcao: responsavel por iniciar a animacao do objeto. Sempre comeca
    * do indice 0 <p>
    @param N/A 
    @return <code>void</code> apenas inicializa o movimento
    ****************************************************************/

    @Override
    public void start() {
      inicializacao(0); // inicializa o movimento para o primeiro ponto do array (de indice 0)
      super.start(); // inicializa a animacao (chama o metodo acao)
    }

    /**************************************************************** <p>
    * Metodo: inicializacao <p>
    * Funcao: realizar o calculo da variacao total e do sentido que o
    * objeto deve se deslocar ao ponto indicado pelo indice do parametro <p>
    @param atual  indice do ponto atual a ser deslocado
    @return <code>void</code>
    ****************************************************************/

    public void inicializacao(int atual) {

      indice_Atual = atual;

      finalX = caminho.getCircuito()[indice_Atual].getX();
      finalY = caminho.getCircuito()[indice_Atual].getY();
      variacao_Total_X = finalX - getPosicaoX();
      variacao_Total_Y = finalY - getPosicaoY();
      variacao_Atual_X = 0;
      variacao_Atual_Y = 0;

      if (variacao_Total_X < 0)
        setSentidoX(false);
      else if (variacao_Total_X > 0)
        setSentidoX(true);
      if (variacao_Total_Y < 0)
        setSentidoY(false);
      else if (variacao_Total_Y > 0)
        setSentidoY(true);

      variacao_Total_X = Math.abs(variacao_Total_X);
      variacao_Total_Y = Math.abs(variacao_Total_Y);
    }

    /**************************************************************** <p>
    * Metodo: acao <p>
    * Funcao: movimentar o objeto ao ponto desejado. Analisar se a variacao
    * atual do objeto chegou na variacao total e se a movimentacao
    * foi completa. Reinciar o movimento caso haja ponto de reinicio,
    * e para-lo caso nao.<p>
    @param variacao_de_Tempo double tempo de 1 frame 
    @return <code>void</code>
    ****************************************************************/

    @Override
    public void acao(double variacao_De_Tempo) {
      if (variacao_Atual_X < variacao_Total_X) { // se o objeto ainda nao atingiu o ponto no eixo x
        // mover x
        atualizarX(variacao_De_Tempo);
        variacao_Atual_X += Math.abs(getVelocidadeX() * variacao_De_Tempo);
      } else {
        // se a variacao necessaria de movimento foi atingida, entao o movimento e
        // finalizado
        setPosicaoX(finalX);
      }

      if (variacao_Atual_Y < variacao_Total_Y) { // se o objeto ainda nao atingiu o ponto no eixo y
        // mover y
        atualizarY(variacao_De_Tempo);
        variacao_Atual_Y += Math.abs(getVelocidadeY() * variacao_De_Tempo);
      } else {
        // se a variacao necessaria de movimento foi atingida, entao o movimento e
        // finalizado
        setPosicaoY(finalY);
      }

      // se os pontos x e y foram atingidos
      if (variacao_Atual_X >= variacao_Total_X && variacao_Atual_Y >= variacao_Total_Y) {

        // o objeto ira ao ponto final
        setPosicaoX(finalX);
        setPosicaoY(finalY);
        // o objeto se movimentara ao proximo ponto, sendo esto o proximo objeto do
        // array
        indice_Atual++;

        if (indice_Atual == caminho.getCircuito().length) { // se todos os pontos foram atingidos
          // Renicia a animacao
          if (caminho.getReinicio() == null) { // se nao ha ponto de reinicio
            // finaliza do movimento
            emMovimento = false;
            animacao.stop();
          } else { // se ha ponto de reinicio
            // o movimento comeca novamente
            setPosicaoX(caminho.getReinicio().getX());
            setPosicaoY(caminho.getReinicio().getY());
            inicializacao(0);
          }
        } else { // se ainda ha pontos para o objeto percorrer no circuito
          // inicio do movimento para o proximo ponto
          inicializacao(indice_Atual);
        }
      }

    }
  };

  /**************************************************************** <p>
  * Metodo: Movimento <p>
  * Funcao: Construtor da classe Movimento. <p>
  @param objeto Imagem do objeto se movimentando
  @param controle_Velocidade slider da interface
  @return <code>N/A</code> movimento criado
  ****************************************************************/

  public Movimento(ImageView objeto, Slider controle_Velocidade) {
    this.objeto = objeto;
    this.controle_Velocidade = controle_Velocidade;
    setVelocidadeX(controle_Velocidade.getValue());
    setVelocidadeY(controle_Velocidade.getValue());
    posicao = new Ponto(objeto.getLayoutX(), objeto.getLayoutY());
  }

  /**************************************************************** <p>
  * Metodo: run <p>
  * Funcao: inicia o movimento quando a thread inicializa <p>
  @param N/A 
  @return <code>void</code> 
  ****************************************************************/

  @Override
  public void run() {
    Platform.runLater(() -> moverPara());
  }

  /**************************************************************** <p>
  * Metodo: moverPara <p>
  * Funcao: Metodo responsavel por deslocar o objeto por um certo
  * caminho determinado pela variavel "caminho". Um caminho sempre possui
  * um ponto de inicio, um array de pontos de deslocamento
  * e um ponto de reinicio para iniciar novamente o deslocamento.
  * Caso o ponto de inicio seja nulo, o movimento comeca a partir
  * do ponto atual do objeto. Caso o ponto de reinicio seja nulo,
  * o movimento eh encerrado.<p>
  @param N/A 
  @return <code>void</code>
  ****************************************************************/

  public void moverPara() {
    pararMovimento();
    if (caminho.getInicio() != null) { // analisa se ha ponto de inicio pre-definido
      // se teleporta para esse ponto
      setPosicao(new Ponto(caminho.getInicio()));
    }
    // inicio do movimento
    if (!emMovimento) {
      emMovimento = true;
    }
    animacao.start();
  }

  /****************************************************************
   * Metodo: pararMovimento
   * Funcao: consegue parar o movimento do objeto
   @param N/A
   @return <code>void</code>
    ****************************************************************/
  public void pararMovimento() {
    if (emMovimento) {
      animacao.stop();
      emMovimento = false;
    }
  }

  /**************************************************************** <p>
  * Metodo: atualizarX <p>
  * Funcao: responsavel por deslocar o objeto no eixo x durante o tempo
  determinado pelo parametro, em certa velocidade.
  Baseado na formula fisica S = S0 +- v*t <p>
  @param tempo tempo de deslocamento
  @return <code>void</code> atualiza o layout x do objeto
  ****************************************************************/

  public void atualizarX(double tempo) {
    setPosicaoX(getPosicaoX() + getVelocidadeX() * tempo);
    objeto.setLayoutX(getPosicaoX());
  }

  /**************************************************************** <p>
  * Metodo: atualizarY <p>
  * Funcao: responsavel por deslocar o objeto no eixo y durante o tempo
  determinado pelo parametro, em certa velocidade.
  Baseado na formula fisica S = S0 +- v*t <p>
  @param tempo tempo de deslocamento
  @return <code>void</code> atualiza o layout y do objeto
  ****************************************************************/

  public void atualizarY(double tempo) {
    setPosicaoY(getPosicaoY() + getVelocidadeY() * tempo);
    objeto.setLayoutY(getPosicaoY());
  }

  /*
  *************************************************************** <p>
  * Metodo: Getters <p>
  * Funcao: getters dos atributos da classe <p>
  @param N/A
  @return  atributos
  ****************************************************************/

  public ImageView getObjeto() {
    return objeto;
  }

  public double getPosicaoX() {
    return posicao.getX();
  }

  public double getPosicaoY() {
    return posicao.getY();
  }

  public Ponto getPosicao() {
    return posicao;
  }

  public double getVelocidadeX() {
    setVelocidadeX(getControle_Velocidade().getValue()); // sempre retorna o valor indicado pelo controlador de
                                                         // velocidade do objeto
    setSentidoX(sentidoX); // indica o sentido da velocidade apos atualizar o valor
    return velocidadeX;
  }

  public double getVelocidadeY() {
    setVelocidadeY(getControle_Velocidade().getValue());
    setSentidoY(sentidoY);
    return velocidadeY;
  }

  public Slider getControle_Velocidade() {
    return controle_Velocidade;
  }

  public boolean getEmMovimento() {
    return emMovimento;
  }

  public boolean getSentidoX() {
    return sentidoX;
  }

  public Temporizador getAnimacao() {
    return animacao;
  }

  public Caminho getCaminho() {
    return caminho;
  }

  public boolean getEm_Regiao_Critica() {
    return em_Regiao_Critica;
  }

  /*
  *************************************************************** <p>
  * Metodo: Setters <p>
  * Funcao: setters dos atributos da classe <p>
  @param  respectivos atributos
  @return  N/A
  ****************************************************************/

  public void setCaminho(Caminho caminho) {
    this.caminho = caminho != null ? new Caminho(caminho) : null;
  }

  public void setAnimacao(Temporizador animacao) {
    this.animacao = animacao;
  }

  private void setSentidoX(boolean sentidoX) {
    setVelocidadeX(Math.abs(velocidadeX)); // retira o sentido atual da velocidade
    if (!sentidoX) { // caso seja false, a velocidade torna-se negativa
      setVelocidadeX(velocidadeX * (double) -1);
      getObjeto().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT); // o sentido da imagem se inverte
    } else {
      getObjeto().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT); // o sentido da imagem se inverte
    }
    this.sentidoX = sentidoX;
  }

  private void setSentidoY(boolean sentidoY) {
    setVelocidadeY(Math.abs(velocidadeY)); // retira o sentido atual da velocidade
    if (!sentidoY) { // caso seja false, a velocidade torna-se negativa
      setVelocidadeY(velocidadeY * (double) -1);
    }
    this.sentidoY = sentidoY;
  }

  public boolean setSentidoY() {
    return sentidoY;
  }

  public void setObjeto(ImageView objeto) {
    this.objeto = objeto;
  }

  public void setPosicaoX(double posicaoX) {
    posicao.setX(posicaoX); // modifica a coordenada x do ponto
    objeto.setLayoutX(posicaoX); // atualiza a posicao da imagem do objeto
  }

  public void setPosicaoY(double posicaoY) {
    posicao.setY(posicaoY); // modifica a coordenada y do ponto
    objeto.setLayoutY(posicaoY); // atualiza a posicao da imagem do objeto
  }

  public void setPosicao(Ponto posicao) {
    this.posicao = posicao;
    setPosicaoY(posicao.getY());
    setPosicaoX(posicao.getX());
  }

  public void setVelocidadeX(double velocidadeX) {
    this.velocidadeX = velocidadeX;
  }

  public void setVelocidadeY(double velocidadeY) {
    this.velocidadeY = velocidadeY;
  }

  public void setEmMovimento(boolean emMovimento) {
    this.emMovimento = emMovimento;
  }

  public void setControle_Velocidade(Slider controle_Velocidade) {
    this.controle_Velocidade = controle_Velocidade;
  }

  public void setEm_Regiao_Critica(boolean em_Regiao_Critica) {
    this.em_Regiao_Critica = em_Regiao_Critica;
  }
}