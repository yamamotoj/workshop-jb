package ii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int =
            if (year != other.year) year - other.year
            else if (month != other.month) month - other.month
            else if (dayOfMonth != other.dayOfMonth) dayOfMonth - other.dayOfMonth
            else 0


    fun rangeTo(to:MyDate) : DateRange = DateRange(this, to)

    fun plus(interval: RepeatedTimeInterval) :MyDate = addTimeIntervals(interval.ti, interval.n)
    fun plus(interval: TimeInterval) :MyDate = addTimeIntervals(interval, 1)

}
class RepeatedTimeInterval(val ti: TimeInterval, val n: Int){}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;
    fun times(times : Int) :RepeatedTimeInterval = RepeatedTimeInterval(this, times)
}

class DateRange(public override val start: MyDate, public override val end: MyDate) : Iterable<MyDate>, Range<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        var current: MyDate = start
        return object : Iterator<MyDate> {
            override fun hasNext(): Boolean = current.compareTo(end) <= 0
            override fun next(): MyDate {
                val prev = current
                current = prev.nextDay()
                return prev
            }
        }
    }

    override fun contains(item: MyDate) : Boolean =
        start.compareTo(item) <= 0 && item.compareTo(end) <= 0


}
