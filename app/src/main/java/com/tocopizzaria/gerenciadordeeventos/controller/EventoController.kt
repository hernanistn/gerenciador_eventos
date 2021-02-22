package com.tocopizzaria.gerenciadordeeventos.controller

import android.content.Context
import android.widget.Toast
import com.tocopizzaria.gerenciadordeeventos.configuration.RealmConfig
import com.tocopizzaria.gerenciadordeeventos.model.Evento
import com.tocopizzaria.gerenciadordeeventos.model.Sala
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.kotlin.delete
import io.realm.kotlin.deleteFromRealm
import io.realm.kotlin.isLoaded

class EventoController (val context: Context) {

    fun create(evento: Evento){
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        var idAtual = realm.where(Evento::class.java).findAll()
        if(idAtual == null){
            evento.id = 1
        }else{
            evento.id = idAtual.size.inc()
        }
        realm.insert(evento)
        realm.commitTransaction()
        realm.close()
        Toast.makeText(context, evento.id.toString()+" RESPOSTA DO DB", Toast.LENGTH_LONG).show()

    }
    fun getAll(): RealmResults<Evento> {
        RealmConfig(context)
        val realm = Realm.getDefaultInstance()
        var results = realm.where(Evento::class.java).findAll()
        realm.close()
        return results
    }
    fun delete(evento: Evento){
        //Realm.init(context)
        RealmConfig(context)
        val realm= Realm.getDefaultInstance()
        realm.beginTransaction()
        evento.deleteFromRealm()
        realm.commitTransaction()
        realm.close()
    }

}