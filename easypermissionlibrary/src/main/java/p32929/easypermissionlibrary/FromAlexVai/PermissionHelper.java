package p32929.easypermissionlibrary.FromAlexVai;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by p32929 on 8/16/2018.
 */

/*
MIT License

Copyright (c) 2018 Fayaz Bin Salam

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

public class PermissionHelper {

    private Builder builder;

    private PermissionHelper(Builder builder) {
        this.builder = builder;
    }

    public static Builder with(Activity activity) {
        return new Builder(activity);
    }

    public void request() {
        if (this.builder.listener == null) {
            throw new IllegalArgumentException("PermissionsListener is missing. You must add PermissionsListener");
        } else if (this.builder.permissionList == null || this.builder.permissionList.isEmpty()) {
            throw new IllegalArgumentException("No Permissions found. You must add at lest a permissions");
        }


        for (String per : builder.permissionList) {
            if (!hasPermission(builder.activity, per)) {
                throw new NullPointerException(per + " Permission is not found in Manifest.");
            }
        }


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            this.builder.listener.onPermissionGranted();
            return;
        }

        Intent intent = new Intent(builder.activity, PermissionHelperActivity.class);
        intent.putStringArrayListExtra("LIST", builder.permissionList);
        //builder.activity.startActivity(intent);
        PermissionHelperActivity.startActivity(builder.activity, intent, builder.listener);
    }

    public static class Builder {
        ArrayList<String> permissionList = new ArrayList<>();
        PermissionsListener listener = null;
        Activity activity;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setPermissionListener(@Nullable PermissionsListener l) {
            this.listener = l;
            return this;
        }

        public Builder setPermissions(ArrayList<String> permissionList) {
            this.permissionList = permissionList;
            return this;
        }

        public Builder setPermissions(String... permissions) {
            this.permissionList = new ArrayList<>(Arrays.asList(permissions));
            return this;
        }

        public PermissionHelper build() {
            //Log.e("TAG", "permissionList: "+permissionList.size());
            return new PermissionHelper(this);
        }

    }


    //for example, permission can be "android.permission.WRITE_EXTERNAL_STORAGE"
    private boolean hasPermission(Activity activity, String permission) {
        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_PERMISSIONS);
            if (info.requestedPermissions != null) {
                for (String p : info.requestedPermissions) {
                    if (p.equals(permission)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}