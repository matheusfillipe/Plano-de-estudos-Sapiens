package org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Processador;

import org.ufv.sapiens.sapiensplanodeestudos.Constants;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Catalogo;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Displina;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Historico;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Periodo;

import java.util.ArrayList;

public class PlanoDeEstudos {



    private final Historico historico;
    private Historico historicoProposto;
    private final Catalogo catalogo;

    private int ultimoPeriodo;
    private ArrayList<Periodo> periodos;

    public PlanoDeEstudos(Historico historico, Catalogo catalogo) {
        this.historico = historico;

        this.catalogo = catalogo;
        this.ultimoPeriodo = historico.getCurrentPeriodoIndex();
        this.periodos=historico.getPeriodosCursados();
        this.historicoProposto=historico;
    }

    public void reset(){
        for(int i = ultimoPeriodo; i>historicoProposto.getCurrentPeriodoIndex(); i--){
             periodos.remove(ultimoPeriodo);
        }
        ultimoPeriodo=historico.getCurrentPeriodoIndex();
        periodos=historico.getPeriodosCursados();
        historicoProposto=historico;
    }

    public boolean valido(){
        return ultimoPeriodo>=0;
    }

    public Periodo proximoPeriodo(){
        Periodo proximoPeriodo = new Periodo(new ArrayList<Displina>());
        Periodo periodoVigente = periodos.get(ultimoPeriodo);

        ArrayList<Displina> displinas = new ArrayList<Displina>();

        historicoProposto.aprovarPeriodo();

        int i =0;

        ArrayList<Displina> faltaCursar = historicoProposto.faltaCursar(catalogo);
        for (Displina d: faltaCursar) {
            if(proximoPeriodo.getCreditos() <= Constants.MAX_CREDITOS){
                if(catalogo.podeCursar(d,historicoProposto)){
                    d.estado=Constants.CURSANDO;
                    displinas.add(d);
                    proximoPeriodo=new Periodo(displinas);
                    i++;
                }
            }
        }

        proximoPeriodo=new Periodo(displinas);
        historicoProposto.addPeriodo(proximoPeriodo);
        periodos.add(proximoPeriodo);
        ultimoPeriodo++;
        return proximoPeriodo;
    }
}
