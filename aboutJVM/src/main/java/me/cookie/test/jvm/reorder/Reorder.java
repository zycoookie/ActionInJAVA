package me.cookie.test.jvm.reorder;

/**
 * Created by cookie on 2017/6/9.
 */
public class Reorder implements Runnable {

    private boolean flag = false;

    @Override
    public void run() {
        while(!flag){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        this.flag = true;
    }

    public static void main(String[] args) throws InterruptedException {
        Reorder reorder = new Reorder();
        new Thread(reorder).start();
        Thread.sleep(1000);
        reorder.stop();
    }
}
