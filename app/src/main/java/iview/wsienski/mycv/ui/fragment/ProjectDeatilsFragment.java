package iview.wsienski.mycv.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import iview.wsienski.mycv.R;
import iview.wsienski.mycv.data.ProjectInfo;

/**
 * Created by Witold Sienski on 12.07.2016.
 */
public class ProjectDeatilsFragment extends Fragment {

    private static final String TAG = ProjectDeatilsFragment.class.getSimpleName();
    public static final String PROJECT_BUNDLE_ID = "PROJECT_BUNDLE_ID";
    View view;
    SliderLayout sliderLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        view = inflater.inflate(R.layout.fragment_details, container, false);
        sliderLayout= (SliderLayout) view.findViewById(R.id.slider);

        Bundle bundle = getArguments();
        if(bundle!=null){
            ProjectInfo projectInfo = (ProjectInfo) bundle.getSerializable(PROJECT_BUNDLE_ID);
            Log.d("witek","projectInfo");
            Log.d("witek","projectInfo "+projectInfo);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(projectInfo.getTitle());
            if(!TextUtils.isEmpty(projectInfo.getLink())){
                setSlider();
            }
        }else {
            Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_LONG).show();
        }

        return view;
    }

    void setSlider(){
        sliderLayout.addSlider(getTxtSliderView(R.string.image_desc_1, R.drawable.img1_1));
        sliderLayout.addSlider(getTxtSliderView(R.string.image_desc_2, R.drawable.img1_2));
        sliderLayout.setVisibility(View.VISIBLE);
    }

    TextSliderView getTxtSliderView(int stringId, int img){
        TextSliderView textSliderView = new TextSliderView(getActivity());
        textSliderView
                .description(getActivity().getString(stringId))
                .image(img);
        return textSliderView;
    }

}
