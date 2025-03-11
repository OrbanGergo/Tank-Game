import java.util.Random;
import java.util.stream.IntStream;

public class MapGenerator {
    private static int[] map;

    public MapGenerator() {
        map = new int[1400];
        int kezd = new Random().nextInt(10000);
        IntStream.range(0, 1400).forEach(i ->
                map[i] = (int) (Math.sin((kezd + i) * 0.01) * 60
                        + Math.sin(0.007 * (kezd + i)) * 40
                        + Math.sin(0.002) * 70
                        + 400)
        );
        map[1399] = 50;
    }

    public int[] GetMap() {
        return map;
    }

    public static int getAngle(int target) {
        int left = target - 5;
        int right = target + 5;
        int angle = (int) Math.toDegrees(Math.atan2(map[right] - map[left], right - left));

        if (angle < 0) {
            angle += 360;
        }

        return angle;
    }

    public static Tank GenerateTankLeft() {
        int position = new Random().nextInt(640) + 30;
        Tank tank = new Tank(getAngle(position), position, map[position]);
        return tank;
    }

    public static Tank GenerateTankRight() {
        int position = new Random().nextInt(640) + 700;
        Tank tank = new Tank(getAngle(position), position, map[position]);
        return tank;
    }
}
