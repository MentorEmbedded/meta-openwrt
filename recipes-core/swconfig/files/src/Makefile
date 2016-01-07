ifndef CFLAGS
CFLAGS = -O2 -g -I ../src
endif
LIBS = $(shell pkg-config --libs libnl-3.0)
INCS = $(shell pkg-config --cflags libnl-3.0)

all: swconfig

%.o: %.c
	$(CC) $(CFLAGS) $(INCS) -c -o $@ $^

swconfig: cli.o swlib.o uci.o
	$(CC) $(LDFLAGS) -o $@ $^ $(LIBS)
