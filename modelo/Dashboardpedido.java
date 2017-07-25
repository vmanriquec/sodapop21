
package com.food.sistemas.sodapopapp.modelo;

/**
 * Created by USUARIO on 24/04/2016.
 */
public class Dashboardpedido {
    private double totalpedidos;
    private double totalentradas;
    private String nombrealm ;
    private double totalsalidas;

    public Dashboardpedido(double totalentradas, String nombrealm, double totalsalidas,double totalpedidos )
    {
        super();
        this.totalentradas=totalentradas;
        this.nombrealm=nombrealm;
        this.totalsalidas=totalsalidas;
        this.totalpedidos=totalpedidos;
    }



    public double getTotalsalidas() {
        return totalsalidas;
    }

    public void setTotalsalidas(double totalsalidas) {
        this.totalsalidas = totalsalidas;
    }

    public double gettotalpedidos() {
        return totalpedidos;
    }

    public void settotalpedidos(double totalpedidos) {
        this.totalpedidos = totalpedidos;
    }

    public double getTotalentradas() {
        return totalentradas;
    }

    public void setTotalentradas(double totalentradas) {
        this.totalentradas = totalentradas;
    }

    public String getNombrealm() {
        return nombrealm;
    }

    public void setNombrealm(String nombrealm) {
        this.nombrealm = nombrealm;
    }


    @Override public String toString(){return nombrealm.toString();
    }


}


