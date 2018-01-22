package com.iman.inc.zakatcount;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iman.inc.zakatcount.model.Mdl_ZM;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Count_ZM extends AppCompatActivity {
    EditText edtJumlahHarta, edtHargaEmas;
    CheckBox konfirm;
    Button btnHitung, btnReset;


    TextView nishabEmasSaatini;
    TextView totalZakat;
    EditText namaPem;


    //hukum islamnya nishabnya 20dinas = 85gr murni
    final static int nishabPatokan = 85;

    String strJmlHarta;
    String strHargaEmas;
    String strTotalZakat;



    //linearlayout hasil perhitungan, jangan lupa inisiasinya di iniView();
    LinearLayout linearhasilmal;


    //edit Text nama pemilik, jangan lupa inisiasinya di iniView();
    EditText edtNamaPem;


    //textwacther
    WeakReference <EditText>weakReference ;
    TextWatcher textWatcher ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count__zm);
        initView();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtJumlahHarta.getText().toString().isEmpty()){
                    edtJumlahHarta.setError("Masukkan Jumlah Harta");
                    return;
                }
                if (edtHargaEmas.getText().toString().isEmpty()){
                    edtHargaEmas.setError("Masukkan Harga Emas");
                    return;
                }

                if (!konfirm.isChecked()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Count_ZM.this);
                    builder.setTitle("Attention");
                    builder.setMessage(" Anda belum wajib mengeluarkan zakat mal \n" +
                            "karena harta anda belum tetap selama 1 tahun!");
                    builder.setPositiveButton("Siap", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.show();
                    return;
                }

                int hargaEmas = Integer.parseInt(edtHargaEmas.getText().toString());
                long jumlahHarta = Long.parseLong(edtJumlahHarta.getText().toString());

                int convertedNishab = hargaEmas*nishabPatokan;
                if (jumlahHarta<convertedNishab){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Count_ZM.this);
                    builder.setTitle("Attention");
                    builder.setMessage(" Anda belum wajib mengeluarkan zakat mal \n" +
                            "harta anda belum mencapai nishab!");
                    builder.setPositiveButton("Siap", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.show();
                    return;

                }

                //kesepakatan ulama , ketika udah melewati nishab, zakat yg harus dikeluarkan
                //adalah 0.025 atau 2,5% atau 1/40
                long totalZakatReal = jumlahHarta/40;

                //make formater
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("Rp. ");
                formatRp.setDecimalSeparator('.');


                DecimalFormat mataUangIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                mataUangIndonesia.setDecimalFormatSymbols(formatRp);

                //convert int and long ke string dengan formater
                strHargaEmas = mataUangIndonesia.format(convertedNishab);
                strJmlHarta = mataUangIndonesia.format(jumlahHarta);
                strTotalZakat=mataUangIndonesia.format(totalZakatReal);

                //setText hasil
                nishabEmasSaatini.setText(strHargaEmas);
                totalZakat.setText(strTotalZakat);

                linearhasilmal.setVisibility(View.VISIBLE);


            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });





    }




    //method ini digunakan untuk mereset data jadi kita bisa pakai ketika btnreset diklik, ataupun saat setelah save
    public void reset(){

        edtJumlahHarta.setText("");
        edtHargaEmas.setText("");
        edtNamaPem.setText("");
        linearhasilmal.setVisibility(View.INVISIBLE);

    }


    //untuk menampilkan menu save yg berada di toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    //eventhandling ketika menu  yg ada di toolbar (home dan save) diklik
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.item_save){

            if (edtJumlahHarta.getText().toString().isEmpty() || edtHargaEmas.getText().toString().isEmpty()){
                Toast.makeText(this, "Anda belum menghitung", Toast.LENGTH_SHORT).show();
                return false;

            }
            if(strTotalZakat.isEmpty()){
                Toast.makeText(this, "Anda belum menghitung", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (edtNamaPem.getText().toString().isEmpty()){
                edtNamaPem.setError("Masukkan nama pemilik");
                Toast.makeText(this, "masukkan nama pemilik", Toast.LENGTH_SHORT).show();
                return false;
            }
            Mdl_ZM newMzmal= new Mdl_ZM();
            newMzmal.setNamaPemilik(edtNamaPem.getText().toString());
            newMzmal.setHrgEmas(strHargaEmas);
            newMzmal.setJmlHarta(strJmlHarta);
            newMzmal.settZakat(strTotalZakat);

            Db_helper mdb_helper = new Db_helper(Count_ZM.this);
            mdb_helper.saveZMal(newMzmal);



            //buat snack bar dengan aksi

            Snackbar snackbar = Snackbar.make(linearhasilmal, "Berhasil input data mal", Snackbar.LENGTH_LONG);
            snackbar.setAction("Ok", new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            snackbar.show();
            reset();





        }if (id==android.R.id.home){
            finish();
        }



        return true;
    }

    //inisiasi element layout
    public void initView(){
        edtJumlahHarta = findViewById(R.id.edtJmlKekayaan);
        edtHargaEmas=findViewById(R.id.edtHrgEmas);

        konfirm= findViewById(R.id.cbConfirm);
        btnHitung=findViewById(R.id.btnHitungZakatMal);
        btnReset=findViewById(R.id.btnresetZmal);

        nishabEmasSaatini =findViewById(R.id.txtNishabEmas);
        totalZakat=findViewById(R.id.txtTotalZMal);
        namaPem=findViewById(R.id.edtNp);

        //inisiasi linear hasil,
        linearhasilmal = findViewById(R.id.lineahasilmal);
        // nah ketika awal muncul hasilnya dijadikan invisibel biar yg keliatan hanya penghitungan saja
        linearhasilmal.setVisibility(View.INVISIBLE);


        //inisiasi edit nama pemilik
        edtNamaPem = findViewById(R.id.edtNp);


    }
}
