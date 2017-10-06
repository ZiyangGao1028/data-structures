import java.util.*;

public class Heap<E extends Comparable<E>>
{
   private ArrayList<E> theData;
   private int k;

   public Heap(int k)
   {
      theData = new ArrayList<E>();
      this.k = k;
   }

   private void swap(int i, int j)
   {
      E temp = theData.get(i);
      theData.set(i,theData.get(j));
      theData.set(j,temp);
   }

   public boolean offer(E item)
   {
      theData.add(item);
      int child = theData.size() - 1;
      int parent = (child - 1) / k;
      while (parent >= 0 && 
             theData.get(parent).compareTo(theData.get(child)) > 0)
      {
         swap(parent,  child);
         child = parent;
         parent = (child - 1) / k;
      }
      return true;
   }

   public E poll() 
   {
      if (theData.size() == 0)
      {
         return null;
      } 
   
      E result = theData.get(0);
   
      if (theData.size() == 1)
      {
         theData.remove(0);
         return result;
      }
   
      /* Remove the last item from the ArrayList and place it into
         the first position. */
      theData.set(0, theData.remove(theData.size()-1));
   
      int parent = 0;
      while (true) 
      {
         int leftChild = k * parent + 1;
         if (leftChild >= theData.size() )
         {
            break;
         }
      
         int rightChild = leftChild + 1;
         int minChild = leftChild;
         for (int m = 0; m < k; m++)
         {
         
            if (rightChild < theData.size() && 
                theData.get(minChild).compareTo(
                   theData.get(rightChild)) > 0)
            {             
               minChild = rightChild;
            }
            rightChild = rightChild + 1;
         }
         // Move smaller child up heap if necessary.
         if (theData.get(parent).compareTo(theData.get(minChild)) > 0)
         {
            swap(parent,minChild);
            parent = minChild;
         } 
         else 
         {
            // Heap property is restored.
            break;
         }
      }
      return result;
   }

   // This method is just here for debugging and grading purposes,
   // do not change it
   public void display()
   {
      for (int i=0; i < theData.size(); i++)
         System.out.print(theData.get(i) + " ");
      System.out.println();
   }

   public static void test(boolean condition) {
      if (!condition) {
         throw new RuntimeException("Test failed.");
      }
   }

   public static void main(String[] args)
   {
      for (int k=2; k < 6; k++)
      {
         System.out.println("------------------------");
         System.out.println("k = " + k);
         Random rand = new Random(55057);
         Heap<Integer> heap = new Heap<Integer>(k);
      
         // testValues used for testing purposes, to see if values come out of
         // the heap in proper order
         ArrayList<Integer> testValues = new ArrayList<>();
         for (int i=0; i < 100; i++) {
            int nextValue = rand.nextInt(500);
            heap.offer(nextValue);
            testValues.add(nextValue);
         }
      
         Collections.sort(testValues);
         
         heap.display();       
         for (int i=0; i < 50; i++) {
            int valueRemoved = heap.poll();
            test(valueRemoved == testValues.remove(0));
         }
         heap.display();
      
         for (int i=0; i < 50; i++) {
            int nextValue = rand.nextInt(500);
            heap.offer(nextValue);
            testValues.add(nextValue);
         }
      
         Collections.sort(testValues);
      
         for (int i=0; i < 50; i++) {
            int valueRemoved = heap.poll();
            test(valueRemoved == testValues.remove(0));
         }
         heap.display();
      
         System.out.println("Tests pass!");
      }
        
   }
}
