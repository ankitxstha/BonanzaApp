package com.example.bonanzaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPrefences;
    private SharedPreferences.Editor mEditor;
    EditText email, password;
    TextView signup;
    String Email, Password;
    private Button btnLogin;
    ImageView googleImageView;
    private CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email =  findViewById(R.id.emailEditText);
        password =  findViewById(R.id.passEditText);
        signup =  findViewById(R.id.signupTextView);
        checkbox = findViewById(R.id.checkBox);
        btnLogin = findViewById(R.id.signinButton);

        mPrefences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mPrefences.edit();

        checkSharedPrefences();

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkbox.isChecked()){
                    mEditor.putString(getString(R.string.checkBox),"True");

                    String mEmail = email.getText().toString().trim();
                    mEditor.putString(getString(R.string.EmailEditText),mEmail);

                    String mPassword = password.getText().toString();
                    mEditor.putString(getString(R.string.passwordEditText),mPassword);

                    mEditor.apply();
                }else{
                    mEditor.putString(getString(R.string.checkBox),"False");
                    mEditor.putString(getString(R.string.EmailEditText),"");
                    mEditor.putString(getString(R.string.passwordEditText),"");
                    mEditor.apply();
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email = email.getText().toString();
                Password = password.getText().toString();
                btnLogin.setVisibility(View.GONE);

                if (!isConnected(MainActivity.this)) {
                    submit.setVisibility(View.VISIBLE);
                    //  Toast.makeText(LoginActivity.this, getString(R.string.no_internet) + "", Toast.LENGTH_SHORT).show();
                    textview_error.setText(getString(R.string.no_internet) + "");
                } else if (isConnected(MainActivity.this) & checklogin()) {
                    progressBar.setVisibility(View.VISIBLE);


                    new Thread(new Runnable() {
                        public void run() {
                            jsonParseforstatus();
                            jsonParse();

                        }
                    }).start();
                    submit.setVisibility(View.GONE);
                } else {
                    submit.setVisibility(View.VISIBLE);
                }

            }
        }



        );
    }

    private void checkSharedPrefences(){
        String mCheckbox = mPrefences.getString(getString(R.string.checkBox),"False");
        String mEmail = mPrefences.getString(getString(R.string.EmailEditText),"");
        String mPassword = mPrefences.getString(getString(R.string.passwordEditText),"");

        email.setText(mEmail);
        password.setText(mPassword);
        if(mCheckbox.equals("True")){
            checkbox.setChecked(true);
        }else{
            checkbox.setChecked(false);
        }
    }

    public boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return true;
        } else
            return false;
    }

    private boolean checklogin() {

        // Check for a valid email address:
        if (email.getText() == null) {
            email.setError(getString(R.string.error_field_required));
            btnLogin.setVisibility(View.VISIBLE);
            return false;
        } else if (!isEmailValid(Email)) {
            email.setError(getString(R.string.error_invalid_email));
            return false;
        }

        // Check for a valid password:
        if (password.getText() == null ) {
            password.setError(getString(R.string.error_invalid_password));
            btnLogin.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

}
