package iii_properties

import ii_conventions.MyDate
import util.TODO
import java.util.*
import kotlin.properties.ReadWriteProperty

fun todoTask21() = TODO(
        """
        Task 21.
        A delegate expression must have special 'get' and 'set' methods,
        their signatures you can see below (members of 'ReadWriteProperty' interface).

        Implement the members of the class 'EffectiveDate' so it could be delegated to.
        Store only time in milliseconds in 'timeInMillis' property.
        Use extension functions 'MyDate.toMillis' and 'Long.ToDate'.
    """,
        references = { date: MyDate -> date.toMillis().toDate() }
)

class D {
    var date by EffectiveDate()
    //The property date$delegate of type EffectiveDate is created;
    //generated 'get' and 'set' accessors for 'date' are delegated to it.
    //Browse Kotlin tool window for details.
}

class EffectiveDate<R> : ReadWriteProperty<R, MyDate> {
    var timeInMillis: Long? = null

    override fun get(thisRef: R, desc: PropertyMetadata): MyDate =
            if (timeInMillis != null) {
                timeInMillis!!.toDate()
            } else {
                MyDate(0, 0, 0)
            }

    override fun set(thisRef: R, desc: PropertyMetadata, value: MyDate) {
        timeInMillis = value.toMillis()
    }
}

fun MyDate.toMillis(): Long {
    val c = Calendar.getInstance()
    c.set(year, month, dayOfMonth)
    return c.timeInMillis
}

fun Long.toDate(): MyDate {
    val c = Calendar.getInstance()
    c.timeInMillis = this
    return MyDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE))
}
