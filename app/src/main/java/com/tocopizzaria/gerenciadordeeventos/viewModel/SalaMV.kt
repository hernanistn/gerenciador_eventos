package com.tocopizzaria.gerenciadordeeventos.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tocopizzaria.gerenciadordeeventos.controller.SalaController
import com.tocopizzaria.gerenciadordeeventos.model.Sala
import io.realm.RealmResults

class SalaMV(val context: Context) : ViewModel() {
    private var salasLiveData : MutableLiveData<RealmResults<Sala>>
    private val salaController = SalaController(context)
    init {
            salasLiveData = MutableLiveData()
            salasLiveData.value = salaController.getAll()
    }

    fun getAll(): MutableLiveData<RealmResults<Sala>> {
        return salasLiveData
    }

    fun insert(sala: Sala){
        salaController.create(sala)
        salasLiveData.postValue(salaController.getAll())
    }



}