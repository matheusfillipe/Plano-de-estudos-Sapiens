package org.ufv.sapiens.sapiensplanodeestudos.Database.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.ufv.sapiens.sapiensplanodeestudos.Database.Database.Entity.Aluno;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Catalogo;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Historico;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface AlunoDAO {
    @Query("SELECT * FROM Aluno")
    List<Aluno> getAll();

    @Query("SELECT * FROM Aluno WHERE uid IN (:alunoIds)")
    List<Aluno> loadAllByIds(int[] alunoIds);

    @Query("SELECT * FROM Aluno WHERE user LIKE :first LIMIT 1")
    Aluno findByName(String first);

    @Query("DELETE FROM Aluno")
    void clear();

    @Insert(onConflict = REPLACE)
    void insertAll(Aluno... alunos);

    @Delete
    void delete(Aluno aluno);

}


