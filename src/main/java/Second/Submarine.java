package Second;

public class Submarine {

    private int position = 0;
    private int depth = 0;
    private int aim = 0;

    public int getPosition() {
        return position;
    }

    public int getDepth() {
        return depth;
    }

    public void forward(int x) {
        position += x;
        depth += aim * x;
    }

    public void up(int x) {
        aim -= x;
    }

    public void down(int x) {
        aim += x;
    }

}
