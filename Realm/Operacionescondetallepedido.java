package com.food.sistemas.sodapopapp.Realm;

import com.food.sistemas.sodapopapp.modelo.Detallepedido;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by usuario on 11/07/2017.
 */

public class Operacionescondetallepedido {
private Realm realm;
    public Operacionescondetallepedido(Realm realm){
this.realm=realm;
    }

/*


    public Detallepedidorealm[] obtenerdetallepedido(){
        RealmResults<Detallepedidorealm> resulta=realm.where(Detallepedidorealm.class).findAll();
        return resulta.toArray(new Detallepedidorealm[resulta.size()]);


    }
    public void actualizarcantidadpedidadetallepedido(Detallepedidorealm detallepedidorealm, int cantidad)
   {


    }

    public RealmResults<Detallepedidorealm> obtenerdetallepedidoporid(int idde){

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Detallepedidorealm> results = realm.where(Detallepedidorealm.class)
                .equalTo(String.valueOf(Detallepedidorealm.iddetallepedidorealm), idde)
                .findAll();
        return results;
    }
    public void realmgrbarenbasedatos(int iddetallepedido, int idproducto, int cantidad, Double precventa,  String nombreproducto, int idalmacen )
    {

        Realm realm = Realm.getDefaultInstance();


        Detallepedidorealm det = new Detallepedidorealm();

        det.setIddetallepedidorealm(idproducto);
        det.setIdproductorealm(idproducto);
        det.setCantidadrealm(cantidad);
        det.setPrecventarealm(precventa);
        det.setNombreproductorealm(nombreproducto);
        det.setIdalmacenrealm(idalmacen);
        realm.beginTransaction();
        realm.copyToRealm(det);
        realm.commitTransaction();

}
    public static int recuoeraycreaelidgeneradomasuno() {
        Realm realm = Realm.getDefaultInstance();
        Number number = realm.where(Detallepedidorealm.class).max("iddetallepedidorealm");
        return number == null ? 0 : number.intValue() + 1;
    }
    public void eliminadetallepedido(int iddetallepedido){
        realm.where(Detallepedidorealm.class).equalTo("iddetallepedidorealm", iddetallepedido).findFirst().deleteFromRealm();
        realm.beginTransaction();
              realm.commitTransaction();
    }
*/
}
