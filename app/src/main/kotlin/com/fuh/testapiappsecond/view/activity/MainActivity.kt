package com.fuh.testapiappsecond.view.activity

import android.accounts.Account
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.fuh.testapiappsecond.R
import com.fuh.testapiappsecond.model.VkRepository
import com.fuh.testapiappsecond.presenter.VkFriendsPresenter
import com.fuh.testapiappsecond.utils.addFragment
import com.fuh.testapiappsecond.utils.replaceFragment
import com.fuh.testapiappsecond.view.fragment.FriendsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.setDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_settings -> return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_friends -> {
                val friendsFragment =
                        (fragmentManager.findFragmentById(R.id.fragment) as FriendsFragment?) ?:
                                FriendsFragment()

                friendsFragment.presenter = VkFriendsPresenter(friendsFragment, VkRepository())
                replaceFragment(friendsFragment, R.id.fragment)
            }
            R.id.nav_profile -> {

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
