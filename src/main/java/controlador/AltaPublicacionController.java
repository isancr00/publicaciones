/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CategoriaFacadeLocal;
import EJB.PublicacionFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categoria;
import modelo.Publicacion;
import modelo.Usuario;

/**
 *
 * @author sanch
 */
@Named
@ViewScoped
public class AltaPublicacionController implements Serializable{
    private Publicacion publicacion;
    private Usuario usuario;
    private Categoria categoria;
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    @EJB
    private PublicacionFacadeLocal publicacionEJB;
    
    @PostConstruct
    public void init(){
       publicacion = new Publicacion();
       usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
       categoria = new Categoria();
    }
    
    
    
   public void insertarPublicacion(){
       publicacion.setPersona(usuario.getPersona());
       
       if(categoriaEJB.find(categoria) == null){
           categoriaEJB.create(categoria);
       }
      
        publicacion.setCategoria(categoria);
        
        publicacionEJB.create(publicacion);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", 
        "Publicación registrada con éxito."));
   }
    
}
