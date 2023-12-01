fun main() {
    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

fun part1(input: List<String>): Int {
    var sum = 0
    // Iterate lines
    for (line in input) {
        val digits = mutableListOf<String>()
        // Iterate chars in line
        for (char in line) {
            if (char.isDigit()) {
                digits.add(char.toString())
            }
        }

        // Combine first and last digit
        val combined = "${digits[0]}${digits.last()}".toInt()
        // Add to the sum
        sum += combined
    }
    return sum
}

fun part2(input: List<String>): Int {
    var sum = 0
    // Iterate lines
    for (line in input) {
        val digits = mutableListOf<String>()
        // Iterate chars in line

        line.forEachIndexed { index, c ->
            if (c.isDigit()) {
                digits.add(c.toString())
            } else {
                // Attempt to map substring from the current index to the end contains a spelled out digit
                val substring = line.substring(index, line.length)
                val digit = getDigitSpelledOut(substring)
                if (digit != null) {
                    digits.add(digit)
                }
            }
        }

        // Combine first and last digit
        val combined = "${digits[0]}${digits.last()}".toInt()
        // Add to the sum
        sum += combined
    }
    return sum
}

val digitMap = mapOf(
    "zero" to "0",
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9"
)

fun getDigitSpelledOut(str: String): String? {
    for (entry in digitMap.entries.iterator()) {
        if (str.startsWith(entry.key)) {
            return entry.value
        }
    }
    return null
}