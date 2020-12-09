package ui

import kotlinx.browser.document
import react.dom.render
import ui.page.rootPage

fun main() {
    render(document.getElementById("root")) {
        rootPage()
    }
}
