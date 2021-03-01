package com.tocopizzaria.gerenciadordeeventos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tocopizzaria.gerenciadordeeventos.R
import com.tocopizzaria.gerenciadordeeventos.model.Evento

class ListarCafeDoEventoAdapter(val evento: Evento) : RecyclerView.Adapter<ListarCafeDoEventoAdapter.ViewHolder>() {
    inner class ViewHolder constructor(val view: View): RecyclerView.ViewHolder(view){
        var nome_sala_cafe = view.findViewById<TextView>(R.id.list_cafes_do_evento_nome_sala_cafe)
        var nome_pessoas = view.findViewById<TextView>(R.id.list_cafes_do_evento_nome_integrantes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var lista = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_cafes_do_evento, parent, false)
        return ViewHolder(lista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.nome_sala_cafe.text = evento.espacoCafe.get(position)!!.nomeEspacoCafe
        evento.pessoas!!.forEach { holder.nome_pessoas.text = holder.nome_pessoas.text.toString()+it.nome+" "+it.sobrenome+"\n" }
    }

    override fun getItemCount(): Int {
        return evento.espacoCafe.size
    }
}