import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.util.Iterator; 
/**
 * Auto Generated Java Class.
 */
public class RandomizedQueue<Item> implements Iterable<Item>
{
  
    private Item[] a;         // array of items
    private int N;            // number of elements on stack
  
  
    public RandomizedQueue() 
    { 
       a = (Item[]) new Object[2];
    }
  
  
   public boolean isEmpty()                 // is the queue empty?
   { 
     return N == 0;
   }
   
   
   public int size()                        // return the number of items on the queue
   { 
     return N;
   }  
     
   public void enqueue(Item item)           // add the item
   { 
     if (item == null)
         throw new NullPointerException();
     
     
     //System.out.println("N b= " + N);
     //System.out.println("a.length = " + a.length);
     
     //added new element     
     if (N == a.length) 
     {  
        Item[] temp = (Item[]) new Object[2*a.length];
        for (int i = 0; i < N; i++) 
        {
            temp[i] = a[i];
        }
        a = temp;
     } 
     
     a[N++] = item;
     
     //System.out.println("N e= " + N);
     //System.out.println("a.length = " + a.length);
     
   } 
   
   public Item dequeue()                    // remove and return a random item
   { 
     if (isEmpty())
       throw new NoSuchElementException();
     
     int rnd = StdRandom.uniform(N);
     
     if (isEmpty()) throw new NoSuchElementException("Stack underflow");
     Item item = a[rnd];
     a[rnd] = null;                              // to avoid loitering
          
     for (int i = rnd; i < N - 1; i++) 
     {
         a[i] = a[i+1];
     }
     
     a[N-1] = null;                              // to avoid loitering
     N--;
     
     // shrink size of array if necessary
     if (N > 0 && N == a.length/4)
     {
        Item[] temp = (Item[]) new Object[a.length/2];
        for (int i = 0; i < N; i++) 
        {
            temp[i] = a[i];
        }
        a = temp;
       
     }
     return item;

   }
   
   public Item sample()                     // return (but do not remove) a random item
   { 
     if (isEmpty())
       throw new NoSuchElementException();
     
     int rnd = StdRandom.uniform(N);
     Item item = a[rnd];
        
     return item;
   }  
     
   
   
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   { 
     return new RandomArrayIterator();
   }
  
  
     // an iterator, doesn't implement remove() since it's optional
    private class RandomArrayIterator implements Iterator<Item> 
    {  
        private int i;
        private Item[] temp;

        
        //создание итератора
        public RandomArrayIterator() 
        {
            temp = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) 
            {
              temp[i] = a[i];
            }
            StdRandom.shuffle(temp);
            i = 0;            
        }

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            return temp[i++];
        }
    }

  
  public static void main(String[] args) 
  { 
     RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
     
     d.enqueue(1);
     //System.out.println("size 1 = " + d.size());
     d.enqueue(2); //работает
     //System.out.println("size 2 = " + d.size());
     
     d.enqueue(3); //2
     //System.out.println("size 3 = " + d.size());
     
     d.enqueue(4); //работает
     
     d.enqueue(5); //4
     
     d.enqueue(6); // 4
     
     d.enqueue(7); //6
     
     d.enqueue(8); //работает
     
     d.enqueue(9); //4
     
     d.enqueue(10);
     d.enqueue(11); //6
     d.enqueue(12);
     d.enqueue(13);
     d.enqueue(14);
     d.enqueue(15);
     d.enqueue(16);
     d.enqueue(17);
     d.enqueue(18);
     d.enqueue(19);
     d.enqueue(20);
     d.enqueue(21);
     d.enqueue(22);
     d.enqueue(23);
     d.enqueue(24);
     
     
     System.out.println("size = " + d.size());
     Iterator itr = d.iterator();
     while(itr.hasNext()) 
     {
         Object element = itr.next();
         System.out.print(element + " ");
     }
     System.out.println();
     
     
     System.out.println("size = " + d.size());
     Iterator itr0 = d.iterator();
     while(itr0.hasNext()) 
     {
         Object element = itr0.next();
         System.out.print(element + " ");
     }
     System.out.println();
     
     System.out.println (d.dequeue());
     System.out.println (d.dequeue());
     System.out.println (d.dequeue());
     System.out.println (d.dequeue());
     System.out.println (d.dequeue());
     System.out.println (d.dequeue());
     System.out.println (d.dequeue());
     System.out.println (d.dequeue());
     System.out.println (d.dequeue());
     
     System.out.println("size = " + d.size());
     Iterator itr1 = d.iterator();
     while(itr1.hasNext()) 
     {
         Object element = itr1.next();
         System.out.print(element + " ");
     }
     System.out.println();
     
  }
  
  /* ADD YOUR CODE HERE */
  
}
