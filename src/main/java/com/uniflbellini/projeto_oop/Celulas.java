package com.uniflbellini.projeto_oop;

/**
 * Celulas.java - Classe mae a qual representa uma celula com presenca no espaco (cabeca) e uma cor padrao
 * @author leonino
 * @see Leucocitos - Classe de um elemento "dinamico", os leucocitos se movimentam e dependem da mae celula
 * @see CelulasNasais 
 * @see CelulasOculares 
 * @see CelulasBoca 
 */
public class Celulas {
    private int x, y, cor;
    
    /**
     * Construtor principal da classe, ja realiza checagens de colisao para caso o random posicione uma celula num local incorreto
     * @param x  posicao randomica
     * @param y  posicao randomica
     * @param cor cor da celula
     */
    public Celulas(int x, int y, int cor)
    {
        this.cor = cor;
        this.x = x;
        this.y = y;
        colisao();
    }
    
    
    /**
     * Checagem de colisao ocorre na propria classe da celula por ser mais facil manipular x e y
     * Nao recebe argumentos e realiza essa checagem em 8 direcoes 
     */
    public void colisao()
    {
    if(this.x > 29)
        {
            this.x = 0;
            
            if (this.y > 59)      this.y = 0;     // canto superior direito  
            else if (this.y < 0)  this.y = 59; // canto inferior direito
   
        }else if(this.x < 0) 
        {
            this.x = 29;
            
            if (this.y > 59)   this.y = 0;    // superior esquerdo  
            else if (this.y < 0)  this.y = 59; // canto inferior esquerdo
  
        }
        // realizadas mesmas checagens mas dependentes de y
        if(this.y > 59)
        {
            this.y = 0;
            if (this.x > 29)     this.x = 0; 
            else if (this.x < 0) this.x = 29;
            
        }
        else if(this.y < 0)
        {
            this.y = 59;
            
            if (this.x > 29)     this.x = 0;
            else if (this.x < 0) this.x = 29;
            
        }
    
    }
    
    /*
     * Serie de getters e setters 
     */
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

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }
    
}
