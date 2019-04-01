package com.saeedmoeen.food;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.saeedmoeen.food.Model.User;

public class SignUp extends AppCompatActivity {

    MaterialEditText etName, etPassword, etPhone;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = findViewById(R.id.et_Name);
        etPassword = findViewById(R.id.et_Password);
        etPhone = findViewById(R.id.et_Phone);

        btnSignUp = findViewById(R.id.btnSignUn);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("لطفا صبر کنید...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(etPhone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "شماره تلفن تکراری میباشد", Toast.LENGTH_SHORT).show();
                        } else  {
                            mDialog.dismiss();
                            User user = new User(etName.getText().toString(), etPassword.getText().toString());
                            table_user.child(etPhone.getText().toString()).setValue(user);

                            Toast.makeText(SignUp.this, "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
