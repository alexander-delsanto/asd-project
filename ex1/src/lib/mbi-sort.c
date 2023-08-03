#include <stdlib.h>
#include <string.h>
#include "include/mbi-sort.h"

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
