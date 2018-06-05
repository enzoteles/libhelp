package br.com.enzoteles.libhelp

import android.os.Bundle
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.enzoteles.quickhelp.fragment.HelpFragment
import br.com.enzoteles.quickhelp.mask.HelpMask
import kotlinx.android.synthetic.main.dados_do_cliente.*


class ValidateFragment: HelpFragment(){

    var mCpfTextWatcher: TextWatcher?= null
    var mDataTextWatcher: TextWatcher?= null
    var mCellPhoneTextWatcher: TextWatcher?= null
    var mPhoneTextWatcher: TextWatcher?= null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var view = inflater!!.inflate(R.layout.dados_do_cliente, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputCPF()
        inputDate()
        inputCellPhone()
        inputPhone()
        

    }

    private fun inputPhone() {
        if(mPhoneTextWatcher == null){
            mPhoneTextWatcher = HelpMask.insertPhone(et_telefone)
        } else{
            et_telefone.removeTextChangedListener(mPhoneTextWatcher)
            mPhoneTextWatcher = HelpMask.insertPhone(et_telefone)
        }
        et_telefone.addTextChangedListener(mPhoneTextWatcher)
        et_telefone.inputType = InputType.TYPE_CLASS_PHONE
    }

    private fun inputCellPhone() {

        if(mCellPhoneTextWatcher == null){
            mCellPhoneTextWatcher = HelpMask.insertPhone(et_celular)
        } else{
            et_celular.removeTextChangedListener(mCellPhoneTextWatcher)
            mCellPhoneTextWatcher = HelpMask.insertPhone(et_celular)
        }
        et_celular.addTextChangedListener(mCellPhoneTextWatcher)
        et_celular.inputType = InputType.TYPE_CLASS_NUMBER
    }

    private fun inputDate() {

        if(mDataTextWatcher == null){
            mDataTextWatcher = HelpMask.insertDate(et_nascimento)
        } else{
            et_cpf.removeTextChangedListener(mDataTextWatcher)
            mDataTextWatcher = HelpMask.insertCpfCnpj(et_nascimento)
        }
        et_nascimento.addTextChangedListener(mDataTextWatcher)
        et_nascimento.inputType = InputType.TYPE_CLASS_NUMBER
    }

    private fun inputCPF() {
        if(mCpfTextWatcher == null){
            mCpfTextWatcher = HelpMask.insertCpfCnpj(et_cpf)
        } else{
            et_cpf.removeTextChangedListener(mCpfTextWatcher)
            mCpfTextWatcher = HelpMask.insertCpfCnpj(et_cpf)
        }

        et_cpf.addTextChangedListener(mCpfTextWatcher)
        et_cpf.inputType = InputType.TYPE_CLASS_NUMBER
    }
}