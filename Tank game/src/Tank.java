public class Tank {
    private int angle;
    private int position;
    private int health;
    private int imgWidth;
    private int imgHeight;
    private int diff;
    private int imagePositionA;
    private int imagePositionB;
    private int turretAngle;
    private int turretPosA;
    private int turretPosB;
    private int power;
    private int positionB;


    public Tank(int angle, int position, int positionB) {
        this.angle = angle;
        this.position = position;
        this.positionB = positionB;
        health = 3;

        if (angle > 90) {
            diff = 360 - angle;
        } else {
            diff = -angle;
        }
        //System.out.println(diff);
        imgWidth = 60 + Math.abs(diff) / 3;
        imgHeight = imgWidth / 3 * 2;
        //System.out.println(diff);


        int turretAngle = 360 - angle;
        turretPosA = (int) (Math.sin(Math.toRadians(turretAngle)) * imgHeight);
        turretPosB = (int) (Math.cos(Math.toRadians(turretAngle)) * imgHeight);


        //System.out.println(diff);
        imagePositionA = position - turretPosA - 30;
        imagePositionA += diff / 2;

        imagePositionB = positionB - turretPosB + 10;
        imagePositionB -= Math.abs(diff) / 4;


    }

    public int getAngle() {
        return angle;
    }

    public int getPosition() {
        return position;
    }

    public int getHealth() {
        return health;
    }

    public void lowerHealth() {
        health--;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public int getDiff() {
        return diff;
    }

    public int getImagePositionA() {
        return imagePositionA;
    }

    public int getImagePositionB() {
        return imagePositionB;
    }

    public int getTurretAngle() {
        return turretAngle;
    }

    public void setTurretAngle(int turretAngle) {
        this.turretAngle = turretAngle;
    }

    public int getRealTurretAngle() {
        return angle - turretAngle;
    }

    public int getTurretPosA() {
        return turretPosA;
    }

    public int getTurretPosB() {
        return turretPosB;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPositionB(int positionB) {
        this.positionB = positionB;
    }
}
