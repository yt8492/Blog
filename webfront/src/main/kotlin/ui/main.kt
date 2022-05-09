package ui

import kotlinx.browser.document
import react.create
import react.dom.client.createRoot
import ui.page.rootPage

fun main() {
    document.getElementById("root")?.let {
        createRoot(it).render(rootPage.create())
    }
}
