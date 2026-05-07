package ui.page

import emotion.react.css
import js.array.ReadonlyArray
import js.objects.unsafeJso
import js.reflect.unsafeCast
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import tanstack.react.router.Outlet
import tanstack.react.router.Route
import tanstack.react.router.RouterProvider
import tanstack.react.router.createRootRoute
import tanstack.react.router.createRoute
import tanstack.react.router.createRouter
import tanstack.react.router.RootRouteOptions
import tanstack.react.router.RouteOptions
import tanstack.react.router.RouterOptions
import tanstack.router.core.RoutePath
import ui.component.blogTitle
import web.cssom.*

val root = FC<Props> {
    div {
        blogTitle()
        Outlet()

        css {
            fontSize = 16.px
            fontFamily = string("\"Hiragino Kaku Gothic ProN\", \"Meiryo\", sans-serif")
            width = 90.pct
            maxWidth = 1000.px
            margin = Margin(Auto.auto, Auto.auto)
        }
    }
}

private val rootRoute = createRootRoute(
    options = unsafeJso<RootRouteOptions>().also {
        it.asDynamic().component = root
    }
)

private val entriesRoute = createRoute(
    options = unsafeJso<RouteOptions>().also {
        it.asDynamic().getParentRoute = { rootRoute }
        it.asDynamic().path = "/".unsafeCast<RoutePath>()
        it.asDynamic().component = entriesPage
    }
)

private val entryRoute = createRoute(
    options = unsafeJso<RouteOptions>().also {
        it.asDynamic().getParentRoute = { rootRoute }
        it.asDynamic().path = "entries/\$id".unsafeCast<RoutePath>()
        it.asDynamic().loader = entryLoader
        it.asDynamic().component = entryPage
    }
)

private val routeChildren = arrayOf(
    entriesRoute,
    entryRoute,
).unsafeCast<ReadonlyArray<Route>>()

private val routeTree = rootRoute.apply {
    addChildren(routeChildren)
}

val appRouter = createRouter(
    options = unsafeJso<RouterOptions>().also {
        it.asDynamic().routeTree = routeTree
    }
)

val rootPage = FC<Props> {
    RouterProvider {
        router = appRouter
    }
}
