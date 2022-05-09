/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Usuario;

/**
 *
 * @author sanch
 */
@Named
@SessionScoped
public class IndexController implements Serializable{
    private Usuario usuario;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
     @PostConstruct
    public void init(){
        usuario = new Usuario();
    }
    
    public String verificarUsuario(){
        Usuario usuarioB = null;
        
        usuarioB =  usuarioEJB.verificarUsuario(this.usuario);
        if(usuarioB == null){
            System.out.println("Insuficiente");
            return "noPermiso.xhtml";
        }else{
            System.out.println("Suficiente");
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioB);
            return "privado/principal.xhtml";
        }
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }    
    
}
