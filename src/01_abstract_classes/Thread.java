//How Actual looks like Our Thread Class

public class Thread implements Runnable{
   private Runnable target;

  //parameterise constructor 
  public Thread(Runnable r){
     this.target=r;
    }
@Override
  Public void run(){
       if(target!=null){
  target.run();//ye dusrea Run actuall object ka overide wala Run hoga jo object ham Runnable Interface ke help se bna rhe

}
  }
}




//Actual me to thread class me 3000-4000 line of code hai sara secuirty sb ka v hai lekin kuchh main niche tag kiya hun jo mere liye jruri hai
package java.lang;

public class Thread implements Runnable {

    // --- 1. Fields / Attributes ---
    private volatile String name;
    private int priority;
    private boolean daemon = false;
    private Runnable target;             // Jo Runnable hum pass karte hain
    private long tid;                    // Thread ID
    private static long threadSeqNumber; // Unique ID generate karne ke liye
    private volatile int threadStatus = 0; // State track karne ke liye (NEW, RUNNABLE, etc.)

    // Priority Constants
    public static final int MIN_PRIORITY = 1;
    public static final int NORM_PRIORITY = 5;
    public static final int MAX_PRIORITY = 10;

    // --- 2. Thread States (Enum) ---
    public enum State {
        NEW,
        RUNNABLE,
        BLOCKED,
        WAITING,
        TIMED_WAITING,
        TERMINATED;
    }

    // --- 3. Constructors ---
    public Thread() {
        init(null, "Thread-" + nextThreadNum(), 0);
    }

    public Thread(Runnable target) {
        init(target, "Thread-" + nextThreadNum(), 0);
    }

    public Thread(String name) {
        init(null, name, 0);
    }

    public Thread(Runnable target, String name) {
        init(target, name, 0);
    }

    // Common Internal Initialization logic
    private void init(Runnable target, String name, long stackSize) {
        Thread parent = currentThread(); // Jo thread isse create kar raha hai
        this.target = target;
        this.name = name;
        this.priority = parent.getPriority();
        this.daemon = parent.isDaemon();
        this.tid = nextThreadID();
    }

    private static synchronized long nextThreadNum() {
        return ++threadSeqNumber;
    }

    private static synchronized long nextThreadID() {
        return ++threadSeqNumber;
    }

    // --- 4. Core Execution Methods ---
    
    // OS-level thread banata hai aur run() ko trigger karta hai
    public synchronized void start() {
        if (threadStatus != 0) { // Agar thread already started hai
            throw new IllegalThreadStateException();
        }

        // JVM ko inform karta hai (Native Call)
        start0();
    }

    // Yeh native C++ method OS thread banakar background me run() ko call karta hai
    private native void start0();

    @Override
    public void run() {
        if (target != null) {
            target.run(); // Agar Runnable pass kiya tha, to uska code chalega
        }
    }

    // --- 5. Critical Static Methods ---

    // Abhi konsa thread chal raha hai, OS se fetch karke Thread object deta hai
    public static native Thread currentThread();

    // Current thread ko pause karne ke liye
    public static void sleep(long millis) throws InterruptedException {
        sleep0(millis);
    }
    private static native void sleep0(long millis) throws InterruptedException;

    // CPU scheduler ko hint deta hai ki doosre thread ko mauka de
    public static native void yield();

    // --- 6. Instance Control Methods ---

    public final void join() throws InterruptedException {
        join(0);
    }

    public final synchronized void join(long millis) throws InterruptedException {
        long base = System.currentTimeMillis();
        long now = 0;

        if (millis == 0) {
            while (isAlive()) {
                wait(0); // Dusre thread ke khatam hone tak wait karta hai
            }
        } else {
            while (isAlive()) {
                long delay = millis - now;
                if (delay <= 0) break;
                wait(delay);
                now = System.currentTimeMillis() - base;
            }
        }
    }

    // Check karta hai thread abhi chal raha hai ya finish ho gaya
    public final native boolean isAlive();

    public void interrupt() {
        // Thread ko sleep/wait se jagane ke liye flag set karta hai
        interrupt0();
    }
    private native void interrupt0();

    // --- 7. Getters & Setters ---

    public final String getName() {
        return name;
    }

    public final synchronized void setName(String name) {
        this.name = name;
    }

    public final int getPriority() {
        return priority;
    }

    public final void setPriority(int newPriority) {
        if (newPriority > MAX_PRIORITY || newPriority < MIN_PRIORITY) {
            throw new IllegalArgumentException();
        }
        this.priority = newPriority;
        setPriority0(newPriority);
    }
    private native void setPriority0(int newPriority);

    public final void setDaemon(boolean on) {
        if (isAlive()) {
            throw new IllegalThreadStateException();
        }
        this.daemon = on;
    }

    public final boolean isDaemon() {
        return daemon;
    }

    public long getId() {
        return tid;
    }

    public State getState() {
        // Native state code ko Java Enum me convert karta hai
        return State.values()[threadStatus];
    }
}
