import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;
import com.sun.opengl.util.BufferUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.nio.IntBuffer;
import java.util.ArrayList;


public class Exam extends GLCanvas implements GLEventListener, KeyListener, MouseListener {

    private JCheckBox lightOnOff;
    private JCheckBox ambientLighting;
    private JCheckBox diffuseLighting;
    private JCheckBox specularLighting;
    private JCheckBox ambientLight;

    private JButton removeButton;
    private JButton addButton;
    private JButton finishButton;
    private JButton helpButton;
    private JButton quitButton;
    private JButton newGameButton;

    private JFrame frame;
    private JLabel label;

    private Camera camera;

    private TextRenderer textRenderer;
    private TextRenderer textMatch;

    // global variables for the random position of the shapes on the blueprint
    private int randomFront;
    private int randomBack;
    private int randomTop;
    private int randomTopTwo;

    private int randomTopThree;
    private int randomTopFour;
    private int randomTopFive;
    private int randomTopSix;
    private int randomTopSeven;
    private int randomTopEight;
    private int randomTopNine;
    private int randomRight;
    private int randomLeft;
    private int randomLeftTwo;
    private int randomBottom;
    private int randomBottomTwo;


    private int nameID = 0; // name ID for picking

    // id from the palette shape inserted into the blueprint
    private int left_idn = 0;
    private int left_two_idn = 0;
    private int right_idn = 0;
    private int top_idn = 0;
    private int top_two_idn = 0;
    private int top_three_idn = 0;
    private int top_four_idn = 0;
    private int top_five_idn = 0;
    private int top_six_idn = 0;
    private int top_seven_idn = 0;
    private int top_eight_idn = 0;
    private int top_nine_idn = 0;
    private int bottom_idn = 0;
    private int bottom_two_idn = 0;
    private int front_idn = 0;
    private int back_idn = 0;

    // color of the palette shape inserted into the blueprint - it will change when the user finished the game
    private float addShapeRed = 1f,
            addShapeGreen = 102 / 255f,
            addShapeBlue = 0f;
    private float cylinderRed = 0.5f;
    private float cylinderGreen = 0.5f;
    private float cylinderBlue = 0.5f;

    private float conusRed = 0.5f;
    private float conusGreen = 0.0f;
    private float conusBlue = 0.0f;

    private float wallRed = 0.3f;
    private float wallGreen = 0.3f;
    private float wallBlue = 0.3f;


    private float defaultRed = 0f;
    private float defaultGreen = 0.5f;
    private float defaultBlue = 0.5f;

    private float redL = defaultRed;
    private float greenL = defaultGreen;
    private float blueL = defaultBlue;

    private float redLeftTwo = defaultRed;
    private float greenLeftTwo = defaultGreen;
    private float blueLeftTwo = defaultBlue;

    private float redT = defaultRed;
    private float greenT = defaultGreen;
    private float blueT = defaultBlue;

    private float redTopTwo = defaultRed;
    private float greenTopTwo = defaultGreen;
    private float blueTopTwo = defaultBlue;

    private float redTopThree = defaultRed;
    private float greenTopThree = defaultGreen;
    private float blueTopThree = defaultBlue;

    private float redTopFour = defaultRed;
    private float greenTopFour = defaultGreen;
    private float blueTopFour = defaultBlue;

    private float redTopFive = defaultRed;
    private float greenTopFive = defaultGreen;
    private float blueTopFive = defaultBlue;

    private float redTopSix = defaultRed;
    private float greenTopSix = defaultGreen;
    private float blueTopSix = defaultBlue;

    private float redTopSeven = defaultRed;
    private float greenTopSeven = defaultGreen;
    private float blueTopSeven = defaultBlue;

    private float redTopEight = defaultRed;
    private float greenTopEight = defaultGreen;
    private float blueTopEight = defaultBlue;

    private float redTopNine = defaultRed;
    private float greenTopNine = defaultGreen;
    private float blueTopNine = defaultBlue;

    private float redB = defaultRed;
    private float greenB = defaultGreen;
    private float blueB = defaultBlue;

    private float redBottomTwo = defaultRed;
    private float greenBottomTwo = defaultGreen;
    private float blueBottomTwo = defaultBlue;

    private float redR = defaultRed;
    private float greenR = defaultGreen;
    private float blueR = defaultBlue;

    private float redF = defaultRed;
    private float greenF = defaultGreen;
    private float blueF = defaultBlue;

    private float redBack = defaultRed;
    private float greenBack = defaultGreen;
    private float blueBack = defaultBlue;

    // initial position of the shapes on the blueprint

    // LEFT
    private double leftX;
    private double leftY;
    private double leftZ;

    // LEFT
    private double leftTwoX;
    private double leftTwoY;
    private double leftTwoZ;

    // RIGHT
    private double rightX;
    private double rightY;
    private double rightZ;

    // TOP
    private double topX;
    private double topY;
    private double topZ;

    // TOP TWO
    private double topTwoX;
    private double topTwoY;
    private double topTwoZ;

    // TOP THREE
    private double topThreeX;
    private double topThreeY;
    private double topThreeZ;

    // TOP FOUR
    private double topFourX;
    private double topFourY;
    private double topFourZ;

    // TOP FIVE
    private double topFiveX;
    private double topFiveY;
    private double topFiveZ;

    // TOP SIX
    private double topSixX;
    private double topSixY;
    private double topSixZ;

    // TOP SEVEN
    private double topSevenX;
    private double topSevenY;
    private double topSevenZ;

    // TOP EIGHT
    private double topEightX;
    private double topEightY;
    private double topEightZ;

    // TOP NINE
    private double topNineX;
    private double topNineY;
    private double topNineZ;

    // BOTTOM
    private double bottomX;
    private double bottomY;
    private double bottomZ;

    // BOTTOM TWO
    private double bottomTwoX;
    private double bottomTwoY;
    private double bottomTwoZ;

    // FRONT
    private double frontX;
    private double frontY;
    private double frontZ;

    // BACK
    private double backX = 0;
    private double backY = 0;
    private double backZ = -1.5;

    // variable to travers through the blueprint
    private int travers = 0;

    // scaling increment/decrement
    private float scaleDelta = 0.1f;

    // initial scale of the inserted shapes into the blueprint
    private float scaleLeft = 0.5f;
    private float scaleLeftTwo = 0.5f;
    private float scaleRight = 0.5f;
    private float scaleTop = 0.5f;
    private float scaleTopTwo = 0.5f;
    private float scaleTopThree = 0.5f;
    private float scaleTopFour = 0.5f;
    private float scaleTopFive = 0.5f;
    private float scaleTopSix = 0.5f;
    private float scaleTopSeven = 0.5f;
    private float scaleTopEight = 0.5f;
    private float scaleTopNine = 0.5f;
    private float scaleBottom = 0.5f;
    private float scaleBottomTwo = 0.5f;
    private float scaleFront = 0.5f;
    private float scaleBack = 0.5f;

    // initial angle of the inserted shapes into the blueprint
    private int angleLeftY = 90;
    private int angleLeftX = 90;
    private int angleLeftZ = 90;

    private int angleLeftTwoY = 90;
    private int angleLeftTwoX = 90;
    private int angleLeftTwoZ = 90;

    private int angleRightX = 90;
    private int angleRightY = 90;
    private int angleRightZ = 90;

    private int angleTopX = 90;
    private int angleTopY = 90;
    private int angleTopZ = 90;

    private int angleTopTwoX = 90;
    private int angleTopTwoY = 90;
    private int angleTopTwoZ = 90;

    private int angleTopThreeX = 90;
    private int angleTopThreeY = 90;
    private int angleTopThreeZ = 90;

    private int angleTopFourX = 90;
    private int angleTopFourY = 90;
    private int angleTopFourZ = 90;

    private int angleTopFiveX = 90;
    private int angleTopFiveY = 90;
    private int angleTopFiveZ = 90;

    private int angleTopSixX = 90;
    private int angleTopSixY = 90;
    private int angleTopSixZ = 90;

    private int angleTopSevenX = 90;
    private int angleTopSevenY = 90;
    private int angleTopSevenZ = 90;

    private int angleTopEightX = 90;
    private int angleTopEightY = 90;
    private int angleTopEightZ = 90;

    private int angleTopNineX = 90;
    private int angleTopNineY = 90;
    private int angleTopNineZ = 90;

    private int angleBottomX = 90;
    private int angleBottomY = 90;
    private int angleBottomZ = 90;

    private int angleBottomTwoX = 90;
    private int angleBottomTwoY = 90;
    private int angleBottomTwoZ = 90;

    private int angleFrontX = 90;
    private int angleFrontY = 90;
    private int angleFrontZ = 90;

    private int angleBackX = 90;
    private int angleBackY = 90;
    private int angleBackZ = 90;

    // rotation increment/decrement
    private float rotate = 1;

    // colors of the shapes on the palette
    private float paletteRed = 0.78f;
    private float paletteGreen = 0.20f;
    private float paletteBlue = 0.92f;

    private static final int TOP_ID = 1;
    private static final int TOP_TWO_ID = 2;
    private static final int TOP_THREE_ID = 3;
    private static final int TOP_FOUR_ID = 4;
    private static final int TOP_FIVE_ID = 5;
    private static final int TOP_SIX_ID = 6;
    private static final int TOP_SEVEN_ID = 7;
    private static final int TOP_EIGHT_ID = 8;
    private static final int TOP_NINE_ID = 9;


    // blueprint shapes
    private final static int
            CONE = 1,
            CUBE_AZAT = 2,
            CYLINDER = 3;


    // palette shapes
    private final static int CONE_ID = 1;
    private final static int CUBE_AZAT_ID = 2;
    private final static int CYLINDER_ID = 3;

    private final static String[] shape = {" ", "CONE", "CUBE_AZAT", "CYLINDER"};

    private GLCanvas canvas;
    private FPSAnimator animator;
    private static final int FPS = 60;
    private int windowWidth = 640;
    private int windowHeight = 480;
    private final String TITLE = "Exam";
    private GLU glu;

    // size of buffer
    private static final int BUFSIZE = 512;
    private IntBuffer selectBuffer;

