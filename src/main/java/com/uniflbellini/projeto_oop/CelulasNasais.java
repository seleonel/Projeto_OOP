/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniflbellini.projeto_oop;

/**
 * CelulasNasais.java - Definem o conjunto (orgao) de celulas de um nariz
 * @author leonino
 * @see Celulas - Classe mae a qual define a posicao e cor das celulas
 */
public class CelulasNasais extends Celulas{
    /**
     * Construtor basico o qual apenas invoca super
     * @param x - posicao x da celula definida na cabeca
     * @param y - posicao y da celula definida na cabeca
     * @param cor - cor da celula
     */
    public CelulasNasais(int x, int y, int cor){
        super(x, y, cor);
    }
    
}
