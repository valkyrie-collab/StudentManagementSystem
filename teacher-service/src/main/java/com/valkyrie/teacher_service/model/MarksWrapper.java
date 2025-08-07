package com.valkyrie.teacher_service.model;

public class MarksWrapper {
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

    public MarksWrapper setMaths(int maths) {
        this.maths = maths;
        return this;
    }

    public MarksWrapper setPhysics(int physics) {
        this.physics = physics;
        return this;
    }

    public MarksWrapper setMsc(int msc) {
        this.msc = msc;
        return this;
    }

    public MarksWrapper setBiology(int biology) {
        this.biology = biology;
        return this;
    }

    public MarksWrapper setChemistry(int chemistry) {
        this.chemistry = chemistry;
        return this;
    }

    public MarksWrapper setGk(int gk) {
        this.gk = gk;
        return this;
    }

    public MarksWrapper setHistoryAndCivics(int historyAndCivics) {
        this.historyAndCivics = historyAndCivics;
        return this;
    }

    public MarksWrapper setBengali(int bengali) {
        this.bengali = bengali;
        return this;
    }

    public MarksWrapper setHindi(int hindi) {
        this.hindi = hindi;
        return this;
    }

    public MarksWrapper setComputer(int computer) {
        this.computer = computer;
        return this;
    }

    public MarksWrapper setEnglishI(int englishI) {
        this.englishI = englishI;
        return this;
    }

    public MarksWrapper setEnglishII(int englishII) {
        this.englishII = englishII;
        return this;
    }

    public MarksWrapper setDrawing(char drawing) {
        this.drawing = drawing;
        return this;
    }

    public MarksWrapper setGeography(int geography) {
        this.geography = geography;
        return this;
    }

    public MarksWrapper setGsc(int gsc) {
        this.gsc = gsc;
        return this;
    }

    public MarksWrapper setSst(int sst) {
        this.sst = sst;
        return this;
    }
}
