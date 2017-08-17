package com.cly.scanlibrary.tasks;

import com.cly.scanlibrary.utils.VerificationPool;

import java.util.Random;

import static com.cly.scanlibrary.utils.Log.*;

/**
 * 校验条码是否符合规则的任务
 * Created by 丛龙宇 on 2017/8/16.
 */

public class VerificationTerminus extends Verification {
    public VerificationTerminus(VerificationPool p) {
        super(p);
    }

    @Override
    protected void performService() {
        final Random random = new Random(25);
        boolean state = random.nextBoolean();
        d(this +  "--> updateTerminus:state = " + state);
        assembler.scanDatas().updateTerminus(state);
    }
}
