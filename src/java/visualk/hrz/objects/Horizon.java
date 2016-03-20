package visualk.hrz.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import java.io.Serializable;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Random;

import visualk.hrz.db.DbHorizons;
import visualk.html.UniqueName;

public class Horizon implements Serializable {

    private static final String URL_IMG = "http://art.hrzmkr.com/img/";
    /**
     *
     */
    private static final long serialVersionUID = 24L;
    private static final String HRZMKR_VERSION = "hrzmkr v0.2";

    private final static int MAX_WIDTH = 401;
    private final static int MAX_HEIGTH = 401;

    private int max_width = MAX_WIDTH;
    private int max_height = MAX_HEIGTH;

    private final static int MIN_WIDTH = 210;
    private final static int MIN_HEIGTH = 210;

    private Image bmpSuperNova;
    private Image bmpTextura;
    private Image bmpCel;

    private String version = HRZMKR_VERSION;
    private String nameHrz = "void";
    private int topHrz = 50;
    private Color topHrzColor = Color.red;
    private Color bottomHrzColor = Color.blue;
    private int canvasWidth = 300;
    private int canvasHeigth = 100;
    private String authorHrz = "auto";
    private int xPal, yPal = 10;
    private int hPalx, hPaly = 20;
    private int alcada = 20;
    private Color colPal = Color.red;
    private boolean horizontal = true;
    private boolean aureaProp = false;

    private int superX = 150;
    private int superY = 50;
    private boolean textura = true;

    private DbHorizons db = new DbHorizons(); // connexio a la BD

    public void save2Db(String name) {
        setNameHrz(new UniqueName(8).getName());
        db.addHorizon(this, name);
        carrega(this.getNameHrz());
    }

    public boolean isAurea() {
        return (aureaProp);
    }

    public void setAureaProp(boolean sino) {
        this.aureaProp = sino;
    }

    public void setHorizontal() {
        horizontal = true;
    }

    public void setvertical() {
        horizontal = false;
    }

    public Horizon(String name) {
        this.nameHrz = name;
        max_width = MAX_WIDTH;
        max_height = MAX_HEIGTH;

        this.version = HRZMKR_VERSION;

        loadSuperNova();// carreguem imatge de la llum

        loadTextura();// carreguem textura del terra

        loadCel();// carreguem textura del cel

    }

    private int getAureo(int mida) {
        return ((int) (mida * 0.618033988272397));
    }

    public void carrega(String nom) {

        Horizon tmp = db.getHrznBD(nom);
        this.nameHrz = tmp.nameHrz;
        this.topHrz = tmp.topHrz;
        this.topHrzColor = tmp.topHrzColor;
        this.bottomHrzColor = tmp.bottomHrzColor;
        this.canvasWidth = tmp.canvasWidth;
        this.canvasHeigth = tmp.canvasHeigth;
        this.authorHrz = tmp.authorHrz;
        this.xPal = tmp.xPal;
        this.yPal = tmp.yPal;
        this.hPalx = tmp.hPalx;
        this.hPaly = tmp.hPaly;
        this.alcada = tmp.alcada;
        this.colPal = tmp.colPal;
        this.horizontal = tmp.horizontal;
        this.aureaProp = tmp.aureaProp;
        this.version = tmp.version;
        this.superX = tmp.superX;
        this.superY = tmp.superY;
        this.textura = tmp.textura;

    }

    public void saveToFile(String name) {
        save2Db(name);

    }

