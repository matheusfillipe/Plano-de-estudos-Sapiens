package org.ufv.sapiens.sapiensplanodeestudos.Database.Database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Catalogo;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Historico;

@Entity(tableName = "Aluno")
public class Aluno {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name="user")
    private String user;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "historico")
    private Historico historico;

    @ColumnInfo (name = "catalogo")
    private Catalogo catalogo;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Historico getHistorico() {
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "uid=" + uid +
                ", Matrícula='" + user + '\'' +
                '}';
    }
}
