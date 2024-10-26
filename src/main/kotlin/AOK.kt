import java.util.*
import java.util.function.Consumer
import kotlin.math.pow

object AOK {
    var koreans: ArrayList<String> = ArrayList(
        mutableListOf(
            "ㅂ",
            "ㅈ",
            "ㄷ",
            "ㄱ",
            "ㅅ",
            "ㅛ",
            "ㅕ",
            "ㅑ",
            "ㅐ",
            "ㅔ",
            "ㅁ",
            "ㄴ",
            "ㅇ",
            "ㄹ",
            "ㅎ",
            "ㅗ",
            "ㅓ",
            "ㅏ",
            "ㅣ",
            "ㅋ",
            "ㅌ",
            "ㅊ",
            "ㅍ",
            "ㅠ",
            "ㅜ",
            "ㅡ",
            "ㄳ",
            "ㄵ",
            "ㄶ",
            "ㄺ",
            "ㄻ",
            "ㄼ",
            "ㄽ",
            "ㄾ",
            "ㄿ",
            "ㅀ",
            "ㅄ",
            "ㅘ",
            "ㅙ",
            "ㅚ",
            "ㅝ",
            "ㅞ",
            "ㅟ",
            "ㅢ",
            "ㄲ",
            "ㄸ",
            "ㅃ",
            "ㅆ",
            "ㅉ",
            "ㅖ",
            "ㅒ"
        )
    )

    var englishes: ArrayList<String> = ArrayList(
        mutableListOf(
            "q",
            "w",
            "e",
            "r",
            "t",
            "y",
            "u",
            "i",
            "o",
            "p",
            "a",
            "s",
            "d",
            "f",
            "g",
            "h",
            "j",
            "k",
            "l",
            "z",
            "x",
            "c",
            "v",
            "b",
            "n",
            "m",
            "rt",
            "sw",
            "sg",
            "fr",
            "fa",
            "fq",
            "ft",
            "fx",
            "fv",
            "fg",
            "qt",
            "hk",
            "ho",
            "hl",
            "nj",
            "np",
            "nl",
            "ml",
            "R",
            "E",
            "Q",
            "T",
            "W",
            "P",
            "O"
        )
    )

    var CHO: ArrayList<String> = ArrayList(
        mutableListOf(
            "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ",
            "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
        )
    )
    var JOONG: ArrayList<String> = ArrayList(
        mutableListOf(
            "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
            "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"
        )
    )
    var JONG: ArrayList<String> = ArrayList(
        mutableListOf(
            "", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ",
            "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
        )
    )
    var SNum: ArrayList<String> = ArrayList(
        mutableListOf(
            "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"
        )
    )
    var Num: ArrayList<String> = ArrayList(
        mutableListOf(
            "", "만", "억", "조", "경", "해", "자", "양"
        )
    )
    var Numm: ArrayList<String> = ArrayList(
        mutableListOf(
            "자", "양", "해", "경", "조", "억", "만"
        )
    )

    fun numberToKorean(number: Int): String? {
        var text: String? = ""
        val nt = number.toString().replace("-", "")
        val inte = ArrayList<String>()
        val texts = ArrayList<String?>()
        inte.add("")

        var a = 0
        while (a <= nt.length - 1) {
            val now = nt.toCharArray()[nt.length - 1 - a].toString().toInt()
            if (inte[inte.size - 1].isEmpty()) {
                inte[inte.size - 1] = now.toString()
            } else if (inte[inte.size - 1].toInt() >= 1000) {
                inte.add("")
                inte[inte.size - 1] = now.toString()
            } else {
                inte[inte.size - 1] = now.toString() + inte[inte.size - 1]
            }
            a += 1
        }
        for ((b, integer) in inte.withIndex()) {
            texts.add(Num[b])
            texts.add(smallNumberToKorean(integer.toInt(), b == 1))
        }
        texts.reverse()
        for (s in texts) {
            text += s
        }
        if (number < 0) text = "마이너스 $text"
        return text
    }

