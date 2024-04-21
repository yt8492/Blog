package lib.reactmarkdown

import js.core.Object
import kotlinx.html.classes
import lib.reactsyntaxhighlighter.Prism
import lib.reactsyntaxhighlighter.styles.darcula
import lib.rest
import react.*
import react.dom.code
import kotlin.js.json

@JsModule("remark-gfm")
@JsNonModule
external val remarkGfm: dynamic

@JsModule("react-markdown")
@JsNonModule
internal external object Markdown : FC<_ReactMarkdownProps> {
    override var displayName: String? = definedExternally
}

@JsName("ReactMarkdownProps")
internal external interface _ReactMarkdownProps : Props {
    var children: String
    var remarkPlugins: Array<dynamic>
    var components: dynamic
}

@JsName("_ReactMarkdownProps")
external interface ReactMarkdownProps : Props {
    var children: String
    var plugins: List<dynamic>
}

val reactMarkdown = fc<ReactMarkdownProps> { props ->
    Markdown {
        attrs.children = props.children
        attrs.remarkPlugins = props.plugins.toTypedArray()
        attrs.components = json(
            "code" to fun (props: dynamic) {
                val children: String = props.children as String
                val className: String = props.className as String
                val rest = rest(props, "children", "className", "node")
                val match = """language-(\w+)""".toRegex().find(className)
                if (match != null) {
                    Prism {
                        Object.assign(attrs, rest)
                        + children.replace("""\n$""".toRegex(), "")
                        attrs.language = match.value
                        attrs.style = darcula
                        attrs.PreTag = "div"
                    }
                } else {
                    code {
                        attrs.classes = setOf(className)
                        + children
                    }
                }
            }
        )
    }
}
