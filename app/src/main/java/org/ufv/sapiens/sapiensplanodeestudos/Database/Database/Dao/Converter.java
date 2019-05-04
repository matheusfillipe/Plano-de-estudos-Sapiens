package org.ufv.sapiens.sapiensplanodeestudos.Database.Database.Dao;

import android.arch.persistence.room.TypeConverter;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Catalogo;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Historico;


public class Converter{
    @TypeConverter
    public String fromHistorico(Historico historico){
        if(historico==null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Historico>(){}.getType();
        String json = gson.toJson(historico, type);
        return json;
    }

    @TypeConverter
    public  String fromCatalogo(Catalogo historico){
        if(historico==null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Historico>(){}.getType();
        String json = gson.toJson(historico, type);
        return json;
    }

    @TypeConverter
    public Historico toHistorico(String string){
        if (string==null)
            return (null);

        Gson gson = new Gson();
        Type type = new TypeToken<Historico>(){}.getType();
        Historico historico = gson.fromJson(string, type);
        return historico;
    }

    @TypeConverter
    public Catalogo toCatalogo(String string){
        if (string==null)
            return (null);

        Gson gson = new Gson();
        Type type = new TypeToken<Catalogo>(){}.getType();
        Catalogo catalogo = gson.fromJson(string, type);
        return catalogo;
    }
}
