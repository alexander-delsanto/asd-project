# Code directories and binary file name
_BINARY=main_ex1
CODEDIRS=src src/lib
BUILD=build
BIN=bin

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
	$(CC) -o $@ $^

$(BIN):
	mkdir -p $@

# To generate object files in the build/ folder and link them
# to create the executable replace $(CFILES) with $(OBJECTS) in
# the $(BINARY) dependencies.
$(BUILD):
	mkdir -p $@

$(BUILD)/%.o: src/%.c | $(BUILD)
	$(CCOMPILE) -c $^ -o $@

$(BUILD)/%.o: src/lib/%.c | $(BUILD)
	$(CCOMPILE) -c $^ -o $@

# General use
recompile: clean all
clean:
	$(RM) -r $(BIN)
	$(RM) -r $(BUILD)