package org.sodeja.parsec.standart;

import org.sodeja.functional.Predicate1;
import org.sodeja.math.Rational;
import org.sodeja.parsec.Parser;
import org.sodeja.parsec.combinator.SatisfiesParser;

public class StandartParsers {
	private StandartParsers() {
	}
	
	public static Parser<String, String> justString(final String name) {
		return new StringParser(name);
	}

	public static Parser<String, String> justStartEnd(final String name, final String startEnd) {
		return new StartEndParser(name, startEnd);
	}
	
	public static Parser<String, String> justText(final String name) {
		return new SatisfiesParser<String>(name + "_PARSER", new Predicate1<String>() {
			public Boolean execute(String p) {
				return true;
			}
		});
	}
	
	public static Parser<String, String> literal(final String match) {
		return new SatisfiesParser<String>(match + "_PARSER", new Predicate1<String>() {
			public Boolean execute(String p) {
				return p.equals(match);
			}
		});
	}
	
	public static Parser<String, String> javaIdentifierStart(final String name) {
		return new SatisfiesParser<String>(name, new Predicate1<String>(){
			public Boolean execute(String p) {
				return Character.isJavaIdentifierStart(p.charAt(0));
			}
		});
	}
	
	public static Parser<String, String> javaIdentifier(final String name) {
		return new SatisfiesParser<String>(name, new Predicate1<String>() {
			public Boolean execute(String p) {
				if(! Character.isJavaIdentifierStart(p.charAt(0))) {
					return false;
				}
				
				for(int i = 1, n = p.length();i < n;i++) {
					if(! Character.isJavaIdentifierPart(p.charAt(i))) {
						return false;
					}
				}
				
				return true;
			}
		});
	}
	
	public static Parser<String, Integer> simpleIntegerParser(final String name) {
		return new SimpleIntegerParser(name);
	}
	
	public static Parser<String, String> alpha(final String name) {
		return new CharByCharSatisfiesParser(name) {
			@Override
			protected boolean checkChar(char ch) {
				return Character.isLetter(ch);
			}
		};
	}
	
	public static Parser<String, String> digits(final String name) {
		return new CharByCharSatisfiesParser(name) {
			@Override
			protected boolean checkChar(char ch) {
				return Character.isDigit(ch);
			}
		};
	}

	public static Parser<String, String> alphaDigits(final String name) {
		return new CharByCharSatisfiesParser(name) {
			@Override
			protected boolean checkChar(char ch) {
				return Character.isLetterOrDigit(ch);
			}
		};
	}

	public static Parser<String, String> alphaDigitsUnderscore(final String name) {
		return new CharByCharSatisfiesParser(name) {
			@Override
			protected boolean checkChar(char ch) {
				return Character.isLetterOrDigit(ch) || (ch == '_');
			}
		};
	}
	
	public static Parser<String, Rational> rational(final String name) {
		return new RationalParser(name);
	}
}
