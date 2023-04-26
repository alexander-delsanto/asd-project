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

int compare_int(const void *int1_pointer, const void *int2_pointer)
{
  if (*(int *)int1_pointer == *(int *)int2_pointer)
    return 0;
  return (*(int *)int1_pointer < *(int *)int2_pointer) ? -1 : 1;
}

int compare_double(const void *double1_pointer, const void *double2_pointer)
{
  if (*(double *)double1_pointer == *(double *)double2_pointer)
    return 0;
  return (*(double *)double1_pointer < *(double *)double2_pointer) ? -1 : 1;
}
