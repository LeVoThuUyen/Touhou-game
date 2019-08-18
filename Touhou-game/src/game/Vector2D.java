package game;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void scale(double rate) {
        this.x *= rate;
        this.y *= rate;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector2D clone() {
        return new Vector2D(this.x, this.y);
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public void setLength(double length) {
        double currentLength = this.getLength();
        if(currentLength != 0) {
//            this.x = x * length / currentLength;
//            this.y = y * length / currentLength;
            this.scale(length / currentLength);
        }
    }

    public double distanceTo(Vector2D other) {
        double distanceX = other.x - this.x;
        double distanceY = other.y - this.y;
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    public double getAngle() {
        return Math.atan2(y, x);
    }

    public void setAngle(double angle) {
        double length = this.getLength();
        if(length != 0) {
            this.x = length * Math.cos(angle);
            this.y = length * Math.sin(angle);
        }
    }

    public static void main(String[] args) {
        Vector2D v1 = new Vector2D(3, 3);
        System.out.println(v1.getLength() + " " + (3 * Math.sqrt(2)));
        System.out.println(v1.getAngle() + " " + (Math.PI / 4));

        v1.setLength(10);
        System.out.println(v1.getLength());
        v1.setAngle(Math.toRadians(60));
        System.out.println(v1.getAngle() + " " + (Math.toRadians(60)));
    }

}
