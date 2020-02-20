package com.example.bonanzaapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText mFirstName,mLastName,mEmail,mPassword,mReTypePassword,mCurrentAddress,mPermanentAddress,mPhoneNumber;
    Button mSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstName =  findViewById(R.id.firstNameEditText);
        mLastName  =  findViewById(R.id.lastNameEditText);
        mEmail =  findViewById(R.id.emailEditText);
        mPassword =  findViewById(R.id.passEditText);
        mReTypePassword =  findViewById(R.id.retypePassEditText);
        mCurrentAddress =  findViewById(R.id.currentAddEditText);
        mPermanentAddress =  findViewById(R.id.permanentAddEditText);
        mPhoneNumber =  findViewById(R.id.phoneNumberEditText);

        mSignup=  findViewById(R.id.signupButton);
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                String firstName = mFirstName.getText().toString().trim();
                String lastName = mLastName.getText().toString().trim();
                String reTypePassword = mReTypePassword.getText().toString().trim();
                String currentAddress = mCurrentAddress.getText().toString().trim();
                String permanentAddress = mPermanentAddress.getText().toString().trim();
                String phoneNumber = mPhoneNumber.getText().toString().trim();

                if(email.isEmpty()){
                    mEmail.setError("Email required");
                    mEmail.requestFocus();
                }
                if(password.isEmpty()){
                    mPassword.setError("Password required");
                    mPassword.requestFocus();
                }


            }
        });
    }


}
