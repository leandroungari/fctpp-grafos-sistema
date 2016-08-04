/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.conectividade;

/**
 *
 * @author BRUNO
 */
public class Vertice implements Comparable<Vertice>{
    
    private int posicao;
    private int conjuntoConexo;
    private boolean visitado;
    private int tempoInicio;
    private int tempoFim;
    private Vertice predecessor;
    private String corVisitaBusca;

    public Vertice() {
    }

    public Vertice(int posicao, int conjuntoConexo, boolean visitado, Vertice pai) {
        this.posicao = posicao;
        this.conjuntoConexo = conjuntoConexo;
        this.visitado = visitado;
        this.predecessor = pai;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getConjuntoConexo() {
        return conjuntoConexo;
    }

    public void setConjuntoConexo(int conjuntoConexo) {
        this.conjuntoConexo = conjuntoConexo;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
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

    public Vertice getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertice predecessor) {
        this.predecessor = predecessor;
    }

    public String getCorVisitaBusca() {
        return corVisitaBusca;
    }

    public void setCorVisitaBusca(String corVisitaBusca) {
        this.corVisitaBusca = corVisitaBusca;
    }
    
    public void exibir(){
        
        System.out.print(this.getPosicao() + " | ");
        System.out.print(this.getCorVisitaBusca()+ " | ");
        if(this.predecessor == null) System.out.print("-1 | ");
        else System.out.print(this.predecessor.getPosicao() + " | ");
        System.out.print(this.getTempoInicio() + " | ");
        System.out.print(this.getTempoFim() + " | ");
        System.out.println(this.getConjuntoConexo()+ " | ");
    }
    
    @Override
    public int compareTo(Vertice o) {
        
        if(this.getTempoFim() > o.getTempoFim()) return(1);
        else if(this.getTempoFim() < o.getTempoFim()) return(-1);
        else return(0);
    }
}
