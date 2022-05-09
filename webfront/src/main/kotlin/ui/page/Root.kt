package ui.page

import kotlinx.css.*
import react.*
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter
import styled.css
import styled.styledDiv
import ui.component.blogTitle

val rootPage = fc<Props> {
    BrowserRouter {
        styledDiv {
            blogTitle()
            styledDiv {
                Routes {
                    Route {
                        attrs.index = true
                        attrs.element = entriesPage.create()
                    }
                    Route {
                        attrs.path = "/entries"
                        attrs.element = entriesPage.create()
                    }
                    Route {
                        attrs.path = "/entries/:id"
                        attrs.element = entryPage.create()
                    }
                }

                css {
                    width = 90.pct
                    maxWidth = 1000.px
                    margin(LinearDimension.auto)
                }
            }

            css {
                fontSize = 16.px
                fontFamily = "\"Hiragino Kaku Gothic ProN\", \"Meiryo\", sans-serif"
            }
        }
    }
}
