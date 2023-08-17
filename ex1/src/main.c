#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lib/include/mbi-sort.h"

// Prototypes
int compare_record_int_field(const void *, const void *);
int compare_record_int_field(const void *, const void *);
int compare_record_string_field(const void *, const void *);


// Data structure used to load csv file
typedef struct {
    int index;
    char *string_field;
    int integer_field;
    double double_field;
} Record;

int compare_record_int_field(const void *int1_p, const void *int2_p)
{
    if(int1_p == NULL || int2_p == NULL){
        dprintf(2, "compare_record_int_field: the parameters cannot be null pointers\n");
        exit(EXIT_FAILURE);
    }
    Record *rec1_p = (Record *)int1_p;
    Record *rec2_p = (Record *)int2_p;
    if (rec1_p->integer_field == rec2_p->integer_field)
        return 0;
    else if (rec1_p->integer_field > rec2_p->integer_field)
        return 1;
    else return -1;
}

int compare_record_double_field(const void *double1_p, const void *double2_p)
{
    if(double1_p == NULL || double2_p == NULL){
        dprintf(2, "compare_record_double_field: the parameters cannot be null pointers\n");
        exit(EXIT_FAILURE);
    }
    Record *rec1_p = (Record *)double1_p;
    Record *rec2_p = (Record *)double2_p;
    if (rec1_p->double_field == rec2_p->double_field)
        return 0;
    else if (rec1_p->double_field > rec2_p->double_field)
        return 1;
    else return -1;
}

int compare_record_string_field(const void *string1_p, const void *string2_p)
{
    if(string1_p == NULL || string2_p == NULL){
        dprintf(2, "compare_record_string_field: the parameters cannot be null pointers\n");
        exit(EXIT_FAILURE);
    }
    Record *rec1_p = (Record *)string1_p;
    Record *rec2_p = (Record *)string2_p;
    return strcmp(rec1_p->string_field, rec2_p->string_field);
}

int main(/*int argc, char *argv[]*/)
{
    exit(EXIT_SUCCESS);
}
