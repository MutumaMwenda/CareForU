package com.cbitss.careforu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorsActivity extends AppCompatActivity {


private RecyclerView mDoctorsList;
private DatabaseReference mDoctorsDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);


        mDoctorsList=(RecyclerView) findViewById(R.id.doctors_list);
        mDoctorsList.setHasFixedSize(true);
        mDoctorsList.setLayoutManager(new LinearLayoutManager(this));
        mDoctorsDatabase= FirebaseDatabase.getInstance().getReference().child("Users");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Doctors ,DoctorsViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<Doctors, DoctorsViewHolder>(
                Doctors.class,
                R.layout.doctors_single_layout,
                DoctorsViewHolder.class,
                mDoctorsDatabase

        ) {
            @Override
            protected void populateViewHolder(DoctorsViewHolder viewHolder, Doctors model, int position) {

                viewHolder.setName(model.getName());
            }
        };
        mDoctorsList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class DoctorsViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public DoctorsViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setName(String name){
            TextView nameTextView= (TextView) mView.findViewById(R.id.doctor_single_name);
            nameTextView.setText(name);

        }
    }
}
