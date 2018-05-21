package br.com.enzoteles.quickhelp

import br.com.enzoteles.quickhelp.fragment.HelpManagerFragment
import android.text.TextUtils
import java.math.BigInteger
import java.nio.charset.Charset
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and
import android.R.id.edit
import android.content.ContentValues.TAG
import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.spec.InvalidKeySpecException
import javax.crypto.*
import javax.crypto.spec.DESKeySpec

/**
 * Created by enzoteles on 14/03/18.
 */
object HelpConstant{

    var manager: HelpManagerFragment?= null
    var cryptoPass:String = "h3lp3rl1b"
    var passWord: String?= null

    /**
     * Método para criptografar uma String
     * @params valeu
     * @return value
     * */
    fun encrypt(value: String): String {
        try {
            val charset = Charsets.UTF_8
            val keySpec = DESKeySpec(cryptoPass.toByteArray(charset))
            val keyFactory = SecretKeyFactory.getInstance("DES")
            val key = keyFactory.generateSecret(keySpec)

            val clearText = value.toByteArray(charset("UTF8"))
            // Cipher is not thread safe
            val cipher = Cipher.getInstance("DES")
            cipher.init(Cipher.ENCRYPT_MODE, key)

            val encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT)
            return encrypedValue

        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: InvalidKeySpecException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        }

        return value
    };

    /**
     * Método para descriptografar uma String
     * @params valeu
     * @return value
     * */
    fun decrypt(value: String): String {
        try {

            val charset = Charsets.UTF_8
            val keySpec = DESKeySpec(cryptoPass.toByteArray(charset))
            val keyFactory = SecretKeyFactory.getInstance("DES")
            val key = keyFactory.generateSecret(keySpec)

            val encrypedPwdBytes = Base64.decode(value, Base64.DEFAULT)
            // cipher is not thread safe
            val cipher = Cipher.getInstance("DES")
            cipher.init(Cipher.DECRYPT_MODE, key)
            val decrypedValueBytes = cipher.doFinal(encrypedPwdBytes)

            val decrypedValue = String(decrypedValueBytes)
            return decrypedValue

        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: InvalidKeySpecException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        }

        return value
    }
}