package lib.reactmarkdown

import js.objects.Object
import lib.reactsyntaxhighlighter.Prism
import lib.reactsyntaxhighlighter.styles.darcula
import lib.rest
import react.*
import react.dom.html.ReactHTML.code
import web.cssom.ClassName
import kotlin.js.json

@JsModule("remark-gfm")
@JsNonModule
private external object RemarkGfm {
    @JsName("default")
    val remarkGfm: dynamic
}

val remarkGfm = RemarkGfm.remarkGfm

@JsModule("react-markdown")
@JsNonModule
private external object ReactMarkdown {
    @JsName("default")
    val Markdown: FC<_ReactMarkdownProps>
}

@JsName("ReactMarkdownProps")
internal external interface _ReactMarkdownProps : PropsWithChildren {
    var remarkPlugins: Array<dynamic>
    var components: dynamic
}

@JsName("_ReactMarkdownProps")
external interface ReactMarkdownProps : PropsWithChildren {
    var plugins: List<dynamic>
}

val reactMarkdown = FC<ReactMarkdownProps> { props ->
    ReactMarkdown.Markdown {
        + props.children
        remarkPlugins = props.plugins.toTypedArray()
        components = json(
            "code" to fun (props: dynamic): ReactNode {
                val children: String = props.children as String
                val className: String = props.className as String
                val rest = rest(props, "children", "className", "node")
                console.log(rest)
                val match = """language-(\w+)""".toRegex().find(className)
                return if (match != null) {
                    Prism.create {
                        console.log(this)
                        Object.assign(this, rest)
                        console.log(this)
                        + children.replace("""\n$""".toRegex(), "")
                        language = match.value
                        style = darcula
                        PreTag = "div"
                    }
                } else {
                    code.create {
                        this.className = ClassName(className)
                        + children
                    }
                }
            }
        )
    }
}
