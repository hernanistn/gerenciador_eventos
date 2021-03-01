package com.tocopizzaria.gerenciadordeeventos.dialogBox

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import com.tocopizzaria.gerenciadordeeventos.R
import com.tocopizzaria.gerenciadordeeventos.adapter.SpinnerEventoAdapter
import com.tocopizzaria.gerenciadordeeventos.model.Evento
import com.tocopizzaria.gerenciadordeeventos.model.Pessoa
import com.tocopizzaria.gerenciadordeeventos.viewModel.EventoVM
import com.tocopizzaria.gerenciadordeeventos.viewModel.PessoaVM

class PessoaOptionDialog(context: Context, var pessoa: Pessoa) : Dialog(context), View.OnClickListener {
    private lateinit var spinner_eventos : Spinner
    private lateinit var btn_adicionar : Button
    private lateinit var txt_is_full : TextView
    override fun onClick(v: View?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_pessoa_options)
        spinner_eventos = findViewById(R.id.dialog_pessoa_options_spinner_eventos)
        btn_adicionar = findViewById(R.id.dialog_pessoa_options_btn_adicionar)
        txt_is_full = findViewById(R.id.dialog_pessoa_option_txt_eventoLotado)
        txt_is_full.visibility = View.GONE
        addDataOnSpinner()
        btn_adicionar.setOnClickListener {
                var pessoaVM = PessoaVM(context)
                pessoaVM.insertEvento(pessoa, getSpinnerSelected())
                dismiss()
        }
        spinner_eventos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                verificarSeEventoEstaLotado(getSpinnerSelected())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }


    fun addDataOnSpinner(){
        var eventoVM = EventoVM(context)
        var eventos = eventoVM.getAll()
        var adapter = SpinnerEventoAdapter(context, eventos)
        spinner_eventos.adapter = adapter
    }

    fun getSpinnerEvento(position: Int): Evento {
        var eventoSpinner =spinner_eventos.getItemAtPosition(position) as Evento
        return eventoSpinner
    }

    fun getSpinnerSelected():Evento{
        var eventoSpinnerSelected = spinner_eventos.selectedItem as Evento
        return eventoSpinnerSelected
    }

    fun verificarSeEventoEstaLotado(evento: Evento){
        var capacidadeEvento = 0
        evento.salas.forEach { capacidadeEvento = capacidadeEvento+it.lotacaoMaxima.toInt()}
        if(evento.pessoas!!.size >= capacidadeEvento){
            btn_adicionar.visibility = View.INVISIBLE
            txt_is_full.visibility = View.VISIBLE
        }else{
            btn_adicionar.visibility = View.VISIBLE
            txt_is_full.visibility = View.GONE
        }
    }
}