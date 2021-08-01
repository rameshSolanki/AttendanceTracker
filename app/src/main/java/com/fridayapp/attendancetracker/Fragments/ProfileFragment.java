package com.fridayapp.attendancetracker.Fragments;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.fridayapp.attendancetracker.Activity.MainActivity;
import com.fridayapp.attendancetracker.Constants.Constant;
import com.fridayapp.attendancetracker.Extras.GPSLocator;
import com.fridayapp.attendancetracker.Model.EmpData;
import com.fridayapp.attendancetracker.R;
import com.fridayapp.attendancetracker.Services.MyInterface;
import com.fridayapp.attendancetracker.Services.ServiceApi;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

public class ProfileFragment extends Fragment {
    private TextView name, email;
    private Button logoutBtn, btnCheckIn;
    CircleImageView imgProfile;
    MyInterface logoutListener;
    EditText tv_latitude, tv_longhitud;
    ServiceApi serviceApi;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        imgProfile = view.findViewById(R.id.imgUser);
        name = view.findViewById(R.id.name);
        tv_latitude = view.findViewById(R.id.tv_latitude);
        tv_longhitud = view.findViewById(R.id.tv_longhitud);
        String Name = "Hi, " + MainActivity.appPreference.getDisplayName();
        String Image = MainActivity.appPreference.getDisplayImage();
        name.setText(Name);

        email = view.findViewById(R.id.email);

        String Email_cDate = MainActivity.appPreference.getDisplayEmail()
                +"\n Registered at: " + MainActivity.appPreference.getCreDate();
        email.setText(Email_cDate);
        //Log.e("created_at: ", c_date);
        Picasso.get()
                .load(Constant.baseUrl.IMAGE_BASE_URL + Image)
                .placeholder(R.drawable.ic_person_black_24dp)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .fit()
                .centerCrop()
                .networkPolicy(NetworkPolicy.NO_CACHE).into(imgProfile);

        btnCheckIn = view.findViewById(R.id.checkinBtn);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutListener.logout();
            }
        });
        btnCheckIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Check In Clicked", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        123);
                GPSLocator gpsLocator = new GPSLocator(getContext());
                Location location = gpsLocator.GetLocation();
                if(location != null) {
                    double latitude = location.getLatitude();
                    double longhitud = location.getLongitude();
                    tv_latitude.setText(String.valueOf(latitude));
                    tv_longhitud.setText(String.valueOf(longhitud));
                }
            }
        });
        return view;
    } // ending onCreateView

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logoutListener = (MyInterface) activity;

    }


}
