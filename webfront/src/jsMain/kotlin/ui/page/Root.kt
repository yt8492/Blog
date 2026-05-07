package ui.page

import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import tanstack.react.router.Outlet
import tanstack.react.router.RouterProvider
import tanstack.react.router.createRootRoute
import tanstack.react.router.createRoute
import tanstack.react.router.createRouter
import tanstack.react.router.RouteOptions
import tanstack.react.router.RootRouteOptions
import tanstack.react.router.RouterOptions
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

fun createAppRouter() = createRootRoute(
    options = RootRouteOptions(
        component = root,
    ),
).let { rootRoute ->
    val entriesRoute = createRoute(
        options = RouteOptions(
            getParentRoute = { rootRoute },
            path = ENTRIES_PATH,
            component = entriesPage,
        ),
    )

    val entryRoute = createRoute(
        options = RouteOptions(
            getParentRoute = { rootRoute },
            path = ENTRY_ROUTE_PATH,
            loader = entryLoader,
            component = entryPage,
        ),
    )

    rootRoute.addChildren(
        arrayOf(
            entriesRoute,
            entryRoute,
        ),
    )

    createRouter(
        options = RouterOptions(
            routeTree = rootRoute,
        ),
    )
}

val appRouter = createAppRouter()

val rootPage = FC<Props> {
    RouterProvider {
        router = appRouter
    }
}
