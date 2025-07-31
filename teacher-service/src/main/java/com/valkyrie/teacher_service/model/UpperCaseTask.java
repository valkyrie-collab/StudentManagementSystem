package com.valkyrie.teacher_service.model;

public class UpperCaseTask implements Runnable {
    private String word;

    private UpperCaseTask(String word) {this.word = word;}

    @Override
    public void run() {
        char[] wordArray = word.toCharArray(); int size = wordArray.length;
        wordArray[0] = Character.toUpperCase(wordArray[0]);

        for (int i = 1; i < size; i++) {
            wordArray[i] = Character.toLowerCase(wordArray[i]);
        }

        word = new String(wordArray);
    }

    public static UpperCaseTask initialize(String word) {
        return new UpperCaseTask(word);
    }

    public String getWord() {return word;}
}
