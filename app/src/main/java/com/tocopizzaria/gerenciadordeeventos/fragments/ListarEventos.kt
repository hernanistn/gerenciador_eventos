package com.tocopizzaria.gerenciadordeeventos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tocopizzaria.gerenciadordeeventos.R
import com.tocopizzaria.gerenciadordeeventos.adapter.ListarEventoAdapter
import com.tocopizzaria.gerenciadordeeventos.viewModel.EventoVM


class ListarEventos : Fragment() {
    private val eventoVM : EventoVM by viewModels<EventoVM>() {
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return EventoVM(context!!) as T
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_listar_eventos, container, false)
        var recyclerView = view.findViewById<RecyclerView>(R.id.fragment_listar_eventos_recycle_view)

        eventoVM.getAll().observe(viewLifecycleOwner, Observer {
            var adapter = ListarEventoAdapter(eventoVM.getAll(), requireContext(), this)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter =adapter
            adapter.notifyDataSetChanged()
        })
        return view
    }

}