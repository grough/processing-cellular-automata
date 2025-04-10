# Cellular Automata for Processing

This library allows you to make art in Processing using [cellular automata](https://en.wikipedia.org/wiki/Cellular_automaton). See [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) for an example of a well known cellular automaton.

## Get Started

1. Install [Processing](https://processing.org)
2. Download the latest [automata.zip](https://github.com/grough/processing-cellular-automata/releases/latest/download/automata.zip)
3. Extract the zip file to the "libraries" sub-folder of your Processing documents folder
4. Launch Processing, go to _File → Examples_, and browse the _Cellular Automata_ examples

## Usage

```processing
// Import the library
import com.grough.automata.*;

// Create your own animation by extending the base class.
// Choose a data type for your cells. In this case we use Boolean.
class MyAnimation extends Automaton<Boolean> {

  // Populate the initial cell values.
  Boolean populate() {
    return random(0, 1) < 0.5;
  }

  // Calculate the next cell value based on the previous cell value.
  Boolean evolve() {
    if (random(0, 1) < 0.1) {
      return self();
    } else {
      return !self();
    }
  }

  // Assign the cell a color based on its value.
  int shade() {
    return self() ? 0 : 255;
  }
}

MyAnimation animation = new MyAnimation();

void setup() {
  size(640, 640);
  
  // Keep the edges sharp when we scale up the graphics.
  noSmooth();
}

void draw() {
  // Advance to the next animation frame each time we draw.
  animation.next();

  // Draw the animation's graphics on the screen.
  image(animation.graphics(), 0, 0, 640, 640);
}
```
