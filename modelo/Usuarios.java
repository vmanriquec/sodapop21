
package com.food.sistemas.sodapopapp.modelo;

/**
 * Created by Perseo on 04/08/2014.
 */
public class Usuarios {
    private String nombreusuario,claveusuario,almacenusuario;
    public Usuarios(String nombreusuario, String claveusuario,String almacenusuario )
    {
        super();
        this.nombreusuario=nombreusuario;
        this.claveusuario=claveusuario;
        this.almacenusuario=almacenusuario;

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

    @Override
    public String toString(){
        return this.nombreusuario;
    }

}
