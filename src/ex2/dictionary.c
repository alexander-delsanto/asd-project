#include "dictionary.h"
#include "lib/skiplist.h"
#include <string.h>

#define BUFFER_SIZE 1024

static int compare_string(const void *string_1, const void *string_2)
{
    return strcmp((char *)string_1, (char *)string_2);
}

static void string_to_lowercase(char *string)
{
    for(size_t i = 0; i < strlen(string); i++){
        if(string[i] >= 'A' && string[i] <= 'Z')
            string[i] += 32;
    }
}

static struct SkipList * dictionary_to_skiplist(FILE *dictfile, size_t max_height)
{
    struct SkipList *dictionary;
    new_skiplist(&dictionary, max_height, compare_string);

    int i = 0;
    char ch;
    char buffer[BUFFER_SIZE];
    while((ch = fgetc(dictfile)) != EOF){
        if(ch != '\n'){
            buffer[i] = ch;
            i++;
        }else{
            buffer[i] = '\0';
            char *s = strdup(buffer);
            insert_skiplist(dictionary, (void *) s);
            i = 0;
        }
    }
    return dictionary;
}

void find_errors(FILE *dictfile, FILE *textfile, size_t max_height)
{
    struct SkipList *dictionary = dictionary_to_skiplist(dictfile, max_height);

    int i = 0;
    char ch;
    char buffer[BUFFER_SIZE];
    while((ch = fgetc(textfile)) != EOF){
        if(ch >= 'A' && ch <= 'z'){
            buffer[i] = ch;
            i++;
        }else if(i > 0){
            buffer[i] = '\0';
            string_to_lowercase(buffer);
            char *s = strdup(buffer);
            if(search_skiplist(dictionary, (void *) s) == NULL)
                printf("%s\n", s);
            i = 0;
        }
    }
}
