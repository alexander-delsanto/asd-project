#ifndef _RECORD_H
#define _RECORD_H

#include <stdlib.h>
#include <stdio.h>

/*
 * Sorts the infile CSV file and writes the outfile sorted CSV file.
 * Uses the merge binary insersion sort library to sort; the parameter k is passed to the function.
 * The parameter field can be of value 1 to 3 and it is used to determine which field of the CSV
 * file will be used to sort it by passing an appropriate compar function to the sorting algorithm.
*/
void sort_records(FILE *infile, FILE *outfile, size_t k, size_t field);

#endif
