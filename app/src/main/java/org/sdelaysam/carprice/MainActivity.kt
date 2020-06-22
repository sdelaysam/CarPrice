package org.sdelaysam.carprice

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import org.sdelaysam.carprice.util.navigation.HasNavController
import org.sdelaysam.carprice.util.ui.RxActivity

class MainActivity : RxActivity(), HasNavController {

    override val navController: NavController
        get() = findNavController(R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupWithNavController(toolbar, navController)
    }
}