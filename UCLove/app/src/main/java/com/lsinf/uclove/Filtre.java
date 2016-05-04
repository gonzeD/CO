package com.lsinf.uclove;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by damien on 26/04/16.
 */

//définit un filtre personnalisé de l'utilisateur

public class Filtre
{
    public String name;
    private ArrayList<Integer> nameCara = new ArrayList<>();
    private ArrayList<Integer> cara = new ArrayList<>();
    public int idFiltre;


    public ArrayList<Integer> getIdNameCara(){return nameCara;}
    public ArrayList<Integer> getIdCara(){return cara;}

    public int getSize(){return cara.size();}
    public String getNameCara(int i,Context ctx){
        Log.e("dodormeur","nameCara "+i);
        return ctx.getResources().getStringArray(R.array.nameCara)[nameCara.get(i)];
    }

    public void clearCara()
    {
        nameCara.clear();cara.clear();
    }
    public String getCara(int i,Context ctx)
    {
        Log.e("dodormeur","Cara "+i);
        switch(nameCara.get(i))
        {
            case 0:return ctx.getResources().getStringArray(R.array.hair_color)[cara.get(i)];
            case 1:return ctx.getResources().getStringArray(R.array.eye_color)[cara.get(i)];
            case 2:return ctx.getResources().getStringArray(R.array.genre)[cara.get(i)];
        }
        return "error";
    }
    public void loadData(String data)
    {

        String temp[] = data.split(":");
        for(int i = 0;i<temp.length/2;i++)
        {
            nameCara.add(Integer.parseInt(temp[i*2]));
            cara.add(Integer.parseInt(temp[i*2+1]));
        }
    }

    public String getData()
    {
        Log.e("dodormeur","size cara :"+nameCara.size());
        String temp = "";
        for(int i = 0;i<nameCara.size();i++)
        {
            temp+=nameCara.get(i)+":"+cara.get(i);
            if(i+1<nameCara.size())temp+=":";
        }
        return temp;
    }
}
