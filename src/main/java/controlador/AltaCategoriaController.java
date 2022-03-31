/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categoria;
import EJB.CategoriaFacadeLocal;

/**
 *
 * @author sanch
 */

@Named
@ViewScoped
public class AltaCategoriaController implements Serializable {
    private Categoria cat;
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    @PostConstruct
    public void init(){
        cat = new Categoria();
    }
    
    public void insertarCategoria(){
        try{
            categoriaEJB.create(cat);        
        }catch(Exception e){
            System.out.println("Error al insertar la categoria " + e.getMessage());
        }
    }

    public Categoria getCat() {
        return cat;
    }

    public void setCat(Categoria cat) {
        this.cat = cat;
    }
    
    
}
