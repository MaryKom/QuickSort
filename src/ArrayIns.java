public class ArrayIns {
    private long[] theArray;
    private int nElements;

    public ArrayIns(int max) {
        theArray = new long[max];
        nElements = 0;
    }

    public void insert(long value) {
        theArray[nElements++] = value;
    }

    public void display() {
        for (int i = 0; i < nElements; i++) {
            System.out.print(theArray[i] + " ");
        }
        System.out.println("");
    }

    public void quickSort() {
        recQuickSort(0, nElements-1);
    }

    public void recQuickSort(int left, int right) {
        int size = right - left +1;
        if (size < 10)
            insertionSort(left, right);
        else {
            long median = medianOf3(left, right);
            int partition = partitionIt(left, right, median);
            recQuickSort(left, partition-1);
            recQuickSort(partition+1,right);
        }
    }

    public long medianOf3(int left, int right) {
        int center = (left + right)/2;
        if (theArray[left] > theArray[center])
            swap(left, center);
        if (theArray[left] > theArray[right])
            swap(left, right);
        if (theArray[center] > theArray[right])
            swap(center, right);

        swap(center, right-1);
        return theArray[right-1];
    }

    private void swap(int one, int two) {
        long temp = theArray[one];
        theArray[one] = theArray[two];
        theArray[two] = temp;
    }

    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left;
        int rightPtr = right - 1;
        while (true) {
            while (theArray[++leftPtr] < pivot)
                ;
            while (theArray[--rightPtr] > pivot)
                ;
            if (leftPtr >= rightPtr)
                break;
            else
                swap(leftPtr, rightPtr);
        }
        swap(leftPtr, right - 1);
        return leftPtr;
    }

    public void insertionSort(int left, int right) {
        int in, out;
        for (out = left + 1; out <= right; out++) {
            long temp = theArray[out];
            in = out;
            while (in > left && theArray[in - 1] >= temp) {
                theArray[in] = theArray[in-1];
                --in;
            }
            theArray[in] = temp;
        }
    }

}
