import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class HashSearchEngine 
{  
   public static void main (String[] args) throws IOException
   {  
      
      Scanner input = new Scanner(System.in);
      System.out.print("Please input the key. ");
      String keyinp = input.next();
      
      Hashmap201 hashmap = new Hashmap201();
      Scanner inp = new Scanner(new File(args[0]), "ISO-8859-1");
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
           String value = inp.nextLine();
           hashmap.put(key, value);
           if (!inp.hasNextLine()){break;};
           symbol = inp.nextLine();
         }
         
         else {if (!inp.hasNextLine()){break;};symbol = inp.nextLine();};
      }
      long startTime = System.nanoTime();
      hashmap.getValue(keyinp);
      long estimatedTime = System.nanoTime() - startTime;
      System.out.println(estimatedTime);
}
}











