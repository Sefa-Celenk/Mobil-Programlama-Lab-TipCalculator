package com.sefacelenk.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etCorbaAdet, etYemekAdet, etTatliAdet, etBahsisOrani, etKisiSayisi;
    private Button btnHesapla;
    private TextView tvSonuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCorbaAdet = findViewById(R.id.etCorbaAdet);
        etYemekAdet = findViewById(R.id.etYemekAdet);
        etTatliAdet = findViewById(R.id.etTatliAdet);
        etBahsisOrani = findViewById(R.id.etBahsisOrani);
        etKisiSayisi = findViewById(R.id.etKisiSayisi);
        btnHesapla = findViewById(R.id.btnHesapla);
        tvSonuc = findViewById(R.id.tvSonuc);

        btnHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hesapla();
            }
        });
    }

    private void hesapla() {
        try {
            int corbaAdet = etCorbaAdet.getText().toString().isEmpty() ? 0 : Integer.parseInt(etCorbaAdet.getText().toString());
            int yemekAdet = etYemekAdet.getText().toString().isEmpty() ? 0 : Integer.parseInt(etYemekAdet.getText().toString());
            int tatliAdet = etTatliAdet.getText().toString().isEmpty() ? 0 : Integer.parseInt(etTatliAdet.getText().toString());

            double bahsisOrani = etBahsisOrani.getText().toString().isEmpty() ? 5.0 : Double.parseDouble(etBahsisOrani.getText().toString());
            int kisiSayisi = etKisiSayisi.getText().toString().isEmpty() ? 1 : Integer.parseInt(etKisiSayisi.getText().toString());
            if (kisiSayisi <= 0) kisiSayisi = 1;

            double corbaFiyat = 4.5;
            double yemekFiyat = 7.4;
            double tatliFiyat = 5.3;

            double toplamYemek = (corbaAdet * corbaFiyat) + (yemekAdet * yemekFiyat) + (tatliAdet * tatliFiyat);
            double toplamBahsis = (toplamYemek * bahsisOrani) / 100.0;
            double toplamHesap = toplamYemek + toplamBahsis;
            double kisiBasi = toplamHesap / kisiSayisi;

            tvSonuc.setText(String.format("Toplam Yemek: %.2f TL\nToplam Bahşiş (%%%.0f): %.2f TL\nToplam Hesap: %.2f TL\nKişi Başı Düşen: %.2f TL",
                    toplamYemek, bahsisOrani, toplamBahsis, toplamHesap, kisiBasi));

        } catch (NumberFormatException e) {
            tvSonuc.setText("Lütfen geçerli sayısal değerler girin!");
        }
    }
}