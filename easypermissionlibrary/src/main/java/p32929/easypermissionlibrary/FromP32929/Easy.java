package p32929.easypermissionlibrary.FromP32929;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import p32929.easypermissionlibrary.FromAlexVai.PermissionHelper;
import p32929.easypermissionlibrary.FromAlexVai.PermissionsListener;

/**
 * Created by p32929 on 8/16/2018.
 */

public class Easy {
    public static void getPermissions(ArrayList<String> permissions, final Context context, PermissionsListener listener) {
        PermissionHelper permissionHelper = PermissionHelper.with((Activity) context)
                .setPermissionListener(listener)
                .setPermissions(permissions)
                .build();
        permissionHelper.request();
    }

    public static void getPermission(String permission, final Context context, PermissionsListener listener) {
        PermissionHelper permissionHelper = PermissionHelper.with((Activity) context)
                .setPermissionListener(listener)
                .setPermissions(permission)
                .build();
        permissionHelper.request();
    }

}
