#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lib/include/mbi-sort.h"

// Data structure used to load csv file
typedef struct {
	int index;
	char *string_field;
	int integer_field;
	double double_field;
} record_t;
