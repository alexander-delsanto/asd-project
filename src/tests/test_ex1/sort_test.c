#include "../../ex1/lib/mbi_sort.h"
#include "../../../resources/C/Unity/unity.h"

int compare_int(const void *int1_pointer, const void *int2_pointer)
{
    if(*(int *)int1_pointer == *(int *)int2_pointer)
        return 0;
    else if(*(int *)int1_pointer < *(int *)int2_pointer)
        return -1;
    return 1;
}

static int length = 6;
static int *int_arr;
static const int ordered_int_arr[] = {-1, 0, 1, 2, 3, 4};

void setUp(void)
{
    int_arr = malloc(sizeof(int) * length);
    int_arr[0] = 4;
    int_arr[1] = -1;
    int_arr[2] = 2;
    int_arr[3] = 0;
    int_arr[4] = 1;
    int_arr[5] = 3;
}

void tearDown(void)
{
    free(int_arr);
}

void test_mbi_sort_null_array(void)
{
    int *a = NULL;
    merge_binary_insertion_sort((void *) a, 0, 0, 0, compare_int);
    TEST_ASSERT_NULL(a);
    merge_binary_insertion_sort((void *) a, 0, 0, 3, compare_int);
    TEST_ASSERT_NULL(a);
    merge_binary_insertion_sort((void *) a, 0, 0, length, compare_int);
    TEST_ASSERT_NULL(a);

}

void test_mbi_sort_empty_array(void)
{
    int a[] = {};
    merge_binary_insertion_sort((void *) a, 0, sizeof(int), 0, compare_int);
    TEST_ASSERT_EQUAL_INT(0, sizeof(a));
    merge_binary_insertion_sort((void *) a, 0, sizeof(int), 3, compare_int);
    TEST_ASSERT_EQUAL_INT(0, sizeof(a));
    merge_binary_insertion_sort((void *) a, 0, sizeof(int), length, compare_int);
    TEST_ASSERT_EQUAL_INT(0, sizeof(a));
}

void test_mbi_sort_one_element_array(void)
{
    int a[] = {1}, b[] = {1};
    merge_binary_insertion_sort((void *) a, 1, sizeof(int), 0, compare_int);
    TEST_ASSERT_EQUAL_INT_ARRAY(b, a, 1);
    merge_binary_insertion_sort((void *) a, 1, sizeof(int), 3, compare_int);
    TEST_ASSERT_EQUAL_INT_ARRAY(b, a, 1);
    merge_binary_insertion_sort((void *) a, 1, sizeof(int), length, compare_int);
    TEST_ASSERT_EQUAL_INT_ARRAY(b, a, 1);
}

void test_mbi_sort_same_elements_array(void)
{
    int a[] = {1, 1, 1, 1}, b[] = {1, 1, 1, 1};
    merge_binary_insertion_sort((void *) a, 4, sizeof(int), 0, compare_int);
    TEST_ASSERT_EQUAL_INT_ARRAY(b, a, 4);
    merge_binary_insertion_sort((void *) a, 4, sizeof(int), 3, compare_int);
    TEST_ASSERT_EQUAL_INT_ARRAY(b, a, 4);
    merge_binary_insertion_sort((void *) a, 4, sizeof(int), length, compare_int);
    TEST_ASSERT_EQUAL_INT_ARRAY(b, a, 4);
}

void test_mbi_sort_array(void)
{
    merge_binary_insertion_sort((void *)int_arr, length, sizeof(int_arr[0]), 0, compare_int);
    TEST_ASSERT_EQUAL_INT_ARRAY(ordered_int_arr, int_arr, length);
    tearDown();
    setUp();
    merge_binary_insertion_sort((void *)int_arr, length, sizeof(int_arr[0]), 3, compare_int);
    TEST_ASSERT_EQUAL_INT_ARRAY(ordered_int_arr, int_arr, length);
    tearDown();
    setUp();
    merge_binary_insertion_sort((void *)int_arr, length, sizeof(int_arr[0]), length, compare_int);
    TEST_ASSERT_EQUAL_INT_ARRAY(ordered_int_arr, int_arr, length);
}

int main(void)
{
    UNITY_BEGIN();
    RUN_TEST(test_mbi_sort_null_array);
    RUN_TEST(test_mbi_sort_empty_array);
    RUN_TEST(test_mbi_sort_one_element_array);
    RUN_TEST(test_mbi_sort_same_elements_array);
    RUN_TEST(test_mbi_sort_array);
    UNITY_END();
}