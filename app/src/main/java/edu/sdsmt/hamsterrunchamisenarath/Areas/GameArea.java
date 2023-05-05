package edu.sdsmt.hamsterrunchamisenarath.Areas;

import edu.sdsmt.hamsterrunchamisenarath.Game;

// `public interface GameArea {` is declaring a Java interface named `GameArea` with two abstract
// methods `pickup` and `enter`. Any class that implements this interface must provide an
// implementation for these two methods.
public interface GameArea {
    void pickup(Game g);
    void enter(Game g);
}
