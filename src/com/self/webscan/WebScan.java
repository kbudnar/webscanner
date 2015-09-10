package com.self.webscan;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScan {

	
    private Queue<String> urlsToScan;
    private String url;
    private HashSet<String> scannedUrls;
    private DetailSite detailSite;

    public WebScan(String url) {
        this.url = url;
        this.urlsToScan = new LinkedList<String>();
        this.urlsToScan.add(url);
        this.scannedUrls = new HashSet<String>();
    }
    
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a URL to be crawled");
	    String domainurl = in.nextLine();
        WebScan webscan = new WebScan(domainurl);
        DetailSite detailSite = webscan.getSiteLinks();
        System.out.println("pages visited: " + detailSite.getLinks().keySet().size());
        Iterator<String> it = detailSite.getLinks().keySet().iterator();
        String keyTemp;
        while (it.hasNext()) {
            keyTemp = it.next();
            System.out.println("\n"+keyTemp);
            for(String s : detailSite.getLinks().get(keyTemp)) {
                System.out.println("-- "+s);
            }

        }
	        
	    }

	   
	    public void processPage(String url)  {
			
				//get useful information
	    		Set<String> domainLinks = new HashSet<String>();
	    		
	    		Document doc = null;
				try {
					doc = Jsoup.connect(url).get();
				
			    Elements links = doc.select("a[href]");
		        
		        for(Element link: links){
	                if(link.attr("href").contains(this.url)) {
	                	domainLinks.add(link.attr("href"));
	                    this.urlsToScan.add(link.attr("href"));
	                } 

	            }
	            //DetailSite to be updated
	            this.detailSite.addLinks(url, domainLinks);
	         
	            organiseUrls(url);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 
			}
	    
	    private void organiseUrls (String url) {
	    	scannedUrls.add(url);
	        this.urlsToScan = new LinkedList<String>(this.urlsToScan);
	        this.urlsToScan.removeAll(scannedUrls);
	        System.out.println(this.urlsToScan.size() + " Links to be Scanned");
	    }
	    
	    public DetailSite getSiteLinks () throws IOException {
	        detailSite = new DetailSite();
	        for (String nextUrl; (nextUrl = urlsToScan.poll()) != null;){
	            processPage(nextUrl);
	        }
	        return this.detailSite;
	    }
	}


