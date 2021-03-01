package com.tocopizzaria.gerenciadordeeventos.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tocopizzaria.gerenciadordeeventos.controller.PessoaController
import com.tocopizzaria.gerenciadordeeventos.model.Evento
import com.tocopizzaria.gerenciadordeeventos.model.Pessoa
import io.realm.RealmResults

class PessoaVM(val context: Context) : ViewModel(){
    private var pessoaLiveData : MutableLiveData<RealmResults<Pessoa>>
    private val pessoaController = PessoaController(context)
    init {
        pessoaLiveData = MutableLiveData()
        pessoaLiveData.value = pessoaController.getAll()
    }

    fun getAll(): MutableLiveData<RealmResults<Pessoa>> {
        return pessoaLiveData
    }
    fun insert(pessoa: Pessoa){
        pessoaController.create(pessoa)
        pessoaLiveData.postValue(pessoaController.getAll())
    }

    fun update(pessoa: Pessoa){
        pessoaController.update(pessoa)
        pessoaLiveData.postValue(pessoaController.getAll())
    }
    fun getById(id: Int): Pessoa{
        return  pessoaController.getById(id)
    }
    fun insertEvento(pessoa: Pessoa, evento: Evento){
        pessoaController.insertEvento(pessoa, evento)
    }

}