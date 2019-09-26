/*
 * Esse programa tem o objetivo de simular a contaminacao de uma pessoa pelo virus da gripe (Influenza)
 * Tem-se o objetivo de demonstrar a multiplicao de tais virus e o combate por parte dos leucocitos
 * Entretanto, trata-se de um sistema apenas para fins ilustrativos
 */
package com.uniflbellini.projeto_oop;
import java.util.concurrent.TimeUnit;
/**
 * MainCorpo - Classe principal onde ocorre a chamada de metodos em sequencia para funcionamento da funcao
 * @author leonino
 */
public class MainCorpo {

    /**
     * @param args argumentos enviados pelo terminal
     * @throws InterruptedException prepara o codigo para interrupcoes que nao setem flags na JVM 
     */
    public static void main(String[] args)  throws InterruptedException{
        Cabeca cabeca = new Cabeca(); 
        int qtd_vir = 0, passo = 250; // passo eh em milissegundos  
        
        // 5 virus iniciais por agregacao com a cor de numero 5
        for (int i = 0; i < 5;i++)
            cabeca.addvirus(new Influenza(5));
        
        /*
         * Nao foi especificada na documentacao do projeto um metodo ou checagem para o final
         * Logo a simulacao apenas terminara quando qtd_vir chegar a zero
         */
        for(int i = 0; ; i++)
        {
            // metodo sleep funciona como uma funcao delay() de um microcontrolador, todo o programa é pausado
            TimeUnit.MILLISECONDS.sleep(passo); 
            /*
             * devido a sua natureza "randomica", leucocitos podem nascer sobre virus 
             * logo cedo na iteracao tambem eh checado se ha a necessidade de matar os leucocitos
             * logo depois sao movimentados os leucocitos
             */
            cabeca.checarLeucocitos();
            cabeca.matarLeucocitos();
            cabeca.nascerLeucocitos();
            cabeca.movimentarLeucocitos();
            cabeca.checarLeucocitos();
            
            /* 
             * virus dependem do tempo de iteracao como velocidade
             * a checagem de virus sempre retorna a quantidade atual destes
             */
            cabeca.movimentarVirus(i);
            cabeca.checarLeucocitos();
            qtd_vir = cabeca.checarVirus();
            
            
            // como na renderizacao de um jogo, o "mapa" eh atualizado com as posicoes
            cabeca.atualizarCabeca();
            cabeca.desenhaCabeca();
            /*
             * as infos necessarias impressas abaixo, posicionar acima de desenhaCabeca()
             * caso desejar resultado semelhante ao video
             */
            cabeca.mostrarContagem(i, passo);
            cabeca.limparMapa();
            
            if(qtd_vir == 0)
                break;
        }
        System.out.println("Parabens aos leucocitos!\n O paciente foi curado");
    }
    
}
