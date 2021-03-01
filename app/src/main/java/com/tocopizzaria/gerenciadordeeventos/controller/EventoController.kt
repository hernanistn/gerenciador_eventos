package com.tocopizzaria.gerenciadordeeventos.controller

import android.content.Context
import android.widget.Toast
import com.tocopizzaria.gerenciadordeeventos.DBconfiguration.RealmConfig
import com.tocopizzaria.gerenciadordeeventos.model.Evento
import io.realm.Realm
import io.realm.RealmResults

class EventoController (val context: Context) {

    fun create(evento: Evento){
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        var idAtual = realm.where(Evento::class.java).findAll().size
        realm.commitTransaction()
        realm.close()
        if(idAtual == null){
            evento.id = 1
        }else{
            evento.id = idAtual.inc()
        }
        if(evento.nomeEvento.isNotBlank()){
            realm.beginTransaction()
            realm.insert(evento)
            realm.commitTransaction()
        }else{
            Toast.makeText(context, "Nome do Evento em Branco", Toast.LENGTH_LONG).show()
        }
        realm.close()


    }
    fun getAll(): RealmResults<Evento> {
        RealmConfig(context)
        val realm = Realm.getDefaultInstance()
        var results = realm.where(Evento::class.java).findAll()
        realm.close()
        return results
    }
    fun delete(evento: Evento){
        RealmConfig(context)
        val realm= Realm.getDefaultInstance()
        realm.beginTransaction()
        evento.deleteFromRealm()
        realm.commitTransaction()
        realm.close()
    }


}