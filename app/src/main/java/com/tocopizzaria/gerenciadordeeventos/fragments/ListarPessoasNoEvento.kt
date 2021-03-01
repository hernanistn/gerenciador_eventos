package com.tocopizzaria.gerenciadordeeventos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tocopizzaria.gerenciadordeeventos.R
import com.tocopizzaria.gerenciadordeeventos.adapter.ListarCafeDoEventoAdapter
import com.tocopizzaria.gerenciadordeeventos.adapter.ListarSalasDoEventoAdapter
import com.tocopizzaria.gerenciadordeeventos.model.Evento


class ListarPessoasNoEvento(val evento: Evento) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_listar_pessoas_no_evento, container, false)
        var recyleViewSalas = view.findViewById<RecyclerView>(R.id.fragment_listar_pessoas_no_evento_recycle_view_salas)
        var recycleViewCafes = view.findViewById<RecyclerView>(R.id.fragment_listar_pessoas_no_evento_recycle_view_cafes)
        var adapterSalas = ListarSalasDoEventoAdapter(evento)
        recycleViewCafes.layoutManager = LinearLayoutManager(context)
        recyleViewSalas.layoutManager = LinearLayoutManager(context)
        recyleViewSalas.adapter = adapterSalas
        var adapterCafes = ListarCafeDoEventoAdapter(evento)
        recycleViewCafes.adapter = adapterCafes
        return view
    }

}