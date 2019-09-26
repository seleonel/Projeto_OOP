package com.uniflbellini.projeto_oop;


/**
 * CelulasBoca.java - Definem o conjunto (orgao) de celulas de uma boca
 * @author leonino
 * @see Celulas - Classe mae a qual define a posicao e cor das celulas
 */
public class CelulasBoca extends Celulas{
    
    /**
     * Construtor basico que apenas invoca super
     * @param x - x da celula definida na cabeca
     * @param y - y da celula definida na cabeca
     * @param cor - cor da celula
     */
    public CelulasBoca(int x, int y, int cor){
        super(x, y, cor);
    
    }
}
