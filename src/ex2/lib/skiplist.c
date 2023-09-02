/**
 * @file skiplist.c
 * @brief Skiplist implementation.
 */

#include "skiplist.h"
#include <stdio.h>
#include <time.h>

#define RANDOM (rand()/(double)RAND_MAX)

/**
 * @brief Generate a random height for a new node.
 *
 * @param[in] max_height the maximum height for the skiplist.
 * @return the randomly generated height.
 */
size_t random_level(size_t max_height)
{
    size_t lvl = 1;

    while(RANDOM < 0.5 && lvl < max_height)
        lvl += 1;
    return lvl;
}

/**
 * @brief Create a new node for the skiplist.
 *
 * @param[in] item the item to be stored in the node.
 * @param[in] size the height of the node.
 * @return a pointer to the newly created node.
 */
struct Node * create_node(void *item, size_t size)
{
    struct Node *new_node = malloc(sizeof(struct Node));
    new_node->next = calloc(size, sizeof(new_node));
    if(new_node == NULL || new_node->next == NULL){
        fprintf(stderr, "create_node: unable to allocate memory.\n");
        exit(EXIT_FAILURE);
    }
    new_node->size = size;
    new_node->item = item;
    return new_node;
}

/**
 * @brief Initialize a new skip list.
 *
 * @param[out] list a pointer to the skiplist pointer that needs to be initialized.
 * @param[in] max_height the maximum height for the skiplist.
 * @param[in] compar a function pointer to compare two items in the skiplist.
 */
void new_skiplist(struct SkipList **list, size_t max_height, int (*compar)(const void*, const void*))
{
    srand(time(NULL));
    *list = malloc(sizeof(struct SkipList));
    if(*list == NULL){
        fprintf(stderr, "new_skiplist: unable to allocate memory.\n");
        exit(EXIT_FAILURE);
    }
    (*list)->heads = calloc(max_height, sizeof(*list));
    (*list)->max_height = max_height;
    (*list)->compare = compar;
}

/**
 * @brief Clear a skiplist and free the memory allocated to its elements.
 *
 * @param[in,out] list a pointer to the skip list pointer that needs to be cleared.
 */
void clear_skiplist(struct SkipList **list)
{
    struct Node *curr = (*list)->heads[0];
    for(struct Node *n = curr; n != NULL; n = curr){
        curr = n->next[0];
        free(n->next);
        free(n);
    }
    free((*list)->heads);
    free(*list);
    *list = NULL;
}

/**
 * @brief Insert an item into the skiplist.
 *
 * @param[in,out] list a pointer to the skiplist.
 * @param[in] item the item to be inserted.
 */
void insert_skiplist(struct SkipList *list, void *item)
{
    struct Node *new = create_node(item, random_level(list->max_height));
    if(new->size > list->max_level)
        list->max_level = new->size;

    struct Node **x = list->heads;
    for(ssize_t k = list->max_level - 1; k >= 0; k--){
        if(x[k] == NULL || (list->compare(item, x[k]->item) < 0)){
            if(k < (ssize_t)new->size){
                new->next[k] = x[k];
                x[k] = new;
            }
        }else{
            x = x[k]->next;
            k++;
        }
    }
}

/**
 * @brief Search for an item in the skiplist.
 *
 * @param[in] list a pointer to the skiplist.
 * @param[in] item the item to be searched for.
 * @return a pointer to the item in the skiplist if found, NULL otherwise.
 */
const void* search_skiplist(struct SkipList *list, void *item)
{
    struct Node **x = list->heads;
    for(ssize_t i = list->max_level - 1; i >= 0; i--){
        while(x[i] != NULL && (list->compare(x[i]->item, item) < 0)){
            x = x[i]->next;
        }
    }
    if(x[0] != NULL && list->compare(x[0]->item, item) == 0)
        return x[0]->item;
    return NULL;
}
