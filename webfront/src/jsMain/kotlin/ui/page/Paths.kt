package ui.page

import tanstack.router.core.ParamName
import tanstack.router.core.RoutePath

val ENTRY_ID_PARAM: ParamName = ParamName("id")

val ENTRIES_PATH: RoutePath = RoutePath("/")
val ENTRY_ROUTE_PATH: RoutePath = RoutePath("/entries/", ENTRY_ID_PARAM)

fun entryPath(id: String): RoutePath =
    RoutePath("/entries/$id")
