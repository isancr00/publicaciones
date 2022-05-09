/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CategoriaFacadeLocal;
import EJB.PublicacionFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private String nombreCat;
    
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
       try{
           
        Calendar calendar = Calendar.getInstance();

        Date dateObj = calendar.getTime();
        
        
        publicacion.setPersona(usuario.getPersona());
        insertarCategoria(nombreCat);
        publicacion.setCategoria(categoria);
        publicacion.setFecha(dateObj);
        
        publicacionEJB.create(publicacion);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", 
        "Publicación registrada con éxito."));
       }catch(Exception e){
        System.out.println("Error al insertar la publi " + e.getMessage());

       }      
   }
   
   public void insertarCategoria(String nombre){
       Categoria cat = new Categoria();
       cat.setNombre(nombre);
       
       if(categoriaEJB.find(nombre) == null){
           categoriaEJB.create(cat);
       }
       
        categoria = cat;

   }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaFacadeLocal getCategoriaEJB() {
        return categoriaEJB;
    }

    public void setCategoriaEJB(CategoriaFacadeLocal categoriaEJB) {
        this.categoriaEJB = categoriaEJB;
    }

    public PublicacionFacadeLocal getPublicacionEJB() {
        return publicacionEJB;
    }

    public void setPublicacionEJB(PublicacionFacadeLocal publicacionEJB) {
        this.publicacionEJB = publicacionEJB;
    }   

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }
}
