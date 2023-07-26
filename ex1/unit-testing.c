#include "mbi-sort.h"
#include "unity/unity.h"

void setUp(){}
void tearDown(){}

void test_compare_int()
{
	int num1 = 5, num2 = 6;
	void *p1 = &num1, *p2 = &num2;
	TEST_ASSERT_EQUAL_INT(-1, compare_int(p1, p2));
	TEST_ASSERT_EQUAL_INT(1, compare_int(p2, p1));
	TEST_ASSERT_EQUAL_INT(0, compare_int(p1, p1));
}

void test_compare_double()
{
	double num1 = 5.53413, num2 = 6.1254922;
	void *p1 = &num1, *p2 = &num2;
	TEST_ASSERT_EQUAL_INT(-1, compare_double(p1, p2));
	TEST_ASSERT_EQUAL_INT(1, compare_double(p2, p1));
	TEST_ASSERT_EQUAL_INT(0, compare_double(p1, p1));
}

void test_compare_string()
{
	char str1[] = "Test String ", str2[] = "Test String 2";
	void *p1 = &str1, *p2 = &str2;
	TEST_ASSERT_EQUAL_INT(-1, compare_string(p1, p2));
	TEST_ASSERT_EQUAL_INT(1, compare_string(p2, p1));
	TEST_ASSERT_EQUAL_INT(0, compare_string(p1, p1));
}


int main()
{
	UNITY_BEGIN();
	RUN_TEST(test_compare_int);
	RUN_TEST(test_compare_double);
	RUN_TEST(test_compare_string);
	UNITY_END();
}