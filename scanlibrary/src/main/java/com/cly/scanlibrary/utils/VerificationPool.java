package com.cly.scanlibrary.utils;

import com.cly.scanlibrary.tasks.Assembler;
import com.cly.scanlibrary.tasks.Verification;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 丛龙宇 on 2017/8/16.
 */

public class VerificationPool {

    private Set<Verification> pool = new HashSet<>();

    public synchronized void add(Verification v) {
        pool.add(v);
        notifyAll();
    }
    public synchronized void hire(Class<? extends Verification> robotType, Assembler d)
            throws InterruptedException {
        for(Verification v : pool)
            if(v.getClass().equals(robotType)) {
                pool.remove(v);
                v.assignAssembler(d);
                v.engage(); // Power it up to do the task
                return;
            }
        wait(); // None available
        hire(robotType, d); // Try again, recursively
    }
    public synchronized void release(Verification v) { add(v); }


}
