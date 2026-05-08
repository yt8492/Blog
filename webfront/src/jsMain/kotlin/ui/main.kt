package ui

import react.create
import react.dom.client.createRoot
import ui.page.rootPage
import web.dom.document
import web.dom.ElementId

fun main() {
    document.getElementById(ElementId("root"))?.let {
        createRoot(it).render(rootPage.create())
    }
}
