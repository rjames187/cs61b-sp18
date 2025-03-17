public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double numerator = Planet.G * this.mass * p.mass;
        double distance = calcDistance(p);
        double denominator = distance * distance;
        return numerator / denominator;
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double numerator = calcForceExertedBy(p) * dx;
        double denominator = calcDistance(p);
        return numerator / denominator;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        double numerator = calcForceExertedBy(p) * dy;
        double denominator = calcDistance(p);
        return numerator / denominator;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double result = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                result += calcForceExertedByX(p);
            }
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double result = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                result += calcForceExertedByY(p);
            }
        }
        return result;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX / mass;
        double ay = fY / mass;
        double vx = xxVel + dt * ax;
        double vy = yyVel + dt * ay;
        xxPos = xxPos + dt * vx;
        yyPos = yyPos + dt * vy;
        xxVel = vx;
        yyVel = vy;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
