package com.example.a5_creacion_de_elementos_por_codigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.a5_creacion_de_elementos_por_codigo.databinding.ActivityAlumnoBinding;
import com.example.a5_creacion_de_elementos_por_codigo.modelos.Alumno;

public class AlumnoActivity extends AppCompatActivity {
    private ActivityAlumnoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_alumno);
        binding = ActivityAlumnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnAceptarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Añadir lo que escriben al alumno
                Alumno alumno = crearAlumno();

                if (alumno == null){
                    Toast.makeText(AlumnoActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }else{
                    //Enviar la informacion
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ALUMNO", alumno);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                }
                //Terminar
                finish();
            }
        });

    }

    private Alumno crearAlumno(){
        if (binding.txtNombreAlumno.getText().toString().isEmpty())
            return null;
        if (binding.txtApellidosAlumno.getText().toString().isEmpty())
            return null;
        if (binding.spCiclosAlumno.getSelectedItemPosition() == 0)
            return null;
        if (!binding.rbAAlumno.isChecked()
        && !binding.rbBAlumno.isChecked()
        && !binding.rbCAlumno.isChecked())
            return null;

        RadioButton rb = findViewById(binding.rgGrupoAlumno.getCheckedRadioButtonId());
        char letra = rb.getText().charAt(rb.getText().length()-1);

        Alumno alumno = new Alumno(
                binding.txtNombreAlumno.getText().toString(),
                binding.txtApellidosAlumno.getText().toString(),
                binding.spCiclosAlumno.getSelectedItem().toString(),
                letra
        );

        return alumno;
    }
}