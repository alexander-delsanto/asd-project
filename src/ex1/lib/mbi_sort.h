/**
 * @file mbi_sort.h
 * @brief Merge-BinaryInsertion Sort header file.
 */

#ifndef _MBI_SORT_H
#define _MBI_SORT_H

#include <stdlib.h>

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
void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void*, const void*));

#endif
