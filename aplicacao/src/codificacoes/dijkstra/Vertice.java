/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.dijkstra;

/**
 *
 * @author BRUNO
 */
public class Vertice {
    
    private int posicao; //Nome do vertice
    private Vertice PI; //Pai do vertice
    private int distancia; //Distância da origem até o vertice
    
    public Vertice(){
        
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public Vertice getPI() {
        return PI;
    }

    public void setPI(Vertice PI) {
        this.PI = PI;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
    
    
    
}
