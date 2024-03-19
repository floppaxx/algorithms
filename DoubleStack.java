import java.util.*;

/** Stack manipulation.
 * @since 1.8
 */
public class DoubleStack {

   public static void main(String[] args) {
     
  }

   private Stack<Double> stack;

   DoubleStack() {
      stack = new Stack<>();
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      DoubleStack clonedStack = new DoubleStack();
        clonedStack.stack = (Stack<Double>) this.stack.clone();
        return clonedStack;
    }
   

   public boolean stEmpty() {
      return stack.isEmpty(); 
   }

   public void push (double a) {
     stack.push(a); 
   }

   public double pop() {
      if (stEmpty()) {
         throw new RuntimeException("Stack underflow: Cannot pop from an empty stack.");
     }
     return stack.pop();
   } // pop

   public void op (String s) {
      if (stack.size() < 2) {
         throw new RuntimeException("Stack underflow: Insufficient operands for operation " + s);
     }
     double operand2 = pop();
     double operand1 = pop();
     switch (s) {
         case "+":
             push(operand1 + operand2);
             break;
         case "-":
             push(operand1 - operand2);
             break;
         case "*":
             push(operand1 * operand2);
             break;
         case "/":
             if (operand2 == 0) {
                 throw new RuntimeException("Division by zero");
             }
             push(operand1 / operand2);
             break;
         default:
             throw new RuntimeException("Invalid operation: " + s);
     }
   }
  
   public double tos() {
      if (stEmpty()) {
         throw new RuntimeException("Stack is empty");
     }
     return stack.peek();

   }

   @Override
   public boolean equals (Object o) {
      if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleStack that = (DoubleStack) o;
        return stack.equals(that.stack);
   }

   @Override
   public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("[");
      
      Iterator<Double> iterator = stack.iterator();
      while (iterator.hasNext()) {
         result.append(iterator.next());
         if (iterator.hasNext()) {
               result.append(", ");
         }
      }

      result.append("]");
      return result.toString();
   }

   public static double interpret (String pol) {
      DoubleStack stack = new DoubleStack();
      String[] tokens = pol.split("\\s+");

      for (String token : tokens) {
         token = token.trim();
         if (token.isEmpty()) {
               continue; 
         }

         try {
               double operand = Double.parseDouble(token);
               stack.push(operand);
         } catch (NumberFormatException e) {
               stack.op(token);
         }
      }

      if (stack.stEmpty() || stack.stack.size() > 1) {
         throw new RuntimeException("Invalid expression: Redundant elements on the stack");
      }

      return stack.pop();
      }

}
