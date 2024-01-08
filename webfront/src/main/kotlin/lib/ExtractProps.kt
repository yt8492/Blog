package lib

import js.core.Object
import kotlin.js.Json
import kotlin.js.json

fun rest(obj: dynamic, vararg props: String): Json {
    val rem = json()
    Object.keys(obj).forEach {
        if (!props.contains(it)) {
            rem[it] = obj[it]
        }
    }
    return rem
}
