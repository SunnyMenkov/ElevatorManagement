import java.util.List;

public class Dispatcher {
    private final List<Elevator> elevators;

    public Dispatcher(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    public void assignRequest(Request request) {
        Elevator bestElevator = null;
        int bestDistance = Integer.MAX_VALUE;

        // Поиск лифта, который минимизирует расстояние
        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - request.getSourceFloor());
            if (distance < bestDistance) {
                bestDistance = distance;
                bestElevator = elevator;
            }
        }

        if (bestElevator != null) {
            bestElevator.addTask(request);
            System.out.println("Assigned request " + request + " to Elevator " + bestElevator.getCurrentFloor());
        }
    }
}
