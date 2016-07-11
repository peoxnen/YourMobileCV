package iview.wsienski.mycv.data;

import com.google.gson.annotations.Expose;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.prototypes.CardWithList;

/**
 * Created by Witold Sienski on 11.07.2016.
 */
public class BasicInfo implements CardWithList.ListObject {

    @Expose
    private String title;
    @Expose
    private String value;

    public BasicInfo(){
    }

    public BasicInfo(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getObjectId() {
        return null;
    }

    @Override
    public Card getParentCard() {
        return null;
    }

    @Override
    public void setOnItemClickListener(CardWithList.OnItemClickListener onItemClickListener) {

    }

    @Override
    public CardWithList.OnItemClickListener getOnItemClickListener() {
        return null;
    }

    @Override
    public boolean isSwipeable() {
        return false;
    }

    @Override
    public void setSwipeable(boolean isSwipeable) {

    }

    @Override
    public CardWithList.OnItemSwipeListener getOnItemSwipeListener() {
        return null;
    }

    @Override
    public void setOnItemSwipeListener(CardWithList.OnItemSwipeListener onSwipeListener) {

    }
}
