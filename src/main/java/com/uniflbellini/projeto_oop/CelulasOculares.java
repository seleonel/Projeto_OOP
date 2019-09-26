package com.uniflbellini.projeto_oop;


/**
 * CelulasOculares.java - Definem o conjunto (orgao) de celulas de dois olhos
 * @author leonino
 * @see Celulas - Classe mae a qual define a posicao e cor das celulas
 */
public class CelulasOculares extends Celulas{
    /**
     * Construtor basico o qual apenas invoca super
     * @param x - posicao x da celula definida na cabeca
     * @param y - posicao y da celula definida na cabeca
     * @param cor - cor da celula
     */
    public CelulasOculares(int x, int y, int cor)
    {
        super(x, y, cor);
    }
    
}
