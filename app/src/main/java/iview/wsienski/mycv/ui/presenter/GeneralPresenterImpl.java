package iview.wsienski.mycv.ui.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import iview.wsienski.mycv.R;
import iview.wsienski.mycv.data.BasicInfo;
import iview.wsienski.mycv.ui.view.GeneralView;

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
        String[] mTestArray = context.getResources().getStringArray(R.array.general_array_titles);

        List<BasicInfo> basicInfoList = new ArrayList<>();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        for(String item : mTestArray){
            basicInfoList.add(gson.fromJson(item, BasicInfo.class));
        }

        generalView.showBasicInfoCard(basicInfoList);
    }
}
