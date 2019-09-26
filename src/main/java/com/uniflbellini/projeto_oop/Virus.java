package com.uniflbellini.projeto_oop;
import java.security.SecureRandom; 
import java.util.Random;


/**
 * Virus.java - Superclasse/mãe, essa classe representa um virus com cor e posicoes no espaco (cabeca)
 * @author leonino
 * @see Influenza - unica classe filha
 */
public class Virus {
    private int velocidade, cor, x, y;
    private Random randomico = new Random();
    private static final SecureRandom random = new SecureRandom();
    /*
     * enum "guarda" as 4 direcoes possiveis de movimentacao
     * em C++ checks baseados em enums sao mais otimizados que checks em strings, desconheco quanto a java
     */
    enum Direcoes  {
        CIMA,
        BAIXO,
        DIREITA,
        ESQUERDA
    }
    /**
     * Construtor da classe recebe uma cor e ja randomiza as posicoes em x e y
     * @param cor - cor a qual o virus recebera no quadro
     */
    public Virus(int cor){
        this.x = randomico.nextInt(30);
        this.y = randomico.nextInt(60);
        this.cor = cor;
 
    }
    // tirado de https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enums
    // retorna um enum randomico para ser usado no switch case de movimento
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
    /**
     * Metodo para checagem de colisao nao recebe argumentos, sua principal funcao eh inverter as posicoes por um simples check 
     */
    public void colisao()
    {
        if(this.x > 29)
            this.x = 0;         // aparece na esquerda

        else if(this.x < 0)
            this.x = 29;        // aparece na direita
  
        if(this.y > 59)         // aparece sobre a cabeca
            this.y = 0;
        else if (this.y < 0)
            this.y = 59;        // aparece embaixo
    }
    
    /**
     * Metodo para mover o virus eh mais limitado por possuir apenas 4 direcoes
     * @param iteracoes passadas ja faz o trabalho da velocidade, podendo passar por um simples check
     */
    public void mover(int iteracoes)
    {
        // nao movimenta do primeiro "turno"
        if(( iteracoes % 2 == 0 ) && ( iteracoes != 0 ))
        {
            // switch checa qual enum randomico foi retornado
            switch(randomEnum(Direcoes.class))
            {
                case CIMA: --this.y; 
                       break;
                case BAIXO: ++this.y;
                       break;
                case DIREITA: ++this.x;
                       break;
                case ESQUERDA: --this.x;
                       break;
            } 
            colisao(); 
        }
    }
    
    
    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
