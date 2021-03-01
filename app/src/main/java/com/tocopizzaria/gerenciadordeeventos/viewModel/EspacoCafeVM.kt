package com.tocopizzaria.gerenciadordeeventos.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tocopizzaria.gerenciadordeeventos.controller.EspacoCafeController
import com.tocopizzaria.gerenciadordeeventos.model.EspacoCafe
import io.realm.RealmResults

class EspacoCafeVM(val context: Context) : ViewModel() {
    private var salasLiveData : MutableLiveData<RealmResults<EspacoCafe>>
    private val espacoCafeController = EspacoCafeController(context)
    init {
        salasLiveData = MutableLiveData()
        salasLiveData.value = espacoCafeController.getAll()
    }

    fun getAll(): MutableLiveData<RealmResults<EspacoCafe>> {
        return salasLiveData
    }
    fun insert(espacoCafe: EspacoCafe){
        espacoCafeController.create(espacoCafe)
    }

}