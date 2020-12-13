
package com.mycompany.p2_pss.dao;

import java.util.ArrayList;

public interface InterfaceDAO<T> {

    public void salvar(T elem);
    
    public void excluir(int id);
    
    public void alterar(T elem);
    
    public T buscar(int id);
    
    public ArrayList<T> buscar();
    
}
