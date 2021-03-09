package com.example.mediakirjasto;

public class Game {
    int _id;
    String _name;
    String _platform;
    boolean _expansion;
    String _region;
    public Game(){ }
    public Game(int id, String name, String platform, boolean expansion, String region){
        this._id = id;
        this._name = name;
        this._platform = platform;
        this._expansion = expansion;
        this._region = region;
    }

    public Game(String name, String platform, boolean expansion, String region){
        this._name = name;
        this._platform = platform;
        this._expansion = expansion;
        this._region = region;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

}
