
package com.food.sistemas.sodapopapp.modelo;

import java.net.IDN;

/**
 * Created by Perseo on 04/08/2014.
 */
public class Usuarios {
    private String idusuario,nombreusuario,claveusuario,almacenusuario,imagen;

    public Usuarios(String idusuario,String nombreusuario, String claveusuario,String almacenusuario ,String imagen)
    {
        super();
        this.idusuario=idusuario;
        this.nombreusuario=nombreusuario;
        this.claveusuario=claveusuario;
        this.almacenusuario=almacenusuario;
        this.imagen=imagen;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }





    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getClaveusuario() {
        return claveusuario;
    }

    public void setClaveusuario(String claveusuario) {
        this.claveusuario = claveusuario;
    }


    public String getAlmacenusuario() {
        return almacenusuario;
    }

    public void setAlmacenusuario(String almacenusuario) {
        this.almacenusuario = almacenusuario;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString(){
        return this.nombreusuario;
    }

}
