package com.saeedmoeen.food;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSignup, btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     btnSignin = findViewById(R.id.btnSignin);
     btnSignup = findViewById(R.id.btnSignup);

     btnSignin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent signin = new Intent(MainActivity.this, SignIn.class);
             startActivity(signin);
         }
     });

     btnSignup.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent signup = new Intent(MainActivity.this, SignUp.class);
             startActivity(signup);
         }
     });



    }
}
