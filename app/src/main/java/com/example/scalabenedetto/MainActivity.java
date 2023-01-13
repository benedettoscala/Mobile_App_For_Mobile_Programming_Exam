package com.example.scalabenedetto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Button azzera;
    Button[][] bottoni;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottoni = new Button[3][3];

        //assegna ad ogni bottone la sua posizione
        bottoni[0][0] = findViewById(R.id.button1);
        bottoni[0][1] = findViewById(R.id.button2);
        bottoni[0][2] = findViewById(R.id.button3);
        bottoni[1][0] = findViewById(R.id.button4);
        bottoni[1][1] = findViewById(R.id.button5);
        bottoni[1][2] = findViewById(R.id.button6);
        bottoni[2][0] = findViewById(R.id.button7);
        bottoni[2][1] = findViewById(R.id.button8);
        bottoni[2][2] = findViewById(R.id.button9);

        azzera = findViewById(R.id.azzera);

        //recupera lo stato dei bottoni
        if (savedInstanceState != null) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Integer stato = Integer.parseInt(savedInstanceState.getString("bottone" + i + j));
                    stato++;
                    bottoni[i][j].setText(stato.toString());
                }
            }
        }

        aSwitch = findViewById(R.id.switch1);;

        //assegna x e y ad ogni bottone usando il tag
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                bottoni[i][j].setTag(i*10+j);
            }
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;

                int numeroColonna = getPosizione(b)[1];
                int numeroRiga = getPosizione(b)[0];
                System.out.println("numeroColonna: " + numeroColonna);
                System.out.println("numeroRiga: " + numeroRiga);
                if(aSwitch.isChecked()){
                    cambiaColonne(numeroColonna);
                } else {
                    cambiaRighe(numeroRiga);
                }
            }
        };



        //assegna ad ogni bottone il suo listener
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bottoni[i][j].setOnClickListener(listener);
            }
        }

        //azzera i bottoni
        azzera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        bottoni[i][j].setText("0");
                    }
                }
            }
        });
    }



    //aggiungi 1 a tutti i bottoni di  una colonna
    private void cambiaColonne(int colonna) {
        for (int i = 0; i < 3; i++) {
            int valore = Integer.parseInt(bottoni[i][colonna].getText().toString());
            valore++;
            bottoni[i][colonna].setText(String.valueOf(valore));
        }
    }

    //aggiungi 1 a tutti i bottoni di una riga
    public void cambiaRighe(int riga){
        for (int i = 0; i < 3; i++) {
            int valore = Integer.parseInt(bottoni[riga][i].getText().toString());
            valore++;
            bottoni[riga][i].setText(String.valueOf(valore));
        }
    }

    //metodo che recupera la posizione del bottone dal tag
    public int[] getPosizione(Button b){
        int[] posizione = new int[2];
        int tag = (Integer) b.getTag();
        posizione[0] = tag/10;
        posizione[1] = tag%10;
        return posizione;
    }

    //In on save instance si va a salvare lo stato dei bottoni
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        //salva lo stato dei bottoni
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                outState.putString("bottone" + i + j, bottoni[i][j].getText().toString());
            }
        }
        super.onSaveInstanceState(outState);
    }
}