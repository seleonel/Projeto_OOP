package com.uniflbellini.projeto_oop;
import java.util.ArrayList;
import java.util.Random; 
import java.util.ListIterator; // ListIterator age como ponteiros por ENTRE os itens da lista
/**
 * Cabeca.java - Classe essencial para o projeto, define uma cabeca com orgaos/celulas e esta eh infectadas
 * @author leonino
 * @see Celulas
 * @see Virus
 */
public class Cabeca {
    /*
     * Mapa definido no instanciamento permanece o mesmo
     * Uma copia do mapa eh gerada para ser modificada
     */
    int[][] mapa = new int [30][60];
    int[][] copiamapa = new int [30][60];

    // serie de listas
    private final ArrayList<Leucocitos>      leuc = new ArrayList<>();
    private final ArrayList<CelulasOculares> oc = new ArrayList<>();
    private final ArrayList<CelulasNasais>   nas = new ArrayList<>();
    private final ArrayList<CelulasBoca>     bocc = new ArrayList<>();
    private final ArrayList<Influenza>       virus = new ArrayList<>();
    
    
    private final Random randomico = new Random(); 
    
    // vetor de string das cores que podem ser utilizadas
    // virus sao verde e leucocitos azuis
    private final String[] corzinhas = new String[] 
    {
    "\u001B[47m", // branco
    "\u001B[45m", // magenta
    "\u001B[41m", // vermelho
    "\u001B[42m", // verde,
    "\u001B[43m", // amarelo
    "\u001B[42;1m", // verde
    "\u001b[44m", // azul
    "\u001b[0m" // reset
    };
    
    /**
     * Metodo define olhos na matriz com um for, cortando trabalho desnecessario
     */
    private void definirOlhos(){
        for(int i = 0; i < 10; i++)
        {
            oc.add(new CelulasOculares(7, 49-i ,4));
            oc.add(new CelulasOculares(8, 49-i ,4));
        }
        for(int i = 0; i < 10; i++)
        {
            oc.add(new CelulasOculares(7, 9+i ,4));
            oc.add(new CelulasOculares(8, 9+i ,4));
        }
    }
    /**
     * Metodo define nariz na matriz (rimou)
     */
    private void definirNariz(){
         for(int i = 0; i < 4; i++)
        {
            nas.add(new CelulasNasais(12+i, 28 ,4));
            nas.add(new CelulasNasais(12+i, 29 ,4));
            nas.add(new CelulasNasais(12+i, 30 ,4));
        }   
    }
    
    /**
     * Metodo define posicao das celulas da boca
     */
    private void definirBoca()
    {
        
        for(int i = 0; i < 29; i++)
        {
            bocc.add(new CelulasBoca(22, 15+i ,4));
        }   
        
    }
    

    /**
     * Construtor da cabeca define a posicao das celulas e ja define a matriz principal que servira para o resto da simulacao
     */
    public Cabeca(){
       definirBoca();
       definirOlhos();
       definirNariz();
       int i, j;
        for (i = 0; i < 30; i++ )
        {
            for (j = 0; j< 60; j++)
            {
                if(j == 0 || j == 59 || i == 0 || i == 29)
                    mapa[i][j] = 1; // bordas sao magenta
                else
                    mapa[i][j] = 0; // centro e branco
        }
       
    }
        for(CelulasBoca c: bocc){
            mapa[c.getX()][c.getY()] = c.getCor();
        }
        for(CelulasOculares c: oc){
            mapa[c.getX()][c.getY()] = c.getCor();
        }
         for(CelulasNasais c: nas){
            mapa[c.getX()][c.getY()] = c.getCor();
        }
        // para definicao dos valores da matriz copia, mapa eh "limpo" uma primeira vez
        limparMapa(); 
    }
    
    /**
     * Esse metodo recebe por agregacao o virus
     * @param inf virus recebido
     */
    public void addvirus(Influenza inf){
        virus.add(inf);
       }
    
    /**
     * Mantem a contagem de leucocitos sempre em 10 (no minimo)
     */
    public void nascerLeucocitos()
    {
        while (leuc.size() < 10)
        {
            leuc.add(new Leucocitos(randomico.nextInt(30), randomico.nextInt(60), 6));
        }
            
    }
    
    /**
     * Metodo para movimentacao simples dos leucocitos
     */
    public void movimentarLeucocitos()
    
    {
        for(Leucocitos l: leuc){
            l.mover();
        }
    }
    
