package com.example.sql_lite_table;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Rusername,Remail,Rpassword,RCpassword;
    Button Register;
    DbHelper Dbhelper;
    TextView gotologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Rusername = (EditText) findViewById(R.id.Rusername);
        Remail = (EditText) findViewById(R.id.Lusername);
        Rpassword = (EditText) findViewById(R.id.Lpassword);
        RCpassword = (EditText) findViewById(R.id.RCpassword);
        gotologin = (EditText) findViewById(R.id.gotologin);
        Register = (Button) findViewById(R.id.Login);
        Dbhelper = new DbHelper(MainActivity.this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rusername = Rusername.getText().toString();
                String remail = Remail.getText().toString();
                String rpassword = Rpassword.getText().toString();
                String rcpassword = RCpassword.getText().toString();
                if (rpassword.equals(rcpassword)) {
                    boolean emailcheck = Dbhelper.emailcheck(remail);
                        if (emailcheck == true) {
                            Toast.makeText(MainActivity.this, "Email Already Exist", Toast.LENGTH_SHORT).show();
                        }
                        else {
                        boolean successfullyinserted = Dbhelper.Insertrecord(rusername, remail, rpassword);
                        if (successfullyinserted == true) {
                            Toast.makeText(MainActivity.this, "Data Insert Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();

                        }
                        finish();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this , Login.class);
                startActivity(intent);
            }
        });
    }
}