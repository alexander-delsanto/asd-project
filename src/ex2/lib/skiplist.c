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

struct Node * create_node(void *item, size_t size)
{
    struct Node *new_node = malloc(sizeof(struct Node));
    new_node->next = malloc(sizeof(new_node) * size);
    new_node->size = size;
    new_node->item = item;
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
void insert_skiplist(struct SkipList *list, void *item)
{
    struct Node *new = create_node(item, random_level(list->max_height));
    if(new->size > list->max_level)
        list->max_level = new->size;
    struct Node **x = list->heads;
    for(int k = list->max_level - 1; k >= 0; k--){
        if(x[k] == NULL || (list->compare(item, x[k]->item) < 0)){
            if(k < new->size){
                new->next[k] = x[k];
                x[k] = new;
            }
        }else{
            x = x[k]->next;
            k++;
        }
    }
}
