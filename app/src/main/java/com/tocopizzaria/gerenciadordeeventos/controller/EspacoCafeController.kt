package com.tocopizzaria.gerenciadordeeventos.controller

import android.content.Context
import android.widget.Toast
import com.tocopizzaria.gerenciadordeeventos.model.EspacoCafe
import com.tocopizzaria.gerenciadordeeventos.model.Evento
import io.realm.Realm
import io.realm.RealmResults

class EspacoCafeController(val context: Context) {
    fun create(espacoCafe: EspacoCafe){
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        var idAtual = realm.where(EspacoCafe::class.java).findAll()
        if(idAtual == null){
            espacoCafe.id = 1
        }else{
            espacoCafe.id = idAtual.size.inc()
        }
        realm.insert(espacoCafe)
        realm.commitTransaction()
        realm.close()
        Toast.makeText(context, espacoCafe.id.toString()+" RESPOSTA DO DB", Toast.LENGTH_LONG).show()

    }
    fun getAll(): RealmResults<EspacoCafe> {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        var results = realm.where(EspacoCafe::class.java).findAll()
        return results
    }
    fun delete(evento: Evento){
        Realm.init(context)
        Realm.Transaction {
            it.delete(evento.javaClass)
            it.close()
        }
    }


}