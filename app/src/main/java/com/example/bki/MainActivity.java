package com.example.bki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Gerekli değerlerin tanımlamaları yapılır.
    RadioGroup radioGroup;
    Button hesapla;
    Button listele;
    EditText etKilo;
    EditText etBoy;
    TextView tvTitle;
    RadioButton radioButon;
    DatabaseHelper mDatabaseHelper;
    TextView tvSonuc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Kullanılacak değerlerin XML dosyasındaki karşılıkları ile eşleştirilmesi yapılır
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radioSex);
        hesapla =findViewById(R.id.btnHesapla);
        etKilo = findViewById(R.id.etKilo);
        etBoy = findViewById(R.id.etBoy);
        tvTitle = findViewById(R.id.tvBKI);
        tvSonuc = findViewById(R.id.tvSonuc);
        mDatabaseHelper = new DatabaseHelper(this);
        listele = findViewById(R.id.btnListele);


        //ClickListener sayesinde Butona tıklandığında Beden Kitle Endeksi Hesaplamaları Yapılır.
        hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButon= findViewById(selectedId);
                //Radio Butondaki Kadın Erkek Seçeneğine göre Farklı İşlemler Olacağından
                //Switch case ile işlemler birbirinden ayrılır
                switch(selectedId)
                {

                    case R.id.radioFemale: {
                        if (!etBoy.getText().toString().isEmpty()&& !etKilo.getText().toString().isEmpty()){
                            Float height = Float.parseFloat(etBoy.getText().toString());
                            Float weight = Float.parseFloat( etKilo.getText().toString() );
                            Float result = (weight/(height*height)*10000);
                            String description= " ";
                            if (result<20){
                                description = "Zayıf | Kadın";
                            }
                            else if (result>=20 && result<24){
                                description = "Normal | Kadın" ;
                            }
                            else if (result>=24 && result<29){
                                description = "Hafif Şişman | Kadın";
                            }
                            else {
                                description = "Şişman | Kadın";
                            }
                            tvTitle.setText( result + "   /  "+description);
                        }
                        break; }

                    case R.id.radioMale: {
                        if (!etBoy.getText().toString().isEmpty()&& !etKilo.getText().toString().isEmpty()){
                            Float height = Float.parseFloat(etBoy.getText().toString());
                            Float weight = Float.parseFloat(etKilo.getText().toString() );
                            Float result = (weight/(height*height)*10000);
                            String description= " ";
                            if (result<22){
                                description = "Zayıf | Erkek";
                            }
                            else if (result>=22 && result<27){
                                description = "Normal | Erkek" ;
                            }
                            else if (result>=27 && result<32){
                                description = "Hafif Şişman | Erkek";
                            }
                            else {
                                description = "Şişman | Erkek";
                            }
                            tvTitle.setText(result + "   /  "+description);
                        }
                        break;}


                }
                //Database'e gönderilecek değerler alınır.
                String kilo = etKilo.getText().toString();
                String boy = etBoy.getText().toString();
                String index = tvTitle.getText().toString();
                String sonuc = tvSonuc.getText().toString();
                mDatabaseHelper = new DatabaseHelper(MainActivity.this);
                mDatabaseHelper.addData(kilo,boy,index,sonuc);



            } });

        // Önceki kayıtların listelendiği sayfaya ulaşmak butona verlin Listener ile Intent kullanılır.
        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);

            }
        });

    }

}