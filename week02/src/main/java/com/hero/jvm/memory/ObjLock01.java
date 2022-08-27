package com.hero.jvm.memory;

import org.openjdk.jol.info.ClassLayout;

public class ObjLock01 {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println("new object" + ClassLayout.parseInstance(o).toPrintable());
    }
}
