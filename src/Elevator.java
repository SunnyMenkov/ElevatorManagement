import java.util.Queue;
import java.util.LinkedList;

public class Elevator implements Runnable {
    private final int id;
    private int currentFloor;
    private String direction; // "UP", "DOWN", "IDLE"
    private final Queue<Request> tasks;
    private boolean isRunning;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0; // Начальный этаж
        this.direction = "IDLE";
        this.tasks = new LinkedList<>();
        this.isRunning = true;
    }

    public synchronized void addTask(Request request) {
        tasks.offer(request);
    }

    public synchronized void move() {
        if (!tasks.isEmpty()) {
            Request currentRequest = tasks.peek();
            if (currentRequest.getSourceFloor() > currentFloor) {
                direction = "UP";
                currentFloor++;
            } else if (currentRequest.getSourceFloor() < currentFloor) {
                direction = "DOWN";
                currentFloor--;
            }

            if (currentRequest.getSourceFloor() == currentFloor) {
                tasks.poll(); // Заявка выполнена
                System.out.println("Elevator " + id + " picked up passenger at floor " + currentFloor);
            }
        } else {
            direction = "IDLE";
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            move();
            try {
                Thread.sleep(1000); // Задержка для имитации движения
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public String getDirection() {
        return direction;
    }
}
