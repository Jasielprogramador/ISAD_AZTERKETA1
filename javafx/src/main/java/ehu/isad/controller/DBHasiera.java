package ehu.isad.controller;


import ehu.isad.model.Txanpona;

import java.lang.reflect.AnnotatedArrayType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBHasiera {

    private static final DBHasiera instance = new DBHasiera();

    public static DBHasiera getInstance() {
        return instance;
    }

    public List<Txanpona> datuBaseaLortu(){

        List<Txanpona> emaitza = new ArrayList<>();
        String query = "select * from txanponak";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        try{
            while(rs.next()){
                Integer id = rs.getInt("id");
                String data = rs.getString("data");
                Float balioa = rs.getFloat("balioa");
                String mota = rs.getString("mota");
                Float bolumena = rs.getFloat("bolumena");
                Txanpona txanpona = new Txanpona(id,data,balioa,mota,bolumena);
                emaitza.add(txanpona);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return emaitza;
    }

    public void datuBaseaEzabatu(){
        String query = "remove * from txanponak";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }

    public void datuBaseaGorde(List<Txanpona> a){

        for(int i = 0;i<a.size();i++) {
            String query = "insert into txanponak values";
            DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
            dbKudeatzaile.execSQL(query);
        }
    }


}
