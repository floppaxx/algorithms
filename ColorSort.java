/** Sorting of balls.
 * @since 1.8
 */
public class ColorSort {

   enum Color {red, green, blue};
   
   public static void main (String[] param) {
      Color[] balls = {Color.green, Color.red, Color.blue, Color.green, Color.red, Color.blue};
        reorder(balls);
   }
   
   public static void reorder(Color[] balls) {
    int start = 0;
    int end = balls.length - 1;
    int i = 0;

    while (i <= end) {
        switch (balls[i]) {
            case red:
                Color tempRed = balls[start];
                balls[start] = balls[i];
                balls[i] = tempRed;
                start++;
                i++;
                break;
            case blue:
                Color tempBlue = balls[end];
                balls[end] = balls[i];
                balls[i] = tempBlue;
                end--;
                break;
            case green:
                i++;
                break;
        }
    }
}

}

