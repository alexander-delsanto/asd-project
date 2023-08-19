#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lib/include/mbi-sort.h"
#include "record.h"

int main(int argc, char *argv[])
{
    if(argc < 5){
        dprintf(2, "Usage: main_ex1 <infile> <outfile> <k> <field>\n");
        exit(EXIT_FAILURE);
        }

    // Setting up inputs for sort_records
    size_t k, field;
    FILE *infile, *outfile;

    infile = fopen(argv[1], "r");
    if(infile == NULL){
        dprintf(2, "main: unable to open %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }
    outfile = fopen(argv[2], "w");
    if(outfile == NULL){
        dprintf(2, "main: unable to open %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }
    k = atol(argv[3]);
    field = atol(argv[4]);

    sort_records(infile, outfile, k, field);

    exit(EXIT_SUCCESS);
}
