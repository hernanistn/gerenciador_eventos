package com.tocopizzaria.gerenciadordeeventos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tocopizzaria.gerenciadordeeventos.R
import com.tocopizzaria.gerenciadordeeventos.model.EspacoCafe
import io.realm.RealmResults

class ListarEspacoCafeAdapter(val listaEspacoCafe: MutableLiveData<RealmResults<EspacoCafe>>): RecyclerView.Adapter<ListarEspacoCafeAdapter.ViewHolder>() {
    var cheboxSelecionados = ArrayList<EspacoCafe>()
    inner class ViewHolder constructor(val view: View): RecyclerView.ViewHolder(view){
        var checkbox = view.findViewById<CheckBox>(R.id.list_espaco_cafe_criar_evento_check_box)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var lista = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_espaco_cafe_criar_evento, parent, false)
        return ViewHolder(lista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkbox.text = listaEspacoCafe.value?.get(position)?.nomeEspacoCafe+"- Capacidade: "+ listaEspacoCafe.value?.get(position)?.lotacaoEspacoCafe?.toInt()
        holder.checkbox.setOnClickListener {
            if (holder.checkbox.isChecked) {
                cheboxSelecionados.add(listaEspacoCafe.value!!.get(position)!!)
            } else {
                cheboxSelecionados.remove(listaEspacoCafe.value!!.get(position)!!)
            }
        }
        getCheckBoxSelecionados().forEach { //persiste que o checkBox esteja selecionado enquanto rola a lista do recycleView
            if(holder.checkbox.text.equals(it.nomeEspacoCafe)){
                holder.checkbox.isChecked = true
            }else{
                holder.checkbox.isChecked = false
            }
        }
    }

    override fun getItemCount(): Int {
        return listaEspacoCafe.value!!.size
    }
    public fun getCheckBoxSelecionados(): ArrayList<EspacoCafe>{
        return this.cheboxSelecionados
    }
}