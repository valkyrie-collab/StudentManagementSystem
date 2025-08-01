package com.valkyrie.student_service.model;

public class UpperCaseTask implements Runnable {
    private String word;

    private UpperCaseTask(String word) {
        this.word = word;
    }

    @Override
    public void run() {
        char[] wordArray = word.toCharArray();
        wordArray[0] = Character.toUpperCase(wordArray[0]);

        for (int i = 1; i < wordArray.length; i++) {
            wordArray[i] = Character.toLowerCase(wordArray[i]);
        }

        word = new String(wordArray);
    }

    public static UpperCaseTask initialize(String word) {return new UpperCaseTask(word);}

    public String getWord() {return word;}
}
