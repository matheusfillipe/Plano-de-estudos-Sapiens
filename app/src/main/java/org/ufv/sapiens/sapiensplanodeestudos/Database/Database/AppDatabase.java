package org.ufv.sapiens.sapiensplanodeestudos.Database.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import org.ufv.sapiens.sapiensplanodeestudos.Database.Database.Dao.AlunoDAO;
import org.ufv.sapiens.sapiensplanodeestudos.Database.Database.Dao.Converter;
import org.ufv.sapiens.sapiensplanodeestudos.Database.Database.Entity.Aluno;


@Database(entities = {Aluno.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase = null;

    /**
     * from developers android, made my own singleton
     *
     * @param context
     * @return
     */
    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "database-name").build();
        }
        return appDatabase;
    }

    public abstract AlunoDAO alunoDAO();
}
