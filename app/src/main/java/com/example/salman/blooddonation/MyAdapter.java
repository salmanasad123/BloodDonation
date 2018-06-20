package com.example.salman.blooddonation;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Salman on 12/13/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    List<Donor> donors;
    View view;
    Gson gson;
    GetDonors context;
    ImageView imageView;


    public MyAdapter(GetDonors context, List<Donor> donors) {
        this.donors = donors;
        this.context = context;
    }

    @Override
    public MyAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {

        final Donor donor = donors.get(position);
        holder.name.setText(donor.getFirstName() + " " + donor.getLastName());
        //holder.last_name.setText(donor.getLastName());
        if (donor.getBloodGroupId() == 1) {
            holder.blood_group_id.setText("AB+");
        } else if (donor.getBloodGroupId() == 2) {
            holder.blood_group_id.setText("AB-");
        } else if (donor.getBloodGroupId() == 3) {
            holder.blood_group_id.setText("A+");
        } else if (donor.getBloodGroupId() == 4) {
            holder.blood_group_id.setText("A-");
        } else if (donor.getBloodGroupId() == 5) {
            holder.blood_group_id.setText("B+");
        } else if (donor.getBloodGroupId() == 6) {
            holder.blood_group_id.setText("B-");
        } else if (donor.getBloodGroupId() == 7) {
            holder.blood_group_id.setText("O+");
        } else if (donor.getBloodGroupId() == 8) {
            holder.blood_group_id.setText("O-");
        }
        Picasso.with(context).load("http://icons.iconarchive.com/icons/dapino/medical/256/blood-drop-icon.png")
                .resize(160, 160).into(imageView);
        gson = new Gson();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = gson.toJson(donors.get(position));
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("MyObjectString", str);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return donors.size();

    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView blood_group_id;

        public myViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name);
            blood_group_id = itemView.findViewById(R.id.BloodGroupId);
            imageView = itemView.findViewById(R.id.imageView2);
        }
    }


}
