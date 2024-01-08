package ui

import react.create
import react.dom.client.createRoot
import ui.page.rootPage
import web.dom.document

fun main() {
    document.getElementById("root")?.let {
        createRoot(it).render(rootPage.create())
    }
}
