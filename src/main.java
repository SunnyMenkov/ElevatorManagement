import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        List<Elevator> elevators = new ArrayList<>();
        int numElevators = 3; // Количество лифтов

        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator(i + 1));
        }

        Dispatcher dispatcher = new Dispatcher(elevators);
        RequestGenerator requestGenerator = new RequestGenerator(dispatcher);

        // Запуск потоков для лифтов
        for (Elevator elevator : elevators) {
            new Thread(elevator).start();
        }

        // Запуск потока для генератора заявок
        new Thread(requestGenerator).start();
    }
}
