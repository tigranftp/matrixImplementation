@file:Suppress("UNUSED_PARAMETER")
package mmcs.assignment2

import java.lang.IllegalArgumentException

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height <= 0 )
        throw IllegalArgumentException("heigh is less or equal 0")
    if (width <= 0 )
        throw IllegalArgumentException("width is less or equal 0")
    var result = MatrixImpl<E>(height, width)
    for (i in 0 until height)
        for (j in 0 until width)
            result.elements.add(e);

    return result
}

/**
 * Реализация интерфейса "матрица"
 */

@Suppress("EqualsOrHashCode")
class MatrixImpl<E>(override val height: Int, override val width: Int) : Matrix<E> {

    var elements: MutableList<E> = mutableListOf<E>()

    override fun get(row: Int, column: Int): E {
        if (row > height -1)
            throw IllegalArgumentException("Cell's row is more then the height of the Matrix")
        if (column > width -1)
            throw IllegalArgumentException("Cell's column is more then the width of the Matrix")
        return elements[row*this.height + column];
    }

    override fun get(cell: Cell): E {
        return get(cell.row, cell.column)
    }

    override fun set(row: Int, column: Int, value: E) {
        elements[row*this.height + column] = value
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MatrixImpl<*>) return false

        if (this.height != other.height) return false
        if (this.width != other.width) return false

        for (i in 0 until this.height)
            for (j in 0 until this.width)
                if (this.elements[i*this.height + j] != other.elements[i*this.height + j])
                    return false

        return true
    }

    override fun toString(): String {
        var res = StringBuilder()
        for (i in 0 until height) {

            for (j in 0 until width) {
                res.append(elements[i*this.height + j])
                res.append(' ')
            }
            if (i != height -1 )
                res.append('\n')
        }
        return res.toString()
    }


}
