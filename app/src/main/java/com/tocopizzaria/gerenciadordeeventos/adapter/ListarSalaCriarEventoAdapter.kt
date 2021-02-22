package com.tocopizzaria.gerenciadordeeventos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tocopizzaria.gerenciadordeeventos.R
import com.tocopizzaria.gerenciadordeeventos.model.Sala
import io.realm.RealmResults

class ListarSalaCriarEventoAdapter(var listaSalas: MutableLiveData<RealmResults<Sala>>): RecyclerView.Adapter<ListarSalaCriarEventoAdapter.ViewHolder>() {
    var cheboxSelecionados = ArrayList<Sala>()
    inner class ViewHolder constructor(val view: View) : RecyclerView.ViewHolder(view) {
        var checkbox = view.findViewById<CheckBox>(R.id.list_listar_salas_criar_evento_check_box)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var lista = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_listar_salas_criar_evento, parent, false)
        return ViewHolder(lista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkbox.text = listaSalas.value?.get(position)?.nome+" -Capacidade: "+ listaSalas.value?.get(position)?.lotacaoMaxima?.toInt()
        holder.checkbox.setOnClickListener {
            if (holder.checkbox.isChecked) {
                cheboxSelecionados.add(listaSalas.value!!.get(position)!!)
            } else {
                cheboxSelecionados.remove(listaSalas.value!!.get(position)!!)
            }
        }
        getCheckBoxSelecionados().forEach { //persiste que o checkBox esteja selecionado enquanto rola a lista do recycleView
            if(holder.checkbox.text.equals(it.nome)){
                    holder.checkbox.isChecked = true
            }else{
                holder.checkbox.isChecked = false
            }
        }
    }
    override fun getItemCount(): Int {
        return  listaSalas.value!!.size
    }

    public fun getCheckBoxSelecionados(): ArrayList<Sala>{
        return this.cheboxSelecionados
    }
}