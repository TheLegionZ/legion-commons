package br.com.thelegion.legioncommons.scanners;

import br.com.thelegion.legioncommons.chat.replacer.StringReplacer;

import java.security.CodeSource;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * The ZISScanner (ZipInputStream Scanner), as the name suggests, scans the
 * literal jar file with a ZipInputStream; recursing through every file entry
 * while checking the name to see if it's a class, and if the package
 * isn't on the exclusion list. If all is well, load the class through the main
 * classes classloader.
 */
public final class ZISScanner {

	private static final StringReplacer CLASS_REPLACER = StringReplacer.replacing('/', '.')
		.and(".class", "");



	public static Set<Class<?>> getClasses(final Class<?> main, String pckg) {
		pckg = pckg.replace('.', '/');

		final ClassLoader loader = main.getClassLoader();
		final CodeSource src = main.getProtectionDomain().getCodeSource();
		if (src == null) {
			return Collections.emptySet();
		}

		final Set<Class<?>> classes = new HashSet<>();
		try (final ZipInputStream zip = new ZipInputStream(src.getLocation().openStream())) {
			ZipEntry entry;

			while ((entry = zip.getNextEntry()) != null) {
				final String name = entry.getName();

				if (!name.endsWith(".class") || (!name.startsWith(pckg))) {
					continue;
				}

				try {
					Class<?> clazz = loader.loadClass(CLASS_REPLACER.transform(name));
					if (clazz.isAssignableFrom(main)) {
						classes.add(clazz);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return classes;
	}
}