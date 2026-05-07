package ui.page

import emotion.react.css
import js.array.ReadonlyArray
import js.objects.unsafeJso
import js.reflect.unsafeCast
import react.FC
import react.Props
import react.create
import react.dom.html.ReactHTML.div
import react.router.Outlet
import react.router.RouteObject
import react.router.dom.RouterProvider
import react.router.dom.createBrowserRouter
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

val appRouter = createBrowserRouter(
    routes = arrayOf(
        unsafeJso<RouteObject>().apply {
            path = "/"
            element = root.create()
            children = arrayOf(
                unsafeJso<RouteObject>().apply {
                    index = true
                    element = entriesPage.create()
                },
                unsafeJso<RouteObject>().apply {
                    path = "entries/:id"
                    loader = entryLoader
                    element = entryPage.create()
                },
            ).unsafeCast<ReadonlyArray<RouteObject>>()
        },
    ).unsafeCast<ReadonlyArray<RouteObject>>()
)

val rootPage = FC<Props> {
    RouterProvider {
        router = appRouter
    }
}
