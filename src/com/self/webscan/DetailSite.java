package com.self.webscan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class DetailSite {

	    private Map<String, List<String>> domainLinks;
	    private Set<String> extDomainLinks;
	    

	    public DetailSite(Map<String, List<String>> links) {
	        this.domainLinks = links;
	    }

	    public DetailSite() {
	        this.domainLinks = new HashMap<String, List<String>>();
	    }

	    public Map<String, List<String>> getLinks() {
	        return domainLinks;
	    }

	    public void addLink(String url, String link) {
	        if(domainLinks.containsKey(url)) {
	            domainLinks.get(url).add(link);
	        } else {
	            domainLinks.put(url, new ArrayList<String>());
	        }
	    }

	    public void addLinks(String url, List<String> links) {
	        this.domainLinks.put(url, links);
	    }

	    public void addLinks(String url, Set<String> links) {
	        this.addLinks(url, new ArrayList<String>(links));
	    }

	    public void addExternalLinks(Set<String> extLinks) {
	        extDomainLinks.addAll(extLinks);
	    }


	
}
