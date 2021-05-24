package com.example.googlemap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;


public class HomeFragment extends Fragment implements ValueEventListener {
private Button btn;
private FirebaseUser user;
private DatabaseReference reference;
private String userId;
private TextView fullname1;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viw = inflater.inflate(R.layout.fragment_home, container, false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId= user.getUid();
        fullname1 = (TextView) viw.findViewById(R.id.username);
        reference.child(userId).addListenerForSingleValueEvent(this);
        // Inflate the layout for this fragment
        return viw;
    }

    @Override
    public void onDataChange(@NonNull  DataSnapshot snapshot) {
        User userprofile = snapshot.getValue(User.class);
        if(userprofile != null)
        {
            String fullname = userprofile.fullname;
            fullname1.setText(fullname);
        }
    }

    @Override
    public void onCancelled(@NonNull  DatabaseError error) {
        Toast.makeText(getActivity(),"fiald",Toast.LENGTH_LONG).show();
    }
}