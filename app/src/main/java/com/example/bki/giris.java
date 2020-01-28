package com.example.bki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class giris extends AppCompatActivity {
    private Button btnloginSayfasi;

    //Buton tanımlama yaptıktan sonra intent ile sayfalar arası geçiş yapması sağlanır.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris);
        btnloginSayfasi = findViewById(R.id.butonAnasayfa);
        btnloginSayfasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(giris.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}