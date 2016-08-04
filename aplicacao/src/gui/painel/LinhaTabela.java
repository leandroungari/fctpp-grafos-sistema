/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.painel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 *   
 * @author Leandro Ungari <leandroungari@gmail.com>
 * @date   August 02,2016
 */
public class LinhaTabela {

    private final SimpleIntegerProperty code;
    private final SimpleStringProperty nome;
    private final SimpleIntegerProperty distancia;
    
    public LinhaTabela(Integer code, String nome, Integer distancia){
        this.code = new SimpleIntegerProperty(code);
        this.nome = new SimpleStringProperty(nome);
        this.distancia = new SimpleIntegerProperty(distancia);
    }

    public Integer getCode(){
        return code.get();
    }
    
    public String getNome(){
        return nome.get();
    }
    
    public Integer getDistancia(){
        return distancia.get();
    }
    
    public void setCode(int code){
        this.code.set(code);
    }
    
    public void setNome(String nome){
        this.nome.set(nome);
    }
    
    public void setDistancia(int distancia){
        this.distancia.set(distancia);
    }
}
