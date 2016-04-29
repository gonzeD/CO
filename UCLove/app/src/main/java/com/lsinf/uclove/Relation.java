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
    private ArrayList<Integer> idFriend = new ArrayList<Integer>();
    private ArrayList<Integer> state  = new ArrayList<Integer>();

    public int getSize(){if(idFriend == null)return 0;return idFriend.size();}
    public void add(int id,int state)
    {
        idFriend.add(id);
        this.state.add(state);
    }
    public int getRelationById(int id)
    {
        for(int i = 0;i<idFriend.size();i++)
            if(idFriend.get(i) == id)
                return state.get(i);
        return -1;
    }
    public int getRelation(int i){return state.get(i);}
    public int getId(int i){return idFriend.get(i);}

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
    }
}
