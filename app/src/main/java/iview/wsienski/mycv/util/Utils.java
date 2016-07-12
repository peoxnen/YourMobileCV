package iview.wsienski.mycv.util;

import android.content.Context;

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
}
