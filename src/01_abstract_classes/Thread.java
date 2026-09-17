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
