/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.art;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import visualk.art.graph.GifEntity;
import visualk.art.graph.LiveMosaic;


/**
 *
 * @author lamaken
 */

public class Main extends HttpServlet {

    public static float counter = 0;

    public void copy(final InputStream in, final OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int count;

        while ((count = in.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }

        // Flush out stream, to write any remaining buffered data
        out.flush();
    }

    
    
    
    static public void step(){
        counter+=0.01;
        if(counter>10.3){
            counter=0;
        }
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("image/GIF");
//        
//        
//        
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Main</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Main at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            out.close();
//        }

        String mx = request.getParameter("mx");
        String my = request.getParameter("my");
        String cell = request.getParameter("cellw");
        String hrzmkr = request.getParameter("hrzmkr");

        if (mx != null) {
            Main.CANVASX_SIZE = Integer.parseInt(mx);
        }
        if (my != null) {
            Main.CANVASY_SIZE = Integer.parseInt(my);
        }
        if (cell != null) {
            Main.cellw = Integer.parseInt(cell);
        }

        if (Main.counter > 0.3) {
            Main.counter = 0;
        }
        Main.counter += 0.001;

        
        if (request.getParameter("mosaic") != null) {
             getMosaic("mosaic v0.0.1");

            response.setContentType("image/gif");
            
            String pathToWeb = getServletContext().getRealPath(File.separator);
            String filename = pathToWeb+"mosaic.gif";
            OutputStream out = response.getOutputStream();
            InputStream in = new FileInputStream(new File(filename));
            try {
                copy(in, out);
            } catch (Exception e) {
            } finally {
                in.close();
            }

            out.close();

        }else if (request.getParameter("hrzmkr") == null) {
            ImageIO.write(getFace(Main.counter), "gif", response.getOutputStream());

        } else{

            getHrz(hrzmkr, Main.CANVASX_SIZE, Main.CANVASY_SIZE);

            response.setContentType("image/gif");
            
            String pathToWeb = getServletContext().getRealPath(File.separator);
            String filename = pathToWeb+"last.gif";
            OutputStream out = response.getOutputStream();
            InputStream in = new FileInputStream(new File(filename));
            try {
                copy(in, out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                in.close();
            }

            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static Integer CANVASX_SIZE = 100;
    public static Integer CANVASY_SIZE = 100;
    public static Integer cellw = 15;

    
    public BufferedImage getFace(float seed) {

        //seed=seed*new Float(Math.random()).intValue();
        BufferedImage buf = new BufferedImage(CANVASX_SIZE, CANVASY_SIZE, 2);
        Graphics2D g2 = buf.createGraphics();
        g2.clipRect(0, 0, CANVASX_SIZE + cellw, CANVASY_SIZE + cellw);

// g2.setColor(Color.CYAN);
//g2.fillRect(100, 100, CANVASX_SIZE-200, CANVASY_SIZE-200);
        for (int n = 0; n < new Float(Main.CANVASX_SIZE / 2).intValue(); n += cellw) {
            for (int m = 0; m < new Float(Main.CANVASY_SIZE / 2).intValue(); m += cellw) {
                /*                    
                 if((n+m)%m==0)
                 {
                 */
                    //g2.setColor(Color.getHSBColor(CANVASX_SIZE-(((n+1)*(m+1))/seed), CANVASX_SIZE-(((n+1)*(m+1))/seed*3), 1-n*10*m));
                    /*}else if((n+m)%n==0){
                 g2.setColor(Color.getHSBColor((m+n)/2+seed, (m+n)/2+seed, seed+(m+n)/2));
                 }else {
                 */
                //         g2.setColor(Color.decode(seed*n*m+""));
                    /*}*/

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.getHSBColor((m * n) / 2 + seed, (m * n) / 2 + seed, (m * n) / 2 + seed));
                   // g2.setColor(Color.getHSBColor(seed, n, m));

                //   g2.setColor(Color.getHSBColor((m+n)/2+seed, (m+n)/2+seed, seed+(m+n)/2));
                //g2.drawLine(n,m,new Float(n*(Math.cos(CANVASX_SIZE-n))).intValue(),new Float(m*(Math.cos(CANVASY_SIZE-m))).intValue());
                //g2.drawLine(n,m,CANVASX_SIZE-n,CANVASY_SIZE-m);
                g2.fillRect(n - cellw, m - cellw, cellw * 2, cellw * 2);
                g2.fillRect(Main.CANVASX_SIZE - n - cellw, m - cellw, cellw * 2, cellw * 2);
                g2.fillRect(n - cellw, Main.CANVASY_SIZE - m - cellw, cellw * 2, cellw * 2);
                g2.fillRect(Main.CANVASX_SIZE - n - cellw, Main.CANVASY_SIZE - m - cellw, cellw * 2, cellw * 2);

            }
        }

        g2.dispose();
        return (buf);
    }

    
    
    
    
    
    
    public void getHrz(String name, int mx, int my) {
        /*
         BufferedImage buf = new BufferedImage(mx, my, 2);
         Graphics2D g2 = buf.createGraphics();
         g2.clipRect(0, 0, mx, my + 20);

         Horizon hrz2;
         hrz2 = new Horizon("default");
         hrz2.setAuthorHrz(name);
         hrz2.setHorizontal();
         hrz2.setAureaProp(true);
         hrz2.setCanvasHeigth(my-20);
         hrz2.setCanvasWidth(mx);
         hrz2.makeRandomAlçadaHoritzo();
         hrz2.makeRandomPal();
         hrz2.makeRandomSuperNova();
         hrz2.makeRandomHombra();
         hrz2.makeRandomColors();

         g2.drawImage(hrz2.getHrzImage(), null, 0, 0);
         g2.dispose();
         */
        String[] args = new String[3];

        args[0] = name;
        args[1] = "frm1.gif,frm2.gif,frm3.gif,frm4.gif,frm5.gif,frm6.gif,frm7.gif,frm8.gif,frm9.gif,frm10.gif,frm11.gif,frm12.gif,frm13.gif,frm14.gif";
        args[2] = "50,50,50,50,50,50,50,50,50,50,50,50,50,50";

        try {
            GifEntity.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //return (buf);
    }
    
     public void getMosaic(String name) {
        String[] args = new String[3];

        args[0] = name;
        args[1] = "frm1.gif,frm2.gif,frm3.gif,frm4.gif,frm5.gif,frm6.gif,frm7.gif,frm8.gif,frm9.gif,frm10.gif,frm11.gif,frm12.gif,frm13.gif,frm14.gif";
        args[2] = "30,30,30,30,30,30,30,30,30,30,30,30,30,30";

        try {
            LiveMosaic.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

       
    }
}
