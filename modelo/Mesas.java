package com.food.sistemas.sodapopapp.modelo;

import org.xml.sax.Parser;

/**
 * Created by Perseo on 05/08/2014.
 */
public class Mesas{
    private int idmesas;
    private int numeromesa;
    private String estadomesa;
    private int sillasmesa;
    public Mesas(int idmesas,int numeromesa,String estadomesa,int sillasmesa )
    {
        super();
        this.idmesas=idmesas;
        this.numeromesa=numeromesa;
        this.estadomesa=estadomesa;
        this.sillasmesa=sillasmesa;
    }



    public int getIdmesas() {
        return idmesas;
    }

    public void setIdmesas(int idmesas) {
        this.idmesas = idmesas;
    }

    public int getNumeromesa() {
        return numeromesa;
    }

    public void setNumeromesa(int numeromesa) {
        this.numeromesa = numeromesa;
    }

    public String getEstadomesa() {
        return estadomesa;
    }

    public void setEstadomesa(String estadomesa) {
        this.estadomesa = estadomesa;
    }

    public int getSillasmesa() {
        return sillasmesa;
    }

    public void setSillasmesa(int sillasmesa) {
        this.sillasmesa = sillasmesa;
    }
    @Override
    public String toString(){
        return String.valueOf(idmesas)+"Ms:"+String.valueOf(numeromesa);
    }


}
