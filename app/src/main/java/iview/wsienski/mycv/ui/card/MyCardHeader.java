package iview.wsienski.mycv.ui.card;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import it.gmariotti.cardslib.library.internal.CardHeader;
import iview.wsienski.mycv.R;

/**
 * Created by Witold Sienski on 12.07.2016.
 */
public class MyCardHeader extends CardHeader {

    public MyCardHeader(Context context) {
        super(context, R.layout.card_header);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

    }
}