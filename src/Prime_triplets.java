/**
 * Created by ����� on 21.04.2015.
 */
public class Prime_triplets {
    public static void main(String[] args) {
        long line = 10000, result = 0;
        ShiftingTable table = new ShiftingTable(line);
        for (int i = 0; i <= line ; i++) {
            if (table.IsInTriplet())
                result += table.GetData(0, 0);
            table.ShiftInPyramid();
        }
        System.out.println(result);
    }
}
