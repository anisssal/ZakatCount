package com.iman.inc.zakatcount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iman.inc.zakatcount.model.Mdl_ZF;
import com.iman.inc.zakatcount.model.Mdl_ZM;

import java.util.ArrayList;

/**
 * Created by z on 21/01/18.
 */

public class Db_helper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "zakatdata";
//    private final static int DATABASE_VERSION = 1;
    //diatas adalah versi yg lama , karena kita menambah tabel baru yaitu tabel zakat mal, maka kita ubah versinya ke versi 2

    private final static int DATABASE_VERSION = 2;

    private final static String TABEL_ZF = "tbzf";

    private final static String FITRAH_ID = "id";
    private final static String COL_NAMAKEPKEL = "namaKK";
    private final static String COL_JUMLAHANGKEL = "jumlahAngkel";
    private final static String COL_TOTALZAKATFITRAH = "totalzakatfitrah";



    //nama tabel zmal
    private final static String TABLE_ZAKATMAL= "zakatmal";
    //kolom pada table zakat mal
    private final static String MAL_id = "id";
    private final static String COL_NAMA_PEMILIK = "nama";
    private final static String COL_JML_KEKAYAAN= "jmlkaya";
    private final static String COL_HARGA_EMAS= "hargaemas";
    private final static String COL_TOTAL_ZAKATMAL = "totalzakatmal";

    Context context;
    public Db_helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABEL_ZF+ " ("
        +FITRAH_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
        +COL_JUMLAHANGKEL+" INTEGER, "
        +COL_TOTALZAKATFITRAH+" INTEGER,"
        +COL_NAMAKEPKEL+" TEXT)");
        db.execSQL("create table "+ TABLE_ZAKATMAL +" ("
                + MAL_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_JML_KEKAYAAN+" TEXT, "
                +COL_HARGA_EMAS+" TEXT, "
                +COL_TOTAL_ZAKATMAL+" TEXT,"
                +COL_NAMA_PEMILIK+" TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABEL_ZF);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ZAKATMAL);
        onCreate(db);
    }


    public void saveDataZF(Mdl_ZF mdl_zf){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_JUMLAHANGKEL, mdl_zf.getJumlahKel());
        contentValues.put(COL_TOTALZAKATFITRAH, mdl_zf.getTotalZakat());
        contentValues.put(COL_NAMAKEPKEL, mdl_zf.getNamaKK());
        sqLiteDatabase.insert(TABEL_ZF, null, contentValues);
        sqLiteDatabase.close();

    }


    public ArrayList<Mdl_ZF> getAllDataFitr(){
        SQLiteDatabase sqLiteDatabase  =this.getReadableDatabase();
        String [] allColums = {
                FITRAH_ID,
                COL_JUMLAHANGKEL,
                COL_TOTALZAKATFITRAH,
                COL_NAMAKEPKEL
        };

        Cursor cursor = sqLiteDatabase.query(TABEL_ZF, allColums,
                null, null,null,null,null);

        ArrayList<Mdl_ZF> tempKembalian = new ArrayList<>();

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id= cursor.getInt(0);
                int jumlahKel = cursor.getInt(1);
                int totalZakat = cursor.getInt(2);
                String namaKK = cursor.getString(3);

                Mdl_ZF mdl_zf = new Mdl_ZF();
                mdl_zf.setId(id);
                mdl_zf.setJumlahKel(jumlahKel);
                mdl_zf.setTotalZakat(totalZakat);
                mdl_zf.setNamaKK(namaKK);
                tempKembalian.add(mdl_zf);
                cursor.moveToNext();

            }
        }
        sqLiteDatabase.close();
        return tempKembalian;



    }

    public  void deleteDataZfitr(Mdl_ZF mdl_zf){
        int id = mdl_zf.getId();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        sqLiteDatabase.delete(TABEL_ZF, FITRAH_ID+"="+id, null);
        /*sqLiteDatabase.delete(TABEL_ZF, FITRAH_ID+"=?",
                new String []{String.valueOf(id)});
*/
        sqLiteDatabase.close();

    }

    public void saveZMal(Mdl_ZM mdl_zm){
        SQLiteDatabase msqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_JML_KEKAYAAN, mdl_zm.getJmlHarta());
        contentValues.put(COL_HARGA_EMAS, mdl_zm.getHrgEmas());
        contentValues.put(COL_TOTAL_ZAKATMAL, mdl_zm.gettZakat());
        contentValues.put(COL_NAMA_PEMILIK, mdl_zm.getNamaPemilik());

        msqLiteDatabase.insert(TABLE_ZAKATMAL, null, contentValues);

        msqLiteDatabase.close();
    }

    public ArrayList <Mdl_ZM> getAllDataMal(){
        SQLiteDatabase msqLiteDatabase = this.getReadableDatabase();
        //array yg berisi semua column
        String [] Allcolumn = {
                MAL_id,
                COL_JML_KEKAYAAN,
                COL_HARGA_EMAS,
                COL_TOTAL_ZAKATMAL,
                COL_NAMA_PEMILIK};


        Cursor cursor = msqLiteDatabase.query(TABLE_ZAKATMAL, Allcolumn,
                null, null, null, null , null);
        ArrayList <Mdl_ZM> list = new ArrayList<>();

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){

                //get data from db base on columnindex
                int id = cursor.getInt(0);
                String jmlK = cursor.getString(1);
                String hargaEmas= cursor.getString(2);
                String totalZakat= cursor.getString(3);
                String namaPem = cursor.getString(4);

                //adding data to the model
                Mdl_ZM zakatMal = new Mdl_ZM();
                zakatMal.setId(id);
                zakatMal.setHrgEmas(jmlK);
                zakatMal.setHrgEmas(hargaEmas);
                zakatMal.settZakat(totalZakat);
                zakatMal.setNamaPemilik(namaPem);


                //add the model to the list
                list.add(zakatMal);
                cursor.moveToNext();
            }
        }
        msqLiteDatabase.close();
        return list;

    }


    public void deleteDataMal(Mdl_ZM mdl_zm){
        SQLiteDatabase msqLiteDatabase = this.getWritableDatabase();
        int id = mdl_zm.getId();
        msqLiteDatabase.delete(TABLE_ZAKATMAL, MAL_id+"="+id, null);
        msqLiteDatabase.close();
    }




}
