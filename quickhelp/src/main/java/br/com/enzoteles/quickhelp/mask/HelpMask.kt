package br.com.enzoteles.quickhelp.mask

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.icu.text.NumberFormat
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.*

object HelpMask {

    var CPF_MASK = "###.###.###-##"
    var CELULAR_MASK = "(##) #### #####"
    var CEP_MASK = "#####-###"

    /**
     * Método para tirar a máscara do EditText
     * @param s
     * */
    fun unmask(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
                .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "")
                .replace("[)]".toRegex(), "").replace("[R$]".toRegex(), "")
                .replace("[$]".toRegex(), "").replace("[,]".toRegex(), "")
    }

    /**
     * Método para inserir máscara de cpf no EditText
     * @param ediTxt
     * */
    fun insertCpfCnpj(ediTxt: EditText): TextWatcher {
        return object : TextWatcher {
            internal var isUpdating: Boolean = false
            internal var old = ""

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = HelpMask.unmask(s.toString())
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }

                val cpfMask = "###.###.###-##"
                val cnpjMask = "##.###.###/####-##"

                val mask = if (str.length > 11) cnpjMask else cpfMask

                var i = 0
                for (m in mask.toCharArray()) {
                    try {
                        if (m == '#' && TextUtils.isNum(str.get(i)) === false)
                            break
                        if (m == 'L' && TextUtils.isLetter(str.get(i)) === false)
                            break
                    } catch (e: Exception) {
                        break
                    }

                    if (m != '#' && str.length > old.length || m != '#' && str.length < old.length
                            && str.length != i) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += Character.toUpperCase(str.get(i))
                    } catch (e: Exception) {
                        break
                    }

                    i++
                }
                isUpdating = true
                ediTxt.setText(mascara)
                ediTxt.setSelection(mascara.length)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
            }

            override fun afterTextChanged(s: Editable) {}
        }
    }

    /**
     * Método para inserir máscara de data no EditText
     * @param ediTxt
     * */
    fun insertDate(ediTxt: EditText): TextWatcher {
        return object : TextWatcher {
            internal var isUpdating: Boolean = false
            internal var old = ""

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = HelpMask.unmask(s.toString())
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }

                val dateMask = "##/##/####"

                var i = 0
                for (m in dateMask.toCharArray()) {
                    try {
                        if (m == '#' && TextUtils.isNum(str.get(i)) === false)
                            break
                        if (m == 'L' && TextUtils.isLetter(str.get(i)) === false)
                            break
                    } catch (e: Exception) {
                        break
                    }

                    if (m != '#' && str.length > old.length || m != '#' && str.length < old.length
                            && str.length != i) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += Character.toUpperCase(str.get(i))
                    } catch (e: Exception) {
                        break
                    }

                    i++
                }
                isUpdating = true
                ediTxt.setText(mascara)
                ediTxt.setSelection(mascara.length)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
            }

            override fun afterTextChanged(s: Editable) {}
        }
    }
    /**
     * Método para inserir máscara de telefone e celular basta definir o maxleght celular 14 e telefone 13 no EditText
     * @param ediTxt
     * */
    fun insertPhone(ediTxt: EditText): TextWatcher {
        return object : TextWatcher {
            internal var isUpdating: Boolean = false
            internal var old = ""

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = HelpMask.unmask(s.toString())
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }

                val homePhoneMask = "(##)####-####"
                val workPhoneMask = "(##)#####-####"

                val mask = if (str.length > 10) workPhoneMask else homePhoneMask

                var i = 0
                for (m in mask.toCharArray()) {
                    try {
                        if (m == '#' && TextUtils.isNum(str.get(i)) === false)
                            break
                        if (m == 'L' && TextUtils.isLetter(str.get(i)) === false)
                            break
                    } catch (e: Exception) {
                        break
                    }

                    if (m != '#' && str.length > old.length || m != '#' && str.length < old.length
                            && str.length != i) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += Character.toUpperCase(str.get(i))
                    } catch (e: Exception) {
                        break
                    }

                    i++
                }
                isUpdating = true
                ediTxt.setText(mascara)
                ediTxt.setSelection(mascara.length)

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
            }

            override fun afterTextChanged(s: Editable) {}
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
            /**
     * metódo para formatar os valores em valor real
     * @param double
     * return result
     * @author Enzo Teles
     * */
    fun insertMoneyDouble(double: Double): String? {
         var result = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(double)
        return result
    }

    fun insertMoney(ediTxt: EditText): TextWatcher {
        return object : TextWatcher {
            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
                ediTxt.removeTextChangedListener(this)

                val cleanString = s.toString().replace("[R$,.]".toRegex(), "")

                val parsed = java.lang.Double.parseDouble(cleanString)
                /*String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));*/
                val formatted = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(parsed / 100)

                ediTxt.setText(formatted)
                ediTxt.setSelection(formatted.length)
                ediTxt.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
            }

            override fun afterTextChanged(s: Editable) {}
        }
    }

    interface OnZipChangeListener{
        fun onZipTypeDone()
    }

    fun insertZip(ediTxt: EditText, listener: OnZipChangeListener? = null): TextWatcher {
        return object : TextWatcher {
            internal var isUpdating: Boolean = false
            internal var old = ""

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = HelpMask.unmask(s.toString())
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }

                val zipMask = "#####-###"

                var i = 0
                for (m in zipMask.toCharArray()) {
                    try {
                        if (m == '#' && TextUtils.isNum(str.get(i)) === false)
                            break
                        if (m == 'L' && TextUtils.isLetter(str.get(i)) === false)
                            break
                    } catch (e: Exception) {
                        break
                    }
                    if (m != '#' && str.length > old.length || m != '#' && str.length < old.length
                            && str.length != i) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += Character.toUpperCase(str.get(i))
                    } catch (e: Exception) {
                        break
                    }

                    i++
                }
                isUpdating = true
                ediTxt.setText(mascara)
                ediTxt.setSelection(mascara.length)

                if(mascara.length >= 9)
                    listener?.onZipTypeDone()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
            }

            override fun afterTextChanged(s: Editable) {}
        }
    }
}

