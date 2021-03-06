package org.ufv.sapiens.sapiensplanodeestudos;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import org.ufv.sapiens.sapiensplanodeestudos.Database.Database.AppDatabase;
import org.ufv.sapiens.sapiensplanodeestudos.Database.Database.Dao.AlunoDAO;
import org.ufv.sapiens.sapiensplanodeestudos.Database.Database.DbHandler;
import org.ufv.sapiens.sapiensplanodeestudos.Dialogos.AlertCatalogo;
import org.ufv.sapiens.sapiensplanodeestudos.Leitor.FileManager;
import org.ufv.sapiens.sapiensplanodeestudos.Leitor.Utils;
import org.ufv.sapiens.sapiensplanodeestudos.SapienService.LoginActivity;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Processador.PlanoDeEstudos;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Catalogo;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Displina;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Historico;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Periodo;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.ufv.sapiens.sapiensplanodeestudos.Constants.CSV;


public class TelaInicial extends AppCompatActivity {


    private ArrayList<String> disciplinas;
    private Historico historico;
    private Catalogo catalogo = null;
    private FileManager file;
    private PlanoDeEstudos planoDeEstudos;
    private DbHandler dbHandler;
    private AlunoDAO db;
    private List<List<TextView>> texto;
    private Menu menu;


    private AlertCatalogo fragment;

    public void toggleVisibilityTextView(TextView txtView) {

        if (txtView.getVisibility() == View.VISIBLE)
            txtView.setVisibility(View.INVISIBLE);
        else
            txtView.setVisibility(View.VISIBLE);
    }

