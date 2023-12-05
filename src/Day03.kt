fun main() {
    val input = readInput("Day03")
    val day03 = Day03()
    println(day03.part1(input))
    println(day03.part2(input))
}

data class Number(val start: Int, val end: Int, val number: Int, val line: Int) {}
data class AsteriskPosition(val position: Int, val line: Int)

class Day03 {
    fun part1(input: List<String>): Int {
        var sum = 0

        // Find all numbers with regex, including their start/end index
        val regex = Regex("\\d+")
        val numbers = mutableListOf<Number>()
        for (i in input.indices) {
            val numbersFromLine = regex.findAll(input[i]).map {
                Number(it.range.first, it.range.last, it.value.toInt(), i)
            }
            numbers.addAll(numbersFromLine)
        }

        for (number in numbers) {
            sum += getNumberIfHasAdjacentSymbol(input, number)
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        // Find all numbers with regex, including their start/end index
        val regex = Regex("\\*")
        val asterisks = mutableListOf<AsteriskPosition>()
        for (i in input.indices) {
            val asterisksFromLine = regex.findAll(input[i]).map {
                AsteriskPosition(it.range.first, i)
            }
            asterisks.addAll(asterisksFromLine)
        }

        for (asterisk in asterisks) {
            sum += getGearProduct(input, asterisk)
        }

        return sum
    }
}

fun getNumberIfHasAdjacentSymbol(input: List<String>, number: Number): Int {
    val symbols = listOf('#', '=', '@', '*', '/', '%', '-', '$', '+', '&')

    val line = input[number.line]
    var rangeToCheck = number.start - 1..number.end + 1
    if (rangeToCheck.first < 0) {
        rangeToCheck = 0..rangeToCheck.last
    }
    if (rangeToCheck.last > 139) {
        rangeToCheck = rangeToCheck.first..139
    }

    // Get all chars from line, previous line, next line
    var chars = line.substring(rangeToCheck)
    if (number.line != 0) {
        chars += input[number.line-1].substring(rangeToCheck)
    }
    if (number.line != 139) {
        chars += input[number.line+1].substring(rangeToCheck)
    }

    // Check if the chars contain a symbol
    val match = chars.filter { it in symbols }
    if (match.isNotEmpty()) {
        return number.number
    }

    return 0
}


fun getGearProduct(input: List<String>, asterisk: AsteriskPosition): Int {
    return 0
}