package com.food.sistemas.sodapopapp.response;

import com.food.sistemas.sodapopapp.modelo.Almacen;

import java.util.ArrayList;

public class ResponsableResponse {

    private ArrayList<Almacen> almacen;
    private boolean error;

    public ArrayList<Almacen> getResponsables() {
        return almacen;
    }

    public void setResponsables(ArrayList<Almacen> almacenes) {
        this.almacen = almacen;
    }



}
