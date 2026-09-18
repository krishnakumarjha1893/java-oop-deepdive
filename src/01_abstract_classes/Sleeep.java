//esme ham sleep() and  methode ko 

public class Main{

    public static void main(String args[]){
          PaymentService p=new PaymentService();
          InvoiceService i=new InvoiceService();
          Thread t2=new Thread(i);
          p.start();
          t2.start();

    }


}
//ek class niche Thread extends kr ke bnata hun aur ek runnable interface ko imlements kr ke bnate hain

class PaymentService extends Thread{

 @Override
  public void run(){
     System.out.println("Processing payment for ₹5000");
    try{
      Thread.sleep(10000);
    }
    catch(Exception e){System.out.println("Main Exception hun re...");}
    System.out.println("Payment SUCCESSFUL by: "+Thread.currentThread().getName());
  }
  
}


class InvoiceService implements Runnable{


  @Override
  public void run(){
      System.out.println("Generating PDF Invoice...");
     try{
      Thread.sleep(10000);
    }
    catch(Exception e){System.out.println("Main Exception hun re...");}
    System.out.println("Invoice Generated & Sent by: "+Thread.currentThread().getName());
  }


}





/*
public class SleepDemo {
    public static void main(String[] args) {
        Thread timerThread = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Tick: " + i);
                try {
                    Thread.sleep(1000); // 1 second ka pause
                } catch (InterruptedException e) {
                    System.out.println("Sleep interrupt ho gaya!");
                }
            }
        });

        timerThread.start();
    }
}
*/

/*
2. sleep() ko lekar key points

Non-blocking for other threads: Jab PaymentService sleep kar raha hota hai, wo InvoiceService ko block nahi karta. Dono alag-alag CPU cores/threads par independently pause hote hain.

Duration: 10000 milliseconds = 10 full seconds. Testing ke waqt time kam rakh sakte ho (jaise 1500 ms ya 1000 ms) taaki console par result jaldi dikhe.

3. Agar chahte ho ki Payment khatam hone ke baad hi Invoice shuru ho (join method):

Real life me bina payment successful huye invoice generate nahi honi chahiye. Us case me join() lagaya jata hai:

public class Main {
    public static void main(String args[]) throws InterruptedException {
        PaymentService p = new PaymentService();
        InvoiceService i = new InvoiceService();
        Thread t2 = new Thread(i);

        p.start();
        p.join(); // main thread ruk jayega jab tak payment khatam na ho

        t2.start(); // Payment complete hone ke baad hi invoice thread chalega
    }
}

*/
