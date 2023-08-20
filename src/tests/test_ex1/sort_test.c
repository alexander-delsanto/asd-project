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

void test_mbi_sort_null_array_k_zero(void)
{
    int *a = NULL;
    merge_binary_insertion_sort((void *) a, 0, 0, 0, compare_int);
    TEST_ASSERT_NULL(a);
}

void test_insertion_sort_empty_array(void)
{
    int a[] = {};
    binary_insertion_sort((void *) a, 0, 4, compare_int);
    TEST_ASSERT_EQUAL_INT(0, sizeof(a));
}

void test_insertion_sort_one_element_array(void)
{
    int a[] = {1}, b[] = {1};
    binary_insertion_sort((void *) a, 1, 4, compare_int);
    TEST_ASSERT_EQUAL_INT_ARRAY(b, a, 1);
}

void test_insertion_sort_array(void)
{
    binary_insertion_sort((void *)int_arr, length, sizeof(int_arr[0]), compare_int);
    TEST_ASSERT_EQUAL_INT_ARRAY(ordered_int_arr, int_arr, length);
}

void test_merge_sort_null_array(void)
{
    int *a = NULL;
    merge_sort((void *) a, 0, 0, compare_int, 0, 0);
    TEST_ASSERT_NULL(a);
}

void test_merge_sort_one_element_array(void)
{
    int a[] = {1}, b[] = {1};
    merge_sort((void *) a, 1, 0, compare_int, 0, 0);
    TEST_ASSERT_EQUAL_INT_ARRAY(b, a, 1);
}

void test_merge_sort(void)
{
    merge_sort((void *) int_arr, sizeof(int_arr[0]), 0, compare_int, 0, length - 1);
    TEST_ASSERT_EQUAL_INT_ARRAY(ordered_int_arr, int_arr, 6);
}

void test_merge_binary_insertion_sort(void)
{
    merge_binary_insertion_sort((void *) int_arr, 6, sizeof(int_arr[0]), 3, compare_int);
    TEST_ASSERT_EQUAL_INT_ARRAY(ordered_int_arr, int_arr, 6);
}

int main(void)
{
    UNITY_BEGIN();
    RUN_TEST(test_mbi_sort_null_array_k_zero);
    RUN_TEST(test_insertion_sort_empty_array);
    RUN_TEST(test_insertion_sort_one_element_array);
    RUN_TEST(test_insertion_sort_array);
    RUN_TEST(test_merge_sort_null_array);
    RUN_TEST(test_merge_sort_one_element_array);
    RUN_TEST(test_merge_sort);
    RUN_TEST(test_merge_binary_insertion_sort);
    UNITY_END();
}