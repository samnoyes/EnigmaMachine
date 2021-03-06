import java.util.*;

public class Rotor {
    private char[] inAlphabet;
    private char[] outAlphabet;
    private int start;
    private boolean isReflector;
    private int ring;
    private int step;

    public Rotor(int num, int s, int r, boolean reflect) { //num=rotor number, s=rotor offset, r=ring setting
        inAlphabet = new char[26];
        outAlphabet = new char[26];
        start = s;
        ring = r;
        isReflector = reflect;
        for (int i = 0; i<26; i++) {
            inAlphabet[i] = (char)(65+i);
        }
        if (isReflector) {
            start = 0;//force start to be zero if reflector
            if (num == 1) {
                outAlphabet = "YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray();
            }
            else {
                outAlphabet = "FVPJIAOYEDRZXWGCTKUQSBNMHL".toCharArray();
            }
        }
        else {
            switch (num) {
                case 1:
                    outAlphabet = rot("EKMFLGDQVZNTOWYHXUSPAIBRCJ", r).toCharArray();
                    step = 17; //R
                    break;
                case 2:
                    outAlphabet = rot("AJDKSIRUXBLHWTMCQGZNPYFVOE", r).toCharArray();
                    step = 5; //F
                    break;
                case 3:
                    outAlphabet = rot("BDFHJLCPRTXVZNYEIWGAKMUSQO", r).toCharArray();
                    step = 22; //W
                    break;
                case 4:
                    outAlphabet = rot("ESOVPZJAYQUIRHXLNFTGKDCMWB", r).toCharArray();
                    step = 10; //K
                    break;
                default: //"case 5:"
                    outAlphabet = rot("VZBRGITYUPSDNHLXAWMJQOFECK", r).toCharArray();
                    step = 0; //A
                    break;
            }
        }
    }
    public char getOutput(char c) {
        char in = shift(c,start);
        int i = new String(inAlphabet).indexOf(Character.toString(in));
        if (i!=-1) {
            return shift(outAlphabet[i],26-start);
        }
        else {
            System.out.println("Something has gone terribly wrong: " + Character.toString(c));
            System.out.println("Here's the alphabet: " + Arrays.toString(inAlphabet));
            return '?';
        }
    }
    public char getReversedOutput(char c) {
        char in = shift(c,start);
        int i = new String(outAlphabet).indexOf(Character.toString(in));
        if (i!=-1) {
            return shift(inAlphabet[i],26-start);
        }
        else {
            System.out.println("Something has gone terribly wrong: " + Character.toString(c));
            System.out.println("Here's the alphabet: " + Arrays.toString(outAlphabet));
            return '?';
        }
    }
    public void increment() {
        if (start == 25) {
            start = 0;
        }
        else { start++; }
    }
    public boolean needIncrement() {
        return (start==(step+25)%26);
    }
    public int getPosition() {
        return start;
    }
    public String rot(String str, int i) {
        i=i%26;
        return (str.substring(i)+str.substring(0,i));
    }
    public char shift(char c, int shift) {
        int i=(int)c - 65;
        i=(i+shift) % 26;
        return (char)(i+65);
    }
}
