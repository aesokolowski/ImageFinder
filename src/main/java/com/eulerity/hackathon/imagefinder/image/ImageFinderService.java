package com.eulerity.hackathon.imagefinder.image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageFinderService {
    private String userAgent;
    private String referrer;
    private Document doc;
    private List<String> imageSources;
    private Set<String> pageSources;
    private String url;
    private String shortUrl;
    private UserAgents factory;

    public ImageFinderService() {
        factory = new UserAgents();
    }

    public List<String> scrape(String path, String url) throws IOException, InterruptedException {
        userAgent = factory.getAgent();
        referrer = factory.getReferrer();
        imageSources = new ArrayList<>();
        pageSources = new TreeSet<>();
        // add '/' to end of url if not already there
        this.url = url.charAt(url.length() - 1) == '/' ? url : url + "/";
        if (this.url.length() > 12) {
            if (this.url.substring(0, 7).equals("http://")) {
                shortUrl = this.url.substring(7);
            } else if (this.url.substring(0, 8).equals("https://")) {
                shortUrl = this.url.substring(8);
            }
        }
       
        //System.out.println("shortUrl: " + shortUrl);
        //System.out.println("path: " + path + "\nurl: " + this.url);  // test, take out when actual scraping is implemented

        doc = Jsoup.connect(this.url).timeout(4500).userAgent(userAgent).referrer(referrer).get();
        addSources("img", "src", imageSources);
        addSources("a", "href", pageSources);

        System.out.println(pageSources);

        for (String ps : pageSources) {
            Thread.sleep(2100);
            // not sure if this actually helps prevent getting 403'd for some sites:
            userAgent = factory.getAgent();
            referrer = factory.getReferrer();
            
            System.out.println(ps);
            if (ps.contains(shortUrl) && !ps.contains("javascript:")) {
                doc = Jsoup.connect(ps).timeout(45000).userAgent(userAgent).referrer(referrer).get();
            }
            
            addSources("img", "src", imageSources);
        }

        return imageSources;
    }

    private void addSources(String type, String attribute, Collection<String> sources) throws InterruptedException {
        Elements els = doc.select(type);

        for (Element e : els) {
            String derived = e.attr(attribute);

            //System.out.println("before: " + derived);
            // remove initial / if present in relative URL
            if (derived.length() >= 2 &&derived.charAt(0) == '/' && derived.charAt(1) != '/') {
                derived = derived.substring(1);
            }

            // to cope with images not stored at $URL/$IMGURL
            if (derived.length() >= 4) {
                // make relative addresses absolute if possible:
                derived = derived.substring(0, 4).equals("http") || derived.substring(0, 2).equals("//") ?
                        derived : url + derived;
                // add http: to addresses that start with //
                derived = derived.substring(0,2).equals("//") ? "http:" + derived: derived;
            }

            System.out.println("adding source to collection: " + derived);
            if (!(derived.equals("") || derived.equals("#"))) {
                sources.add(derived);
            }
        }
    }
}
