/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;

/**
 *
 * @author sanch
 */
@Named
@ViewScoped
public class PlantillaController implements Serializable{
    public void verificarYMostrar() throws IOException{
       Usuario devuelve = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
       if((devuelve == null) || (devuelve.getRol().getIdRol() != 3)){
           FacesContext.getCurrentInstance().getExternalContext().redirect("noPermiso.xhtml");
       }     
    }
}
