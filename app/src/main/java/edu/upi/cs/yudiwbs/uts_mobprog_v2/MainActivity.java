package edu.upi.cs.yudiwbs.uts_mobprog_v2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
perlu dilengkapi
 */
public class MainActivity extends AppCompatActivity {

    DbPegawai dbPeg;
    ArrayList<Pegawai> alPegawaiRV = new ArrayList();
    ArrayList<Pegawai> alPegawaiDB;
    OpenHelper handler = new OpenHelper(this);
    Pegawai pegawai = new Pegawai();

    RecyclerView rvPegawai;
    RecyclerView.LayoutManager lm;
    AdapterPegawai adapter;

    public void refreshIsiRV() {
        //refresh isi recyle view berdasarkan isi db
        alPegawaiDB = dbPeg.getAllPegawai();
        alPegawaiRV.clear(); //kosongkan rec view
        for (Pegawai peg:alPegawaiDB) {
            alPegawaiRV.add(peg);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbPeg = new DbPegawai(getApplicationContext());
        dbPeg.open();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        // lengkapi inisialisasi recycle view.
        // adapter, class pegawai, layout dsb sudah disediakan jadi tidak perlu dibuat

        //TextView tamToast = (TextView) findViewById(R.id.tvNama);
        rvPegawai = findViewById(R.id.rvPegawai);
        lm = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL,false);
        rvPegawai.setLayoutManager(lm);
        rvPegawai.setAdapter(adapter);

        //displayAllData(handler,pegawai);

        //jika sudah selesai dilengkapi panggil ini:
        //refreshIsiRV();

        /*tamToast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "NAMA : LUVI ZATNIKA NIM : 1501512",Toast.LENGTH_LONG).show();
            }
        });*/
    }

    /*private void displayAllData(OpenHelper handler, Pegawai profile) {
        rvPegawai.clear();
        List<Pegawai> list = handler.getAllDataPegawai();
        for (Pegawai profileModel : list) {
            String log = "NAMA : " + profileModel.getNama();
            System.out.println(log);

            //add to list
            rvPegawai.add(log);

            //set to the model

            profile.setNama(profileModel.getNama());

        }
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.mNimNama:
                //lengkapi
                Toast.makeText(getApplicationContext(), "NAMA : LUVI ZATNIKA NIM : 1501512", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.mTambahDB:
                //jangan diubah
                Toast.makeText(getApplicationContext(), "Tambah", Toast.LENGTH_LONG).show();
                dbPeg.insertPegawai("Ahmad");
                dbPeg.insertPegawai("Elfan");
                dbPeg.insertPegawai("Badu");
                refreshIsiRV();
                return true;

            case R.id.mTambahManual:
                Toast.makeText(getApplicationContext(), "TambahManual", Toast.LENGTH_LONG).show();
                dbPeg.insertPegawai("Manual Ahmad");
                dbPeg.insertPegawai("Manual Elfan");
                dbPeg.insertPegawai("Manual Badu");
                refreshIsiRV();
                adapter.notifyDataSetChanged();
                return true;

            case R.id.mUpdate:
                //jangan diubah
                Toast.makeText(getApplicationContext(), "Update Badu jadi Budi", Toast.LENGTH_LONG).show();
                dbPeg.updatePegawai("Badu","Budi");
                refreshIsiRV();
                return true;

            case R.id.mHapus:
                //jangan diubah
                Toast.makeText(getApplicationContext(), "Hapus Ahmad", Toast.LENGTH_LONG).show();
                dbPeg.deletePegawai("Ahmad");
                refreshIsiRV();
                return true;

            case R.id.mHapusSemua:
                dbPeg.deleteAll();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onDestroy() {
        dbPeg.close();
        super.onDestroy();
    }

}
