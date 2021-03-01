package com.tocopizzaria.gerenciadordeeventos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tocopizzaria.gerenciadordeeventos.R
import com.tocopizzaria.gerenciadordeeventos.model.Sala
import com.tocopizzaria.gerenciadordeeventos.viewModel.SalaMV

class CadastratSala : Fragment() {
    private lateinit var edt_nome_sala : EditText
    private lateinit var edt_capacidade_maxima: EditText
    private lateinit var btn_salvar: Button
    private val salaVM : SalaMV by viewModels<SalaMV>() { // variável que permite futuramente utilizar OBSERVER em LiveData
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SalaMV(context!!) as T
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
        val view = inflater.inflate(R.layout.fragment_cadastrar_sala, container, false)
        edt_nome_sala = view.findViewById<EditText>(R.id.fragment_cadastraR_sala_edt_nome_sala)
        edt_capacidade_maxima = view.findViewById<EditText>(R.id.fragment_cadastraR_sala_edt_lotacao_maxima)
        btn_salvar = view.findViewById<Button>(R.id.fragment_cadastraR_sala_btn_salvar)

        btn_salvar.setOnClickListener {
            var novaSala = Sala()
            novaSala.id = 0
            novaSala.lotacaoMaxima = edt_capacidade_maxima.text.toString().toFloat()
            novaSala.nome = edt_nome_sala.text.toString()
            salaVM.insert(novaSala)
        }
        return view
    }


}