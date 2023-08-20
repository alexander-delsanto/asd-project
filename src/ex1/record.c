#include <string.h>
#include "lib/mbi_sort.h"
#include "record.h"

// Data structure used to load csv file
typedef struct {
    int index;
    char *string_field;
    int integer_field;
    double double_field;
} Record;

// Prototypes
static int compare_record_int_field(const void *, const void *);
static int compare_record_int_field(const void *, const void *);
static int compare_record_string_field(const void *, const void *);
static void print_record_to_file(FILE *, Record *, size_t);
static void free_record(Record *, size_t);
static size_t read_record(FILE *, Record **);

static int compare_record_int_field(const void *int1_p, const void *int2_p)
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

static int compare_record_double_field(const void *double1_p, const void *double2_p)
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

static int compare_record_string_field(const void *string1_p, const void *string2_p)
{
    if(string1_p == NULL || string2_p == NULL){
        dprintf(2, "compare_record_string_field: the parameters cannot be null pointers\n");
        exit(EXIT_FAILURE);
    }
    Record *rec1_p = (Record *)string1_p;
    Record *rec2_p = (Record *)string2_p;
    return strcmp(rec1_p->string_field, rec2_p->string_field);
}

static void print_record_to_file(FILE *outfile, Record *record, size_t n_records)
{
    for(int i = 0; i < n_records; i++)
        fprintf(outfile, "%d,%s,%d,%f\n", record[i].index, record[i].string_field,
            record[i].integer_field, record[i].double_field);
}

static void free_record(Record *record, size_t n_records)
{
    for(size_t i = 0; i < n_records; i++)
        free(record[i].string_field);
    free(record);
}

static size_t read_record(FILE *file, Record **file_record)
{
    char *current_read_line;
    size_t buffer_size = 1024;
    char buffer[buffer_size];
    size_t n_records = 0;
    size_t max_records = 2500000;

    *file_record = malloc(sizeof(Record) * max_records);
    if(*file_record == NULL){
            dprintf(2, "main: unable to allocate memory\n");
            exit(EXIT_FAILURE);
        }
    dprintf(1, "Loading records from file...\n");
    while(fgets(buffer, buffer_size, file) != NULL){
        current_read_line = malloc(buffer_size + 1);
        if(current_read_line == NULL){
            dprintf(2, "main: unable to allocate memory\n");
            exit(EXIT_FAILURE);
        }
        strcpy(current_read_line, buffer);
        char *index_field_current_line = strtok(current_read_line, ",");
        char *string_field_current_line = strtok(NULL, ",");
        char *integer_field_current_line = strtok(NULL, ",");
        char *double_field_current_line = strtok(NULL, ",");

        if(n_records >= max_records){
            max_records = max_records * 2;
            *file_record = realloc(*file_record, sizeof(Record) * max_records);
            if(*file_record == NULL){
                dprintf(2, "main: unable to reallocate memory\n");
                exit(EXIT_FAILURE);
            }
        }

        char *string_field = malloc(strlen(string_field_current_line) + 1);
        if(string_field == NULL){
            dprintf(2, "main: unable to allocate memory\n");
            exit(EXIT_FAILURE);
        }
        strcpy(string_field, string_field_current_line);

        (*file_record)[n_records].index = atoi(index_field_current_line);
        (*file_record)[n_records].string_field = string_field;
        (*file_record)[n_records].integer_field = atoi(integer_field_current_line);
        (*file_record)[n_records].double_field = atof(double_field_current_line);

        n_records++;
        free(current_read_line);
    }
    return n_records;
}

void sort_records(FILE *infile, FILE *outfile, size_t k, size_t field)
{
    if(field < 1 || field > 3){
        dprintf(2, "main: invalid <field> value\n");
        exit(EXIT_FAILURE);
    }

    size_t n_read_records;
    Record *infile_record;
    n_read_records = read_record(infile, &infile_record);

    switch (field){
        case 3:
            dprintf(1, "Sorting records by double field...\n");
            merge_binary_insertion_sort((void *) infile_record, n_read_records, sizeof(Record), k, compare_record_double_field);
            break;
        case 2:
            dprintf(1, "Sorting records by integer field...\n");
            merge_binary_insertion_sort((void *) infile_record, n_read_records, sizeof(Record), k, compare_record_int_field);
            break;
        default:
            dprintf(1, "Sorting records by string field...\n");
            merge_binary_insertion_sort((void *) infile_record, n_read_records, sizeof(Record), k, compare_record_string_field);
            break;
    }
    dprintf(1, "Creating sorted records file...\n");
    print_record_to_file(outfile, infile_record, n_read_records);

    free_record(infile_record, n_read_records);
}
