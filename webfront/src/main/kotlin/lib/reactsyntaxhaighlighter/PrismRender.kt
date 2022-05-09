package lib.reactsyntaxhaighlighter

import lib.reactsyntaxhaighlighter.styles.darcula
import react.Props
import react.fc
import reactsyntaxhighlighter.Prism

external interface PrismRenderProps : Props {
    var language: String
    var value: String
}

val prismRender = fc<PrismRenderProps> { props ->
    Prism {
        + props.value
        attrs.language = props.language
        attrs.style = darcula
    }
}