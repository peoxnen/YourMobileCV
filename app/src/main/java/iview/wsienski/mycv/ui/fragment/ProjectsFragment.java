package iview.wsienski.mycv.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;
import iview.wsienski.mycv.R;
import iview.wsienski.mycv.data.ProjectInfo;
import iview.wsienski.mycv.ui.card.SuggestedCard;
import iview.wsienski.mycv.ui.presenter.ProjectPresenterImpl;
import iview.wsienski.mycv.ui.view.ProjectView;

/**
 * Created by Witold Sienski on 11.07.2016.
 */
public class ProjectsFragment extends Fragment implements ProjectView{

    private static final String TAG = ProjectsFragment.class.getSimpleName();
    View view;
    CardListView listView;
    ProjectPresenterImpl projectPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        view = inflater.inflate(R.layout.fragment_projects, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.nav_title_projects));

        listView = (CardListView) view.findViewById(R.id.list_cards_projects);
        projectPresenter = new ProjectPresenterImpl(getActivity(), this);
        projectPresenter.getProjectData();

        return view;
    }

    @Override
    public void showProjectCard(List<ProjectInfo> projectInfos) {
        ArrayList<Card> cards = new ArrayList<>();
        for(ProjectInfo projectInfo : projectInfos){
            SuggestedCard suggestedCard = new SuggestedCard(getActivity(), R.layout.card_suggested_inner, projectInfo);
            cards.add(suggestedCard);
        }
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);
        if (listView!=null){
            listView.setAdapter(mCardArrayAdapter);
        }
    }
}
