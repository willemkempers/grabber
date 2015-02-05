XML xml;

void setup() {
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