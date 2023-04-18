public class Request {
    private int block;
    private int priority;

    public Request(int block, int priority) {
        this.block = block;
        this.priority = priority;
    }

    public int getBlock() {
        return block;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "(" + block + ", " + priority + ")";
    }
}