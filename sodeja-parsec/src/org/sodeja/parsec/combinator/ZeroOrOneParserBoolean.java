package org.sodeja.parsec.combinator;

import org.sodeja.collections.ConsList;
import org.sodeja.parsec.AbstractParser;
import org.sodeja.parsec.ParseSuccess;
import org.sodeja.parsec.Parser;
import org.sodeja.parsec.ParsingResult;

public class ZeroOrOneParserBoolean<Tok> extends AbstractParser<Tok, Boolean> {

	private final Parser<Tok, ?> subparser;
	
	public ZeroOrOneParserBoolean(String name, Parser<Tok, ?> subparser) {
		super(name);
		this.subparser = subparser;
	}

	@Override
	public ParsingResult<Tok, Boolean> execute(ConsList<Tok> tokens) {
		return executeDelegate(tokens);
	}

	@Override
	protected ParsingResult<Tok, Boolean> executeDelegate(ConsList<Tok> tokens) {
		ParsingResult<Tok, ?> result = subparser.execute(tokens);
		if(isSuccess(result)) {
			return success(Boolean.TRUE, ((ParseSuccess<Tok, ?>) result).tokens);
		}

		return success(Boolean.FALSE, tokens);
	}
}
