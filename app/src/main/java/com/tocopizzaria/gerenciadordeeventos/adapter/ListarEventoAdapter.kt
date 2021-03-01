package com.tocopizzaria.gerenciadordeeventos.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tocopizzaria.gerenciadordeeventos.R
import com.tocopizzaria.gerenciadordeeventos.fragments.ListarPessoasNoEvento
import com.tocopizzaria.gerenciadordeeventos.model.Evento
import com.tocopizzaria.gerenciadordeeventos.viewModel.EventoVM
import io.realm.RealmResults

class ListarEventoAdapter(val listaEventos: MutableLiveData<RealmResults<Evento>>, val context: Context, val fragment: Fragment) : RecyclerView.Adapter<ListarEventoAdapter.ViewHolder>() {
    inner class ViewHolder constructor(val view: View): RecyclerView.ViewHolder(view){
        var txt_nome_evento = view.findViewById<TextView>(R.id.list_evento_nome_evento)
        var txt_capacidade_atual = view.findViewById<TextView>(R.id.list_evento_capacidade_atual)
        var txt_capacidade_maxima = view.findViewById<TextView>(R.id.list_evento_capacidade_maxima)
        var main_linear_layout = view.findViewById<LinearLayout>(R.id.list_evento_main_linear_layout)
        var card_view = view.findViewById<CardView>(R.id.list_eventos_card_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var lista = LayoutInflater.from(parent.context).inflate(R.layout.list_eventos, parent, false )
        return ViewHolder(lista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.txt_nome_evento.text = listaEventos.value!!.get(position)?.nomeEvento
        holder.txt_capacidade_atual.text = listaEventos!!.value!!.get(position)!!.pessoas!!.size.toString()
        listaEventos.value!!.get(position)?.salas?.forEach { holder.txt_capacidade_maxima.text = (holder.txt_capacidade_maxima.text.toString().toInt()+it.lotacaoMaxima.toInt()).toString() }
        holder.main_linear_layout.setOnLongClickListener {
            callDialogOptionsEvento(listaEventos.value!!.get(position)!!)
            return@setOnLongClickListener true
        }
        holder.card_view.setOnClickListener {
            var evento = ListarPessoasNoEvento(listaEventos.value!!.get(position)!!)
            fragment.requireActivity().supportFragmentManager.beginTransaction().replace(fragment.id, evento).addToBackStack(fragment.toString()).commit()
        }
    }

    override fun getItemCount(): Int {
     return listaEventos.value!!.size
    }

    private fun callDialogOptionsEvento(evento: Evento){
        var alertDialog: AlertDialog = this.context!!.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("Opções do Evento")
                    .setPositiveButton("Deletar", DialogInterface.OnClickListener { dialog, which ->
                        var eventoVM = EventoVM(this.context)
                        eventoVM.delete(evento)
                        notifyDataSetChanged()
                    })
            }
            builder.create()
        }
        alertDialog.show()
    }

}