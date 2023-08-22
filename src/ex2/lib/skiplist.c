#include "skiplist.h"
#include <stdio.h>
#include <time.h>

#define RANDOM (rand()/(double)RAND_MAX)

size_t random_level(size_t max_height)
{
    size_t lvl = 1;

    while(RANDOM < 0.5 && lvl < max_height)
        lvl += 1;
    return lvl;
}
void new_skiplist(struct SkipList **list, size_t max_height, int (*compar)(const void*, const void*))
{
    srand(time(NULL));
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
