boolean sketchFullScreen() {
  return true;
}

ArrayList<Node> nodes = new ArrayList<Node>();
Table table;
int id = 0;
int[] totalissues = new int[1];
int[] numtotaltitles = new int[0];
String[] totaltitles = new String[0];

float distance = 1000000.0;
int closestnode = 0;

void setup() {
  theSetup();
  table = loadTable("woningwet2.csv", "header");
  addLidNodes();
}

void draw() {
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

  distance = 1000000.0;
  closestnode = 0;
}