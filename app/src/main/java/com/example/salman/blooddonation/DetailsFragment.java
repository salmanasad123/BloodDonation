package com.example.salman.blooddonation;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    Gson gson;
    TextView showFirstName;
    TextView showLastName;
    TextView showPhoneNumber;
    TextView showAge;
    TextView showBloodGroup;
    TextView showEmailid;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        gson = new Gson();
        View view2 = inflater.inflate(R.layout.fragment_details, container, false);

        EventBus.getDefault().register(this);

        showFirstName = view2.findViewById(R.id.showFirstName);
        showLastName = view2.findViewById(R.id.showLastName);
        showPhoneNumber = view2.findViewById(R.id.showPhoneNumber);
        showAge = view2.findViewById(R.id.showAge);
        showBloodGroup = view2.findViewById(R.id.showBloodGroup);
        showEmailid = view2.findViewById(R.id.showEmailiD);


        return view2;
    }

    @Subscribe
    public void onEvent(DonorEvent event) {
        String str = event.getMessage();
        Donor donor = gson.fromJson(str, Donor.class);
        showFirstName.setText("First Name : " + donor.getFirstName());
        showLastName.setText("Last Name : " + donor.getLastName());
        showPhoneNumber.setText("Phone Number : " + donor.getPhoneNumber().toString());
        showAge.setText("Age : " + donor.getAge().toString());
        int blood_group_id = donor.getBloodGroupId();
        if (blood_group_id == 1) {
            showBloodGroup.setText("Blood Group : " + "AB+");
        }
        if (blood_group_id == 2) {
            showBloodGroup.setText("Blood Group : " + "AB-");
        }
        if (blood_group_id == 3) {
            showBloodGroup.setText("Blood Group : " + "A+");
        }
        if (blood_group_id == 4) {
            showBloodGroup.setText("Blood Group : " + "A-");
        }
        if (blood_group_id == 5) {
            showBloodGroup.setText("Blood Group : " + "B+");
        }
        if (blood_group_id == 6) {
            showBloodGroup.setText("Blood Group : " + "B-");
        }
        if (blood_group_id == 7) {
            showBloodGroup.setText("Blood Group : " + "O+");
        }
        if (blood_group_id == 1) {
            showBloodGroup.setText("Blood Group : " + "O-");
        }

        showEmailid.setText(donor.getEmail().toString());
    }

}