    public void makeRandomCanvas() {
        Random r = new Random();

        if (max_width <= MIN_WIDTH) {
            max_width = MIN_WIDTH - 15;
        }
        if (max_height <= MIN_HEIGTH) {
            max_height = MIN_HEIGTH - 15;
        }

        if (max_width >= MAX_WIDTH) {
            max_width = MAX_WIDTH - 15;
        }
        if (max_height >= MAX_HEIGTH) {
            max_height = MAX_HEIGTH - 15;
        }

        // el tamany de la imatge
        if (!horizontal) {
            this.canvasHeigth = r.nextInt(max_height);
            while (this.canvasHeigth < MIN_HEIGTH) {
                this.canvasHeigth = r.nextInt(max_height);
            }
            if (aureaProp) {
                this.canvasWidth = getAureo(this.canvasHeigth);
                while (this.canvasWidth > max_width) {
                    this.canvasWidth = r.nextInt(max_width);
                }
                if (this.canvasWidth != getAureo(this.canvasHeigth)) {
                    setAureaProp(false);
                }

            } else {
                this.canvasWidth = r.nextInt(max_width);
                while (this.canvasWidth < MIN_WIDTH) {
                    this.canvasWidth = r.nextInt(max_width);
                }
            }

        } else {
            this.canvasWidth = r.nextInt(max_width);
            while (this.canvasWidth < MIN_WIDTH) {
                this.canvasWidth = r.nextInt(max_width);
            }
            if (aureaProp) {
                this.canvasHeigth = getAureo(this.canvasWidth);
                while (this.canvasHeigth > max_height) {
                    this.canvasHeigth = r.nextInt(max_height);
                }
                if (this.canvasHeigth != getAureo(this.canvasWidth)) {
                    setAureaProp(false);
                }

            } else {
                this.canvasHeigth = r.nextInt(max_height);
                while (this.canvasHeigth < MIN_HEIGTH) {
                    this.canvasHeigth = r.nextInt(max_height);
                }
            }
        }

        System.out.println("rw:" + this.canvasWidth + " rh:"
                + this.canvasHeigth);
    }

    public void makeRandomCanvas(int maxx, int maxy, int minx, int miny) {
        Random r = new Random();

        // el tamany de la imatge
        if (!horizontal) {
            this.canvasHeigth = r.nextInt(maxy);
            while (this.canvasHeigth < miny) {
                this.canvasHeigth = r.nextInt(maxy);
            }
            if (aureaProp) {
                this.canvasWidth = getAureo(this.canvasHeigth);
            } else {
                this.canvasWidth = r.nextInt(maxx);
                while (this.canvasWidth < minx) {
                    this.canvasWidth = r.nextInt(maxx);
                }
            }

        } else {
            this.canvasWidth = r.nextInt(maxx);
            while (this.canvasWidth < minx) {
                this.canvasWidth = r.nextInt(maxx);
            }
            if (aureaProp) {
                this.canvasHeigth = getAureo(this.canvasWidth);
            } else {
                this.canvasHeigth = r.nextInt(maxy);
                while (this.canvasHeigth < miny) {
                    this.canvasHeigth = r.nextInt(maxy);
                }
            }
        }

    }

    public void makeRandomSuperNova() {
        Random r = new Random();
        this.superX = r.nextInt(this.canvasWidth);
        this.superY = r.nextInt(this.topHrz);

    }

    public void makeRandomTextura() {
        Random r = new Random();
        this.textura = (r.nextInt(2) == 1);
    }

    public void makeRandomAureo() {
        Random r = new Random();
        // nuemero aureo
        this.aureaProp = (r.nextInt(2) == 1);
        System.out.println("exit makeRandomAureo");

    }

    public void makeRandomHorizontal() {
        Random r = new Random();

        this.horizontal = (r.nextInt(2) == 1);
        System.out.println("exit makeRandomHorizontal");
    }

    public void makeRandomAlçadaHoritzo() {
        Random r = new Random();

        // l'alçada de l'horitzó
        if (aureaProp) {
            this.topHrz = getAureo(this.canvasHeigth);
        } else {
            this.topHrz = r.nextInt(this.canvasHeigth);
            // while(this.topHrz<50)this.topHrz=r.nextInt(this.canvasHeigth);
        }
        System.out.println("exit makeRandomAlçadaHoritzo");
    }

