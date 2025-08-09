package com.valkyrie.marks_service.model;

public class MarksWrapper {
    private Integer maths;
    private Integer physics;
    private Integer chemistry;
    private Integer biology;
    private Integer msc;
    private Integer gk;
    private Integer historyAndCivics;
    private Integer bengali;
    private Integer hindi;
    private Integer computer;
    private Integer englishI;
    private Integer englishII;
    private char drawing;
    private Integer geography;
    private Integer gsc;
    private Integer sst;
    private String term;

    public Integer getMaths() {return maths;}

    public Integer getPhysics() {return physics;}

    public Integer getChemistry() {return chemistry;}

    public Integer getBiology() {return biology;}

    public Integer getMsc() {return msc;}

    public Integer getGk() {return gk;}

    public Integer getBengali() {return bengali;}

    public Integer getHindi() {return hindi;}

    public Integer getComputer() {return computer;}

    public Integer getHistoryAndCivics() {return historyAndCivics;}

    public Integer getEnglishI() {return englishI;}

    public Integer getEnglishII() {return englishII;}

    public char getDrawing() {return drawing;}

    public Integer getGeography() {return geography;}

    public String getTerm() {return term;}

    public Integer getGsc() {return gsc;}

    public Integer getSst() {return sst;}

    public MarksWrapper setMaths(Integer maths) {
        this.maths = maths;
        return this;
    }

    public MarksWrapper setPhysics(Integer physics) {
        this.physics = physics;
        return this;
    }

    public MarksWrapper setMsc(Integer msc) {
        this.msc = msc;
        return this;
    }

    public MarksWrapper setBiology(Integer biology) {
        this.biology = biology;
        return this;
    }

    public MarksWrapper setChemistry(Integer chemistry) {
        this.chemistry = chemistry;
        return this;
    }

    public MarksWrapper setGk(Integer gk) {
        this.gk = gk;
        return this;
    }

    public MarksWrapper setHistoryAndCivics(Integer historyAndCivics) {
        this.historyAndCivics = historyAndCivics;
        return this;
    }

    public MarksWrapper setBengali(Integer bengali) {
        this.bengali = bengali;
        return this;
    }

    public MarksWrapper setHindi(Integer hindi) {
        this.hindi = hindi;
        return this;
    }

    public MarksWrapper setComputer(Integer computer) {
        this.computer = computer;
        return this;
    }

    public MarksWrapper setEnglishI(Integer englishI) {
        this.englishI = englishI;
        return this;
    }

    public MarksWrapper setEnglishII(Integer englishII) {
        this.englishII = englishII;
        return this;
    }

    public MarksWrapper setDrawing(char drawing) {
        this.drawing = drawing;
        return this;
    }

    public MarksWrapper setGeography(Integer geography) {
        this.geography = geography;
        return this;
    }

    public MarksWrapper setTerm(String term) {
        this.term = term;
        return this;
    }

    public MarksWrapper setGsc(Integer gsc) {
        this.gsc = gsc;
        return this;
    }

    public MarksWrapper setSst(Integer sst) {
        this.sst = sst;
        return this;
    }
}
