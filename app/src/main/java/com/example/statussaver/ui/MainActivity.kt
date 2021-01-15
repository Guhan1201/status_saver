package com.example.statussaver.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.statussaver.R
import com.example.statussaver.adapter.TabPagerAdapter
import com.example.statussaver.model.TabItem
import com.example.statussaver.model.TabItem.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTERNAL_STORAGE_PERMISSION_CODE: Int = 343

    }

    lateinit var tabListItem: List<TabItem>

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
        tabListItem = listOf(
            PhotoTab("photo"),
            VedioTab("vedio"), DownloadTab("download")
        )
        initPagerAdapter()
        askForPermission()
    }

    private fun initPagerAdapter() {
        pager.adapter = TabPagerAdapter(
            this, tabListItem
        )

        TabLayoutMediator(tab, pager) { tab, position ->
            tab.text = tabListItem[position].displayName
        }.attach()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun askForPermission() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                EXTERNAL_STORAGE_PERMISSION_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)


        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            MaterialAlertDialogBuilder(this@MainActivity)
                .setTitle(resources.getString(R.string.no_permission))
                .setMessage(resources.getString(R.string.require_read_write_external_storage_permissin))
                .setNegativeButton(resources.getString(R.string.close)) { dialog, which ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        askForPermission()
                    }
                }
                .setPositiveButton(resources.getString(R.string.allow)) { dialog, which ->

                    if (!ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    ) {
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", packageName, null)
                        )
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)

                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            askForPermission()
                        }
                    }
                }
                .setCancelable(false)
                .show()
        }


    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}