/*
   2017-05-29
   This program tells the user the words needed to accomplish 
   the changeling between two words.
**/
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Iterator;

/*
   This class prints out the words needed to accomplish the 
   changeling between two words.
**/
public class Changeling
{
   /*
      This method takes in two Strings and returns true or false 
      based on weather the two words differ by 1 words. The assumption
      is that the two words are both 3-letter-long.
   **/
   private static boolean differone (String word1, String word2)
   {
      if (word1.substring(0, 2).equals(word2.substring(0, 2))
          && !word1.substring(2,3).equals(word2.substring(2, 3)))
      {
         return true;
      }
      if (word1.substring(1, 3).equals(word2.substring(1, 3))
          && !word1.substring(0,1).equals(word2.substring(0, 1)))
      {
         return true;
      }
      if (word1.substring(0, 1).equals(word2.substring(0, 1)) && 
          word1.substring(2, 3).equals(word2.substring(2, 3)) && 
          !word1.substring(1,2).equals(word2.substring(1, 2)))
      {
         return true;
      }
      return false;
   }
   
   /*
      This method takes in three parameters and prints out the words
      needed to accomplish changeling between two words.
      
      Parameters:
      String start: the string to change
      String end: the string to change to in the end
      Map<String, Set<String>> graph: all the words available
      to choose for changeling.
      
      This method does not return anything.
   **/
   private static void printShortestPath
   (String start, String end, Map<String, Set<String>> graph)
   {
      Queue<String> queue = new ArrayDeque<String>();
      Map<String, String> previousVertex = new HashMap<>();
      Map<String, Boolean> visited = new HashMap<>();
      
      previousVertex.put(start, "does not exist");
      visited.put(start, true);
      boolean done = false;
      queue.offer(start);
      
      while (!queue.isEmpty() && !done)
      {
         String currentVertex = queue.remove();
         
         Set<String> connections = graph.get(currentVertex);
         if (connections == null)
         {
            System.out.println("One of the words does not exist");
            return;
         }
         Iterator<String> itr = connections.iterator();
         for (int k = 0; k < connections.size(); k++)
         {
            String i = itr.next().toString();
            if (visited.get(i)==null){
               visited.put(i, true);
               previousVertex.put(i, currentVertex);
               queue.offer(i);
               if (i.equals(end))
               {
                  done = true;
               }
            }
         }
      }
      
      String currentVertex = end;
      ArrayList<String> path = new ArrayList<String>();
      // store the path into an ArrayList
      while(!currentVertex.equals("does not exist"))
      {
         path.add(0, currentVertex);
         currentVertex = previousVertex.get(currentVertex);
         if (currentVertex == null)
         {
            System.out.println("One of the words does not exist");
            return;
         }
      }
   
      // print out the items in the ArrayList
      for (String i: path)
      {
         System.out.println(i);
      }
   }
   
   /*
      This method takes in a parameter String[] args and prints out
      the path to change one word to the other.
   **/
   public static void main(String[] args) throws IOException
   {
      // exit the program and gives a warning if the user
      // entered invalid enter.
      if (args.length != 3)
      {
         System.out.println("Invaid Enter!");
         return;
      } 
      Scanner input = new Scanner(new File(args[0]));
      String start = args[1];
      String end = args[2];
      Map<String, Set<String>> graph = new HashMap<>();
      ArrayList<String> mylist = new ArrayList<String>();
      
      while (input.hasNext()) 
      {
         String word = input.next();
         mylist.add(word);
      }   
      
      // Storing the names and its connections to a HashMap
      for (String word: mylist)
      {
         Set<String> connections = new HashSet<>();
         for (String i: mylist)
         {
            if (i.length() == word.length() && differone(i, word))
            {
               connections.add(i);
            }
         } 
         graph.put(word, connections);
      }
      printShortestPath(start, end, graph);
   }
}