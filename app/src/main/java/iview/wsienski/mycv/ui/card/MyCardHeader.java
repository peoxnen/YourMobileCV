package iview.wsienski.mycv.ui.card;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.gmariotti.cardslib.library.internal.CardHeader;
import iview.wsienski.mycv.R;

/**
 * Created by Witold Sienski on 12.07.2016.
 */
public class MyCardHeader extends CardHeader {

    String title="";

    public MyCardHeader(Context context) {
        super(context, R.layout.card_header);
    }

    public MyCardHeader(Context context, String title) {
        super(context, R.layout.card_header);
        this.title=title;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        if (view!=null) {
            TextView titleTv = (TextView) view.findViewById(R.id.title);
            titleTv.setText(title);
        }
    }
}