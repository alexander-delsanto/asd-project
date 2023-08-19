#ifndef _MBI_SORT_H
#define _MBI_SORT_H

#include <stdlib.h>

// Function that the user should call
void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void*, const void*));

#endif
