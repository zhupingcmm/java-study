package com.hero.jvm.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeapInstance {

    public static void main(String[] args) {
        List<Picture> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.add(new Picture(new Random().nextInt(1024 * 1024)));
        }
    }
    static class Picture {
        private byte[] pixels;
        public Picture(int length){
            this.pixels = new byte[length];
        }
    }
}
