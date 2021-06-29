package com.example.contactlist.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.contactlist.R
import com.example.contactlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 1
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)
        EnableRuntimePermission()
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPhoneContactsPermission(Manifest.permission.READ_CONTACTS))
                requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE
            )
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContactListFragment.newInstance())
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                .commitNow()
        }*/
    }

   /* private fun hasPhoneContactsPermission(permission: String): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val hasPermission =
                ContextCompat.checkSelfPermission(applicationContext, permission)
            hasPermission == PackageManager.PERMISSION_GRANTED
        } else true
    }*/

   private fun EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this@MainActivity,
                Manifest.permission.READ_CONTACTS
            )
        ) {
            Toast.makeText(
                this@MainActivity,
                "CONTACTS permission allows us to Access CONTACTS app",
                Toast.LENGTH_LONG
            ).show()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContactListFragment.newInstance())
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                .commit()
        } else {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.READ_CONTACTS),REQUEST_CODE
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this@MainActivity,
                    "Permission Granted, Now your application can access CONTACTS.",
                    Toast.LENGTH_LONG
                ).show()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ContactListFragment.newInstance())
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .commit()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Permission Canceled, Now your application cannot access CONTACTS.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}