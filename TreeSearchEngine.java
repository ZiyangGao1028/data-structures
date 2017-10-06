import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//A main checking class which at the user prompt, if a user 
//types in a performer name such as Rech, returns back all 
//titles and summaries that start with that prefix. Do this 
//repeatedly until the user types ####, in which case the
// program ends.
public class TreeSearchEngine 
{  
   public static void main (String[] args) throws IOException
   {  
      TreeMapForString<String> tree = new TreeMapForString<>();

      Scanner input = new Scanner(System.in);
      System.out.print("Please input the prefix you want to search. ");
      String keyinp = input.nextLine();
      if (keyinp.equals("####"))
      {
         System.out.println("Search ends");
         return;
      }
      
      Scanner inp = new Scanner(new File("/Users/gaoziyang/Desktop/data structures/bio_unsorted.list"), "ISO-8859-1");
      String symbol = inp.nextLine();
      while (!symbol.equals(null))
      {
         if (symbol.isEmpty() && inp.hasNextLine())
         {
           symbol = inp.nextLine();
           continue;
         }
         
         if (symbol.length() >= 2 && symbol.substring(0,2).equals("NM"))
         {
           String key = symbol.substring(4);
           String emptyLine = inp.nextLine();
           
 //          long startTime1 = System.nanoTime();
           String value = inp.nextLine();
 //          long estimatedTime1 = System.nanoTime() - startTime1;
 //          System.out.println(estimatedTime1);
           
 //          System.out.println();
           
 //          long startTime2 = System.nanoTime();
           tree.put(key, value);
 //          long estimatedTime2 = System.nanoTime() - startTime2;
  //         System.out.println(estimatedTime2);


           if (!inp.hasNextLine()){break;};
           symbol = inp.nextLine();
         }
         
         else {if (!inp.hasNextLine()){break;};symbol = inp.nextLine();};
      }
      
      ArrayList<String> p = tree.getKeysForPrefix(keyinp);
      String[] parray = p.toArray(new String[p.size()]);      
      for (String s : parray)
      {
          tree.getValue(s);
      }


}
}











