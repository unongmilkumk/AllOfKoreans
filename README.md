# Proj. AOK
### All Of Koreans

Korean pack for JVM Users

## How to Import?
there is no way to import with gradle / maven, so just install to import.

## What can I do?

- **Number Conversion**: Convert Arabic numerals to Korean words and vice versa.
- **Character Mapping**: Map between Korean and English keyboard characters.
- **Hangul Decomposition and Composition**: Split Korean Hangul characters into initial, medial, and final sounds (자음/모음/종성) and recombine them.
- **Bidirectional Language Conversion**: Convert between Korean and English characters in a string, merging characters appropriately for Korean.

## How to code?

### Number Conversion

- **`numberToKorean(int number)`**  
  Converts an integer to its Korean text representation.  
  Example:
  ```java
  String koreanText = KoreanPack.numberToKorean(123456);
  // Output: "십이만 삼천 사백 오십육"
  ```

- **`koreanToNumber(String korean)`**  
  Converts a Korean text representation of a number back to an integer.  
  Example:
  ```java
  int number = KoreanPack.koreanToNumber("십이만 삼천 사백 오십육");
  // Output: 123456
  ```

### Character Mapping

- **`koreanToEnglish(String string)`**  
  Converts Korean characters to their corresponding English keyboard mappings.  
  Example:
  ```java
  String englishText = KoreanPack.koreanToEnglish("안녕하세요");
  // Output: "dkssudgktpdy"
  ```

- **`englishToKorean(String string)`**  
  Converts English keyboard mappings back to Korean characters.  
  Example:
  ```java
  String koreanText = KoreanPack.englishToKorean("dkssudgktpdy");
  // Output: "안녕하세요"
  ```

### Hangul Decomposition and Composition

- **`splitKorean(String string)`**  
  Splits Korean Hangul characters into initial (초성), medial (중성), and final (종성) components.  
  Example:
  ```java
  String decomposedText = KoreanPack.splitKorean("한글");
  // Output: "ㅎㅏㄴㄱㅡㄹ"
  ```

- **`joinKorean(String text)`**  
  Combines split Korean characters back into Hangul syllables.  
  Example:
  ```java
  String combinedText = KoreanPack.joinKorean("ㅎㅏㄴㄱㅡㄹ");
  // Output: "한글"
  ```

- **`mergeLanguage(String string)`**  
  Detects both Korean and English characters within a string, converting each accordingly and recombining.  
  Example:
  ```java
  String mergedText = KoreanPack.mergeLanguage("안녕 dkssud");
  // Output: "dkssud 안녕"
  ```

### Utilities

- **`softMergeKorean(String a, String b)`**  
  Soft-merges two Korean characters where possible.

- **`combine(int x1, int x2, int x3)`**  
  Combines initial, medial, and final sound indexes into a single Hangul character.