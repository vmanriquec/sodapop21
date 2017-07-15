package com.food.sistemas.sodapopapp.Realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by usuario on 11/07/2017.
 */

public class Detallepedidorealm {
    @PrimaryKey
    public static  int iddetallepedidorealm;

    private int idproductorealm;
    private int cantidadrealm;
    private Double precventarealm;

    private String nombreproductorealm;
    private String imagenrealm;
    private int idalmacenrealm;



    public static int getIddetallepedidorealm() {
        return iddetallepedidorealm;
    }

    public static void setIddetallepedidorealm(int iddetallepedidorealm) {
        Detallepedidorealm.iddetallepedidorealm = iddetallepedidorealm;
    }

    public int getIdproductorealm() {
        return idproductorealm;
    }

    public void setIdproductorealm(int idproductorealm) {
        this.idproductorealm = idproductorealm;
    }

    public int getCantidadrealm() {
        return cantidadrealm;
    }

    public void setCantidadrealm(int cantidadrealm) {
        this.cantidadrealm = cantidadrealm;
    }

    public Double getPrecventarealm() {
        return precventarealm;
    }

    public void setPrecventarealm(Double precventarealm) {
        this.precventarealm = precventarealm;
    }

    public String getNombreproductorealm() {
        return nombreproductorealm;
    }

    public void setNombreproductorealm(String nombreproductorealm) {
        this.nombreproductorealm = nombreproductorealm;
    }



    public String getImagenrealm() {
        return imagenrealm;
    }

    public void setImagenrealm(String imagenrealm) {
        this.imagenrealm = imagenrealm;
    }


    public int getIdalmacenrealm() {
        return idalmacenrealm;
    }

    public void setIdalmacenrealm(int idalmacenrealm) {
        this.idalmacenrealm = idalmacenrealm;
    }






}
