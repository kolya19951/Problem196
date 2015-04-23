/**
 * Created by Коля on 22.04.2015.
 */
public class ShiftingTable {
    private int shift;
    private long[][] data;
    private int[][] primesData;
    private int position;
    private Pyramid_5_Lines pyramid;
    private Primes primes;

    ShiftingTable(long lineNum) {
        data = new long[5][5];
        primesData = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                data[i][j] = -1;
                primesData[i][j] = 0;
            }
        }
        primes = new Primes();
        position = -3;
        pyramid = new Pyramid_5_Lines(lineNum);
        this.ShiftInPyramid();
        this.ShiftInPyramid();
        this.ShiftInPyramid();
    }

    public long GetData(int x, int y) {
        return data[(x + shift + 2) % 5][y + 2];
    }

    public int GetPrimeData(int x, int y) {
        return primesData[(x + shift + 2) % 5][y + 2];
    }

    public void SetData(int x, int y, long l) {
        data[(x + shift + 2) % 5][y + 2] = l;
        primesData[(x + shift + 2) % 5][y + 2] = 0;
    }

    public void Shift(long... l) {
        for (int i = -2; i <= 2; i++) {
            SetData(-2, i, l[i + 2]);
        }
        shift = shift + 1;
        shift = shift % 5;
    }

    public int NeighboursCount(int x, int y) {
        int neighbours = 0;
        for (int i = -1 + y; i <= 1 + y; i++) {
            for (int j = -1 + x; j <= 1 + x; j++) {
                if (!((i == y) && (j == x))) {
                    if (GetPrimeData(j, i) == 1)
                        neighbours++;
                    if (GetPrimeData(j, i) == 0) {
                        if (Primes.IsPrime(GetData(j, i)))
                            neighbours++;
                    }
                    if (neighbours >= 2)
                        return 2;

                }
            }
        }
        return neighbours;
    }

    public boolean IsInTriplet() {
        if (IsCentralNumberPrime()) {
            int neighbours = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (!((i == 0) && (j == 0))) {
                        if (Primes.IsPrime(GetData(j, i))) {
                            neighbours++;
                            if (neighbours >= 2)
                                return true;
                        }
                    }
                }
            }
            neighbours = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (!((i == 0) && (j == 0))) {
                        if (Primes.IsPrime(GetData(j, i))) {
                            neighbours++;
                            if (neighbours >= 2)
                                return true;
                            if (NeighboursCount(j, i) >= 2)
                                return true;
                        }
                    }
                }
            }
            return neighbours >= 2;
        } else
            return false;
    }

    private boolean IsCentralNumberPrime() {
        long l = GetData(0, 0);
        if (GetPrimeData(0, 0) == 1)
            return true;
        if (GetPrimeData(0, 0) == -1)
            return false;
        return Primes.IsPrime(l);
    }

    public void ShiftInPyramid() {
        long[] num = new long[5];
        for (int i = 0; i < 5; i++) {
            num[i] = pyramid.GetNumber(position + 4, i - 2);
        }
        this.Shift(num);
        position++;
    }
}
