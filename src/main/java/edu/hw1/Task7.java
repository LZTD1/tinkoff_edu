package edu.hw1;

public class Task7 {
    public Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        int bits = Integer.toBinaryString(n).length();
        return (n << shift | n >>> (bits - shift)) & ((1 << bits) - 1);
    }

    public static int rotateRight(int n, int shift) {
        int bits = Integer.toBinaryString(n).length();
        return (n >>> shift | n << (bits - shift)) & ((1 << bits) - 1);
    }
}
