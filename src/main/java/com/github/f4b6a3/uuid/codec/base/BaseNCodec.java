/*
 * MIT License
 * 
 * Copyright (c) 2018-2020 Fabio Lima
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.f4b6a3.uuid.codec.base;

import java.util.UUID;
import java.util.function.Function;

import com.github.f4b6a3.uuid.codec.UuidCodec;
import com.github.f4b6a3.uuid.codec.base.function.Base16Decoder;
import com.github.f4b6a3.uuid.codec.base.function.Base16Encoder;
import com.github.f4b6a3.uuid.codec.base.function.Base32Decoder;
import com.github.f4b6a3.uuid.codec.base.function.Base32Encoder;
import com.github.f4b6a3.uuid.codec.base.function.Base64Decoder;
import com.github.f4b6a3.uuid.codec.base.function.Base64Encoder;
import com.github.f4b6a3.uuid.exception.UuidCodecException;

/**
 * Abstract class that contains the basic functionality for base-n codecs of
 * this package.
 */
public abstract class BaseNCodec implements UuidCodec<String> {

	protected final BaseN base;
	protected final String alphabet;

	protected final Function<UUID, String> encoder;
	protected final Function<String, UUID> decoder;

	/**
	 * @param baseNAlphabet a enumeration that represents a supported alphabet.
	 */
	protected BaseNCodec(BaseNAlphabet baseNAlphabet) {
		this(baseNAlphabet.getBase(), baseNAlphabet.getAlphabet());
	}

	/**
	 * @param base     an enumeration that represents the base-n encoding
	 * @param alphabet a string that contains the base-n alphabet
	 */
	public BaseNCodec(BaseN base, String alphabet) {

		if (alphabet.length() != base.getNumber()) {
			throw new IllegalArgumentException(String.format("Invalid alphabet length: %s", alphabet.length()));
		}

		this.base = base;
		this.alphabet = alphabet;

		switch (base) {
		case BASE_16:
			encoder = new Base16Encoder(this.alphabet);
			decoder = new Base16Decoder(this.alphabet);
			break;
		case BASE_32:
			encoder = new Base32Encoder(this.alphabet);
			decoder = new Base32Decoder(this.alphabet);
			break;
		case BASE_64:
			encoder = new Base64Encoder(this.alphabet);
			decoder = new Base64Decoder(this.alphabet);
			break;
		default:
			throw new UuidCodecException("Unsupported base-n");
		}
	}

	@Override
	public String encode(UUID uuid) {
		return encoder.apply(uuid);
	}

	@Override
	public UUID decode(String string) {
		return decoder.apply(string);
	}

	public BaseN getBase() {
		return this.base;
	}

	public String getAlphabet() {
		return this.alphabet;
	}
}