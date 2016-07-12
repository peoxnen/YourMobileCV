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

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;
import iview.wsienski.mycv.R;
import iview.wsienski.mycv.ui.presenter.EducationPreenterImpl;
import iview.wsienski.mycv.ui.view.EducationView;

/**
 * Created by Witold Sienski on 11.07.2016.
 */
public class EducationFragment extends Fragment implements EducationView{

    private static final String TAG = EducationFragment.class.getSimpleName();
    View view;
    CardListView listView;
    EducationPreenterImpl educationPreenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        view = inflater.inflate(R.layout.fragment_education, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.nav_title_education));
        listView = (CardListView) view.findViewById(R.id.list_cards_edu);
        educationPreenter = new EducationPreenterImpl(this);
        educationPreenter.getEducationInfo(getActivity());

        return view;
    }

    @Override
    public void showEducationCard(ArrayList<Card> cards) {

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);
        if (listView!=null){
            listView.setAdapter(mCardArrayAdapter);
        }
    }
}
