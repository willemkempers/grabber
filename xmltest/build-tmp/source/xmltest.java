import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class xmltest extends PApplet {

XML xml;

public void setup() {
  xml = loadXML("data.xml");
  XML[] children = xml.getChildren("wlid");

  for (int i = 0; i < children.length; i++) {
  	XML kop = children[i].getChild("kop");
  	XML kopnr = kop.getChild("nr");
    println(kopnr.getContent());

    XML al = children[i].getChild("al");
    println(al.getContent());

    try {
    	XML arttext = children[i].getChild("arttkst");
	  	XML lid = arttext.getChild("lid");
	  	XML lidnr = lid.getChild("nr");
	  	String[] lids = {};
  		try {
  			XML[] lidal = lid.getChildren("al");
  			for (int j = 0; j < lidal.length; j++) {
			    lids = append(lids, lidal[j].getContent());
			  }
  		} catch (Exception e) {
  			
  		}

	  	println(lidnr.getContent());
	  	 println(lids);

    } catch (Exception e) {
    	
    }

   //  try {
   //  	XML wlichaam = children[i].getChild("wlichaam");
	  // 	XML art = wlichaam.getChild("art");
	  // 	XML[] wllids = art.getChildren("lid");

			// for (int j = 0; j < wllids.length; j++) {
			// 	XML wlnr = wllids[j].getChild("nr");
			// 	XML wlal = wllids[j].getChild("al");
	  // 		println(wlnr.getContent() + wlal.getContent());
	  // 	}
   //  } catch (Exception e) {
    	
   //  }
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "xmltest" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
