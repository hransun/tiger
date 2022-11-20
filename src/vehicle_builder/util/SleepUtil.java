package vehicle_builder.util;


public class SleepUtil  implements Runnable{
    long  duration;
    public SleepUtil(long duration) {
        this.duration = duration;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(duration);
        }
        catch (InterruptedException e) {
            System.out.println("Interrupted.");

        }
    }
}
