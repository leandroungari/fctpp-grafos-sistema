/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.buscaProfundidade;

/**
 *
 * @author BRUNO
 */
public class Vertice {
    
    private String nome;
    private String cor;
    private Vertice predecessor;
    private int tempoInicio;
    private int tempoFim;
    private int posicao;
    private int componenteConexa;
    
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

    public Vertice getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertice predecessor) {
        this.predecessor = predecessor;
    }

    public int getTempoInicio() {
        return tempoInicio;
    }

    public void setTempoInicio(int tempoInicio) {
        this.tempoInicio = tempoInicio;
    }

    public int getTempoFim() {
        return tempoFim;
    }

    public void setTempoFim(int tempoFim) {
        this.tempoFim = tempoFim;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getComponenteConexa() {
        return componenteConexa;
    }

    public void setComponenteConexa(int componenteConexa) {
        this.componenteConexa = componenteConexa;
    }
    
    public void mostarVertice(){
        
        System.out.print(this.getPosicao() + " | ");
        System.out.print(this.getCor() + " | ");
        if(this.predecessor == null) System.out.print("-1 | ");
        else System.out.print(this.predecessor.getPosicao() + " | ");
        System.out.print(this.getTempoInicio() + " | ");
        System.out.print(this.getTempoFim() + " | ");
        System.out.println(this.getComponenteConexa() + " | ");
        
    }

}
