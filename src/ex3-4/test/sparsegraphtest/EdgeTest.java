package sparsegraphtest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sparsegraph.Edge;

public class EdgeTest {
   Edge<String, Integer> edge1 = new Edge<>("A", "B", 1);

   @Test
   public void testGetStart() {
      assertEquals("A", edge1.getStart());
   }

   @Test
   public void testGetEnd() {
      assertEquals("B", edge1.getEnd());
   }

   @Test
   public void testGetLabel() {
      assertEquals(1, edge1.getLabel());
   }

   @Test
   public void testToString() {
      assertEquals("A -> B: 1", edge1.toString());
   }
}
