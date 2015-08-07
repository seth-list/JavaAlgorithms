/**
 * Auto Generated Java Class.
 */
public class Key implements Comparable<Key>
{
  
  public Character name;
  
  public Key(char ch)
  {
    name = ch;
  }
  
  public char getKey()
  {
    return name;
  } 
  
       
  public int compareTo(Key other) 
  {
    return name.compareTo(other.name);
  }
  
}
