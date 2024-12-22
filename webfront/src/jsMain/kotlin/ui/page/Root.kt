package ui.page

import emotion.react.css
import js.objects.jso
import react.FC
import react.Props
import react.create
import react.dom.html.ReactHTML.div
import react.router.Outlet
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
        jso {
            path = "/"
            element = root.create()
            children = arrayOf(
                jso {
                    index = true
                    element = entriesPage.create()
                },
                jso {
                    path = "entries/:id"
                    loader = entryLoader
                    element = entryPage.create()
                },
            )
        },
    )
)

val rootPage = FC<Props> {
    RouterProvider {
        router = appRouter
    }
}
