package com.ed.libsutils.assertj.assertions;

import static org.assertj.core.api.Fail.fail;

public class NoNpeAssert {
	public static void assertThatNPENotThrown(Runnable runnable) {
		try {
			runnable.run();
		} catch (NullPointerException e) {
			fail("Expected to not throw NPE", e);
		}
	}
}
