package org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas;

import org.ufv.sapiens.sapiensplanodeestudos.Constants;

import java.util.ArrayList;

public class Catalogo {

    public ArrayList<Displina> displinas;

    public Catalogo(ArrayList<Displina> displinas) {
        this.displinas = displinas;
    }

    public boolean podeCursar(Displina displina, Historico passado){
         if(passado.horas >= displina.horasMinimas && !passado.aprovouDisciplinaPeloNome(displina.nome)){
             int i=0;
             for (String requisito:displina.requisitos) {
                 if(passado.aprovouDisciplinaPeloNome(requisito)){
                     i++;
                 }
             }
             if(i<=displina.requisitos.size()) {
                 int j = 0;
                 for (String requisito : displina.corequisitos) {
                     if (passado.aprovouDisciplinaPeloNome(requisito)) {
                         j++;
                     }
                 }
                 if(i+j>=displina.requisitos.size())
                     return true;

             }else
                 return true;

        }else
             return false;

         return false;

    }


    public Displina getDisciplina(String nome){
        for (Displina displina: displinas) {
            if(displina.nome.equalsIgnoreCase(nome)){
                return displina;
            }
        }

        return Constants.DISPLINA_INVALIDA;
    }

    @Override
    public String toString() {
        String string ="";

        for (Displina d:displinas) {
            string += d.nome + ", " + d.creditos +"\n";
        }

        return string;
    }
}
