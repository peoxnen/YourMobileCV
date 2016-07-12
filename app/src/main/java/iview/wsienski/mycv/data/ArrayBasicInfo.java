package iview.wsienski.mycv.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created by Witold Sienski on 12.07.2016.
 */
public class ArrayBasicInfo {
    @Expose
    private String title;
    @Expose
    private ArrayList<BasicInfo> array;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public ArrayList<BasicInfo> getArray ()
    {
        return array;
    }

    public void setArray (ArrayList<BasicInfo> array)
    {
        this.array = array;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", array = "+array+"]";
    }
}