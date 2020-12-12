package ui.page

import io.ktor.http.*
import kotlinx.css.*
import react.RBuilder
import react.RProps
import react.child
import react.router.dom.hashRouter
import react.router.dom.route
import react.router.dom.switch
import styled.css
import styled.styledDiv
import ui.component.blogTitle

fun RBuilder.rootPage() {
    styledDiv {
        blogTitle()
        styledDiv {
            hashRouter {
                switch {
                    route("/", exact = true) {
                        child(entriesPage) {
                            attrs.page = 1
                        }
                    }
                    route<RProps>("/entries", exact = true) { props ->
                        child(entriesPage) {
                            attrs.page = parseQueryString(props.location.search)["page"]
                                ?.toIntOrNull() ?: 1
                        }
                    }
                    route<EntryProps>("/entries/:id", exact = true) { props ->
                        child(entryPage) {
                            attrs.id = props.match.params.id
                        }
                    }
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
        }
    }
}
