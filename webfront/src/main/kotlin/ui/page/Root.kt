package ui.page

import js.core.jso
import kotlinx.css.*
import react.*
import react.router.RouterProvider
import react.router.dom.createBrowserRouter
import styled.css
import styled.styledDiv
import ui.component.blogTitle

val appRouter = createBrowserRouter(
    routes = arrayOf(
        jso {
            index = true
            element = entriesPage.create()
        },
        jso {
            path = "entries"
            element = entriesPage.create()
            children = arrayOf(
                jso {
                    path = ":id"
                    element = entryPage.create()
                }
            )
        },
    )
)

val rootPage = fc<Props> {
    styledDiv {
        blogTitle()
        styledDiv {
            RouterProvider {
                attrs.router = appRouter
            }

            css {
                width = 90.pct
                maxWidth = 1000.px
                margin = Margin(LinearDimension.auto)
            }
        }

        css {
            fontSize = 16.px
            fontFamily = "\"Hiragino Kaku Gothic ProN\", \"Meiryo\", sans-serif"
        }
    }
}
