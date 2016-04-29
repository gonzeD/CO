package com.lsinf.uclove;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by damien on 26/04/16.
 */

//Contient la liste des message de l'utilisateur

public class Messages
{

    private ArrayList<String> text = new ArrayList<String>();
    private ArrayList<Integer> getter = new ArrayList<Integer>();
    private ArrayList<Integer> sender = new ArrayList<Integer>();
    private ArrayList<String> date = new ArrayList<String>();

    public String getText(int i){return text.get(i);}
    public String getdate(int i){return date.get(i);}
    public Integer getGetter(int i){return getter.get(i);}
    public Integer getSender(int i){return sender.get(i);}
    public int size()
    {
        return text.size();
    }
    public void clear()
    {
        text = new ArrayList<String>();
        getter = new ArrayList<Integer>();
        sender = new ArrayList<Integer>();
        date = new ArrayList<String>();
    }
    public void add(Integer getter,Integer sender,String date,String text)
    {
        this.getter.add(getter);
        this.sender.add(sender);
        this.date.add(date);
        this.text.add(text);
    }

    public ArrayList<String> getText() {
        return text;
    }

    public void setText(ArrayList<String> text) {
        this.text = text;
    }


}
