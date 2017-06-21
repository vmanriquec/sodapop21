package com.food.sistemas.sodapopapp.modelo;

import java.util.Date;

public class Pedido {


private int idpedido;
    private int idcliente;
    private int idmesa;
    private Double totalpedido;
    private String estadopedido ;
    private Date fechapedido;
    private int idusuario;
    private int idalmacen;


    public Pedido(int idpedido,int idcliente,int idmesa,Double totalpedido,String estadopedido,Date fechapedido,int idusuario,int idalmacen )
    {
        super();
        this.idpedido=idpedido;
        this.idcliente=idcliente;
        this.idmesa=idmesa;
        this.totalpedido=totalpedido;
        this.estadopedido=estadopedido;
        this.fechapedido=fechapedido;
        this.idusuario=idusuario;
        this.idalmacen=idalmacen;
    }

    public Pedido(int idcliente,int idmesa,Double totalpedido,String estadopedido,Date fechapedido,int idusuario,int idalmacen )
    {
        super();
        this.idcliente=idcliente;
        this.idmesa=idmesa;
        this.totalpedido=totalpedido;
        this.estadopedido=estadopedido;
        this.fechapedido=fechapedido;
        this.idusuario=idusuario;
        this.idalmacen=idalmacen;
    }




public int getIdalmacen(){return  idalmacen;}
    public void setIdalmacen(int idalmacen){this.idalmacen=idalmacen;}

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdmesa() {
        return idmesa;
    }

    public void setIdmesa(int idmesa) {
        this.idmesa = idmesa;
    }

    public Double getTotalpedido() {
        return totalpedido;
    }

    public void setTotalpedido(Double totalpedido) {
        this.totalpedido = totalpedido;
    }

    public String getEstadopedido() {
        return estadopedido;
    }

    public void setEstadopedido(String estadopedido) {
        this.estadopedido = estadopedido;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }


}
