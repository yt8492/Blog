package component

import org.intellij.markdown.flavours.gfm.GFMFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import react.RBuilder
import react.dom.div
import kotlin.js.json

fun RBuilder.markdown(src: String) {
    val flavor = GFMFlavourDescriptor()
    val parsedTree = MarkdownParser(flavor).buildMarkdownTreeFromString(src)
    val html = HtmlGenerator(src, parsedTree, flavor).generateHtml()
    div {
        attrs["dangerouslySetInnerHTML"] = json("__html" to html)
    }
}
