package iview.wsienski.mycv.ui.card;

import android.content.Context;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardWithList;
import iview.wsienski.mycv.R;
import iview.wsienski.mycv.data.BasicInfo;

/**
 * Created by Witold Sienski on 11.07.2016.
 */
public class GeneralCard extends CardWithList{

    String title;
    List<BasicInfo> basicInfoList;

    public GeneralCard(Context context, List<BasicInfo> basicInfoList, String title) {
        super(context);
        this.title=title;
        this.basicInfoList=basicInfoList;
    }

    @Override
    protected CardHeader initCardHeader() {
        Context context = getContext();
        MyCardHeader cardHeader = new MyCardHeader(context, title);
        return cardHeader;
    }

    @Override
    protected void initCard() {
    }

    @Override
    protected List<ListObject> initChildren() {
        List<ListObject> listObject =  new ArrayList<>();
        listObject.addAll(basicInfoList);
        return listObject;
    }

    @Override
    public View setupChildView(int childPosition, ListObject object, View convertView, ViewGroup parent) {
        TextView title = (TextView) convertView.findViewById(R.id.item_title);
        TextView value = (TextView) convertView.findViewById(R.id.item_value);
        title.setText(basicInfoList.get(childPosition).getTitle());
        value.setText(basicInfoList.get(childPosition).getValue());
        Linkify.addLinks(title, Linkify.ALL);
        Linkify.addLinks(value, Linkify.ALL);
        return convertView;
    }

    @Override
    public int getChildLayoutId() {
        return R.layout.card_general_inner;
    }

    @Override
    public void init() {
        super.init();
    }
}
