package lib.reactsyntaxhaighlighter

import lib.reactsyntaxhaighlighter.styles.darcula
import react.RProps
import react.child
import react.functionalComponent
import reactsyntaxhighlighter.Prism

external interface PrismRenderProps : RProps {
    var language: String
    var value: String
}

val prismRender = functionalComponent<PrismRenderProps> { props ->
    child(::Prism) {
        + props.value
        attrs.language = props.language
        attrs.style = darcula
    }
}