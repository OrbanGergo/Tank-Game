import java.util.stream.IntStream;

public class TrajectoryCalculator {

    private static final double GRAVITY = 9.81;
    private int[] xCoordinates;
    private int[] yCoordinates;

    public TrajectoryCalculator(double angle, double velocity, int steps) {
        // A koordináták tárolása
        xCoordinates = new int[steps];
        yCoordinates = new int[steps];

        double radianAngle = Math.toRadians(angle);
//        for (int t = 0; t < steps; t++) {
//            double time = t * 0.1;
//            int x = (int) (velocity * time * Math.cos(radianAngle));
//            int y = (int) (velocity * time * Math.sin(radianAngle) - 0.5 * GRAVITY * time * time);
//
//
//            if (y < -300) break;
//
//            xCoordinates[t] = x;
//            yCoordinates[t] = y;
//        }

        IntStream.range(0, steps)
                .takeWhile(t -> {
                    double time = t * 0.1;
                    int y = (int) (velocity * time * Math.sin(radianAngle) - 0.5 * GRAVITY * time * time);
                    return y >= -300;
                })
                .forEach(t -> {
                    double time = t * 0.1;
                    xCoordinates[t] = (int) (velocity * time * Math.cos(radianAngle));
                    yCoordinates[t] = (int) (velocity * time * Math.sin(radianAngle) - 0.5 * GRAVITY * time * time);
                });
    }

    public int[] getXCoordinates() {
        return xCoordinates;
    }

    public int[] getYCoordinates() {
        return yCoordinates;
    }

    public int Distance(int x, int y, int xx, int yy) {
        int dx = xx - x;
        int dy = yy - y;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
}


