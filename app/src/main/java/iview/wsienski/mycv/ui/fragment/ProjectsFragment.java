package iview.wsienski.mycv.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;
import iview.wsienski.mycv.Configuration;
import iview.wsienski.mycv.R;
import iview.wsienski.mycv.data.ProjectInfo;
import iview.wsienski.mycv.ui.card.SuggestedCard;
import iview.wsienski.mycv.util.Utils;

/**
 * Created by Witold Sienski on 11.07.2016.
 */
public class ProjectsFragment extends Fragment {

    private static final String TAG = ProjectsFragment.class.getSimpleName();
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        view = inflater.inflate(R.layout.fragment_projects, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.nav_title_projects));

        ArrayList<Card> cards = new ArrayList<>();

        String json = Utils.loadJSONFromAsset(getActivity(), Configuration.PROJECT_FILE_NAME);
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<ProjectInfo>>(){}.getType();
        List<ProjectInfo> projectInfos = gson.fromJson(json, listType);

        for(ProjectInfo projectInfo : projectInfos){
            SuggestedCard suggestedCard = new SuggestedCard(getActivity(), R.layout.carddemo_suggested_inner_content, projectInfo);
            cards.add(suggestedCard);
        }

        CardListView listView = (CardListView) view.findViewById(R.id.list_cards_projects);
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);
        if (listView!=null){
            listView.setAdapter(mCardArrayAdapter);
        }
        return view;
    }
}
