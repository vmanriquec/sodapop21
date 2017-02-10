package com.food.sistemas.sodapopapp.response;

import com.food.sistemas.sodapopapp.modelo.Almacen;

import java.util.ArrayList;

public class ResponsableResponse {

    private ArrayList<Almacen> almacenes;
    private boolean error;

    public ArrayList<Almacen> getResponsables() {
        return almacenes;
    }

    public void setResponsables(ArrayList<Almacen> almacenes)
    {
        this.almacenes = almacenes;
    }
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }



}
