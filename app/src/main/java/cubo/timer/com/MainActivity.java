package cubo.timer.com;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.style.BackgroundColorSpan;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.security.KeyStore;

import cubo.timer.com.BaseDatos.BaseDatosOpenHelper;
import cubo.timer.com.BaseDatos.FeedReaderTimer.FeedEntry;


public class MainActivity extends AppCompatActivity {
    Button btn_start, btn_historial, btn_tutorial;
    Chronometer chronometer;
    private boolean correr=false;
    long detenerse;
    private Scramble scramble;
    private TextView edtScramble;

    private SQLiteDatabase conexion;
    private BaseDatosOpenHelper datosOpenHelper;





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.crono);
        btn_start = findViewById(R.id.button);
        btn_historial = findViewById(R.id.button3);
        btn_tutorial = findViewById(R.id.button2);
        edtScramble = findViewById(R.id.textView);

        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                generar_scramble();
            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                startCrono();
            }
        });

        btn_historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, ActShowTiempos.class);
                startActivityForResult(it, 0);

            }
        });

        btn_tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, ActTutorial.class);
                startActivityForResult(it, 0);

            }
        });
    }

    private void guardar_item(Chronometer chronometer){
        datosOpenHelper = new BaseDatosOpenHelper(this);
        conexion = datosOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();



        values.put(FeedEntry.COLUMN_SCR, edtScramble.getText().toString().trim());
        values.put(FeedEntry.COLUMN_TIME, chronometer.getText().toString().trim());
        conexion.insert(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_ID, values);
        //conexion.delete(FeedEntry.TABLE_NAME,null,null);
        conexion.close();

    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startCrono(){

        if (!correr){
            chronometer.setBase(SystemClock.elapsedRealtime() - this.detenerse);
            chronometer.start();
            this.correr = true;
        } else{
            chronometer.stop();
            this.detenerse = SystemClock.elapsedRealtime() - chronometer.getBase();
            this.correr = false;
            this.detenerse = 0;
            guardar_item(chronometer);
            generar_scramble();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void movimiento_R() {
        Drawable r_1 =  findViewById(R.id.r1).getBackground();
        Drawable r_2 =  findViewById(R.id.r2).getBackground();
        Drawable r_8 =  findViewById(R.id.r8).getBackground();
        Drawable r_9 =  findViewById(R.id.r9).getBackground();
        findViewById(R.id.r1).setBackground(findViewById(R.id.r7).getBackground());
        findViewById(R.id.r2).setBackground(findViewById(R.id.r4).getBackground());
        findViewById(R.id.r8).setBackground(findViewById(R.id.r6).getBackground());
        findViewById(R.id.r9).setBackground(findViewById(R.id.r3).getBackground());
        findViewById(R.id.r3).setBackground(r_1);
        findViewById(R.id.r4).setBackground(r_8);
        findViewById(R.id.r6).setBackground(r_2);
        findViewById(R.id.r7).setBackground(r_9);


        Drawable v_3 = findViewById(R.id.v3).getBackground();
        Drawable v_6 = findViewById(R.id.v6).getBackground();
        Drawable v_9 = findViewById(R.id.v9).getBackground();
        Drawable az_1 = findViewById(R.id.az1).getBackground();
        Drawable az_4 = findViewById(R.id.az4).getBackground();
        Drawable az_7 = findViewById(R.id.az7).getBackground();
        //cara verde
        findViewById(R.id.v3).setBackground(findViewById(R.id.am3).getBackground());
        findViewById(R.id.v6).setBackground(findViewById(R.id.am6).getBackground());
        findViewById(R.id.v9).setBackground(findViewById(R.id.am9).getBackground());

        //cara azul
        findViewById(R.id.az1).setBackground(findViewById(R.id.b9).getBackground());
        findViewById(R.id.az4).setBackground(findViewById(R.id.b6).getBackground());
        findViewById(R.id.az7).setBackground(findViewById(R.id.b3).getBackground());

        //cara blanca
        findViewById(R.id.b3).setBackground(v_3);
        findViewById(R.id.b6).setBackground(v_6);
        findViewById(R.id.b9).setBackground(v_9);

        //cara amarillo
        findViewById(R.id.am3).setBackground(az_7);
        findViewById(R.id.am6).setBackground(az_4);
        findViewById(R.id.am9).setBackground(az_1);

        }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void movimiento_L() {
        //cara naranja
        Drawable n_1 =  findViewById(R.id.n1).getBackground();
        Drawable n_2 =  findViewById(R.id.n2).getBackground();
        Drawable n_8 =  findViewById(R.id.n8).getBackground();
        Drawable n_9 =  findViewById(R.id.n9).getBackground();
        findViewById(R.id.n1).setBackground(findViewById(R.id.n7).getBackground());
        findViewById(R.id.n2).setBackground(findViewById(R.id.n4).getBackground());
        findViewById(R.id.n8).setBackground(findViewById(R.id.n6).getBackground());
        findViewById(R.id.n9).setBackground(findViewById(R.id.n3).getBackground());
        findViewById(R.id.n3).setBackground(n_1);
        findViewById(R.id.n4).setBackground(n_8);
        findViewById(R.id.n6).setBackground(n_2);
        findViewById(R.id.n7).setBackground(n_9);


        Drawable v_1 = findViewById(R.id.v1).getBackground();
        Drawable v_4 = findViewById(R.id.v4).getBackground();
        Drawable v_7 = findViewById(R.id.v7).getBackground();
        Drawable az_3 = findViewById(R.id.az3).getBackground();
        Drawable az_6 = findViewById(R.id.az6).getBackground();
        Drawable az_9 = findViewById(R.id.az9).getBackground();
        //cara verde
        findViewById(R.id.v1).setBackground(findViewById(R.id.b1).getBackground());
        findViewById(R.id.v4).setBackground(findViewById(R.id.b4).getBackground());
        findViewById(R.id.v7).setBackground(findViewById(R.id.b7).getBackground());

        //cara azul
        findViewById(R.id.az3).setBackground(findViewById(R.id.am7).getBackground());
        findViewById(R.id.az6).setBackground(findViewById(R.id.am4).getBackground());
        findViewById(R.id.az9).setBackground(findViewById(R.id.am1).getBackground());

        //cara blanca
        findViewById(R.id.b1).setBackground(az_9);
        findViewById(R.id.b4).setBackground(az_6);
        findViewById(R.id.b7).setBackground(az_3);

        //cara amarillo
        findViewById(R.id.am1).setBackground(v_1);
        findViewById(R.id.am4).setBackground(v_4);
        findViewById(R.id.am7).setBackground(v_7);

    }

    private void movimiento_D() {
        //cara amarilla
        Drawable am_1 =  findViewById(R.id.am1).getBackground();
        Drawable am_2 =  findViewById(R.id.am2).getBackground();
        Drawable am_8 =  findViewById(R.id.am8).getBackground();
        Drawable am_9 =  findViewById(R.id.am9).getBackground();
        findViewById(R.id.am1).setBackground(findViewById(R.id.am7).getBackground());
        findViewById(R.id.am2).setBackground(findViewById(R.id.am4).getBackground());
        findViewById(R.id.am8).setBackground(findViewById(R.id.am6).getBackground());
        findViewById(R.id.am9).setBackground(findViewById(R.id.am3).getBackground());
        findViewById(R.id.am3).setBackground(am_1);
        findViewById(R.id.am4).setBackground(am_8);
        findViewById(R.id.am6).setBackground(am_2);
        findViewById(R.id.am7).setBackground(am_9);


        Drawable v_7 = findViewById(R.id.v7).getBackground();
        Drawable v_8 = findViewById(R.id.v8).getBackground();
        Drawable v_9 = findViewById(R.id.v9).getBackground();
        Drawable az_7= findViewById(R.id.az7).getBackground();
        Drawable az_8 = findViewById(R.id.az8).getBackground();
        Drawable az_9 = findViewById(R.id.az9).getBackground();
        //cara verde
        findViewById(R.id.v7).setBackground(findViewById(R.id.n7).getBackground());
        findViewById(R.id.v8).setBackground(findViewById(R.id.n8).getBackground());
        findViewById(R.id.v9).setBackground(findViewById(R.id.n9).getBackground());

        //cara azul
        findViewById(R.id.az7).setBackground(findViewById(R.id.r7).getBackground());
        findViewById(R.id.az8).setBackground(findViewById(R.id.r8).getBackground());
        findViewById(R.id.az9).setBackground(findViewById(R.id.r9).getBackground());

        //cara naranja
        findViewById(R.id.n7).setBackground(az_7);
        findViewById(R.id.n8).setBackground(az_8);
        findViewById(R.id.n9).setBackground(az_9);

        //cara roja
        findViewById(R.id.r7).setBackground(v_7);
        findViewById(R.id.r8).setBackground(v_8);
        findViewById(R.id.r9).setBackground(v_9);
    }

    private void movimiento_U() {
        //cara blanca
        Drawable b_1 =  findViewById(R.id.b1).getBackground();
        Drawable b_2 =  findViewById(R.id.b2).getBackground();
        Drawable b_8 =  findViewById(R.id.b8).getBackground();
        Drawable b_9 =  findViewById(R.id.b9).getBackground();
        findViewById(R.id.b1).setBackground(findViewById(R.id.b7).getBackground());
        findViewById(R.id.b2).setBackground(findViewById(R.id.b4).getBackground());
        findViewById(R.id.b8).setBackground(findViewById(R.id.b6).getBackground());
        findViewById(R.id.b9).setBackground(findViewById(R.id.b3).getBackground());
        findViewById(R.id.b3).setBackground(b_1);
        findViewById(R.id.b4).setBackground(b_8);
        findViewById(R.id.b6).setBackground(b_2);
        findViewById(R.id.b7).setBackground(b_9);


        Drawable v_1 = findViewById(R.id.v1).getBackground();
        Drawable v_2 = findViewById(R.id.v2).getBackground();
        Drawable v_3 = findViewById(R.id.v3).getBackground();
        Drawable az_1= findViewById(R.id.az1).getBackground();
        Drawable az_2 = findViewById(R.id.az2).getBackground();
        Drawable az_3 = findViewById(R.id.az3).getBackground();
        //cara verde
        findViewById(R.id.v1).setBackground(findViewById(R.id.r1).getBackground());
        findViewById(R.id.v2).setBackground(findViewById(R.id.r2).getBackground());
        findViewById(R.id.v3).setBackground(findViewById(R.id.r3).getBackground());

        //cara azul
        findViewById(R.id.az1).setBackground(findViewById(R.id.n1).getBackground());
        findViewById(R.id.az2).setBackground(findViewById(R.id.n2).getBackground());
        findViewById(R.id.az3).setBackground(findViewById(R.id.n3).getBackground());

        //cara naranja
        findViewById(R.id.n1).setBackground(v_1);
        findViewById(R.id.n2).setBackground(v_2);
        findViewById(R.id.n3).setBackground(v_3);

        //cara roja
        findViewById(R.id.r1).setBackground(az_1);
        findViewById(R.id.r2).setBackground(az_2);
        findViewById(R.id.r3).setBackground(az_3);
    }

    private void movimiento_F() {
        //cara verde
        Drawable v_1 =  findViewById(R.id.v1).getBackground();
        Drawable v_2 =  findViewById(R.id.v2).getBackground();
        Drawable v_8 =  findViewById(R.id.v8).getBackground();
        Drawable v_9 =  findViewById(R.id.v9).getBackground();
        findViewById(R.id.v8).setBackground(findViewById(R.id.v6).getBackground());
        findViewById(R.id.v9).setBackground(findViewById(R.id.v3).getBackground());
        findViewById(R.id.v1).setBackground(findViewById(R.id.v7).getBackground());
        findViewById(R.id.v2).setBackground(findViewById(R.id.v4).getBackground());
        findViewById(R.id.v3).setBackground(v_1);
        findViewById(R.id.v4).setBackground(v_8);
        findViewById(R.id.v6).setBackground(v_2);
        findViewById(R.id.v7).setBackground(v_9);

        Drawable b_7 = findViewById(R.id.b7).getBackground();
        Drawable b_8 = findViewById(R.id.b8).getBackground();
        Drawable b_9 = findViewById(R.id.b9).getBackground();
        Drawable am_1= findViewById(R.id.am1).getBackground();
        Drawable am_2 = findViewById(R.id.am2).getBackground();
        Drawable am_3 = findViewById(R.id.am3).getBackground();
        //cara blanca
        findViewById(R.id.b7).setBackground(findViewById(R.id.n9).getBackground());
        findViewById(R.id.b8).setBackground(findViewById(R.id.n6).getBackground());
        findViewById(R.id.b9).setBackground(findViewById(R.id.n3).getBackground());

        //cara amarilla
        findViewById(R.id.am1).setBackground(findViewById(R.id.r7).getBackground());
        findViewById(R.id.am2).setBackground(findViewById(R.id.r4).getBackground());
        findViewById(R.id.am3).setBackground(findViewById(R.id.r1).getBackground());

        //cara naranja
        findViewById(R.id.n3).setBackground(am_1);
        findViewById(R.id.n6).setBackground(am_2);
        findViewById(R.id.n9).setBackground(am_3);

        //cara roja
        findViewById(R.id.r1).setBackground(b_7);
        findViewById(R.id.r4).setBackground(b_8);
        findViewById(R.id.r7).setBackground(b_9);
    }

    private void movimiento_B() {
        //cara azul
        Drawable az_1 =  findViewById(R.id.az1).getBackground();
        Drawable az_2 =  findViewById(R.id.az2).getBackground();
        Drawable az_8 =  findViewById(R.id.az8).getBackground();
        Drawable az_9 =  findViewById(R.id.az9).getBackground();
        findViewById(R.id.az1).setBackground(findViewById(R.id.az7).getBackground());
        findViewById(R.id.az2).setBackground(findViewById(R.id.az4).getBackground());
        findViewById(R.id.az8).setBackground(findViewById(R.id.az6).getBackground());
        findViewById(R.id.az9).setBackground(findViewById(R.id.az3).getBackground());
        findViewById(R.id.az3).setBackground(az_1);
        findViewById(R.id.az4).setBackground(az_8);
        findViewById(R.id.az6).setBackground(az_2);
        findViewById(R.id.az7).setBackground(az_9);


        Drawable b_1 = findViewById(R.id.b1).getBackground();
        Drawable b_2 = findViewById(R.id.b2).getBackground();
        Drawable b_3 = findViewById(R.id.b3).getBackground();
        Drawable am_7 = findViewById(R.id.am7).getBackground();
        Drawable am_8 = findViewById(R.id.am8).getBackground();
        Drawable am_9 = findViewById(R.id.am9).getBackground();
        //cara blanca
        findViewById(R.id.b1).setBackground(findViewById(R.id.r3).getBackground());
        findViewById(R.id.b2).setBackground(findViewById(R.id.r6).getBackground());
        findViewById(R.id.b3).setBackground(findViewById(R.id.r9).getBackground());

        //cara amarillo
        findViewById(R.id.am7).setBackground(findViewById(R.id.n1).getBackground());
        findViewById(R.id.am8).setBackground(findViewById(R.id.n4).getBackground());
        findViewById(R.id.am9).setBackground(findViewById(R.id.n7).getBackground());

        //cara naranja
        findViewById(R.id.n1).setBackground(b_3);
        findViewById(R.id.n4).setBackground(b_2);
        findViewById(R.id.n7).setBackground(b_1);

        //cara roja
        findViewById(R.id.r3).setBackground(am_9);
        findViewById(R.id.r6).setBackground(am_8);
        findViewById(R.id.r9).setBackground(am_7);
    }

    public void resolver_cubo(){
        findViewById(R.id.b1).setBackground(findViewById(R.id.cara_blanca).getBackground());
        findViewById(R.id.b2).setBackground(findViewById(R.id.cara_blanca).getBackground());
        findViewById(R.id.b3).setBackground(findViewById(R.id.cara_blanca).getBackground());
        findViewById(R.id.b4).setBackground(findViewById(R.id.cara_blanca).getBackground());
        findViewById(R.id.b5).setBackground(findViewById(R.id.cara_blanca).getBackground());
        findViewById(R.id.b6).setBackground(findViewById(R.id.cara_blanca).getBackground());
        findViewById(R.id.b7).setBackground(findViewById(R.id.cara_blanca).getBackground());
        findViewById(R.id.b8).setBackground(findViewById(R.id.cara_blanca).getBackground());
        findViewById(R.id.b9).setBackground(findViewById(R.id.cara_blanca).getBackground());

        findViewById(R.id.am1).setBackground(findViewById(R.id.cara_amarilla).getBackground());
        findViewById(R.id.am2).setBackground(findViewById(R.id.cara_amarilla).getBackground());
        findViewById(R.id.am3).setBackground(findViewById(R.id.cara_amarilla).getBackground());
        findViewById(R.id.am4).setBackground(findViewById(R.id.cara_amarilla).getBackground());
        findViewById(R.id.am5).setBackground(findViewById(R.id.cara_amarilla).getBackground());
        findViewById(R.id.am6).setBackground(findViewById(R.id.cara_amarilla).getBackground());
        findViewById(R.id.am7).setBackground(findViewById(R.id.cara_amarilla).getBackground());
        findViewById(R.id.am8).setBackground(findViewById(R.id.cara_amarilla).getBackground());
        findViewById(R.id.am9).setBackground(findViewById(R.id.cara_amarilla).getBackground());

        findViewById(R.id.v1).setBackground(findViewById(R.id.cara_verde).getBackground());
        findViewById(R.id.v2).setBackground(findViewById(R.id.cara_verde).getBackground());
        findViewById(R.id.v3).setBackground(findViewById(R.id.cara_verde).getBackground());
        findViewById(R.id.v4).setBackground(findViewById(R.id.cara_verde).getBackground());
        findViewById(R.id.v5).setBackground(findViewById(R.id.cara_verde).getBackground());
        findViewById(R.id.v6).setBackground(findViewById(R.id.cara_verde).getBackground());
        findViewById(R.id.v7).setBackground(findViewById(R.id.cara_verde).getBackground());
        findViewById(R.id.v8).setBackground(findViewById(R.id.cara_verde).getBackground());
        findViewById(R.id.v9).setBackground(findViewById(R.id.cara_verde).getBackground());

        findViewById(R.id.az1).setBackground(findViewById(R.id.cara_azul).getBackground());
        findViewById(R.id.az2).setBackground(findViewById(R.id.cara_azul).getBackground());
        findViewById(R.id.az3).setBackground(findViewById(R.id.cara_azul).getBackground());
        findViewById(R.id.az4).setBackground(findViewById(R.id.cara_azul).getBackground());
        findViewById(R.id.az5).setBackground(findViewById(R.id.cara_azul).getBackground());
        findViewById(R.id.az6).setBackground(findViewById(R.id.cara_azul).getBackground());
        findViewById(R.id.az7).setBackground(findViewById(R.id.cara_azul).getBackground());
        findViewById(R.id.az8).setBackground(findViewById(R.id.cara_azul).getBackground());
        findViewById(R.id.az9).setBackground(findViewById(R.id.cara_azul).getBackground());

        findViewById(R.id.r1).setBackground(findViewById(R.id.cara_roja).getBackground());
        findViewById(R.id.r2).setBackground(findViewById(R.id.cara_roja).getBackground());
        findViewById(R.id.r3).setBackground(findViewById(R.id.cara_roja).getBackground());
        findViewById(R.id.r4).setBackground(findViewById(R.id.cara_roja).getBackground());
        findViewById(R.id.r5).setBackground(findViewById(R.id.cara_roja).getBackground());
        findViewById(R.id.r6).setBackground(findViewById(R.id.cara_roja).getBackground());
        findViewById(R.id.r7).setBackground(findViewById(R.id.cara_roja).getBackground());
        findViewById(R.id.r8).setBackground(findViewById(R.id.cara_roja).getBackground());
        findViewById(R.id.r9).setBackground(findViewById(R.id.cara_roja).getBackground());

        findViewById(R.id.n1).setBackground(findViewById(R.id.cara_naranja).getBackground());
        findViewById(R.id.n2).setBackground(findViewById(R.id.cara_naranja).getBackground());
        findViewById(R.id.n3).setBackground(findViewById(R.id.cara_naranja).getBackground());
        findViewById(R.id.n4).setBackground(findViewById(R.id.cara_naranja).getBackground());
        findViewById(R.id.n5).setBackground(findViewById(R.id.cara_naranja).getBackground());
        findViewById(R.id.n6).setBackground(findViewById(R.id.cara_naranja).getBackground());
        findViewById(R.id.n7).setBackground(findViewById(R.id.cara_naranja).getBackground());
        findViewById(R.id.n8).setBackground(findViewById(R.id.cara_naranja).getBackground());
        findViewById(R.id.n9).setBackground(findViewById(R.id.cara_naranja).getBackground());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void generar_scramble(){
        resolver_cubo();
        this.scramble = new Scramble();
        TextView text = findViewById(R.id.textView);
        TextView text_1;
        String csr1[] = scramble.get_array_scr();
        text.setText(scramble.get_cadena());


        for (String s : csr1) {
            //text.setText(s);
            if (s.charAt(0) == 'R') {
                movimiento_R();
                if (s.charAt(1) == '2') {
                    movimiento_R();
                } else if (s.charAt(1) == '\'') {
                    movimiento_R();
                    movimiento_R();
                }
            } else if (s.charAt(0) == 'L') {
                movimiento_L();
                if (s.charAt(1) == '2') {
                    movimiento_L();
                } else if (s.charAt(1) == '\'') {
                    movimiento_L();
                    movimiento_L();
                }
            } else if (s.charAt(0) == 'U') {
                movimiento_U();
                if (s.charAt(1) == '2') {
                    movimiento_U();
                } else if (s.charAt(1) == '\'') {
                    movimiento_U();
                    movimiento_U();
                }
            } else if (s.charAt(0) == 'D') {
                movimiento_D();
                if (s.charAt(1) == '2') {
                    movimiento_D();
                } else if (s.charAt(1) == '\'') {
                    movimiento_D();
                    movimiento_D();
                }
            } else if (s.charAt(0) == 'F') {
                movimiento_F();
                if (s.charAt(1) == '2') {
                    movimiento_F();
                } else if (s.charAt(1) == '\'') {
                    movimiento_F();
                    movimiento_F();
                }
            } else if (s.charAt(0) == 'B') {
                movimiento_B();
                if (s.charAt(1) == '2') {
                    movimiento_B();
                } else if (s.charAt(1) == '\'') {
                    movimiento_B();
                    movimiento_B();
                }
            }
        }
    }


}