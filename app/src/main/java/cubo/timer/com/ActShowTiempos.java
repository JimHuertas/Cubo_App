package cubo.timer.com;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cubo.timer.com.BaseDatos.BaseDatosOpenHelper;
import cubo.timer.com.BaseDatos.FeedReaderTimer.FeedEntry;

public class ActShowTiempos extends AppCompatActivity {
    private ListView lstDatos;
    private ArrayAdapter<String> adaptador;
    private ArrayList<String> tiempos;

    private SQLiteDatabase conexion;
    private BaseDatosOpenHelper datosOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tiempos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        actualizar();

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar_datos();
            }
        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar_todo();
            }
        });
    }


    private void actualizar(){
        lstDatos =(ListView) findViewById(R.id.lst_Datos);
        tiempos = new ArrayList<String>();
        String sTiempo;
        String sScramble;
        String sId;

        try {
            datosOpenHelper = new BaseDatosOpenHelper(this);
            conexion = datosOpenHelper.getWritableDatabase();
            String[] projection = {
                    FeedEntry.COLUMN_ID,
                    FeedEntry.COLUMN_SCR,
                    FeedEntry.COLUMN_TIME,
                    FeedEntry.COLUMN_DATETIME
            };

            Cursor resultado = conexion.query(
                    FeedEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            if (resultado.getCount() > 0) {
                resultado.moveToFirst();
                do {
                    sTiempo = resultado.getString(resultado.getColumnIndex(FeedEntry.COLUMN_TIME));
                    sScramble = resultado.getString(resultado.getColumnIndex(FeedEntry.COLUMN_SCR));
                    sId = resultado.getString(resultado.getColumnIndex(FeedEntry.COLUMN_ID));
                    tiempos.add(sTiempo + "(" + sId + ")" + "\n " + sScramble);
                } while (resultado.moveToNext());
                resultado.close();
            }
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tiempos);
            lstDatos.setAdapter(adaptador);
        } catch (Exception ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Precaucion");
            dlg.setMessage(ex.getMessage());
            dlg.show();
        }
    }

    //Eliminar un producto o articulo
    public void Eliminar_todo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Estas segurto que quieres eliminar todo");
        builder.setTitle("Precaucion");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                datosOpenHelper = new BaseDatosOpenHelper(ActShowTiempos.this);
                conexion = datosOpenHelper.getWritableDatabase();
                conexion.delete(FeedEntry.TABLE_NAME, null, null);
                Intent it = new Intent(ActShowTiempos.this, ActShowTiempos.class);
                startActivityForResult(it, 0);
                conexion.close();
            }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        AlertDialog dialog=builder.create();
        dialog.show();
        }




    public void borrar_elemento(String txt){
        datosOpenHelper = new BaseDatosOpenHelper(this);
        conexion = datosOpenHelper.getWritableDatabase();
        String producto = FeedEntry.COLUMN_ID + " LIKE \'" + txt +"\'";
        conexion.delete(FeedEntry.TABLE_NAME, producto, null);
        conexion.close();
        Intent it = new Intent(ActShowTiempos.this, ActShowTiempos.class);
        startActivityForResult(it, 0);
    }

    public void mostrar_datos(){

        BaseDatosOpenHelper db = new BaseDatosOpenHelper(this);
        final SQLiteDatabase sQlite = db.getReadableDatabase();
        EditText txt2 = findViewById(R.id.edtNombre);
        final String consulta = txt2.getText().toString();
        String mostrar = "";
        String[] devolver = {
                FeedEntry.COLUMN_SCR,
                FeedEntry.COLUMN_TIME,
                FeedEntry.COLUMN_DATETIME,
                FeedEntry.COLUMN_ID
        };

        final String producto = FeedEntry.COLUMN_ID + " LIKE \'" + consulta +"\'";

        final Cursor cursor = sQlite.query(
                FeedEntry.TABLE_NAME,
                devolver,
                producto,
                null,
                null,
                null,
                null
        );
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                String sTiem = cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_TIME));
                String sScram = cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_SCR));
                String sIds = cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_ID));
                String sDate = cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_DATETIME));
                mostrar="Tiempo: " + sTiem + "\nScramble: " + sScram + "\nId: " + sIds + "\nCuando ocurrio: " + sDate;
            } while (cursor.moveToNext());
        } else{
            mostrar="No se encontraron coinicidencias";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mostrar);
        builder.setTitle("Tiempo");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        if (mostrar != "No se encontraron coinicidencias"){
            builder.setNegativeButton("Borrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AlertDialog.Builder buil = new AlertDialog.Builder(ActShowTiempos.this);
                    buil.setMessage("¿Estas seguro que quieres borrarlo?");
                    buil.setTitle("Warning");
                    buil.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            borrar_elemento(consulta);

                        }
                    });
                    buil.setNeutralButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dia =buil.create();
                    dia.show();
                }
            });
        }
        AlertDialog dialog=builder.create();
        dialog.show();
    }

}