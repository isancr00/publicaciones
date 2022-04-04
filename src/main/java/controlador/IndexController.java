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
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;

/**
 *
 * @author sanch
 */
@Named
@ViewScoped
public class IndexController implements Serializable{
    private Usuario usuario;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
     @PostConstruct
    public void init(){
        usuario = new Usuario();
    }
    
    public String verificarUsuario(){
        if(usuarioEJB.verificarUsuario(usuario) == null){
            return "permiossinsuficientes.xhtml";
        }else{
            return "privado/inicio.xhtml";
        }
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }    
    
}
