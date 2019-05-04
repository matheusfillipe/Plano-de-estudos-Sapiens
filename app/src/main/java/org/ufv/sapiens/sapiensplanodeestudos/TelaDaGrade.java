package org.ufv.sapiens.sapiensplanodeestudos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Processador.PlanoDeEstudos;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Historico;

public class TelaDaGrade extends AppCompatActivity {

    boolean apertado[] = {false, false, false, false, false, false, false, false};

    int checked[] = {1,1,1,1,1,1,1,1};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_da_grade);

        TextView texto[] = {
                findViewById(R.id.materiaFazendo1),
                findViewById(R.id.materiaFazendo2),
                findViewById(R.id.materiaFazendo3),
                findViewById(R.id.materiaFazendo4),
                findViewById(R.id.materiaFazendo5),
                findViewById(R.id.materiaFazendo6),
                findViewById(R.id.materiaFazendo7),
                findViewById(R.id.materiaFazendo8)
        };

        Button opcoes[] = {

                findViewById(R.id.buttonM1),
                findViewById(R.id.buttonM2),
                findViewById(R.id.buttonM3),
                findViewById(R.id.buttonM4),
                findViewById(R.id.buttonM5),
                findViewById(R.id.buttonM6),
                findViewById(R.id.buttonM7),
                findViewById(R.id.buttonM8)
        };

        TextView textoFim[] = {
                findViewById(R.id.materiaProximoPeriodo1),
                findViewById(R.id.materiaProximoPeriodo2),
                findViewById(R.id.materiaProximoPeriodo3),
                findViewById(R.id.materiaProximoPeriodo4),
                findViewById(R.id.materiaProximoPeriodo5),
                findViewById(R.id.materiaProximoPeriodo6),
                findViewById(R.id.materiaProximoPeriodo7),
                findViewById(R.id.materiaProximoPeriodo8)
        };

        TextView mAtuais = (TextView) findViewById(R.id.materias);

        mAtuais.setTextColor(Color.rgb(0, 0, 0));

        mAtuais.setTextSize(16);

        mAtuais.setText("Matérias atuais:");

        for (int i = 0; i < 8; i++) {

            texto[i].setBackgroundColor(Color.rgb(220,220,220));
            texto[i].setText(" " + Historico.materiasFazendo[i] + " ");

            opcoes[i].setBackgroundColor(Color.TRANSPARENT);

            textoFim[i].setBackgroundColor(Color.rgb(220,220,220));
            textoFim[i].setText(" " + PlanoDeEstudos.materiaProximoPeriodo[i] + " ");

        }


        TextView mProximo = (TextView) findViewById(R.id.materiasProximoPeriodo);


        mProximo.setTextColor(Color.rgb(0, 0, 0));
        mProximo.setTextSize(16);


        mProximo.setText("Matérias para os próximos periodos:");


        Button botaoM1 = (Button) findViewById(R.id.buttonM1);

        botaoM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean apertar = false;

                for (int i = 0; i < 8; i++) {

                    if (apertado[i] == true && i != 0) {

                        apertar = false;
                        break;

                    } else if (apertado[i] == false && i != 0) {
                        apertar = true;
                    }

                }

                if (apertado[0] == false && apertar == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB1JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB1VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB1NaoPasso);

                    checkJaPassei.setTextColor(Color.BLACK);
                    checkVouPassar.setTextColor(Color.BLACK);
                    checkNaoPasso.setTextColor(Color.BLACK);

                    checkJaPassei.setText("Já passei    ");
                    checkVouPassar.setText("Vou Passar ");
                    checkNaoPasso.setText("Não passo  ");

                    checkJaPassei.setBackgroundColor(Color.TRANSPARENT);
                    checkVouPassar.setBackgroundColor(Color.TRANSPARENT);
                    checkNaoPasso.setBackgroundColor(Color.TRANSPARENT);

                    checkJaPassei.setVisibility(View.VISIBLE);
                    checkVouPassar.setVisibility(View.VISIBLE);
                    checkNaoPasso.setVisibility(View.VISIBLE);

                    Button botao = (Button) findViewById(R.id.buttonM1);

                    botao.setText("v");

                    apertado[0] = true;

                } else if (apertado[0] == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB1JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB1VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB1NaoPasso);

                    checkJaPassei.setVisibility(View.GONE);
                    checkVouPassar.setVisibility(View.GONE);
                    checkNaoPasso.setVisibility(View.GONE);

                    Button botao = (Button) findViewById(R.id.buttonM1);

                    CheckBox jaPassei = (CheckBox) findViewById(R.id.checkBoxB1JaPassei);
                    CheckBox vouPassar = (CheckBox) findViewById(R.id.checkBoxB1VouPassar);
                    CheckBox naoPasso = (CheckBox) findViewById(R.id.checkBoxB1NaoPasso);

                    if(jaPassei.isChecked()){
                        checked[0] = 1;
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }else if(vouPassar.isChecked()){
                        checked[0] = 2;
                        jaPassei.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    } else if(naoPasso.isChecked()){
                        checked[0] = 3;
                        jaPassei.setChecked(false);
                        vouPassar.setChecked(false);
                        botao.setText(">");
                    } else {
                        checked[0] = 1;
                        jaPassei.setChecked(true);
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }

                    apertado[0] = false;

                }


            }
        });

        Button botaoM2 = (Button) findViewById(R.id.buttonM2);

        botaoM2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean apertar = false;

                for (int i = 0; i < 8; i++) {

                    if (apertado[i] == true && i != 1) {

                        apertar = false;
                        break;

                    } else if (apertado[i] == false && i != 1) {
                        apertar = true;
                    }

                }

                if (apertado[1] == false && apertar == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB2JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB2VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB2NaoPasso);

                    checkJaPassei.setTextColor(Color.BLACK);
                    checkVouPassar.setTextColor(Color.BLACK);
                    checkNaoPasso.setTextColor(Color.BLACK);

                    checkJaPassei.setText("Já passei    ");
                    checkVouPassar.setText("Vou Passar ");
                    checkNaoPasso.setText("Não passo  ");

                    checkJaPassei.setBackgroundColor(Color.TRANSPARENT);
                    checkVouPassar.setBackgroundColor(Color.TRANSPARENT);
                    checkNaoPasso.setBackgroundColor(Color.TRANSPARENT);

                    checkJaPassei.setVisibility(View.VISIBLE);
                    checkVouPassar.setVisibility(View.VISIBLE);
                    checkNaoPasso.setVisibility(View.VISIBLE);

                    Button botao = (Button) findViewById(R.id.buttonM2);

                    botao.setText("v");

                    apertado[1] = true;

                } else if (apertado[1] == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB2JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB2VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB2NaoPasso);

                    checkJaPassei.setVisibility(View.GONE);
                    checkVouPassar.setVisibility(View.GONE);
                    checkNaoPasso.setVisibility(View.GONE);

                    Button botao = (Button) findViewById(R.id.buttonM2);

                    CheckBox jaPassei = (CheckBox) findViewById(R.id.checkBoxB2JaPassei);
                    CheckBox vouPassar = (CheckBox) findViewById(R.id.checkBoxB2VouPassar);
                    CheckBox naoPasso = (CheckBox) findViewById(R.id.checkBoxB2NaoPasso);

                    if(jaPassei.isChecked()){
                        checked[1] = 1;
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }else if(vouPassar.isChecked()){
                        checked[1] = 2;
                        jaPassei.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    } else if(naoPasso.isChecked()){
                        checked[1] = 3;
                        jaPassei.setChecked(false);
                        vouPassar.setChecked(false);
                        botao.setText(">");
                    } else {
                        checked[1] = 1;
                        jaPassei.setChecked(true);
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }

                    apertado[1] = false;

                }

            }
        });

        Button botaoM3 = (Button) findViewById(R.id.buttonM3);


        botaoM3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean apertar = false;

                for (int i = 0; i < 8; i++) {

                    if (apertado[i] == true && i != 2) {

                        apertar = false;
                        break;

                    } else if (apertado[i] == false && i != 2) {
                        apertar = true;
                    }

                }

                if (apertado[2] == false && apertar == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB3JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB3VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB3NaoPasso);

                    checkJaPassei.setTextColor(Color.BLACK);
                    checkVouPassar.setTextColor(Color.BLACK);
                    checkNaoPasso.setTextColor(Color.BLACK);

                    checkJaPassei.setText("Já passei    ");
                    checkVouPassar.setText("Vou Passar ");
                    checkNaoPasso.setText("Não passo  ");

                    checkJaPassei.setBackgroundColor(Color.TRANSPARENT);
                    checkVouPassar.setBackgroundColor(Color.TRANSPARENT);
                    checkNaoPasso.setBackgroundColor(Color.TRANSPARENT);

                    checkJaPassei.setVisibility(View.VISIBLE);
                    checkVouPassar.setVisibility(View.VISIBLE);
                    checkNaoPasso.setVisibility(View.VISIBLE);

                    Button botao = (Button) findViewById(R.id.buttonM3);

                    botao.setText("v");

                    apertado[2] = true;

                } else if (apertado[2] == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB3JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB3VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB3NaoPasso);

                    checkJaPassei.setVisibility(View.GONE);
                    checkVouPassar.setVisibility(View.GONE);
                    checkNaoPasso.setVisibility(View.GONE);

                    Button botao = (Button) findViewById(R.id.buttonM3);

                    CheckBox jaPassei = (CheckBox) findViewById(R.id.checkBoxB3JaPassei);
                    CheckBox vouPassar = (CheckBox) findViewById(R.id.checkBoxB3VouPassar);
                    CheckBox naoPasso = (CheckBox) findViewById(R.id.checkBoxB3NaoPasso);

                    if(jaPassei.isChecked()){
                        checked[2] = 1;
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }else if(vouPassar.isChecked()){
                        checked[2] = 2;
                        jaPassei.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    } else if(naoPasso.isChecked()){
                        checked[2] = 3;
                        jaPassei.setChecked(false);
                        vouPassar.setChecked(false);
                        botao.setText(">");
                    } else {
                        checked[2] = 1;
                        jaPassei.setChecked(true);
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }

                    apertado[2] = false;

                }

            }
        });

        Button botaoM4 = (Button) findViewById(R.id.buttonM4);


        botaoM4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean apertar = false;

                for (int i = 0; i < 8; i++) {

                    if (apertado[i] == true && i != 3) {

                        apertar = false;
                        break;

                    } else if (apertado[i] == false && i != 3) {
                        apertar = true;
                    }

                }

                if (apertado[3] == false && apertar == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB4JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB4VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB4NaoPasso);

                    checkJaPassei.setTextColor(Color.BLACK);
                    checkVouPassar.setTextColor(Color.BLACK);
                    checkNaoPasso.setTextColor(Color.BLACK);

                    checkJaPassei.setText("Já passei    ");
                    checkVouPassar.setText("Vou Passar ");
                    checkNaoPasso.setText("Não passo  ");

                    checkJaPassei.setBackgroundColor(Color.TRANSPARENT);
                    checkVouPassar.setBackgroundColor(Color.TRANSPARENT);
                    checkNaoPasso.setBackgroundColor(Color.TRANSPARENT);

                    checkJaPassei.setVisibility(View.VISIBLE);
                    checkVouPassar.setVisibility(View.VISIBLE);
                    checkNaoPasso.setVisibility(View.VISIBLE);

                    Button botao = (Button) findViewById(R.id.buttonM4);

                    botao.setText("v");

                    apertado[3] = true;

                } else if (apertado[3] == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB4JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB4VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB4NaoPasso);

                    checkJaPassei.setVisibility(View.GONE);
                    checkVouPassar.setVisibility(View.GONE);
                    checkNaoPasso.setVisibility(View.GONE);

                    Button botao = (Button) findViewById(R.id.buttonM4);

                    CheckBox jaPassei = (CheckBox) findViewById(R.id.checkBoxB4JaPassei);
                    CheckBox vouPassar = (CheckBox) findViewById(R.id.checkBoxB4VouPassar);
                    CheckBox naoPasso = (CheckBox) findViewById(R.id.checkBoxB4NaoPasso);

                    if(jaPassei.isChecked()){
                        checked[3] = 1;
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }else if(vouPassar.isChecked()){
                        checked[3] = 2;
                        jaPassei.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    } else if(naoPasso.isChecked()){
                        checked[3] = 3;
                        jaPassei.setChecked(false);
                        vouPassar.setChecked(false);
                        botao.setText(">");
                    } else {
                        checked[3] = 1;
                        jaPassei.setChecked(true);
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }

                    apertado[3] = false;

                }

            }
        });

        Button botaoM5 = (Button) findViewById(R.id.buttonM5);


        botaoM5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean apertar = false;

                for (int i = 0; i < 8; i++) {

                    if (apertado[i] == true && i != 4) {

                        apertar = false;
                        break;

                    } else if (apertado[i] == false && i != 4) {
                        apertar = true;
                    }

                }

                if (apertado[4] == false && apertar == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB5JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB5VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB5NaoPasso);

                    checkJaPassei.setTextColor(Color.BLACK);
                    checkVouPassar.setTextColor(Color.BLACK);
                    checkNaoPasso.setTextColor(Color.BLACK);

                    checkJaPassei.setText("Já passei  ");
                    checkVouPassar.setText("Vou Passar ");
                    checkNaoPasso.setText("Não passo  ");

                    checkJaPassei.setBackgroundColor(Color.TRANSPARENT);
                    checkVouPassar.setBackgroundColor(Color.TRANSPARENT);
                    checkNaoPasso.setBackgroundColor(Color.TRANSPARENT);

                    checkJaPassei.setVisibility(View.VISIBLE);
                    checkVouPassar.setVisibility(View.VISIBLE);
                    checkNaoPasso.setVisibility(View.VISIBLE);

                    Button botao = (Button) findViewById(R.id.buttonM5);

                    botao.setText("v");

                    apertado[4] = true;

                } else if (apertado[4] == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB5JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB5VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB5NaoPasso);

                    checkJaPassei.setVisibility(View.GONE);
                    checkVouPassar.setVisibility(View.GONE);
                    checkNaoPasso.setVisibility(View.GONE);

                    Button botao = (Button) findViewById(R.id.buttonM5);

                    CheckBox jaPassei = (CheckBox) findViewById(R.id.checkBoxB5JaPassei);
                    CheckBox vouPassar = (CheckBox) findViewById(R.id.checkBoxB5VouPassar);
                    CheckBox naoPasso = (CheckBox) findViewById(R.id.checkBoxB5NaoPasso);

                    if(jaPassei.isChecked()){
                        checked[4] = 1;
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }else if(vouPassar.isChecked()){
                        checked[4] = 2;
                        jaPassei.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    } else if(naoPasso.isChecked()){
                        checked[4] = 3;
                        jaPassei.setChecked(false);
                        vouPassar.setChecked(false);
                        botao.setText(">");
                    } else {
                        checked[4] = 1;
                        jaPassei.setChecked(true);
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }

                    apertado[4] = false;

                }

            }
        });

        Button botaoM6 = (Button) findViewById(R.id.buttonM6);


        botaoM6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean apertar = false;

                for (int i = 0; i < 8; i++) {

                    if (apertado[i] == true && i != 5) {

                        apertar = false;
                        break;

                    } else if (apertado[i] == false && i != 5) {
                        apertar = true;
                    }

                }

                if (apertado[5] == false && apertar == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB6JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB6VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB6NaoPasso);

                    checkJaPassei.setTextColor(Color.BLACK);
                    checkVouPassar.setTextColor(Color.BLACK);
                    checkNaoPasso.setTextColor(Color.BLACK);

                    checkJaPassei.setText("Já passei    ");
                    checkVouPassar.setText("Vou Passar ");
                    checkNaoPasso.setText("Não passo    ");

                    checkJaPassei.setBackgroundColor(Color.TRANSPARENT);
                    checkVouPassar.setBackgroundColor(Color.TRANSPARENT);
                    checkNaoPasso.setBackgroundColor(Color.TRANSPARENT);

                    checkJaPassei.setVisibility(View.VISIBLE);
                    checkVouPassar.setVisibility(View.VISIBLE);
                    checkNaoPasso.setVisibility(View.VISIBLE);

                    Button botao = (Button) findViewById(R.id.buttonM6);

                    botao.setText("v");

                    apertado[5] = true;

                } else if (apertado[5] == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB6JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB6VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB6NaoPasso);

                    checkJaPassei.setVisibility(View.GONE);
                    checkVouPassar.setVisibility(View.GONE);
                    checkNaoPasso.setVisibility(View.GONE);

                    Button botao = (Button) findViewById(R.id.buttonM6);

                    CheckBox jaPassei = (CheckBox) findViewById(R.id.checkBoxB6JaPassei);
                    CheckBox vouPassar = (CheckBox) findViewById(R.id.checkBoxB6VouPassar);
                    CheckBox naoPasso = (CheckBox) findViewById(R.id.checkBoxB6NaoPasso);

                    if(jaPassei.isChecked()){
                        checked[5] = 1;
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }else if(vouPassar.isChecked()){
                        checked[5] = 2;
                        jaPassei.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    } else if(naoPasso.isChecked()){
                        checked[5] = 3;
                        jaPassei.setChecked(false);
                        vouPassar.setChecked(false);
                        botao.setText(">");
                    } else {
                        checked[5] = 1;
                        jaPassei.setChecked(true);
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }

                    apertado[5] = false;

                }

            }
        });

        Button botaoM7 = (Button) findViewById(R.id.buttonM7);


        botaoM7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean apertar = false;

                for (int i = 0; i < 8; i++) {

                    if (apertado[i] == true && i != 6) {

                        apertar = false;
                        break;

                    } else if (apertado[i] == false && i != 6) {
                        apertar = true;
                    }

                }

                if (apertado[6] == false && apertar == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB7JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB7VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB7NaoPasso);

                    checkJaPassei.setTextColor(Color.BLACK);
                    checkVouPassar.setTextColor(Color.BLACK);
                    checkNaoPasso.setTextColor(Color.BLACK);

                    checkJaPassei.setText("Já passei    ");
                    checkVouPassar.setText("Vou Passar ");
                    checkNaoPasso.setText("Não passo  ");

                    checkJaPassei.setBackgroundColor(Color.TRANSPARENT);
                    checkVouPassar.setBackgroundColor(Color.TRANSPARENT);
                    checkNaoPasso.setBackgroundColor(Color.TRANSPARENT);

                    checkJaPassei.setVisibility(View.VISIBLE);
                    checkVouPassar.setVisibility(View.VISIBLE);
                    checkNaoPasso.setVisibility(View.VISIBLE);

                    Button botao = (Button) findViewById(R.id.buttonM7);

                    botao.setText("v");

                    apertado[6] = true;

                } else if (apertado[6] == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB7JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB7VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB7NaoPasso);

                    checkJaPassei.setVisibility(View.GONE);
                    checkVouPassar.setVisibility(View.GONE);
                    checkNaoPasso.setVisibility(View.GONE);

                    Button botao = (Button) findViewById(R.id.buttonM7);

                    CheckBox jaPassei = (CheckBox) findViewById(R.id.checkBoxB7JaPassei);
                    CheckBox vouPassar = (CheckBox) findViewById(R.id.checkBoxB7VouPassar);
                    CheckBox naoPasso = (CheckBox) findViewById(R.id.checkBoxB7NaoPasso);

                    if(jaPassei.isChecked()){
                        checked[6] = 1;
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }else if(vouPassar.isChecked()){
                        checked[6] = 2;
                        jaPassei.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    } else if(naoPasso.isChecked()){
                        checked[6] = 3;
                        jaPassei.setChecked(false);
                        vouPassar.setChecked(false);
                        botao.setText(">");
                    } else {
                        checked[6] = 1;
                        jaPassei.setChecked(true);
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }

                    apertado[6] = false;

                }

            }
        });

        Button botaoM8 = (Button) findViewById(R.id.buttonM8);


        botaoM8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean apertar = false;

                for (int i = 0; i < 8; i++) {

                    if (apertado[i] == true && i != 7) {

                        apertar = false;
                        break;

                    } else if (apertado[i] == false && i != 7) {
                        apertar = true;
                    }

                }

                if (apertado[7] == false && apertar == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB8JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB8VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB8NaoPasso);

                    checkJaPassei.setTextColor(Color.BLACK);
                    checkVouPassar.setTextColor(Color.BLACK);
                    checkNaoPasso.setTextColor(Color.BLACK);

                    checkJaPassei.setText("Já passei    ");
                    checkVouPassar.setText("Vou Passar ");
                    checkNaoPasso.setText("Não passo  ");

                    checkJaPassei.setBackgroundColor(Color.TRANSPARENT);
                    checkVouPassar.setBackgroundColor(Color.TRANSPARENT);
                    checkNaoPasso.setBackgroundColor(Color.TRANSPARENT);

                    checkJaPassei.setVisibility(View.VISIBLE);
                    checkVouPassar.setVisibility(View.VISIBLE);
                    checkNaoPasso.setVisibility(View.VISIBLE);

                    Button botao = (Button) findViewById(R.id.buttonM8);

                    botao.setText("v");

                    apertado[7] = true;

                } else if (apertado[7] == true) {

                    CheckBox checkJaPassei = (CheckBox) findViewById(R.id.checkBoxB8JaPassei);
                    CheckBox checkVouPassar = (CheckBox) findViewById(R.id.checkBoxB8VouPassar);
                    CheckBox checkNaoPasso = (CheckBox) findViewById(R.id.checkBoxB8NaoPasso);

                    checkJaPassei.setVisibility(View.GONE);
                    checkVouPassar.setVisibility(View.GONE);
                    checkNaoPasso.setVisibility(View.GONE);

                    Button botao = (Button) findViewById(R.id.buttonM8);

                    CheckBox jaPassei = (CheckBox) findViewById(R.id.checkBoxB8JaPassei);
                    CheckBox vouPassar = (CheckBox) findViewById(R.id.checkBoxB8VouPassar);
                    CheckBox naoPasso = (CheckBox) findViewById(R.id.checkBoxB8NaoPasso);

                    if(jaPassei.isChecked()){
                        checked[7] = 1;
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }else if(vouPassar.isChecked()){
                        checked[7] = 2;
                        jaPassei.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    } else if(naoPasso.isChecked()){
                        checked[7] = 3;
                        jaPassei.setChecked(false);
                        vouPassar.setChecked(false);
                        botao.setText(">");
                    } else {
                        checked[7] = 1;
                        jaPassei.setChecked(true);
                        vouPassar.setChecked(false);
                        naoPasso.setChecked(false);
                        botao.setText(">");
                    }

                    apertado[7] = false;

                }

            }
        });//fim dos botões





    }//fim do on create;

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(TelaDaGrade.this, TelaInicial.class);
        startActivity(intent);
        finish();

    }

}
