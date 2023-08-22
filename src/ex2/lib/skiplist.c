#include "skiplist.h"
#include <stdio.h>
void new_skiplist(struct SkipList **list, size_t max_height, int (*compar)(const void*, const void*))
{
    if(*list != NULL){
        fprintf(stderr, "new_skiplist: the list already exists.\n");
        exit(EXIT_FAILURE);
    }
    *list = malloc(sizeof(struct SkipList));
    if(*list == NULL){
        fprintf(stderr, "new_skiplist: unable to allocate memory.\n");
        exit(EXIT_FAILURE);
    }
    (*list)[0].heads = malloc(sizeof(*list) * max_height);
    (*list)[0].max_height = max_height;
    (*list)[0].compare = compar;
}
