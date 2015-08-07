import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.util.Iterator; 

/**
 * Auto Generated Java Class.
 */


//������������ ������� �������

public class PriorityQueue<Item extends Comparable<Item>> implements Iterable<Item>
{
  
    private Item[] a;         // array of items
    private int N;            // number of elements
    
    
    //�����������
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
     
   //��������� ����������, ���� �����
   private void swim(int number)
   {
      //���� ��� �� ������ - a[0] - ������
      //� ���� ������������ ������� ������ - ��������
      //��� 0 - ���� 1 � 2
      //��� 1 - ���� 3 � 4, ��� 2 - ���� 5 � 6
      //���� ���������� 1, �� (1 - 1)/2 = 0
      //tckb ���������� 2, �� (2 - 1)/2 = 0
      //���� ���������� 3, �� (3 - 1)/2 = 1
      //���� ���������� 4, �� (4 - 1)/2 = 1
      //���� ���������� 5, �� (5 - 1)/2 = 2   
      //���� ���������� 6, �� (6 - 1)/2 = 2
      while(number > 0 && less((number - 1)/2, number))
      {
        exch(number, (number - 1)/2); //����� ��������� �������� � ������������
        number = (number - 1)/2;      //�������� �������� ����� �������� ����� (������: 4 ���������� 1) 
      }
                          
   }
   
   //��������� ����������, ���� �����
   private void sink(int number)
   {
      //���� ���������� �������� ��������, �������� �������� 2*number + 1 � 2*number + 2
      //���� ������� ����, �� ����� N = 1
      //�� ���������� number = 0
      //���� ���� 0, 1 � 2 
      //�� ���������� 1, � N = 3
      //����� ���������� 0, �� ���� ���� �� 2
      //����� ���������� 1, �� ���� ���� �� 4
      //����� ���������� 2, �� ���� ���� �� 6
      //2*(number + 1)      
      while(2*(number + 1) <= N)
      {
        int j = 2*number + 1; //������ �������� �������
        
        if (j + 1 < N && less(j, j+1)) //���� ���������� ������ �������� �������, ��� �������� ��� j < N
        j++;                       //���������� a[j+1], ���� ������ a[j+1], ��� a[j], �� ���� ������ � ��� j++
       
        
        //���� ��������� ������� �� ������ �� ������ ��� ���� �� ��������, � ������
        //�� ������ ������� ������������, ������������ ������� ������ ���� ������
        if (!less(number, j)) 
        break;
        
        //����� ���� �������� ������������ � ��������
        exch(number, j);
        //� ��������� ��� ������, ����� ��������� ����� ������
        number = j;        
      }
      
   }
   
   
   
   
   public void insert(Item item)           // add the item
   { 
     if (item == null)
       throw new NullPointerException();
     
     //System.out.println("N1 = " + N + ", A = " + a.length);
     
     //���������� �������, ���� �����
     if (N == a.length) 
     {  
       Item[] temp = (Item[]) new Comparable[2*a.length]; //��������� ������ ������ � ��� ����
       for (int i = 0; i < N; i++) //����������
       {
         temp[i] = a[i];
       }
       a = temp; //���������� ������ ������� ��������
     } 
     
     //System.out.println("N2 = " + N + ", A = " + a.length);
     
     //������ ���� ���������� � ������� �����������
     //������� ������������� N - ���������� ���������
     a[N] = item;
     N++;
     //���������� ���������, ���� �����
     swim(N - 1);
     
     //System.out.println("N3 = " + N + ", A = " + a.length);
    
   } 
   
   
   
   public Item delMax()                    // remove and return a min item
   { 
          
     if (isEmpty()) 
       throw new NoSuchElementException("Stack underflow");   
     
     //System.out.println("ND1 = " + N + ", A = " + a.length);
    
     Item item = a[0]; 
     //�������� ������ - ����������� � ���������
     exch(0, --N);
     sink(0); //�������� ��������� ������ �������     
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
   
   //����� ���������
   private void exch(int first, int last)
   {
     Item buf = a[first]; a[first] = a[last]; a[last] = buf;
   }
   
   //���������
   private boolean less(int i, int j)
   { 
     return a[i].compareTo(a[j]) < 0; 
   }
   
   //������� ��������
   public Iterator<Item> iterator()         // return an iterator over items
   { 
     return new PriorityIterator();
   }
  
   
   
  
    // an iterator, doesn't implement remove() since it's optional
    private class PriorityIterator implements Iterator<Item> 
    {  
        private int i;
        private Item[] temp;

        
        //�������� ���������
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
