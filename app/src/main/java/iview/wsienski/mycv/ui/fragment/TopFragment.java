package iview.wsienski.mycv.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.view.CardView;
import iview.wsienski.mycv.R;
import iview.wsienski.mycv.data.BasicInfo;
import iview.wsienski.mycv.ui.card.GeneralCard;

/**
 * Created by Witold Sienski on 11.07.2016.
 */
public class TopFragment extends Fragment {

    private static final String TAG = TopFragment.class.getSimpleName();
    GeneralCard card;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_general, container, false);
        getActivity().setTitle(getString(R.string.nav_title_general));

        String[] mTestArray = getResources().getStringArray(R.array.general_array_titles);

        List<BasicInfo> basicInfoList = new ArrayList<>();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        for(String item : mTestArray){
           // basicInfoList.add(gson.fromJson(item, BasicInfo.class));
            Log.d("witek", "item down:");
            Log.d("witek", "item "+item);
            basicInfoList.add(gson.fromJson(item, BasicInfo.class));
        }
        //GeneralCard
        card = new GeneralCard(getActivity(),basicInfoList);
        card.init();

        //Set card in the cardView
        CardView cardView = (CardView) view.findViewById(R.id.card_general);
        cardView.setCard(card);

        return view;
    }
}
