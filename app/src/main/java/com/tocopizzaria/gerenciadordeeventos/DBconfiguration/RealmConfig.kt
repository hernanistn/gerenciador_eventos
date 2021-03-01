package com.tocopizzaria.gerenciadordeeventos.DBconfiguration

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmConfig(val context: Context) : Application(){
    init {
        onCreate()
    }

    override fun onCreate() {
        Realm.init(this.context)
        super.onCreate()
        var config = RealmConfiguration.Builder().
        name(Realm.DEFAULT_REALM_NAME).
        schemaVersion(0).allowWritesOnUiThread(true).
        deleteRealmIfMigrationNeeded().
        build()

        Realm.setDefaultConfiguration(config)
        Realm.getInstance(config)
    }

}