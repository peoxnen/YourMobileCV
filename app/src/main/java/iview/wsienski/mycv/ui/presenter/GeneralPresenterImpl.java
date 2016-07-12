package iview.wsienski.mycv.ui.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import iview.wsienski.mycv.Configuration;
import iview.wsienski.mycv.data.BasicInfo;
import iview.wsienski.mycv.ui.view.GeneralView;
import iview.wsienski.mycv.util.Utils;

/**
 * Created by Witold Sienski on 11.07.2016.
 */
public class GeneralPresenterImpl implements GeneralPresenter {

    Context context;
    GeneralView generalView;

    public GeneralPresenterImpl(GeneralView generalView, Context context) {
        this.generalView = generalView;
        this.context = context;
    }

    @Override
    public void getBasicInfo() {
        String json = Utils.loadJSONFromAsset(context, Configuration.GENERAL_FILE_NAME);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Type listType = new TypeToken<List<BasicInfo>>(){}.getType();
        List<BasicInfo> basicInfoList = gson.fromJson(json, listType);
        generalView.showBasicInfoCard(basicInfoList);
    }
}
