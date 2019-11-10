package io.github.chloedawn.gamerules;

import java.util.NoSuchElementException;

final class NoSuchRuleException extends NoSuchElementException {
	NoSuchRuleException(final String rule) {
		super(rule);
	}
}
