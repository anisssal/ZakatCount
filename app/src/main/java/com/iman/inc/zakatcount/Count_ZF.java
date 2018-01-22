package com.iman.inc.zakatcount;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iman.inc.zakatcount.model.Mdl_ZF;

public class Count_ZF extends AppCompatActivity {

    EditText edtJumlahKel ,edtNamaKepKel;
    Button hitung,reset;
    TextView textTotalZakat;
    LinearLayout linearHasil;

    int jumlahKel;
    static final int TAKARAN = 3;
    int totalZakat = 0;
    String nKepKel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count__zf);
        edtJumlahKel = findViewById(R.id.edtJumlahKeluarga);
        edtNamaKepKel = findViewById(R.id.edtkepalaKeluarga);
        hitung = findViewById(R.id.btnHitungZakatFitr);
        reset = findViewById(R.id.btnResetZfitr);
        textTotalZakat = findViewById(R.id.txtTotalZakatF);
        linearHasil = findViewById(R.id.linearHasil);
        linearHasil.setVisibility(View.GONE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //display back button

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtJumlahKel.getText().toString().isEmpty()){
                    edtJumlahKel.setError("Masukkan Jumlah Keluarga");
                    return;
                }

                String strjumlahKel = edtJumlahKel.getText().toString();
                jumlahKel = Integer.parseInt(strjumlahKel);

                totalZakat = jumlahKel*TAKARAN;

                textTotalZakat.setText(String.valueOf(totalZakat));
                linearHasil.setVisibility(View.VISIBLE);



            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtJumlahKel.setText("");
                edtNamaKepKel.setText("");
                linearHasil.setVisibility(View.INVISIBLE);
            }
        });








    }
    //method buat aksi ditoolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get item id
        int id = item.getItemId();

        if (id==android.R.id.home){
            finish();
        }
        if (id==R.id.item_save){
            if (totalZakat==0){
                Toast.makeText(this, "Silahkan hitung dahulu", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (edtNamaKepKel.getText().toString().isEmpty()){
                edtNamaKepKel.setError("Masukkan nama KK");
                return false;
            }

            Db_helper db_helper = new Db_helper(Count_ZF.this);
            Mdl_ZF temp= new Mdl_ZF();
            temp.setJumlahKel(jumlahKel);
            temp.setTotalZakat(totalZakat);
            temp.setNamaKK(edtNamaKepKel.getText().toString());
            db_helper.saveDataZF(temp);
            Snackbar.make(linearHasil, "Sukses save data", Snackbar.LENGTH_SHORT).show();


        }


        return true;
    }

    //buat inflate toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }
}
