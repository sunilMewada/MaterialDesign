package com.sunil.materialdesign;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sunil on 27-03-2017.
 */

public class SignUpActivity extends AppCompatActivity {
    private EditText name,email,password;
    private Button signButton;
    private TextView loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        name = (EditText)findViewById(R.id.input_name);
        email = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        signButton = (Button)findViewById(R.id.btn_signup);
        loginLink = (TextView)findViewById(R.id.link_login);

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void signup(){
        if (!validate()){
            onSignupFailed();
            return;
        }
        signButton.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,R.style.Theme_AppCompat_DayNight_DarkActionBar);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String nameText = name.getText().toString();
        String emailText = email.getText().toString();
        String pass = password.getText().toString();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onSignupSuccess();
                progressDialog.dismiss();
            }
        },1000000);
    }
    public void onSignupSuccess(){
        signButton.setEnabled(true);
        setResult(RESULT_OK,null);
        finish();
    }
    public void onSignupFailed(){
        Toast.makeText(getApplicationContext(),"Login Failed...",Toast.LENGTH_SHORT).show();
        signButton.setEnabled(true);
    }
    public boolean validate(){
        boolean valid = true;

        String nameText = name.getText().toString();
        String emailText = email.getText().toString();
        String pass = password.getText().toString();

        if (nameText.isEmpty() || nameText.length()<3){
            name.setError("Enter name");
            valid = false;
        }else {
            name.setError(null);
        }
        if (emailText.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            email.setError("Enter a valid email address");
            valid = false;
        }else {
            email.setError(null);
        }
        if (pass.isEmpty() || password.length()<4 || password.length() >10){
            password.setError("between 4 to 10 alfanumeric");
            valid = false;
        }
        else {
            password.setError(null);
        }
        return valid;
    }
}
