package com.github.f4b6a3.uuid.creator.rfc4122;

import org.junit.Test;

import com.github.f4b6a3.uuid.UuidCreator;
import com.github.f4b6a3.uuid.creator.AbstractUuidCreatorTest;
import com.github.f4b6a3.uuid.creator.rfc4122.NameBasedMd5UuidCreator;
import com.github.f4b6a3.uuid.enums.UuidNamespace;
import com.github.f4b6a3.uuid.enums.UuidVersion;

import static com.github.f4b6a3.uuid.util.ByteUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

public class NameBasedMd5UuidCreatorTest extends AbstractUuidCreatorTest {

	@Test
	public void testNameBasedMd5Uuid() {

		UUID[] list = new UUID[DEFAULT_LOOP_MAX];
		NameBasedMd5UuidCreator creator = UuidCreator.getNameBasedMd5Creator();

		byte[] name;

		for (int i = 0; i < DEFAULT_LOOP_MAX; i++) {
			name = ("url" + i).getBytes();
			list[i] = creator.create(UuidNamespace.NAMESPACE_URL.getValue(), name);
		}

		checkNullOrInvalid(list);
		checkUniqueness(list);
		checkVersion(list, UuidVersion.VERSION_NAME_BASED_MD5.getValue());

		for (int i = 0; i < DEFAULT_LOOP_MAX; i++) {
			name = ("url" + i).getBytes();
			UUID other = creator.create(UuidNamespace.NAMESPACE_URL.getValue(), name);
			assertTrue("Two different MD5 UUIDs for the same input", list[i].equals(other));
		}
	}

	@Test
	public void testGetNameBasedMd5NamespaceUrlAndSiteGithub() {

		UuidNamespace namespace = UuidNamespace.NAMESPACE_DNS;
		String name = GITHUB_URL;

		String uuidString1 = "d85b3e68-c422-3cfc-b1ea-b58b6d8dfad0"; // generated by MD5SUM (gnu-coreutils)
		String uuidString2 = "2c02fba1-0794-3c12-b62b-578ec5f03908"; // generated by UUIDGEN (util-linux)

		UUID uuid3 = UUID.fromString(uuidString1);
		UUID uuid4 = UuidCreator.getNameBasedMd5(name);
		assertEquals(uuid3, uuid4);

		UUID uuid1 = UUID.fromString(uuidString2);
		UUID uuid2 = UuidCreator.getNameBasedMd5(namespace, name);
		assertEquals(uuid1, uuid2);

		NameBasedMd5UuidCreator creator1 = UuidCreator.getNameBasedMd5Creator().withNamespace(namespace);
		UUID uuid5 = UUID.fromString(uuidString2);
		UUID uuid6 = creator1.create(name);
		assertEquals(uuid5, uuid6);
	}

	@Test
	public void testGetNameBasedMd5CompareWithJavaUtilUuidNameUuidFromBytes() {

		UuidNamespace namespace = UuidNamespace.NAMESPACE_DNS;
		String name = null;
		UUID uuid = null;

		for (int i = 0; i < DEFAULT_LOOP_MAX; i++) {

			name = UuidCreator.getRandomBased().toString();
			uuid = UuidCreator.getNameBasedMd5(namespace, name);

			byte[] namespaceBytes = toBytes(namespace.getValue().toString().replaceAll("-", ""));
			byte[] nameBytes = name.getBytes();
			byte[] bytes = concat(namespaceBytes, nameBytes);

			assertEquals(UUID.nameUUIDFromBytes(bytes).toString(), uuid.toString());
		}
	}
}