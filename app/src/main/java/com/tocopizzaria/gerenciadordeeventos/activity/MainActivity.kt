package com.tocopizzaria.gerenciadordeeventos.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.getbase.floatingactionbutton.FloatingActionButton
import com.getbase.floatingactionbutton.FloatingActionsMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tocopizzaria.gerenciadordeeventos.R
import com.tocopizzaria.gerenciadordeeventos.fragments.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        createFragment(ListarEventos())
        var fab = findViewById<FloatingActionsMenu>(R.id.fab)
        var fab_criar_sala = findViewById<FloatingActionButton>(R.id.fab_criarSala)
        var fab_criar_evento = findViewById<FloatingActionButton>(R.id.fab_adicionarEvento)
        var fab_criar_espaco_cafe = findViewById<FloatingActionButton>(R.id.fab_criarCafe)
        var fab_cadastrar_pessoa = findViewById<FloatingActionButton>(R.id.fab_cadastrar_pessoas)

        fab_criar_evento.setOnClickListener {
            createFragment(CadastrarEvento())
            fab.collapse()
        }

        fab_criar_sala.setOnClickListener {
            createFragment(CadastratSala())
            fab.collapse()
        }
        fab_criar_espaco_cafe.setOnClickListener {
            createFragment(CadastrarSalaCafe())
            fab.collapse()
        }
        findViewById<RelativeLayout>(R.id.activity_main_layout).setOnClickListener {
            fab.collapse()
        }

        fab_cadastrar_pessoa.setOnClickListener {
            fab.collapse()
            createFragment(CadastrarPessoa())
        }



        findViewById<BottomNavigationView>(R.id.activity_main_botton).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_activity_main_eventos -> createFragment(ListarEventos())
                R.id.menu_activity_main_pessoas -> createFragment(ListarPessoas())
                else -> false
            }
        }
    }



    fun createFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.activity_main_host_fragment, fragment).setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).addToBackStack(null).commit()
        return true
    }

}