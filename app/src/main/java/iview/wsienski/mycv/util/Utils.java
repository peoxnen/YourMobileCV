package iview.wsienski.mycv.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Witold Sienski on 12.07.2016.
 */
public class Utils {

       public static String checkUrl(String url){
           if (!url.startsWith("http://") && !url.startsWith("https://"))
               url = "http://" + url;
           return url;
       }

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {

            InputStream is = context.getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static boolean checkPerm(Activity activity, String perm){
        boolean flag;
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(activity,
                perm)
                != PackageManager.PERMISSION_GRANTED) {
            flag=false;
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    perm)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity,
                        new String[]{perm},
                        123);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{
            flag=true;
        }
        return flag;
    }
}
