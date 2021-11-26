package blu.samanpr.interview

import org.junit.Assert
import org.junit.Test

class UnitTests {

    @Test
    fun testMethod_1() {
        Assert.assertEquals(true, method_1(isChecked = true, isActivated = true))
        Assert.assertEquals(false, method_1(isChecked = true, isActivated = false))
        Assert.assertEquals(false, method_1(isChecked = false, isActivated = true))
        Assert.assertEquals(true, method_1(isChecked = false, isActivated = false))
    }

    @Test
    fun testMethod_2() {
        Assert.assertEquals(listOf(17, 21, 33), method_2(listOf(17, 21, 44, 33, 16)))
        Assert.assertEquals(listOf<Int>(), method_2(listOf()))
        Assert.assertEquals(listOf<Int>(), method_2(listOf(2, 4, 28)))
    }

    @Test
    fun testContainsRange() {
        Assert.assertEquals(true, containsRange(12..17, 15..17))
        Assert.assertNotEquals(true, containsRange(5..7, 1..12))
    }

    @Test
    fun testSumNumbersUntil() {
        Assert.assertEquals(28, sumNumbersUntil(7))
        Assert.assertNotEquals(10, sumNumbersUntil(3))
    }

    @Test
    fun testCountDown() {
        Assert.assertEquals(listOf(4, 3, 2, 1, 0), countDown(4))
        Assert.assertEquals(listOf(0), countDown(0))
    }

    @Test
    fun testEqualDigitFrequency() {
        Assert.assertNotEquals(true, equalDigitFrequency(5, 10))
        Assert.assertEquals(true, equalDigitFrequency(100, 100))
    }

    @Test
    fun testBinarySearch() {
        Assert.assertEquals(1,binarySearch("farshid".toList(),'a'))
        Assert.assertEquals(-1,binarySearch("farshid".toList(),'q'))


    }


//TODO: Optimize below methods

    private fun method_1(isChecked: Boolean, isActivated: Boolean): Boolean {

        return (isActivated==isChecked)
//        return when {
//            isChecked && isActivated -> true
//            isChecked && !isActivated -> false
//            !isChecked && !isActivated -> true
//            !isChecked && isActivated -> false
//            else -> false
//        }
    }

    private fun method_2(list: List<Int>): List<Int> {
        val result = mutableListOf<Int>()

        fun helper(list: List<Int>) {
            if (list.isEmpty()) {
                return
            }

            if (list.first() % 2 == 1) {
                result.add(list.first())
            }

            helper(list.drop(1))
        }

        helper(list)

        return result
    }

    private fun countDown(n: Int): List<Int> {
        if (n == 0) {
            return listOf(0)
        }
        return mutableListOf(n).also { it.addAll(countDown(n - 1)) }
    }


//TODO: Implement below methods

    private fun containsRange(range1: IntRange, range2: IntRange) =
        (range2.first >= range1.first && range2.last <= range1.last)

}

private fun sumNumbersUntil(n: Int): Int {
    var sum = 0

    for (i in 1..n) {
        sum += i
    }
    return sum
}


//TODO: Write Unit Test for below methods

private fun equalDigitFrequency(i1: Int, i2: Int): Boolean {
    val i1Str = i1.toString()
    val i2Str = i2.toString()

    if (i1Str.length != i2Str.length) {
        return false
    }

    val frequencyCounter1 = i1Str.groupingBy { it }.eachCount()
    val frequencyCounter2 = i2Str.groupingBy { it }.eachCount()
    return frequencyCounter1 == frequencyCounter2
}

private fun binarySearch(list: List<Char>, element: Char): Int {
    var left = 0
    var right = list.size - 1

    while (left <= right) {
        val middle = (right + left) / 2

        if (list[middle] == element)
            return middle

        if (list[middle] < element)
            left = middle + 1
        else
            right = middle - 1
    }

    return -1
}


//TODO: Optimize below code

data class User(val name: String, val age: Int)
enum class Type { REMOTE, LOCAL, MOCK }


class ModuleBuilder() {
    fun provideUserSaver(type: Type): UserSaver {
        return when (type) {
            Type.MOCK -> MockDataSource()
            Type.LOCAL -> LocalDataSource()
            Type.REMOTE -> RemoteDataSource()
        }
    }

    private class Repository(private val userSaver: UserSaver) {

        private fun saveUser(user: User) {
            userSaver.saveUser(user)
        }
    }

    interface UserSaver {
        fun saveUser(user: User)
    }

    private class RemoteDataSource : UserSaver {
        override fun saveUser(user: User) {
            // some code to save fake user
        }
    }

    private class LocalDataSource : UserSaver {
        override fun saveUser(user: User) {
            // some code to save fake user
        }
    }

    private class MockDataSource : UserSaver {
        override fun saveUser(user: User) {
            // some code to save fake user
        }
    }
}

