/*
Coding Assignment: "Data Pipeline Task"

Ek pipeline banao jisme Data Fetch pehle ho, aur Data Process uske baad hi start ho.

Thread 1 (Data Fetcher):

Thread class extend karke ya lambda se banao.

Print karo: "Fetching data from database..."

Thread.sleep(2000) lagao.

Print karo: "Data fetched successfully!"

Thread 2 (Data Processor):

Lambda se ek thread banao.

Print karo: "Processing the fetched data..."

Print karo: "Data processing completed!"

Main Method Requirement:

Dono threads ke beech join() use karo taaki Processor wala thread tabhi start ho ya aage badhe jab Fetcher poora finish ho jaye.

Fetcher ke start hone se pehle, dauran, aur baad me isAlive() check karke print karo.
*/





//CODE===)

public class Pipeline {

   public static void main(String[] args) throws InterruptedException{

     System.out.println("Hllo ye main Thread ka sabse pahla instruction chal rha es thread ka nam hai - "+Thread.currentThread().getName());
     //ab dono thread bna leta hun fir start and execution order dekhunga
     DataFetcher df= new DataFetcher();
     DataProcessor dp=new DataProcessor();
     Thread dpp=new Thread(dp);
     df.start();
     df.join();
     dpp.start();
     dpp.join();
     System.out.println("Our Desire output Should be ->Hllo ye main Thread ka sabse pahla instruction chal rha es thread ka nam hai->Fetching data from database..->Data fetched successfully!->Processing the fetched data...->Data processing completed agar aisa aa rha to samjho sab shi hai re Halu!");

   }

}


//es k0 Thread class ke help se bna leta hun
class DataFetcher extends Thread{

    @Override
    public void run(){
      System.out.println("Fetching data from database... ");
     try{Thread.sleep(3000);}
        catch(Exception e){System.out.println("Main Exception hun re !");}
      System.out.println("Data fetched successfully!");                
    }

}

//Esko Runnable imlement kr ke bna lete hain object aur fir use Thread ke parameterise condtructor me pass kr ke Thread bna lenge
class DataProcessor implements Runnable{

       @Override
      public void run(){
          System.out.println("Processing the fetched data...");
          System.out.println("Data processing completed");
      }

}



















