public class ThreadPractice{
   public static void main(String args[]){

      System.out.println("Below Output Generated Through Another Thread btw current Thread  is-"+Thread.currentThread().getName());
      Chocklet c=new Chocklet();
      c.start();//start method andar se khud run() method cal kr lega aur expected output chocklte wala aayega
      Biscuit b=new Biscuit();
      Thread t=new Thread(b);
      t.start();
       
   }

}
//ek Thread thread class ke help se bnaunga ek Runnable ke hep se
//1  Thread class ke help se bnataa hun
class Chocklet extends Thread{
//ab extends kiya hai to run() ko v ovveride krega n taki run block me apna Thread ka kam krwa sakun
   @Override
   public void run(){
         System.out.println("Main chocklet wala Thread hun re ! ");
         System.out.println("Mera chocklet,Mera Thread nam "+Thread.currentThread().getName()+" hai re");

   }


}

//Note-1.run() manually call karne ki zaroorat nahi hoti. start() method ka contract hi yahi hai: naya thread banao aur us naye thread ke andar run() ko trigger karke kaam shuru karwa do.

//biscut bna leta hun jo Runnable ko implement kr ke Thread creeate krega
class Biscuit implements Runnable {

   
@Override
public void run(){

   System.out.println("Main Biscuit wala Thread hun re mere thread name "+Thread.currentThread().getName()+" hai re...");
}
}
