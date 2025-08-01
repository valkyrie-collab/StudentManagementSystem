package com.valkyrie.student_service.model;

import jakarta.persistence.Lob;

public class Image {
    private String type;
    private String name;
    @Lob
    private byte[] data;

    public String getType() {return type;}

    public String getName() {return name;}

    public byte[] getData() {return data;}

    public Image setType(String type) {
        this.type = type;
        return this;
    }

    public Image setName(String name) {
        this.name = name;
        return this;
    }

    public Image setData(byte[] data) {
        this.data = data;
        return this;
    }

    public String getImageFullName() {return name + "." + type;}
}
