package com.tocopizzaria.gerenciadordeeventos.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass


@RealmClass
open class Evento : RealmObject() {
    @PrimaryKey
    var id : Int = 0
    var nomeEvento: String = ""
    var salas: RealmList<Sala> = RealmList()
    var espacoCafe: RealmList<EspacoCafe> = RealmList()
    @LinkingObjects("eventosParticipante")
    final val pessoas: RealmResults<Pessoa>? = null
}