    public void hideTextView(TextView textView){
        textView.setVisibility(View.INVISIBLE);
    }
    public void showTextView(TextView textView){
        textView.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        disciplinas= new ArrayList<>();



        FloatingActionButton gerador = (FloatingActionButton) findViewById(R.id.gerarGrade);

        gerador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportar();
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
               // MenuItem importarHistorico=(MenuItem)  menu.findItem(R.id.importarHistorico).getActionView();

                if (id == R.id.importarGrade) {
                    catalogo();
                 //   importarHistorico.setVisible(true);
                }else if (id == R.id.importarHistorico) {
                    passado();
                }else if (id == R.id.ajuda) {
                    ajudar();
                }else if (id == R.id.compartilhar) {
                    exportar();
                }else if (id == R.id.atualizar) {
                    updateView();
                }
                return true;
            }
        });

        checkPerms();

        texto = new ArrayList<List<TextView>>();
        texto.add(Arrays.asList(
                        (TextView) findViewById(R.id.textView6),
                        (TextView) findViewById(R.id.textView14),
                        (TextView) findViewById(R.id.textView22),
                        (TextView) findViewById(R.id.textView30),
                        (TextView) findViewById(R.id.textView38),
                        (TextView) findViewById(R.id.textView46),
                        (TextView) findViewById(R.id.textView54)));

                        texto.add(Arrays.asList(
                        (TextView) findViewById(R.id.textView7),
                        (TextView) findViewById(R.id.textView15),
                        (TextView) findViewById(R.id.textView23),
                        (TextView) findViewById(R.id.textView31),
                        (TextView) findViewById(R.id.textView39),
                        (TextView) findViewById(R.id.textView47),
                        (TextView) findViewById(R.id.textView55)));

        texto.add(Arrays.asList(
                        (TextView) findViewById(R.id.textView8),
                        (TextView) findViewById(R.id.textView16),
                        (TextView) findViewById(R.id.textView24),
                        (TextView) findViewById(R.id.textView32),
                        (TextView) findViewById(R.id.textView40),
                        (TextView) findViewById(R.id.textView48),
                        (TextView) findViewById(R.id.textView56)));

                texto.add(Arrays.asList(
                        (TextView) findViewById(R.id.textView9),
                        (TextView) findViewById(R.id.textView17),
                        (TextView) findViewById(R.id.textView25),
                        (TextView) findViewById(R.id.textView33),
                        (TextView) findViewById(R.id.textView41),
                        (TextView) findViewById(R.id.textView49),
                        (TextView) findViewById(R.id.textView57)));


                texto.add(Arrays.asList(
                        (TextView) findViewById(R.id.textView10),
                        (TextView) findViewById(R.id.textView18),
                        (TextView) findViewById(R.id.textView26),
                        (TextView) findViewById(R.id.textView34),
                        (TextView) findViewById(R.id.textView42),
                        (TextView) findViewById(R.id.textView50),
                        (TextView) findViewById(R.id.textView58)));


                texto.add(Arrays.asList(
                        (TextView) findViewById(R.id.textView11),
                        (TextView) findViewById(R.id.textView19),
                        (TextView) findViewById(R.id.textView27),
                        (TextView) findViewById(R.id.textView35),
                        (TextView) findViewById(R.id.textView43),
                        (TextView) findViewById(R.id.textView51),
                        (TextView) findViewById(R.id.textView59)));


                texto.add(Arrays.asList(
                        (TextView) findViewById(R.id.textView12),
                        (TextView) findViewById(R.id.textView20),
                        (TextView) findViewById(R.id.textView28),
                        (TextView) findViewById(R.id.textView36),
                        (TextView) findViewById(R.id.textView44),
                        (TextView) findViewById(R.id.textView52),
                        (TextView) findViewById(R.id.textView60)));


                texto.add(Arrays.asList(
                        (TextView) findViewById(R.id.textView13),
                        (TextView) findViewById(R.id.textView21),
                        (TextView) findViewById(R.id.textView29),
                        (TextView) findViewById(R.id.textView37),
                        (TextView) findViewById(R.id.textView45),
                        (TextView) findViewById(R.id.textView53),
                        (TextView) findViewById(R.id.textView61)));

        for(int i =0 ; i< texto.size(); i++){
            for(int j=0; j< texto.get(i).size(); j++){
                hideTextView(texto.get(i).get(j));
            }
        }

        db = AppDatabase.getInstance(this).alunoDAO();
        dbHandler = new DbHandler(db);

    }

    public void updateView(){

        TextView txtHoras = (TextView) findViewById(R.id.textoHoras);

        HorizontalScrollView fundoTexto_ = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);

        int i=0;
        for (Periodo p: historico.periodos) {

            int j=0;
            for (Displina d: p.displinas) {
                TextView text = texto.get(i).get(j);

                text.setTextColor(Color.BLACK);
                text.setText(" " + d.nome + " ");
                text.setTextSize(18);

                if (d.reprovada()) text.setBackgroundColor(Color.rgb(255, 0, 0));

                else if (d.cursando()) text.setBackgroundColor(Color.rgb(0, 50, 255));

                else if (d.aprovada()) text.setBackgroundColor(Color.rgb(10, 200, 5));

                else if (d.pendente()) text.setBackgroundColor(Color.rgb(200, 200, 200));
                showTextView(text);
                j++;
            }
            i++;
        }

        txtHoras.setText("Você acumulou " + historico.getHoras() + " horas ");
    }

    public void passado(){

          if(catalogo == null){
                fragment = AlertCatalogo.newInstance("","");
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                ft.add(R.id.fragment_placeholder, fragment, "A");
                ft.show(fragment);
                ft.commit();
                return;
            }

        Intent intent = new Intent()
                .setType(CSV)
                .setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Escolha o arquivo de seu passado curricular"), 1);
    }

    public void catalogo(){

        Intent intent = new Intent()
                .setType(CSV)
                .setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Escolha sua grade curricular"), 2);



    }

    public void exportar(){

        Periodo p= planoDeEstudos.proximoPeriodo();
        disciplinas.add("Novo: " + p.toString());

        Intent intent = new Intent(TelaInicial.this, GradeProximoPeriodo.class);
        intent.putExtra("periodoCursando", historico.getPeriodo(historico.getCurrentPeriodoIndex()));
        intent.putExtra("proximoPeriodo", p);
        startActivity(intent);
        finish();
    }

    public void ajudar(){
        Intent intent = new Intent(this, TelaAjuda.class);
        startActivity(intent);
        finish();

        updateView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws NullPointerException{
        super.onActivityResult(requestCode, resultCode, data);
        String nome="";

        if(requestCode==1 && resultCode==RESULT_OK) {

            Uri selectedfile = data.getData();
            try {

                file=new FileManager(Utils.getPathFromUri(getApplicationContext(), selectedfile));

                historico=file.historico(catalogo);
                disciplinas.add(historico.toString());
                nome="1";
                planoDeEstudos= new PlanoDeEstudos(historico, catalogo);
                dbHandler.insert("test", "1", historico, catalogo);
                updateView();

            }catch (NullPointerException e){
                e.printStackTrace();
                nome="";
            }

        }

        if(requestCode==2 && resultCode==RESULT_OK) {
            Uri selectedfile = data.getData();
            try {

                file=new FileManager(Utils.getPathFromUri(getApplicationContext(), selectedfile));

                catalogo = file.catalogo();
                disciplinas.add(catalogo.toString());
                nome="1";

            }catch (java.lang.NullPointerException e){
                e.printStackTrace();
                nome="";
            }
        }

        if(nome.length()==0)
            return;


        }


    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        disciplinas.clear();


        updateView();
    }

    private boolean checkPerms() {
        String TAG = "Plano de Estudos";

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"WRITE Permission is granted");

            } else {

                Log.v(TAG,"WRITE Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);

            }

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"READ Permission is granted");


            } else {
                Log.v(TAG,"READ Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);

            }

            if (checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"INTERNET Permission is granted");
                return true;

            } else {
                Log.v(TAG,"INTERNET Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 3);
                return false;
            }

        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permissions are all granted");
            return true;
        }

    }


    public void closeDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // removes the existing fragment calling onDestroy
        ft.hide(fragment);
        ft.commit();
    }

    public void clearDb(boolean r){
        if (r)
            dbHandler.clear();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.tela_inicial, menu);
       // MenuItem importarHistorico=(MenuItem)  menu.findItem(R.id.importarHistorico).getActionView();
       // this.menu = menu;  // this will copy menu values to upper defined menu so that we can change icon later akash
       // if(historico==null)
       //     importarHistorico.setVisible(false);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {

            Intent intent = new Intent(TelaInicial.this, Ajuda.class);
            startActivity(intent);
            finish();

            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}


