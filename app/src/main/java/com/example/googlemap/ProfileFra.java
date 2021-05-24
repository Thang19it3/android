package com.example.googlemap;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFra extends Fragment implements View.OnClickListener, ValueEventListener {


    private static FirebaseUser user;
    private static DatabaseReference reference;
    private static  String userId;
    private TextInputEditText fullname1,age1,email1;
    public ProfileFra() {
        // Required empty public constructor

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile2, container, false);
        Button btn = (Button)v.findViewById(R.id.logout);
        btn.setOnClickListener(this);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId= user.getUid();

        fullname1 = (TextInputEditText) v.findViewById(R.id.fullname);
        age1 = (TextInputEditText) v.findViewById(R.id.age);
        email1 = (TextInputEditText) v.findViewById(R.id.email);
        reference.child(userId).addListenerForSingleValueEvent(this);
        return  v;
    }

    @Override
    public void onClick(View v) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getActivity(),Login_viethan.class));
    }

    @Override
    public void onDataChange(@NonNull  DataSnapshot snapshot) {
        User userprofile = snapshot.getValue(User.class);
        if(userprofile != null)
        {
            String fullname = userprofile.fullname;
            String email = userprofile.email;
            String age = userprofile.age;
            fullname1.setText(fullname);
            age1.setText(age);
            email1.setText(email);
        }
    }

    @Override
    public void onCancelled(@NonNull  DatabaseError error) {
        Toast.makeText(getActivity(),"fiald",Toast.LENGTH_LONG).show();
    }
}