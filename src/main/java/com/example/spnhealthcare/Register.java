package com.example.spnhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText regun,regem,regpwd,regcpwd;

    Button btn;

    TextView logtxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regun = findViewById(R.id.regun);
        regpwd = findViewById(R.id.regpwd);
        regem = findViewById(R.id.em);
        regcpwd = findViewById(R.id.cpwd);
        logtxt = findViewById(R.id.regtolog);
        btn = findViewById(R.id.rb);

        logtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Register.this,login.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = regun.getText().toString();
                String email = regem.getText().toString();
                String password = regpwd.getText().toString();
                String confirmpwd = regcpwd.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if (username.length()==0 || password.length()==0 || confirmpwd.length()==0){
                    Toast.makeText(getApplicationContext(),"Please Fill All Details",Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirmpwd)==0){
                        if (isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"Record Inserted",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,login.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Password must contain at least 8 character, having letter, digit and special symbol",Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        Toast.makeText(getApplicationContext(),"Password And Confirm Password Didn't Match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }

    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=3;
        if (passwordhere.length()<8){
            return false;
        } else {
            for (int p=0;p<passwordhere.length();p++){
                if (Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for (int r=0;r<passwordhere.length();r++){
                if (Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }

            for (int s=0;s<passwordhere.length();s++){
                char c=passwordhere.charAt(s);
                if (c>=33&&c<=46||c==64){
                    f3=1;
                }
            }

            if (f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
}