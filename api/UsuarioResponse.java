package com.food.sistemas.sodapopapp.api;

import com.food.sistemas.sodapopapp.modelo.Usuarios;

import java.util.ArrayList;

/**
 * Created by sistemas on 03/02/2017.
 */

public class UsuarioResponse {
    ArrayList<Usuarios> usuarios;

    public ArrayList<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
}
