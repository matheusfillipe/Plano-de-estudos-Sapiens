package org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas;

import org.ufv.sapiens.sapiensplanodeestudos.Constants;

import java.util.ArrayList;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Periodo implements Serializable {

    public ArrayList<Displina> displinas;


    public Periodo(ArrayList<Displina> displinas){
        this.displinas=displinas;
    }

    public Displina getDisciplina(String name){
        for (int i=0; i<displinas.size(); i++){
            Displina disc=displinas.get(i);
            if(disc.nome.equalsIgnoreCase(name)){
                return disc;
            }
        }
        return Constants.DISPLINA_INVALIDA;
    }

    public int getCreditos(){
        int c=0;
        for (Displina disc: displinas) {
            c+=disc.creditos;
        }
        return c;
    }

    @Override
    public String toString() {
        String string ="";

        string += "Periodo ";
        for (Displina d:displinas) {
            string += d.nome + ", " + d.estado + ";  ";
        }
        return string;
    }

}

