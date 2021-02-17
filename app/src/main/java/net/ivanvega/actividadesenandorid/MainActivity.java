package net.ivanvega.actividadesenandorid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.ivanvega.actividadesenandorid.data.DAOUsuarios;
import net.ivanvega.actividadesenandorid.data.DB;
import net.ivanvega.actividadesenandorid.data.Usuario;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent =  getIntent();

        Usuario usuarioAutenticado =
                (Usuario)intent.getSerializableExtra("user");

        ((TextView)findViewById(R.id.textView)).setText(usuarioAutenticado.getNombre());


        Toast.makeText(this, "Bienvenido " + usuarioAutenticado.getEmail() ,
                Toast.LENGTH_LONG).show();
        //Bryan OSwaldo Jiménez Guzmán

        DAOUsuarios dao = new DAOUsuarios(this);
        Cursor c = dao.getAllCursor();

        SimpleCursorAdapter simpleCursorAdapter  = new
                SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                c,
                new String[]{ DB.COLUMS_TABLEUSUARIOS[0], DB.COLUMS_TABLEUSUARIOS[2]}   ,
                new int[]{android.R.id.text1, android.R.id.text2 },
                SimpleCursorAdapter.NO_SELECTION
                );

        spn =  findViewById(R.id.spinner);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                 String email =  ((Cursor)  adapterView.getItemAtPosition(i)).getString(2);

                 String cadenaID = ((TextView)view.findViewById(android.R.id.text1)).getText().toString();
                  Toast.makeText(getApplicationContext(), cadenaID ,Toast.LENGTH_LONG).show();
                  Toast.makeText(getApplicationContext(), email ,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spn .setAdapter(simpleCursorAdapter);

    }


    public void btnIntentTel_click(View v){
    /*
        Uri number = Uri.parse("tel:5551234");
        Intent intent = new Intent(Intent.ACTION_DIAL);
        //Intent intent = new Intent(Intent.ACTION_DIAL, number);
        intent.setData(number);

        startActivity(intent);
        */
        Uri webpage = Uri.parse("https://www.android.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

                // Always use string resources for UI text.
        // This says something like "Share this photo with"
                //String title = getResources().getString(R.string.chooser_title);
        String title = "Mi titutlo";
        // Create intent to show chooser
                Intent chooser = Intent.createChooser(intent, title);


                // Verify the intent will resolve to at least one activity
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
        }


    }

}