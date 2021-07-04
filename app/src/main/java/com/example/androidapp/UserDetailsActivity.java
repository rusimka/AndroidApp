package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetailsActivity extends AppCompatActivity {

    TextView tvIdText;
    TextView tvNameText;
    TextView tvEmailText;
    Button backBtn;
    ImageView profile_image;

    FirebaseUser mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        tvIdText = findViewById(R.id.id_txt);
        tvEmailText = findViewById(R.id.email_txt);

        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        tvIdText.setText(mAuth.getUid());
//        tvNameText.setText(mAuth.getDisplayName());
        tvEmailText.setText(mAuth.getEmail());

//        Glide.with(this).load(mAuth.getPhotoUrl()).into(profile_image);








    }
}