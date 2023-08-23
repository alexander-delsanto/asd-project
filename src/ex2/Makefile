# Code directories and binary file name
.PHONY: benchmark test
_BINARY=main_ex2
CODEDIRS=. lib
BUILD=build
BIN=bin
TESTDEP=../../resources/C/Unity/unity.c

# Compiling setup
CC=gcc
CFLAGS=-g -Wall -Wextra
CCOMPILE=$(CC) $(CFLAGS)

# Files lists
BINARY=$(addprefix $(BIN)/, $(_BINARY))
CFILES=$(foreach D,$(CODEDIRS),$(wildcard $(D)/*.c))
OBJECTS=$(foreach D,$(CODEDIRS), $(patsubst $(D)/%.c, $(BUILD)/%.o, $(wildcard $(D)/*.c)))

# Main
all: $(BINARY)

$(BINARY): $(CFILES) | $(BIN)
	@echo -e Compiling $(_BINARY)...
	$(CCOMPILE) -o $@ $^

$(BIN):
	@mkdir -p $@

# To generate object files in the build/ folder and link them
# to create the executable replace $(CFILES) with $(OBJECTS) in
# the $(BINARY) dependencies.
$(BUILD):
	mkdir -p $@

$(BUILD)/%.o: src/%.c | $(BUILD)
	@$(CCOMPILE) -c $^ -o $@

$(BUILD)/%.o: src/lib/%.c | $(BUILD)
	@$(CCOMPILE) -c $^ -o $@

benchmark: $(CFILES) | $(BIN)
	@echo -e Running benchamark...
	@$(CC) -o $(BINARY)_benchmark -D BENCHMARK $^
	@scripts/benchmark.sh
	@echo -e Done.

# Unit testing
test: $(BIN)
	@$(CC) lib/mbi_sort.c ../tests/test_ex1/sort_test.c $(TESTDEP) -o $(BIN)/test_ex1
	@./bin/test_ex1


# General use
recompile: clean all
clean:
	@$(RM) -r $(BIN)
	@$(RM) -r $(BUILD)