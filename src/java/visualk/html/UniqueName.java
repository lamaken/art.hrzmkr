/**
 * 
 */
package visualk.html;

import java.util.Random;

/**
 * @author alex
 *
 */
public class UniqueName {
	
	private String name;
	
	public String getName(){
		return(this.name);
	}

	private void genName(int size){
		String str=new  String("QAa0bcLdUK2eHfJgTP8XhiFj61DOklNm9nBoI5pGqYVrs3CtSuMZvwWx4yE7zR");
		StringBuffer sb=new StringBuffer();
		Random r = new Random();
		   int te=0;
		   for(int i=1;i<=size;i++){
			   te=r.nextInt(62);
			   sb.append(str.charAt(te));
		   }
		this.name = "u"+sb.toString();
		 
	    }
	     
	public UniqueName(int size){
		genName(size);
	}
}
