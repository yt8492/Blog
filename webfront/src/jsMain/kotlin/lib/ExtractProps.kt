package lib

import js.objects.Object
import kotlin.js.Json
import kotlin.js.json

fun rest(obj: dynamic, vararg props: String): Json {
    val rem = json(
        "hoge" to "fuga",
        "a" to 1
    )
    Object.keys(obj).forEach {
        if (!props.contains(it)) {
            rem[it] = obj[it]
        }
    }
    return rem
}
