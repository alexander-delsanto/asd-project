#include "dictionary.h"
#include <string.h>

int main(int argc, char *argv[])
{
    if(argc < 4){
        dprintf(2, "Usage: main_ex1 <dictfile> <textfile> <max_height>\n");
        exit(EXIT_FAILURE);
        }

    // Setting up inputs for find_errors
    size_t max_height;
    FILE *dictfile, *textfile;

    dictfile = fopen(argv[1], "r");
    if(dictfile == NULL){
        dprintf(2, "main: unable to open %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }
    textfile = fopen(argv[2], "r");
    if(textfile == NULL){
        dprintf(2, "main: unable to open %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }
    max_height = atol(argv[3]);

    find_errors(dictfile, textfile, max_height);
    fclose(dictfile);
    fclose(textfile);

    exit(EXIT_SUCCESS);
}
