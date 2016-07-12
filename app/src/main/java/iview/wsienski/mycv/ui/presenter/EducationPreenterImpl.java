package iview.wsienski.mycv.ui.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import iview.wsienski.mycv.Configuration;
import iview.wsienski.mycv.data.ArrayBasicInfo;
import iview.wsienski.mycv.ui.card.GeneralCard;
import iview.wsienski.mycv.ui.card.MyCardHeader;
import iview.wsienski.mycv.ui.view.EducationView;
import iview.wsienski.mycv.util.Utils;

/**
 * Created by Witold Sienski on 12.07.2016.
 */
public class EducationPreenterImpl implements EducationPresenter {

    EducationView educationView;

    public EducationPreenterImpl(EducationView educationView) {
        this.educationView = educationView;
    }

    @Override
    public void getEducationInfo(Context context) {

        String json = Utils.loadJSONFromAsset(context, Configuration.EDU_FILE_NAME);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Type listType = new TypeToken<List<ArrayBasicInfo>>(){}.getType();
        List<ArrayBasicInfo> arrayBasicInfos = gson.fromJson(json, listType);

        ArrayList<Card> cards = new ArrayList<>();
        for(ArrayBasicInfo arrayBasicInfo : arrayBasicInfos){
            MyCardHeader header = new MyCardHeader(context,arrayBasicInfo.getTitle());
            GeneralCard card = new GeneralCard(context, arrayBasicInfo.getArray());
            card.init();
            card.addCardHeader(header);
            cards.add(card);
        }

        educationView.showEducationCard(cards);
    }
}
