package iview.wsienski.mycv.ui.card;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import iview.wsienski.mycv.R;
import iview.wsienski.mycv.data.ProjectInfo;

/**
 * Created by Witold Sienski on 11.07.2016.
 */
public class SuggestedCard extends Card {

    ProjectInfo projectInfo;

    public SuggestedCard(Context context, int innerLayout, ProjectInfo projectInfo) {
        super(context, innerLayout);
        this.projectInfo=projectInfo;
        init();
    }

    private void init() {

        //Add a header
        MyCardHeader header = new MyCardHeader(getContext(), projectInfo.getTitle());
        addCardHeader(header);
        header.setOtherButtonDrawable(R.drawable.ic_menu_send);
        if(!TextUtils.isEmpty(projectInfo.getLink()))
            setOtherBtn(header);

        //Set click listener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getContext(), "Click listener", Toast.LENGTH_LONG).show();
            }
        });

    }

    private int getIconId(){
        if(projectInfo.getIco()==1){
            return R.drawable.ico_miboa;
        }else{
            return R.drawable.ic_menu_send;
        }
    }

    private void setOtherBtn(CardHeader header){
        header.setOtherButtonVisible(true);

        //Add a callback
        header.setOtherButtonClickListener(new CardHeader.OnClickCardHeaderOtherButtonListener() {
            @Override
            public void onButtonItemClick(Card card, View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(projectInfo.getLink()));
                getContext().startActivity(browserIntent);
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        if (view != null) {
            TextView title = (TextView) view.findViewById(R.id.tv1);
            TextView member = (TextView) view.findViewById(R.id.tv2);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageview);

            if (title != null)
                title.setText(projectInfo.getDesc());

            if (member != null)
                member.setText(R.string.info_press_details);

            if(imageView!=null)
                imageView.setImageDrawable(getContext().getResources().getDrawable(getIconId()));
        }
    }


}

