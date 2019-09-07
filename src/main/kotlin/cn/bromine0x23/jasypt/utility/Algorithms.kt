package cn.bromine0x23.jasypt.utility

import java.security.Security
import java.util.*

object Algorithms {

	val allDigestAlgorithms: List<String>
		@JvmStatic
		get() {
			return algorithmsOf("MessageDigest").sorted()
		}

	val allPBEAlgorithms: List<String>
		@JvmStatic
		get() {
			return algorithmsOf("Cipher").filter { it.startsWith("PBE") }.sorted()
		}

	@JvmStatic
	private fun algorithmsOf(service: String) : Set<String> {
		val result = mutableSetOf<String>()
		if (!service.endsWith('.')) {
			for (provider in Security.getProviders()) {
				for (key in provider.keys) {
					key as String
					val upperKey = key.toUpperCase(Locale.ENGLISH)
					if (upperKey.startsWith(service.toUpperCase(Locale.ENGLISH)) && key.indexOf(' ') < 0) {
						result.add(key.substring(service.length + 1))
					}
				}
			}
		}
		return result
	}
}