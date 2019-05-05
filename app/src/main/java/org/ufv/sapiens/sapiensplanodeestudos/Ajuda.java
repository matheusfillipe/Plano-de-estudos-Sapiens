package org.ufv.sapiens.sapiensplanodeestudos;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Ajuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_ajuda);

        TextView textoDeAjuda = (TextView) findViewById(R.id.textView);

        textoDeAjuda.setTextColor(Color.rgb(0,0,0));
        textoDeAjuda.setTextSize(14);

        textoDeAjuda.setText("Este projeto foi desenvolvido em Novembro de 2018\n\n\n" +

                "Com o intuito de ajudar a organizar-se a matriz escolar de matérias no periodo letivo.");
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Ajuda.this, TelaInicial.class);
        startActivity(intent);
        finish();

    }
}
