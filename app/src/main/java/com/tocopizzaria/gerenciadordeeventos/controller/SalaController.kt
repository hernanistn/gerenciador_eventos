package com.tocopizzaria.gerenciadordeeventos.controller

import android.content.Context
import android.widget.Toast
import com.tocopizzaria.gerenciadordeeventos.model.Sala
import io.realm.Realm
import io.realm.RealmResults

class SalaController(val context: Context) {



    fun create(sala: Sala){
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        var idAtual = realm.where(Sala::class.java).findAll()
        realm.commitTransaction()
        realm.close()
        if(idAtual == null){
            sala.id = 1
        }else{
         sala.id = idAtual.size.inc()
        }
        if(sala.nome.isNotBlank()){
            realm.beginTransaction()
            realm.insert(sala)
            realm.commitTransaction()
        }else{
            Toast.makeText(context, "Nome em branco!", Toast.LENGTH_LONG).show()
        }
        realm.close()

    }
    fun getAll(): RealmResults<Sala> {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        var results = realm.where(Sala::class.java).findAll()
        return results
    }
    fun delete(sala: Sala){
        Realm.init(context)
        Realm.Transaction {
            it.delete(sala.javaClass)
            it.close()
        }
    }
}

