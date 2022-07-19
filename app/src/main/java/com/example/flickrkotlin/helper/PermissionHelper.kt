package com.example.flickrkotlin.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object PermissionHelper {

    const val WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 999
    private fun checkGrandPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }


    fun checkGrandWriteExternalStoragePermission(context: Context): Boolean {
        return checkGrandPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    private fun requestPermission(
        activity: Activity,
        permissions: Array<String>,
        requestCode: Int
    ) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }


    fun requestWriteExternalStorage(activity: Activity) {
        requestPermission(
            activity,
            Array(1) { Manifest.permission.WRITE_EXTERNAL_STORAGE },
            WRITE_EXTERNAL_STORAGE_REQUEST_CODE
        )
    }

    fun requestWriteExternalStorage(fragment: Fragment) {
        fragment.requestPermissions(
            Array(1) { Manifest.permission.WRITE_EXTERNAL_STORAGE },
            WRITE_EXTERNAL_STORAGE_REQUEST_CODE
        )
    }


}