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
import com.tocopizzaria.gerenciadordeeventos.model.Pessoa
import com.tocopizzaria.gerenciadordeeventos.viewModel.PessoaVM
import com.tocopizzaria.gerenciadordeeventos.viewModel.SalaMV

class CadastrarPessoa : Fragment() {
    private lateinit var edt_nome_pessoa : EditText
    private lateinit var edt_sobre_nome_pessoa: EditText
    private lateinit var btn_cadastrar_pessoa: Button
    val pessoaVM : PessoaVM by viewModels<PessoaVM>() {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PessoaVM(requireContext()) as T
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
        var view = inflater.inflate(R.layout.fragment_cadastrar_pessoa, container, false)
        edt_nome_pessoa = view.findViewById<EditText>(R.id.fragment_cadastraR_pessoa_edt_nome_pessoa)
        edt_sobre_nome_pessoa= view.findViewById<EditText>(R.id.fragment_cadastraR_pessoa_edt_sobrenome_pessoa)
        btn_cadastrar_pessoa= view.findViewById<Button>(R.id.fragment_cadastrar_pessoa_btn_cadastrar)

        btn_cadastrar_pessoa.setOnClickListener {
            var pessoa: Pessoa = Pessoa()
            pessoa.nome = edt_nome_pessoa.text.toString()
            pessoa.sobrenome = edt_sobre_nome_pessoa.text.toString()
            pessoaVM.insert(pessoa)
        }

        return view
    }


}