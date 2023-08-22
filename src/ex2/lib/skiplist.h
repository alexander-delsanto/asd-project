#ifndef _SKIPLIST_H
#define _SKIPLIST_H

#include <stdlib.h>

struct SkipList {
    struct Node **heads;
    size_t max_level;
    size_t max_height;
    int (*compare)(const void*, const void*);
};

struct Node {
    struct Node **next;
    size_t size;
    void *item;
};

void new_skiplist(struct SkipList **list, size_t max_height, int (*compar)(const void*, const void*));

void clear_skiplist(struct SkipList **list);

void insert_skiplist(struct SkipList *list, void *item);

const void* search_skiplist(struct SkipList *list, void *item);


#endif
