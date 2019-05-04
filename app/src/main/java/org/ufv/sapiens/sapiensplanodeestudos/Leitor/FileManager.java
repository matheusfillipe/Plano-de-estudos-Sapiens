package org.ufv.sapiens.sapiensplanodeestudos.Leitor;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import org.ufv.sapiens.sapiensplanodeestudos.Constants;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Catalogo;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Displina;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Historico;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Periodo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {
        String csvFilename;
        public ArrayList<ArrayList<String>> planilha = new ArrayList<ArrayList<String>>();

        public FileManager(String csvFilename)  {
            this.csvFilename = csvFilename;
            try {
                this.ReadFile();
            }catch (IOException e){
                e.printStackTrace();

            }
        }


        public void ReadFile() throws FileNotFoundException, IOException{
                CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
                String[] row = null;
                while ((row = csvReader.readNext()) != null) {
                    ArrayList<String> linha = new ArrayList<String>();
                    for (String s:row) {
                        linha.add(s);
                    }

                    planilha.add(linha);
                }

                csvReader.close();
        }

        public void clear(){
            planilha=null;
        }

        public Catalogo catalogo(){
            ArrayList<Displina> displinas = new ArrayList<Displina>();
            for (ArrayList linha:planilha) {
                Displina displina = new Displina(linha.get(0).toString(), Constants.CATALOGO, Integer.valueOf(linha.get(1).toString()));
                displina.addRequisitos(new ArrayList<String>(Arrays.asList(linha.get(2).toString().split(Constants.SEPARADOR_DE_DISPLINAS))),
                        new ArrayList<String>(Arrays.asList(linha.get(3).toString().split(Constants.SEPARADOR_DE_DISPLINAS))));


                displinas.add(displina);
            }

            Catalogo catalogo = new Catalogo(displinas);


            return catalogo;

        }

        public Historico historico(Catalogo catalogo){
            ArrayList<Periodo> periodos=new ArrayList<Periodo>();
             for (ArrayList linha:planilha) {

                 ArrayList<Displina> displinas=new ArrayList<Displina>();
                 for (Object elem: linha) {

                     String e = elem.toString();
                     if (!e.isEmpty()) {

                         String nome = e.split(Constants.SEPARADOR_DE_DISPLINAS)[0];
                         int estado = Integer.valueOf(e.split(Constants.SEPARADOR_DE_DISPLINAS)[1].replace(" ", ""));
                         Displina catDisc = catalogo.getDisciplina(nome);

                         if (catDisc.isInvalid()) {
                             return null;
                         }

                         Displina disc = new Displina(nome, estado, catDisc.creditos);
                         disc.requisitos = catDisc.requisitos;
                         disc.corequisitos = catDisc.corequisitos;
                         disc.horasMinimas = catDisc.horasMinimas;

                         displinas.add(disc);



                     }
                 }
                 Periodo periodo = new Periodo(displinas);
                 periodos.add(periodo);

             }

            Historico historico = new Historico(periodos);

            return historico;
        }




        public void WriteFile(String csv) throws IOException{

            CSVWriter writer = new CSVWriter(new FileWriter(csv));

            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] {"India", "New Delhi"});
            data.add(new String[] {"United States", "Washington D.C"});
            data.add(new String[] {"Germany", "Berlin"});

            writer.writeAll(data);

            writer.close();
       }





}
