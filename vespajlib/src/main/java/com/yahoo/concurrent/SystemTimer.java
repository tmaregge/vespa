// Copyright Yahoo. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is an implementation of {@link Timer} that is backed by an actual system timer.
 *
 * @author Simon Thoresen Hult
 */
public enum SystemTimer implements Timer {

    INSTANCE;

    private volatile long millis;

    public static int detectHz() {
        Logger log = Logger.getLogger(SystemTimer.class.getName());
        String hzEnv = System.getenv("VESPA_TIMER_HZ");
        int hz = 1000;
        if ((hzEnv != null) && !hzEnv.isBlank()) {
            try {
                hz = Integer.parseInt(hzEnv);
            } catch (NumberFormatException e) {
                log.log(Level.WARNING, "Failed parsing VESPA_TIMER_HZ='" + hzEnv + "'", e);
            }
        };
        hz = Math.min(1000, Math.max(1, hz)); // Capping to valid range [1...1000]hz
        log.fine("vespa-system-timer running at " + hz + "hz. VESPA_TIMER_HZ='" + hzEnv + "'");
        return hz;
    }

    public static int adjustTimeoutByDetectedHz(int timeoutMS) {
        return (timeoutMS * 1000)/ detectHz();
    }

    SystemTimer() {
        int napTime = adjustTimeoutByDetectedHz(1);
        millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
        Thread thread = new Thread() {

            @Override
            public void run() {
                while (true) {
                    millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
                    try {
                        Thread.sleep(napTime);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.setName("vespa-system-timer");
        thread.start();
    }

    @Override
    public long milliTime() {
        return millis;
    }
}
