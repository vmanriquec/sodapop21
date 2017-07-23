
package com.food.sistemas.sodapopapp.modelo;

/**
 * Created by USUARIO on 24/04/2016.
 */
public class dashboardpedido {
    private int iddashboard;
    private double totalpedido;
    private;
    private String correoalm;
    public Almacen(int idalmacen, String nombrealm, String telfonoalm, String correoalm )
    {
        super();
        this.idalmacen=idalmacen;
        this.nombrealm=nombrealm;
        this.telfonoalm=telfonoalm;
        this.correoalm=correoalm;
    }




    public int getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(int idalmacen) {
        this.idalmacen = idalmacen;
    }

    public String getNombrealm() {
        return nombrealm;
    }

    public void setNombrealm(String nombrealm)
    {
        this.nombrealm = nombrealm;
    }

    public String getTelfonoalm() {
        return telfonoalm;
    }
    public void setTelfonoalm(String telfonoalm)
    {
        this.telfonoalm = telfonoalm;
    }

    public void setCorreoalm(String correoalm) {
        this.correoalm = correoalm;
    }

    public String getCorreoalm() {
        return correoalm;
    }

    @Override public String toString(){return String.valueOf(idalmacen)+" - "+ String.valueOf(nombrealm);
    }


}


