package iview.wsienski.mycv.ui.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import iview.wsienski.mycv.Configuration;
import iview.wsienski.mycv.OdometerService;
import iview.wsienski.mycv.R;
import iview.wsienski.mycv.data.PlaceInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Witold Sienski on 12.07.2016.
 */
public class MyMapFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = MyMapFragment.class.getSimpleName();
    View view;
    private GoogleMap mMap;
    MapFragment map;
    PlaceInfo placeInfo;
    private OdometerService odometerService;
    private  boolean bound  = false;
    TextView distance_tv;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            OdometerService.OdometerBinder odometerBinder = (OdometerService.OdometerBinder) iBinder;
            odometerService = odometerBinder.getOdometerBinder();
            bound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound=false;
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        view = inflater.inflate(R.layout.fragment_map, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.nav_title_map));
        map = MapFragment.newInstance();
        distance_tv= (TextView) view.findViewById(R.id.distance_tv);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mapLayout, map).commit();
        map.getMapAsync(this);//remember getMap() is deprecated!

        watchDistance();

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (placeInfo == null) {
            DownloadWebPageTask downloadWebPageTask = new DownloadWebPageTask();
            downloadWebPageTask.execute(Configuration.ADDRESS);
        }
    }

    void addMarker() {
        LatLng sydney = new LatLng(placeInfo.getLatitude(), placeInfo.getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title(placeInfo.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MapFragment f = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapLayout);
        if (f != null) {
            getFragmentManager().beginTransaction().remove(f).commit();
        }
    }


    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            OkHttpClient client = new OkHttpClient();
            Request request =
                    new Request.Builder()
                            .url(urls[0])
                            .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Download failed";
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                int latPosi = s.indexOf(Configuration.LAT) + Configuration.LAT.length();
                String lat = s.substring(latPosi, s.indexOf(",", latPosi));
                int lonPosi = s.indexOf(Configuration.LON) + Configuration.LON.length();
                String lon = s.substring(lonPosi, s.indexOf(",", lonPosi));
                Log.d("addMarker", "lat " + lat + " lon " + lon);
                double latD = Double.valueOf(lat);
                double lonD = Double.valueOf(lon);
                        placeInfo = new PlaceInfo(latD, lonD, "Espeo Software");
                    addMarker();

                Location loc = new Location("dummyprovider");
                loc.setLatitude(placeInfo.getLatitude());
                loc.setLongitude(placeInfo.getLongitude());
                OdometerService.placeLocation=loc;

                } catch (NumberFormatException e) {
                    Log.e("ERROR", e.getMessage());
                } catch (StringIndexOutOfBoundsException e) {
                    Log.e("ERROR", e.getMessage());
                }
                //problem with JSON respone on view-source:http://pastebin.com/raw/uYCM5u0P
                //            Gson gson = new GsonBuilder().create();
                //            PlaceInfo placeInfo = gson.fromJson(s, PlaceInfo.class);
            }
    };

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getActivity(), OdometerService.class);
        getActivity().bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(bound){
            getActivity().unbindService(connection);
            bound = false;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(bound){
            getActivity().unbindService(connection);
            bound = false;
        }
    }

    void watchDistance(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double distance = 0.0;
                if(odometerService!=null){
                    distance = odometerService.getDistance();
                }
                String distStr = String.format("Distance: %1$,.2f km",distance);
                if(distance>0){
                    distance_tv.setText(distStr);
                }
                Log.d("watchDistance","distStr "+distStr);
                handler.postDelayed(this, 1000);
            }
        });
    }
}
