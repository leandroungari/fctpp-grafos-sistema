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
public class LinhaTabelaDados {

    private final SimpleIntegerProperty code;
    private final SimpleStringProperty nome;
    
    public LinhaTabelaDados(Integer code, String nome){
        this.code = new SimpleIntegerProperty(code);
        this.nome = new SimpleStringProperty(nome);
    }

    public Integer getCode(){
        return code.get();
    }
    
    public String getNome(){
        return nome.get();
    }
    
    public void setCode(int code){
        this.code.set(code);
    }
    
    public void setNome(String nome){
        this.nome.set(nome);
    }
    
}
