package com.example.mediakirjasto;

public class Game {
    int _id;
    String _name;
    String _platform;
    String _region;
    String _expansion;
    String _media_type;
    String _copies;
    String _notes;

    public Game(){ }

    public Game(int id, String name, String platform, String region, String expansion, String _media_type, String copies, String notes) {
        this._id = id;
        this._name = name;
        this._platform = platform;
        this._region = region;
        this._expansion = expansion;
        this._media_type = _media_type;
        this._copies = copies;
        this._notes = notes;
    }

    public Game(String name, String platform, String region, String expansion, String _media_type, String copies, String notes) {
        this._name = name;
        this._platform = platform;
        this._region = region;
        this._expansion = expansion;
        this._media_type = _media_type;
        this._copies = copies;
        this._notes = notes;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getPlatform() {
        return this._platform;
    }

    public void setPlatform(String platform) {
        this._platform = platform;
    }

    public String getRegion() {
        return this._region;
    }

    public void setRegion(String region){
        this._region = region;
    }

    public String getExpansion() {
        return this._expansion;
    }

    public void setExpansion(String expansion) {
        this._expansion = expansion;
    }

    public String getMediaType() {
        return this._media_type;
    }

    public void setMediaType(String mediaType) {
        this._media_type = mediaType;
    }

    public String getCopies() {
        return this._copies;
    }

    public void setCopies(String copies) {
        this._copies = copies;
    }

    public String getNotes() {
        return this._notes;
    }

    public void setNotes(String notes) {
        this._notes = notes;
    }
}
