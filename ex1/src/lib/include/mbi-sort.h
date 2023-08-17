#include <stdlib.h>

#ifndef _MBI_SORT_H
#define _MBI_SORT_H


// Function that the user should call
void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void*, const void*));

// Functions used only for unit testing
int binary_search(void *base, size_t size, int (*compar)(const void *, const void*), int left_index, int right_index, void *elem);

void binary_insertion_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void*));

void merge_sort(void *base, size_t size, size_t k, int (*compar)(const void*, const void*), int l, int r);

#endif
