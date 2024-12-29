package common;

public enum Direction {
    NORTH(0, -1, true),
    NORTHEAST(1, -1, false),
    EAST(1, 0, true),
    SOUTHEAST(1, 1, false),
    SOUTH(0, 1, true),
    SOUTHWEST(-1, 1, false),
    WEST(-1, 0, true),
    NORTHWEST(-1, -1, false);

    private final int deltaX;
    private final int deltaY;
    private final boolean cardinal;

    Direction(int deltaX, int deltaY, boolean Cardinal) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.cardinal = Cardinal;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public boolean isCardinal() {
        return cardinal;
    }
}
