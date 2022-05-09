/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Categoria;

/**
 *
 * @author sanch
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> implements CategoriaFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }

    @Override
    public Categoria find(String nombre) {
        List<Categoria> categorias = findAll();
        
        if(categorias != null){
            for(int i=0;i<categorias.size();i++){
                if(nombre.equals(categorias.get(i).getNombre())){
                    return categorias.get(i);
                }
            }
        }
        
        return null;
    }
    
}
