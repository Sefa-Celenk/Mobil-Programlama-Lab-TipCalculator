package com.sefacelenk.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // 1. DEĞİŞKENLERİ VE SABİTLERİ BURADA TANIMLIYORSUN (Sınıfın içi, metodların dışı)

    EditText etCorbaAdet, etYemekAdet, etTatliAdet;
    TextView tvCorbaToplam, tvYemekToplam, tvTatliToplam;
    TextView tvToplamYemek, tvToplamBahsis, tvToplamHesap;
    Button btnHesapla;

    final double CORBA_FIYAT = 4.5;
    final double YEMEK_FIYAT = 7.4;
    final double TATLI_FIYAT = 5.3;
    final double BAHSIS_ORANI = 0.05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 2. EŞLEŞTİRMELERİ VE BUTON DİNLEYİCİYİ BURADA (onCreate içinde) YAPIYORSUN
        etCorbaAdet = findViewById(R.id.etCorbaAdet);
        etYemekAdet = findViewById(R.id.etYemekAdet);
        etTatliAdet = findViewById(R.id.etTatliAdet);

        tvCorbaToplam = findViewById(R.id.tvCorbaToplam);
        tvYemekToplam = findViewById(R.id.tvYemekToplam);
        tvTatliToplam = findViewById(R.id.tvTatliToplam);

        tvToplamYemek = findViewById(R.id.tvToplamYemek);
        tvToplamBahsis = findViewById(R.id.tvToplamBahsis);
        tvToplamHesap = findViewById(R.id.tvToplamHesap);

        btnHesapla = findViewById(R.id.btnHesapla);

        btnHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hesapla();
            }
        });
    }

    // 3. HESAPLAMA METODUNU BURAYA (onCreate'in dışına) YAZIYORSUN
    private void hesapla() {
        int corbaAdet = 0;
        int yemekAdet = 0;
        int tatliAdet = 0;

        try {
            if (!etCorbaAdet.getText().toString().isEmpty()) {
                corbaAdet = Integer.parseInt(etCorbaAdet.getText().toString());
            }
            if (!etYemekAdet.getText().toString().isEmpty()) {
                yemekAdet = Integer.parseInt(etYemekAdet.getText().toString());
            }
            if (!etTatliAdet.getText().toString().isEmpty()) {
                tatliAdet = Integer.parseInt(etTatliAdet.getText().toString());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        double corbaToplam = corbaAdet * CORBA_FIYAT;
        double yemekToplam = yemekAdet * YEMEK_FIYAT;
        double tatliToplam = tatliAdet * TATLI_FIYAT;

        tvCorbaToplam.setText("Toplam Fiyat: " + String.format("%.2f", corbaToplam) + " TL");
        tvYemekToplam.setText("Toplam Fiyat: " + String.format("%.2f", yemekToplam) + " TL");
        tvTatliToplam.setText("Toplam Fiyat: " + String.format("%.2f", tatliToplam) + " TL");

        double toplamYemekFiyati = corbaToplam + yemekToplam + tatliToplam;
        double toplamBahsis = toplamYemekFiyati * BAHSIS_ORANI;
        double toplamHesap = toplamYemekFiyati + toplamBahsis;

        tvToplamYemek.setText(String.format("%.2f", toplamYemekFiyati) + " TL");
        tvToplamBahsis.setText(String.format("%.2f", toplamBahsis) + " TL");
        tvToplamHesap.setText(String.format("%.2f", toplamHesap) + " TL");
    }
}