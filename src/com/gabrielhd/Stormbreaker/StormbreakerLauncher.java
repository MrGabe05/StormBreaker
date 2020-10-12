package com.gabrielhd.Stormbreaker;

import com.gabrielhd.Stormbreaker.option.Options;

public class StormbreakerLauncher
{
    public static void main(String... args) {
        Stormbreaker breaker = new Stormbreaker(Options.Builder.of(args));
        breaker.launch();
    }
}
