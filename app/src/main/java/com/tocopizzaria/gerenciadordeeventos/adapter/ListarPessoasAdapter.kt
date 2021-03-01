package com.tocopizzaria.gerenciadordeeventos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tocopizzaria.gerenciadordeeventos.R
import com.tocopizzaria.gerenciadordeeventos.dialogBox.PessoaOptionDialog
import com.tocopizzaria.gerenciadordeeventos.model.Pessoa
import io.realm.RealmResults

class ListarPessoasAdapter(val listarPessoas: MutableLiveData<RealmResults<Pessoa>>, val context: Context) : RecyclerView.Adapter<ListarPessoasAdapter.ViewHolder>() {
    inner class ViewHolder constructor(val view: View): RecyclerView.ViewHolder(view){
        var nome_pessoa = view.findViewById<TextView>(R.id.list_listar_pessoa_nome)
        var sobre_nome_pessoa = view.findViewById<TextView>(R.id.list_listar_pessoa_sobre_nome)
        var evento_participante = view.findViewById<TextView>(R.id.list_listar_pessoa_eventos_que_participa)
        var btn_opcoes = view.findViewById<ImageButton>(R.id.list_listar_pessoa_btn_edt)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListarPessoasAdapter.ViewHolder {
        var lista = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_listar_pessoa, parent, false)
        return ViewHolder(lista)
    }

    override fun onBindViewHolder(holder: ListarPessoasAdapter.ViewHolder, position: Int) {
        holder.nome_pessoa.setText(listarPessoas.value!!.get(position)!!.nome)
        holder.sobre_nome_pessoa.setText(listarPessoas!!.value!!.get(position)!!.sobrenome)
        listarPessoas!!.value!!.get(position)!!.eventosParticipante!!.forEach { holder.evento_participante.setText(holder.evento_participante.text.toString()+it.nomeEvento+", ")
        }
        holder.btn_opcoes.setOnClickListener {
            listarPessoas.value!!.get(position)?.let { it1 -> callDialogPessoaOptions(it1) }
        }
    }

    override fun getItemCount(): Int {
        return listarPessoas.value!!.size
    }
    fun callDialogPessoaOptions(pessoa: Pessoa){ //abre um dialogo onde escolhe o evento que a Pessoa pretende participar
       var dialog =  PessoaOptionDialog(context, pessoa)
        dialog.show()
        dialog.setOnDismissListener {
            this.notifyDataSetChanged() //quando o dialogo é fechado, atualiza os eventos que a pessoa participa
        }

    }
}