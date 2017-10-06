import java.util.LinkedList;
import java.lang.*;
//a class called HashMap201 that will be used to map Strings 
//to Strings. (The intention is to use it to map performers 
//to biographies.) The map is implemented as a hash table with 
//chaining via linked lists.
public class Hashmap201 
{
   //private LinkedList<Entry> myList;
   private final static int Arraysize = 400;
   private LinkedList<Entry>[] array = new LinkedList[Arraysize];
   private class Entry
   { 
      String key;
      String value;
      
      public Entry(String key, String value)
      {
         this.key = key;
         this.value = value;
      } 
   }
  
   public Hashmap201()
   {
      for (int i = 0; i<Arraysize; i++)
      {
         array[i] = new LinkedList<Entry>();
      }
   }
   
   //add a key and a value to your map. If the key is already 
   //present in the map, replace the old value with the new one.
   public void put(String key, String value)
   {   
      int index = key.hashCode();
      int hashindex = index%400;
      LinkedList<Entry> mylist = array[Math.abs(hashindex)];
      int signal = 0;
      Entry m = new Entry(key, value);
      for (Entry e : mylist)
      {
         if (e.key == key)
         {       
            e.value = value;
            signal = 1;
         }
      } 
      if (signal == 0)
      {
         mylist.add(m);
      }  
   }
   
   //get the value associated with a particular key. 
   //Return null if the key is not in the map.
   public String getValue(String key)
   {
      int index = key.hashCode();
      int hashindex = index%400;
      LinkedList<Entry> mylist = array[Math.abs(hashindex)];
      for (Entry e: mylist)
      {
         if (e.key.equals(key))
         {
            System.out.println(e.value);
            return e.value;
         }
         else{
              System.out.println("Sorry, key not found");
             }

       
      }
      return null;
   }
   
   public static void main(String[] args)
   {
      int Arraysize = 400;
      Hashmap201 myarray = new Hashmap201();

   }
}


   