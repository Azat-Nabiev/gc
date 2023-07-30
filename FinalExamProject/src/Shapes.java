import com.jogamp.opengl.GL2;

public class Shapes {

    public static void cubeAzat(GL2 gl) {
        cuboid(gl, 2, 1, 0.1, true);
    }

    public static void platform(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslated(0, 0, -0.5);
        uvPlatform(gl, 2, 0.2, 16, 10, 5, true);
        gl.glPopMatrix();
    }

    public static void uvCylinder(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslated(0, 0, -0.5);
        uvCylinder(gl, 0.4, 1.2, 16, 10, 5, true);
        gl.glPopMatrix();
    }

    public static void uvPlatform(GL2 gl, double radius, double height,
                                  int slices, int stacks, int rings, boolean makeTexCoords) {
        double stackHeight = height / stacks;
        double ringHeight = height / (rings + 1);
        double deltaRadius = radius / rings;

        for (int i = 0; i < slices; i++) {
            double angle1 = (i * 2 * Math.PI) / slices;
            double angle2 = ((i + 1) * 2 * Math.PI) / slices;

            double x1 = radius * Math.cos(angle1);
            double z1 = radius * Math.sin(angle1);
            double x2 = radius * Math.cos(angle2);
            double z2 = radius * Math.sin(angle2);

            for (int j = 0; j <= stacks; j++) {
                double y = j * stackHeight;

                gl.glBegin(GL2.GL_QUADS);
                gl.glNormal3d(x1 / radius, 0, z1 / radius);
                if (makeTexCoords)
                    gl.glTexCoord2d(i / (double) slices, j / (double) stacks);
                gl.glVertex3d(x1, y, z1);
                gl.glNormal3d(x2 / radius, 0, z2 / radius);
                if (makeTexCoords)
                    gl.glTexCoord2d((i + 1) / (double) slices, j / (double) stacks);
                gl.glVertex3d(x2, y, z2);

                gl.glNormal3d(x2 / radius, 0, z2 / radius);
                if (makeTexCoords)
                    gl.glTexCoord2d((i + 1) / (double) slices, (j + 1) / (double) stacks);
                gl.glVertex3d(x2, y + stackHeight, z2);
                gl.glNormal3d(x1 / radius, 0, z1 / radius);
                if (makeTexCoords)
                    gl.glTexCoord2d(i / (double) slices, (j + 1) / (double) stacks);
                gl.glVertex3d(x1, y + stackHeight, z1);
                gl.glEnd();
            }

            for (int k = 0; k < rings; k++) {
                double y = (k + 1) * ringHeight;
                double r1 = radius - (k + 1) * deltaRadius;
                double r2 = radius - k * deltaRadius;

                gl.glBegin(GL2.GL_QUADS);
                for (int j = 0; j < slices; j++) {
                    angle1 = (j * 2 * Math.PI) / slices;
                    angle2 = ((j + 1) * 2 * Math.PI) / slices;

                    x1 = r1 * Math.cos(angle1);
                    z1 = r1 * Math.sin(angle1);
                    x2 = r1 * Math.cos(angle2);
                    z2 = r1 * Math.sin(angle2);
                    double x3 = r2 * Math.cos(angle2);
                    double z3 = r2 * Math.sin(angle2);
                    double x4 = r2 * Math.cos(angle1);
                    double z4 = r2 * Math.sin(angle1);

                    gl.glNormal3d(0, -1, 0);
                    if (makeTexCoords)
                        gl.glTexCoord2d(j / (double) slices, k / (double) rings);
                    gl.glVertex3d(x1, y - ringHeight, z1);
                    if (makeTexCoords)
                        gl.glTexCoord2d((j + 1) / (double) slices, k / (double) rings);
                    gl.glVertex3d(x2, y - ringHeight, z2);
                    if (makeTexCoords)
                        gl.glTexCoord2d((j + 1) / (double) slices, (k + 1) / (double) rings);
                    gl.glVertex3d(x3, y, z3);
                    if (makeTexCoords)
                        gl.glTexCoord2d(j / (double) slices, (k + 1) / (double) rings);
                    gl.glVertex3d(x4, y, z4);

                    gl.glNormal3d(0, 1, 0);
                    if (makeTexCoords)
                        gl.glTexCoord2d(j / (double) slices, k / (double) rings);
                    gl.glVertex3d(x4, height - y, z4);
                    if (makeTexCoords)
                        gl.glTexCoord2d((j+ 1) / (double) slices, k / (double) rings);
                    gl.glVertex3d(x3, height - y, z3);
                    if (makeTexCoords)
                        gl.glTexCoord2d((j + 1) / (double) slices, (k + 1) / (double) rings);
                    gl.glVertex3d(x2, height - y + ringHeight, z2);
                    if (makeTexCoords)
                        gl.glTexCoord2d(j / (double) slices, (k + 1) / (double) rings);
                    gl.glVertex3d(x1, height - y + ringHeight, z1);
                }
                gl.glEnd();
            }
        }
    }

