package iview.wsienski.mycv.ui.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import iview.wsienski.mycv.Configuration;
import iview.wsienski.mycv.data.ProjectInfo;
import iview.wsienski.mycv.ui.view.ProjectView;
import iview.wsienski.mycv.util.Utils;

/**
 * Created by Witold Sienski on 12.07.2016.
 */
public class ProjectPresenterImpl implements ProjectPresenter{

    Context context;
    ProjectView projectView;

    public ProjectPresenterImpl(Context context, ProjectView projectView) {
        this.context = context;
        this.projectView = projectView;
    }

    @Override
    public void getProjectData() {
        String json = Utils.loadJSONFromAsset(context, Configuration.PROJECT_FILE_NAME);
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<ProjectInfo>>(){}.getType();
        List<ProjectInfo> projectInfos = gson.fromJson(json, listType);
        projectView.showProjectCard(projectInfos);
    }
}
