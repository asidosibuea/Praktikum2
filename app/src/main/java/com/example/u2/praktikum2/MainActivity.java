package com.example.u2.praktikum2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("data_login", Context.MODE_PRIVATE);
        spe = sp.edit();

        //ambil setting yang sudah ada jika tersimpan
        String username = sp.getString("username","");
        String password = sp.getString("password","");

        if (validasiLogin(username,password)) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void login(View view){
        edtUsername = (EditText) findViewById(R.id.username);
        edtPassword = (EditText) findViewById(R.id.password);

        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        if(validasiLogin(username,password)){
            Bundle bundle = new Bundle();
            bundle.putString("username",username);
//            bundle.putString("password", password);

            spe.putString("username",username);
            spe.putString("password",password);
            spe.commit();
            Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();

        } else{
            Toast.makeText(this, "Maaf username atau password salah", Toast.LENGTH_LONG).show();
        }

    }

    public boolean validasiLogin(String username, String password) {
        if(username.equals("asido") && password.equals("123456")) {
            return true;
        }
        return false;
    }
}
