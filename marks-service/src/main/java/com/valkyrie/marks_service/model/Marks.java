package com.valkyrie.marks_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "marks")
public class Marks {
    @Id
    private String id;
    private int maths;
    private int physics;
    private int chemistry;
    private int biology;
    private int msc;
    private int gk;
    private int historyAndCivics;
    private int bengali;
    private int hindi;
    private int computer;
    private int englishI;
    private int englishII;
    private char drawing;
    private int geography;
    private int gsc;
    private int sst;
    private String studentId;

    public String getId() {return id;}

    public int getMaths() {return maths;}

    public int getPhysics() {return physics;}

    public int getChemistry() {return chemistry;}

    public int getBiology() {return biology;}

    public int getMsc() {return msc;}

    public int getGk() {return gk;}

    public int getBengali() {return bengali;}

    public int getHindi() {return hindi;}

    public int getComputer() {return computer;}

    public int getHistoryAndCivics() {return historyAndCivics;}

    public int getEnglishI() {return englishI;}

    public int getEnglishII() {return englishII;}

    public char getDrawing() {return drawing;}

    public int getGeography() {return geography;}

    public int getGsc() {return gsc;}

    public int getSst() {return sst;}

    public String getStudentId() {return studentId;}

    public Marks setId(String id) {
        this.id = id;
        return this;
    }

    public Marks setMaths(int maths) {
        this.maths = maths;
        return this;
    }

    public Marks setPhysics(int physics) {
        this.physics = physics;
        return this;
    }

    public Marks setMsc(int msc) {
        this.msc = msc;
        return this;
    }

    public Marks setBiology(int biology) {
        this.biology = biology;
        return this;
    }

    public Marks setChemistry(int chemistry) {
        this.chemistry = chemistry;
        return this;
    }

    public Marks setGk(int gk) {
        this.gk = gk;
        return this;
    }

    public Marks setHistoryAndCivics(int historyAndCivics) {
        this.historyAndCivics = historyAndCivics;
        return this;
    }

    public Marks setBengali(int bengali) {
        this.bengali = bengali;
        return this;
    }

    public Marks setHindi(int hindi) {
        this.hindi = hindi;
        return this;
    }

    public Marks setComputer(int computer) {
        this.computer = computer;
        return this;
    }

    public Marks setEnglishI(int englishI) {
        this.englishI = englishI;
        return this;
    }

    public Marks setEnglishII(int englishII) {
        this.englishII = englishII;
        return this;
    }

    public Marks setDrawing(char drawing) {
        this.drawing = drawing;
        return this;
    }

    public Marks setGeography(int geography) {
        this.geography = geography;
        return this;
    }

    public Marks setGsc(int gsc) {
        this.gsc = gsc;
        return this;
    }

    public Marks setSst(int sst) {
        this.sst = sst;
        return this;
    }

    public Marks setStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    @Override
    public String toString() {
        return id + maths + physics + chemistry +
                biology + msc + gk + historyAndCivics + bengali + hindi +
                computer + englishI + englishII + drawing + geography + gsc + sst;
    }
}
