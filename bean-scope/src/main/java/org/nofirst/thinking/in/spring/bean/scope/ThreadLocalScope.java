package org.nofirst.thinking.in.spring.bean.scope;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import reactor.util.annotation.NonNull;

/**
 * ThreadLocal 级别的 Scope
 */
public class ThreadLocalScope implements Scope {

    public static final String SCOPE_NAME = "thread-local";

    private final NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal("thread-local-scope") {

        public Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {

        // 这里是非空的
        Map<String, Object> context = getContext();

        Object object = context.get(name);
        if (object == null) {
            object = objectFactory.getObject();
            context.put(name, object);
        }

        return object;
    }

    @NonNull
    private Map<String, Object> getContext() {
        return threadLocal.get();
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> context = getContext();

        return context.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // todo
    }

    @Override
    public Object resolveContextualObject(String key) {
        Map<String, Object> context = getContext();

        return context.get(key);
    }

    @Override
    public String getConversationId() {
        Thread thread = Thread.currentThread();

        return String.valueOf(thread.getId());
    }
}
