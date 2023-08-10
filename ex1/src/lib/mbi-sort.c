#include <stdlib.h>
#include <string.h>
#include "include/mbi-sort.h"

// Prototyes
int binary_search(void *, size_t, int (*)(const void *, const void*), int, int, void *);
void binary_insertion_sort(void *, size_t, size_t, int (*)(const void *, const void*));
void merge_sort(void *, size_t, int (*)(const void*, const void*), int, int);
void merge(void *, size_t, int (*)(const void*, const void*), int, int, int);



int binary_search(void *base, size_t size, int (*compar)(const void *, const void*), int left_index, int right_index, void *elem)
{
	if (right_index <= left_index) {
		if(compar(elem, base + left_index * size) > 0)
			return left_index + 1;
		else return left_index;
	} else {
		int m = (left_index + right_index) / 2;
		if (compar(elem, base + m * size) == 0)
			return m + 1;
		if (compar(elem, base + m * size) > 0)
			return binary_search(base, size, compar, m + 1, right_index, elem);
		else return binary_search(base, size, compar, left_index, m - 1, elem);
	}
}

void binary_insertion_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void*))
{
	unsigned long i;
	int j, k;
	char * temp = calloc(1, size);
	for (i = 1; i < nitems; i++) {
		j = binary_search(base, size, compar, 0, i-1, base + i * size);
		k = i;
		while (k > j) {
			memmove((void *) temp, base + (k - 1) * size, size);
			memmove(base + (k - 1) * size, base + k * size, size);
			memmove(base + k * size, (void * ) temp, size);
			k--;
		}
	}
}

void merge_sort(void *base, size_t size, int (*compar)(const void*, const void*), int l, int r)
{
	if(l < r){
		unsigned long m = (l + r) / 2;
		merge_sort(base, size, compar, l, m);
		merge_sort(base, size, compar, m + 1, r);
		merge(base, size, compar, l, m, r);
	}
}

void merge(void *base, size_t size, int (*compar)(const void*, const void*), int l, int m, int r)
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
	return;
}