    public void makeRandomPal() {
        Random r = new Random();

        // pal
        if (aureaProp) {
            this.xPal = getAureo(r.nextInt(this.canvasWidth));
            this.yPal = getAureo(r.nextInt(this.canvasHeigth - this.topHrz));
            this.alcada = getAureo(r.nextInt(this.canvasHeigth - this.yPal));
        } else {
            this.xPal = r.nextInt(this.canvasWidth);
            this.yPal = r.nextInt(this.canvasHeigth - this.topHrz);
            this.alcada = r.nextInt(this.canvasHeigth - this.yPal);
        }
        System.out.println("exit makeRandomPal");
    }

    public void makeRandomHombra() {
        Random r = new Random();

        // hombra
        this.hPalx = r.nextInt(this.canvasWidth);
        this.hPaly = r.nextInt(this.canvasHeigth - this.topHrz);
        System.out.println("exit makeRandomHombra");

    }

    public void makeRandomColors() {
        Random r = new Random();

        // colors
        this.colPal = new Color(r.nextInt(255 * 255 * 255));
        this.topHrzColor = new Color(r.nextInt(255 * 255 * 255));
        this.bottomHrzColor = new Color(r.nextInt(255 * 255 * 255));
    }

    public void makeRandom() {
        makeRandomTextura();

        makeRandomHorizontal();
        makeRandomAureo();

        makeRandomCanvas();

        makeRandomAlçadaHoritzo();

        makeRandomSuperNova();
        makeRandomPal();
        makeRandomHombra();
        makeRandomColors();

        if (isAurea()) {
            setAuthorHrz("* " + this.version);
        } else {
            setAuthorHrz("" + this.version);
        }
        System.out.println("exit makeRandom");
    }

    public BufferedImage getHrzSmallImage(int w, int h) {
        BufferedImage buf = this.getHrzImage();
        float mx = 0, my = 0;
        int mmy = 0, mmx = 0;

        mx = this.getCanvasWidth();
        my = this.getCanvasHeigth();
        if (mx > my) {
            mmx = w;
            mmy = Math.round((my / mx) * w);
        }
        if (mx <= my) {
            mmx = Math.round((mx / my) * w);
            mmy = h;
        }
        ;

        // TODO:DEBUG
        System.out.println("mx:" + mx + " my:" + my + " mmx:" + mmx + " mmy:"
                + mmy);

        Image img = buf
                .getScaledInstance(mmx, mmy, BufferedImage.SCALE_DEFAULT);

        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img
                .getHeight(null), 1);
        Graphics g = bimage.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();

