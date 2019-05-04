package org.ufv.sapiens.sapiensplanodeestudos.Database.Database;

import android.os.AsyncTask;

import org.ufv.sapiens.sapiensplanodeestudos.Database.Database.Dao.AlunoDAO;
import org.ufv.sapiens.sapiensplanodeestudos.Database.Database.Entity.Aluno;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Catalogo;
import org.ufv.sapiens.sapiensplanodeestudos.Sapiens.Tabelas.Historico;

import java.util.ArrayList;
import java.util.List;

public class DbHandler {

    private static  AlunoDAO db;
    private static ArrayList<Aluno> Alunos;
    private static boolean m_lock = false;
    private static Aluno aluno;

    public DbHandler(AlunoDAO db) {
        this.db=db;
    }

    private static boolean isLocked(){
        if (!m_lock){
            return true;
        }else {
            return isLocked();
        }
    }

    private static void lock(){
        m_lock=true;
    }

    private static void unlock(){
        m_lock=false;
    }


    public static void clear(){

                    new AsyncTask<Void, Void, Void>() {


                        @Override
                        protected Void doInBackground(Void... voids) {
                            if (!isLocked()) {
                                lock();
                                db.clear();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            unlock();
                        }
                    }.execute();
                }




    public static void computeAlunos() {
           new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    if (!isLocked()) {
                        lock();

                        List<Aluno> alunosTmp = new ArrayList<>();
                        alunosTmp = db.getAll();
                        Alunos.clear();
                        for (Aluno b : alunosTmp) {
                            Alunos.add(b);
                        }
                    }

                return null;

                }

                @Override
                protected void onPostExecute(Void agentsCount) {
                    unlock();
                }
            }.execute();
        }



        public static void findAlunoByName(final String user) {


            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                        if (!isLocked()) {
                            lock();
                            aluno = db.findByName(user);
                        }
                   return null;
                }

                @Override
                protected void onPostExecute(Void agentsCount) {
                    unlock();
                }
            }.execute();
        }

        public static void insert(final String user, final String password, final Historico historico, final Catalogo catalogo) {

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    if (!isLocked()) {
                        Aluno alunoToInset = new Aluno();
                        alunoToInset.setUser(user);
                        alunoToInset.setCatalogo(catalogo);
                        alunoToInset.setHistorico(historico);
                        alunoToInset.setPassword(password);

                        db.insertAll(alunoToInset);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    unlock();
                }

            }.execute();
        }


    public static Aluno getAluno() {
        return aluno;
    }

    public static ArrayList<Aluno> getAlunos() {
        return Alunos;
    }
}
