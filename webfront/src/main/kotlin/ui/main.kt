package ui

import kotlinx.browser.document
import ui.page.entriesPage
import react.child
import react.dom.render

fun main() {
    render(document.getElementById("root")) {
        child(entriesPage) {
            attrs.page = 1
        }
    }
}
