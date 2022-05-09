package lib.reactmarkdown

import react.*

@JsModule("react-markdown")
@JsNonModule
internal external object ReactMarkdown : FC<_ReactMarkdownProps> {
    override var displayName: String? = definedExternally
}

@JsModule("remark-gfm")
@JsNonModule
external val gfm: dynamic

@JsName("ReactMarkdownProps")
internal external interface _ReactMarkdownProps : Props {
    var children: String
    var plugins: Array<dynamic>
    var allowDangerousHtml: Boolean
    var renderers: dynamic
}

@JsName("_ReactMarkdownProps")
external interface ReactMarkdownProps : Props {
    var children: String
    var plugins: List<dynamic>
    var allowDangerousHtml: Boolean
    var renderers: dynamic
}

val reactMarkdown = fc<ReactMarkdownProps> { props ->
    ReactMarkdown {
        attrs.children = props.children
        attrs.plugins = props.plugins.toTypedArray()
        attrs.allowDangerousHtml = props.allowDangerousHtml
        attrs.renderers = props.renderers
    }
}