    public static void uvCone(GL2 gl) {
        gl.glTranslated(0, 0, -0.5);
        uvCone(gl, 0.5, 1, 16, 10, 5, true);
    }

    public static void uvCylinder(GL2 gl, double radius, double height,
                                  int slices, int stacks, int rings, boolean makeTexCoords) {
        if (radius <= 0)
            throw new IllegalArgumentException("Radius must be positive.");
        if (height <= 0)
            throw new IllegalArgumentException("Height must be positive.");
        if (slices < 3)
            throw new IllegalArgumentException("Number of slices must be at least 3.");
        if (stacks < 2)
            throw new IllegalArgumentException("Number of stacks must be at least 2.");
        for (int j = 0; j < stacks; j++) {
            double z1 = (height / stacks) * j;
            double z2 = (height / stacks) * (j + 1);
            gl.glBegin(GL2.GL_QUAD_STRIP);
            for (int i = 0; i <= slices; i++) {
                double longitude = (2 * Math.PI / slices) * i;
                double sinLong = Math.sin(longitude);
                double cosLong = Math.cos(longitude);
                double x = cosLong;
                double y = sinLong;
                gl.glNormal3d(x, y, 0);
                if (makeTexCoords)
                    gl.glTexCoord2d(1.0 / slices * i, 1.0 / stacks * (j + 1));
                gl.glVertex3d(radius * x, radius * y, z2);
                if (makeTexCoords)
                    gl.glTexCoord2d(1.0 / slices * i, 1.0 / stacks * j);
                gl.glVertex3d(radius * x, radius * y, z1);
            }
            gl.glEnd();
        }
        if (rings > 0) { // draw top and bottom
            gl.glNormal3d(0, 0, 1);
            for (int j = 0; j < rings; j++) {
                double d1 = (1.0 / rings) * j;
                double d2 = (1.0 / rings) * (j + 1);
                gl.glBegin(GL2.GL_QUAD_STRIP);
                for (int i = 0; i <= slices; i++) {
                    double angle = (2 * Math.PI / slices) * i;
                    double sin = Math.sin(angle);
                    double cos = Math.cos(angle);
                    if (makeTexCoords)
                        gl.glTexCoord2d(0.5 * (1 + cos * d1), 0.5 * (1 + sin * d1));
                    gl.glVertex3d(radius * cos * d1, radius * sin * d1, height);
                    if (makeTexCoords)
                        gl.glTexCoord2d(0.5 * (1 + cos * d2), 0.5 * (1 + sin * d2));
                    gl.glVertex3d(radius * cos * d2, radius * sin * d2, height);
                }
                gl.glEnd();
            }
            gl.glNormal3d(0, 0, -1);
            for (int j = 0; j < rings; j++) {
                double d1 = (1.0 / rings) * j;
                double d2 = (1.0 / rings) * (j + 1);
                gl.glBegin(GL2.GL_QUAD_STRIP);
                for (int i = 0; i <= slices; i++) {
                    double angle = (2 * Math.PI / slices) * i;
                    double sin = Math.sin(angle);
                    double cos = Math.cos(angle);
                    if (makeTexCoords)
                        gl.glTexCoord2d(0.5 * (1 + cos * d2), 0.5 * (1 + sin * d2));
                    gl.glVertex3d(radius * cos * d2, radius * sin * d2, 0);
                    if (makeTexCoords)
                        gl.glTexCoord2d(0.5 * (1 + cos * d1), 0.5 * (1 + sin * d1));
                    gl.glVertex3d(radius * cos * d1, radius * sin * d1, 0);
                }
                gl.glEnd();
            }
        }
    }

