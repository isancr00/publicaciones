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
import javax.persistence.Query;
import modelo.Menu;
import modelo.Rol;
import modelo.Usuario;

/**
 *
 * @author sanch
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> implements MenuFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }

    @Override
    public List<Menu> obtenerMenusUsuario(Usuario us) {
        List<Menu> menus = null;
        String consulta = "FROM Menu m WHERE m.rol=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", us.getRol());
        
        menus = query.getResultList();
        
        return menus;
    }
    
}
