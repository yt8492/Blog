@file:JsModule("react-syntax-highlighter")
@file:JsNonModule
package reactsyntaxhighlighter

import react.FunctionComponent
import react.RProps
import react.ReactElement

external object Prism : FunctionComponent<PrismProps>

external interface PrismProps : RProps {
    var language: String
    var style: dynamic
}
