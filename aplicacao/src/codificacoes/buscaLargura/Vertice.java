/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.buscaLargura;

/**
 *
 * @author BRUNO
 */
public class Vertice {
    
    private String nome;
    private String cor;
    private int distancia;
    private Vertice predecessor;
    private int posicao;
    
    public Vertice(){
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Vertice getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertice predecessor) {
        this.predecessor = predecessor;
    }
    
    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    
    public void mostarVertice(){
        
        System.out.print(this.getCor() + " | ");
        System.out.print(this.getDistancia() + " | ");
        if(this.predecessor == null) System.out.println("-1 | ");
        else System.out.println(this.predecessor.getPosicao() + " | ");
        
    }
}
