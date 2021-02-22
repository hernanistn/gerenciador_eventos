package com.tocopizzaria.gerenciadordeeventos.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class EspacoCafe: RealmObject()  {

    @PrimaryKey
    var id: Int = 0
    var nomeEspacoCafe: String = ""
    var lotacaoEspacoCafe: Float = 0F
    var sala: Sala? = null
}