    private boolean inSelectionMode = false;
    private int xCursor, yCursor;

    private String[] textureFileNames = {
            "disney4.jpg",
            "wood3.png",
            "mickeyframe.png",
            "background.jpg"
    };
    private Texture[] textures = new Texture[textureFileNames.length];

    private int currentAngleOfRotationX = 0;
    private int currentAngleOfRotationY = 0;
    private int currentAngleOfVisibleField = 55;

    private int angleDelta = 5;
    private float aspect;
    private float aspectP;
    private boolean gameFinished = false;
    private boolean newGame = true;

    private float translateX;
    private float translateY;
    private float translateZ;

    private float scale;
    private float scaleLeftShape;
    private float scaleLeftTwoShape;
    private float scaleRightShape;
    private float scaleTopShape;
    private float scaleTopTwoShape;
    private float scaleTopThreeShape;
    private float scaleTopFourShape;
    private float scaleTopFiveShape;
    private float scaleTopSixShape;
    private float scaleTopSevenShape;
    private float scaleTopEightShape;
    private float scaleTopNineShape;
    private float scaleBottomShape;
    private float scaleBottomTwoShape;
    private float scaleFrontShape;
    private float scaleBackShape;

    public Exam() {

        GLProfile profile = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(profile);
        caps.setAlphaBits(8);
        caps.setDepthBits(24);
        caps.setDoubleBuffered(true);
        caps.setStencilBits(8);

        SwingUtilities.invokeLater(() -> {
            // Create the OpenGL rendering canvas
            canvas = new GLCanvas();
            canvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
            canvas.addGLEventListener(this);
            canvas.addKeyListener(this);
            canvas.addMouseListener(this);
            canvas.setFocusable(true);
            canvas.requestFocus();
            canvas.requestFocusInWindow();

            animator = new FPSAnimator(canvas, FPS, true);

            frame = new JFrame();

            removeButton = new JButton("Remove");
            addButton = new JButton("  Add ");
            finishButton = new JButton("Finish");
            quitButton = new JButton("Quit");
            helpButton = new JButton("Help");
            newGameButton = new JButton("New Game");

            addButton.setPreferredSize(new Dimension(100, 20));
            removeButton.setPreferredSize(new Dimension(100, 20));
            finishButton.setPreferredSize(new Dimension(100, 20));
            helpButton.setPreferredSize(new Dimension(100, 20));
            quitButton.setPreferredSize(new Dimension(100, 20));
            newGameButton.setPreferredSize(new Dimension(100, 20));

            label = new JLabel("Click on the Help button to read game instructions.");

            lightOnOff = new JCheckBox("Turn Light ON/OFF", true);
            ambientLighting = new JCheckBox("Ambient Light", false);
            specularLighting = new JCheckBox("Specular Light", false);
            diffuseLighting = new JCheckBox("Diffuse Light", false);
            ambientLight = new JCheckBox("Global Ambient Light", false);

            JPanel bottom = new JPanel();
            bottom.setLayout(new GridLayout(2, 2));

            JPanel row1 = new JPanel();
            row1.add(removeButton);
            row1.add(addButton);
            row1.add(ambientLight);
            row1.add(lightOnOff);
            row1.add(ambientLighting);
            row1.add(diffuseLighting);
            row1.add(specularLighting);
            bottom.add(row1);

            JPanel row2 = new JPanel();
            row2.add(label);
            row2.add(helpButton);
            row2.add(finishButton);
            row2.add(newGameButton);
            row2.add(quitButton);
            bottom.add(row2);

            frame.add(bottom, BorderLayout.SOUTH);

            ambientLight.setFocusable(false);
            lightOnOff.setFocusable(false);
            ambientLighting.setFocusable(false);
            diffuseLighting.setFocusable(false);
            specularLighting.setFocusable(false);

            addButton.addActionListener(e -> {
                if (e.getSource() == addButton) {
                    if (travers == 1) {
                        top_idn = nameID;
                    } else if (travers == 2) {
                        top_two_idn = nameID;
                    } else if (travers == 3) {
                        top_three_idn = nameID;
                    } else if (travers == 4) {
                        top_four_idn = nameID;
                    } else if (travers == 5) {
                        top_five_idn = nameID;
                    } else if (travers == 6) {
                        top_six_idn = nameID;
                    } else if (travers == 7) {
                        top_seven_idn = nameID;
                    } else if (travers == 8) {
                        top_eight_idn = nameID;
                    }  else if (travers == 9) {
                        top_nine_idn = nameID;
                    }
                }
                addButton.setFocusable(false);
            });

            removeButton.addActionListener(e -> {
                if (e.getSource() == removeButton) {

                    if (travers == 1) {
                        top_idn = 0;
                    } else if (travers == 2) {
                        top_two_idn = 0;
                    } else if (travers == 3) {
                        top_three_idn = 0;
                    } else if (travers == 4) {
                        top_four_idn = 0;
                    } else if (travers == 5) {
                        top_five_idn = 0;
                    } else if (travers == 6) {
                        top_six_idn = 0;
                    } else if (travers == 7) {
                        top_seven_idn = 0;
                    } else if (travers == 8) {
                        top_eight_idn = 0;
                    } else if (travers == 9) {
                        top_nine_idn = 0;
                    }
                }
                removeButton.setFocusable(false);
            });

            finishButton.addActionListener(e -> {
                if (e.getSource() == finishButton) {
                    gameFinished = true;
                    addShapeRed = 0;
                    addShapeGreen = 200 / 255f;
                    addShapeBlue = 1;
                    currentAngleOfVisibleField = 110;
                    translateY = -2;
                }
                finishButton.setFocusable(false);
            });

            helpButton.addActionListener(e -> {
                if (e.getSource() == helpButton) {

                    JOptionPane.showMessageDialog(frame, "Instructions: \n" +
                                    "W - traverse through the blueprint\n" +
                                    "A - reduce the scale of the shape inserted into the blueprint\n" +
                                    "S - increase the scale of the shape inserted into the blueprint\n" +
                                    "Z - increase the scale of the blueprint\n" +
                                    "X - reduce the scale of the blueprint\n" +
                                    "I - move the blueprint (translate) on the z-axis in positive direction\n" +
                                    "O - move the blueprint (translate) on the z-axis in negative direction\n" +
                                    "J - move the blueprint (translate) on the x-axis in positive direction \n" +
                                    "K - move the blueprint (translate) on the x-axis in negative direction\n" +
                                    "N - move the blueprint (translate) on the y-axis in positive direction\n" +
                                    "M - move the blueprint (translate) on the y-axis in negative direction\n" +


                                    "Add Button - after selecting a shape from the palette, you can add it to the selected blueprint shape by the Add button\n" +
                                    "Remove Button - after selecting a shape from the palette, you can remove it from the selected blueprint shape by the Remove button\n" +

                                    "Finish Button - after the game finished, by pressing on the finish button, you can see your results\n" +
                                    "New Game Button - generate a new game\n" +
                                    "Quit Button - quit from the game \n" +
                                    "Light - you can enable/disable different light models by checking/unchecking  the light chekboxes (global ambient light, ambient, diffuse and specular)\n" +


                                    "+ (Numerical Keypad 9)- zoom in\n" +
                                    "- (Numerical Keypad 9)- zoom out\n" +
                                    "Left arrow - negative rotation around the x-axis of the blueprint\n" +
                                    "Right arrow - positive rotation around the x-axis of the blueprint \n" +
                                    "Up arrow - negative rotation around the y-axis of the blueprint\n" +
                                    "Down arrow - positive rotation around the y-axis of the blueprint\n" +
                                    "1 (Numerical Keypad 1) - positive rotation around the x-axis of the shape inserted into the blueprint\n" +
                                    "3 (Numerical Keypad 3)- negative rotation around the x-axis of the shape inserted into the blueprint\n" +
                                    "4 (Numerical Keypad 4)- positive rotation around the y-axis of the shape inserted into the blueprint\n" +
                                    "6 (Numerical Keypad 6)- negative rotation around the y-axis of the shape inserted into the blueprint\n" +
                                    "7 (Numerical Keypad 7)- positive rotation around the z-axis of the shape inserted into the blueprint\n" +
                                    "9 (Numerical Keypad 9)- negative rotation around the y-axis of the shape inserted into the blueprint\n"
                            , "Help", JOptionPane.INFORMATION_MESSAGE);
                }
                helpButton.setFocusable(false);
            });

            quitButton.addActionListener(e -> {
                if (e.getSource() == quitButton) {
                    animator.stop();
                    System.exit(0);

                }
                quitButton.setFocusable(false);
            });

            newGameButton.addActionListener(e -> {
                if (e.getSource() == newGameButton) {
                    newGame = true;
                    gameFinished = false;
                }
                newGameButton.setFocusable(false);
            });

            frame.getContentPane().add(canvas);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    new Thread(() -> {
                        if (animator.isStarted()) animator.stop();
                        System.exit(0);
                    }).start();
                }
            });

            camera = new Camera();
            camera.lookAt(-5, 0, 3, 0, 0, 0, 0, 1, 0);
            camera.setScale(15);

