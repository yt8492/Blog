package ui.page

import com.yt8492.blog.common.model.Entry
import js.objects.jso
import kotlinx.css.LinearDimension
import kotlinx.css.Margin
import kotlinx.css.fontFamily
import kotlinx.css.fontSize
import kotlinx.css.margin
import kotlinx.css.maxWidth
import kotlinx.css.pct
import kotlinx.css.px
import kotlinx.css.width
import react.Props
import react.create
import react.dom.html.ReactHTML.div
import react.fc
import react.router.Outlet
import react.router.RouterProvider
import react.router.dom.createBrowserRouter
import react.router.useLoaderData
import styled.css
import styled.styledDiv
import ui.component.blogTitle

val appRouter = createBrowserRouter(
    routes = arrayOf(
        jso {
            path = "/"
            element = div.create {
                fc<Props> {
                    root()
                }()
            }
            children = arrayOf(
                jso {
                    index = true
                    element = entriesPage.create()
                },
                jso {
                    path = "entries/:id"
                    loader = entryLoader
                    element = div.create {
                        fc<Props> {
                            console.log("entry page")
                            val entry = useLoaderData() as Entry?
                            console.log(entry)
                            entryPage()
                        }
                    }
                },
            )
        },
    )
)

val root = fc<Props> {
    styledDiv {
        blogTitle()
        Outlet()

        css {
            fontSize = 16.px
            fontFamily = "\"Hiragino Kaku Gothic ProN\", \"Meiryo\", sans-serif"
            width = 90.pct
            maxWidth = 1000.px
            margin = Margin(LinearDimension.auto)
        }
    }
}

val rootPage = fc<Props> {
    RouterProvider {
        attrs.router = appRouter
    }
}
