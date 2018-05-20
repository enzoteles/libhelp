package br.com.enzoteles.quickhelp

import android.util.Log

object HelpLog{

    const val TAG: String = "HELP"

    fun info(msg:String){
        Log.i(TAG, msg)
    }

    fun error(msg:String){
        Log.e(TAG, msg)
    }

    fun debug(msg:String){
        Log.d(TAG, msg)
    }

    fun verbose(msg:String){
        Log.v(TAG, msg)
    }
}