package com.cly.scanlibrary.tasks;

import com.cly.scanlibrary.utils.VerificationPool;

import java.util.Random;

import static com.cly.scanlibrary.utils.Log.*;

/**
 * 校验条码是否重复的任务
 * Created by 丛龙宇 on 2017/8/16.
 */

public class VerificationRepeat extends Verification {

    public VerificationRepeat(VerificationPool p) {
        super(p);
    }

    @Override
    protected void performService() {
        final Random random = new Random();
        boolean state = random.nextBoolean();
        d(this + "--> updateRepeat:state = " + state);
        if (state)
            assembler.scanDatas().repeat();
    }
}
