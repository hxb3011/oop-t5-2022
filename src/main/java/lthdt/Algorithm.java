package lthdt;

import java.util.Arrays;

public abstract class Algorithm {
  private Algorithm() { }
  @FunctionalInterface
  public interface IComparable<T extends IComparable<T>> extends java.lang.Comparable<T> {
    int compareTo(T rightOperand);
  }

  @FunctionalInterface
  public interface IComparator<T> extends java.util.Comparator<T> {
    int compare(T leftOperand, T rightOperand);
  }

  private static class ComparableComparator<T extends IComparable<T>> implements IComparator<T> {
    public int compare(T l, T r) {
      return l != null
          ? l.compareTo(r)
          : r != null
          ? -r.compareTo(l)
          : 0;
    }
  }

  public <T extends IComparable<T>> void mergeSort(T[] arr) {
    mergeSort(arr, new ComparableComparator<>());
  }

  public <T extends IComparable<T>> void mergeSort(T[] arr, int offset, int count) {
    mergeSort(arr, offset, count, new ComparableComparator<>());
  }

  public <T> void mergeSort(T[] arr, IComparator<T> c) {
    if (arr == null || c == null) throw new UnsupportedOperationException();
    _mergeSort(arr, 0, arr.length, c);
  }

  public <T> void mergeSort(T[] arr, int offset, int count, IComparator<T> c) {
    if (arr == null || offset < 0 || count < 0 || offset + count > arr.length || c == null)
      throw new UnsupportedOperationException();
    _mergeSort(arr, offset, count, c);
  }

  private <T> void _mergeSort(T[] arr, int offset, int count, IComparator<T> c) {
    if (count <= 1) return;
    int mid = ((count - 1) / 2) + 1;
    _mergeSort(arr, offset, mid, c);
    _mergeSort(arr, offset + mid, count - mid, c);

    T[] leftHaft = Arrays.copyOfRange(arr, offset, offset + mid);
    int i = 0, j = mid, k = 0;
    while (i < mid && j < count)
      arr[offset + (k++)]
          = (c.compare(leftHaft[i], arr[offset + j]) <= 0)
          ? leftHaft[i++]
          : arr[offset + (j++)];
    while (i < mid)
      arr[offset + (k++)] = leftHaft[i++];
  }
}
