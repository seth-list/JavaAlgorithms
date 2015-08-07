import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.util.Iterator; 

/**
 * Auto Generated Java Class.
 */


//максимальный элемент впереди

public class PriorityQueue<Item extends Comparable<Item>> implements Iterable<Item>
{
  
    private Item[] a;         // array of items
    private int N;            // number of elements
    
    
    //конструктор
    public PriorityQueue() 
    { 
       a = (Item[]) new Comparable[2];
    }
    
    
   public boolean isEmpty()                 // is the queue empty?
   { 
     return N == 0;
   }
   
   
   public int size()                        // return the number of items on the queue
   { 
     return N;
   }  
     
   //повышение приоритета, если нужно
   private void swim(int number)
   {
      //пока это не корень - a[0] - корень
      //и пока родительский элемент меньше - обменять
      //для 0 - дети 1 и 2
      //для 1 - дети 3 и 4, для 2 - дети 5 и 6
      //если передается 1, то (1 - 1)/2 = 0
      //tckb передается 2, то (2 - 1)/2 = 0
      //если передается 3, то (3 - 1)/2 = 1
      //если передается 4, то (4 - 1)/2 = 1
      //если передается 5, то (5 - 1)/2 = 2   
      //если передается 6, то (6 - 1)/2 = 2
      while(number > 0 && less((number - 1)/2, number))
      {
        exch(number, (number - 1)/2); //обмен дочернего элемента с родительским
        number = (number - 1)/2;      //проверка элемента после перехода вверх (пример: 4 становится 1) 
      }
                          
   }
   
   //понижение приоритета, если нужно
   private void sink(int number)
   {
      //пока существуют дочерние элементы, дочерние элементы 2*number + 1 и 2*number + 2
      //если элемент один, то длина N = 1
      //то передается number = 0
      //если есть 0, 1 и 2 
      //то передается 1, и N = 3
      //когда передается 0, то надо хотя бы 2
      //когда передается 1, то надо хотя бы 4
      //когда передается 2, то надо хотя бы 6
      //2*(number + 1)      
      while(2*(number + 1) <= N)
      {
        int j = 2*number + 1; //первый дочерний элемент
        
        if (j + 1 < N && less(j, j+1)) //если существует второй дочерний элемент, это означает что j < N
        j++;                       //существует a[j+1], если меньше a[j+1], чем a[j], то надо менять с ним j++
       
        
        //если очередной элемент по номеру не меньше чем один из дочерних, а больше
        //то значит порядок восстановлен, родительский элемент должен быть больше
        if (!less(number, j)) 
        break;
        
        //иначе надо обменять родительский с дочерним
        exch(number, j);
        //и проверять уже дальше, номер изменился после обмена
        number = j;        
      }
      
   }
   
   
   
   
   public void insert(Item item)           // add the item
   { 
     if (item == null)
       throw new NullPointerException();
     
     //System.out.println("N1 = " + N + ", A = " + a.length);
     
     //увеличение массива, если нужно
     if (N == a.length) 
     {  
       Item[] temp = (Item[]) new Comparable[2*a.length]; //временный массив больше в два раза
       for (int i = 0; i < N; i++) //перезапись
       {
         temp[i] = a[i];
       }
       a = temp; //присвоение новому массиву значения
     } 
     
     //System.out.println("N2 = " + N + ", A = " + a.length);
     
     //теперь само добавление в очередь приоритетов
     //вначале увеличивается N - количество элементов
     a[N] = item;
     N++;
     //повышается приоритет, если нужно
     swim(N - 1);
     
     //System.out.println("N3 = " + N + ", A = " + a.length);
    
   } 
   
   
   
   public Item delMax()                    // remove and return a min item
   { 
          
     if (isEmpty()) 
       throw new NoSuchElementException("Stack underflow");   
     
     //System.out.println("ND1 = " + N + ", A = " + a.length);
    
     Item item = a[0]; 
     //поменять первый - минимальный с последним
     exch(0, --N);
     sink(0); //понизить приоритет нового первого     
     a[N] = null;    // to avoid loitering
     
     //System.out.println("ND2 = " + N + ", A = " + a.length);
     
     // shrink size of array if necessary
     if (N > 0 && N == a.length/4)
     {
        Item[] temp = (Item[]) new Comparable[a.length/2];
        for (int i = 0; i < N; i++) 
        {
            temp[i] = a[i];
        }
        a = temp;
       
     }
     
     //System.out.println("ND3 = " + N + ", A = " + a.length);
     
     return item;

   }
   
   //обмен элементов
   private void exch(int first, int last)
   {
     Item buf = a[first]; a[first] = a[last]; a[last] = buf;
   }
   
   //сравнение
   private boolean less(int i, int j)
   { 
     return a[i].compareTo(a[j]) < 0; 
   }
   
   //вернуть итератор
   public Iterator<Item> iterator()         // return an iterator over items
   { 
     return new PriorityIterator();
   }
  
   
   
  
    // an iterator, doesn't implement remove() since it's optional
    private class PriorityIterator implements Iterator<Item> 
    {  
        private int i;
        private Item[] temp;

        
        //создание итератора
        public PriorityIterator() 
        {
            temp = (Item[]) new Comparable[N];
            for (int i = 0; i < N; i++) 
            {
              temp[i] = a[i];
            }        
        }

        public boolean hasNext() 
        {
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
     
    
     PriorityQueue<Key> d = new PriorityQueue<Key>();
     System.out.println("size = " + d.size());
     
     d.insert(new Key('1'));
     
     System.out.println("size = " + d.size());
     
     d.insert(new Key('2'));
     
     System.out.println("size = " + d.size());
     
     d.insert(new Key('3')); 
     d.insert(new Key('4')); 
     d.insert(new Key('5'));
     d.insert(new Key('6'));
     d.insert(new Key('7')); 
     d.insert(new Key('8'));
     
     
     Iterator itr = d.iterator();
     while(itr.hasNext()) 
     {
         Key element = (Key)itr.next();
         System.out.print("element: " + element.getKey() + " ");
     }
     System.out.println();
     
     
     Key element = (Key)d.delMax();
     System.out.println (element.getKey());
   
     element = (Key)d.delMax();
     System.out.println (element.getKey());
     
     element = (Key)d.delMax();
     System.out.println (element.getKey());
     
     element = (Key)d.delMax();
     System.out.println (element.getKey());
     
     element = (Key)d.delMax();
     System.out.println (element.getKey());
     
     element = (Key)d.delMax();
     System.out.println (element.getKey());
     
     element = (Key)d.delMax();
     System.out.println (element.getKey());
     
     d.insert(new Key('2'));
     
     System.out.println("size = " + d.size());
     
     d.insert(new Key('3')); 
     d.insert(new Key('4')); 
     d.insert(new Key('5'));
     d.insert(new Key('6'));
     d.insert(new Key('7')); 
     d.insert(new Key('8'));
     
     Iterator itr1 = d.iterator();
     while(itr1.hasNext()) 
     {
         Key element1 = (Key)itr1.next();
         System.out.print("element: " + element1.getKey() + " ");
     }
     System.out.println();
     
     /*

     element = (Key)d.delMax();
     System.out.println (element.getKey());
     element = (Key)d.delMax();
     System.out.println (element.getKey());
     element = (Key)d.delMax();
     System.out.println (element.getKey());
     */
     

     
  }
   
   
   
   
   
   
    
  
}
