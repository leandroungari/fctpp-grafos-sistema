/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.conjuntoDados;

/**
 *
 * @author BRUNO
 */
public class CDOrdenacao {
    
    public static VerticeSelecao [] quickSort(VerticeSelecao[] vetor, int inicio, int fim) {
        
        if (inicio < fim) {
            
            int posicaoPivo = separar(vetor, inicio, fim);
            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }
        
        return(vetor);
    }
   
    private static int separar(VerticeSelecao[] vetor, int inicio, int fim) { 
        
        VerticeSelecao pivo = vetor[inicio];
        int i = inicio + 1, f = fim;
        
        //Ordem decrescente - Do maior para o menor.
        while (i <= f) {
            if (vetor[i].getMediaDePublico() >= pivo.getMediaDePublico()) i++;
            else if (pivo.getMediaDePublico() > vetor[f].getMediaDePublico()) f--;
            else {
                VerticeSelecao troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++; f--;
            }
        }
               
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return(f);
    }
    
}
