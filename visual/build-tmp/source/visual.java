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

public class visual extends PApplet {

public boolean sketchFullScreen() {
  return true;
}

ArrayList<Node> nodes = new ArrayList<Node>();
Table table;
int id = 0;
int[] totalissues = new int[1];
int[] numtotaltitles = new int[0];
String[] totaltitles = new String[0];

float distance = 1000000.0f;
int closestnode = 0;

public void setup() {
  theSetup();
  table = loadTable("woningwet2.csv", "header");
  addLidNodes();
}

public void draw() {
  background(0,0,0);
  for (int i = 0; i<nodes.size(); i++) {
    Node n = nodes.get(i);
    n.update();
    n.display();

    float nodeDist = dist(mouseX,mouseY,n.xp,n.yp);
    if(nodeDist < distance) {
      distance = nodeDist;
      closestnode = i;
    }
  }
  stroke(0,0,100);
  Node dn = nodes.get(closestnode);
  line(mouseX,mouseY,dn.xp,dn.yp);
  text(str(dn.totaltitles), constrain(mouseX+10, 0, width-300), mouseY-90, 300, height);
  text(str(dn.titlenum), constrain(mouseX+10, 0, width-300), mouseY-70, 300, height);
  text(str(dn.yr), constrain(mouseX+10, 0, width-300), mouseY-50, 300, height);
  text(str(dn.issue), constrain(mouseX+10, 0, width-300), mouseY-30, 300, height);
  text(dn.title, constrain(mouseX+10, 0, width-300), mouseY-10, 300, height);
  text(dn.alinea, constrain(mouseX+10, 0, width-300), mouseY+20, 300, height);

  distance = 1000000.0f;
  closestnode = 0;
}
public void addLidNodes() {
	  for (TableRow row : table.rows()) {
    int yr = row.getInt("Year");
    int issue = row.getInt("Issue");
    if(id==0) totalissues[0] = issue;
    if(issue != totalissues[totalissues.length-1]) totalissues = append(totalissues, issue);
    
    int issuenum = totalissues.length;
    int editnr = row.getInt("Editnr");
    int totallids = row.getInt("Totallids");
    String title = row.getString("Title");
    
    if(editnr == 1) {
      if(id > 0) {
       numtotaltitles = append(numtotaltitles, totaltitles.length);
      }
      totaltitles = new String[1];
      totaltitles[totaltitles.length-1] = title;
    }
    if(title.equals(totaltitles[totaltitles.length-1]) == false) totaltitles = append(totaltitles, title);
    // println(totaltitles.length);
    int titlenum = totaltitles.length;

    String lidnrtxt = row.getString("Lidnr");
    int lidnr = PApplet.parseInt(lidnrtxt.substring(0, lidnrtxt.length() - 1));
    String alinea = row.getString("Alinea");
    new Node(true, id, yr, issue, issuenum, editnr, totallids, title, titlenum, lidnr, alinea);
    id++;
  }
  numtotaltitles = append(numtotaltitles, totaltitles.length);

  // println("total"+numtotaltitles);
  // println("---------------------------");
  for (int i = 0; i<nodes.size(); i++) {
    Node n = nodes.get(i);
    n.update_value_totalissues(totalissues.length);
    for(int j = 0; j < totalissues.length; j++) {
      if(n.issue == totalissues[j]) {
        n.update_value_totaltitles(numtotaltitles[j]);
      }
    }
  }
}
class Node {
  int id, yr, issue, issuenum, totalissues, titlenum, totaltitles, lidnr, editnr, totallids;
  float xp, yp, xOrigin, yOrigin;
  float radius = 25;
  String title, alinea;
  
  Node(boolean save, int id, int yr, int issue, int issuenum, int editnr, int totallids, String title, int titlenum, int lidnr, String alinea) {
    this.id = id;
    this.yr = yr;
    this.issue = issue;
    this.issuenum = issuenum;
    this.editnr = editnr;
    this.totallids = totallids;
    this.title = title;
    this.titlenum = titlenum;
    this.lidnr = lidnr;
    this.alinea = alinea;
    if(save)nodes.add(this);
  }
  
  public void update() {
    // if(id>0) {
    //   Node otherNode = (Node) nodes.get(id-1);
    //   if(otherNode.title.equals(title)) {
    //     offsetX += 50;
    //   }
    // }

    // xp = map(issuenum, 0, totalissues+1, 0, width) + lidnr*20;
    // yp = map(titlenum, 0, totaltitles+1, 0, height);
    xOrigin = map(issuenum, 0, totalissues+1, 0, width);
    yOrigin = map(titlenum, 0, totaltitles+1, 0, height);

    xp = xOrigin + sin(radians(lidnr*30))*(radius);;
    yp = yOrigin + cos(radians(lidnr*30))*(radius);
  }
  
  public void display() {
    fill(0,0,100);
    noStroke();
    ellipse(xp, yp, 3, 3);
    stroke(0,0,100);
    line(xp,yp,xOrigin,yOrigin);
  }

  public void update_value_totalissues(int totaliss) {
    this.totalissues = totaliss;
  }

  public void update_value_totaltitles(int totaltitles) {
    this.totaltitles = totaltitles;
  }
}
public void theSetup() {
  size(displayWidth, displayHeight);
  frameRate(60);
  colorMode(HSB, 360, 100, 100, 100);
  smooth(8);
  background(0);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "visual" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
