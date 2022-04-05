package com.example.tuan7_lab7_a;

public class People {

    int _id;
    String _name;

    public People() {

    }

    public People(int id, String name) {
        this._id = id;
        this._name = name;
    }

    public People(String name) {
        this._name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
