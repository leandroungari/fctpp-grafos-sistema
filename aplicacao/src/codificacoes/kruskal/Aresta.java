/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.kruskal;

/**
 *
 * @author BRUNO
 */
public class Aresta implements Comparable<Aresta>{

    private int verticeInicio;
    private int verticeFim;
    private int peso;

    public Aresta(int verticeInicio, int verticeFim, int peso) {
        this.verticeInicio = verticeInicio;
        this.verticeFim = verticeFim;
        this.peso = peso;
    }

    public int getVerticeInicio() {
        return verticeInicio;
    }

    public void setVerticeInicio(int verticeInicio) {
        this.verticeInicio = verticeInicio;
    }

    public int getVerticeFim() {
        return verticeFim;
    }

    public void setVerticeFim(int verticeFim) {
        this.verticeFim = verticeFim;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    public String mostrarAresta(){
        
        return "(" + this.getVerticeInicio() + "," + this.getVerticeFim() + "," + this.getPeso() + ")\n";
    }

    @Override
    public int compareTo(Aresta a) {
        
        if(this.getPeso() < a.getPeso()) return(-1);
        else if (this.getPeso() > a.getPeso()) return(1);
        else return(0);
    }
    
}
