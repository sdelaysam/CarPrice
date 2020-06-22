package org.sdelaysam.carprice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import org.sdelaysam.carprice.util.navigation.HasNavController
import org.sdelaysam.carprice.util.ui.NoToolbar
import org.sdelaysam.carprice.util.ui.RxActivity

class MainActivity : RxActivity(), HasNavController {

    override val navController: NavController
        get() = findNavController(R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupWithNavController(toolbar, navController)
        supportFragmentManager.registerFragmentLifecycleCallbacks(object: FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
                if (f.id == R.id.fragment_container) {
                    toolbar.visibility = if (f is NoToolbar) View.GONE else View.VISIBLE
                }
            }
        }, true)
    }
}