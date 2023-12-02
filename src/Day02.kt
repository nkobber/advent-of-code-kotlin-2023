fun main() {
    val input = readInput("Day02")
    val d2 = Day02()
    println(d2.part1(input))
    println(d2.part2(input))
}

class Day02 {
    fun part1(input: List<String>): Int {
        var sum = 0

        for (line in input) {
            val split = line.split(":")
            val gameNum = split[0].substring(5).toInt()
            if (isPossible(split[1])) {
                sum += gameNum
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        for (line in input) {
            val split = line.split(":")
            sum += productOfMaxColorCountInGame(split[1])
        }

        return sum
    }
}

fun productOfMaxColorCountInGame(game: String): Int {
    val maxColorMap = mutableMapOf(
        "red" to 0,
        "blue" to 0,
        "green" to 0,
    )
    val draws = game.split(";")
    for (draw in draws) {
        val colors = draw.split(",")
        for (color in colors) {
            val c = color.filter { it.isLetter() }
            val count = color.filter { it.isDigit() }.toInt()
            if (maxColorMap[c]!! < count) {
                maxColorMap[c] = count
            }
        }
    }
    return maxColorMap["blue"]!! * maxColorMap["red"]!! * maxColorMap["green"]!!
}

val maxColorMap = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14,
)

fun isPossible(game: String): Boolean {
    val draws = game.split(";")
    for (draw in draws) {
        val colors = draw.split(",")
        for (color in colors) {
            val c = color.filter { it.isLetter() }
            val count = color.filter { it.isDigit() }.toInt()
            if (count > maxColorMap[c]!!) {
                return false
            }
        }
    }
    return true
}
