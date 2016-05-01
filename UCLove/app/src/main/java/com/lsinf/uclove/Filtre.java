package com.lsinf.uclove;

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
    public ArrayList<String> nameCara = new ArrayList<>();
    public ArrayList<String> cara = new ArrayList<>();
    public int idFiltre;

    public void loadData(String data)
    {

        String temp[] = data.split(":");
        for(int i = 0;i<temp.length/2;i++)
        {
            nameCara.add(temp[i*2]);
            cara.add(temp[i*2+1]);
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
