#include "../lib/skiplist.h"
#include "../lib/skiplist_utils.h"
#include "../../../resources/C/Unity/unity.h"

int compare_int(const void *int1_pointer, const void *int2_pointer)
{
    if(*(int *)int1_pointer == *(int *)int2_pointer)
        return 0;
    else if(*(int *)int1_pointer < *(int *)int2_pointer)
        return -1;
    return 1;
}

static struct SkipList *list;
static int n1, n2, n3, n4, n5 , n6;

void setUp(void)
{
    n1 = 3, n2 = 0, n3 = -1, n4 = 2, n5 = 1, n6 = -2;
    new_skiplist(&list, 10, compare_int);
}

void tearDown(void)
{
    clear_skiplist(&list);
}

void test_new_skiplist(void)
{
    struct SkipList *s_list;
    new_skiplist(&s_list, 10, compare_int);
    TEST_ASSERT_NOT_NULL(s_list);
    clear_skiplist(&s_list);
}

void test_clear_skiplist(void)
{
    struct SkipList *s_list;
    new_skiplist(&s_list, 10, compare_int);
    clear_skiplist(&s_list);
    TEST_ASSERT_NULL(s_list);
}

void test_skiplist_is_empty_zero_el(void)
{
    TEST_ASSERT_TRUE(skiplist_is_empty(list));
}

void test_skiplist_is_empty_one_el(void)
{
    insert_skiplist(list, (void *)&n1);
    TEST_ASSERT_FALSE(skiplist_is_empty(list));
}

void test_insert_skiplist_size_one_el(void)
{
    insert_skiplist(list, (void *)&n1);
    TEST_ASSERT_EQUAL_INT(1, skiplist_length(list));
}

void test_insert_skiplist_size_two_el(void)
{
    insert_skiplist(list, (void *)&n1);
    insert_skiplist(list, (void *)&n2);
    TEST_ASSERT_EQUAL_INT(2, skiplist_length(list));
}

void test_skiplist_search_existing_el(void)
{
    insert_skiplist(list, (void *)&n1);
    insert_skiplist(list, (void *)&n2);
    insert_skiplist(list, (void *)&n3);
    insert_skiplist(list, (void *)&n4);
    insert_skiplist(list, (void *)&n5);
    insert_skiplist(list, (void *)&n6);
    int elem = 3;
    TEST_ASSERT_EQUAL_INT(3, *(int *)search_skiplist(list, &elem));
}

void test_skiplist_search_not_existing_el(void)
{
    insert_skiplist(list, (void *)&n1);
    insert_skiplist(list, (void *)&n2);
    insert_skiplist(list, (void *)&n3);
    insert_skiplist(list, (void *)&n4);
    insert_skiplist(list, (void *)&n5);
    insert_skiplist(list, (void *)&n6);
    int elem = 4;
    TEST_ASSERT_NULL(search_skiplist(list, &elem));
}

void test_skiplist_is_ordered(void)
{
    insert_skiplist(list, (void *)&n1);
    insert_skiplist(list, (void *)&n2);
    insert_skiplist(list, (void *)&n3);
    insert_skiplist(list, (void *)&n4);
    insert_skiplist(list, (void *)&n5);
    insert_skiplist(list, (void *)&n6);
    int list_arr[6];
    struct Node *n = list->heads[0];
    for(int i = 0; i < 6; i++){
        list_arr[i] = *(int *)n->item;
        n = n->next[0];
    }
    int ordered_arr[6] = {-2, -1, 0, 1, 2, 3};
    TEST_ASSERT_EQUAL_INT_ARRAY(ordered_arr, list_arr, 6);
}

int main(void)
{
    UNITY_BEGIN();
    RUN_TEST(test_new_skiplist);
    RUN_TEST(test_clear_skiplist);
    RUN_TEST(test_skiplist_is_empty_zero_el);
    RUN_TEST(test_skiplist_is_empty_one_el);
    RUN_TEST(test_insert_skiplist_size_one_el);
    RUN_TEST(test_insert_skiplist_size_two_el);
    RUN_TEST(test_skiplist_search_existing_el);
    RUN_TEST(test_skiplist_search_not_existing_el);
    RUN_TEST(test_skiplist_is_ordered);
    UNITY_END();
}