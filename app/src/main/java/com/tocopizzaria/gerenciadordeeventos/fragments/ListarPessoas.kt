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
import com.tocopizzaria.gerenciadordeeventos.adapter.ListarPessoasAdapter
import com.tocopizzaria.gerenciadordeeventos.viewModel.PessoaVM

class ListarPessoas : Fragment() {
    private val pessoaVM : PessoaVM by viewModels<PessoaVM>() {
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PessoaVM(context!!) as T
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_listar_pessoas, container, false)
        var recyclerView = view.findViewById<RecyclerView>(R.id.fragment_listar_pessoas_recycle_view)
        pessoaVM.getAll().observe(viewLifecycleOwner, Observer {
            var adapter = ListarPessoasAdapter(pessoaVM.getAll(), requireContext())
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter =adapter
            adapter.notifyDataSetChanged()
        })
        return view
    }
}
