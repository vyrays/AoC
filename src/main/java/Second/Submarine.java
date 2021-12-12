package Second;

public class Submarine {

    private int position = 0;
    private int depth = 0;

    public int getPosition() {
        return position;
    }

    public int getDepth() {
        return depth;
    }

    public void forward(int x) {
        position += x;
    }

    public void up(int x) {
        depth -= x;
    }

    public void down(int x) {
        depth += x;
    }

}
