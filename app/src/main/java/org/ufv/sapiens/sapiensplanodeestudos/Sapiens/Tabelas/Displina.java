package org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas;

import org.ufv.sapiens.sapiensplanodeestudos.Constants;

import java.util.ArrayList;

public class Displina {

    public String nome;
    public int estado;
    public int creditos;
    public int dificuldade = 1;
    public ArrayList<String> requisitos = new ArrayList<String>();
    public ArrayList<String> corequisitos = new ArrayList<String>();
    public int horasMinimas=0;
    public boolean optativa = false;



    public Displina(String nome, int estado, int creditos) {
        this.nome = nome;
        this.estado = estado;
        this.creditos=creditos;
    }




    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public void addRequisitos(ArrayList<String> req, ArrayList<String> co){

        if(!(req.get(0).isEmpty() || req==null || req.size() <=0 ))

            requisitos.addAll(req);

        if(!(co.get(0).isEmpty() || co==null || co.size() <= 0)) {
            requisitos.addAll(co);
            corequisitos.addAll(co);
        }
    }



    public int getCreditos() {
        return creditos;
    }

    public boolean aprovada(){

        return estado== Constants.APROVADO;
    }

    public boolean reprovada(){

        return estado==Constants.REPROVADO;
    }

    public boolean cursando(){

        return estado==Constants.CURSANDO;
    }

    public boolean pendente(){
        return estado==Constants.PENDENTE;
    }

    public boolean isInvalid(){
        return (this.estado==Constants.INVALIDA);
    }



}