    private fun smallNumberToKorean(number: Int, isForMan: Boolean): String {
        var text = ""
        if (number == 0) {
            return ""
        }
        if (number == 1 && !isForMan) {
            return "일"
        }
        when (getDigit(number, 4)) {
            1 -> text += "천"
            2, 3, 4, 5, 6, 7, 8, 9 -> {
                text += SNum[getDigit(number, 4) - 1]
                text += "천"
            }
        }
        when (getDigit(number, 3)) {
            1 -> text += "백"
            2, 3, 4, 5, 6, 7, 8, 9 -> {
                text += SNum[getDigit(number, 3) - 1]
                text += "백"
            }
        }
        when (getDigit(number, 2)) {
            1 -> text += "십"
            2, 3, 4, 5, 6, 7, 8, 9 -> {
                text += SNum[getDigit(number, 2) - 1]
                text += "십"
            }
        }
        when (getDigit(number, 1)) {
            1, 2, 3, 4, 5, 6, 7, 8, 9 -> text += SNum[getDigit(number, 1) - 1]
        }
        return text
    }

    fun getDigit(number: Int, a: Int): Int {
        return (((number / 10).toDouble().pow((a - 1).toDouble())) % 10).toInt()
    }

    fun koreanToNumber(korean: String): Int {
        if (korean == "영" || korean == "마이너스 영") return 0
        var ret = 0
        val ints = ArrayList<Int?>()
        var kor = korean.replace("마이너스 ", "")
        for (s in Numm) {
            if (!kor.contains(s)) continue
            val string = kor.split(s.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
            if (s == "만" && string.isEmpty()) {
                ints.add(1)
                kor = kor.split("만".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                continue
            }
            kor = kor.split(s.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
            ints.add(smallKoreanToNumber(string))
        }

        ints.add(smallKoreanToNumber(kor))

        ints.reverse()

        var temp = 1
        for (anInt in ints) {
            ret += anInt!! * temp
            temp *= 10000
        }
        if (korean.contains("마이너스")) ret *= -1
        return ret
    }

    private fun smallKoreanToNumber(korean: String): Int {
        var ret = ""
        val integers = ArrayList<Int>()
        var cc = ""
        for (c in korean.toCharArray()) {
            when (c) {
                '천' -> {
                    if (cc.isEmpty()) {
                        integers.add(1)
                    } else {
                        integers.add(SNum.indexOf(cc) + 1)
                    }
                    cc = ""
                }

                '백' -> {
                    if (!korean.contains("천")) integers.add(0)
                    if (cc.isEmpty()) {
                        integers.add(1)
                    } else {
                        integers.add(SNum.indexOf(cc) + 1)
                    }
                    cc = ""
                }

                '십' -> {
                    if (!korean.contains("백")) {
                        if (!korean.contains("천")) integers.add(0)
                        integers.add(0)
                    }
                    if (cc.isEmpty()) {
                        integers.add(1)
                    } else {
                        integers.add(SNum.indexOf(cc) + 1)
                    }
                    cc = ""
                }

                else -> cc += c
            }
        }
        integers.add(SNum.indexOf(cc) + 1)
        for (integer in integers) {
            ret += integer.toString()
        }
        return ret.toInt()
    }

    fun mergeLanguage(string: String): String {
        var text = ""
        for (ind in string.indices) {
            val i = string.toCharArray()[ind]
            if (i.code in 0xAC00..0xD7AF) {
                text += koreanToEnglish(i.toString())
            } else if ((i.code in 0x0041..0x005A) || (i.code in 0x0061..0x007A)) {
                text += englishToKorean(i.toString())
            } else {
                text += i
            }
        }
        return joinKorean(text)
    }

    fun koreanToEnglish(string: String): String {
        var text = splitKorean(string)
        for (i in 0 until koreans.size) {
            if (text.contains(koreans[i])) {
                text = text.replace(koreans[i], englishes[i])
            }
        }
        return text
    }

    fun englishToKorean(string: String): String {
        var text = string
        for (i in 0 until koreans.size) {
            if (text.contains(englishes[i])) {
                text = text.replace(englishes[i], koreans[i])
            }
        }
        return joinKorean(text)
    }

    fun splitKorean(string: String): String {
        val text = StringBuilder()
        for (element in string.toCharArray()) {
            var uniVal = element

            if (uniVal.code >= 0xAC00) {
                uniVal = (uniVal.code - 0xAC00).toChar()
                val cho = (uniVal.code / 28 / 21).toChar()
                val joong = (uniVal.code / 28 % 21).toChar()
                val jong = (uniVal.code % 28).toChar()
                text.append(CHO[cho.code]).append(JOONG[joong.code]).append(JONG[jong.code])
            } else {
                text.append(uniVal)
            }
        }
        return text.toString()
    }

    fun joinKorean(text: String): String {
        val texts = ArrayList<ArrayList<String>>()
        for (it in 0 until text.toCharArray().size) {
            val prev4 = if (it > 3) text.toCharArray()[it - 4].toString() else ""
            val prev3 = if (it > 2) text.toCharArray()[it - 3].toString() else ""
            val prev2 = if (it > 1) text.toCharArray()[it - 2].toString() else ""
            val prev = if (it > 0) text.toCharArray()[it - 1].toString() else ""
            val now = text.toCharArray()[it].toString()
            if (JOONG.contains(now) && CHO.contains(prev)) {
                texts[texts.size - 1].add(now)
            } else if (JONG.contains(now) && JOONG.contains(prev) && CHO.contains(prev2)) {
                texts[texts.size - 1].add(now)
            } else if (JONG.contains(softMergeKorean(prev, now)) && JOONG.contains(prev2) && CHO.contains(prev3)) {
                texts[texts.size - 1].add(now)
            } else if (JOONG.contains(softMergeKorean(prev, now)) && CHO.contains(prev2)) {
                texts[texts.size - 1].add(now)
            } else if (JONG.contains(now) && JOONG.contains(softMergeKorean(prev2, prev)) && CHO.contains(prev3)) {
                texts[texts.size - 1].add(now)
            } else if (JONG.contains(softMergeKorean(prev, now)) && JOONG.contains(
                    softMergeKorean(
                        prev3,
                        prev2
                    )
                ) && CHO.contains(prev4)
            ) {
                texts[texts.size - 1].add(now)
            } else {
                texts.add(ArrayList())
                texts[texts.size - 1].add(now)
            }
        }
        val tt = object {
            var t: String = ""
        }
        texts.forEach(Consumer { it: ArrayList<String> ->
            when (it.size) {
                1 -> tt.t += it[0]
                2 -> tt.t += combine(
                    CHO.indexOf(it[0]),
                    JOONG.indexOf(it[1]),
                    0
                )

                3 -> {
                    if (JOONG.contains(it[2])) {
                        tt.t += combine(
                            CHO.indexOf(it[0]),
                            JOONG.indexOf(softMergeKorean(it[1], it[2])),
                            0
                        )
                    } else {
                        tt.t += combine(
                            CHO.indexOf(it[0]),
                            JOONG.indexOf(it[1]),
                            JONG.indexOf(it[2])
                        )
                    }
                }

                4 -> tt.t += if (JOONG.contains(it[2])) combine(
                    CHO.indexOf(
                        it[0]
                    ),
                    JOONG.indexOf(softMergeKorean(it[1], it[2])),
                    JONG.indexOf(it[3])
                )
                else combine(
                    CHO.indexOf(it[0]),
                    JOONG.indexOf(it[1]),
                    JONG.indexOf(softMergeKorean(it[2], it[3]))
                )

                5 -> tt.t += combine(
                    CHO.indexOf(it[0]),
                    JOONG.indexOf(softMergeKorean(it[1], it[2])),
                    JONG.indexOf(softMergeKorean(it[3], it[4]))
                )
            }
        })
        return tt.t
    }

    fun softMergeKorean(a: String, b: String): String {
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
        return "nope"
    }

    private fun combine(x1: Int, x2: Int, x3: Int): Char {
        val x = x1 * 21 * 28 + x2 * 28 + x3
        return (x + 0xAC00).toChar()
    }
}