#include <stdlib.h>
#include "mbi-sort.h"

int binary_search(void *base, size_t size, int (*compar)(const void *, const void*), int left_index, int right_index, void *elem)
{
  int m;
  if(left_index > right_index)
    return left_index;
  else {
    m = (left_index + right_index) / 2;
    if (compar(elem, (base + m*size)) <= -1)
      return binary_search(base, size, compar, left_index, m - 1, elem);
    else
      return binary_search(base, size, compar, m + 1, right_index, elem);
  }
}

int compare_int(const void *p_int1, const void *p_int2)
{
	if (*(int *)p_int1 == *(int *)p_int2)
		return 0;
	else if (*(int *)p_int1 > *(int *)p_int2)
		return 1;
	else return -1;
}

int compare_double(const void *p_double1, const void *p_double2)
{
	if (*(double *)p_double1 == *(double *)p_double2)
    return 0;
	else if (*(double *)p_double1 > *(double *)p_double2)
		return 1;
	else return -1;
}

int compare_string(const void *p_string1, const void *p_string2)
{
	while(*(char *)p_string1 != '\0' && *(char *)p_string2 != '\0') {
		if (*(char *)p_string1 > *(char *)p_string2)
			return 1;
		else if (*(char *)p_string1 < *(char *)p_string2)
			return -1;
		else {
			p_string1++;
			p_string2++;
		}
	}
	if (*(char *)p_string1 == *(char *)p_string2)
    return 0;
	else if (*(char *)p_string1 == '\0')
		return -1;
	else return 1;
}

