import java.lang.*;
import java.util.*;

/**
 * Auto Generated Java Class.
 */
public class Deque<Item> implements Iterable<Item>
{
  
   // construct an empty deque
   public Deque() 
   { 
     /* YOUR CONSTRUCTOR CODE HERE*/
   }
  
   public boolean isEmpty()                 // is the deque empty?
   {
     
   }
   public int size()                        // return the number of items on the deque
   {
     
   }
   
   public void addFirst(Item item)          // add the item to the front
   {
       if (item == null)
         throw java.lang.NullPointerException;
       
   }
   
   public void addLast(Item item)           // add the item to the end
   {
      if (item == null)
         throw java.lang.NullPointerException;
   }
   
   public Item removeFirst()                // remove and return the item from the front
   {
     if (isEmpty())
         throw java.util.NoSuchElementException ;
   }
   
   public Item removeLast()                 // remove and return the item from the end
   {
     if (isEmpty())
         throw java.util.NoSuchElementException ;
   }
   
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
     
   }
   public static void main(String[] args)   // unit testing
   {
     
   }
  
  
  public static void main(String[] args) { 
    
  }
  
  /* ADD YOUR CODE HERE */
  
}
