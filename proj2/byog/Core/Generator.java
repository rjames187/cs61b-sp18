package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Generator {
    private Random random;
    private int width;
    private int height;

    private TETile[][] world;

    private int x;
    private int y;

    public Generator(long seed, int width, int height) {
        this.random = new Random(seed);
        this.width = width;
        this.height = height;

        this.world = new TETile[height][width];
    }

    public TETile[][] generate() {
        initializeWorld();
        draw();

        return world;
    }

    private void draw() {
        x = RandomUtils.uniform(random, 1, width - 2);
        y = 1;

        world[y - 1][x] = Tileset.WALL;
        world[y - 1][x + 1] = Tileset.WALL;
        world[y - 1][x - 1] = Tileset.WALL;

        String dir = "y+";

        int numSteps = RandomUtils.uniform(random, 80, 200);
        for (int i = 0; i < numSteps; i++) {
            String newDir = checkEdge(dir);
            if (newDir != null) {
                dir = newDir;
                continue;
            }

            step(dir);

            dir = getNewDir(dir);

            if (chance(10)) {
                drawRoom(dir);
            }
        }
    }

    private void step(String dir) {
        if (dir == "y+") {
            y += 1;
        } else if (dir == "y-") {
            y -= 1;
        } else if (dir == "x+") {
            x += 1;
        } else if (dir == "x-") {
            x -= 1;
        }

        placeFloor(y, x);
    }

    private void drawRoom(String dir) {
        int origX = x;
        int origY = y;

        for (int i = 0; i < RandomUtils.uniform(random, 3, 9); i++) {
            int newX = origX + i;
            if (validCoord(newX, origY)) {
                placeFloor(origY, newX);
            }
            if (validCoord(newX, origY + 1)) {
                placeFloor(origY + 1, newX);
            }
        }
    }

    private String getNewDir(String dir) {
        if (chance(90)) {
            return dir;
        }

        if (chance(75)) {
            return randomDir("x");
        } else {
            return randomDir("y");
        }
    }

    private String checkEdge(String dir) {
        if (dir == "y+" && y >= height - 2) {
            return randomDir("x");
        } else if (dir == "y-" && y <= 1) {
            return randomDir("x");
        } else if (dir == "x+" && x >= width - 2) {
            return randomDir("y");
        } else if (dir == "x-" && x <= 1) {
            return randomDir("y");
        }
        return null;
    }

    private boolean validCoord(int x, int y) {
        if (y >= height - 1 || y <= 0) {
            return false;
        }
        if (x >= width - 1 || x <= 0) {
            return false;
        }
        return true;
    }

    private void placeFloor(int y, int x) {
        world[y][x] = Tileset.FLOOR;
        placeWall(y + 1, x);
        placeWall(y - 1, x);
        placeWall(y, x + 1);
        placeWall(y, x - 1);
    }

    private void placeWall(int y, int x) {
        TETile tile = world[y][x];
        if (tile == Tileset.FLOOR) {
            return;
        }
        world[y][x] = Tileset.WALL;
    }

    private void initializeWorld() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    private boolean chance(int p) {
        int roll = RandomUtils.uniform(random, 100);
        return roll <= p;
    }

    private String randomDir(String axis) {
        if (axis == "x") {
            if (chance(50)) {
                return "x+";
            } else {
                return "x-";
            }
        } else if (axis == "y") {
            if (chance(50)) {
                return "y+";
            } else {
                return "y-";
            }
        }
        return null;
    }
}
