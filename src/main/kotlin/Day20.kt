class Day20(inputName: String) {
    private val input: Sequence<String> = readInput(inputName)

    fun part1(): Int {
        val list = input.map { it.toInt() }.toMutableList()
        val size = list.size
        val isMoved = MutableList(size) { false }

        fun removeAt(index: Int) {
            list.removeAt(index)
            isMoved.removeAt(index)
        }

        fun add(index: Int, value: Int) {
            list.add(index, value)
            isMoved.add(index, true)
        }

        var index = 0
        while (index < size) {
            val n = list[index]
            val newPos = (index + n + (if (n > 0) 1 else 0) + 2 * size).rem(size)
            // listOf(
            //     "index $index",
            //     "n $n",
            //     "newPos $newPos",
            // ).joinToString().also { println(it) }
            when {
                newPos == index -> {
                    isMoved[index] = true
                }
                newPos < index -> {
                    removeAt(index)
                    add(newPos, n)
                }
                else -> {
                    add(newPos, n)
                    removeAt(index)
                }
            }
            while (index < size && isMoved[index]) index++
            // println(list)
        }
        val zeroIndex = list.indexOf(0)
        return listOf(1000, 2000, 3000).sumOf {
            list[(zeroIndex + it).rem(size)]
        }
    }

    fun part2(): Int = -1
}

fun main() {
    val task = Day20("Day20")
    println(task.part1())
    println(task.part2())
}