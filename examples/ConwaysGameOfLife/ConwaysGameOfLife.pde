import com.grough.cellularAutomata.*;

class GameOfLife extends Automaton<Boolean> {

  // Start with a random selection of living cells.
  Boolean populate() {
    return random(0, 1) < 0.25;
  }

  // Calculate the next frame
  Boolean evolve() {
    // Count living neighbors
    int neighbors = 0;
    neighbors += rel(-1, -1) ? 1 : 0;
    neighbors += rel(-1, 0) ? 1 : 0;
    neighbors += rel(-1, 1) ? 1 : 0;
    neighbors += rel(0, -1) ? 1 : 0;
    neighbors += rel(0, 1) ? 1 : 0;
    neighbors += rel(1, -1) ? 1 : 0;
    neighbors += rel(1, 0) ? 1 : 0;
    neighbors += rel(1, 1) ? 1 : 0;

    // A live cell with fewer than two live neighbors dies.
    if (self() && neighbors < 2) {
      return false;
    }

    // A live cell with two or three live neighbors survives.
    if (self() && (neighbors == 2 || neighbors == 3)) {
      return true;
    }

    // A live cell with more than three live neighbors dies.
    if (self() && neighbors > 3) {
      return false;
    }

    // A dead cell with exactly three live neighbors becomes a live cell.
    if (!self() && neighbors == 3) {
      return true;
    }

    return self();
  }

  // Paint dead cells yellow and living cells red.
  int shade() {
    if (self()) {
      return #FF4136;
    } else {
      return #FFDC00;
    }
  }
}

GameOfLife life = new GameOfLife();

void setup() {
  noSmooth();
  size(480, 480);
  frameRate(12);
  life.size(32, 32);
}

void draw() {
  life.next();
  image(life.graphics(), 0, 0, 480, 480);
}
