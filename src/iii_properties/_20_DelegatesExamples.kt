package iii_properties

import util.TODO
import kotlin.properties.Delegates
import kotlin.properties.set
import kotlin.properties.get
import kotlin.properties.ReadWriteProperty

class A(initializer: () -> Int) {

    val lazy: Int by lazy(LazyThreadSafetyMode.NONE, initializer)
}

class B() {

    var notNull: Int by Delegates.notNull()

    //If the property is accessed without initialization, an exception is thrown.
    fun foo() = notNull
}

class Commodity(data: MutableMap<String, Any?>) {

    val description: String by data
    var price: Int by data
    var isAvailable: Boolean by data
}

fun <T> todoTask20(): ReadWriteProperty<Commodity, T> = TODO(
        """
        Task 20.
        Make the properties in class Commodity reflect the data stored in the map.
        E.g., the value of property 'price' should be the value in 'data' by the key "price".
        Use Delegates.mapVar().
    """,
        references = {
            val data = hashMapOf<String, Any?>("description" to "snowboard", "price" to 349, "isAvailable" to true)
            Commodity(data)
            data
        }
)
