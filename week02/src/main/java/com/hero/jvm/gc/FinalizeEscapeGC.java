package com.hero.jvm.gc;

public class FinalizeEscapeGC {
    public static FinalizeEscapeGC saveHook = null;
    public void isAlive () {
        System.out.println("你愁啥呢？哥还活着");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("执行了 finalize 方法() !");
        FinalizeEscapeGC.saveHook = this;
    }

    public static void main(String[] args) throws InterruptedException {
        saveHook = new FinalizeEscapeGC();

        saveHook = null;
        System.gc();

        Thread.sleep(500);

        if(saveHook != null) {
            saveHook.isAlive();
        } else {
            System.out.println(" oh no ,哥已经死了");
        }
    }
}
