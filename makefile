JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class: ; $(JC) $(JFLAGS) $*.java

PACKAGES := Main.java \
    src/game/display \
    src/game/interfaces \
    src/game/sprites \
    src/game/gamelogic

CLASSES := $(shell find $(PACKAGES) -type f -name '*.java')

.PHONY: default clean

default: classes

classes: $(CLASSES:.java=.class)

clean:  $(rm) $(shell find $(PACKAGES) -type f -name '*.class') 
