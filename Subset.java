/**
 * Auto Generated Java Class.
 */
public class Subset 
{
  
  public static void main(String[] args) 
  { 
    
    int k = Integer.parseInt(args[0]); 
    
    RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
    String main = StdIn.readLine();
    StdOut.println(main);
    
    String[] a = main.split(" ");
    
    for(String elem : a)
    rq.enqueue(elem);  
    
    for (int i = 0; i < k; i++) 
    StdOut.println(rq.dequeue());

    
    /*
      int N = Integer.parseInt(args[0]); 
      int sum = 0; 
      for (int i = 0; i < N; i++) 
         sum += StdIn.readInt(); 
      StdOut.println("Sum is " + sum); 
      
      */
  }
  
  
}
