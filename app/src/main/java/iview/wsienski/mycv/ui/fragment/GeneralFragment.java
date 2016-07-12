package iview.wsienski.mycv.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.gmariotti.cardslib.library.view.CardView;
import iview.wsienski.mycv.R;
import iview.wsienski.mycv.data.BasicInfo;
import iview.wsienski.mycv.ui.card.GeneralCard;
import iview.wsienski.mycv.ui.presenter.GeneralPresenterImpl;
import iview.wsienski.mycv.ui.view.GeneralView;

/**
 * Created by Witold Sienski on 11.07.2016.
 */
public class GeneralFragment extends Fragment implements GeneralView {

    private static final String TAG = GeneralFragment.class.getSimpleName();
    View view;
    GeneralCard card;
    GeneralPresenterImpl generalPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        view = inflater.inflate(R.layout.fragment_general, container, false);
        getActivity().setTitle(getString(R.string.nav_title_general));

        generalPresenter = new GeneralPresenterImpl(this, getActivity());
        generalPresenter.getBasicInfo();
        return view;
    }

    @Override
    public void showBasicInfoCard(List<BasicInfo> basicInfoList) {
        //GeneralCard
        card = new GeneralCard(getActivity(),basicInfoList);
        card.init();

        //Set card in the cardView
        CardView cardView = (CardView) view.findViewById(R.id.card_general);
        cardView.setCard(card);
    }
}
