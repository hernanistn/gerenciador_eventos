package com.tocopizzaria.gerenciadordeeventos.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Sala() : RealmObject() {

    @PrimaryKey
    var id: Int = 0
    var nome: String = ""
    var lotacaoMaxima: Float = 0F
    var pessoas: RealmList<Pessoa> = RealmList() //relação one-to-many

}