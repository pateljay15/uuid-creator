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

package com.github.f4b6a3.uuid.codec.uuid;

import java.util.UUID;

import com.github.f4b6a3.uuid.codec.UuidCodec;
import com.github.f4b6a3.uuid.util.UuidUtil;

import static com.github.f4b6a3.uuid.codec.uuid.DotNetGuid1Codec.toAndFromDotNetGuid;

public class DotNetGuid4Codec implements UuidCodec<UUID> {

	/**
	 * Codec for .Net Guids.
	 * 
	 * This codec converts a random-based UUID (v4) to a .Net Guid.
	 * 
	 * It rearranges the most significant bytes from big-endian to little-endian,
	 * and vice-versa.
	 * 
	 * The .Net Guid stores the most significant bytes as little-endian, while the
	 * least significant bytes are stored as big-endian (network order).
	 * 
	 * @param uuid a UUID
	 * @return another UUID
	 */
	@Override
	public UUID encode(UUID uuid) {
		if (!UuidUtil.isRandomBased(uuid)) {
			throw new IllegalArgumentException(String.format("Not a random-based UUID: %s.", uuid.toString()));
		}
		return toAndFromDotNetGuid(uuid);
	}

	/**
	 * Convert a .Net Guid to a random-based UUID (v4).
	 * 
	 * It rearranges the most significant bytes from big-endian to little-endian,
	 * and vice-versa.
	 * 
	 * The .Net Guid stores the most significant bytes as little-endian, while the
	 * least significant bytes are stored as big-endian (network order).
	 * 
	 * @param uuid a UUID
	 * @return another UUID
	 */
	@Override
	public UUID decode(UUID uuid) {
		UUID uuidv1 = toAndFromDotNetGuid(uuid);
		if (!UuidUtil.isRandomBased(uuidv1)) {
			throw new IllegalArgumentException(String.format("Not a random-based UUID: %s.", uuidv1.toString()));
		}
		return uuidv1;
	}
}