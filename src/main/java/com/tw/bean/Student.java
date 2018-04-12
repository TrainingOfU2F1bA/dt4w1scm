package com.tw.bean;

import java.util.List;
import java.util.Map;

public class Student {
    private String name;
    private String id;
    private Map<String,Integer> map;

    public Student(String name, String id, Map<String, Integer> map) {
        this.name = name;
        this.id = id;
        this.map = map;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
