package org.sodeja.parsec.examples.bp.expression;

import java.util.List;
import java.util.Map;

public class BeanPath {
	public final List<Expression> expressions;

	public BeanPath(final List<Expression> expressions) {
		this.expressions = expressions;
	}

	public Object read(Map<String, Object> ctx) {
		Object temp = null;
		for(Expression expression : expressions) {
			if(temp == null) {
				temp = expression.read(ctx);
			} else {
				temp = expression.read(ctx, temp);
			}
		}
		return temp;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Expression expr : expressions) {
			sb.append(expr);
			sb.append(".");
		}
		if(sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}
}
