package com.javafera.javascript.executor;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public final class JavaScript {
	
	private static Context context;
	private static Scriptable scope;
	
	private JavaScript(){}
	
	public static Object execute(String script) {
		initContext();
		return process(script, scope);
	}
	
	public static Object execute(String script, Map<String, Object> params) {
		initContext();
		Set<Entry<String, Object>> entrySet = params.entrySet();
		
		for(Entry<String, Object> param: entrySet){
			ScriptableObject.putProperty(scope, param.getKey(), param.getValue());
		}
		
		return process(script, scope);
	}

	private static void initContext() {
		context = Context.enter();
		scope = context.initStandardObjects();
	}
	

	private static Object process(String script, Scriptable scope) {
		Object result = context.evaluateString(scope, script, "JavaScript", 1, null);
		Context.exit();
		return result;
	}
}