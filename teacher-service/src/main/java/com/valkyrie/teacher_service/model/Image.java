package com.valkyrie.teacher_service.model;

import jakarta.persistence.Lob;

public class Image {
    private String name;
    private String type;
    @Lob
    private byte[] data;

    public String getName() {return name;}

    public String getType() {return type;}

    public byte[] getData() {return data;}

    public Image setName(String name) {
        this.name = name;
        return this;
    }

    public Image setType(String type) {
        this.type = type;
        return this;
    }

    public Image setData(byte[] data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {return name + "." + type;}
}