    public static void uvCone(GL2 gl, double radius, double height,
                              int slices, int stacks, int rings, boolean makeTexCoords) {
        if (radius <= 0)
            throw new IllegalArgumentException("Radius must be positive.");
        if (height <= 0)
            throw new IllegalArgumentException("Height must be positive.");
        if (slices < 3)
            throw new IllegalArgumentException("Number of slices must be at least 3.");
        if (stacks < 2)
            throw new IllegalArgumentException("Number of stacks must be at least 2.");
        for (int j = 0; j < stacks; j++) {
            double z1 = (height / stacks) * j;
            double z2 = (height / stacks) * (j + 1);
            gl.glBegin(GL2.GL_QUAD_STRIP);
            for (int i = 0; i <= slices; i++) {
                double longitude = (2 * Math.PI / slices) * i;
                double sinLong = Math.sin(longitude);
                double cosLong = Math.cos(longitude);
                double x = cosLong;
                double y = sinLong;
                double nz = radius / height;
                double normLength = Math.sqrt(x * x + y * y + nz * nz);
                gl.glNormal3d(x / normLength, y / normLength, nz / normLength);
                if (makeTexCoords)
                    gl.glTexCoord2d(1.0 / slices * i, 1.0 / stacks * (j + 1));
                gl.glVertex3d((height - z2) / height * radius * x, (height - z2) / height * radius * y, z2);
                if (makeTexCoords)
                    gl.glTexCoord2d(1.0 / slices * i, 1.0 / stacks * j);
                gl.glVertex3d((height - z1) / height * radius * x, (height - z1) / height * radius * y, z1);
            }
            gl.glEnd();
        }
        if (rings > 0) {
            gl.glNormal3d(0, 0, -1);
            for (int j = 0; j < rings; j++) {
                double d1 = (1.0 / rings) * j;
                double d2 = (1.0 / rings) * (j + 1);
                gl.glBegin(GL2.GL_QUAD_STRIP);
                for (int i = 0; i <= slices; i++) {
                    double angle = (2 * Math.PI / slices) * i;
                    double sin = Math.sin(angle);
                    double cos = Math.cos(angle);
                    if (makeTexCoords)
                        gl.glTexCoord2d(0.5 * (1 + cos * d2), 0.5 * (1 + sin * d2));
                    gl.glVertex3d(radius * cos * d2, radius * sin * d2, 0);
                    if (makeTexCoords)
                        gl.glTexCoord2d(0.5 * (1 + cos * d1), 0.5 * (1 + sin * d1));
                    gl.glVertex3d(radius * cos * d1, radius * sin * d1, 0);
                }
                gl.glEnd();
            }
        }
    }

