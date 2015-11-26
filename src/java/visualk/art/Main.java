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
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lamaken
 */
@WebServlet(name = "Main", urlPatterns = {"/Main"})
public class Main extends HttpServlet {
    
    static Integer counter = 83;

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
        
    
        if(mx!=null)Main.CANVASX_SIZE=Integer.parseInt(mx);
        if(my!=null)Main.CANVASY_SIZE=Integer.parseInt(my);
        if(cell!=null)Main.cellw=Integer.parseInt(cell);
        
        
        
        if(Main.counter>2)Main.counter=0;
        
        ImageIO.write(getFace(Main.counter++),"gif",response.getOutputStream());
 
    
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
    
 static Integer CANVASX_SIZE=90;
 static Integer CANVASY_SIZE=90;
 static Integer cellw=10;    
    
    public BufferedImage getFace(Integer seed) {
        
                //seed=seed*new Float(Math.random()).intValue();
                
                
                
        
        
		BufferedImage buf = new BufferedImage(CANVASX_SIZE,CANVASY_SIZE, 2);
		Graphics2D g2 = buf.createGraphics();
                g2.clipRect(0,0,CANVASX_SIZE+cellw,CANVASY_SIZE+cellw);
                
// g2.setColor(Color.CYAN);
//g2.fillRect(100, 100, CANVASX_SIZE-200, CANVASY_SIZE-200);
                
                
	        for (int n=0;n<new Float(CANVASX_SIZE/2).intValue();n+=cellw){
                for (int m=0;m<new Float(CANVASY_SIZE/2).intValue();m+=cellw){
/*                    
                    if((n+m)%m==0)
                    {
                    */
                    //g2.setColor(Color.getHSBColor(CANVASX_SIZE-(((n+1)*(m+1))/seed), CANVASX_SIZE-(((n+1)*(m+1))/seed*3), 1-n*10*m));
                    /*}else if((n+m)%n==0){
                        g2.setColor(Color.getHSBColor((m+n)/2+seed, (m+n)/2+seed, seed+(m+n)/2));
                    }else {
                        */
                        //g2.setColor(Color.decode(seed*n*m+""));
                    /*}*/
                
                    
                   g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
                
               //     g2.setColor(Color.decode((m*m+n*n)/seed*(m*m+n*n+1)/2+""));
                    
                    g2.setColor(Color.getHSBColor((m*n)/2+seed, (m*n)/2+seed, seed+(m*n)/2));
                    
                  //  g2.setColor(Color.getHSBColor((m+n)/2+seed, (m+n)/2+seed, seed+(m+n)/2));
                    
                    //g2.drawLine(n,m,new Float(n*(Math.cos(CANVASX_SIZE-n))).intValue(),new Float(m*(Math.cos(CANVASY_SIZE-m))).intValue());
                    //g2.drawLine(n,m,CANVASX_SIZE-n,CANVASY_SIZE-m);
                    
                    g2.fillRect(n-cellw, m-cellw, cellw*2, cellw*2);
                    g2.fillRect(CANVASX_SIZE-n-cellw, m-cellw, cellw*2, cellw*2);
                    g2.fillRect(n-cellw,CANVASY_SIZE-m-cellw, cellw*2, cellw*2);
                    g2.fillRect(CANVASX_SIZE-n-cellw,CANVASY_SIZE-m-cellw, cellw*2, cellw*2);
                   
                    
                    
                    
                     
                }
                }

/*		int mx = this.getCanvasWidth();
		int my = this.getCanvasHeigth();

		int ratiox = mx / 200;
		int ratioy = my / 200;
		int nx, ny = 0;
		

		g2.clipRect(0,0,this.getCanvasWidth(), this.getCanvasHeigth() + 21);
		
		//cel
		g2.setColor(this.getTopHrzColor());
		g2.fillRect(0, 0, this.getCanvasWidth(), this.getTopHrz());
		
		g2.drawImage(bmpCel, 0,0, null);
			



		
		// posem la llum
		g2.drawImage(bmpSuperNova, this.superX - 100, this.superY - 100, null);

		// posem el terra
		g2.setColor(this.getBottomHrzColor());
		g2.fillRect(0, this.getTopHrz(), this.getCanvasWidth(), this
				.getCanvasHeigth()
				- this.getTopHrz());
		// posem la textura per tot el terra

		
		for (nx = 0; nx <= ratiox; nx++)
			for (ny = 0; ny <= ratioy; ny++) {
				if (this.isTextura())
					g2.drawImage(bmpTextura, nx * 200, this.getTopHrz()
							+ (ny * 200), null);
			}

		// ombra
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.black);
		g2.drawLine(this.getxPal(), this.getyPal() + this.getTopHrz(), this
				.gethPalx(), this.getTopHrz() + this.gethPaly());

		// pal
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
		g2.setColor(this.getColPal());
		g2.drawLine(this.getxPal(), this.getyPal() + this.getTopHrz(), this
				.getxPal(), this.getTopHrz() + this.getyPal()
				- this.getAlçada());
		g2.drawLine(this.getxPal() + 1, this.getyPal() + this.getTopHrz(), this
				.getxPal() + 1, this.getTopHrz() + this.getyPal()
				- this.getAlçada());

		// firma
		// pal
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g2.setColor(Color.white);
		g2.fillRect(0, this.getCanvasHeigth() + 1, this.getCanvasWidth(), 20);
		g2.setColor(Color.gray);
		g2.drawString(this.authorHrz, 2, this.getCanvasHeigth() + 15);
	

*/		
		g2.dispose();
		return (buf);
	}
}



