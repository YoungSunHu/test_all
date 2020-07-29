package com.hqy.concurrency08;

public final class ActionContext {

    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    //使用holder进行单例控制
    private static class ContextHolder {
        private final static ActionContext action_context = new ActionContext();
    }

    public static ActionContext getActionContext() {
        return ContextHolder.action_context;
    }

    public Context getContext() {
        return threadLocal.get();
    }
}
