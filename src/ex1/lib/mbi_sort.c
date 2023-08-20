#include <stdlib.h>
#include <string.h>
#include "mbi_sort.h"

// Prototyes
void merge_binary_insertion_sort(void *, size_t, size_t, size_t, int (*)(const void*, const void*));
static void _merge_binary_insertion_sort(void *, size_t, size_t, int (*)(const void*, const void*), int, int);
static void merge(void *, size_t, int (*)(const void*, const void*), int, int, int);
static void binary_insertion_sort(void *, size_t, size_t, int (*)(const void *, const void*));
static int binary_search(void *, size_t, int (*)(const void *, const void*), int, int, void *);


/**
 * Merge-BinaryInsertion Sort wrapper
 *
 * Wrapper function called by the user.
 * Calls the function that sorts a generic array using the Merge Sort and the BinaryInsertion Sort algorithms combined.
 *
 * @param[in,out] base pointer to the first element of the array.
 * @param[in] nitems number of elements in the array.
 * @param[in] size size of each element of the array.
 * @param[in] k when \p k >= array.length BinaryInsertion Sort is used; when \p k = 0 only Merge Sort is used.
 * @param[in] compar pointer to function used to determine how the array will be sorted.
 *
*/
void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void*, const void*))
{
    _merge_binary_insertion_sort(base, size, k, compar, 0, nitems - 1);
}

/**
 * Merge-BinaryInsertion Sort
 *
 * Sorts a generic array using the Merge Sort and the BinaryInsertion Sort algorithms combined.\n
 * When \p k = 0 it behaves like a normal Merge Sort algorithm; in this case the array is sorted by recursively splitting the main
 * array into sub-arrays until one element arrays are reached; the merge function is then called to merge the sub-arrays back to the starting array,
 * while ordering them with the compar function.\n
 * When \p k > 0 the array is split until the sub-arrays length <= k; BinaryInsertion Sort is then called to sort the sub-arrays; merge is
 * then called to merge the sorted sub-arrays back to the starting array.
 *
 * @param[in,out] base pointer to the first element of the array.
 * @param[in] size size of each element of the array.
 * @param[in] k when k >= array.length BinaryInsertion Sort is used; when k = 0 only Merge Sort is used.
 * @param[in] compar pointer to function used to determine how the array will be sorted.
 * @param[in] l index of the leftmost element of the array or sub-array.
 * @param[in] r index of the rightmost element of the array or sub-array.
 *
*/
static void _merge_binary_insertion_sort(void *base, size_t size, size_t k, int (*compar)(const void*, const void*), int l, int r)
{
    if(l < r){
        size_t length = r - l + 1;
        if(length <= k){
            binary_insertion_sort(base + l * size, length, size, compar);
            return;
        }
        int m = (l + r) / 2;
        _merge_binary_insertion_sort(base, size, k, compar, l, m);
        _merge_binary_insertion_sort(base, size, k, compar, m + 1, r);
        merge(base, size, compar, l, m, r);
    }
}

/**
 * Merge
 *
 * Takes two sorted sub-arrays as input and merges them into the starting one in the right order, determined by the \p compar function.\n
 * The arrays are merged by comparing each element of the left and right array and copyig them in the right order back into the starting array.
 *
 * @param[in,out] base pointer to the first element of the array.
 * @param[in] size size of each element of the array.
 * @param[in] compar pointer to function used to determine how the array will be sorted.
 * @param[in] l index of the leftmost element of the array or sub-array.
 * @param[in] m index of the middle element of the array.
 * @param[in] r index of the rightmost element of the array or sub-array.
 *
*/
static void merge(void *base, size_t size, int (*compar)(const void*, const void*), int l, int m, int r)
{
    int i, j, k;
    int left_length = m - l + 1;
    int right_length = r - m;
    void *left_arr = malloc(left_length * size);
    void *right_arr = malloc(right_length * size);
    for(i = 0; i < left_length; i++){
        memcpy(left_arr + (i * size), base + ((l + i) * size), size);
    }
    for(i = 0; i < right_length; i++){
        memcpy(right_arr + (i * size), base + ((m + 1 + i) * size), size);
    }

    i = 0;
    j = 0;
    k = l;
    while(i < left_length && j < right_length){
        if(compar(left_arr + i * size, right_arr + j * size) <= 0){
            memcpy(base + k * size, left_arr + i * size, size);
            i++;
        }else{
            memcpy(base + k * size, right_arr + j * size, size);
            j++;
        }
        k++;
    }

    while(i < left_length){
        memcpy(base + k * size, left_arr + i * size, size);
        i++;
        k++;
    }

    while(j < right_length){
        memcpy(base + k * size, right_arr + j * size, size);
        j++;
        k++;
    }

    free(left_arr);
    free(right_arr);
}

/**
 * BinaryInsertion Sort
 *
 * Sorts a generic array.\n
 * Starting from the second element of the array binary_search is called to determine the index where the element should be moved;
 * if the returned index is the same as the current one the next element is examined, otherwise it moves the element back k - j positions by
 * swapping it with the one that precedes it; because of this, the portion of the array from base to i is always sorted.
 *
 * @param[in,out] base pointer to the first element of the array.
 * @param[in] nitems number of elements in the array.
 * @param[in] size size of each element of the array.
 * @param[in] compar pointer to function used to determine how the array will be sorted.
 *
*/
static void binary_insertion_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void*))
{
    int i, j, k;
    void *temp = malloc(size);
    for (i = 1; (size_t) i < nitems; i++) {
        j = binary_search(base, size, compar, 0, i-1, base + i * size);
        k = i;
        while (k > j) {
            memcpy((void *) temp, base + (k - 1) * size, size);
            memcpy(base + (k - 1) * size, base + k * size, size);
            memcpy(base + k * size, (void * ) temp, size);
            k--;
        }
    }
    free(temp);
}

/**
 * BinarySearch
 *
 * Finds the position that \p elem should be in for the array to be sorted.\n
 * The position is found by splitting the array into sub-arrays and comparing elem with the element at the middle index; if elem = array[m],
 * m + 1 is returned; if elem > array[m] the right sub-array is examined to find the position; else if elem < array[m], the left sub-array is examined;
 * when r <= l the function returns l + 1 if elem > array[l], else it returns l.
 *
 * @param[in] base pointer to the first element of the array.
 * @param[in] size size of each element of the array.
 * @param[in] compar pointer to function used to determine how the array will be sorted.
 * @param[in] l left index of the examined portion of the array.
 * @param[in] r right index of the examined portion of the array.
 * @param[in] elem element to be examined.
 *
 * @return The index where elem should be for the array to be sorted.
*/
static int binary_search(void *base, size_t size, int (*compar)(const void *, const void*), int l, int r, void *elem)
{
    if (r <= l) {
        if(compar(elem, base + l * size) > 0)
            return l + 1;
        else return l;
    } else {
        int m = (l + r) / 2;
        int compare_res = compar(elem, base + m * size);
        if (compare_res == 0)
            return m + 1;
        if (compare_res > 0)
            return binary_search(base, size, compar, m + 1, r, elem);
        return binary_search(base, size, compar, l, m - 1, elem);
    }
}
