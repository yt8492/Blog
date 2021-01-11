@file:JsModule("react-syntax-highlighter")
@file:JsNonModule
package reactsyntaxhighlighter

import react.RProps
import react.ReactElement

external fun Prism(props: PrismProps): ReactElement

external interface PrismProps : RProps {
    var language: String
    var style: dynamic
}
