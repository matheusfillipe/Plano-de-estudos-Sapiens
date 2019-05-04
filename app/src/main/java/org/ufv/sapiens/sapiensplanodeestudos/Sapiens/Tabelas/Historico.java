package org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas;

import org.ufv.sapiens.sapiensplanodeestudos.Constants;

import java.util.ArrayList;
import java.util.ListIterator;

public class Historico {

    public static int horas;
    public static String materias_[][] = {
            {"","","","","","","","","",""},
            {"","","","","","","","","",""},
            {"","","","","","","","","",""},
            {"","","","","","","","","",""},
            {"","","","","","","","","",""},
            {"","","","","","","","","",""},
            {"","","","","","","","","",""},
            {"","","","","","","","","",""},
            {"","","","","","","","","",""},
            {"","","","","","","","","",""}};

    public static int estadoMaterias[][] = {
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0}};

    public static String materiasFazendo[] = {"","","","","","","",""};

    public ArrayList<Periodo> periodos;

    public Historico(ArrayList<Periodo> periodos, int horas){
        this.periodos=periodos;
        this.horas=horas;
    }

    public Historico(ArrayList<Periodo> periodos){
        this.periodos=periodos;
        calcularHoras();
    }


    public Periodo getPeriodo(int i){
        return periodos.get(i);
    }

    public int getCurrentPeriodoIndex(){
        for (Periodo periodo:periodos) {
            for (Displina d:periodo.displinas) {
                if(d.cursando())
                    return periodos.indexOf(periodo);
            }
        }
        return -1;
    }



    public ArrayList<Periodo> getPeriodosCursados(){
        int i = getCurrentPeriodoIndex();
        if(i>=0){
            ArrayList<Periodo> periodoArrayList = new ArrayList<Periodo>();
            int j=0;
            for (Periodo p: periodos) {
                if(j>i)
                    break;
                periodoArrayList.add(p);
                j++;
            }
            return periodoArrayList;
        }
        else {
            return periodos;
        }

    }


    public ArrayList<Displina> displinasCursando() {
        ArrayList<Displina> displinas=null;

        for (Periodo p: this.periodos) {
            for (Displina d:p.displinas) {
                if(d.cursando()){
                    displinas.add(d);
                }
            }

        }
        return displinas;
    }

    private void calcularHoras(){
        int h = horas;
        for (Periodo periodo : periodos) {
            for (Displina disc: periodo.displinas) {
                if(disc.aprovada()){
                    h+=disc.creditos* Constants.HORAS_POR_CREDITO;
                }
            }

        }
        horas=h;
    }

    public boolean cursouDisciplina(Displina displina){
        for(Periodo p: periodos) {
            Displina cursada =p.getDisciplina(displina.nome);
            if(cursada.isInvalid()){
                return true;
            }
        }
        return false;
    }

    public boolean cursandoDisciplinaPeloNome(String nome) throws  NullPointerException{
        ArrayList<String> ultimoPeriodo = new ArrayList<String>();
        ArrayList<Displina> displinas = periodos.get(periodos.size()-1).displinas;

        for (Displina d: displinas) {
            ultimoPeriodo.add(d.nome);
        }

        return ultimoPeriodo.indexOf(nome)>=0;
    }
     public boolean aprovouDisciplinaPeloNome(String disciplina){

         ListIterator<Periodo> iterator = periodos.listIterator(periodos.size());
         while (iterator.hasPrevious()){
            Periodo p = iterator.previous();
            Displina cursada = p.getDisciplina(disciplina);
            if(cursada.aprovada()){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Displina> faltaCursar(Catalogo catalogo){
        ArrayList<Displina> displinas = new ArrayList<Displina>();


        for (Displina d:catalogo.displinas) {
            if(!aprovouDisciplinaPeloNome(d.nome)){
                displinas.add(d);
            }
        }

        return displinas;
    }

    public ArrayList<Displina> reprovouEm(){
         ArrayList<Displina> displinas = new ArrayList<Displina>();

        for (Periodo p: periodos) {
            for(Displina d:p.displinas) {
                if (cursouDisciplina(d) && d.reprovada()) {
                    displinas.add(d);
                }
            }
        }

        return displinas;

    }

    public void addPeriodo(Periodo periodo){
        periodos.add(periodo);
        calcularHoras();

    }

    public boolean reprovouDisciplinaPeloNome(String nome){

        for (Displina d: reprovouEm()){
            if(d.nome.equalsIgnoreCase(nome))
                return true;
        }

        return false;
    }


    @Override
    public String toString() {
        String string ="";
        int i =0;
        int j =0;
        int k =0;
        for (Periodo p:periodos) {
            string += "Periodo " + i + ":";
            for (Displina d:p.displinas) {
                string += d.nome + ", " + d.estado + ";  ";
                materias_[i][j] = d.nome;
                estadoMaterias[i][j] = d.estado;
                if(d.estado == 0){
                    materiasFazendo[k] = d.nome;
                    k++;
                }
                j++;
            }
            string+="\n";
            i++;
        }

        return "Horas Cursadas: " + horas + "\n\n" + string;
    }

    public ArrayList<Displina> currentPeriodoDisciplinas(){
        return periodos.get(getCurrentPeriodoIndex()).displinas;
    }

    public void aprovarPeriodo() {
        int c = currentPeriodoDisciplinas().size()-1;
        for(int i=0; i<=c; i++){
            currentPeriodoDisciplinas().get(i).estado=Constants.APROVADO;
        }
    }
}
