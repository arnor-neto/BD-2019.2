/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakeifood;

import java.util.ArrayList;

/**
 *
 * @author arnor
 */
public class Carrinho {
    
    public ArrayList<Integer> id_pratos;
    public ArrayList<String> nome_pratos;
    public ArrayList<String> preco_pratos;
    public ArrayList<Integer> quantidades;

    
    private int id_restaurante;
    
    public Carrinho(){
        id_pratos = new ArrayList<>();
        quantidades = new ArrayList<>();
        nome_pratos = new ArrayList<>();
        preco_pratos = new ArrayList<>();
        id_restaurante = 0;
        
    }
    
    public void setRestaurante(int id_restaurante){
        this.id_restaurante = id_restaurante;
    }
    
    public int getRestaurante(){
        return id_restaurante;
    }
    
    public void addItem(int id_prato, String nome_prato, String preco, int quantidade){
        id_pratos.add(id_prato);
        nome_pratos.add(nome_prato);
        preco_pratos.add(preco);
        quantidades.add(quantidade);
    }
    
    public void removeItem(int index){
        id_pratos.remove(index);
        nome_pratos.remove(index);
        preco_pratos.remove(index);
        quantidades.remove(index);
    }
    
    public void esvaziaCarrinho(){
        int nElementos = id_pratos.size();
        for(int i = 0; i < nElementos; i++){
            removeItem(0);
        }
    }
}
