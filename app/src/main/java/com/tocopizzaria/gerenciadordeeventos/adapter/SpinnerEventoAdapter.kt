package com.tocopizzaria.gerenciadordeeventos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.tocopizzaria.gerenciadordeeventos.R
import com.tocopizzaria.gerenciadordeeventos.model.Evento
import io.realm.RealmResults

class SpinnerEventoAdapter internal constructor(internal val context: Context, internal var list: MutableLiveData<RealmResults<Evento>>): BaseAdapter(){
    override fun getCount(): Int {
        return  list.value!!.size
    }

    override fun getItem(position: Int): Evento {
        return list.value!!.get(position)!!
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if(view == null){
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.spinner_listar_eventos, parent, false)
        }
        var nome_evento = view!!.findViewById<TextView>(R.id.spinner_eventos_txt)
        var capacidadeMax : Int = 0
        list.value!!.get(position)!!.salas.forEach { capacidadeMax = capacidadeMax+it.lotacaoMaxima.toInt() }
        nome_evento.text = list.value!!.get(position)!!.nomeEvento+" Capacidade: "+ list!!.value!!.get(position)!!.pessoas!!.size+"/"+capacidadeMax
        return nome_evento
    }
}