        return (bimage);
    }

    private void loadSuperNova() {
        System.out.print("Loading supernova");

        URL url;
        try {
            url = new URL(URL_IMG + "llum2.png");
            bmpSuperNova = Toolkit.getDefaultToolkit().getImage(url);

            if (bmpSuperNova == null) {
                System.out.println("... error");
            } else {
                System.out.println("... ok");
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            System.out.print(".. ERROR url.");
            e.printStackTrace();
        }

    }

    private void loadTextura() {
        System.out.print("Loading textura");

        URL url;
        try {
            url = new URL(URL_IMG + "textura.png");
            bmpTextura = Toolkit.getDefaultToolkit().getImage(url);

            if (bmpTextura == null) {
                System.out.println("... error");
            } else {
                System.out.println("... ok");
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            System.out.print(".. ERROR url.");
            e.printStackTrace();
        }
    }

    private void loadCel() {
        System.out.print("Loading cel");

        URL url;
        try {
            url = new URL(URL_IMG + "celgran.png");
            bmpCel = Toolkit.getDefaultToolkit().getImage(url);

            if (bmpCel == null) {
                System.out.println("... error");
            } else {
                System.out.println("... ok");
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            System.out.print(".. ERROR url.");
            e.printStackTrace();
        }
    }

    public BufferedImage getHrzImage() {

        int mx = this.getCanvasWidth();
        int my = this.getCanvasHeigth();

        int ratiox = mx / 200;
        int ratioy = my / 200;
        int nx, ny = 0;

        BufferedImage buf = new BufferedImage(this.getCanvasWidth(), this.getCanvasHeigth() + 21, 1);
        Graphics2D g2 = buf.createGraphics();

        g2.clipRect(0, 0, this.getCanvasWidth(), this.getCanvasHeigth() + 21);

        //cel
        g2.setColor(this.getTopHrzColor());
        g2.fillRect(0, 0, this.getCanvasWidth(), this.getTopHrz());

        g2.drawImage(bmpCel, 0, 0, null);

        // posem la llum
        g2.drawImage(bmpSuperNova, this.superX - 100, this.superY - 100, null);

        // posem el terra
        g2.setColor(this.getBottomHrzColor());
        g2.fillRect(0, this.getTopHrz(), this.getCanvasWidth(), this
                .getCanvasHeigth()
                - this.getTopHrz());
		// posem la textura per tot el terra

        for (nx = 0; nx <= ratiox; nx++) {
            for (ny = 0; ny <= ratioy; ny++) {
                if (this.isTextura()) {
                    g2.drawImage(bmpTextura, nx * 200, this.getTopHrz()
                            + (ny * 200), null);
                }
            }
        }

        // ombra
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.black);
        /*g2.drawLine(this.getxPal(), this.getyPal() + this.getTopHrz(), this
                .gethPalx(), this.getTopHrz() + this.gethPaly());
*/
         int rotation = 0;
        
        if (this.gethPaly() < this.getyPal() ) {
            sethPaly(this.getyPal());
        }
        
        
        rotation = 2 * this.getxPal() - this.superX;
        

        g2.drawLine(this.getxPal(), this.getyPal() + this.getTopHrz(), rotation, this.getTopHrz() + this.gethPaly());

        
        // pal
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        g2.setColor(Color.DARK_GRAY);
        g2.drawLine(this.getxPal(), this.getyPal() + this.getTopHrz(), this
                .getxPal(), this.getTopHrz() + this.getyPal()
                - this.getAlçada());
        g2.drawLine(this.getxPal() + 1, this.getyPal() + this.getTopHrz(), this
                .getxPal() + 1, this.getTopHrz() + this.getyPal()
                - this.getAlçada());

		// firma
      
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setColor(Color.BLACK);
        g2.fillRect(0, this.getCanvasHeigth(), this.getCanvasWidth(), 20);
        g2.setColor(Color.GRAY);
        g2.fillRect(0, this.getCanvasHeigth()+1, this.getCanvasWidth(), 18);
        
        /*g2.setColor(Color.GRAY);
        
        g2.fillRect(4, this.getCanvasHeigth() - 4, this.getCanvasWidth()-2, 18);
        */
        g2.setColor(Color.WHITE);
        g2.drawString(this.authorHrz, 2, this.getCanvasHeigth() + 15);

        g2.dispose();

        return (buf);
    }

    public String getNameHrz() {
        return nameHrz;
    }

    public void setNameHrz(String nameHrz) {
        this.nameHrz = nameHrz;
    }

    public int getTopHrz() {
        return topHrz;
    }

    public void setTopHrz(int topHrz) {
        this.topHrz = topHrz;
    }

    public Color getTopHrzColor() {
        return topHrzColor;
    }

    public void setTopHrzColor(Color topHrzColor) {
        this.topHrzColor = topHrzColor;
    }

    public Color getBottomHrzColor() {
        return bottomHrzColor;
    }

    public void setBottomHrzColor(Color bottomHrzColor) {
        this.bottomHrzColor = bottomHrzColor;
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasHeigth() {
        return canvasHeigth;
    }

    public void setCanvasHeigth(int canvasHeigth) {
        this.canvasHeigth = canvasHeigth;
    }

    public String getAuthorHrz() {
        return authorHrz;
    }

    public void setAuthorHrz(String authorHrz) {
        this.authorHrz = authorHrz;
    }

    public int getxPal() {
        return xPal;
    }

    public void setxPal(int xPal) {
        this.xPal = xPal;
    }

    public int getyPal() {
        return yPal;
    }

    public void setyPal(int yPal) {
        this.yPal = yPal;
    }

    public int getAlçada() {
        return alcada;
    }

    public void setAlcada(int alçada) {
        this.alcada = alçada;
    }

    public Color getColPal() {
        return colPal;
    }

    public void setColPal(Color colPal) {
        this.colPal = colPal;
    }

    public int gethPalx() {
        return hPalx;
    }

    public void sethPalx(int hPalx) {
        this.hPalx = hPalx;
    }

    public int gethPaly() {
        return hPaly;
    }

    public void sethPaly(int hPaly) {
        this.hPaly = hPaly;
    }

    @Deprecated
    public String getSVG() {

        String palcolor = "rgb(" + this.getColPal().getRed() + ","
                + this.getColPal().getGreen() + ","
                + this.getColPal().getBlue() + ")";

        String topcolor = "rgb(" + this.getTopHrzColor().getRed() + ","
                + this.getTopHrzColor().getGreen() + ","
                + this.getTopHrzColor().getBlue() + ")";

        String bottomcolor = "rgb(" + this.getBottomHrzColor().getRed() + ","
                + this.getBottomHrzColor().getGreen() + ","
                + this.getBottomHrzColor().getBlue() + ")";

        String xml = "<svg style=\"text-align:center\" width=\"100%\" height=\"100%\" version=\"1.1\""
                + "     xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">"
                + " <desc>hrzmkr.com</desc>"
                + "<rect x=\"0\" y=\"0\" width=\""
                + this.canvasWidth
                + "\" height=\""
                + this.getTopHrz()
                + "\""
                + "      fill=\""
                + topcolor
                + "\" stroke=\"white\"  stroke-width=\".13\"/>"
                + "<rect x=\"0\" y=\""
                + this.getTopHrz()
                + "\" width=\""
                + this.canvasWidth
                + "\" height=\""
                + (this.canvasHeigth - this.getTopHrz())
                + "\" fill=\""
                + bottomcolor
                + "\" stroke=\"white\"  stroke-width=\".13\"/>"
                + "<line x1=\""
                + this.getxPal()
                + "\" y1=\""
                + (this.getyPal() + this.getTopHrz())
                + "\" x2=\""
                + this.gethPalx()
                + "\" y2=\""
                + (this.getTopHrz() + this.gethPaly())
                + "\""
                + " style=\"stroke:rgb(0,0,0);stroke-width:2\"/>"
                + "<line x1=\""
                + this.getxPal()
                + "\" y1=\""
                + (this.getyPal() + this.getTopHrz())
                + "\" x2=\""
                + this.getxPal()
                + "\" y2=\""
                + (this.getTopHrz() + this.getyPal() - this.getAlçada())
                + "\""
                + " style=\"stroke:" + palcolor + ";stroke-width:2\"/>"
                + "</svg>";

        /*
         * //pal g2.setColor(this.getColPal()); g2.drawLine(this.getxPal(),
         * this.getyPal()+this.getTopHrz(),
         * this.getxPal(),this.getTopHrz()+this.getyPal()-this.getAlçada());
         * g2.drawLine(this.getxPal()+1, this.getyPal()+this.getTopHrz(),
         * this.getxPal()+1,this.getTopHrz()+this.getyPal()-this.getAlçada());
         * 
         * 
         * 
         * <line x1="0" y1="0" x2="300" y2="300"
         * style="stroke:rgb(99,99,99);stroke-width:2"/>
         */
        return xml;
    }

	// iexplorer
    @Deprecated
    public String getVML() {
        String xml = "<html xmlns:v>"
                + "<body>"
                + "<v:oval style=\"left:0;top:0;width:100;height:50\" fillcolor=\"blue\" stroked=\"f\" />"
                + "</body>" + "</html>";
        return xml;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getSuperX() {
        return superX;
    }

    public void setSuperX(int superX) {
        this.superX = superX;
    }

    public int getSuperY() {
        return superY;
    }

    public void setSuperY(int superY) {
        this.superY = superY;
    }

    public boolean isTextura() {
        return textura;
    }

    public void setTextura(boolean textura) {
        this.textura = textura;
    }

    public boolean isAureaProp() {
        return aureaProp;
    }

}
