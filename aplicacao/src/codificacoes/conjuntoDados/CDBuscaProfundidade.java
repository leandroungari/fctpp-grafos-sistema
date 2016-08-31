/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.conjuntoDados;

import codificacoes.representacaoComputacional.Adjacencia;

/**
 *
 * @author BRUNO
 */
public class CDBuscaProfundidade {
    
    public VerticeSelecao vertices[];
    public int tempo;
    
    public CDBuscaProfundidade(Adjacencia adj, VerticeSelecao [] selecoes){
        
        this.vertices = selecoes;
        
    }

    //0 = Ver classificados de um grupo.
    
    public VerticeSelecao[] DFS(Adjacencia adj, int verticeInicial, int finalidade){
        
        //Inicializando
        int i, componenteConexa = 0;
        
        for(i = 0; i < this.vertices.length; i++){
            
            this.vertices[i].setCor("BRANCO");
        }
        
        this.tempo = 0;
        
        //Precorrendo e verificando
        if(finalidade == 0) this.DFSVisitaClassificadosGrupo(this.vertices[0], adj, ++componenteConexa);
        else if(finalidade == 1) this.DFSVisitaMediaPublicoDeUmGrupo(this.vertices[0], adj, ++componenteConexa);
        else if(finalidade == 2) this.DFSVisitaCalcularCampeao(this.vertices[0], adj, ++componenteConexa);
        for(i = 0; i < this.vertices.length; i++){
            
            if(this.vertices[i].getCor().equalsIgnoreCase("BRANCO")){
                
                if(finalidade == 0) this.DFSVisitaClassificadosGrupo(this.vertices[i], adj, ++componenteConexa);
                else if(finalidade == 1) this.DFSVisitaMediaPublicoDeUmGrupo(this.vertices[i], adj, ++componenteConexa);
                else if(finalidade == 2) this.DFSVisitaCalcularCampeao(this.vertices[i], adj, ++componenteConexa);
            }
        }
        
        return(this.vertices);
    }
    
    public void DFSVisitaClassificadosGrupo(VerticeSelecao v, Adjacencia adj, int componenteConexa){
        
        int i;
        int gols = 0;
        
        v.setCor("CINZA");
        this.tempo++;
        
        adj.inicializaIterator(v.getNumero());
        while(adj.hasNext(v.getNumero())){
            
            
            i = adj.next(v.getNumero());
            
            gols = adj.getPeso(v.getNumero(), i);
            if(gols == 0){
                    
                this.vertices[v.getNumero()].setPontos(1);
            }
            else {
                
                this.vertices[v.getNumero()].setPontos(3);
                this.vertices[v.getNumero()].setSaldoDeGols(gols);
            }
            
            if(this.vertices[i].getCor().equalsIgnoreCase("BRANCO")){                
                
                this.DFSVisitaClassificadosGrupo(this.vertices[i], adj, componenteConexa);
            }
        }//Fim while
        
        v.setCor("PRETO");
        this.tempo++;        
    }
    
    
    public void DFSVisitaMediaPublicoDeUmGrupo(VerticeSelecao v, Adjacencia adj, int componenteConexa){
        
        int i;
        double publico = 0.0;
        
        v.setCor("CINZA");
        this.tempo++;
        
        adj.inicializaIterator(v.getNumero());
        while(adj.hasNext(v.getNumero())){
            
            i = adj.next(v.getNumero());
            
            publico = (double) adj.getPeso(v.getNumero(), i);
            this.vertices[v.getNumero()].setPublicoTotal(publico);
            
            if(this.vertices[i].getCor().equalsIgnoreCase("BRANCO")){

                this.DFSVisitaMediaPublicoDeUmGrupo(this.vertices[i], adj, componenteConexa);
            }
        }//Fim while
        
        v.setCor("PRETO");
        this.tempo++;        
    }
    
    public void DFSVisitaCalcularCampeao(VerticeSelecao v, Adjacencia adj, int componenteConexa){
        
        int i;
        int numeroJogos = 0;
        
        v.setCor("CINZA");
        v.setFaseDaCompeticao("Fase de grupos");
        this.tempo++;
        
        adj.inicializaIterator(v.getNumero());
        while(adj.hasNext(v.getNumero())){
            
            
            i = adj.next(v.getNumero());
            
            numeroJogos = this.vertices[v.getNumero()].getNumeroDeJogos() + 1;
            
            this.vertices[v.getNumero()].setNumeroDeJogos(numeroJogos);
            
            if(this.vertices[i].getCor().equalsIgnoreCase("BRANCO")){

                this.DFSVisitaCalcularCampeao(this.vertices[i], adj, componenteConexa);
            }
        }//Fim while
        
        v.setCor("PRETO");
        if(v.getNumeroDeJogos() == 7) v.setFaseDaCompeticao("Final");
        else if(v.getNumeroDeJogos() == 6) v.setFaseDaCompeticao("Semi-final");
        else if(v.getNumeroDeJogos() == 5) v.setFaseDaCompeticao("Quartas de final");
        else if(v.getNumeroDeJogos() == 4) v.setFaseDaCompeticao("Oitavas de final");
        this.tempo++;
    }
    
    public void resultadosDaBusca(int finalidade){
        
        System.out.println("\n\nResultados da Busca\n");
        
        for(int i = 0; i < this.vertices.length; i++){
            
            if(finalidade == 2) {
                if(i == 0) System.out.println("Nº | Fase da competição | Classificação | Nº Jogos | ");
                this.vertices[i].mostrarSelecao(2);
            }
            else if(finalidade == 3) {
                if(i == 0) System.out.println("Nº | Publico Total | Média de Publico");
                this.vertices[i].mostrarSelecao(3);
            }
            else {
                this.vertices[i].mostrarSelecao(1);
                if( ((i+1) != 0) && ((i+1)%4 == 0) ) System.out.println();
            }
        }
    }
    

}
