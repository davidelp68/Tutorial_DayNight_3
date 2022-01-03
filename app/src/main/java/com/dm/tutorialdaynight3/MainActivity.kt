package com.dm.tutorialdaynight3

import android.content.Context
import android.content.SharedPreferences            //Importazione della Classe SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate     //Importazione della Classe AppCompactDelegate

class MainActivity : AppCompatActivity() {

    private var rbDay: RadioButton? = null
    private var rbNight: RadioButton? = null
    private var rbDefault: RadioButton? = null
    private var txtMode: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rbDay = findViewById(R.id.rB_day)
        rbNight = findViewById(R.id.rB_night)
        rbDefault = findViewById(R.id.rB_default)
        txtMode = findViewById(R.id.tV_mode)

        val appSettingPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs", Context.MODE_PRIVATE)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
        val isNightMode = appSettingPrefs.getInt("NightMode", -1)
        /*MODE_NIGHT_FOLLOW_SYSTEM = -1
        * MODE_NIGHT_AUTO = 0 (deprecated)
        * MODE_NIGHT_NO = 1
        * MODE_NIGHT_YES = 2
        * MODE_NIGHT_AUTO_BATTERY = 3*/

        when (isNightMode) {
            -1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                rbDefault!!.isChecked = true
                txtMode!!.text = "Modalità: Predefinito da sistema attiva"
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                rbDay!!.isChecked = true
                txtMode!!.text = "Modalità: Day attiva"
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                rbNight!!.isChecked = true
                txtMode!!.text = "Modalità: Night attiva"
            }
            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                rbDay!!.isChecked = true
                txtMode!!.text = "Modalità: Day attiva"
            }
        }



        //gestione dei clic sui vari RadioButton
        rbDay!!.setOnClickListener {
            //Imposta il tema chiaro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            val mode = AppCompatDelegate.getDefaultNightMode()
            sharedPrefsEdit.putInt("NightMode", mode)
            sharedPrefsEdit.apply()
        }

        rbNight!!.setOnClickListener {
            //Imposta il tema scuro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            val mode = AppCompatDelegate.getDefaultNightMode()
            sharedPrefsEdit.putInt("NightMode", mode)
            sharedPrefsEdit.apply()
        }

        rbDefault!!.setOnClickListener {
            //Imposta il tema in base all'impostazione di sistema
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            val mode = AppCompatDelegate.getDefaultNightMode()
            sharedPrefsEdit.putInt("NightMode", mode)
            sharedPrefsEdit.apply()
        }

    }

}