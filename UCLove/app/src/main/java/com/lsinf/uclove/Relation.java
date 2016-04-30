package com.lsinf.uclove;

import java.util.ArrayList;

/**
 * Created by damien on 26/04/16.
 */

//gêre les relation de l'utilisateur
public class Relation
{
    public static final int BLOQUED = 0;
    public static final int NOTHING = 1;
    public static final int DEMAND = 2;
    public static final int FRIEND = 3;
    public static final int FAVORITE = 4;
    private ArrayList<Integer> idAsker = new ArrayList<Integer>();
    private ArrayList<Integer> idReceiver = new ArrayList<Integer>();
    private ArrayList<Integer> state  = new ArrayList<Integer>();

    public int getSize(){if(state == null)return 0;return state.size();}
    public void add(int asker,int receiver,int state)
    {
        idAsker.add(asker);
        idReceiver.add(receiver);
        this.state.add(state);
    }
   /* public int getRelationById(int a)
    {
        for(int i = 0;i<idFriend.size();i++)
            if(idFriend.get(i) == id)
                return state.get(i);
        return -1;
    }*/
    public int getRelation(int i){return state.get(i);}
    public int getAsker(int i){return idAsker.get(i);}
    public int getReceiver(int i){return idReceiver.get(i);}
/*
    public ArrayList<Integer> getState() {
        return state;
    }

    public void setState(ArrayList<Integer> state) {
        this.state = state;
    }

    public ArrayList<Integer> getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(ArrayList<Integer> idFriend) {
        this.idFriend = idFriend;
    }*/
}
