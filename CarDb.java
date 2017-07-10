package com.food.sistemas.sodapopapp;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by usuario on 09/07/2017.
 */

public class CarDb extends RealmObject {

        public static final String K_CAR_PLATE_NUMBER = "idproducto";

        @PrimaryKey


        private int idproducto;

        private Double precio;
    
    private int cantidadapedir;
  
 
    private String nombreproducto;
    

        public CarDb() {
        }

        private CarDb(Builder builder) {

            this.cantidadapedir = builder.cantidadapedir;

            this.idproducto = builder.idproducto;

            this.nombreproducto = builder.nombreproducto;

            this.precio = builder.precio;

        }

        public Double getprecio() {
            return precio;
        }

        public void setprecio(Double precio) {
            this.precio = precio;
        }

        public int getidproducto() {
            return idproducto;
        }

        public void setidproducto(int idproducto) {
            this.idproducto = idproducto;
        }

        public String getnombreproducto() {
            return nombreproducto;
        }

        public void setnombreproducto(String nombreproducto) {
            this.nombreproducto = nombreproducto;
        }

        public int getcantidadapedir() {
            return cantidadapedir;
        }

        public void setcantidadapedir(int cantidadapedir) {
            this.cantidadapedir = cantidadapedir;
        }

        public static class Builder {

            private int cantidadapedir;

            private int idproducto;

            private String nombreproducto;

            private Double precio;

            public Builder setprecio(Double precio) {
                this.precio = precio;
                return this;
            }

            public Builder setidproducto(int idproducto) {
                this.idproducto = idproducto;
                return this;
            }

            public Builder setnombreproducto(String nombreproducto) {
                this.nombreproducto = nombreproducto;
                return this;
            }

            public Builder setcantidadapedir(int cantidadapedir) {
                this.cantidadapedir = cantidadapedir;
                return this;
            }

            public CarDb build() {
                return new CarDb(this);
            }
        }

    }

