#ifndef _MBI_SORT_H
#define _MBI_SORT_H

#include <stdlib.h>

/*!
 * \brief   Merge binary insertion sort
 *
 *          Sorts a generic array of nitems elements using the merge sort algorithm and the binary insertion sort algorithm.
 *          The algorithm used to sort the array is determined by the value of k; merge sort is used until length <= k.
 *
*/
void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void*, const void*));

#endif
