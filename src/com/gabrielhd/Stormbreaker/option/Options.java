package com.gabrielhd.Stormbreaker.option;

import java.util.HashMap;
import java.util.Map;

public class Options
{
    private Map<String, Object> options;
    
    public Options() {
        this.options = new HashMap<String, Object>();
    }
    
    public boolean isOption(String id) {
        return this.options.containsKey(id);
    }
    
    public <T> T getOption(String id, T defaultValue) {
        return (T)this.options.getOrDefault(id, defaultValue);
    }
    
    public Map<String, Object> getOptions() {
        return this.options;
    }
    
    public static class Builder
    {
        public static Options of(String... args) {
            Options options = new Options();
            for (String arg : args) {
                String[] part = arg.split("=");
                if (part.length > 1) {
                    Object value = part[1];
                    try {
                        value = Integer.parseInt((String)value);
                    }
                    catch (Exception ex) {}
                    Label_0110: {
                        if (!(value instanceof String) || !value.equals("true")) {
                            if (!value.equals("false")) {
                                break Label_0110;
                            }
                        }
                        try {
                            value = Boolean.parseBoolean((String)value);
                        }
                        catch (Exception ex2) {}
                    }
                    options.options.put(part[0].replace("=", ""), value);
                }
            }
            return options;
        }
    }
}
