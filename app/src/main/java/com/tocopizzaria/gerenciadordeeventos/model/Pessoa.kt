package com.tocopizzaria.gerenciadordeeventos.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Pessoa: RealmObject()  {

    @PrimaryKey
    var id : Int = 0
    var nome: String = ""
    var sobrenome: String = ""
}