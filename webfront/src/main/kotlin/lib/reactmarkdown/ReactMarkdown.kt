package lib.reactmarkdown

import react.*

@JsModule("react-markdown")
@JsNonModule
internal external object ReactMarkdown : FunctionComponent<ReactMarkdownProps>

@JsModule("remark-gfm")
@JsNonModule
external val gfm: dynamic

external interface ReactMarkdownProps : RProps {
    var children: String
    var plugins: Array<dynamic>
    var allowDangerousHtml: Boolean
    var renderers: dynamic
}

fun RBuilder.reactMarkdown(
    children: String,
    plugins: List<dynamic> = listOf(),
    allowDangerousHtml: Boolean = false,
    renderers: dynamic = null
) {
    child(ReactMarkdown) {
        attrs.children = children
        attrs.plugins = plugins.toTypedArray()
        attrs.allowDangerousHtml = allowDangerousHtml
        attrs.renderers = renderers
    }
}
