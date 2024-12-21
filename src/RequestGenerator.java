import java.util.Random;

public class RequestGenerator implements Runnable {
    private final Dispatcher dispatcher;
    private final Random random;

    public RequestGenerator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.random = new Random();
    }

    private Request generateRandomRequest() {
        int sourceFloor = random.nextInt(10); // Генерация этажа от 0 до 9
        int destinationFloor = random.nextInt(10);
        while (destinationFloor == sourceFloor) { // Проверяем, чтобы этаж назначения не совпадал с этажом вызова
            destinationFloor = random.nextInt(10);
        }
        return new Request(sourceFloor, destinationFloor);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000); // Генерация новой заявки каждую секунду
                Request newRequest = generateRandomRequest();
                dispatcher.assignRequest(newRequest);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
