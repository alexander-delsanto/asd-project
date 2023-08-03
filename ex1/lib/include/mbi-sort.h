#include <stdlib.h>

#ifndef _MBI_SORT_H
#define _MBI_SORT_H



//void merge_binary_insertion_sort(void *base, size_t nitems, size_t size, size_t k, int (*compar)(const void*, const void*));

//void sort_records(const char *infile, const char *outfile, size_t k, size_t field);

//void binary_insertion_sort(void *base, size_t nitems, size_t size);

int binary_search(void *base, size_t size, int (*compar)(const void *, const void*), int left_index, int right_index, void *elem);

void binary_insertion_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void*));

int compare_int(const void *pointer1, const void *pointer2);

int compare_double(const void *pointer1, const void *pointer2);

int compare_string(const void *pointer1, const void *pointer2);

#endif
