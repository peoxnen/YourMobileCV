package iview.wsienski.mycv.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Witold Sienski on 12.07.2016.
 */
public class ProjectInfo implements Serializable {

    private String title;
    private String desc;
    private String link;
    private ArrayList<BasicInfo> array;
    private int ico;

    public ProjectInfo() {
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIco() {
        return ico;
    }

    public void setIco(int ico) {
        this.ico = ico;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<BasicInfo> getArray() {
        return array;
    }

    public void setArray(ArrayList<BasicInfo> array) {
        this.array = array;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", array.size = "+array.size()+", desc = "+desc+", link = "+link+", ico = "+ico+"]";
    }
}
