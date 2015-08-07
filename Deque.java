import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.util.Iterator; 


/**
 * Auto Generated Java Class.
 */
public class Deque<Item> implements Iterable<Item>
{
  
   private int N; //length of deque
   public Node first;    // top of stack
   public Node last;     // bottom of stack
   
  
    // helper linked list class
    private class Node 
    {
        public Item item;
        public Node next;
        public Node prev;
    }

     
   // construct an empty deque
   public Deque() 
   {      
        first = null;
        last = null;
        N = 0;
   }
  
   public boolean isEmpty()                 // is the deque empty?
   {
     return N == 0;
   }
   
   public int size()                        // return the number of items on the deque
   {
     return N;
   }
   
   public void addFirst(Item item)          // add the item to the front
   {
       if (item == null)
         throw new NullPointerException();
       
       //first make new node from the beginning
       if(isEmpty()) 
       {
         first = new Node();
         first.item = item;
         first.next = null;
         first.prev = null;
         last = first; //last and first is same
       }
       else
       {         
         Node oldfirst = first;
         first = new Node(); //new node becomes first
         first.item = item;
         first.next = oldfirst; 
         first.prev = null;
         oldfirst.prev = first;
       }
       N++;
   }
   
   public void addLast(Item item)           // add the item to the end
   {
      if (item == null)
         throw new NullPointerException();
      
      if(isEmpty())
      {
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = null;
        first = last; //first and last same
      }
      else
      {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        oldlast.next = last;
      }
      N++;      
   }
   
   public Item removeFirst()                // remove and return the item from the front
   {
     if (isEmpty())
       throw new NoSuchElementException();
     
     Item item = first.item;
     first = first.next;
     N--;
          
     if(isEmpty()) last = null;  
     else first.prev = null;                    
     return item;
   }
   
   public Item removeLast()                 // remove and return the item from the end
   {
     if (isEmpty())
       throw new NoSuchElementException();
     
     Item item = last.item;
     last = last.prev;
     N--;
          
     if(isEmpty()) first = null;
     else last.next = null;
     return item;
              
   }
   
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
     return new ListIterator();
   }
   
    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> 
    {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
   
   
   
   public static void main(String[] args)   // unit testing
   {
     Deque<Integer> d = new Deque<Integer>();
          
     d.addFirst(1);
     System.out.println ("element added size = " + d.size());
     d.removeLast();
     System.out.println ("element added size = " + d.size());
     d.addFirst(1);
     d.addFirst(2);
     d.addFirst(3);
     d.addFirst(4);
     d.addFirst(5);
     d.addFirst(6);
     d.addFirst(7);
     d.addFirst(8);
     
     System.out.print("Original contents of d: ");
     Iterator itr = d.iterator();
     while(itr.hasNext()) 
     {
         Object element = itr.next();
         System.out.print(element + " ");
     }
     System.out.println();
     
     
     
     System.out.println ("element added size = " + d.size());
     System.out.println (d.removeLast());
     System.out.println (d.removeLast());
     System.out.println (d.removeLast());
     System.out.println (d.removeLast());
     System.out.println (d.removeLast());
     System.out.println (d.removeLast());
     System.out.println (d.removeLast());
     System.out.println (d.removeLast());
     d.addFirst(1);
     d.addFirst(2);
     d.addFirst(3);
     d.addFirst(4);
     d.addFirst(5);
     d.addFirst(6);
     d.addFirst(7);
     d.addFirst(8);
     System.out.println ("element added size = " + d.size());
     System.out.println (d.removeFirst());
     System.out.println (d.removeFirst());
     System.out.println (d.removeFirst());
     System.out.println (d.removeFirst());
     System.out.println (d.removeFirst());
     System.out.println (d.removeFirst());
     System.out.println (d.removeFirst());
     System.out.println (d.removeFirst());
     System.out.println ("element added size = " + d.size());    
   }  
  
  /* ADD YOUR CODE HERE */
  
}
