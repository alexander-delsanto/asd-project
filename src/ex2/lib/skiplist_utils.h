/**
 * @file skiplist_utils.h
 * @brief Skiplist utilitiy functions header file.
 */


#ifndef _SKIPLIST_UTILS_H
#define _SKIPLIST_UTILS_H

#include "skiplist.h"

/**
 * @brief Check if a skiplist is empty.
 *
 * @param[in] list a pointer to the skiplist.
 * @return returns 1 if the skiplist is empty, 0 otherwise.
 */
int skiplist_is_empty(struct SkipList *list);

/**
 * @brief Calculates the length (number of nodes) of a skiplist.
 *
 * @param[in] list a pointer to the skiplist.
 * @return the length of the skiplist.
 */
size_t skiplist_length(struct SkipList *list);

/**
 * @brief Prints a skiplist of integer type items.
 *
 * @note This function assumes that the skiplist's item type is int.
 *
 * @param[in] list a pointer to the skiplist.
 */
void skiplist_print_int(struct SkipList *list);

#endif