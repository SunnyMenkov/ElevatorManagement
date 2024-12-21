public class Request {
    private final int sourceFloor;
    private final int destinationFloor;
    private final long timestamp;

    public Request(int sourceFloor, int destinationFloor) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.timestamp = System.currentTimeMillis();
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Request from " + sourceFloor + " to " + destinationFloor;
    }
}
