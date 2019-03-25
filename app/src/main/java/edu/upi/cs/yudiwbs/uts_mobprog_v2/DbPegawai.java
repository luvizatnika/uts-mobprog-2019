package edu.upi.cs.yudiwbs.uts_mobprog_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
/*
   perlu dilengkapi
 */
public class DbPegawai {


    private  SQLiteDatabase db;
    private final OpenHelper dbHelper;

    public DbPegawai(Context c) {
        dbHelper =  new OpenHelper(c);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    //HATI-HATI nama tabelnya PEGAWAI, bukan MAHASISWA

    public void updatePegawai(String namaAsal, String namaTujuan) {

    }

    public void deleteAll() {
        //lengkapi  lihat modul android db  halaman terakhir
    }


    public void deletePegawai(String nama) {
        //lengkapi lihat modul android db halaman terakhir
    }

    public long insertPegawai(String nama) {
        ContentValues newValues = new ContentValues();
        newValues.put("NAMA", nama);
        return db.insert("PEGAWAI", null, newValues);
        //return -1;
    }

    public ArrayList<Pegawai> getAllPegawai() {
        Cursor cur = null;
        ArrayList<Pegawai> out = new ArrayList<>();
        cur = db.rawQuery("SELECT nama FROM Pegawai Limit 10", null);
        if (cur.moveToFirst()) {
            do {
                Pegawai mhs = new Pegawai();
                mhs.nama = cur.getString(0);
                out.add(mhs);
            } while (cur.moveToNext());
        }
        cur.close();
        return out;
    }



}
