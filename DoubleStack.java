import java.io.Console;
import java.util.*;

/** Stack manipulation.
 * @since 1.8
 */
public class DoubleStack {

   public static void main(String[] args) {
      System.out.println("Result for '2. 5. SWAP -': " + interpret("2. 5. SWAP -")); // Expected output: 3.0
      System.out.println("Result for '3. DUP *': " + interpret("3. DUP *")); // Expected output: 9.0
      System.out.println("Result for '2. 5. 9. ROT - -': " + interpret("2. 5. 9. ROT - -")); // Expected output: -2.0
      System.out.println("Result for '-3. -5. -7. ROT - SWAP DUP * +': " + interpret("-3. -5. -7. ROT - SWAP DUP * +")); // Expected output: 21.0
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

   public double[] getTwoOperands() {
      if (stack.size() < 2) {
          throw new RuntimeException("Stack underflow: Insufficient elements for getting two operands");
      }
      double op2 = pop();
      double op1 = pop();
      return new double[] {op1, op2};
  }

  public double[] getThreeOperands() {
     if (stack.size() < 3) {
        throw new RuntimeException("Stack underflow: Insufficient elements for getting three operands");
     }
     double op3 = pop();
     double op2 = pop();
     double op1 = pop();
     return new double[] {op1, op2, op3};
   }

   public double getOneOperand() {
      if (stack.size() < 1) {
          throw new RuntimeException("Stack underflow: Insufficient elements for getting one operand");
      }
      return pop();
   }
  

   public void op (String s) {

     switch (s) {
         case "SWAP":
               double[] operands_swap = getTwoOperands();
               swap( operands_swap[0], operands_swap[1]);
               break;
         case "DUP":
               double operand = getOneOperand();
               dup(operand);
               break;
         case "ROT":
               double[] operands_rot = getThreeOperands();
               rot(operands_rot[0], operands_rot[1], operands_rot[2]); 
               break;
         case "+":
            double[] operands_add = getTwoOperands();
             push(operands_add[0] + operands_add[1]);
             break;
         case "-":
               double[] operands_sub = getTwoOperands();
               push(operands_sub[0] - operands_sub[1]);
             break;
         case "*":
               double[] operands_mul = getTwoOperands();
               push(operands_mul[0] * operands_mul[1]);
             break;
         case "/":
               double[] operands_div = getTwoOperands();
             if (operands_div[1] == 0) {
                 throw new RuntimeException("Division by zero");
             }
             push(operands_div[0] / operands_div[1]);
             break;
         default:
             throw new RuntimeException("Invalid operation: " + s);
     }
   }

   private void swap(double a, double b) {
      
      push(b);
      push(a);
   }

   private void rot(double a, double b, double c) {
      
      push(b);
      push(c);
      push(a);
   }

   private void dup(double a) {
      // if (stEmpty()) {
      //    throw new RuntimeException("Stack underflow: Cannot duplicate from an empty stack");
      // }
      push(a);
      push(a);
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

   public static double interpret(String pol) {
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

