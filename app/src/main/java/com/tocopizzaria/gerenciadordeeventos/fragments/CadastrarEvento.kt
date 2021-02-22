package com.tocopizzaria.gerenciadordeeventos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tocopizzaria.gerenciadordeeventos.R
import com.tocopizzaria.gerenciadordeeventos.adapter.ListarEspacoCafeAdapter
import com.tocopizzaria.gerenciadordeeventos.adapter.ListarSalaAdapter
import com.tocopizzaria.gerenciadordeeventos.adapter.ListarSalaCriarEventoAdapter
import com.tocopizzaria.gerenciadordeeventos.model.Evento
import com.tocopizzaria.gerenciadordeeventos.modelView.EspacoCafeVM
import com.tocopizzaria.gerenciadordeeventos.modelView.EventoVM
import com.tocopizzaria.gerenciadordeeventos.modelView.SalaMV

class CadastrarEvento : Fragment() {
    private lateinit var edt_nome_evento : EditText
    private lateinit var btn_salvar_evento: Button

    val salaVM : SalaMV by viewModels<SalaMV>() {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SalaMV(requireContext()) as T
            }
        }
    }
    val eventoVM : EventoVM by viewModels<EventoVM>() {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return EventoVM(requireContext()) as T
            }
        }
    }
    val espacoCafeVM : EspacoCafeVM by viewModels<EspacoCafeVM>() {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return EspacoCafeVM(requireContext()) as T
            }
        }
    }
    private lateinit var adapterSala : ListarSalaCriarEventoAdapter
    private lateinit var adapterEspacoCafe : ListarEspacoCafeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_cadastrar_evento, container, false)
        initRecycleViews(view)
        return view



    }
    private fun initRecycleViews(view: View){
        var recyclerViewSala = view.findViewById<RecyclerView>(R.id.fragment_cadastrar_evento_recycle_view_lista_salas)
        recyclerViewSala.layoutManager = LinearLayoutManager(context)
        adapterSala = ListarSalaCriarEventoAdapter(salaVM.getAll())
        recyclerViewSala.adapter = adapterSala

        var recyclerViewEspacoCafe = view.findViewById<RecyclerView>(R.id.fragment_cadastrar_evento_recycle_view_lista_cafe)
        recyclerViewEspacoCafe.layoutManager = LinearLayoutManager(context)
        adapterEspacoCafe = ListarEspacoCafeAdapter(espacoCafeVM.getAll())
        recyclerViewEspacoCafe.adapter = adapterEspacoCafe

        edt_nome_evento = view.findViewById(R.id.fragment_cadastrar_evento_edt_nome_evento)
        btn_salvar_evento = view.findViewById(R.id.fragment_cadastrar_evento_btn_salvar_evento)
        btn_salvar_evento.setOnClickListener {
            if(verificarSeEspacoDeCafeTemCapacidadeParaOEvento()){
                var novoEvento = Evento()
                novoEvento.id = 0
                novoEvento.nomeEvento = edt_nome_evento.text.toString()
                novoEvento.salas.addAll(adapterSala.cheboxSelecionados)
                novoEvento.espacoCafe.addAll(adapterEspacoCafe.cheboxSelecionados)
                eventoVM.insert(novoEvento)
            }else{
                Toast.makeText(context, "A capacidade da sala de café é inferior a capacidade geral do evento!", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun verificarSeEspacoDeCafeTemCapacidadeParaOEvento():Boolean{ //verifica se o espaço de café capacita a quantidade máxima das salas do evento
        var capacidadeMaxSala = 0
        var capacidadeMaxSalaCafe = 0
        adapterSala.getCheckBoxSelecionados().forEach { capacidadeMaxSala = capacidadeMaxSala+(it.lotacaoMaxima.toInt()) }
        adapterEspacoCafe.getCheckBoxSelecionados().forEach { capacidadeMaxSalaCafe = capacidadeMaxSalaCafe+(it.lotacaoEspacoCafe.toInt()) }
        if(capacidadeMaxSalaCafe<capacidadeMaxSala){
            return false
        }else{
            return true
        }
    }

}