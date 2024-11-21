package com.example.gambling;

public class SlotsMachine {

    private int[] reels;

    public SlotsMachine() {
        // 😮😮😮😮😮 gambling
    }

    public SlotsMachine(int amountOfReels) {
        reels = new int[amountOfReels];
    }


    public int[] roll() {
        for(int i = 0; i < reels.length; i++){
            reels[i] = (int) (Math.random() * (6 - 1) + 1);
        }
        return reels;
    }

    public int countReels() {
        int sum = 0;
        for(int a : reels) {
            sum = sum + a;
        }
        return sum;
    }

    public void setReels(int amountOfReels) {
        reels = new int[amountOfReels];
    }

    public void reset() {
        reels = new int[reels.length];
    }

}
