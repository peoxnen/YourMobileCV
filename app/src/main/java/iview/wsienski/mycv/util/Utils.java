package iview.wsienski.mycv.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import iview.wsienski.mycv.data.BasicInfo;

/**
 * Created by Witold Sienski on 12.07.2016.
 */
public class Utils {

    public static List<BasicInfo> getBasicIngoFromArrays(Context context, int id){
        List<BasicInfo> basicInfoList = new ArrayList<>();
        try {
            String[] mTestArray = context.getResources().getStringArray(id);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            for (String item : mTestArray) {
                basicInfoList.add(gson.fromJson(item, BasicInfo.class));
            }
        } catch(Resources.NotFoundException e){
            Log.d("error", e.getLocalizedMessage());
        } catch (JsonSyntaxException e){
            Log.d("error", e.getLocalizedMessage());
        }
        return  basicInfoList;
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