    /**
     * Movimentacao dos virus depende do passo
     * @param i passo da iteracao, virus nao se movera uma iteracao
     */
    public void movimentarVirus(int i)
    {
        for(Influenza v: virus)
        {
            v.mover(i);
        }
    
    }
    /**
     * Metodo definido para matar os leucocitos apos 7 segundos
     */
    public void matarLeucocitos()
    {
        // como explicado, iterador de lista funciona como um ponteiro
        ListIterator<Leucocitos> it = leuc.listIterator();
        
        while (it.hasNext()){ //enquanto tiverem itens depois do ponteiro, continuar iterando 
           Leucocitos l = it.next();
           if((System.currentTimeMillis() - l.getNascimento()) >= 7000){
               it.remove(); // listIterator permite remover dinamicamente de um arraylist
           }
        
        }
        // por seguranca, trimToSize pois nao confio no garbagecollector
        leuc.trimToSize();
    }
    
    /**
     * Checagem principal para os leucocitos, se baseia totalmente na posicao relativa dos mesmos para cada virus presente
     * Torna a simulacao mais lenta para casos extremos, podendo esquentar seu pc!
     */
    public void checarLeucocitos()
    {
        /*
         * Ambos iteradores serao utilizados para manipulacao dinamica das listas
         */
        ListIterator<Leucocitos> leuc_it = leuc.listIterator();
        ListIterator<Influenza> vir_it = virus.listIterator();
       
        
        while(leuc_it.hasNext()){
            Leucocitos l = leuc_it.next();
            vir_it = virus.listIterator(); // forca reset do ponteiro no item antes do primeiro
            
            while(vir_it.hasNext())
            {
                Influenza v = vir_it.next();
                if(l.getX() == v.getX() && l.getY() == v.getY()) // checagem se estiverem no mesmo lugar na matriz
                {
                    
                    vir_it.remove(); // o viruz apontado eh morto
                    leuc_it.add(new Leucocitos(randomico.nextInt(29) , randomico.nextInt(59) ,6)); // um novo leucocito eh instanciado em posicao aleatoria

                }
                
            }
     
        }
        //obs: talvez seja necessario adicionar trimtosize
    }
    /**
     * Checa se o virus esta na posicao de algum orgao interno para realizar multiplicacao
     * @return retorna o tamanho de virus para servir de controle no main
     */
    public int checarVirus()
    {
        ListIterator<Influenza> vir_it = virus.listIterator();
        while(vir_it.hasNext())
        {   // para fins de equilibrio o virus nao se mantem se multiplicando caso ja tenha entrado e permaneca na celula
            Influenza v = vir_it.next();
            if(mapa[v.getX()][v.getY()] == 4 && !v.isCelula() ){ 
                vir_it.add(new Influenza(5));
                v.estaNaCelula(true); // para isso e utilizada o estaNaCelula em true
            }
            else{
                v.estaNaCelula(false);
            }
        }
        
        return virus.size();
    }
    
    /**
     * Simples funcao para printar tudas as informacoes, layout inspirado no video do prof
     * @param i iteracao no momento definida pelo for
     * @param passo milissegundos definidos no main
     */
    public void mostrarContagem(int i, int passo)
    {
        leuc.trimToSize();
        virus.trimToSize();
        System.out.println("\n" + corzinhas[6] + " " + corzinhas[7] + " " + "Quantidade de leucocitos: " + leuc.size() + "\tTempo: " + ((i*passo)/1000)+
                "\n" + corzinhas[5] + " " + corzinhas[7] + " " + "Quantidade de influenza: " + virus.size());

    }
    
    /**
     * Simples metodo para atualizar a copia do mapa em relacao aos virus e leucocitos
     */
    public void atualizarCabeca()
    {
        virus.trimToSize();
        leuc.trimToSize();
        for(Influenza v: virus)
        {
      
            copiamapa[v.getX()][v.getY()] = v.getCor();
            
        }
         for(Leucocitos l: leuc)
        {
           
            copiamapa[l.getX()][l.getY()] = l.getCor();
        }
        
    
    }
    
    /**
     * Copia do mapa eh resetado para a matriz original
     */
    public void limparMapa()
    {
        for(int i = 0; i< 30; i++){
            for(int j = 0; j< 60; j++)
            {
               
               copiamapa[i][j] = mapa[i][j]; 
                
            }
        }
    
    }
    
    /**
     * Metodo para mostrar a matriz ja atualizada ja imprime as cores no vetor de strings
     */
    public void desenhaCabeca()
    {
        for(int i = 0; i< 30; i++){
            for(int j = 0; j< 60; j++)
            {
               
                System.out.printf("%s ", corzinhas[copiamapa[i][j]]);
                if(j == 59){
                    System.out.println(corzinhas[7]); // reset necessario
                    if(i == 29)
                        System.out.printf("%s ", corzinhas[7]); // reset tb necessario
                        }
            }
        }
       
    
    }
    
}
