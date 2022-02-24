package com.example.chatapplication.services

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
import java.io.IOException
import java.lang.RuntimeException
import java.security.GeneralSecurityException

class Session(context: Context) {

    private val context:Context = context;

    private lateinit var editor:SharedPreferences.Editor;
    private lateinit var pref:SharedPreferences;

    companion object{
        var tempHolder = "";
    }

    init {
        try {
            if (android.os.Build.VERSION.SDK_INT>= android.os.Build.VERSION_CODES.M){
                var masterKey: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
                pref = EncryptedSharedPreferences.create("appSession",masterKey,context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
                editor = pref.edit();
            }
        }catch (e:GeneralSecurityException){
            throw RuntimeException(e)
        }catch (e:IOException){
            throw RuntimeException(e)
        }

    }
}