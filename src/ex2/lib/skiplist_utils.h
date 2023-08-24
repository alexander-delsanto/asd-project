#ifndef _SKIPLIST_UTILS_H
#define _SKIPLIST_UTILS_H

#include "skiplist.h"

int skiplist_is_empty(struct SkipList *list);

size_t skiplist_length(struct SkipList *list);

void skiplist_print_int(struct SkipList *list);

#endif