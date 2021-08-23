package br.com.thelegion.legioncommons.chat.util;

import br.com.thelegion.legioncommons.chat.component.LegacyConverter;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.apache.commons.lang.StringUtils;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

	private static final TextUtil INSTANCE = new TextUtil();

	public static TextUtil getInstance() {
		return INSTANCE;
	}

	public static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yy");
	public static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();

	static {
		NUMBER_FORMAT.setMaximumFractionDigits(2);
	}

	public static final String formatDate(long date) {
		return FORMAT.format(new Date(date));
	}

	public static final String formatDouble(double v) {
		return NUMBER_FORMAT.format(v);
	}

	public static List<String> wrapText(String text, ChatColor startColor, int wordsPerLine) {
		List<String> textWrapped = new LinkedList<>();

		String[] textWords = text.split(" ");
		int wordsLength = textWords.length;

		StringBuilder line = new StringBuilder();
		int wordsInLine = 0;
		String lastColors = startColor != null ? startColor.toString() : "";

		for (int i = 0; i <= wordsLength; i++) {
			boolean isLastWord = i == wordsLength;
			if (isLastWord || wordsInLine >= wordsPerLine) {
				String lineAsString = line.toString();
				String string = lastColors + lineAsString;
				textWrapped.add(string);

				String textLastColors = org.bukkit.ChatColor.getLastColors(lineAsString);
				if (textLastColors != null) lastColors = textLastColors;

				line = new StringBuilder();
				wordsInLine = 0;
			}
			if (isLastWord) break;

			String word = textWords[i];
			if (wordsInLine != 0) line.append(" ");
			line.append(word);
			wordsInLine++;
		}

		return textWrapped;
	}



    /*private final Pattern HEX_COLORS_PATTERN = Pattern.compile("\\{#([a-fA-F0-9]{6})}");
    private final Pattern HEX_GRADIENT_PATTERN = Pattern.compile("\\{#([a-fA-F0-9]{6})(:#([a-fA-F0-9]{6}))+( )([^{}])*(})");*/

	private final Pattern HEX_SPIGOT_PATTERN = Pattern.compile("(?i)§[0-9A-FK-ORX]((?i)§[0-9A-F]){6}");

	private final List<ChatColor> FORMAT_COLORS = Arrays.asList(ChatColor.MAGIC, ChatColor.UNDERLINE, ChatColor.STRIKETHROUGH, ChatColor.BOLD, ChatColor.ITALIC, ChatColor.RESET);

	public boolean isColor(ChatColor color) {
		for (ChatColor formatColor : FORMAT_COLORS) {
			if (formatColor.equals(color)) {
				return false;
			}
		}

		return true;
	}

	public boolean isFormat(ChatColor color) {
		return !isColor(color);
	}

	/**
	 * Removes spigot hex-codes of string
	 *
	 * @param str string to strip hex
	 * @return stripped string
	 */
	public String stripHex(String str) {
		if (str == null) {
			return null;
		}

		Matcher matcher = HEX_SPIGOT_PATTERN.matcher(str);
		return matcher.replaceAll("");
	}

	/**
	 * Finds simple and gradient hex patterns in string and converts it to Spigot format
	 *
	 * @param text string to stylish
	 * @return stylished string
	 */
	public String stylish(String text) {
		if (text == null) {
			return StringUtils.EMPTY;
		}

		return ChatColor.translateAlternateColorCodes('&', text);
	}

	public String fixMultilineFormatting(String text) {
		return text.replaceAll("\n$", "").replaceAll("\n", "\n&r");
	}

	public String getLastColors(String text) {
		return new LegacyConverter(text).toFancyMessage().getLastColors();
	}


	/**
	 * Sends json-formatted message to player
	 */
	public void sendJson(Player player, String json) {
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(
			new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a(json)));
	}

   /* private Color calculateGradientColor(int x, int parts, Color of, Color to) {
        double p = (double) (parts - x + 1) / (double) parts;

        return new Color(
                (int) (of.getRed() * p + to.getRed() * (1 - p)),
                (int) (of.getGreen() * p + to.getGreen() * (1 - p)),
                (int) (of.getBlue() * p + to.getBlue() * (1 - p))
        );
    }*/
}
