/**
 * @file mbi_sort.c
 * @brief Merge-BinaryInsertion Sort implementation.
 */

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
 * @brief Merge-BinaryInsertion Sort function.
 *
 * Sorts a generic array by combining the Merge Sort and BinaryInsertion Sort algorithms. The function
 * behaves like a standard Merge Sort when the length of the sub-array is greater than \( k \). However,
 * when the length of the sub-array is less than or equal to \( k \), BinaryInsertion Sort is applied.
 *
 * @param[in,out] base pointer to the first element of the array.
 * @param[in] nitems number of elements in the array.
 * @param[in] size size of each element of the array.
 * @param[in] k threshold for switching to BinaryInsertion Sort. If \( k = 0 \), only Merge Sort is used.
 * @param[in] compar function pointer used for element comparison.
 */
void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void*, const void*))
{
    _merge_binary_insertion_sort(base, size, k, compar, 0, nitems - 1);
}

/**
 * @brief Internal recursive function for Merge-BinaryInsertion Sort.
 *
 * Recursive function to sort and merge sub-arrays. It checks the length of the current sub-array against \( k \).
 * If the length is less than or equal to \( k \), it uses BinaryInsertion Sort. Otherwise, it continues to split the sub-array.
 *
 * @param[in,out] base pointer to the first element of the array.
 * @param[in] size size of each element in the array.
 * @param[in] k threshold for switching to BinaryInsertion Sort.
 * @param[in] compar function pointer used for element comparison.
 * @param[in] l index of the leftmost element of the array or sub-array.
 * @param[in] r index of the rightmost element of the array or sub-array.
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
 * @brief Merge function for Merge Sort.
 *
 * Merges two sorted sub-arrays into one sorted array.
 *
 * @param[in,out] base pointer to the first element of the array.
 * @param[in] size size of each element of the array.
 * @param[in] compar function pointer used for element comparison.
 * @param[in] l index of the leftmost element of the array or sub-array.
 * @param[in] m index of the middle element of the array.
 * @param[in] r index of the rightmost element of the array or sub-array.
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
 * @brief BinaryInsertion Sort algorithm.
 *
 * A variation of the standard Insertion Sort. Instead of linear search, it uses binary search to place the current element
 * at its correct position, which reduces the number of comparisons.
 *
 * @param[in,out] base pointer to the first element of the array.
 * @param[in] nitems number of elements in the array.
 * @param[in] size size of each element in the array.
 * @param[in] compar function pointer used for element comparison.
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
 * @brief Binary search function.
 *
 * Finds the correct position for a given element within a sorted array using binary search.
 *
 * @param[in] base pointer to the first element of the array.
 * @param[in] size size of each element in the array.
 * @param[in] compar function pointer used for element comparison.
 * @param[in] l left boundary of the search range.
 * @param[in] r right boundary of the search range.
 * @param[in] elem element for which the position needs to be found.
 * @return the correct index for the given element.
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
