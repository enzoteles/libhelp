package br.com.enzoteles.quickhelp.log

import android.util.Log

/**
 * Created by enzoteles on 14/03/18.
 */
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