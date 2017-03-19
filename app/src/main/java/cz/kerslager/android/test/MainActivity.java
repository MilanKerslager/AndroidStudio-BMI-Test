package cz.kerslager.android.test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextVaha, editTextVyska;
    TextView textViewVysledek, textViewKategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextVaha = (EditText) findViewById(R.id.editTextVaha);
        editTextVyska = (EditText) findViewById(R.id.editTextVyska);
        textViewVysledek = (TextView) findViewById(R.id.textViewVysledek);
        textViewKategorie = (TextView) findViewById(R.id.textViewKategorie);
    }

    public void onButtonPress(View v) {
        String vahaS = editTextVaha.getText().toString();
        String vyskaS = editTextVyska.getText().toString();

        long vaha = 0, vyska = 0;

        try {
            vaha = Long.parseLong(vahaS);
            vyska = Long.parseLong(vyskaS);
        } catch (NumberFormatException e) {
            showToast("Chybný Vstup!");
        }

        if (vaha > 0 && vyska > 0) {
            float vysledek = (float) ((float) vaha / Math.pow((float) vyska / 100, 2));
            textViewVysledek.setText(Float.toString(vysledek));

            if (vysledek < 18.5) {
                textViewKategorie.setText(getResources().getString(R.string.podvyziva));
                textViewKategorie.setTextColor(Color.RED);
            } else if (vysledek < 25) {
                textViewKategorie.setText(getResources().getString(R.string.ideal));
                textViewKategorie.setTextColor(Color.GREEN);
            } else {
                textViewKategorie.setText(getResources().getString(R.string.nadvaha));
                textViewKategorie.setTextColor(Color.RED);
            }

        }
    }

    private void showToast(String s) {
        Toast myToast = Toast.makeText(
                getApplication(), s,
                Toast.LENGTH_LONG
        );
        myToast.show();
    }
}
