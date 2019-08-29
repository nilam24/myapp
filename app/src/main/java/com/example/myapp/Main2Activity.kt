package com.example.myapp

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myapp.view.FragmentMatch
import com.example.myapp.view.FragmentSave
import com.example.myapp.viewmodel.MatchViewModel
import kotlinx.android.synthetic.main.content_main2.*

class Main2Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

       // lateinit var vm : MatchViewModel
       // vm = ViewModelProviders.of(this).get(MatchViewModel::class.java)
      //  vm.getDetail("40.7484,-73.9857","NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ","20180616")

        var frame=findViewById<FrameLayout>(R.id.frame1)

        replaceFragment(FragmentMatch.getInstance(),R.id.frame1,true)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.frame1,FragmentMatch.getInstance())
//            .addToBackStack(null)
//            .commitAllowingStateLoss()

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            clearBackStack()

        }
       // super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
                replaceFragment(FragmentMatch.getInstance(),R.id.frame1,true)
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.frame1,FragmentMatch.getInstance())
//                    .addToBackStack(null)
//                    .commitAllowingStateLoss()
            }
            R.id.nav_gallery -> {
                replaceFragment(FragmentSave.getInstance(),R.id.frame1,true)
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.frame1,FragmentSave.getInstance())
//                    .addToBackStack(null)
//                    .commitAllowingStateLoss()


            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    //@IdRes return int value for the resource id
    private fun replaceFragment(fragment:Fragment, @IdRes frameId:Int, backstack:Boolean){

        if(supportFragmentManager==null) return

            var fragmentTransaction=supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(frameId,fragment,fragment.javaClass.simpleName)
         if(backstack){
             fragmentTransaction.addToBackStack(fragment.tag)
         }
        fragmentTransaction.commitAllowingStateLoss()

    }

    fun clearBackStack(){
        var count = backStackCount()
        if(count!=0)
        {
            supportFragmentManager.popBackStackImmediate()
            finish()
        }


    }

    // entry count of fragment added to backstack for mainactivity2- frame1 id
    fun backStackCount():Int{
        if(supportFragmentManager==null) return  0
        else
         return supportFragmentManager.backStackEntryCount

    }


}
