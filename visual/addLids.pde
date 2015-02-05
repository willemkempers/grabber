void addLidNodes() {
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
    int lidnr = int(lidnrtxt.substring(0, lidnrtxt.length() - 1));
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