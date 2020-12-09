package ui.page

import io.ktor.http.*
import react.RBuilder
import react.RProps
import react.child
import react.router.dom.hashRouter
import react.router.dom.route
import react.router.dom.switch

fun RBuilder.rootPage() {
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
}