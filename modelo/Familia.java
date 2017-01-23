
package com.food.sistemas.sodapopapp.modelo;

/**
 * Created by Perseo on 05/08/2014.
 */
public class Familia {
    private int idfamilia;
    private String nombrefamilia;

    public Familia(int idfamilia,String nombrefamilia)
    {
        super();
        this.idfamilia=idfamilia;
        this.nombrefamilia=nombrefamilia;

    }
    public int getIdfamilia() {
        return idfamilia;
    }

    public void setIdfamilia(int idfamilia) {
        this.idfamilia = idfamilia;
    }

    public String getNombrefamilia() {
        return nombrefamilia;
    }

    public void setNombrefamilia(String nombrefamilia) {
        this.nombrefamilia = nombrefamilia;
    }

    @Override
    public String toString(){
        return this.nombrefamilia;
    }

}