            frame.setTitle(TITLE);
            frame.pack();
            frame.setVisible(true);
            animator.start(); // start the animation loop
        });


    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context

        gl.glClearColor(0.95f, 0.95f, 1f, 0);


        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_NORMALIZE);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);

        gl.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, 1);
        gl.glMateriali(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 100);


        float ambient[] = {0.1f, 0.1f, 0.1f, 1};
        float[] diffuse = {1.0f, 1.0f, 1.0f, 1.0f};
        float[] specular = {1.0f, 1.0f, 1.0f, 1.0f};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_SPECULAR, specular, 0);

        gl.glClearDepth(1.0f);      // set clear depth value to farthest
        gl.glEnable(GL2.GL_DEPTH_TEST); // enables depth testing
        gl.glDepthFunc(GL2.GL_LEQUAL);  // the type of depth test to do
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
        gl.glShadeModel(GL2.GL_SMOOTH); // blends colors nicely, and smoothes out lighting
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        glu = GLU.createGLU(gl); // get GL Utilities

        for (int i = 0; i < textureFileNames.length; i++) {
            try {
                URL textureURL;
                textureURL = getClass().getClassLoader().getResource("textures/" + textureFileNames[i]);
                if (textureURL != null) {
                    BufferedImage img = ImageIO.read(textureURL);
                    ImageUtil.flipImageVertically(img);
                    textures[i] = AWTTextureIO.newTexture(GLProfile.getDefault(), img, true);
                    textures[i].setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
                    textures[i].setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        textures[0].enable(gl);

        textRenderer = new TextRenderer(new Font("SansSerif", Font.PLAIN, 12));
        textMatch = new TextRenderer(new Font("SansSerif", Font.BOLD, 20));
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);


        if (inSelectionMode) {
            pickModels(drawable);
        } else { // normal rendering
            palette(drawable);
            drawBlueprint(drawable);
            drawBackground(drawable);
        }

        camera.apply(gl);
        lights(gl);

        float zero[] = {0, 0, 0, 1};

        if (ambientLight.isSelected()) {
            gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, new float[]{0.1F, 0.1F, 0.1F, 1}, 0);
        } else {
            gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, zero, 0);
        }

        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, new float[]{0.1F, 0.1F, 0.1F, 1}, 0);

        if (gameFinished) {

            printResult(drawable);
        }

        if (!gameFinished) {

            printMatch(drawable);
        }


        if (newGame) {
            newGame();
            newGame = false;
        }
    }

    private void drawBackground(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glViewport(0, 0, windowWidth, windowHeight);

        aspect = (float) windowHeight / ((float) windowWidth);

        gl.glOrtho((float) -10 / 2, (float) 10 / 2,
                (-10 * aspect) / 2,
                (10 * aspect) / 2, 0, 100);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);

        gl.glPushMatrix();
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
        gl.glGenerateMipmap(GL.GL_TEXTURE_2D);
        textures[3].bind(gl);  // Says which texture to use.
        gl.glTranslated(0, 0, -100);
        gl.glScalef(1.75f, 1, 1);
        gl.glColor3f(1f, 1f, 1f);

        double radius = 3.25;
        gl.glBegin(GL2.GL_POLYGON);
        gl.glNormal3f(0, 0, 1);

        gl.glTexCoord2d(0, 1);
        gl.glVertex2d(-radius, radius);
        gl.glTexCoord2d(0, 0);
        gl.glVertex2d(-radius, -radius);
        gl.glTexCoord2d(1, 0);
        gl.glVertex2d(radius, -radius);
        gl.glTexCoord2d(1, 1);
        gl.glVertex2d(radius, radius);
        gl.glEnd();

        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glEnd();
        gl.glPopMatrix();

    }

    public void newGame() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            list.add(i);
        }

 //       Collections.shuffle(list);


        randomTop = list.get(0);
    //    randomTopTwo = list.get(3);
        randomTopTwo = list.get(1);
        randomTopThree = list.get(2);
        randomTopFour = list.get(2);
        randomTopFive = list.get(0);
        randomTopSix = list.get(2);
        randomTopSeven = list.get(0);
        randomTopEight = list.get(1);
        randomTopNine = list.get(1);


        // default values

        currentAngleOfRotationX = 0;
        currentAngleOfRotationY = 0;
        currentAngleOfVisibleField = 55;

        translateX = 0;
        translateY = 0;
        translateZ = 0;

        scale = 1;

        nameID = 0;
        left_idn = 0;
        left_two_idn = 0;
        right_idn = 0;
        top_idn = 0;
        top_three_idn = 0;
        top_two_idn = 0;
        top_four_idn = 0;
        top_five_idn = 0;
        top_six_idn = 0;
        top_seven_idn = 0;
        top_eight_idn = 0;
        top_nine_idn = 0;
        bottom_idn = 0;
        bottom_two_idn = 0;
        front_idn = 0;
        back_idn = 0;
        addShapeRed = 1f;
        addShapeGreen = 102 / 255f;
        addShapeBlue = 0f;

        redL = defaultRed;
        greenL = defaultGreen;
        blueL = defaultBlue;

        redLeftTwo = defaultRed;
        greenLeftTwo = defaultGreen;
        blueLeftTwo = defaultBlue;

        redT = defaultRed;
        greenT = defaultGreen;
        blueT = defaultBlue;

        redTopTwo = defaultRed;
        greenTopTwo = defaultGreen;
        blueTopTwo = defaultBlue;

        redTopThree = defaultRed;
        greenTopThree = defaultGreen;
        blueTopThree = defaultBlue;

        redTopFour = defaultRed;
        greenTopFour = defaultGreen;
        blueTopFour = defaultBlue;

        redTopFive = defaultRed;
        greenTopFive = defaultGreen;
        blueTopFive = defaultBlue;

        redTopSix = defaultRed;
        greenTopSix = defaultGreen;
        blueTopSix = defaultBlue;

        redTopSeven = defaultRed;
        greenTopSeven = defaultGreen;
        blueTopSeven = defaultBlue;

        redTopEight = defaultRed;
        greenTopEight = defaultGreen;
        blueTopEight = defaultBlue;

        redTopNine = defaultRed;
        greenTopNine = defaultGreen;
        blueTopNine = defaultBlue;

        redB = defaultRed;
        greenB = defaultGreen;
        blueB = defaultBlue;

        redBottomTwo = defaultRed;
        greenBottomTwo = defaultGreen;
        blueBottomTwo = defaultBlue;

        redR = defaultRed;
        greenR = defaultGreen;
        blueR = defaultBlue;

        redF = defaultRed;
        greenF = defaultGreen;
        blueF = defaultBlue;

        redBack = defaultRed;
        greenBack = defaultGreen;
        blueBack = defaultBlue;

        // LEFT
        leftX = -1.25;
        leftY = 0.5;
        leftZ = 0;

        scaleLeftShape = 0.5f;

        // LEFT TWO
        leftTwoX = -1.25;
        leftTwoY = -0.5;
        leftTwoZ = 0;

        scaleLeftTwoShape = 0.5f;

        // RIGHT
        rightX = 1.4;
        rightY = 0;
        rightZ = 0.5;

        scaleRightShape = 0.75f;

        // TOP
        topX = -1.3;
        topY = 2;
        topZ = 0.5;

        scaleTopShape = 1;

        // TOP TWO
        topTwoX = 0.4;
        topTwoY = 1.3;
        topTwoZ = -1.2;

        scaleTopTwoShape = 1;

        // TOP THREE
        topThreeX = -1.3;
        topThreeY = 0.8;
        topThreeZ = 0.5;

        scaleTopThreeShape = 1;


        // TOP FOUR
        topFourX = 0.5;
        topFourY = 0.8;
        topFourZ = 1;

        scaleTopFourShape = 1;

        // TOP FIVE
        topFiveX = 0.5;
        topFiveY = 2;
        topFiveZ = 1;

        scaleTopFiveShape = 1;

        // TOP SIX
        topSixX = 0.5;
        topSixY = 0.8;
        topSixZ = -1.6;

        scaleTopSixShape = 1;

        // TOP SEVEN
        topSevenX = 0.5;
        topSevenY = 2;
        topSevenZ = -1.6;

        scaleTopSevenShape = 1;

        // TOP EIGHT
        topEightX = 0.8;
        topEightY = 1.3;
        topEightZ = -1.3;

        scaleTopEightShape = 1;

        // TOP NINE
        topNineX = -1.5;
        topNineY = 1.3;
        topNineZ = 0.5;

        scaleTopNineShape = 1;

        // BOTTOM
        bottomX = 1;
        bottomY = -1.63;
        bottomZ = 0.5f;

        scaleBottomShape = 1.25f;

        // BOTTOM TWO
        bottomTwoX = -1;
        bottomTwoY = -1.63;
        bottomTwoZ = -0.5f;

        scaleBottomTwoShape = 1.25f;

        // FRONT
        frontX = -0.25;
        frontY = 0;
        frontZ = 1.75;

        scaleFrontShape = 1.5f;

        // BACK
        backX = 0;
        backY = 0;
        backZ = -1.9;

        scaleBackShape = 1.75f;

        travers = 0;

        scaleDelta = 0.05f;
        scaleLeft = 0.5f;
        scaleLeftTwo = 0.5f;
        scaleRight = 0.5f;
        scaleTop = 0.5f;
        scaleTopTwo = 0.5f;
        scaleTopThree = 0.5f;
        scaleTopFour = 0.5f;
        scaleTopFive = 0.5f;
        scaleTopSix = 0.5f;
        scaleTopSeven = 0.5f;
        scaleTopEight = 0.5f;
        scaleTopNine = 0.5f;
        scaleBottom = 0.5f;
        scaleBottomTwo = 0.5f;
        scaleFront = 0.5f;
        scaleBack = 0.5f;


        angleLeftY = 90;
        angleLeftX = 90;
        angleLeftZ = 90;

        angleLeftTwoY = 90;
        angleLeftTwoX = 90;
        angleLeftTwoZ = 90;

        angleRightX = 90;
        angleRightY = 90;
        angleRightZ = 90;

        angleTopX = 90;
        angleTopY = 90;
        angleTopZ = 90;

        angleTopTwoX = 90;
        angleTopTwoY = 90;
        angleTopTwoZ = 90;

        angleTopThreeX = 90;
        angleTopThreeY = 90;
        angleTopThreeZ = 90;

        angleTopFourX = 90;
        angleTopFourY = 90;
        angleTopFourZ = 90;

        angleTopFiveX = 90;
        angleTopFiveY = 90;
        angleTopFiveZ = 90;

        angleTopSixX = 90;
        angleTopSixY = 90;
        angleTopSixZ = 90;

        angleTopSevenX = 90;
        angleTopSevenY = 90;
        angleTopSevenZ = 90;

        angleTopEightX = 90;
        angleTopEightY = 90;
        angleTopEightZ = 90;

        angleTopNineX = 90;
        angleTopNineY = 90;
        angleTopNineZ = 90;

        angleBottomX = 90;
        angleBottomY = 90;
        angleBottomZ = 90;

        angleFrontX = 90;
        angleFrontY = 90;
        angleFrontZ = 90;

        angleBackX = 90;
        angleBackY = 90;
        angleBackZ = 90;
    }

    private void startPicking(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        selectBuffer = BufferUtil.newIntBuffer(BUFSIZE);
        gl.glSelectBuffer(BUFSIZE, selectBuffer);
        gl.glRenderMode(GL2.GL_SELECT); // switch to selection mode
        gl.glInitNames(); // make an empty name stack
        gl.glMatrixMode(GL2.GL_MODELVIEW); // restore model view
    } // end of startPicking()

    public void palettePicking(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        int[] viewport = new int[4];
        float[] projMatrix = new float[16];
        gl.glGetIntegerv(GL2.GL_VIEWPORT, viewport, 0);
        viewport[0] = 0;
        viewport[1] = 0;
        viewport[2] = windowWidth / 3;
        viewport[3] = windowHeight;
        gl.glGetFloatv(GL2.GL_PROJECTION_MATRIX, projMatrix, 0);
        glu.gluPickMatrix((double) xCursor, (double) (viewport[3] - yCursor),
                1.0, 1.0, viewport, 0);

        gl.glMultMatrixf(projMatrix, 0); // following code from "OpenGL Distilled"
        gl.glOrtho((float) -10 / 2, (float) 10 / 2,
                (-10 * aspectP) / 2,
                (10 * aspectP) / 2, 1, 11);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(-1, 2, 5.0,
                0.0, 0.0, 0.0,
                0.0, 1.0, 0.0);

    }

    public void blueprintPicking(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();

        int[] viewport = new int[4];
        viewport[0] = windowWidth / 8;
        viewport[1] = 0;
        viewport[2] = windowWidth;
        viewport[3] = windowHeight;
        float[] projMatrix = new float[16];
        gl.glGetIntegerv(GL2.GL_VIEWPORT, viewport, 0);
        gl.glGetFloatv(GL2.GL_PROJECTION_MATRIX, projMatrix, 0);
        // System.out.println(viewport[3]);
        glu.gluPickMatrix((double) xCursor, (double) (viewport[3] - yCursor), 1.0, 1.0, viewport, 0);
        // System.out.println(viewport[3] - yCursor);
        gl.glMultMatrixf(projMatrix, 0); // following code from "OpenGL Distilled"
        glu.gluPerspective(currentAngleOfVisibleField,
                1.f * windowWidth / windowHeight, 1, 100);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    private void endPicking(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        // restore original projection matrix
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glFlush();
        // return to normal rendering mode, and process hits
        int numHits = gl.glRenderMode(GL2.GL_RENDER);
        processHits(numHits, drawable);
        inSelectionMode = false;
    } // end of endPicking()

    public void processHits(int numHits, GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        if (numHits == 0)
            return; // no hits to process

        // storage for the name ID closest to the viewport
        int selectedNameID = 0; // dummy initial values
        float smallestZ = -1.0f;
        boolean isFirstLoop = true;
        int offset = 0;
        /* iterate through the hit records, saving the smallest z value and the name ID associated with it */


        for (int i = 0; i < numHits; i++) {
            int numNames = selectBuffer.get(offset);
            offset++;
            // minZ and maxZ are taken from the Z buffer
            float minZ = getDepth(offset);
            offset++;

            // store the smallest z value
            if (isFirstLoop) {
                smallestZ = minZ;
                isFirstLoop = false;
            } else {
                if (minZ < smallestZ)
                    smallestZ = minZ;
            }
            float maxZ = getDepth(offset);
            offset++;
            //TODO: Here we select the ID of the shape.
            for (int j = 0; j < numNames; j++) {
                nameID = selectBuffer.get(offset);
                System.out.print(idToString(nameID) + "\n");


                if (j == (numNames - 1)) {
// if the last one (the top element on the stack)
                    if (smallestZ == minZ) // is this the smallest min z?
                        selectedNameID = nameID; // then store it's name ID
                }
                offset++;
            }
        }
    } // end of processHits()

    public void addShape(GLAutoDrawable drawable, int nameID) {
        GL2 gl = drawable.getGL().getGL2();
        switch (nameID) {
            case CONE_ID:
                Shapes.uvCone(gl);
                break;
            case CUBE_AZAT_ID:
                Shapes.cubeAzat(gl);
                break;
            case CYLINDER_ID:
                Shapes.uvCylinder(gl);
                break;
        }
    }

    //TODO: The rendering of the filler.
    //TODO: conus 1
    private void addTopShape(GLAutoDrawable drawable, int nameID) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(conusRed, conusGreen, conusBlue);
        gl.glTranslated(topX, topY, topZ);
        gl.glScalef(scaleTop, scaleTop, scaleTop);
        gl.glRotatef(angleTopZ, 0, 0, rotate);
        gl.glRotatef(angleTopY, 0, rotate, 0);
        gl.glRotatef(angleTopX, rotate, 0, 0);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        addShape(drawable, nameID);
        gl.glPopMatrix();
    }
    //TODO: wall 1
    private void addTopTwoShape(GLAutoDrawable drawable, int nameID) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(wallRed, wallGreen, wallBlue);
        gl.glTranslated(topTwoX, topTwoY, topTwoZ);
        gl.glScalef(scaleTopTwo, scaleTopTwo, scaleTopTwo);
        gl.glRotatef(angleTopTwoZ, 0, 0, rotate);
        gl.glRotatef(angleTopTwoY, 0, rotate, 0);
        gl.glRotatef(angleTopTwoX, rotate, 0, 0);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        addShape(drawable, nameID);
        gl.glPopMatrix();
    }
    //TODO: cylinder 1
    private void addTopThreeShape(GLAutoDrawable drawable, int nameID) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(cylinderRed, cylinderGreen, cylinderBlue);
        gl.glTranslated(topThreeX, topThreeY, topThreeZ);
        gl.glScalef(scaleTopThree, scaleTopThree, scaleTopThree);
        gl.glRotatef(angleTopThreeZ, 0, 0, rotate);
        gl.glRotatef(angleTopThreeY, 0, rotate, 0);
        gl.glRotatef(angleTopThreeX, rotate, 0, 0);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        addShape(drawable, nameID);
        gl.glPopMatrix();
    }
    //TODO: cylinder 2
    private void addTopFourShape(GLAutoDrawable drawable, int nameID) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(cylinderRed, cylinderGreen, cylinderBlue);
        gl.glTranslated(topFourX, topFourY, topFourZ);
        gl.glScalef(scaleTopFour, scaleTopFour, scaleTopFour);
        gl.glRotatef(angleTopFourZ, 0, 0, rotate);
        gl.glRotatef(angleTopFourY, 0, rotate, 0);
        gl.glRotatef(angleTopFourX, rotate, 0, 0);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        addShape(drawable, nameID);
        gl.glPopMatrix();
    }
    //TODO: conus 2
    private void addTopFiveShape(GLAutoDrawable drawable, int nameID) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(conusRed, conusGreen, conusBlue);
        gl.glTranslated(topFiveX, topFiveY, topFiveZ);
        gl.glScalef(scaleTopFive, scaleTopFive, scaleTopFive);
        gl.glRotatef(angleTopFiveZ, 0, 0, rotate);
        gl.glRotatef(angleTopFiveY, 0, rotate, 0);
        gl.glRotatef(angleTopFiveX, rotate, 0, 0);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        addShape(drawable, nameID);
        gl.glPopMatrix();
    }
    //TODO: cylinder 3
    private void addTopSixShape(GLAutoDrawable drawable, int nameID) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(cylinderRed, cylinderGreen, cylinderBlue);
        gl.glTranslated(topSixX, topSixY, topSixZ);
        gl.glScalef(scaleTopSix, scaleTopSix, scaleTopSix);
        gl.glRotatef(angleTopSixZ, 0, 0, rotate);
        gl.glRotatef(angleTopSixY, 0, rotate, 0);
        gl.glRotatef(angleTopSixX, rotate, 0, 0);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        addShape(drawable, nameID);
        gl.glPopMatrix();
    }
    //TODO: conus 3
    private void addTopSevenShape(GLAutoDrawable drawable, int nameID) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(conusRed, conusGreen, conusBlue);
        gl.glTranslated(topSevenX, topSevenY, topSevenZ);
        gl.glScalef(scaleTopSeven, scaleTopSeven, scaleTopSeven);
        gl.glRotatef(angleTopSevenZ, 0, 0, rotate);
        gl.glRotatef(angleTopSevenY, 0, rotate, 0);
        gl.glRotatef(angleTopSevenX, rotate, 0, 0);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        addShape(drawable, nameID);
        gl.glPopMatrix();
    }
    //TODO: wall 2
    private void addTopEightShape(GLAutoDrawable drawable, int nameID) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(wallRed, wallGreen, wallBlue);
        gl.glTranslated(topEightX, topEightY, topEightZ);
        gl.glScalef(scaleTopEight, scaleTopEight, scaleTopEight);
        gl.glRotatef(angleTopEightZ, 0, 0, rotate);
        gl.glRotatef(angleTopEightY, 0, rotate, 0);
        gl.glRotatef(angleTopEightX, rotate, 0, 0);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        addShape(drawable, nameID);
        gl.glPopMatrix();
    }
    //TODO: wall 3
    private void addTopNineShape(GLAutoDrawable drawable, int nameID) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(wallRed, wallGreen, wallBlue);
        gl.glTranslated(topNineX, topNineY, topNineZ);
        gl.glScalef(scaleTopNine, scaleTopNine, scaleTopNine);
        gl.glRotatef(angleTopNineZ, 0, 0, rotate);
        gl.glRotatef(angleTopNineY, 0, rotate, 0);
        gl.glRotatef(angleTopNineX, rotate, 0, 0);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        addShape(drawable, nameID);
        gl.glPopMatrix();
    }


    // The buffer is examined in processHits().

    private float getDepth(int offset) {
        long depth = (long) selectBuffer.get(offset); // large -ve number
        return (1.0f + ((float) depth / 0x7fffffff));
        // return as a float between 0 and 1
    } // end of getDepth()

    private String idToString(int nameID) {
        if (nameID == TOP_ID)
            return "top";
        else if (nameID == TOP_TWO_ID)
            return "top_two";
        else if (nameID == TOP_THREE_ID) {
            return "top_three";
        } else if (nameID == TOP_FOUR_ID) {
            return "top_four";
        } else if (nameID == TOP_FIVE_ID) {
            return "top_five";
        } else if (nameID == TOP_SIX_ID) {
            return "top_six";
        } else if (nameID == TOP_SEVEN_ID) {
            return "top_seven";
        } else if (nameID == TOP_EIGHT_ID) {
            return "top_eight";
        } else if (nameID == TOP_NINE_ID) {
            return "top_nine";
        } else if (nameID == CUBE_AZAT_ID)
            return "azat_cube";
        else if (nameID == CONE_ID)
            return "palette_cone";
        else if (nameID == CYLINDER_ID)
            return "cylinder";
        // we should not reach this point
        return "nameID " + nameID;
    } // end of idToString()

    //TODO: Сhoosing model from palette
    private void pickModels(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        startPicking(drawable);
        palettePicking(drawable);


        gl.glPushName(CONE_ID);
        paletteCone(drawable);
        gl.glPopName();

        gl.glPushName(CUBE_AZAT_ID);
        paletteCubeAzat(drawable);
        gl.glPopName();

        gl.glPushName(CYLINDER_ID);
        paletteCylinder(drawable);
        gl.glPopName();

        gl.glPushMatrix();
        blueprintPicking(drawable);


        gl.glRotated(currentAngleOfRotationX, 1, 0, 0);
        gl.glRotated(currentAngleOfRotationY, 0, 1, 0);

        gl.glPushName(TOP_ID);
        drawTop(drawable);
        gl.glPopName();

        gl.glPushName(TOP_TWO_ID);
        drawTopTwo(drawable);
        gl.glPopName();

        gl.glPushName(TOP_THREE_ID);
        drawTopThree(drawable);
        gl.glPopName();

        gl.glPopMatrix();
        gl.glPopMatrix();
        endPicking(drawable);
    } // end of pickModels()


    private void setObserver() {

        glu.gluLookAt(-5, 0f, 3f,
                0, 0, 0,
                0, 1, 0);
    }
    //TODO: The rendering of the shape selection palette on the bottom left.
    private void palette(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glViewport(0, 0, windowWidth / 3, windowHeight);

        aspectP = (float) windowHeight / ((float) windowWidth / 3);

        gl.glOrtho((float) -10 / 2, (float) 10 / 2,
                (-10 * aspectP) / 2,
                (10 * aspectP) / 2, 1, 11);

        gl.glMatrixMode(GL2.GL_MODELVIEW);

        paletteBackground(drawable);
        gl.glLoadIdentity();

        glu.gluLookAt(-1, 2, 5.0,
                0.0, 0.0, 0.0,
                0.0, 1.0, 0.0);


        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);

        paletteCone(drawable);
        paletteCubeAzat(drawable);
        paletteCylinder(drawable);
    }
    public void paletteCubeAzat(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glColor3f(paletteRed, paletteGreen, paletteBlue);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
        gl.glPushMatrix();
        gl.glTranslated(-3.5f, 2.5f, 0);
        gl.glRotatef(60, 0, 1, 0);
        Shapes.cubeAzat(gl);
        gl.glPopMatrix();
    }

    private void paletteBackground(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
        gl.glGenerateMipmap(GL.GL_TEXTURE_2D);
        textures[2].bind(gl);  // Says which texture to use.
        gl.glTranslated(-1.5f, -1f, -7);
        gl.glScalef(3.5f, 5, 0);
        gl.glColor3f(1f, 1f, 1f);
        Shapes.square(gl, 2, true);

        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glEnd();
        gl.glPopMatrix();
    }


    private void paletteCone(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glColor3f(paletteRed, paletteGreen, paletteBlue);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        gl.glPushMatrix();
        gl.glTranslated(-1.25f, 2.5f, 0);
        gl.glRotatef(-90, 1, 0, 0);
        gl.glScalef(1.25f, 1.25f, 1.25f);
        Shapes.uvCone(gl);
        gl.glPopMatrix();
    }

    private void paletteCylinder(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glColor3f(paletteRed, paletteGreen, paletteBlue);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        gl.glPushMatrix();
        gl.glTranslated(0.5f, 2.25f, 0);
        gl.glRotatef(-90, 1, 0, 0);
        gl.glScalef(1.25f, 1.25f, 1.25f);
        Shapes.uvCylinder(gl);
        gl.glPopMatrix();
    }
    //TODO: The rendering of the actual shape in the left palette.
    //TODO: The rendering of all shapes and fillers.
    private void drawBlueprint(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(windowWidth / 8, 0, windowWidth, windowHeight);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(currentAngleOfVisibleField, 1.f * windowWidth / windowHeight, 1, 100);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        setObserver();

        gl.glPushMatrix();
        gl.glTranslated(translateX, translateY, translateZ);
        gl.glScalef(scale, scale, scale);
        gl.glRotated(currentAngleOfRotationX, 1, 0, 0);
        gl.glRotated(currentAngleOfRotationY, 0, 1, 0);

        gl.glColor3f(0.5f, 1.0f, 0.7f);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);

        Shapes.platform(gl);
        gl.glDisable(GL.GL_TEXTURE_2D);

        drawTop(drawable);
        drawTopTwo(drawable);
        drawTopThree(drawable);
        drawTopFour(drawable);
        drawTopFive(drawable);
        drawTopSix(drawable);
        drawTopSeven(drawable);
        drawTopEight(drawable);
        drawTopNine(drawable);
        //TODO: The filler receives an ID when a shape is added.
        addTopShape(drawable, top_idn);
        addTopTwoShape(drawable, top_two_idn);
        addTopThreeShape(drawable, top_three_idn);
        addTopFourShape(drawable, top_four_idn);
        addTopFiveShape(drawable, top_five_idn);
        addTopSixShape(drawable, top_six_idn);
        addTopSevenShape(drawable, top_seven_idn);
        addTopEightShape(drawable, top_eight_idn);
        addTopNineShape(drawable, top_nine_idn);
        System.out.println("top nine idn "+top_nine_idn);

        gl.glPopMatrix();
    }

    private void drawShape(GLAutoDrawable drawable, int randomShape) {
        GL2 gl = drawable.getGL().getGL2();

        switch (randomShape) {
            case CONE:
                Shapes.uvCone(gl);
                break;
            case CUBE_AZAT:
                Shapes.cubeAzat(gl);
                break;
            case CYLINDER:
                Shapes.uvCylinder(gl);
                break;
        }
    }

    //TODO: The rendering of the shape
    private void drawTop(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(redT, greenT, blueT);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        gl.glLineWidth(2);

        gl.glTranslated(topX, topY, topZ);
        gl.glScalef(scaleTopShape, scaleTopShape, scaleTopShape);

        gl.glRotatef(-90, 1, 0, 0);

        drawShape(drawable, randomTop);
        gl.glPopMatrix();
    }

    private void drawTopTwo(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(redTopTwo, greenTopTwo, blueTopTwo);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        gl.glLineWidth(2);

        gl.glTranslated(topTwoX, topTwoY, topTwoZ);
        gl.glScalef(scaleTopTwoShape, scaleTopTwoShape, scaleTopTwoShape);

        gl.glRotatef(45, 0, 1, 0);

        drawShape(drawable, randomTopTwo);
        gl.glPopMatrix();
    }

    private void drawTopThree(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(redTopThree, greenTopThree, blueTopThree);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        gl.glLineWidth(2);

        gl.glTranslated(topThreeX, topThreeY, topThreeZ);
        gl.glScalef(scaleTopThreeShape, scaleTopThreeShape, scaleTopThreeShape);

        gl.glRotatef(-90, 1, 0, 0);

        drawShape(drawable, randomTopThree);
        gl.glPopMatrix();
    }

    private void drawTopFour(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(redTopFour, greenTopFour, blueTopFour);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        gl.glLineWidth(2);

        gl.glTranslated(topFourX, topFourY, topFourZ);
        gl.glScalef(scaleTopFourShape, scaleTopFourShape, scaleTopFourShape);

        gl.glRotatef(-90, 1, 0, 0);

        drawShape(drawable, randomTopFour);
        gl.glPopMatrix();
    }

    private void drawTopFive(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(redTopFive, greenTopFive, blueTopFive);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        gl.glLineWidth(2);

        gl.glTranslated(topFiveX, topFiveY, topFiveZ);
        gl.glScalef(scaleTopFiveShape, scaleTopFiveShape, scaleTopFiveShape);

        gl.glRotatef(-90, 1, 0, 0);

        drawShape(drawable, randomTopFive);
        gl.glPopMatrix();
    }

    private void drawTopSix(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(redTopSix, greenTopSix, blueTopSix);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        gl.glLineWidth(2);

        gl.glTranslated(topSixX, topSixY, topSixZ);
        gl.glScalef(scaleTopSixShape, scaleTopSixShape, scaleTopSixShape);

        gl.glRotatef(-90, 1, 0, 0);

        drawShape(drawable, randomTopSix);
        gl.glPopMatrix();
    }

    private void drawTopSeven(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(redTopSeven, greenTopSeven, blueTopSeven);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        gl.glLineWidth(2);

        gl.glTranslated(topSevenX, topSevenY, topSevenZ);
        gl.glScalef(scaleTopSevenShape, scaleTopSevenShape, scaleTopSevenShape);

        gl.glRotatef(-90, 1, 0, 0);

        drawShape(drawable, randomTopSeven);
        gl.glPopMatrix();
    }

    private void drawTopEight(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(redTopEight, greenTopEight, blueTopEight);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        gl.glLineWidth(2);

        gl.glTranslated(topEightX, topEightY, topEightZ);
        gl.glScalef(scaleTopEightShape, scaleTopEightShape, scaleTopEightShape);

        gl.glRotatef(90, 0, 1, 0);

        drawShape(drawable, randomTopEight);
        gl.glPopMatrix();
    }

    private void drawTopNine(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(redTopNine, greenTopNine, blueTopNine);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        gl.glLineWidth(2);

        gl.glTranslated(topNineX, topNineY, topNineZ);
        gl.glScalef(scaleTopNineShape, scaleTopNineShape, scaleTopNineShape);

        gl.glRotatef(160, 0, 1, 0);

        drawShape(drawable, randomTopNine);
        gl.glPopMatrix();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        windowWidth = width;
        windowHeight = height;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    //TODO: From which point do we start the movement
    public void colorShape(int travers) {
        switch (travers) {
            case TOP_ID:
                redT = 1;
                redTopTwo = 0;
                redTopThree = 0;
                redTopFour = 0;
                redTopFive = 0;
                redTopSix = 0;
                redTopSeven = 0;
                redTopEight = 0;
                redTopNine = 0;
                redB = 0;
                redBottomTwo = 0;
                redR = 0;
                redF = 0;
                redBack = 0;
                break;

            case TOP_TWO_ID:
                redT = 0;
                redTopTwo = 1;
                redTopThree = 0;
                redTopFour = 0;
                redTopFive = 0;
                redTopSix = 0;
                redTopSeven = 0;
                redTopEight = 0;
                redTopNine = 0;
                redB = 0;
                redBottomTwo = 0;
                redR = 0;
                redF = 0;
                redBack = 0;
                break;
            case TOP_THREE_ID:
                redT = 0;
                redTopTwo = 0;
                redTopThree = 1;
                redTopFour = 0;
                redTopFive = 0;
                redTopSix = 0;
                redTopSeven = 0;
                redTopEight = 0;
                redTopNine = 0;
                redB = 0;
                redBottomTwo = 0;
                redR = 0;
                redF = 0;
                redBack = 0;
                break;
            case TOP_FOUR_ID:
                redT = 0;
                redTopTwo = 0;
                redTopThree = 0;
                redTopFour = 1;
                redTopFive = 0;
                redTopSix = 0;
                redTopSeven = 0;
                redTopEight = 0;
                redTopNine = 0;
                redB = 0;
                redBottomTwo = 0;
                redR = 0;
                redF = 0;
                redBack = 0;
                break;
            case TOP_FIVE_ID:
                redT = 0;
                redTopTwo = 0;
                redTopThree = 0;
                redTopFour = 0;
                redTopFive = 1;
                redTopSix = 0;
                redTopSeven = 0;
                redTopEight = 0;
                redTopNine = 0;
                redB = 0;
                redBottomTwo = 0;
                redR = 0;
                redF = 0;
                redBack = 0;
                break;
            case TOP_SIX_ID:
                redT = 0;
                redTopTwo = 0;
                redTopThree = 0;
                redTopFour = 0;
                redTopFive = 0;
                redTopSix = 1;
                redTopSeven = 0;
                redTopEight = 0;
                redTopNine = 0;
                redB = 0;
                redBottomTwo = 0;
                redR = 0;
                redF = 0;
                redBack = 0;
                break;
            case TOP_SEVEN_ID:
                redT = 0;
                redTopTwo = 0;
                redTopThree = 0;
                redTopFour = 0;
                redTopFive = 0;
                redTopSix = 0;
                redTopSeven = 1;
                redTopEight = 0;
                redTopNine = 0;
                redB = 0;
                redBottomTwo = 0;
                redR = 0;
                redF = 0;
                redBack = 0;
                break;
            case TOP_EIGHT_ID:
                redT = 0;
                redTopTwo = 0;
                redTopThree = 0;
                redTopFour = 0;
                redTopFive = 0;
                redTopSix = 0;
                redTopSeven = 0;
                redTopEight = 1;
                redTopNine = 0;
                redB = 0;
                redBottomTwo = 0;
                redR = 0;
                redF = 0;
                redBack = 0;
                break;
            case TOP_NINE_ID:
                redT = 0;
                redTopTwo = 0;
                redTopThree = 0;
                redTopFour = 0;
                redTopFive = 0;
                redTopSix = 0;
                redTopSeven = 0;
                redTopEight = 0;
                redTopNine = 1;
                redB = 0;
                redBottomTwo = 0;
                redR = 0;
                redF = 0;
                redBack = 0;
                break;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            // X-rotation
            case KeyEvent.VK_NUMPAD1:
                if (travers == 1) {
                    if (angleTopX < 360) {
                        angleTopX += angleDelta;
                        System.out.println(angleTopX);
                    }
                } else if (travers == 2) {
                    if (angleTopTwoX < 360) {
                        angleTopTwoX += angleDelta;
                        System.out.println(angleTopTwoX);
                    }
                } else if (travers == 3) {
                    if (angleTopThreeX < 360) {
                        angleTopThreeX += angleDelta;
                        System.out.println(angleTopThreeX);
                    }
                } else if (travers == 4) {
                    if (angleTopFourX < 360) {
                        angleTopFourX += angleDelta;
                        System.out.println(angleTopFourX);
                    }
                } else if (travers == 5) {
                    if (angleTopFiveX < 360) {
                        angleTopFiveX += angleDelta;
                        System.out.println(angleTopFiveX);
                    }
                } else if (travers == 6) {
                    if (angleTopSixX < 360) {
                        angleTopSixX += angleDelta;
                        System.out.println(angleTopSixX);
                    }
                } else if (travers == 7) {
                    if (angleTopSevenX < 360) {
                        angleTopSevenX += angleDelta;
                        System.out.println(angleTopSevenX);
                    }
                } else if (travers == 8) {
                    if (angleTopEightX < 360) {
                        angleTopEightX += angleDelta;
                        System.out.println(angleTopEightX);
                    }
                } else if (travers == 9) {
                    if (angleTopNineX < 360) {
                        angleTopNineX += angleDelta;
                        System.out.println(angleTopNineX);
                    }
                }
                break;
            case KeyEvent.VK_NUMPAD3:

                if (travers == 1) {
                    if (angleTopX > 0) {
                        angleTopX -= angleDelta;
                        System.out.println(angleTopX);
                    }
                } else if (travers == 2) {
                    if (angleTopTwoX > 0) {
                        angleTopTwoX -= angleDelta;
                        System.out.println(angleTopTwoX);
                    }
                } else if (travers == 3) {
                    if (angleTopThreeX > 0) {
                        angleTopThreeX -= angleDelta;
                        System.out.println(angleTopThreeX);
                    }
                } else if (travers == 4) {
                    if (angleTopFourX > 0) {
                        angleTopFourX -= angleDelta;
                        System.out.println(angleTopFourX);
                    }
                } else if (travers == 5) {
                    if (angleTopFiveX > 0) {
                        angleTopFiveX -= angleDelta;
                        System.out.println(angleTopFiveX);
                    }
                } else if (travers == 6) {
                    if (angleTopSixX > 0) {
                        angleTopSixX -= angleDelta;
                        System.out.println(angleTopSixX);
                    }
                } else if (travers == 7) {
                    if (angleTopSevenX > 0) {
                        angleTopSevenX -= angleDelta;
                        System.out.println(angleTopSevenX);
                    }
                } else if (travers == 8) {
                    if (angleTopEightX > 0) {
                        angleTopEightX -= angleDelta;
                        System.out.println(angleTopEightX);
                    }
                } else if (travers == 9) {
                    if (angleTopNineX > 0) {
                        angleTopNineX -= angleDelta;
                        System.out.println(angleTopNineX);
                    }
                }
                break;

            // Y-rotation
            case KeyEvent.VK_NUMPAD4:

                if (travers == 1) {
                    if (angleTopY < 360) {
                        angleTopY += angleDelta;
                        System.out.println(angleTopY);
                    }
                } else if (travers == 2) {
                    if (angleTopTwoY < 360) {
                        angleTopTwoY += angleDelta;
                        System.out.println(angleTopTwoY);
                    }
                } else if (travers == 3) {
                    if (angleTopThreeY < 360) {
                        angleTopThreeY += angleDelta;
                        System.out.println(angleTopThreeY);
                    }
                } else if (travers == 4) {
                    if (angleTopFourY < 360) {
                        angleTopFourY += angleDelta;
                        System.out.println(angleTopFourY);
                    }
                } else if (travers == 5) {
                    if (angleTopFiveY < 360) {
                        angleTopFiveY += angleDelta;
                        System.out.println(angleTopFiveY);
                    }
                } else if (travers == 6) {
                    if (angleTopSixY < 360) {
                        angleTopSixY += angleDelta;
                        System.out.println(angleTopSixY);
                    }
                } else if (travers == 7) {
                    if (angleTopSevenY < 360) {
                        angleTopSevenY += angleDelta;
                        System.out.println(angleTopSevenY);
                    }
                } else if (travers == 8) {
                    if (angleTopEightY < 360) {
                        angleTopEightY += angleDelta;
                        System.out.println(angleTopEightY);
                    }
                } else if (travers == 9) {
                    if (angleTopNineY < 360) {
                        angleTopNineY += angleDelta;
                        System.out.println(angleTopNineY);
                    }
                }
                break;
            case KeyEvent.VK_NUMPAD6:

                if (travers == 1) {
                    if (angleTopY > 0) {
                        angleTopY -= angleDelta;
                        System.out.println(angleTopY);
                    }
                } else if (travers == 2) {
                    if (angleTopTwoY > 0) {
                        angleTopTwoY -= angleDelta;
                        System.out.println(angleTopTwoY);
                    }
                } else if (travers == 3) {
                    if (angleTopThreeY > 0) {
                        angleTopThreeY -= angleDelta;
                        System.out.println(angleTopThreeY);
                    }
                } else if (travers == 4) {
                    if (angleTopFourY > 0) {
                        angleTopFourY -= angleDelta;
                        System.out.println(angleTopFourY);
                    }
                } else if (travers == 5) {
                    if (angleTopFiveY > 0) {
                        angleTopFiveY -= angleDelta;
                        System.out.println(angleTopFiveY);
                    }
                } else if (travers == 6) {
                    if (angleTopSixY > 0) {
                        angleTopSixY -= angleDelta;
                        System.out.println(angleTopSixY);
                    }
                } else if (travers == 7) {
                    if (angleTopSevenY > 0) {
                        angleTopSevenY -= angleDelta;
                        System.out.println(angleTopSevenY);
                    }
                } else if (travers == 8) {
                    if (angleTopEightY > 0) {
                        angleTopEightY -= angleDelta;
                        System.out.println(angleTopEightY);
                    }
                } else if (travers == 9) {
                    if (angleTopNineY > 0) {
                        angleTopNineY -= angleDelta;
                        System.out.println(angleTopNineY);
                    }
                }

                break;

            // Z-rotation
            case KeyEvent.VK_NUMPAD7:

                if (travers == 1) {
                    if (angleTopZ < 360) {
                        angleTopZ += angleDelta;
                        System.out.println(angleTopZ);
                    }
                } else if (travers == 2) {
                    if (angleTopTwoZ < 360) {
                        angleTopTwoZ += angleDelta;
                        System.out.println(angleTopTwoZ);
                    }
                } else if (travers == 3) {
                    if (angleTopThreeZ < 360) {
                        angleTopThreeZ += angleDelta;
                        System.out.println(angleTopThreeZ);
                    }
                } else if (travers == 4) {
                    if (angleTopFourZ < 360) {
                        angleTopFourZ += angleDelta;
                        System.out.println(angleTopFourZ);
                    }
                } else if (travers == 5) {
                    if (angleTopFiveZ < 360) {
                        angleTopFiveZ += angleDelta;
                        System.out.println(angleTopFiveZ);
                    }
                } else if (travers == 6) {
                    if (angleTopSixZ < 360) {
                        angleTopSixZ += angleDelta;
                        System.out.println(angleTopSixZ);
                    }
                } else if (travers == 7) {
                    if (angleTopSevenZ < 360) {
                        angleTopSevenZ += angleDelta;
                        System.out.println(angleTopSevenZ);
                    }
                } else if (travers == 8) {
                    if (angleTopEightZ < 360) {
                        angleTopEightZ += angleDelta;
                        System.out.println(angleTopEightZ);
                    }
                } else if (travers == 9) {
                    if (angleTopNineZ < 360) {
                        angleTopNineZ += angleDelta;
                        System.out.println(angleTopNineZ);
                    }
                }
                break;
            case KeyEvent.VK_NUMPAD9:

                if (travers == 1) {
                    if (angleTopZ > 0) {
                        angleTopZ -= angleDelta;
                        System.out.println(angleTopZ);
                    }
                } else if (travers == 2) {
                    if (angleTopTwoZ > 0) {
                        angleTopTwoZ -= angleDelta;
                        System.out.println(angleTopTwoZ);
                    }
                } else if (travers == 3) {
                    if (angleTopThreeZ > 0) {
                        angleTopThreeZ -= angleDelta;
                        System.out.println(angleTopThreeZ);
                    }
                } else if (travers == 4) {
                    if (angleTopFourZ > 0) {
                        angleTopFourZ -= angleDelta;
                        System.out.println(angleTopFourZ);
                    }
                } else if (travers == 5) {
                    if (angleTopFiveZ > 0) {
                        angleTopFiveZ -= angleDelta;
                        System.out.println(angleTopFiveZ);
                    }
                } else if (travers == 6) {
                    if (angleTopSixZ > 0) {
                        angleTopSixZ -= angleDelta;
                        System.out.println(angleTopSixZ);
                    }
                } else if (travers == 7) {
                    if (angleTopSevenZ > 0) {
                        angleTopSevenZ -= angleDelta;
                        System.out.println(angleTopSevenZ);
                    }
                } else if (travers == 8) {
                    if (angleTopEightZ > 0) {
                        angleTopEightZ -= angleDelta;
                        System.out.println(angleTopEightZ);
                    }
                } else if (travers == 9) {
                    if (angleTopNineZ > 0) {
                        angleTopNineZ -= angleDelta;
                        System.out.println(angleTopNineZ);
                    }
                }
                break;

            case KeyEvent.VK_S:

                if (travers == 1) {
                    scaleTop += scaleDelta;
                } else if (travers == 2) {
                    scaleTopTwo += scaleDelta;
                } else if (travers == 3) {
                    scaleTopThree += scaleDelta;
                } else if (travers == 4) {
                    scaleTopFour += scaleDelta;
                } else if (travers == 5) {
                    scaleTopFive += scaleDelta;
                } else if (travers == 6) {
                    scaleTopSix += scaleDelta;
                } else if (travers == 7) {
                    scaleTopSeven += scaleDelta;
                } else if (travers == 8) {
                    scaleTopEight += scaleDelta;
                } else if (travers == 9) {
                    scaleTopNine += scaleDelta;
                }

                break;
            case KeyEvent.VK_A:
                System.out.println(travers);
                if (travers == 1) {
                    scaleTop -= scaleDelta;
                } else if (travers == 2) {
                    scaleTopTwo -= scaleDelta;
                } else if (travers == 3) {
                    scaleTopThree -= scaleDelta;
                } else if (travers == 4) {
                    scaleTopFour -= scaleDelta;
                } else if (travers == 5) {
                    scaleTopFive -= scaleDelta;
                } else if (travers == 6) {
                    scaleTopSix -= scaleDelta;
                } else if (travers == 7) {
                    scaleTopSeven -= scaleDelta;
                } else if (travers == 8) {
                    scaleTopEight -= scaleDelta;
                } else if (travers == 9) {
                    scaleTopNine -= scaleDelta;
                }

                break;
            //TODO: The button for toggling between objects
            case KeyEvent.VK_W:
                travers++;
                colorShape(travers);
                if (travers == 10) {
                    travers = 0;
                }
                break;
            case KeyEvent.VK_UP:
                currentAngleOfRotationX--;
                break;
            case KeyEvent.VK_DOWN:
                currentAngleOfRotationX++;
                break;
            case KeyEvent.VK_LEFT:
                currentAngleOfRotationY++;
                break;
            case KeyEvent.VK_RIGHT:
                currentAngleOfRotationY--;

                break;
            case KeyEvent.VK_ADD:
                if (currentAngleOfVisibleField > 1) {
                    currentAngleOfVisibleField--;
                }
                break;
            case KeyEvent.VK_SUBTRACT:
                if (currentAngleOfVisibleField < 179) {
                    currentAngleOfVisibleField++;
                }
                break;

            case KeyEvent.VK_Z:
                scale += 0.1;
                break;

            case KeyEvent.VK_X:
                scale -= 0.1;
                break;

            case KeyEvent.VK_J:
                translateX += 0.1;
                break;
            case KeyEvent.VK_K:
                translateX -= 0.1;
                break;

            case KeyEvent.VK_M:
                translateY += 0.1;
                break;
            case KeyEvent.VK_N:
                translateY -= 0.1;
                break;
            case KeyEvent.VK_I:
                translateZ += 0.1;
                break;
            case KeyEvent.VK_O:
                translateZ -= 0.1;
                break;
            case KeyEvent.VK_ESCAPE:
                animator.stop();
                System.exit(0);
                break;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: { // left button
                xCursor = e.getX();
                yCursor = e.getY();
                inSelectionMode = true;
                break;
            }
            case MouseEvent.BUTTON3: { // right button
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    private void lights(GL2 gl) {
        gl.glColor3d(0.5, 0.5, 0.5);
        float zero[] = {0, 0, 0, 1};
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, zero, 0);

        if (lightOnOff.isSelected())
            gl.glDisable(GL2.GL_LIGHTING);
        else
            gl.glEnable(GL2.GL_LIGHTING);

        float ambient[] = {0.1f, 0.1f, 0.1f, 1};
        float[] diffuse = {1.0f, 1.0f, 1.0f, 1.0f};
        float[] specular = {1.0f, 1.0f, 1.0f, 1.0f};

        if (ambientLighting.isSelected()) {
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, ambient, 0);
            gl.glEnable(GL2.GL_LIGHT0);
        } else {
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, zero, 0);
            gl.glDisable(GL2.GL_LIGHT0);
        }

        if (diffuseLighting.isSelected()) {
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, diffuse, 0);
            gl.glEnable(GL2.GL_LIGHT1);
        } else {
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, zero, 0);
            gl.glDisable(GL2.GL_LIGHT1);
        }

        if (specularLighting.isSelected()) {
            float[] shiness = {5.0f};
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, specular, 0);
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shiness, 0);
            gl.glEnable(GL2.GL_LIGHT2);
        } else {
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, zero, 0);
            gl.glDisable(GL2.GL_LIGHT2);
        }

        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, zero, 0); // Turn off emission color!
    }

    public void writeText(String tekst, int x, int y) {
        textRenderer.beginRendering(windowWidth, windowHeight);
        textRenderer.setColor(0.3f, 0.3f, 0.5f, 1);
        textRenderer.draw(tekst, x, y);
        textRenderer.endRendering();
    }

    public void writeMatch(String text, int x, int y) {
        textMatch.beginRendering(windowWidth, windowHeight);
        textMatch.setColor(0.3f, 0.3f, 0.5f, 1);
        textMatch.draw(text, x, y);
        textMatch.endRendering();
    }

    public void printMatch(GLAutoDrawable drawable) {
        // CONUS - 1
        // AZAT_CUB - 2
        // CYLINDER_1 - 3
        // CYLINDER_2 - 4
        // CONUS_2 - 5
        // CYLINDER_3 - 6
        // CONUS_3 - 7
        // AZAT_CUB_2 - 8
        // AZAT_CUB_3 - 9

        if (travers == 1) {
            if (shape[randomTop].equals(shape[top_idn]))
                if (topScaleCheck(scaleTop).equals("appropriate")
                        && rotationCheck(randomTop, angleTopX, angleTopY, angleTopZ).equals("correct")) {
                    writeMatch("Well Done! Correct shape, rotation and scaling.",
                            (int) (windowWidth / 4f), windowHeight - 40);
                }

        } else if (travers == 2) {
            if (shape[randomTopTwo].equals(shape[top_two_idn]))
                if (topTwoScaleCheck(scaleTopTwo).equals("appropriate")
                        && rotationCheck(randomTopTwo, angleTopTwoX, angleTopTwoY, angleTopTwoZ).equals("correct")) {
                    writeMatch("Well Done! Correct shape, rotation and scaling.",
                            (int) (windowWidth / 4f), windowHeight - 40);
                }


        } else if (travers == 3) {
            if (shape[randomTopThree].equals(shape[top_three_idn]))
                if (topTwoScaleCheck(scaleTopThree).equals("appropriate")
                        && rotationCheck(randomTopThree, angleTopThreeX, angleTopThreeY, angleTopThreeZ).equals("correct")) {
                    writeMatch("Well Done! Correct shape, rotation and scaling.",
                            (int) (windowWidth / 4f), windowHeight - 40);
                }
        } else if (travers == 4) {
            if (shape[randomTopFour].equals(shape[top_four_idn]))
                if (topTwoScaleCheck(scaleTopFour).equals("appropriate")
                        && rotationCheck(randomTopFour, angleTopFourX, angleTopFourY, angleTopFourZ).equals("correct")) {
                    writeMatch("Well Done! Correct shape, rotation and scaling.",
                            (int) (windowWidth / 4f), windowHeight - 40);
                }
        } else if (travers == 5) {
            if (shape[randomTopFive].equals(shape[top_five_idn]))
                if (topScaleCheck(scaleTopFive).equals("appropriate")
                        && rotationCheck(randomTopFive, angleTopFiveX, angleTopFiveY, angleTopFiveZ).equals("correct")) {
                    writeMatch("Well Done! Correct shape, rotation and scaling.",
                            (int) (windowWidth / 4f), windowHeight - 40);
                }
        } else if (travers == 6) {
            if (shape[randomTopSix].equals(shape[top_six_idn]))
                if (topTwoScaleCheck(scaleTopSix).equals("appropriate")
                        && rotationCheck(randomTopSix, angleTopSixX, angleTopSixY, angleTopSixZ).equals("correct")) {
                    writeMatch("Well Done! Correct shape, rotation and scaling.",
                            (int) (windowWidth / 4f), windowHeight - 40);
                }
        } else if (travers == 7) {
            if (shape[randomTopSeven].equals(shape[top_seven_idn]))
                if (topTwoScaleCheck(scaleTopSeven).equals("appropriate")
                        && rotationCheck(randomTopSeven, angleTopSevenX, angleTopSevenY, angleTopSevenZ).equals("correct")) {
                    writeMatch("Well Done! Correct shape, rotation and scaling.",
                            (int) (windowWidth / 4f), windowHeight - 40);
                }
        } else if (travers == 8) {
            if (shape[randomTopEight].equals(shape[top_eight_idn]))
                if (topTwoScaleCheck(scaleTopEight).equals("appropriate")
                        && rotationCheck(randomTopEight, angleTopEightX, angleTopEightY, angleTopEightZ).equals("correct")) {
                    writeMatch("Well Done! Correct shape, rotation and scaling.",
                            (int) (windowWidth / 4f), windowHeight - 40);
                }
        }  else if (travers == 9) {
            if (shape[randomTopNine].equals(shape[top_nine_idn]))
                if (topTwoScaleCheck(scaleTopNine).equals("appropriate")
                        && rotationCheck(randomTopNine, angleTopNineX, angleTopNineY, angleTopNineZ).equals("correct")) {
                    writeMatch("Well Done! Correct shape, rotation and scaling.",
                            (int) (windowWidth / 4f), windowHeight - 40);
                }
        }
    }

    public void printResult(GLAutoDrawable drawable) {
        writeText("RESULT: " + matchedShape() + "/9 shape matched correctly", (int) (windowWidth / 3.5f), windowHeight - 40);

        writeText("Top One: blueprint shape: " +
                        shape[randomTop] +
                        " - matched shape: " +
                        shape[top_idn] + " Scaling: " +
                        topScaleCheck(scaleTop) +
                        " Rotation: " + rotationCheck(randomTop, angleTopX, angleTopY, angleTopZ),
                (int) (windowWidth / 3.5f),
                windowHeight - 120);

        writeText("Top Two: blueprint shape: " +
                        shape[randomTopTwo] +
                        " - matched shape: " +
                        shape[top_two_idn] + " Scaling: " +
                        topTwoScaleCheck(scaleTopTwo) +
                        " Rotation: " + rotationCheck(randomTopTwo, angleTopTwoX, angleTopTwoY, angleTopTwoZ),
                (int) (windowWidth / 3.5f),
                windowHeight - 140);

        writeText("Top Three: blueprint shape: " +
                        shape[randomTopThree] +
                        " - matched shape: " +
                        shape[top_three_idn] + " Scaling: " +
                        topTwoScaleCheck(scaleTopThree) +
                        " Rotation: " + rotationCheck(randomTopThree, angleTopThreeX, angleTopThreeY, angleTopThreeZ),
                (int) (windowWidth / 3.5f),
                windowHeight - 140);

        writeText("Top Four: blueprint shape: " +
                        shape[randomTopFour] +
                        " - matched shape: " +
                        shape[top_three_idn] + " Scaling: " +
                        topTwoScaleCheck(scaleTopFour) +
                        " Rotation: " + rotationCheck(randomTopFour, angleTopFourX, angleTopFourY, angleTopFourZ),
                (int) (windowWidth / 3.5f),
                windowHeight - 140);

        writeText("Top Five: blueprint shape: " +
                        shape[randomTopFive] +
                        " - matched shape: " +
                        shape[top_five_idn] + " Scaling: " +
                        topTwoScaleCheck(scaleTopFive) +
                        " Rotation: " + rotationCheck(randomTopFive, angleTopFiveX, angleTopFiveY, angleTopFiveZ),
                (int) (windowWidth / 3.5f),
                windowHeight - 140);

        writeText("Top Six: blueprint shape: " +
                        shape[randomTopSix] +
                        " - matched shape: " +
                        shape[top_six_idn] + " Scaling: " +
                        topTwoScaleCheck(scaleTopSix) +
                        " Rotation: " + rotationCheck(randomTopSix, angleTopSixX, angleTopSixY, angleTopSixZ),
                (int) (windowWidth / 3.5f),
                windowHeight - 140);

        writeText("Top Seven: blueprint shape: " +
                        shape[randomTopSeven] +
                        " - matched shape: " +
                        shape[top_seven_idn] + " Scaling: " +
                        topTwoScaleCheck(scaleTopSeven) +
                        " Rotation: " + rotationCheck(randomTopSeven, angleTopSevenX, angleTopSevenY, angleTopSevenZ),
                (int) (windowWidth / 3.5f),
                windowHeight - 140);

        writeText("Top Eight: blueprint shape: " +
                        shape[randomTopEight] +
                        " - matched shape: " +
                        shape[top_eight_idn] + " Scaling: " +
                        topTwoScaleCheck(scaleTopEight) +
                        " Rotation: " + rotationCheck(randomTopEight, angleTopEightX, angleTopEightY, angleTopEightZ),
                (int) (windowWidth / 3.5f),
                windowHeight - 140);

        writeText("Top Nine: blueprint shape: " +
                        shape[randomTopNine] +
                        " - matched shape: " +
                        shape[top_nine_idn] + " Scaling: " +
                        topTwoScaleCheck(scaleTopNine) +
                        " Rotation: " + rotationCheck(randomTopNine, angleTopNineX, angleTopNineY, angleTopNineZ),
                (int) (windowWidth / 3.5f),
                windowHeight - 140);
    }

    private int matchedShape() {

        int match = 0;
        if (shape[randomTop].equals(shape[top_idn])) {
            match++;
        }
        if (shape[randomTopTwo].equals(shape[top_two_idn])) {
            match++;
        }
        if (shape[randomTopThree].equals(shape[top_three_idn])) {
            match++;
        }
        if (shape[randomTopFour].equals(shape[top_four_idn])) {
            match++;
        }
        if (shape[randomTopFive].equals(shape[top_five_idn])) {
            match++;
        }
        if (shape[randomTopSix].equals(shape[top_six_idn])) {
            match++;
        }
        if (shape[randomTopSeven].equals(shape[top_seven_idn])) {
            match++;
        }
        if (shape[randomTopEight].equals(shape[top_eight_idn])) {
            match++;
        }
        if (shape[randomTopNine].equals(shape[top_nine_idn])) {
            match++;
        }
        return match;
    }

    public String topScaleCheck(double scale) {

        double scaling = Math.round(scale * 100.0) / 100.0;

        String text;
        if (scaling == 1.0) {
            text = "appropriate";
        } else {
            text = "not appropriate";
        }
        return text;
    }


    public String topTwoScaleCheck(double scale) {

        double scaling = Math.round(scale * 100.0) / 100.0;

        String text;
        if (scaling == 1.0) {
            text = "appropriate";
        } else {
            text = "not appropriate";
        }
        return text;
    }


    public String rotationCheck(int shape, int angleX, int angleY, int angleZ) {

        // shape #:
        // 1, 5, 7 - Conus
        // 2, 8, 9 - Azat Cube
        // 3, 4, 6 - Cylinder

        System.out.println("shape "+ shape);
        System.out.println("angleX "+angleX);
        System.out.println("angleY "+angleY);
        System.out.println("angleZ "+angleZ);

        String text;
        if (shape == 1 || shape == 5 || shape == 7) {
            if (angleX == 90 && angleY == 90 && angleZ == 180) {
                text = "correct";
            } else {
                text = "incorrect";
            }
        } else if (shape == 2 || shape == 8 || shape == 9) {
            // x and y
            if (((angleX % 360 == 0) && (angleY % 45 == 0) && (angleZ % 360 == 0)) ||
                    ((angleX % 360 == 180) && (angleY % 135 == 0) && (angleZ % 180 == 0)) ||
                    ((angleX % 90 == 0) && (angleY % 90 == 0) && (angleZ % 90 == 0))) {
                text = "correct";
            } else {
                text = "incorrect";
            }
            System.out.println("shape 2");
        } else if (shape == 3 || shape == 4 || shape == 6) {
            // x and y
            if (((angleX % 90 == 0) && (angleY % 90 == 0) && (angleZ % 180 == 0))) {
                text = "correct";
            } else {
                text = "incorrect";
            }

        } else {
            text = "incorrect";
        }
        return text;
    }

    public static void main(String[] args) {
        new Exam();
    }
}
