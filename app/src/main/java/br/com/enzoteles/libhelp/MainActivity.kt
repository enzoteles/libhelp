package br.com.enzoteles.libhelp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.enzoteles.quickhelp.HelpConstant
import br.com.enzoteles.quickhelp.fragment.HelpManagerFragment

class MainActivity : AppCompatActivity() {

    lateinit var home:HomeFragment
    lateinit var manager: HelpManagerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = HelpManagerFragment(this)
        HelpConstant.manager = manager

        home = HomeFragment()
        manager!!.addFragment(R.id.content, home, "home", false)

    }
}
