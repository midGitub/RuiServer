package com.game.framework.asyncdb;

/**
 * @author liguorui
 * @date 2018/1/15 00:49
 */
public enum AsynDBState {

    NOMAL() {
        @Override
        public boolean doOperation(Synchronizer<AsynDBEntity> synchronizer, AsynDBEntity asynDBEntity) {
            return true;
        }
    },

    DELETED() {
        @Override
        public boolean doOperation(Synchronizer<AsynDBEntity> synchronizer, AsynDBEntity asynDBEntity) {
            return true;
        }
    },

    DELETE() {
        @Override
        public boolean doOperation(Synchronizer<AsynDBEntity> synchronizer, AsynDBEntity asynDBEntity) {
            try {
                return synchronizer.deleteData(asynDBEntity);
            } catch (Exception var4) {
                throw new SyncException(String.format("%s", asynDBEntity.getClass().getSimpleName()), var4);
            }
        }
    },

    UPDATE() {
        @Override
        public boolean doOperation(Synchronizer<AsynDBEntity> synchronizer, AsynDBEntity asynDBEntity) {
            try {
                return synchronizer.updateData(asynDBEntity);
            } catch (Exception var4) {
                throw new SyncException(String.format("%s", asynDBEntity.getClass().getSimpleName()), var4);
            }
        }
    },

    INSERT() {
        @Override
        public boolean doOperation(Synchronizer<AsynDBEntity> synchronizer, AsynDBEntity asynDBEntity) {
            try {
                return synchronizer.insertData(asynDBEntity);
            } catch (Exception var4) {
                throw new SyncException(String.format("%s", asynDBEntity.getClass().getSimpleName()), var4);
            }
        }
    },
    ;

    public abstract boolean doOperation(Synchronizer<AsynDBEntity> var1, AsynDBEntity var2);
}
