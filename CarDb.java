package com.food.sistemas.sodapopapp;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by usuario on 09/07/2017.
 */

public class CarDb extends RealmObject {
      private   int iddetallepedido ;
        @PrimaryKey


        private int idproducto;

        private Double precio;
    
    private int cantidadapedir;
  
 
    private String nombreproducto;

    private String imagen;

    public CarDb() {
        }

        private CarDb(Builder builder) {

            this.cantidadapedir = builder.cantidadapedir;

            this.idproducto = builder.idproducto;

            this.nombreproducto = builder.nombreproducto;
            this.imagen = builder.imagen;
            this.precio = builder.precio;
            this.iddetallepedido=builder.iddetallepedido;

        }

        public Double getprecio() {
            return precio;
        }

        public void setprecio(Double precio) {
            this.precio = precio;
        }

        public int getIddetallepedido() {
            return iddetallepedido;
        }

        public void setIddetallepedido(int iddetallepedido) {
            this.iddetallepedido = iddetallepedido;
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

    public String getimagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }





        public static class Builder {

            private int cantidadapedir;

            private int idproducto;
            private int iddetallepedido;

            private String nombreproducto;
            private String imagen;
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
            public Builder setimagen(String imagen) {
                this.imagen = imagen;
                return this;
            }
            public Builder setcantidadapedir(int cantidadapedir) {
                this.cantidadapedir = cantidadapedir;
                return this;
            }
            public Builder setiddetallepedido(int iddetallepedido) {
                this.iddetallepedido = iddetallepedido;
                return this;
            }

            public CarDb build() {
                return new CarDb(this);
            }
        }

    }

