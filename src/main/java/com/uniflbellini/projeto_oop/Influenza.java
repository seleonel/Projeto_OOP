package com.uniflbellini.projeto_oop;

/**
 * Influenza.java - Classe filha de Virus, representa uma entidade dinamica de virus
 * @author leonino
 * @see Virus
 */
public class Influenza extends Virus{
    private boolean celula; // booliano para definir se o virus esta ou nao numa celula de orgao
    
    /**
     * Construtor basico nao recebe x ou y, uma vez que a geracao de numeros ocorre na classe mae
     * @param cor cor do virus
     */
    public Influenza(int cor)
    {
        super( cor);        
    }
    
    public void estaNaCelula(boolean celula){
        this.celula = celula;
    }

    public boolean isCelula() {
        return celula;
    }

    
    
        
   
    
}
