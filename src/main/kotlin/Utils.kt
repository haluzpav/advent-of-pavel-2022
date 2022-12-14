import java.io.File
import kotlin.math.abs

@Suppress("FunctionName")
private fun InputFile(name: String) = File("inputs", "$name.txt")

fun loadInput(name: String): List<String> = InputFile(name).readLines()

fun readInput(name: String): Sequence<String> = sequence {
    yieldAll(InputFile(name).bufferedReader().lineSequence())
}

fun <T, U> Iterable<T>.splitBy(predicate: (T) -> Boolean, mapElement: (T) -> U): List<List<U>> =
    fold(mutableListOf(mutableListOf<U>())) { acc, s ->
        if (predicate(s)) acc += mutableListOf<U>()
        else acc.last() += mapElement(s)
        acc
    }

typealias Pos = Pair<Int, Int>

operator fun List<String>.get(pos: Pos): Char = this[pos.first][pos.second]

operator fun Pos.plus(other: Pos): Pos = first + other.first to second + other.second

operator fun Pos.minus(other: Pos): Pos = first - other.first to second - other.second

fun Pos.manhattanTo(other: Pos): Int =
    abs(first - other.first) + abs(second - other.second)

operator fun <E> List<E>.component6(): E = this[5]

typealias Pos3 = Triple<Int, Int, Int>

fun Pos3.manhattanTo(other: Pos3): Int =
    abs(first - other.first) + abs(second - other.second) + abs(third - other.third)

inline fun <reified T : Enum<T>> T.rotateBy(rotations: Int): T {
    val values = enumValues<T>()
    val size = values.size
    val index = (ordinal + rotations).positiveRem(size)
    return values[index]
}

fun Int.positiveRem(other: Int): Int = (rem(other) + other).rem(other)

fun <T> List<T>.getWrapped(index: Int) = this[index.positiveRem(size)]

operator fun Pair<IntRange, IntRange>.contains(pos: Pos): Boolean {
    val (xRange, yRange) = this
    val (x, y) = pos
    return x in xRange && y in yRange
}
