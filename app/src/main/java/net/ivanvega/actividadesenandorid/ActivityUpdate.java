package net.ivanvega.actividadesenandorid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.ivanvega.actividadesenandorid.data.DAOUsuarios;
import net.ivanvega.actividadesenandorid.data.Usuario;

public class ActivityUpdate extends AppCompatActivity {
    TextView txtNombre, txtPass, txtEmail, txtTel;
    Button btnAgregar, btnCancelar;
    Spinner spnPaisds ;
    String[] arrPaises;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtPass = (TextView) findViewById(R.id.txtPassword);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtTel = (TextView) findViewById(R.id.txtTel);

        String botonOK = "Edit";
        btnAgregar = (Button) findViewById(R.id.btnGuardar);

        btnAgregar.setText(botonOK);

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                String pass = txtPass.getText().toString();
                String email = txtEmail.getText().toString();
                String tel = txtTel.getText().toString();
                DAOUsuarios dao =
                        new DAOUsuarios(getApplicationContext());
                Usuario usuarioAutenticado = dao.autenticar(new Usuario(
                                txtNombre.getText().toString(),
                                txtPass.getText().toString()
                        )
                );
                int id =(int) usuarioAutenticado.getID();
                Usuario nuevo =
                        new Usuario(id,nombre,tel,email,pass);

                if(dao.update(nuevo)){
                    Intent intent = new Intent();
                    intent.putExtra("Cliente actualizado", nuevo);
                    Toast.makeText(getApplicationContext(), "Objecto guardado dentro de Intent!", Toast.LENGTH_LONG).show();

                    setResult(Activity.RESULT_OK,  intent  );
                    finish();
                }else{
                    setResult(Activity.RESULT_CANCELED  );
                    finish();
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        spnPaisds = findViewById(R.id.spnPaises);

        arrPaises =  getResources().getStringArray(R.array.paises);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item,
                        android.R.id.text1,  arrPaises);

        spnPaisds.setAdapter(adapter);
    }
}
