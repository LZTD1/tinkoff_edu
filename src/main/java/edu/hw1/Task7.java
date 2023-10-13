package edu.hw1;

public class Task7 {

    public static int rotateLeft(int n, int shift) {
        int bits = Integer.toBinaryString(n).length();
        return (n << shift | n >>> (bits - shift)) & ((1 << bits) - 1);
    }

    public static int rotateRight(int n, int shift) {
        int bits = Integer.toBinaryString(n).length();
        return (n >>> shift | n << (bits - shift)) & ((1 << bits) - 1);
    }

    public static void main(String[] args) {
        System.out.println(rotateRight(8, 1));  // 4
        System.out.println(rotateLeft(16, 1)); // 1
        System.out.println(rotateLeft(17, 2)); // 6
    }
}
