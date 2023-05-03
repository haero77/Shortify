package com.haero77.urlshortener.domain.url.util;

public class Base62Encoder {

	private static final int BASE = 62;
	private static final String CODEC = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private Base62Encoder() {
	}

	public static String encode(long target) {
		StringBuilder encoded = new StringBuilder();

		do {
			int mod = Math.toIntExact(target % BASE);
			encoded.insert(0, CODEC.charAt(mod));
			target /= BASE;
		} while (target != 0);

		return encoded.toString();
	}
}
