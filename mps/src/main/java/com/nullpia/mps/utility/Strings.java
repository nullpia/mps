/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nullpia.mps.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Strings {

	private static final String FOLDER_SEPARATOR = "/";
	private static final char EXTENSION_SEPARATOR = '.';
	
	public static String quote(String s) throws NullPointerException {
		final StringBuilder buf = new StringBuilder("\"");
		final int len = s.length();
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\n':
				c = 'n';
				buf.append('\\');
				break;
			case '\\':
			case '\"':
			case '*':
			case '?':
				buf.append('\\');
				break;
			}
			buf.append(c);
		}
		buf.append('"');
		return buf.toString();
	}

	public static String unquote(String q) throws IllegalArgumentException, NullPointerException {
		
		final StringBuilder buf = new StringBuilder();
		final int len = q.length();
		if (len < 2 || q.charAt(0) != '"' || q.charAt(len - 1) != '"')
			throw new IllegalArgumentException("Argument not quoted");
		for (int i = 1; i < len - 1; i++) {
			char c = q.charAt(i);
			if (c == '\\') {
				if (i == len - 2)
					throw new IllegalArgumentException("Trailing backslash");
				c = q.charAt(++i);
				switch (c) {
				case 'n':
					c = '\n';
					break;
				case '\\':
				case '\"':
				case '*':
				case '?':
					break;
				default:
					throw new IllegalArgumentException("Bad character '" + c
							+ "' after backslash");
				}
			} else {
				switch (c) {
				case '*':
				case '?':
				case '\"':
				case '\n':
					throw new IllegalArgumentException(
							"Invalid unescaped character '" + c
									+ "' in the string to unquote");
				}
			}
			buf.append(c);
		}
		return buf.toString();
	}

	//---------------------------------------------------------------------
	// General convenience methods for working with Strings
	//---------------------------------------------------------------------

	/**
	 * Check that the given CharSequence is neither <code>null</code> nor of length 0.
	 * Note: Will return <code>true</code> for a CharSequence that purely consists of whitespace.
	 * <p><pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * @param str the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not null and has length
	 * @see #hasText(String)
	 */
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * Check whether the given CharSequence has actual text.
	 * More specifically, returns <code>true</code> if the string not <code>null</code>,
	 * its length is greater than 0, and it contains at least one non-whitespace character.
	 * <p><pre>
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 * @param str the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not <code>null</code>,
	 * its length is greater than 0, and it does not contain whitespace only
	 * @see java.lang.Character#isWhitespace
	 */
	public static boolean hasText(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether the given CharSequence contains any whitespace characters.
	 * @param str the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not empty and
	 * contains at least 1 whitespace character
	 * @see java.lang.Character#isWhitespace
	 */
	public static boolean containsWhitespace(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Trim leading and trailing whitespace from the given String.
	 * @param str the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * Trim <i>all</i> whitespace from the given String:
	 * leading, trailing, and inbetween characters.
	 * @param str the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		int index = 0;
		while (sb.length() > index) {
			if (Character.isWhitespace(sb.charAt(index))) {
				sb.deleteCharAt(index);
			}
			else {
				index++;
			}
		}
		return sb.toString();
	}

	/**
	 * Trim leading whitespace from the given String.
	 * @param str the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimLeadingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * Trim trailing whitespace from the given String.
	 * @param str the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimTrailingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * Trim all occurences of the supplied leading character from the given String.
	 * @param str the String to check
	 * @param leadingCharacter the leading character to be trimmed
	 * @return the trimmed String
	 */
	public static String trimLeadingCharacter(String str, char leadingCharacter) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && sb.charAt(0) == leadingCharacter) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * Trim all occurences of the supplied trailing character from the given String.
	 * @param str the String to check
	 * @param trailingCharacter the trailing character to be trimmed
	 * @return the trimmed String
	 */
	public static String trimTrailingCharacter(String str, char trailingCharacter) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && sb.charAt(sb.length() - 1) == trailingCharacter) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}


	/**
	 * Test if the given String starts with the specified prefix,
	 * ignoring upper/lower case.
	 * @param str the String to check
	 * @param prefix the prefix to look for
	 * @see java.lang.String#startsWith
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	/**
	 * Test if the given String ends with the specified suffix,
	 * ignoring upper/lower case.
	 * @param str the String to check
	 * @param suffix the suffix to look for
	 * @see java.lang.String#endsWith
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		if (str == null || suffix == null) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

	/**
	 * Test whether the given string matches the given substring
	 * at the given index.
	 * @param str the original string (or StringBuilder)
	 * @param index the index in the original string to start matching against
	 * @param substring the substring to match at the given index
	 */
	public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
		for (int j = 0; j < substring.length(); j++) {
			int i = index + j;
			if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Count the occurrences of the substring in string s.
	 * @param str string to search in. Return 0 if this is null.
	 * @param sub string to search for. Return 0 if this is null.
	 */
	public static int countOccurrencesOf(String str, String sub) {
		if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
			return 0;
		}
		int count = 0;
		int pos = 0;
		int idx;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * Replace all occurences of a substring within a string with
	 * another string.
	 * @param inString String to examine
	 * @param oldPattern String to replace
	 * @param newPattern String to insert
	 * @return a String with the replacements
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sb.append(inString.substring(pos));
		// remember to append any characters to the right of a match
		return sb.toString();
	}

	/**
	 * Delete all occurrences of the given substring.
	 * @param inString the original String
	 * @param pattern the pattern to delete all occurrences of
	 * @return the resulting String
	 */
	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}

	/**
	 * Delete any character in a given String.
	 * @param inString the original String
	 * @param charsToDelete a set of characters to delete.
	 * E.g. "az\n" will delete 'a's, 'z's and new lines.
	 * @return the resulting String
	 */
	public static String deleteAny(String inString, String charsToDelete) {
		if (!hasLength(inString) || !hasLength(charsToDelete)) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}


	//---------------------------------------------------------------------
	// Convenience methods for working with formatted Strings
	//---------------------------------------------------------------------

	/**
	 * Strip the filename extension from the given path,
	 * e.g. "mypath/myfile.txt" -> "mypath/myfile".
	 * @param path the file path (may be <code>null</code>)
	 * @return the path with stripped filename extension,
	 * or <code>null</code> if none
	 */
	public static String stripFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		return (sepIndex != -1 ? path.substring(0, sepIndex) : path);
	}

	/**
	 * Apply the given relative path to the given path,
	 * assuming standard Java folder separation (i.e. "/" separators).
	 * @param path the path to start from (usually a full file path)
	 * @param relativePath the relative path to apply
	 * (relative to the full file path above)
	 * @return the full file path that results from applying the relative path
	 */
	public static String applyRelativePath(String path, String relativePath) {
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (separatorIndex != -1) {
			String newPath = path.substring(0, separatorIndex);
			if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
				newPath += FOLDER_SEPARATOR;
			}
			return newPath + relativePath;
		}
		else {
			return relativePath;
		}
	}

	//---------------------------------------------------------------------
	// Convenience methods for working with String arrays
	//---------------------------------------------------------------------

	/**
	 * Append the given String to the given String array, returning a new array
	 * consisting of the input array contents plus the given String.
	 * @param array the array to append to (can be <code>null</code>)
	 * @param str the String to append
	 * @return the new array (never <code>null</code>)
	 */
	public static String[] addStringToArray(String[] array, String str) {
		if (array == null || array.length == 0) {
			return new String[] {str};
		}
		String[] newArr = new String[array.length + 1];
		System.arraycopy(array, 0, newArr, 0, array.length);
		newArr[array.length] = str;
		return newArr;
	}

	/**
	 * Concatenate the given String arrays into one,
	 * with overlapping array elements included twice.
	 * <p>The order of elements in the original arrays is preserved.
	 * @param array1 the first array (can be <code>null</code>)
	 * @param array2 the second array (can be <code>null</code>)
	 * @return the new array (<code>null</code> if both given arrays were <code>null</code>)
	 */
	public static String[] concatenateStringArrays(String[] array1, String[] array2) {
		if (array1 == null || array1.length == 0) {
			return array2;
		}
		if (array2 == null || array2.length == 0) {
			return array1;
		}
		String[] newArr = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, newArr, 0, array1.length);
		System.arraycopy(array2, 0, newArr, array1.length, array2.length);
		return newArr;
	}

	/**
	 * Merge the given String arrays into one, with overlapping
	 * array elements only included once.
	 * <p>The order of elements in the original arrays is preserved
	 * (with the exception of overlapping elements, which are only
	 * included on their first occurence).
	 * @param array1 the first array (can be <code>null</code>)
	 * @param array2 the second array (can be <code>null</code>)
	 * @return the new array (<code>null</code> if both given arrays were <code>null</code>)
	 */
	public static String[] mergeStringArrays(String[] array1, String[] array2) {
		if (array1 == null || array1.length == 0) {
			return array2;
		}
		if (array2 == null || array2.length == 0) {
			return array1;
		}
		List<String> result = new ArrayList<String>();
		result.addAll(Arrays.asList(array1));
		for (String str : array2) {
			if (!result.contains(str)) {
				result.add(str);
			}
		}
		return toStringArray(result);
	}

	/**
	 * Turn given source String array into sorted array.
	 * @param array the source array
	 * @return the sorted array (never <code>null</code>)
	 */
	public static String[] sortStringArray(String[] array) {
		if ( array == null || array.length == 0 ) {
			return new String[0];
		}
		Arrays.sort(array);
		return array;
	}

	/**
	 * Copy the given Collection into a String array.
	 * The Collection must contain String elements only.
	 * @param collection the Collection to copy
	 * @return the String array (<code>null</code> if the passed-in
	 * Collection was <code>null</code>)
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return collection.toArray(new String[collection.size()]);
	}

	/**
	 * Copy the given Enumeration into a String array.
	 * The Enumeration must contain String elements only.
	 * @param enumeration the Enumeration to copy
	 * @return the String array (<code>null</code> if the passed-in
	 * Enumeration was <code>null</code>)
	 */
	public static String[] toStringArray(Enumeration<String> enumeration) {
		if (enumeration == null) {
			return null;
		}
		List<String> list = Collections.list(enumeration);
		return list.toArray(new String[list.size()]);
	}

	/**
	 * Trim the elements of the given String array,
	 * calling <code>String.trim()</code> on each of them.
	 * @param array the original String array
	 * @return the resulting array (of the same size) with trimmed elements
	 */
	public static String[] trimArrayElements(String[] array) {
		if (array == null || array.length == 0) {
			return new String[0];
		}
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			String element = array[i];
			result[i] = (element != null ? element.trim() : null);
		}
		return result;
	}

	/**
	 * Split a String at the first occurrence of the delimiter.
	 * Does not include the delimiter in the result.
	 * @param toSplit the string to split
	 * @param delimiter to split the string up with
	 * @return a two element array with index 0 being before the delimiter, and
	 * index 1 being after the delimiter (neither element includes the delimiter);
	 * or <code>null</code> if the delimiter wasn't found in the given input String
	 */
	public static String[] split(String toSplit, String delimiter) {
		if (!hasLength(toSplit) || !hasLength(delimiter)) {
			return null;
		}
		int offset = toSplit.indexOf(delimiter);
		if (offset < 0) {
			return null;
		}
		String beforeDelimiter = toSplit.substring(0, offset);
		String afterDelimiter = toSplit.substring(offset + delimiter.length());
		return new String[] {beforeDelimiter, afterDelimiter};
	}

	public static String empty(String str) {
		return isEmpty(str) ? "" : str;
	}
	public static String empty(String str, String emptyStr) {
		return isEmpty(str) ? emptyStr : str;
	}
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}
	public static String[] splitToStringArray(String str, String delimiter) {
		if( isEmpty(str) || isEmpty(delimiter) ) {
			return new String[0];
		}
		
		List<String> result = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str, delimiter);
		
		while ( st.hasMoreTokens() ) {
			result.add(st.nextToken().trim());
		}
		return (String[]) result.toArray(new String[result.size()]);
	}


	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}
	
    /**
      * Tokenize the given String into a String array via a StringTokenizer.
      * <p>The given delimiters string is supposed to consist of any number of
      * delimiter characters. Each of those characters can be used to separate
      * tokens. A delimiter is always a single character; for multi-character
      * delimiters, consider using <code>delimitedListToStringArray</code>
      * @param str the String to tokenize
      * @param delimiters the delimiter characters, assembled as String
      * (each of those characters is individually considered as delimiter)
      * @param trimTokens trim the tokens via String's <code>trim</code>
      * @param ignoreEmptyTokens omit empty tokens from the result array
      * (only applies to tokens that are empty after trimming; StringTokenizer
      * will not consider subsequent delimiters as token in the first place).
      * @return an array of the tokens (<code>null</code> if the input String
      * was <code>null</code>)
      * @see java.util.StringTokenizer
      * @see java.lang.String#trim()
      * @see #delimitedListToStringArray
      */
     public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
    	 if (str == null) {
    		 return null;
    	 }
         StringTokenizer st = new StringTokenizer(str, delimiters);
         List<String> tokens = new ArrayList<String>();
         while (st.hasMoreTokens()) {
        	 String token = st.nextToken();
        	 if (trimTokens) {
        		 token = token.trim();
        	 }
        	 if (!ignoreEmptyTokens || token.length() > 0) {
        		 tokens.add(token);
        	 }
         }
         return toStringArray(tokens);
     }	
     
     
     
}
