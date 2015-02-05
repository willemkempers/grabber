import os, glob, string
from lxml import etree

def write_line(f, year, issue, changenr, totallids, title, lidnr, alinea):
	# massage our data to get it ready for output, any further character replacements go here
	year    = year.strip().replace('\n', ' ')
	issue   = issue.strip().replace('\n', ' ')
	ttitle  = title.strip().replace('\n', ' ')
	#print ttitle
	lidnr   = lidnr.strip().replace('\n', ' ')
	alinea  = alinea.strip().replace('\n', ' ')

	f.write( "\"{0}\",\"{1}\",\"{2}\",\"{3}\",\"{4}\",\"{5}\",\"{6}\"\n".format(year, issue, changenr, totallids, ttitle, lidnr, alinea) )

def parse_stadsblad(fname):
	print "Now processing file", fname
	dom = etree.parse(fname)

	# get staatsblad issue and year
	stb = dom.xpath('//stb')
	chs = stb[0].getchildren()
	year = chs[1].text
	issue = chs[2].text
	print "STAATSBLAD ", year, issue

	# get all the titles
	wlids = dom.xpath('//wlid')
	for wlid in wlids:
		titlee = wlid.xpath('al')
		#print "title: ", titlee[0].text.encode('utf-8')
		title = titlee[0].text.encode('utf-8')

		# predefine variables
		lidnr = ""
		alinea = ""

		with open("{0}.txt".format(fname), "w+") as f:			
			# get all the 'lids'
			lids = wlid.xpath('//lid')

			totallids = 0
			for lid in lids:
				totallids+=1

			changenr = 0;
			for lid in lids:
				changenr += 1
				chs = lid.getchildren()
				for c in chs:
					if (c.tag == 'nr'):
						#print "lidnr: ", c.text.encode('utf-8')
						lidnr = c.text.encode('utf-8')
					if (c.tag == 'al'):
						#print "alinea: ", c.text.encode('utf-8')
						alinea += c.text.encode('utf-8')
					
				write_line(f, year, issue, changenr, totallids, title, lidnr, alinea)
				alinea = ""

			totallids = 0
			changenr = 0

def main():
	os.chdir("data/")
	for file in glob.glob("*.xml"):
	    parse_stadsblad(file)
	    print("="*80)

if __name__ == "__main__":
	main()
