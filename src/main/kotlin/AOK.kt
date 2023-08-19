object AOK {
    private val koreans = arrayListOf("ㅂ", "ㅈ", "ㄷ", "ㄱ", "ㅅ", "ㅛ", "ㅕ", "ㅑ", "ㅐ", "ㅔ", "ㅁ", "ㄴ", "ㅇ", "ㄹ", "ㅎ", "ㅗ", "ㅓ", "ㅏ",
        "ㅣ", "ㅋ", "ㅌ", "ㅊ", "ㅍ", "ㅠ", "ㅜ", "ㅡ", "ㄳ", "ㄵ", "ㄶ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅄ", "ㅘ", "ㅙ", "ㅚ", "ㅝ", "ㅞ", "ㅟ", "ㅢ",
    "ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ")

    private val englishes = arrayListOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k",
    "l", "z", "x", "c", "v", "b", "n", "m", "rt", "sw", "sg", "fr", "fa", "fq", "ft", "fx", "fv", "fg", "qt", "hk", "ho", "hl", "nj", "np", "nl", "ml",
    "R", "E", "Q", "T", "W")

    private val CHO = arrayListOf(
        "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ",
        "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
    )
    private val JOONG = arrayListOf(
        "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
        "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"
    )
    private val JONG = arrayListOf(
        "", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ",
        "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
    )
    fun mergeLanguage(string : String) : String {
        var text = ""
        for (i in string.toCharArray()) {
            text += when (i.code) {
                in (0xAC00.. 0xD7AF) -> {
                    koreanToEnglish(i.toString())
                }
                in (0x0041..0x005A), in (0x0061..0x007A) -> {
                    englishToKorean(i.toString())
                }
                else -> {
                    i.toString()
                }
            }
        }
        return joinKorean(text)
    }
    fun koreanToEnglish(string : String) : String {
        var text = splitKorean(string)
        for (i in koreans.indices) {
            if (text.contains(koreans[i])) {
                text = text.replace(koreans[i], englishes[i])
            }
        }
        return text
    }

    fun englishToKorean(string: String): String {
        var text = string
        for (i in koreans.indices) {
            if (text.contains(englishes[i])) {
                text = text.replace(englishes[i], koreans[i])
            }
        }
        return joinKorean(text)
    }

    private fun softMergeKorean(a: String, b: String): String {
        if (a == "ㄱ" && b == "ㅅ") return "ㄳ"
        if (a == "ㄴ" && b == "ㅈ") return "ㄵ"
        if (a == "ㄴ" && b == "ㅎ") return "ㄶ"
        if (a == "ㄹ" && b == "ㄱ") return "ㄺ"
        if (a == "ㄹ" && b == "ㅁ") return "ㄻ"
        if (a == "ㄹ" && b == "ㅂ") return "ㄼ"
        if (a == "ㄹ" && b == "ㅅ") return "ㄽ"
        if (a == "ㄹ" && b == "ㅌ") return "ㄾ"
        if (a == "ㄹ" && b == "ㅍ") return "ㄿ"
        if (a == "ㄹ" && b == "ㅎ") return "ㅀ"
        if (a == "ㅂ" && b == "ㅅ") return "ㅄ"
        if (a == "ㅗ" && b == "ㅏ") return "ㅘ"
        if (a == "ㅗ" && b == "ㅐ") return "ㅙ"
        if (a == "ㅗ" && b == "ㅣ") return "ㅚ"
        if (a == "ㅜ" && b == "ㅓ") return "ㅝ"
        if (a == "ㅜ" && b == "ㅔ") return "ㅞ"
        if (a == "ㅜ" && b == "ㅣ") return "ㅟ"
        if (a == "ㅡ" && b == "ㅣ") return "ㅢ"
        return ""
    }

    fun splitKorean(string : String) : String {
        var text = ""
        for (element in string) {
            var uniVal = element

            if (uniVal.code >= 0xAC00) {
                uniVal = (uniVal.code - 0xAC00).toChar()
                val cho = (uniVal.code / 28 / 21).toChar()
                val joong = (uniVal.code / 28 % 21).toChar()
                val jong = (uniVal.code % 28).toChar()
                text += CHO[cho.code] + JOONG[joong.code] + JONG[jong.code]
            } else {
                text += uniVal
            }
        }
        return text
    }

    fun joinKorean(text : String) : String {
        val texts = arrayListOf<ArrayList<String>>()
        text.toCharArray().indices.forEach {
            val now = text[it].toString()
            val next = if (text.lastIndex != it) text[it + 1].toString() else ""
            if (!CHO.contains(now) && !JOONG.contains(now) && !JONG.contains(now)) {
                texts.add(arrayListOf(now))
            } else if (JOONG.contains(next) && !JOONG.contains(now)) {
                texts.add(arrayListOf(now))
            } else {
                if (texts.isEmpty()) texts.add(arrayListOf())
                texts.last().add(now)
            }
        }
        var t = ""
        texts.forEach {
            when (it.size) {
                1 -> t += it[0]
                2 -> t += combine(CHO.indexOf(it[0]), JOONG.indexOf(it[1]), 0)
                3 -> {
                    t += if (it[2] in JOONG) combine(CHO.indexOf(it[0]), JOONG.indexOf(softMergeKorean(it[1], it[2])), 0)
                    else combine(CHO.indexOf(it[0]), JOONG.indexOf(it[1]), JONG.indexOf(it[2]))
                }
                4 -> {
                    t += if (it[2] in JOONG) combine(CHO.indexOf(it[0]), JOONG.indexOf(softMergeKorean(it[1], it[2])), JONG.indexOf(it[3]))
                    else combine(CHO.indexOf(it[0]), JOONG.indexOf(it[1]), JONG.indexOf(softMergeKorean(it[2], it[3])))
                }
                5 -> {
                    t += combine(CHO.indexOf(it[0]), JOONG.indexOf(softMergeKorean(it[1], it[2])), JONG.indexOf(softMergeKorean(it[3], it[4])))
                }
            }
        }
        return t
    }

    private fun combine(x1: Int, x2: Int, x3: Int): Char {
        val x = x1 * 21 * 28 + x2 * 28 + x3
        return (x + 0xAC00).toChar()
    }
}