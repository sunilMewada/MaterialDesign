package com.sunil.materialdesign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_SIGNUP = 0;
    private EditText email,password;
    private Button login;
    TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        login = (Button) findViewById(R.id.btn_login);
        signup = (TextView) findViewById(R.id.link_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
            }
        });
    }
        public void login(){
            if (!validate()){
                onLoginFailed();
                return;
            }
            login.setEnabled(false);
            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,R.style.Theme_AppCompat_DayNight_DarkActionBar);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            String mail = email.getText().toString();
            String pass = password.getText().toString();

            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onLoginSuccess();
                    progressDialog.dismiss();
                }
            },3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP){
            if (resultCode == RESULT_OK){
                this.finish();
            }
        }
    }
    public void onBackPressed(){
        moveTaskToBack(true);
    }
    public void onLoginSuccess(){
        login.setEnabled(true);
        finish();
    }
    public void onLoginFailed(){
        Toast.makeText(getBaseContext(),"Login Failed",Toast.LENGTH_SHORT).show();

        login.setEnabled(true);
    }
    public boolean validate(){
        boolean valid = true;
        String mail =  email.getText().toString();
        String pass = password.getText().toString();

        if (mail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (pass.isEmpty() || password.length() < 4 || password.length() > 10) {
            email.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}

