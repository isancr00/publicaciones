/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.MenuFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Menu;
import modelo.Usuario;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author sanch
 */
@Named
@SessionScoped
public class MenuController implements Serializable{
    
    private MenuModel modelo;            
    @EJB
    private MenuFacadeLocal menuEJB;
    
     @PostConstruct
    public void init(){
        modelo = new DefaultMenuModel(); 
        obtenerMenu();
    }
     
   /* public void showMenus(){
        Usuario devuelve = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        List<Menu> menus = menuEJB.obtenerMenusUsuario(devuelve);        
    }
*/
    
    public void obtenerMenu(){
        
        Usuario devuelve = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        List<Menu> menus = menuEJB.obtenerMenusUsuario(devuelve);
        ArrayList<DefaultSubMenu> submenusLista = new ArrayList<>();
        
        for(int i=0;i<menus.size();i++){
            Menu menu = menus.get(i);
            
            if(menu.getTipo().equals("S")){
                DefaultSubMenu subMenu = DefaultSubMenu.builder().label(menu.getNombre()).build();
                submenusLista.add(subMenu);
            }else if(menu.getTipo().equals("I")){
                DefaultMenuItem item = DefaultMenuItem.builder().value(menu.getNombre()).url(menu.getUrl()).build();    
                item.setUrl(menu.getUrl());
                
                if(menu.getMenu() != null){
                    //Tiene padre
                    String nombrePadre = menu.getMenu().getNombre();
                    
                    for(int j=0;j<submenusLista.size();j++){
                        if(submenusLista.get(j).getLabel().equals(nombrePadre)){
                            submenusLista.get(j).getElements().add(item);
                        }
                    }                    
                }else{
                    modelo.getElements().add(item);

                }
            }
        }
        
        for(int i= 0; i<submenusLista.size();i++){
            modelo.getElements().add(submenusLista.get(i));
        }
        
    }
    
    public String destruirSesion(){  
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public MenuModel getModelo() {
        return modelo;
    }

    public void setModelo(MenuModel modelo) {
        this.modelo = modelo;
    }

    public MenuFacadeLocal getMenuEJB() {
        return menuEJB;
    }

    public void setMenuEJB(MenuFacadeLocal menuEJB) {
        this.menuEJB = menuEJB;
    }
    
    
    
    
    
}
