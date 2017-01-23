
package com.food.sistemas.sodapopapp.modelo;

/**
 * Created by Perseo on 22/08/2014.
 */
public class Detallepedido {
    private int iddetallepedido;
    private int idproducto;
    private int cantidad;
    private Double precventa;
    private String nombreproducto;
    private int idalmacen;

      public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }
public int getIdalmacen(){return  idalmacen;}
    public void setIdalmacen(int idalmacen){this.idalmacen=idalmacen;}



    public int getIddetallepedido() {
        return iddetallepedido;
    }

    public void setIddetallepedido(int iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecventa() {
        return precventa;
    }

    public void setPrecventa(Double precventa) {
        this.precventa = precventa;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    private Double subtotal ;
    private int idpedido;

    public Detallepedido(int iddetallepedido, int idproducto, int cantidad, Double precventa, Double subtotal, int idpedido, String nombreproducto, int idalmacen )
    {
        super();
        this.iddetallepedido=iddetallepedido;
        this.idproducto=idproducto;
        this.cantidad=cantidad;
        this.precventa=precventa;
        this.subtotal=subtotal;
        this.idpedido=idpedido;
        this.nombreproducto=nombreproducto;
        this.idalmacen=idalmacen;

    }
    public Detallepedido(int idproducto, int cantidad, Double precventa, Double subtotal, int idalmacen )
    {
        super();

        this.idproducto=idproducto;
        this.cantidad=cantidad;
        this.precventa=precventa;
        this.subtotal=subtotal;
        this.idalmacen=idalmacen;

    }


}
