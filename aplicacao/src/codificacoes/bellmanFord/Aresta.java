/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.bellmanFord;

/**
 *
 * @author BRUNO
 */
public class Aresta {
    
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
    
    public void mostrarAresta(){
        
        System.out.println("(" + this.getVerticeInicio() + "," + this.getVerticeFim() + "," + this.getPeso() + ")");
    }
    
}
