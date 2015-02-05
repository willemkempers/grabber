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
  
  void update() {
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
  
  void display() {
    fill(0,0,100);
    noStroke();
    ellipse(xp, yp, 3, 3);
    stroke(0,0,100);
    line(xp,yp,xOrigin,yOrigin);
  }

  void update_value_totalissues(int totaliss) {
    this.totalissues = totaliss;
  }

  void update_value_totaltitles(int totaltitles) {
    this.totaltitles = totaltitles;
  }
}