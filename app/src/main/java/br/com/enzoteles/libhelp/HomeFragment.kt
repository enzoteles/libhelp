package br.com.enzoteles.libhelp

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.enzoteles.quickhelp.HelpConstant
import br.com.enzoteles.quickhelp.fragment.HelpFragment
import br.com.enzoteles.quickhelp.HelpLog
import kotlinx.android.synthetic.main.home.*

class HomeFragment : HelpFragment(){

    lateinit var detail: DetailFragment

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view = inflater!!.inflate(R.layout.home, container, false)
        return view
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detail = DetailFragment()
        bt_ok.setOnClickListener {
            if (et_login.text.toString().equals("adm") && et_password.text.toString().equals("1234")){
                HelpConstant.manager!!.replaceFragment(R.id.content, detail, "detail", true)
            }
        }


    }
}














