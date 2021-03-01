package com.tocopizzaria.gerenciadordeeventos.controller

import android.content.Context
import android.widget.Toast
import com.tocopizzaria.gerenciadordeeventos.model.Evento
import com.tocopizzaria.gerenciadordeeventos.model.Pessoa
import io.realm.Realm
import io.realm.RealmResults

class PessoaController(val context: Context) {

    fun create(pessoa: Pessoa){
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        var idAtual = realm.where(Pessoa::class.java).findAll().size
        realm.commitTransaction()
        realm.close()
        if(idAtual == 0){
            pessoa.id = 1
        }else{
            pessoa.id = idAtual.inc()
        }
        if(pessoa.nome.isNotBlank()){
            realm.beginTransaction()
            realm.insert(pessoa)
            realm.commitTransaction()
        }else{
            Toast.makeText(context, "Nome em branco", Toast.LENGTH_LONG).show()
        }
        realm.close()
    }

    fun getAll(): RealmResults<Pessoa> {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        var results = realm.where(Pessoa::class.java).findAll()
        return results
    }
    fun delete(pessoa: Pessoa){
        Realm.init(context)
    }

    fun update(pessoa: Pessoa){
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.insertOrUpdate(pessoa)
        realm.commitTransaction()
        realm.close()
    }

    fun getById(id: Int): Pessoa {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        return realm.where(Pessoa::class.java).equalTo("id", id).findFirst()!!
    }

    fun insertEvento(pessoa: Pessoa, evento: Evento) {
        Realm.init(context)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        pessoa.eventosParticipante!!.add(evento)
        realm.commitTransaction()
        realm.close()
    }


}