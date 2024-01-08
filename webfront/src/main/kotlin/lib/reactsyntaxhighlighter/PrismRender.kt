package lib.reactsyntaxhighlighter

import lib.reactsyntaxhighlighter.styles.darcula
import react.Props
import react.fc

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