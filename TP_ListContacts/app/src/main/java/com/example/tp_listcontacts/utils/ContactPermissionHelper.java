package com.example.tp_listcontacts.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;

public class ContactPermissionHelper {

    public static boolean hasContactPermission(Context context) {
        return ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }
}
