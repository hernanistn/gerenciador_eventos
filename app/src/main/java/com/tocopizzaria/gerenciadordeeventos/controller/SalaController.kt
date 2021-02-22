package com.tocopizzaria.gerenciadordeeventos.controller

import android.content.Context
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.lifecycle.MutableLiveData
import com.tocopizzaria.gerenciadordeeventos.model.Pessoa
import com.tocopizzaria.gerenciadordeeventos.model.Sala
import io.realm.Realm
import io.realm.RealmResults

class SalaController(val context: Context) {



    fun create(sala: Sala){
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        var idAtual = realm.where(Sala::class.java).findAll()
        if(idAtual == null){
            sala.id = 1
        }else{
         sala.id = idAtual.size.inc()
        }

        realm.insert(sala)
        realm.commitTransaction()
        realm.close()
            Toast.makeText(context, sala.id.toString()+" RESPOSTA DO DB", Toast.LENGTH_LONG).show()
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

