package org.ufv.sapiens.sapiensplanodeestudos;

import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Displina;

public final class Constants {
    public static final int HORAS_POR_CREDITO=15;
    public static final String SEPARADOR_DE_DISPLINAS=",";

    //-1 reprovado
    //0 cursando
    //1 aprovado
    //2 a cursar

       public static final int CATALOGO = -2;
       public static final int REPROVADO = -1;
       public static final int CURSANDO = 0;
       public static final int APROVADO = 1;
       public static final int PENDENTE = 2;
       public static final int INVALIDA = 3;

     public static final Displina DISPLINA_INVALIDA = new Displina("INVALIDA", Constants.INVALIDA, 0);

     public static final String CSV = "text/comma-separated-values";


    public static final int MAX_CREDITOS = 25;
}
