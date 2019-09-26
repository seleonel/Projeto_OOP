package com.uniflbellini.projeto_oop;

import java.security.SecureRandom; // aparentemente SecureRandom gera numeros randomicos de fato randomicos

/**
 * Leucocitos.java - Classe a qual define os leucocitos, entidade dinamica conhecida por "globulos brancos", dependem da classe mae Celulas
 * @author leonino
 * @see Celulas
 */
public class Leucocitos extends Celulas {
    private int velocidade;
    private long nascimento;
    private static final SecureRandom random = new SecureRandom();
    
    // enum de 8 direcoes diferentemente do enum definido para virus
    // me baseei na notacao de tekken por facilidade
    enum Direcoes  {
        CIMA,
        BAIXO,
        DIREITA,
        ESQUERDA,
        UF, //CIMA DIREITA
        UB, // BAIXO ESQUEDA 
        DB, //BAIXO ESQUERDA
        DF // BAIXO DIREITA
    }
    
    /**
     * Construtor de Leucocitos ja inicializa o tempo de "nascimento", pode existir um delay entre a invocacao da classe e o retorno de Millis
     * @param x posicao x dos leucocitos na cabeca
     * @param y posicao y dos leucocitos na cabeca
     * @param cor cor dos leucocitos
     */
    public Leucocitos(int x, int y, int cor){
        super( x, y, cor);
        this.nascimento = System.currentTimeMillis();
        
    }
    
    // tirado de https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enums
    // essa funcao retorna um enum randomico da lista dada
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
    /**
     * Principal metodo para movimentacao dos leucocitos, a partir de uma direcao aleatoria dada, move para qualquer uma das 8 direcoes
     */
    public void mover()
    {
       
      switch(randomEnum(Direcoes.class))
        {
            case CIMA: this.setY(this.getY() + 1); 
                    break;
            case BAIXO: this.setY(this.getY() - 1);
                    break;
            case DIREITA: this.setX(this.getX() + 1);
                    break;
            case ESQUERDA: this.setX(this.getX() - 1);
                    break;
            case UF: 
                this.setX(this.getX() + 1);
                this.setY(this.getY() + 1);
                break;
            case UB:
                this.setX(this.getX() - 1);
                this.setY(this.getY() + 1);
                break;
            case DB:
               this.setX(this.getX() - 1);
               this.setY(this.getY() - 1);
               break;
            case DF:
               this.setX(this.getX() + 1);
               this.setY(this.getY() - 1);
               break;
        }
        this.colisao(); // metodo definido na mae, realiza a checagem de colisao
      
    }

    /*
     * Serie de getters e setters
    */
    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public long getNascimento() {
        return nascimento;
    }

    public void setNascimento(long nascimento) {
        this.nascimento = nascimento;
    }
    
    
    
}
