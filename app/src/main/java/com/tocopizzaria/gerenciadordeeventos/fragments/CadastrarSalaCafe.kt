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
import com.tocopizzaria.gerenciadordeeventos.model.EspacoCafe
import com.tocopizzaria.gerenciadordeeventos.model.Sala
import com.tocopizzaria.gerenciadordeeventos.modelView.EspacoCafeVM
import com.tocopizzaria.gerenciadordeeventos.modelView.SalaMV


class CadastrarSalaCafe : Fragment() {
    private lateinit var edt_nome_sala : EditText
    private lateinit var edt_capacidade_maxima: EditText
    private lateinit var btn_salvar: Button
    private val espacoCafeVM : EspacoCafeVM by viewModels<EspacoCafeVM>() { // variável que permite futuramente utilizar OBSERVER em LiveData
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return EspacoCafeVM(context!!) as T
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
        val view = inflater.inflate(R.layout.fragment_cadastrar_sala_cafe, container, false)
        edt_nome_sala = view.findViewById<EditText>(R.id.fragment_cadastrar_sala_cafe_edt_nome_sala_cafe)
        edt_capacidade_maxima = view.findViewById<EditText>(R.id.fragment_cadastrar_sala_cafe_edt_lotacao_maxima)
        btn_salvar = view.findViewById<Button>(R.id.fragment_cadastrar_sala_cafe_btn_salvar)

        btn_salvar.setOnClickListener {
            var novoEspacoCafeVM = EspacoCafe()
            novoEspacoCafeVM.lotacaoEspacoCafe = edt_capacidade_maxima.text.toString().toFloat()
            novoEspacoCafeVM.nomeEspacoCafe = edt_nome_sala.text.toString()
            espacoCafeVM.insert(novoEspacoCafeVM)
        }
        return view
    }

}