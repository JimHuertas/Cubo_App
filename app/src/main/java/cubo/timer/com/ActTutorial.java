package cubo.timer.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActTutorial extends AppCompatActivity {

    private Spinner spinner1;
    private FrameLayout frame;
    private ImageView image;
    private Button boton_selec;
    FloatingActionButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tutorial);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        frame = findViewById(R.id.frameLayout4);
        image = findViewById(R.id.imageView10);
        boton_selec = findViewById(R.id.button6);
        btn_back = findViewById(R.id.btn_volver);

        String [] opciones = {"Ninguno","R", "L", "F", "B", "U", "D"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);

        boton_selec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //Metodo del boton :D
    public void mostrar(){
        String seleccion = spinner1.getSelectedItem().toString();
        if (seleccion.equals("R")){
            findViewById(R.id.frameLayout4).setBackground(findViewById(R.id.frame_sp).getBackground());
            image.setImageResource(R.drawable.movr);
            image.setVisibility(View.VISIBLE);
        }else if (seleccion.equals("Ninguno")){
            frame.setBackground(null);
            image.setVisibility(View.GONE);
        }else if (seleccion.equals("L")){
            findViewById(R.id.frameLayout4).setBackground(findViewById(R.id.frame_sp).getBackground());
            image.setImageResource(R.drawable.movl);
            image.setVisibility(View.VISIBLE);
        }else if (seleccion.equals("F")){
            findViewById(R.id.frameLayout4).setBackground(findViewById(R.id.frame_sp).getBackground());
            image.setImageResource(R.drawable.movf);
            image.setVisibility(View.VISIBLE);
        }else if (seleccion.equals("B")){
            findViewById(R.id.frameLayout4).setBackground(findViewById(R.id.frame_sp).getBackground());
            image.setImageResource(R.drawable.movb);
            image.setVisibility(View.VISIBLE);
        }else if (seleccion.equals("D")){
            findViewById(R.id.frameLayout4).setBackground(findViewById(R.id.frame_sp).getBackground());
            image.setImageResource(R.drawable.movd);
            image.setVisibility(View.VISIBLE);
        }else if (seleccion.equals("U")){
            findViewById(R.id.frameLayout4).setBackground(findViewById(R.id.frame_sp).getBackground());
            image.setImageResource(R.drawable.movu);
            image.setVisibility(View.VISIBLE);
        }
    }

}