package com.eulerity.hackathon.imagefinder.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserAgents {
    Random rand;
    List<String> agents;
    List<String> referrers;

    public UserAgents() {
        rand = new Random(System.currentTimeMillis());
        agents = new ArrayList<>();
        referrers = new ArrayList<>();

        // list of common user agents taken from https://www.networkinghowtos.com/howto/common-user-agent-list/
        // maybe a bit outdated since from 2017?
        // don't really care which is which so I didn't bother labelling them
        agents.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.302" +
                "9.110 Safari/537.36");
        agents.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0");
        agents.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.270" +
                "4.79 Safari/537.36 Edge/14.14393");
        agents.add("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        agents.add("Mozilla/5.0 (Windows; U; MSIE 7.0; Windows NT 6.0; en-US)");
        agents.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.5" +
                "0727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
        agents.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0; Trident/5.0;  Trident/5.0)");
        agents.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0; MDDCJS)");
        agents.add("Mozilla/5.0 (compatible, MSIE 11, Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko");
        agents.add("Mozilla/5.0 (iPad; CPU OS 8_4_1 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Version/8." +
                "0 Mobile/12H321 Safari/600.1.4");
        agents.add("Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko)" +
                " Version/10.0 Mobile/14E304 Safari/602.1");
        agents.add("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
        agents.add("Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)");
        agents.add("Mozilla/5.0 (Linux; Android 6.0.1; SAMSUNG SM-G570Y Build/MMB29K) AppleWebKit/537.36 (KHTML, like" +
                " Gecko) SamsungBrowser/4.0 Chrome/44.0.2403.133 Mobile Safari/537.36");
        agents.add("Mozilla/5.0 (Linux; Android 5.0; SAMSUNG SM-N900 Build/LRX21V) AppleWebKit/537.36 (KHTML, like Ge" +
                "cko) SamsungBrowser/2.1 Chrome/34.0.1847.76 Mobile Safari/537.36");
        agents.add("Mozilla/5.0 (Linux; Android 6.0.1; SAMSUNG SM-N910F Build/MMB29M) AppleWebKit/537.36 (KHTML, like" +
                " Gecko) SamsungBrowser/4.0 Chrome/44.0.2403.133 Mobile Safari/537.36");
        agents.add("Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Galaxy Nexus Build/IML74K) AppleWebKit/535.7 (KHTML," +
                " like Gecko) CrMo/16.0.912.75 Mobile Safari/535.7");
        agents.add("Mozilla/5.0 (Linux; Android 7.0; HTC 10 Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chro" +
                "me/58.0.3029.83 Mobile Safari/537.36");
        agents.add("curl/7.35.0");
        agents.add("Wget/1.15 (linux-gnu)");
        agents.add("Lynx/2.8.8pre.4 libwww-FM/2.14 SSL-MM/1.4.1 GNUTLS/2.12.23");

        // same but for referrers
        referrers.add("http://google.com/");
        referrers.add("http://bing.com/");
        referrers.add("http://duckduckgo.com/");
        referrers.add("http://yahoo.com/");
        referrers.add("http://reddit.com/");
        referrers.add("http://facebook.com/");
        referrers.add("http://pintrest.com/");
    }

    public String getAgent() {
        int index = rand.nextInt(agents.size());

        return agents.get(index);
    }

    public String getReferrer() {
        int index = rand.nextInt(referrers.size());

        return referrers.get(index);
    }
}
