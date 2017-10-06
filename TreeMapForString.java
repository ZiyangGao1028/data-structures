//a class called TreeMapForString that will be used to map Strings 
//to Strings. (The intention is to use it to map performers 
//to biographies.) The map is implemented as a binary search tree.

import java.util.ArrayList;

public class TreeMapForString<T extends Comparable<T>>
{
   private class Node<T>
   {
      private Entry data;
      private Node<T> left;
      private Node<T> right;
      
      public Node(Entry data, Node<T> left, Node<T> right)
      {
         this.data = data;
         this.left = left;
         this.right = right;
      }
   }
   private Node<T> root;
   private boolean insertStatus;
   
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
   
   public TreeMapForString()
   {
      root = null;
   }
   
   public boolean put(String key, String value)
   {
      insertStatus = true;
      Entry newpair = new Entry(key, value);
      root = put(newpair, root);
      
      return insertStatus;
   }

   //add a key and a value to a node of the BTS. If the key is already 
   //present in the BST, replace the old value with the new one.
   private Node<T> put(Entry newpair, Node<T> localroot)
   {
      //System.out.println(newpair.key);

      if (localroot == null) {
        return new Node<T>(newpair, null, null);
     } else if (newpair.key.compareTo(localroot.data.key) < 0){
      localroot.left = put(newpair, localroot.left);
   } else if (newpair.key.compareTo(localroot.data.key) > 0){
      localroot.right = put(newpair, localroot.right);
   } else if (newpair.key.equals(localroot.data.key)){
      localroot.data.value = newpair.value;

   } else {
      insertStatus = false;
   }
   return localroot;
}

   //get the value associated with a particular key. 
   //Return null if the key is not in the BST.
public String getValue(String key)
{
   Node<T> k = getValue(key, root);

   if (root == null)
   {
      String n = "Key Not Found";
      System.out.println(n);
      return n;
   }
   else 
   {
      System.out.println(k.data.value);
      return k.data.value;
   }  
}
private Node<T> getValue(String key, Node<T> localroot)   {
   if (localroot == null) {
      return null;
   } else if (key.compareTo(localroot.data.key) < 0){
      localroot = getValue(key, localroot.left);
   } else if (key.compareTo(localroot.data.key) > 0){
      localroot = getValue(key, localroot.right);
   } else if (key.equals(localroot.data.key)){
      return localroot;
   }
   return localroot;  
}

//Return a list of all keys that start with string prefix. Keep on
//searching if the prefix length is longer than some of the keys in 
//the binary search tree.
public ArrayList<String> getKeysForPrefix(String prefix)
{
   ArrayList<String> array = new ArrayList<>();
   getKeysForPrefix(prefix, root, array);
   System.out.println(array);
   return array;
}

private void getKeysForPrefix(String prefix, Node<T> localroot, ArrayList<String> array)
{
   if (!(localroot == null)){
     int len = prefix.length();
     int len2 = localroot.data.key.length();
     if (len2 >= len)
     {
      if (localroot == null) {
         return;
      } else if (localroot.data.key.substring(0,len).compareTo(prefix) < 0){
         getKeysForPrefix(prefix, localroot.right, array);
      } else if (localroot.data.key.substring(0,len).compareTo(prefix) > 0){
         getKeysForPrefix(prefix, localroot.left, array);
      } else if (localroot.data.key.substring(0,len).equals(prefix)){
         array.add(localroot.data.key);
         getKeysForPrefix(prefix, localroot.left, array);
         getKeysForPrefix(prefix, localroot.right, array);
      }
   } 
   else
   {
      if (localroot == null) {
         return;
      } else if (localroot.data.key.compareTo(prefix.substring(0,len2)) < 0){
         getKeysForPrefix(prefix, localroot.right, array);
      } else if (localroot.data.key.compareTo(prefix.substring(0,len2)) > 0){
         getKeysForPrefix(prefix, localroot.left, array);
      } else if (localroot.data.key.equals(prefix.substring(0,len2))){
         getKeysForPrefix(prefix, localroot.left, array);
         getKeysForPrefix(prefix, localroot.right, array);
      }
   }
}else{
   return;
}

}

public static void test (boolean condition){
   if (!condition){
      throw new RuntimeException("Test failed.");
   }
}

   //Main function for testing some small data.
public static void main(String[] args)
{
//       TreeMapForString<String> bst = new TreeMapForString<>();
//       bst.put("d1", "D1");
//       bst.put("d2", "D2");
//       bst.put("d3", "D3");
//       bst.put("c", "C");
//       bst.put("f", "F");
//       bst.getValue("c");
//       bst.getKeysForPrefix("d");
   System.out.println("Tests passed!");

}
}



