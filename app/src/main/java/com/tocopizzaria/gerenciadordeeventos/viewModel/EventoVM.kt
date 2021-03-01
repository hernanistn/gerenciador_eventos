package com.tocopizzaria.gerenciadordeeventos.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tocopizzaria.gerenciadordeeventos.controller.EventoController
import com.tocopizzaria.gerenciadordeeventos.model.Evento
import io.realm.RealmResults


class EventoVM(val context: Context): ViewModel() {
    private var salasLiveData : MutableLiveData<RealmResults<Evento>>
    private val eventoController = EventoController(context)
    init {
        salasLiveData = MutableLiveData()
       salasLiveData.value = eventoController.getAll()
    }

    fun getAll(): MutableLiveData<RealmResults<Evento>> {
        return salasLiveData
    }
    fun insert(evento: Evento){
        eventoController.create(evento)
    }

    fun delete(evento: Evento) {
        eventoController.delete(evento)
        salasLiveData.postValue(eventoController.getAll())
    }
}