package com.game.framework.asyncdb;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liguorui
 * @date 2018/1/15 01:00
 */
public enum Operation {

    INSERT(AsynDBState.INSERT, new AsynDBState[] {AsynDBState.NOMAL}, (AsynDBState[]) null),

    DELETE(AsynDBState.DELETE, new AsynDBState[] {AsynDBState.NOMAL, AsynDBState.INSERT, AsynDBState.UPDATE},
            new AsynDBState[] {AsynDBState.DELETE}),

    UPDATE(AsynDBState.UPDATE, new AsynDBState[] {AsynDBState.NOMAL},
            new AsynDBState[] {AsynDBState.UPDATE, AsynDBState.INSERT}),
    ;


    public final AsynDBState STATE;
    private final Set<AsynDBState> NEED_CHANGE_OPERATION;
    private final Set<AsynDBState> CAN_OPERATION;

    private Operation(AsynDBState STATE, AsynDBState[] needChange, AsynDBState[] canOperations) {
        this.STATE = STATE;
        Set<AsynDBState> CAN_OPERATION = new HashSet<AsynDBState>();
        Set<AsynDBState> NEED_CHANGE_OPERATION = new HashSet<AsynDBState>();
        AsynDBState state;
        int var9;
        int var10;
        AsynDBState[] var11;
        if (needChange != null) {
            var11 = needChange;
            var10 = needChange.length;

            for (var9 = 0; var9 < var10; ++var9) {
                state = var11[var9];
                CAN_OPERATION.add(state);
                NEED_CHANGE_OPERATION.add(state);
            }
        }

        if (canOperations != null) {
            var11 = canOperations;
            var10 = canOperations.length;

            for (var9 = 0; var9 < var10; ++var9) {
                state = var11[var9];
                CAN_OPERATION.add(state);
            }
        }

        this.CAN_OPERATION = Collections.unmodifiableSet(CAN_OPERATION);
        this.NEED_CHANGE_OPERATION = Collections.unmodifiableSet(NEED_CHANGE_OPERATION);
    }

    public boolean isCanOperationAt(AsynDBState currentState) {
        return this.CAN_OPERATION.contains(currentState);
    }

    public boolean isNeedToChangeAt(AsynDBState currentState) {
        return this.NEED_CHANGE_OPERATION.contains(currentState);
    }
}
