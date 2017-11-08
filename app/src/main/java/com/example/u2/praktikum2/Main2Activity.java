package com.example.u2.praktikum2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    TextView counter;
    int skor = 0;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    boolean statusSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        counter = (TextView) findViewById(R.id.counter);

        sp = this.getSharedPreferences("data_score", Context.MODE_PRIVATE);
        spe = sp.edit();

        skor = sp.getInt("keyScore",0);
        counter.setText(""+skor);
        terimaBundle();
    }

    public void tambah(View view){

        skor = skor+1;
        counter.setText(""+skor);
        statusSimpan = false;
    }

    public void kurang(View view){
        skor=skor-1;
        counter.setText(""+skor);
        statusSimpan = false;
    }

    public void terimaBundle(){
//        intent with bundle
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");
//        String sandi = bundle.getString("sandi");

        TextView tv1 = (TextView) findViewById(R.id.teksHello);
        tv1.setText("Hello "+username);
    }

    public void simpan(View view){
        Toast.makeText(this, "Score : "+skor+". Tersimpan! ", Toast.LENGTH_LONG).show();
        spe.putInt("keyScore", skor);
        spe.commit();
        statusSimpan = true;
    }
    public void reset(View view){
        skor=0;
        counter.setText("0");
        statusSimpan = false;
    }

    public void logout(View view){
        if (statusSimpan){
            spe.remove("username");
            spe.remove("password");
            spe.commit();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        } else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            //        langkah 2: tambah karakteristik dari dialog box
            builder.setMessage("are you sure want to exit without saving the score?")
                    .setTitle("Logout");


            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //          user click OK button
                    SharedPreferences sp = getSharedPreferences("data_login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor spEditor = sp.edit();
                    spe.remove("username");
                    spe.remove("password");
                    spe.commit();

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//          user click Cancel button
                    dialog.cancel();
                }
            });

            AlertDialog alertdialog = builder.create();
            alertdialog.show();

        }

    }
}
