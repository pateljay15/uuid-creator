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

package com.github.f4b6a3.uuid.codec.base.function;

import java.util.UUID;

import com.github.f4b6a3.uuid.codec.base.BaseN;

/**
 * Function that encodes a UUID to a base-16 string.
 * 
 * It encodes in lower case only.
 * 
 * See: https://tools.ietf.org/html/rfc4648
 */
public final class Base16Encoder extends BaseNEncoder {

	/**
	 * @param alphabet a string that contains the base-n alphabet
	 */
	public Base16Encoder(String alphabet) {
		super(BaseN.BASE_16, alphabet);
	}

	@Override
	public String apply(UUID uuid) {

		final char[] chars = new char[base.getLength()];
		final long msb = uuid.getMostSignificantBits();
		final long lsb = uuid.getLeastSignificantBits();

		chars[0x00] = alphabet[(int) (msb >>> 0x3c & 0xf)];
		chars[0x01] = alphabet[(int) (msb >>> 0x38 & 0xf)];
		chars[0x02] = alphabet[(int) (msb >>> 0x34 & 0xf)];
		chars[0x03] = alphabet[(int) (msb >>> 0x30 & 0xf)];
		chars[0x04] = alphabet[(int) (msb >>> 0x2c & 0xf)];
		chars[0x05] = alphabet[(int) (msb >>> 0x28 & 0xf)];
		chars[0x06] = alphabet[(int) (msb >>> 0x24 & 0xf)];
		chars[0x07] = alphabet[(int) (msb >>> 0x20 & 0xf)];
		chars[0x08] = alphabet[(int) (msb >>> 0x1c & 0xf)];
		chars[0x09] = alphabet[(int) (msb >>> 0x18 & 0xf)];
		chars[0x0a] = alphabet[(int) (msb >>> 0x14 & 0xf)];
		chars[0x0b] = alphabet[(int) (msb >>> 0x10 & 0xf)];
		chars[0x0c] = alphabet[(int) (msb >>> 0x0c & 0xf)];
		chars[0x0d] = alphabet[(int) (msb >>> 0x08 & 0xf)];
		chars[0x0e] = alphabet[(int) (msb >>> 0x04 & 0xf)];
		chars[0x0f] = alphabet[(int) (msb & 0xf)];

		chars[0x10] = alphabet[(int) (lsb >>> 0x3c & 0xf)];
		chars[0x11] = alphabet[(int) (lsb >>> 0x38 & 0xf)];
		chars[0x12] = alphabet[(int) (lsb >>> 0x34 & 0xf)];
		chars[0x13] = alphabet[(int) (lsb >>> 0x30 & 0xf)];
		chars[0x14] = alphabet[(int) (lsb >>> 0x2c & 0xf)];
		chars[0x15] = alphabet[(int) (lsb >>> 0x28 & 0xf)];
		chars[0x16] = alphabet[(int) (lsb >>> 0x24 & 0xf)];
		chars[0x17] = alphabet[(int) (lsb >>> 0x20 & 0xf)];
		chars[0x18] = alphabet[(int) (lsb >>> 0x1c & 0xf)];
		chars[0x19] = alphabet[(int) (lsb >>> 0x18 & 0xf)];
		chars[0x1a] = alphabet[(int) (lsb >>> 0x14 & 0xf)];
		chars[0x1b] = alphabet[(int) (lsb >>> 0x10 & 0xf)];
		chars[0x1c] = alphabet[(int) (lsb >>> 0x0c & 0xf)];
		chars[0x1d] = alphabet[(int) (lsb >>> 0x08 & 0xf)];
		chars[0x1e] = alphabet[(int) (lsb >>> 0x04 & 0xf)];
		chars[0x1f] = alphabet[(int) (lsb & 0xf)];

		return new String(chars);
	}
}