    public static void cube(GL2 gl, double side, boolean makeTexCoords) {
        gl.glPushMatrix();
        gl.glRotatef(-90, -1, 0, 0);  // This puts the textures in the orientation I want.
        gl.glPushMatrix();
        gl.glTranslated(0, 0, side / 2);
        square(gl, side, makeTexCoords);  // Each side of the cube is a transformed square.
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glRotatef(90, 0, 1, 0);
        gl.glTranslated(0, 0, side / 2);
        square(gl, side, makeTexCoords);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glRotatef(180, 0, 1, 0);
        gl.glTranslated(0, 0, side / 2);
        square(gl, side, makeTexCoords);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glRotatef(270, 0, 1, 0);
        gl.glTranslated(0, 0, side / 2);
        square(gl, side, makeTexCoords);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glRotatef(90, -1, 0, 0);
        gl.glTranslated(0, 0, side / 2);
        square(gl, side, makeTexCoords);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glRotatef(-90, -1, 0, 0);
        gl.glTranslated(0, 0, side / 2);
        square(gl, side, makeTexCoords);
        gl.glPopMatrix();
        gl.glPopMatrix();
    }


    public static void cuboid(GL2 gl, double width, double height, double depth, boolean makeTexCoords) {
        gl.glPushMatrix();
        gl.glTranslated(-width / 2, -height / 2, -depth / 2);

        gl.glPushMatrix();
        gl.glTranslated(0, 0, depth / 2);
        gl.glRotatef(180, 0, 1, 0);
        squareSide(gl, width, height, makeTexCoords);
        gl.glPopMatrix();

        //odna iz vetok
        gl.glPushMatrix();
        gl.glTranslated(-width / 2, 0, 0);
        gl.glRotatef(90, 0, 1, 0);
        squareSide(gl, depth, height, makeTexCoords);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(width / 2, 0, 0);
        gl.glRotatef(-90, 0, 1, 0);
        squareSide(gl, depth, height, makeTexCoords);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(0, 0, -depth / 2);
        squareSide(gl, width, height, makeTexCoords);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(0, -height / 2, 0);
        gl.glRotatef(90, 1, 0, 0);
        squareSide(gl, width, depth, makeTexCoords);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(0, height / 2, 0);
        gl.glRotatef(-90, 1, 0, 0);
        squareSide(gl, width, depth, makeTexCoords);
        gl.glPopMatrix();

        gl.glPopMatrix();
    }

    public static void squareSide(GL2 gl, double width, double height, boolean makeTexCoords) {
        double x1 = -width / 2;
        double x2 = width / 2;
        double y1 = -height / 2;
        double y2 = height / 2;

        if (makeTexCoords) {
            gl.glBegin(GL2.GL_POLYGON);
            gl.glNormal3d(0, 0, 1);
            gl.glTexCoord2d(0, 0);
            gl.glVertex3d(x1, y1, 0);
            gl.glTexCoord2d(1, 0);
            gl.glVertex3d(x2, y1, 0);
            gl.glTexCoord2d(1, 1);
            gl.glVertex3d(x2, y2, 0);
            gl.glTexCoord2d(0, 1);
            gl.glVertex3d(x1, y2, 0);
            gl.glEnd();
        } else {
            gl.glBegin(GL2.GL_POLYGON);
            gl.glNormal3d(0, 0, 1);
            gl.glVertex3d(x1, y1, 0);
            gl.glVertex3d(x2, y1, 0);
            gl.glVertex3d(x2, y2, 0);
            gl.glVertex3d(x1, y2, 0);
            gl.glEnd();
        }
    }


    public static void square(GL2 gl, double side, boolean makeTexCoords) {
        double radius = side / 2;
        gl.glBegin(GL2.GL_POLYGON);
        gl.glNormal3f(0, 0, 1);
        if (makeTexCoords)
            gl.glTexCoord2d(0, 1);
        gl.glVertex2d(-radius, radius);
        if (makeTexCoords)
            gl.glTexCoord2d(0, 0);
        gl.glVertex2d(-radius, -radius);
        if (makeTexCoords)
            gl.glTexCoord2d(1, 0);
        gl.glVertex2d(radius, -radius);
        if (makeTexCoords)
            gl.glTexCoord2d(1, 1);
        gl.glVertex2d(radius, radius);
        gl.glEnd();
    }
}