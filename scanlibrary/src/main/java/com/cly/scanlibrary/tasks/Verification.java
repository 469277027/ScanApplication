package com.cly.scanlibrary.tasks;

import com.cly.scanlibrary.entity.ScanDatas;
import com.cly.scanlibrary.utils.VerificationPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

import static com.cly.scanlibrary.utils.Log.*;

/**
 * 校验条码任务的基类
 * Created by 丛龙宇 on 2017/8/16.
 */

public abstract class Verification implements Runnable {

    private VerificationPool pool;

    public Verification(VerificationPool p) {
        pool = p;
    }

    protected List<ScanDatas> list = new ArrayList<>();
    protected Assembler assembler;

    public Verification assignAssembler(Assembler assembler) {
        this.assembler = assembler;
        return this;
    }

    private boolean engage = false;

    public synchronized void engage() {
        engage = true;
        notifyAll();
    }

    abstract protected void performService();

    public void run() {
        try {
            powerDown(); // Wait until needed
            while (!Thread.interrupted()) {
                performService();
                assembler.barrier().await(); // Synchronize
                // We're done with that job...
                powerDown();
            }
        } catch (InterruptedException e) {
            d("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        d(this + " off");
    }

    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        assembler = null; // Disconnect from the Assembler
        // Put ourselves back in the available pool:
        pool.release(this);
        while (!engage)  // Power down
            wait();
    }

    public String toString() {
        return getClass().getName();
    }
}
