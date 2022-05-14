@file:JsModule("react-syntax-highlighter")
@file:JsNonModule
package reactsyntaxhighlighter

import react.FC
import react.Props

external object Prism : FC<PrismProps> {
    override var displayName: String? = definedExternally
}

external interface PrismProps : Props {
    var language: String
    var style: dynamic
}
