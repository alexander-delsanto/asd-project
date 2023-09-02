/**
 * @file skiplist.h
 * @brief Skiplist header file.
 */

#ifndef _SKIPLIST_H
#define _SKIPLIST_H

#include <stdlib.h>
#include <unistd.h>

/**
 * @struct SkipList
 *
 * Represents a skiplist, which is a data structure that allows fast search within an ordered sequence of elements.
 * It achieves this by maintaining a layered list with each layer having a higher level of sparsity.
 */
struct SkipList {
    struct Node **heads;                        /**< Pointers to the head nodes for each level in the skiplist. */
    size_t max_level;                           /**< Current maximum level reached by a node of the skiplist; represents the highest populated level. */
    size_t max_height;                          /**< Maximum potential number of levels a node in the skiplist can have. */
    int (*compare)(const void*, const void*);   /**< Comparison function pointer for sorting the elements in the skiplist. */
};

/**
 * @struct Node
 *
 * Represents a node in the skip list.
 */
struct Node {
    struct Node **next;                         /**< Array of pointers to the next nodes in each level, up to size. */
    size_t size;                                /**< Height of the node. */
    void *item;                                 /**< Generic pointer to the item the node holds. */
};

/**
 * @brief Initialize a new skip list.
 *
 * @param[out] list a pointer to the skiplist pointer that needs to be initialized.
 * @param[in] max_height the maximum height for the skiplist.
 * @param[in] compar a function pointer to compare two items in the skiplist.
 */
void new_skiplist(struct SkipList **list, size_t max_height, int (*compar)(const void*, const void*));

/**
 * @brief Clear a skiplist and free the memory allocated to its elements.
 *
 * @param[in,out] list a pointer to the skip list pointer that needs to be cleared.
 */
void clear_skiplist(struct SkipList **list);

/**
 * @brief Insert an item into the skiplist.
 *
 * @param[in,out] list a pointer to the skiplist.
 * @param[in] item the item to be inserted.
 */
void insert_skiplist(struct SkipList *list, void *item);

/**
 * @brief Search for an item in the skiplist.
 *
 * @param[in] list a pointer to the skiplist.
 * @param[in] item the item to be searched for.
 * @return a pointer to the item in the skiplist if found, NULL otherwise.
 */
const void* search_skiplist(struct SkipList *list, void *item);


#endif
