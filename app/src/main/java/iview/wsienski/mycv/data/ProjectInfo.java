package iview.wsienski.mycv.data;

/**
 * Created by Witold Sienski on 12.07.2016.
 */
public class ProjectInfo {

    private String title;
    private String desc_long;
    private String desc;
    private String link;
    private int ico;

    public ProjectInfo() {
    }

    public String getDesc_long() {
        return desc_long;
    }

    public void setDesc_long(String desc_long) {
        this.desc_long = desc_long;
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


    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", desc_long = "+desc_long+", desc = "+desc+", link = "+link+", ico = "+ico+"]";
    }
}
